package com.genesis.tictactoeAi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;

public class Main implements Runnable {

	public static Thread gameThread;

	public static void main(String[] args) {
		System.out.println("Init()");
		new Main().init();
	}

	public void init() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		double startTime = System.currentTimeMillis();
		double curTime = 0;
		boolean running;

		new Game();

		running = true;
		while (running) {
			curTime = System.currentTimeMillis();
			if (curTime - startTime > 1000.0) {
				if (Game.games.size() < 1) {
					new Game();
				}
				updateGames();
				startTime = curTime;
			}
		}
	}

	public void updateGames() {
		for (int i = 0; i < Game.games.size(); i++) {
			Game.games.get(i).update();
		}
	}

	/**
	 * Reads the JSON String from the given url and returns it as an object.
	 * 
	 * @param url
	 * @return
	 */
	public static JSONObject readFromUrl(String url) {
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
			System.err.println("Error at URL: " + url);
			output = "{status: error}";
		}
		return new JSONObject(output);
	}

}
