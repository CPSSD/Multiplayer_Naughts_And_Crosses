package com.genesis.tictactoe.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Client {

	public static ArrayList<Integer> board;

	public boolean debug = false;

	public static String readFromURL(String url) {
		url = url.replaceAll(" ", "%20");
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
