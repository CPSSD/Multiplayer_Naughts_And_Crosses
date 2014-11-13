package com.genesis.tictactoe.screen;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import com.genesis.tictactoe.Display;
import com.genesis.tictactoe.Frame;
import com.genesis.tictactoe.ImageLoader;

public class GameScreen extends Screen {

	static int[] board;
	static int gridX, gridY;
	static int tileSize, turn = 1, player;

	public static boolean playing = false;
	public static String gameID = "None";
	public static String name = "Default";
	public static String gameAddress = "http://cpssd5-web.computing.dcu.ie/TicTacToeWeb/";
	public static String newGameAddress = "newGame?";
	public static String nextAddress = "next?";
	public static String moveAddress = "move?";

	public GameScreen() {
		super("Game");
		Random r = new Random();
		name = name + (r.nextInt(899999) + 100000);
		
		// gameAddress = "http://vm1.razoft.net:1337/";
		// gameAddress = "http://cpssd6-web.computing.dcu.ie/";
		board = new int[9];
		connect(name);
	}

	public void connect(String name) {
		String url = gameAddress + newGameAddress + "name=" + name;
		System.out.println("Attempting to read from url: \n" + url);
		String text = readFromUrl(url);
		System.out.println("Starting new game...");

		JSONObject json = new JSONObject(text);
		System.out.println(text);

		if (json.has("id")) {
			gameID = json.getString("id");
			System.out.println("Game is started with id: " + gameID);
		} else {
			System.out.println("Unable to start Game");
			Display.screen = new MenuScreen();
		}
		if (json.has("letter")) {
			player = json.getInt("letter");
			System.out.println("You are player: " + player);
		}

		playing = true;
	}

	public void pingServer() {
		getBoard();
	}

	public static String readFromUrl(String url) {
		String output = "";
		try {
			URL page = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(page.openConnection().getInputStream()));
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

	public void render() {
		Display.dbGraphics.setColor(Color.white);

		tileSize = 96;
		int startX = (int) ((640 / 2) - (tileSize * 1.5));
		int startY = (int) ((480 / 2) - (tileSize * 1.2));
		Display.dbGraphics.drawImage(ImageLoader.background, 0, 0, Frame.fWidth, Frame.fHeight, null);
		Display.dbGraphics.drawImage(ImageLoader.curPlayerBackground, startX, startY - tileSize, tileSize * 3, tileSize / 2, null);
		Display.dbGraphics.setFont(new Font("Arial", 0, 32));
		Display.dbGraphics.drawString("Current Turn: " + turn, startX + tileSize / 3, (int) (startY - tileSize / 1.6));
		drawBoard(board, startX, startY);
	}

	public void drawBoard(int[] board, int startX, int startY) {
		gridX = startX - 4;
		gridY = startY - 4;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int newX = gridX + (i * tileSize) + i * 4;
				int newY = gridY + (j * tileSize) + j * 4;
				Display.drawImage(ImageLoader.tileBackground, newX, newY, tileSize, tileSize);

				switch (board[i + j * 3]) {
				case 0:
					break;
				case 1:
					Display.drawImage(ImageLoader.images[0][0], newX, newY, tileSize, tileSize);
					break;
				case 2:
					Display.drawImage(ImageLoader.images[1][0], newX, newY, tileSize, tileSize);
					break;
				}
			}
		}
	}

	public void clickPos(int x, int y) {
		int x1 = x - gridX;
		int y1 = y - gridY;
		int tileX = 0, tileY = 0;
		if (x1 > 0 && y1 > 0 && x1 < (tileSize + 4) * 3 && y1 < (tileSize + 4) * 3) {
			tileX = x1 / (tileSize + 4);
			tileY = y1 / (tileSize + 4);
			placeTile(tileX + tileY * 3);
			System.out.println("Tile Placed..");
		}
	}

	public static void parseBoard(JSONArray board) {
		int[] newBoard = new int[board.length()];
		for (int i = 0; i < board.length(); i++) {
			newBoard[i] = board.getInt(i);
		}
		GameScreen.board = newBoard;
	}

	public static void getBoard() {
		String s = readFromUrl(gameAddress + nextAddress + "id=" + gameID);
		JSONObject text = new JSONObject(s);
		// System.out.println(s);
		if (text.has("board")) {
			JSONArray board = text.getJSONArray("board");
			parseBoard(board);
		}
		if (text.has("turn")) {
			turn = text.getInt("turn");
		}
		if (text.has("winner")) {
			int winner = text.getInt("winner");
			if (winner != 0 && winner != player) {
				System.out.println("Hard Luck. Try again next time.");
				Display.screen = new MenuScreen();
			}
			if (winner == player) {
				System.out.println("Congratulations you won!");
				Display.screen = new MenuScreen();
			}
		}

	}

	public static boolean sendMove(int index) {
		String s = readFromUrl(gameAddress + moveAddress + "id=" + gameID + "&position=" + index);
		JSONObject text = new JSONObject(s);
		if (text.getString("status").equals("error")) {
			System.err.println("Error: " + text.getString("message"));
			// System.out.println(s);
		}
		return true;
	}

	public static void placeTile(int index) {
		if (board[index] == 0) {
			sendMove(index);
		}
	}
}
