package edu.dcu.cpssd.tictactoe.core;

import java.util.HashMap;
import java.util.Map;

import edu.dcu.cpssd.tictactoe.core.exceptions.GameException;

public class GameFactory {
	private Game incompleteGame = null;
	private Map<String, Game> games = new HashMap<>();
	private Map<User, Game> users = new HashMap<>();
	int count = 1;

	public Game newGame(User user) throws GameException {
		Game game;
		String id;
		if (incompleteGame == null) {
			id = getNextId();
			game = new Game(id);
			incompleteGame = game;
		} else {
			game = incompleteGame;
			id = incompleteGame.getId();
			incompleteGame = null;
		}
		
		game.addUser(user);
		games.put(id, game);
		users.put(user, game);
		return game;
	}

	public Game getGameWithId(String id) throws GameException {
		if(id == null || id.isEmpty()) {
			throw new GameException(ErrorType.MISSING_PARAMETER_IN_REQUEST);
		}
		
		Game game = games.get(id);
		if (game == null) {
			throw new GameException(ErrorType.UNKNOWN_PARAMETER_IN_REQUEST);
		}
		return game;
	}

	public Game getGameByUser(User user) {
		return users.get(user);
	}

	public String getNextId() {
		return "game-" + count++;
	}
}