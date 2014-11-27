/**
 * 
 */
package edu.dcu.cpssd.tictactoe.core.exceptions;

import org.json.JSONObject;

import edu.dcu.cpssd.tictactoe.core.ErrorType;

/**
 * @author Jennifer
 *
 */
public class GameException extends Exception {
//	private int code;
	private ErrorType errorType;
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;
	
//	public GameException(String message, int code, Throwable throwable) {
//		super(message, throwable);
//		this.code = code;
//	}
//
//	public GameException(String message, int code) {
//		super(message);
//		this.code = code;
//	}
//	
//	public int getCode() {
//		return this.code;
//	}
	
	public GameException(ErrorType errorType) {
		this.errorType = errorType;
	}

	public JSONObject getErrorType() {
		return this.errorType.toJson();
	}
}
