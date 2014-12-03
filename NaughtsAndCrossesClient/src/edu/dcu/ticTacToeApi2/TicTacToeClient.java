package edu.dcu.ticTacToeApi2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;
import org.mockito.internal.stubbing.answers.Returns;

/**
 * TicTacToe Client creates all possibles String URL to creates the connection
 * that return a JSON file.
 * @author Jennifer
 */
public class TicTacToeClient {

	public static final String VERSION_REQUEST_FORMAT = "version";
	public static final String START_GAME_REQUEST_FORMAT = "startGame?name=%s&description=%s&letter=%s&private=%s";
	public static final String START_GAME_REQUEST_WITH_PIN_FORMAT = START_GAME_REQUEST_FORMAT
			+ "&pin=&s";
	public static final String LIST_REQUEST_FORMAT = "listGames";
	public static final String JOIN_GAME_REQUEST_FORMAT = "joinGame?id=%s&name=%s";
	public static final String JOIN_GAME_WITH_PIN_REQUEST_FORMAT = JOIN_GAME_REQUEST_FORMAT
			+ "&pin=&s";
	public static final String END_GAME_REQUEST_FORMAT = "endGame?secret=%s";
	public static final String NEXT_REQUEST_FORMAT = "next?secret=%s";
	public static final String MOVE_REQUEST_FORMAT = "move?secret=%s&position=%s";
	public static ArrayList<Integer> board;
	public static Scanner in = new Scanner(System.in);

	public String versionAddress;
	public String startGameAddress;
	public String listGamesAddress;
	public String joinGameAddress;
	public String endGameAddress;
	public String next;
	public String move;

	public boolean debug = false;
	private final ServerConnector serverConnector;

	/** TicTacToeCLient Constructor creates instance of server connector */
	public TicTacToeClient() {
		this.serverConnector = new ServerConnector();
	}

	/**
	 * TicTacToeCLient Constructor creates instance of server connector
	 * 
	 * @param serverConnector
	 */
	public TicTacToeClient(ServerConnector serverConnector) {
		this.serverConnector = serverConnector;
	}

	/**
	 * Creates a connection that get game version using the URL assigned
	 * 
	 * @throws IOExeption
	 * @return JSONObject the object the specify the game version
	 */
	public JSONObject getVersion() throws IOException {
		return serverConnector.getUrl(VERSION_REQUEST_FORMAT);
	}

	/*
	 * Creates a connection that starts the game using the URL assigned*
	 * 
	 * @throws IOExeption
	 * 
	 * @return JSONObject the json object that specify the data for the game.
	 * 
	 * @param name the player game.
	 * 
	 * @param description the game description.
	 * 
	 * @param letter the letter that specify player turn, 1 for X, 2 for O.
	 * 
	 * @param isPrivate if the game is private is 1 if O not.
	 */
	public JSONObject startGame(String name, String description, String letter, String isPrivate)
			throws IOException {
		return serverConnector.getUrl(START_GAME_REQUEST_FORMAT, name, description, letter,
				isPrivate);
	}

	/*
	 * Creates a connection that starts the game using the URL assigned*
	 * 
	 * @throws IOExeption
	 * 
	 * @return JSONObject the json object that specify the data for the game.
	 * 
	 * @param name the player game.
	 * 
	 * @param description the game description.
	 * 
	 * @param letter the letter that specify player turn, 1 for X, 2 for O.
	 * 
	 * @param isPrivate if the game is private is 1 if O not.
	 * 
	 * @pin the pin that identify a private game
	 */

	public JSONObject startGame(String name, String description, String letter, String isPrivate,
			String pin) throws IOException {
		return serverConnector.getUrl(START_GAME_REQUEST_WITH_PIN_FORMAT, name, description,
				letter, isPrivate, pin);
	}

	/*
	 * Creates a connection that get a list from all games using the URL
	 * assigned*
	 * 
	 * @throws IOExeption
	 * 
	 * @return JSONObject the json object that specify the list for the game.
	 */
	public JSONObject getlistGames() throws IOException {
		return serverConnector.getUrl(LIST_REQUEST_FORMAT);

	}

	/*
	 * Creates a connection that join to a respective game using the URL
	 * assigned*
	 * 
	 * @throws IOExeption
	 * 
	 * @return JSONObject the json object that specify the data for the game.
	 * 
	 * @param id the player id.
	 * 
	 * @param name the player name.
	 */
	public JSONObject joinGame(String id, String name) throws IOException {
		return serverConnector.getUrl(JOIN_GAME_REQUEST_FORMAT, id, name);

	}

	/*
	 * Creates a connection that join to a respective game using the URL
	 * assigned*
	 * 
	 * @throws IOExeption
	 * 
	 * @return JSONObject the json object that specify the data for the game.
	 * 
	 * @param id the player id.
	 * 
	 * @param name the player name.
	 * 
	 * @param pin the pin game just if the game is private
	 */
	public JSONObject joinGame(String id, String name, String pin) throws IOException {
		return serverConnector.getUrl(JOIN_GAME_WITH_PIN_REQUEST_FORMAT, id, name, pin);

	}

	/*
	 * Creates a connection that endGame to a respective game using the URL
	 * assigned*
	 * 
	 * @throws IOExeption
	 * 
	 * @return JSONObject the json object that specify the data for the game.
	 * 
	 * @param id the player id.
	 * 
	 * @param name the player name.
	 * 
	 * @param pin the pin game just if the game is private
	 */
	public JSONObject endGame(String secret) throws IOException {
		return serverConnector.getUrl(END_GAME_REQUEST_FORMAT, secret);

	}

	/*
	 * Creates a connection next that Allows a player to determine which player
	 * should play next to a respective game, using the URL assigned
	 * 
	 * @throws IOExeption
	 * 
	 * @return JSONObject the json object that print board, status for the game.
	 * 
	 * @param secret number that identify the private game.
	 */
	public JSONObject next(String secret) throws IOException {
		return serverConnector.getUrl(NEXT_REQUEST_FORMAT, secret);

	}

	/*
	 * Creates a connection move that Allows a player to choose the respective
	 * position to play game, using the URL assigned
	 * 
	 * @throws IOExeption
	 * 
	 * @return JSONObject the json object that specify if was ok your movement.
	 * 
	 * @param secret number that identify the private game.
	 * 
	 * @param position position choosed.
	 */
	public JSONObject move(String secret, String position) throws IOException {
		return serverConnector.getUrl(MOVE_REQUEST_FORMAT, secret, position);

	}

	/*
	 * Creates a URL connection
	 * 
	 * @return String url.
	 * 
	 * @param url the url.
	 */
	public static String readFromURL(String url) {
		String output = "";
		try {
			URL page = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(page.openConnection()
					.getInputStream()));
			String input;
			while ((input = in.readLine()) != null) {
				output += input;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return output;
	}

	public static void print(String out) {
		System.out.println(out);
	}

}
