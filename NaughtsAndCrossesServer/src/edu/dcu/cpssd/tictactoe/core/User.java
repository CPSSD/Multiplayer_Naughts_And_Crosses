package edu.dcu.cpssd.tictactoe.core;

import org.json.JSONObject;

import edu.dcu.cpssd.tictactoe.core.exceptions.GameException;

public class User {
	private String name;

	public User(JSONObject jsonObject) {
		this.name = jsonObject.getString("name");
	}

	public User(String name) throws GameException {
		if (name == null || name.isEmpty()) {
			throw new GameException(ErrorType.MISSING_PARAMETER_IN_REQUEST);
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
