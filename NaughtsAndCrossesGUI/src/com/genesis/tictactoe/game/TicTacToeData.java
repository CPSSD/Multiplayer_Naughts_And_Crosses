package com.genesis.tictactoe.game;

import org.json.JSONObject;

import com.genesis.tictactoe.Frame;

public class TicTacToeData {
	
	public static String username = "Default";
	public static String description = "No Description";
	public static int letter = 1;
	public static int turn;
	public static int privateGame = 0;
	public static String pin = "0000";
	public static String id = "game-x";
	public static String secret = "NoSecretKey";
	
	public static void printDetails(){
		Frame.print(username);
		Frame.print(description);
		Frame.print(String.valueOf(letter));
		Frame.print(String.valueOf(pin));
		Frame.print(String.valueOf(privateGame));
		Frame.print(id);
		Frame.print(secret);
	}
	
	public static void startGame(){
		String URL;
		if(privateGame == 0){
			URL = "http://cpssd5-web.computing.dcu.ie/startGame?name=" + username + "&description=\"" + description + "\"&letter=" + letter + "&private=" + String.valueOf(privateGame);
			Frame.print("READING FROM:" + URL);
			JSONObject json = new JSONObject(Client.readFromURL(URL));
			if(json.getString("status").equalsIgnoreCase("okay")){
				id = json.getString("id");
				secret = json.getString("secret");
			}
		}
		else if(privateGame == 1){
			URL = "http://cpssd5-web.computing.dcu.ie/startGame?name=" + username + "&description=\"" + description + "\"&letter=" + letter + "&private=" + String.valueOf(privateGame) + "&pin=" + pin;
			Frame.print("READING FROM:" + URL);
			JSONObject json = new JSONObject(Client.readFromURL(URL));
			if(json.getString("status").equalsIgnoreCase("okay")){
				id = json.getString("id");
				secret = json.getString("secret");
			}
		}
	}
	
	public static void next(){
		String URL;
	}
}
