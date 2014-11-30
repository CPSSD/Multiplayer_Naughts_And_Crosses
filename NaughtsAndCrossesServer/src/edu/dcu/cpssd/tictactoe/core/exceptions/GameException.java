
package edu.dcu.cpssd.tictactoe.core.exceptions;

import org.json.JSONObject;

import edu.dcu.cpssd.tictactoe.core.ErrorType;

public class GameException extends Exception {

	private ErrorType errorType;

	private static final long serialVersionUID = 1L;

	public GameException(ErrorType errorType) {
		this.errorType = errorType;
	}

	public JSONObject getErrorType() {
		return this.errorType.toJson();
	}
}
