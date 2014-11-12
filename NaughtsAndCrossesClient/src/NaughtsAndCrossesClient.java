import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;


public class NaughtsAndCrossesClient {
    
    
    //0 is vacant position
    //1 is occupied by X
    //2 is occupied by O
    public static ArrayList<Integer> board;
    public static Scanner in = new Scanner(System.in);

    public static String gameID = "No Game";
    public static String turn = "0";
    
    public static String gameAddress = "http://cpssd5-web.computing.dcu.ie/TicTacToeWeb/";

    public static String newGameAddress = "newGame?";
    public static String nextAddress = "nextMove?";		//TODO: change to next once jennifer updates server side
    public static String moveAddress = "move?";
    
    public static void main(String[] args){
   	 	newGame("Test");
    	print(getBoard());
    	place("1");
    	print(getBoard());
   	}
    
    public static void print(String out){
    	System.out.println(out);
    }
    
     public static void newGame(String userName){
   	 	String webText = readFromURL(gameAddress + newGameAddress + "name=" + userName);
   	 	print("          Starting new game with link:");
   	 	print("          " + gameAddress + newGameAddress + "name=" + userName);
   	 	
   	 	JSONObject JSONWebText = new JSONObject(webText);
   	 	
   	 	if(JSONWebText.has("id")){
   	   	 	gameID = JSONWebText.getString("id");
   	   	 	print("Game is started with ID: " + gameID);
   	 	}
   	 	else{
   	 		print("          Unable to start game");
   	 	}
   	 	if(JSONWebText.has("letter")){
   	   	 	turn = String.valueOf(JSONWebText.getInt("letter"));
   	   	 	print("You are player: " + turn);
   	 	}
    }
    
    
    public static String getBoard(){
    	JSONObject JSONWebText = new JSONObject(readFromURL(gameAddress + nextAddress + "id=" + gameID));
   	 	print("          Retreiving board with link:");
   	 	print("          " + gameAddress + nextAddress + "id=" + gameID);
   	 	
    	if(JSONWebText.has("board")){
   	   	 	return String.valueOf(JSONWebText.get("board"));
   	 	}
    	else{
    		print("Unable to retrieve board");
    		return "";
    	}
    }
    
    public static void place(String pos){
    	JSONObject JSONWebText = new JSONObject(readFromURL(gameAddress + moveAddress + "id=" + gameID + "&position=" + pos));
    	if(JSONWebText.has("status")){
    		
   	   	 	if(JSONWebText.getString("status").equalsIgnoreCase("okay")){
   	   	 		print("          Big Success! Placed at " + pos);
   	   	 	}
   	   	 	else if(JSONWebText.getString("status").equalsIgnoreCase("error")){
   	   	 		print("          NOT BIG SUCCESS!! :(");
   	   	 		print(String.valueOf(JSONWebText.getInt("code")));
   	   	 		print(JSONWebText.getString("message"));
   	   	 	}
   	 	}
    	else{
    		print("Error in status report");
    	}
    }
    
    public static void placeAt(int move, int pos){
   	 if(move < 2){
   		 move = 0;
   		 System.out.println("Invalid Move!");
   	 }
   	 board.set(pos, move);
    }
    
    public static String getEmptyPositions(){
   	 String out = "";
   	 for(int i = 0; i < board.size(); i++){
   		 if(board.get(i) == 0){
   			 out = out + (i+1) + ", ";
   		 }
   	 }
   	 return out;
    }
    
    public static void printBoard(){
   	 System.out.println(board.get(0) + " , " + board.get(1) + " , " + board.get(2));
   	 System.out.println(board.get(3) + " , " + board.get(4) + " , " + board.get(5));
   	 System.out.println(board.get(6) + " , " + board.get(7) + " , " + board.get(8));
    }
    
    public static void commandLine(){
   	 	System.out.println("Select Position");
   	 	System.out.println(getEmptyPositions());
   	 	String position = in.nextLine();
   	 	int numPosition = Integer.parseInt(position) - 1;
   	 
   	 	System.out.println("Select Character to place at " + position);
   	 	System.out.println("X or O");
   	 	String character = in.nextLine();
   	 
   	 	int numCharacter = 0;
   	 	if(character.equalsIgnoreCase("X")){
   		 	numCharacter = 1;
   	 	}
   	 	else if(character.equalsIgnoreCase("O")){
   		 	numCharacter = 2;
   	 	}
   	 	else{
   		 	System.out.println("Invalid Move, Start Over");
   		 	commandLine();
   	 	}
   	 	board.set(numPosition, numCharacter);
   	 	printBoard();
   	 	commandLine();
    }
    
    public static String readFromURL(String url){
    	String output = "";
    	try {
    		URL page = new URL(url);
    		BufferedReader in = new BufferedReader(new InputStreamReader(page.openConnection().getInputStream()));
    		String input;
    		while ((input = in.readLine()) != null){
    			output += input;
    		}	
    		in.close();
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		System.exit(0);
    	}
    	return output;
    }
}
