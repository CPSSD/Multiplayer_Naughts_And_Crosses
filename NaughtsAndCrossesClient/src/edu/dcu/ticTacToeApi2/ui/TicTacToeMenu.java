package edu.dcu.ticTacToeApi2.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONObject;

import edu.dcu.ticTacToeApi2.TicTacToeClient;
/**
 * Creates a general menu that allows to play the client.
 * @author Jennifer
 *
 */
public class TicTacToeMenu {
	private final BufferedReader in;
	private final PrintWriter out;

	private final TicTacToeClient client;

	/**
	 * Menu constructor used to test 
	 * @param client client object
	 * @param inMock the input string 
	 * @param out    the output string 
	 */
	public TicTacToeMenu(final TicTacToeClient client, final BufferedReader inMock,
			final PrintWriter out) {
		this.client = client;
		this.in = inMock;
		this.out = out;
	}
	/**
	 * Menu constructor
	 * @param client
	 * @throws IOException
	 */
	public TicTacToeMenu(final TicTacToeClient client) throws IOException {
		this.client = client;
		this.in = new BufferedReader(new InputStreamReader(System.in));
		this.out = new PrintWriter(System.out);
	}

	/**
	 * Menu constructor with no parameters 
	 * @throws IOException
	 */
	public TicTacToeMenu() throws IOException {
		this(new TicTacToeClient());
	}
	/**
	 * TicTacToe Menu constructor that accept a buffer as a parameter
	 * @param in
	 */
	public TicTacToeMenu(BufferedReader in) {
		this.in = in;
		this.out = new PrintWriter(System.out);
		this.client = new TicTacToeClient();
	}
	/**
	 * Class that show all menu options 
	 * @throws IOException
	 */
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
	/**
	 * Show the start game menu
	 * @throws IOException
	 */
	public void showStartGameMenu() throws IOException {
		out.println("enter your user name: ");
		out.flush();
		String name = in.readLine();
		out.println("Enter a small description");
		out.flush();
		String description = URLEncoder.encode(in.readLine(), "utf-8");
		out.println("Which letter do you want to choose X or O");
		out.println("Choose 1 for X and 2 for O");
		out.flush();
		String letter = in.readLine();
		out.println("Do you want to play private 1 for yes or O for not");
		out.flush();
		String isPrivate = in.readLine();

		if (isPrivate.equals("1")) {
			out.println("enter pin:");
			out.flush();
			String pin = in.readLine();
			JSONObject response = client.startGame(name, description, letter, isPrivate, pin);
			out.println(response.toString());
			out.flush();
		} else {
			JSONObject response = client.startGame(name, description, letter, isPrivate);
			out.println(response.toString());
			out.flush();
		}
		out.println("Do you want to see the menu again y/n");
		out.flush();
		String yesOrNotShowMenu = in.readLine();
		if (yesOrNotShowMenu.equalsIgnoreCase("y")) {
			showMenu();
		}
		if (yesOrNotShowMenu.equalsIgnoreCase("n")) {
			System.out.println("just exit press ctrl+z");
		} else {
			System.out.println("OOOO option not available");
		}

	}
	/**
	 * Show the version game
	 * @throws IOException
	 */
	public void getVersion() throws IOException {
		JSONObject response = client.getVersion();
		out.println(response.toString());
		out.flush();
		out.println("Do you want to see the menu again y/n");
		out.flush();
		String yesOrNotShowMenu = in.readLine();
		if (yesOrNotShowMenu.equalsIgnoreCase("y")) {
			showMenu();
		}
		if (yesOrNotShowMenu.equalsIgnoreCase("n")) {
			System.out.println("just exit press ctrl+z");
		} else {
			System.out.println("OOOO option not available");
		}

	}

	/**
	 * Show a list of games
	 * @throws IOException
	 */
	public void getListGames() throws IOException {
		JSONObject response = client.getlistGames();
		out.println(response.toString());
		out.flush();
		out.println("Do you want to see the menu again y/n");
		out.flush();
		String yesOrNotShowMenu = in.readLine();
		if (yesOrNotShowMenu.equalsIgnoreCase("y")) {
			showMenu();
		}
		if (yesOrNotShowMenu.equalsIgnoreCase("n")) {
			System.out.println("just exit press ctrl+z");
		} else {
			System.out.println("OOOO option not available");
		}

	}
	/**
	 * Show join game menu
	 * @throws IOException
	 */
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
		out.println("if pin, enter pin: ");
		out.flush();
		pin = in.readLine();
		if (pin == null || pin.isEmpty()) {
			JSONObject response = client.joinGame(id, name);
			out.println(response.toString());
			out.flush();
		} else {
			JSONObject response = client.joinGame(id, name, pin);
			out.println(response.toString());
			out.flush();
		}

		out.println("Do you want to see the menu again y/n");
		out.flush();
		String yesOrNotShowMenu = in.readLine();
		if (yesOrNotShowMenu.equalsIgnoreCase("y")) {
			showMenu();
		}
		if (yesOrNotShowMenu.equalsIgnoreCase("n")) {
			System.out.println("just exit press ctrl+z");
		} else {
			System.out.println("OOOO option not available");
		}

	}
	/**
	 * 
	 * @throws IOException
	 */
	public void nextMenu() throws IOException {
		out.println("enter your secret game");
		out.flush();
		String secret;
		secret = in.readLine();
		JSONObject response = client.next(secret);
		out.println(response.toString());
		out.flush();
		out.println("Do you want to see the menu again y/n");
		out.flush();
		String yesOrNotShowMenu = in.readLine();
		if (yesOrNotShowMenu.equalsIgnoreCase("y")) {
			showMenu();
		}
		if (yesOrNotShowMenu.equalsIgnoreCase("n")) {
			System.out.println("just exit press ctrl+z");
		} else {
			System.out.println("OOOO option not available");
		}

	}

	/***
	 * show if the move option was fine
	 * @throws IOException
	 */
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
		out.println("Do you want to see the menu again y/n");
		out.flush();
		String yesOrNotShowMenu = in.readLine();
		if (yesOrNotShowMenu.equalsIgnoreCase("y")) {
			showMenu();
		}
		if (yesOrNotShowMenu.equalsIgnoreCase("n")) {
			System.out.println("just exit press ctrl+z");
		} else {
			System.out.println("OOOO option not available");
		}

	}

	public void endGame() throws IOException {
		out.println("endGame?");
		out.println("secret");
		out.flush();
		String secret = in.readLine();

		JSONObject response = client.endGame(secret);
		out.println(response.toString());
		out.flush();
		out.println("Do you want to see the menu again y/n");
		out.flush();
		String yesOrNotShowMenu = in.readLine();
		if (yesOrNotShowMenu.equalsIgnoreCase("y")) {
			showMenu();
		}
		if (yesOrNotShowMenu.equalsIgnoreCase("n")) {
			System.out.println("just exit press ctrl+z");
		} else {
			System.out.println("OOOO option not available");
		}

	}
}
