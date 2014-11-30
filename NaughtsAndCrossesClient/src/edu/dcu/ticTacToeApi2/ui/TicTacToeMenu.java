package edu.dcu.ticTacToeApi2.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import org.json.JSONObject;

import edu.dcu.ticTacToeApi2.TicTacToeClient;

public class TicTacToeMenu {
	private final BufferedReader in;
	private final PrintWriter out;

	private final TicTacToeClient client;

	public TicTacToeMenu(final TicTacToeClient client, final BufferedReader inMock,
			final PrintWriter out) {
		this.client = client;
		this.in = inMock;
		this.out = out;
	}

	public TicTacToeMenu(final TicTacToeClient client) throws IOException {
		this.client = client;
		this.in = new BufferedReader(new InputStreamReader(System.in));
		this.out = new PrintWriter(System.out);
	}

	public TicTacToeMenu() throws IOException {
		this(new TicTacToeClient());
	}

	public TicTacToeMenu(BufferedReader in) {
		this.in = in;
		this.out = new PrintWriter(System.out);
		this.client = new TicTacToeClient();
	}

	public void showMenu() throws IOException {
		boolean exitOfMenu = false;

		do {
			out.println("Which operation do you want?");
			out.println("start game press: s");
			out.println("get version game press: v");
			out.println("print list of games press: l");
			out.println("join to a game press: j");
			out.println("end Game press: e");
			out.println("next: n");
			out.println("move: m");
			out.flush();
			String operation = in.readLine();
			switch (operation.toLowerCase()) {
			case "s":
				showStartGameMenu();
				exitOfMenu = false;
				break;
			case "v":
				getVersion();
				exitOfMenu = false;
				break;
			case "l":
				getListGames();
				exitOfMenu = false;
				break;
			case "j":
				showJoinGameMenu();
				exitOfMenu = false;
				break;
			case "e":
				endGame();
				exitOfMenu = false;
				break;
			case "n":
				nextMenu();
				exitOfMenu = false;
				break;
			case "m":
				move();
				exitOfMenu = false;
				break;
			default:
				out.println(operation + "is not a option, please try again");
				out.flush();
				exitOfMenu = true;
			}
		} while (exitOfMenu);
	}

	public void showStartGameMenu() throws IOException {
		out.println("enter your user name: ");
		out.flush();
		String name = in.readLine();
		out.println("Enter a small description");
		out.flush();
		String description = in.readLine();
		out.println("Which letter do you want to choose X or O");
		out.println("Choose 1 for X and 2 for O");
		out.flush();
		String letter = in.readLine();
		out.println("Do you want play private 1 for yes or O for not");
		out.flush();
		String isPrivate = in.readLine();

		JSONObject response = client.startGame(name, description, letter, isPrivate);
		out.println(response.toString());
		out.flush();
	}

	public void getVersion() throws IOException {
		JSONObject response = client.getVersion();
		out.println(response.toString());
		out.flush();
	}

	public void getListGames() throws IOException {
		JSONObject response = client.getlistGames();
		out.println(response.toString());
		out.flush();
	}

	public void showJoinGameMenu() throws IOException {
		String id;
		String name;
		String pin;
		boolean isNotOption;

		out.println("Please enter id:");
		out.flush();
		id = in.readLine();
		out.println("Please enter name:");
		out.flush();
		name = in.readLine();
		out.println("Do you want to add personal pin: press y/n");
		out.println("enter pin: (just 4 decimal digits)");
		out.flush();
		pin = in.readLine();
		JSONObject response = client.joinGame(id, name);
		out.println(response.toString());
		out.flush();
	}

	public void nextMenu() throws IOException {
		out.println("enter your secret game");
		out.flush();
		String secret;
		secret = in.readLine();
		JSONObject response = client.next(secret);
		out.println(response.toString());
		out.flush();
	}

	public void move() throws IOException {
		out.println("move?");
		out.println("enter secret: ");
		out.flush();
		String secret = in.readLine();
		out.println("position: ");
		out.flush();
		String position = in.readLine();

		JSONObject response = client.move(secret, position);
		out.println(response.toString());
		out.flush();
	}

	public void endGame() throws IOException {
		out.println("endGame?");
		out.println("secret");
		out.flush();
		String secret = in.readLine();

		JSONObject response = client.endGame(secret);
		out.println(response.toString());
		out.flush();
	}
}
