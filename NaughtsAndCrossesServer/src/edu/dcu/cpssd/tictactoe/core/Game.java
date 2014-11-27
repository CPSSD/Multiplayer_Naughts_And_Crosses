package edu.dcu.cpssd.tictactoe.core;

import java.util.ArrayList;

import edu.dcu.cpssd.tictactoe.core.exceptions.GameException;

public class Game {
	final ArrayList<User> users;
	final Board board;
	String id;
	int turn = 1;
	int winner = 0;
	boolean first = false;

	public Game(String id) throws GameException {
		if (id == null || id.isEmpty()) {
			throw new GameException(ErrorType.MISSING_PARAMETER_IN_REQUEST);
		}
		this.id = id;
		this.users = new ArrayList<User>(2);
		this.board = new Board();
	}

	public Game(ArrayList<User> users, Board board) {
		this.users = users;
		this.board = board;
	}

	public Game(String id, Board board) throws GameException {
		this.id = id;
		this.users = new ArrayList<>(2);
		this.users.add(new User("test"));
		this.users.add(new User("test2"));
		this.board = board;
	}

	public ArrayList<User> addUser(User user) {
		users.add(user);
		return users;
	}

	public Game createNewGame(String name) throws GameException {
		User user = new User(name);
		users.add(user);
		return this;
	}

	public int getUserTurn(User user) {
		return users.indexOf(user) + 1;
	}

	public int[] move(int position, User user) throws GameException {
		int turn = getUserTurn(user);
		// TODO check if turn is wrong
		if (turn != this.turn) {
			throw new GameException(ErrorType.NOT_PLAYERS_TURN);
		}
		int[] positions = board.move(position, turn);
		this.turn = turn == 1 ? 1 : 2;
		return positions;
	}

	public boolean isWinner(int turn) {
		if (winner != 0) {
			return turn == winner;
		}
		boolean isWinner = board.isWinner(turn);
		if (isWinner) {
			this.winner = turn;
		}
		return isWinner;
	}

	public Board getBoard() {
		return this.board;
	}

	public int getTurn() {
		return this.turn;
	}

	public int getWinner() {
		for (int i = 1; i <= 2; i++) {
			if(isWinner(i)){
				return turn;
				
			}
		}
		return 0;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public String getId() {
		return this.id;
	}
}
