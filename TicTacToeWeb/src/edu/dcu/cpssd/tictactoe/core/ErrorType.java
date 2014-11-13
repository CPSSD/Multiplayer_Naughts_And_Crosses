package edu.dcu.cpssd.tictactoe.core;

import org.json.JSONObject;

public enum ErrorType {

	OTHER_ERROR(100, "Other error"), MISSING_PARAMETER_IN_REQUEST(101,
			"Missing parameter in request"), UNKNOWN_PARAMETER_IN_REQUEST(102,
			"Unknown parameter in request"), UNKNOWN_REQUEST(103, "UnKnown request"), SERVER_NOT_READY(
			104, "Server not ready or busy"), NOT_PLAYERS_TURN(105, "Not players turn");

	private String errorMessage;
	private Integer errorCode;

	ErrorType(final int errorCode, final String errorMessage) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public JSONObject toJson() {
		return new JSONObject().put("code", errorCode)
				.put("message", errorMessage).put("status", "error");
	}
}
