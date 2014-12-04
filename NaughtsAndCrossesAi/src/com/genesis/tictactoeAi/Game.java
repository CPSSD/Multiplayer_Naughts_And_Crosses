package com.genesis.tictactoeAi;

import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class Game {

	public static ArrayList<Game> games = new ArrayList<Game>();
	private static String gameIp = "http://cpssd5-web.computing.dcu.ie";
	private static String startGame = "/startGame?";
	private static String endGame = "/endGame?";
	private static String move = "/move?";
	private static String next = "/next?";
	private static String name;
	private static String defaultName = "Ai%20";
	private static int id = 1;
	private static String description;
	private static String defaultDescription = "Ai%20Game%20";
	private String secret;
	private int letter;
	private boolean isFinished;
	private Random r;

	public Game() {
		name = defaultName + (id);
		description = defaultDescription + (id);
		id++;
		System.out.println("Created: " + name + " | Secret: " + secret);
		games.add(this);
		r = new Random();
		createGame(r.nextInt(2) + 1);
	}

	/**
	 * Creates a game with the ai as the specified letter.
	 * 
	 * @param letter
	 *            The letter of the AI.
	 */
	private void createGame(int letter) {
		JSONObject game = Main.readFromUrl(gameIp + startGame + "name=" + name + "&description=\"" + description + "\"&letter=" + letter + "&private=0");
		if (game.getString("status").equals("okay")) {
			secret = game.getString("secret");
			this.letter = letter;
		}
		System.out.println("Game Created... Secret = " + secret);
	}

	/**
	 * Pings to server to retrive the current game data.
	 * 
	 * @return Game Data.
	 */
	private JSONObject pingServer() {
		return Main.readFromUrl(gameIp + next + "secret=" + secret);
	}

	/**
	 * Ends the current game.
	 */
	private void endGame() {
		Main.readFromUrl(gameIp + endGame + "secret=" + secret);
		games.remove(this);
	}

	/**
	 * Attempts to make a move at the given position.
	 * 
	 * @param position
	 */
	private void move(int position) {
		System.out.println("Attempting to make a move at pos: " + position);
		Main.readFromUrl(gameIp + move + "secret=" + secret + "&position=" + position);
	}

	/**
	 * Attempts to find the best possible moves the ai can make.
	 * 
	 * @param intBoard
	 *            An integer array containing the board.
	 * @param index
	 *            Index of the current move.
	 * @param letter
	 *            Letter of current player
	 * @param depth
	 *            How many turns from the original check.
	 * @return
	 */
	private int updateMoveSet(int[] intBoard, int index, int letter, int depth) {
		int[] newBoard = cloneBoard(intBoard);
		if (newBoard[index] == 0) {
			newBoard[index] = letter;
		} else {
			return -99;
		}
		// displayBoard(newBoard, depth);
		int best = -99;
		boolean full = true;
		for (int i = 0; i < 8; i++) {
			if (newBoard[i] == 0) {
				full = false;
			}
		}
		if (checkWinner(newBoard) != 0) {
			return checkWinner(newBoard);
		}
		if (full) {
			// System.out.println("Value: " + checkWinner(newBoard) * (depth +
			// 1));
			int win = checkWinner(newBoard);
			if (win != 0) {
				return win;
			}
		}
		for (int i = 0; i < 8; i++) {
			int a = updateMoveSet(newBoard, i, nextLetter(letter), depth + 1);
			if (a > best) {
				best = a;
			}
		}
		return best;
	}

	/**
	 * Checks the winner of the game.
	 * 
	 * @param board
	 * @return an Integer denoting the winner.
	 */
	private int checkWinner(int[] board) {
		int won = 0;
		for (int i = 0; i < 3; i++) {
			if (board[i * 3 + 0] != 0 && board[i * 3 + 0] == board[i * 3 + 1] && board[i * 3 + 0] == board[i * 3 + 2]) {
				won = board[i * 3 + 0];
			}

			if (board[0 * 3 + i] != 0 && board[0 * 3 + i] == board[1 * 3 + i] && board[0 * 3 + i] == board[2 * 3 + i]) {
				won = board[0 * 3 + i];
			}
		}

		if (board[0] != 0 && board[0] == board[4] && board[0] == board[8]) {
			won = board[0];
		}

		if (board[2] != 0 && board[2] == board[4] && board[2] == board[6]) {
			won = board[2];
		}

		if (won == letter) {
			return 10 - getMovesMade(board);
		} else if (won == nextLetter(letter)) {
			return -10 + getMovesMade(board);
		} else {
			return 0;
		}
	}

	/**
	 * Gets the next person to make a move.
	 * 
	 * @param letter
	 * @return
	 */
	private int nextLetter(int letter) {
		if (letter == 2) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * Converts a JSONArray into an Integer Array.
	 * 
	 * @param board
	 * @return Integer Array
	 */
	private int[] toIntArray(JSONArray board) {
		int[] array = new int[board.length()];
		for (int i = 0; i < board.length(); i++) {
			array[i] = board.getInt(i);
		}
		return array;
	}

	/**
	 * Clones an integer array.
	 * 
	 * @param board
	 * @return
	 */
	private int[] cloneBoard(int[] board) {
		int[] array = new int[board.length];
		for (int i = 0; i < board.length; i++) {
			array[i] = board[i];
		}
		return array;
	}

	/**
	 * Checks if there are any good moves for the ai to make. (Prevents a bug
	 * from occuring when the ai thinks there is nothing he can do and refuses
	 * to draw)
	 * 
	 * @param moves
	 * @return
	 */
	private boolean hasGoodMoves(int[] moves) {
		for (int i = 0; i < moves.length; i++) {
			if (moves[i] != -99) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the best move based on the values of the MoveSet.
	 * @param moveSet
	 * @param board
	 * @return
	 */
	private int getBestMove(int[] moveSet, int[] board) {
		int bestMove = -99;
		int bestMoveIndex = -1;
		if (!hasGoodMoves(moveSet)) {
			return getRandomEmptyPos(board);
		}
		for (int i = 0; i < moveSet.length; i++) {
			if (moveSet[i] > bestMove) {
				bestMove = moveSet[i];
				bestMoveIndex = i;
			}
		}
		return bestMoveIndex;
	}

	/**
	 * Gets a random empty position on the board.
	 * @param board
	 * @return
	 */
	private int getRandomEmptyPos(int[] board) {
		for (int i = 0; i < board.length; i++) {
			if (board[i] == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Counts the total moves that have been made so far.
	 * @param board
	 * @return
	 */
	private int getMovesMade(int[] board) {
		int tick = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i] != 0) {
				tick++;
			}
		}
		return tick;
	}

	/**
	 * Updates the game.
	 * Called from the Main function.
	 */
	public void update() {
		JSONObject info = pingServer();
		JSONArray board = null;
		isFinished = false;
		int[] moveSet;

		if (info.has("winner")) {
			System.out.println("AI-" + id + " | Results: Winner=" + info.getInt("winner"));
			isFinished = true;
		}
		if (isFinished) {
			endGame();
		}
		if (!isFinished && info.has("board")) {
			board = info.getJSONArray("board");
		}
		if (!isFinished && info.has("turn")) {
			if (info.getInt("turn") == letter) {
				if (board != null) {
					moveSet = new int[9];

					if (getMovesMade(toIntArray(board)) == 0) {
						move(4);
					} else {
						int[] b = toIntArray(board);

						for (int i = 0; i < 9; i++) {
							moveSet[i] = updateMoveSet(b, i, letter, 0);
						}
						move(getBestMove(moveSet, b));
						for (int i = 0; i < 9; i++) {
							moveSet[i] = updateMoveSet(b, i, letter, 0);
						}
					}
				}
			}
		}
	}
}
