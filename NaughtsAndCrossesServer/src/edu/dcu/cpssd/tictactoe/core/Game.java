package edu.dcu.cpssd.tictactoe.core;

import java.util.ArrayList;

import org.json.JSONObject;

import edu.dcu.cpssd.tictactoe.core.exceptions.GameException;
import edu.dcu.cpssd.tictactoe.servlet.ListGames;

/**
 * Handles all details relating to the TicTacToe Game.
 */
public class Game {

	public String[] secret;
	public String[] names;
	private static int lastSecret = 0;

	public int hostLetter;
	public int currentTurn;
	private int winner = -1;
	public boolean isFull, isOver, isPrivate;
	private int[] board;
	private String pin;

	private int gameId;
	private static int lastId;

	public String description;

	public static ArrayList<Game> games = new ArrayList<Game>();

	/**
	 * Creates a private Tic-Tac-Toe game.
	 * 
	 * @param name
	 *            Name of player creating the game.
	 * @param description
	 *            Description for the game.
	 * @param letter
	 *            Letter of the host. (1 for X, 2 for 0)
	 * @param pin
	 *            The pin used by another user to join the private game.
	 */
	public Game(String name, String description, int letter, String pin) {
		this.secret = new String[2];
		this.names = new String[2];
		this.secret[letter - 1] = generateSecret();
		this.names[letter - 1] = name;
		this.description = description;
		this.hostLetter = letter;
		this.isPrivate = true;
		this.pin = pin;
		initGame();
	}

	/**
	 * Creates a public Tic-Tac-Toe game.
	 * 
	 * @param name
	 *            Name of player creating the game.
	 * @param description
	 *            Description for the game.
	 * @param letter
	 *            Letter of the host. (1 for X, 2 for O);
	 */
	public Game(String name, String description, int letter) {
		this.secret = new String[2];
		this.names = new String[2];
		this.secret[letter - 1] = generateSecret();
		this.names[letter - 1] = name;
		this.description = description;
		this.hostLetter = letter;
		this.isPrivate = false;
		initGame();
	}

	/**
	 * Initialises the game. Called from constructor.
	 */
	private void initGame() {
		board = new int[9];
		gameId = lastId + 1;
		lastId += 1;
		currentTurn = 1;
		isFull = false;
		isOver = false;
		games.add(this);
	}

	/**
	 * Returns the games id.
	 * 
	 * @return The id of the game.
	 * @see String
	 */
	public String getId() {
		System.out.println("From Game(): game-" + gameId + " | lastId: " + lastId);
		return "game-" + gameId;
	}

	/**
	 * Exits the current game.
	 * 
	 * @param secret
	 *            the secret of the player ending the game.
	 * @throws GameException
	 */
	public void exitGame(String secret) throws GameException {
		if (this.secret[0].equals(secret) || this.secret[1].equals(secret)) {
			endGame();
		}
	}

	/**
	 * Ends the game. Called when a player ends the game early or there is a
	 * winner.
	 */
	private void endGame() {
		isOver = true;
		winner = 0;
	}

	/**
	 * Returns the game assigned to the id parameter.
	 * 
	 * @param id
	 *            The id of the game you are looking for.
	 * @return The game with the specified id.
	 * @see Game
	 */
	public static Game getGameById(String id) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getId().equals(id)) {
				return games.get(i);
			}
		}
		return null;
	}

	/**
	 * Attempts to make a move at the given position for the player specified by
	 * the secret.
	 * 
	 * @param position
	 *            Position on the board.
	 * @param secret
	 *            Secret of the player making the move.
	 * @throws GameException
	 */
	public void move(String position, String secret) throws GameException {
		if (isOver) {
			throw new GameException(ErrorType.OTHER_ERROR);
		}
		if (this.secret[currentTurn - 1].equals(secret)) {
			int pos = Integer.parseInt(position);
			if (pos >= 0 && pos < board.length && board[pos] == 0) {
				board[pos] = currentTurn;
				nextTurn();
			} else {
				throw new GameException(ErrorType.OTHER_ERROR);
			}
		} else {
			throw new GameException(ErrorType.NOT_PLAYERS_TURN);
		}
	}

	/**
	 * Checks if either player has won the game. If a winner is found it ends
	 * the game.
	 */
	public void checkWinner() {
		for (int i = 0; i < 3; i++) {
			if (board[i * 3 + 0] != 0 && board[i * 3 + 0] == board[i * 3 + 1] && board[i * 3 + 0] == board[i * 3 + 2]) {
				System.out.println("Winner is: " + board[i * 3 + 0]);
				gameWon(board[i * 3 + 0]);
			}
			if (board[0 * 3 + i] != 0 && board[0 * 3 + i] == board[1 * 3 + i] && board[0 * 3 + i] == board[2 * 3 + i]) {
				System.out.println("Winner is: " + board[0 * 3 + i]);
				gameWon(board[0 * 3 + i]);
			}
			if (board[0] != 0 && board[0] == board[4] && board[0] == board[8]) {
				gameWon(board[0]);
			}
			if (board[2] != 0 && board[2] == board[4] && board[2] == board[6]) {
				gameWon(board[2]);
			}
		}
	}

	/**
	 * Ends the game. Specifying the winner as the winner of the game.
	 * 
	 * @param winner
	 *            letter of the player that won the game.
	 */
	public void gameWon(int winner) {
		this.winner = winner;
		System.out.println("Winner is: " + winner);
		isOver = true;
	}

	/**
	 * Sets the current turn to the next player.
	 */
	public void nextTurn() {
		checkWinner();
		if (!isOver) {
			if (currentTurn == 1) {
				currentTurn = 2;
			} else {
				currentTurn = 1;
			}
		}
	}

	/**
	 * Returns the game containing the player with the secret specified.
	 * 
	 * @param secret
	 *            The secret of the players game..
	 * @return The game with containing the player with that secret.
	 * @see Game
	 */
	public static Game getGameBySecret(String secret) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i) != null && (games.get(i).secret[0].equals(secret) || games.get(i).secret[1].equals(secret))) {
				return games.get(i);
			}
		}
		return null;
	}

	/**
	 * Generates a secret for a player.
	 * 
	 * @return a secret used to interact with a game.
	 */
	public static String generateSecret() {
		return "" + (lastSecret++);
	}

	/**
	 * Adds a player to the game.
	 * 
	 * @param name
	 *            Name of the player
	 * @param secret
	 *            Secret of the player. Used to interact with the game.
	 */
	public void addPlayer(String name, String secret) {
		if (!isFull) {
			this.names[getOtherLetter() - 1] = name;
			this.secret[getOtherLetter() - 1] = secret;
			isFull = true;
		}
	}

	/**
	 * Gets the letter that will be assigned to the second player.
	 * 
	 * @return An integer denoting the letter the player will play as.
	 */
	public int getOtherLetter() {
		if (hostLetter == 1) {
			return 2;
		} else {
			return 1;
		}
	}

	/**
	 * Gets the winner.
	 * 
	 * @return The winner of the game.
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * Gets the board.
	 * 
	 * @return The game board.
	 */
	public int[] getBoard() {
		return board;
	}

	/**
	 * Gets the pin used if the game is a private game.
	 * 
	 * @return the pin used to join the private game.
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * Creates a JSONOBject containing details about the current game..
	 * 
	 * @return JSONObject containing information about the game.
	 * @throws GameException
	 * @see ListGames
	 */
	public JSONObject getInfo() throws GameException {
		JSONObject a = new JSONObject();
		a.put("id", getId());
		a.put("name", names[hostLetter - 1]);
		a.put("description", description);
		a.put("letter", getOtherLetter());
		if (isPrivate) {
			a.put("private", 1);
		} else {
			a.put("private", 0);
		}
		return a;
	}
}
