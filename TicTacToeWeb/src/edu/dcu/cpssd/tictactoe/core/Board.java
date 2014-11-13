package edu.dcu.cpssd.tictactoe.core;

import edu.dcu.cpssd.tictactoe.core.exceptions.GameException;

public class Board {
	private int[] positions;

	public Board() {
		this.positions = new int[9];
	}

	public Board(int[] positions) {
		this.positions = positions;
	}

	public int[] getPositions() {
		return positions;
	}

	public int[] move(int position, int turn) throws GameException {
		// TODO jennifer 5/11/2014 check length and throw exceptions

		if(positions[position] != 0){
			throw new GameException(ErrorType.OTHER_ERROR);
		}
		if (position < positions.length && position >= 0) {
			positions[position] = turn;
		}else{
			throw new GameException(ErrorType.UNKNOWN_PARAMETER_IN_REQUEST);
		}
		return getPositions();
	}

	public boolean isWinner(int turn) {
		if (positions[0] == turn && positions[0] == positions[4] && positions[4] == positions[8]
				|| positions[0] == turn && positions[0] == positions[1]
				&& positions[1] == positions[2] || positions[3] == turn
				&& positions[3] == positions[4] && positions[4] == positions[5]
				|| positions[6] == turn && positions[6] == positions[7]
				&& positions[7] == positions[8]) {
			return true;
		}
		return false;
	}
}
