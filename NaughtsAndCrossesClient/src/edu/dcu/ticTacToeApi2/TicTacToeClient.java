package edu.dcu.ticTacToeApi2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

public class TicTacToeClient {

	public static final String VERSION_REQUEST_FORMAT = "version";
	public static final String START_GAME_REQUEST_FORMAT = "startGame?name=%s&description=%s&letter=%s&private=%s";
	public static final String START_GAME_REQUEST_WITH_PIN_FORMAT = START_GAME_REQUEST_FORMAT
			+ "&pin=&s";
	public static final String LIST_REQUEST_FORMAT = "listGames";
	public static final String JOIN_GAME_REQUEST_FORMAT = "joinGame?id=%s&name=%s";
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

	public TicTacToeClient() {
		this.serverConnector = new ServerConnector();
	}

	public TicTacToeClient(ServerConnector serverConnector) {
		this.serverConnector = serverConnector;
	}

	public JSONObject getVersion() throws IOException {
		return serverConnector.getUrl(VERSION_REQUEST_FORMAT);
	}

	public JSONObject startGame(String name, String description, String letter, String isPrivate)
			throws IOException {
		return serverConnector.getUrl(START_GAME_REQUEST_FORMAT, name, description, letter,
				isPrivate);
	}

	public JSONObject startGame(String name, String description, String letter, String isPrivate,
			String pin) throws IOException {
		return serverConnector.getUrl(START_GAME_REQUEST_WITH_PIN_FORMAT, name, description,
				letter, isPrivate, pin);
	}

	public JSONObject getlistGames() throws IOException {
		return serverConnector.getUrl(LIST_REQUEST_FORMAT);

	}

	public JSONObject joinGame(String id, String name) throws IOException {
		return serverConnector.getUrl(JOIN_GAME_REQUEST_FORMAT, id, name);

	}

	public JSONObject endGame(String secret) throws IOException {
		return serverConnector.getUrl(END_GAME_REQUEST_FORMAT, secret);

	}

	public JSONObject next(String secret) throws IOException {
		return serverConnector.getUrl(NEXT_REQUEST_FORMAT, secret);

	}

	public JSONObject move(String secret, String position) throws IOException {
		return serverConnector.getUrl(MOVE_REQUEST_FORMAT, secret, position);

	}

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
