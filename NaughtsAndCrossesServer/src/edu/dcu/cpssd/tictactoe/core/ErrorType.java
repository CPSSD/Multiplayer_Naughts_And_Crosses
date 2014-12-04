package edu.dcu.cpssd.tictactoe.core;

import org.json.JSONObject;

public enum ErrorType {

	OTHER_ERROR(100, "Other error"), MISSING_PARAMETER_IN_REQUEST(101, "Missing parameter in request"), UNKNOWN_PARAMETER_IN_REQUEST(102, "Unknown parameter in request"), UNKNOWN_REQUEST(103, "Unknown request"), SERVER_NOT_READY(104, "Server not ready or busy"), NOT_PLAYERS_TURN(105, "Not players turn"), INVALID_MOVE(106, "Invalid Move"), NO_SUCH_GAME(107, "No game found"), GAME_FULL(108, "Game already has two players");

	private String errorMessage;
	private Integer errorCode;

	/**
	 * Creates an Error of the given code and type.
	 * 
	 * @param errorCode
	 * @param errorMessage
	 */
	ErrorType(final int errorCode, final String errorMessage) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	/**
	 * Creates a JSONObject containing the error code and message.
	 * 
	 * @return The error as a JSONObject
	 * @see JSONObject
	 */
	public JSONObject toJson() {
		return new JSONObject().put("code", errorCode).put("message", errorMessage).put("status", "error");
	}
}
