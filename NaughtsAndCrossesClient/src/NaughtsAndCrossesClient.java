import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class NaughtsAndCrossesClient {
	
	
	//0 is vacant position
	//1 is occupied by X
	//2 is occupied by O 
	public static ArrayList<Integer> Board;
	public static Scanner in = new Scanner(System.in);

	public static String GameID = "No Game";
	public static String Move = "0";
	
	public static String GameAddress = "http://cpssd5-web.computing.dcu.ie/TicTacToeWeb/";

	public static String newGameAddress = "newGame?name=";
	public static String nextAddress = "next?id=";
	public static String moveAddress = "move?id=" + GameID + "&position=";
	
	public static void main(String[] args){
		//Board  = new ArrayList<Integer>();
		//initializeBoard();
		//printBoard();
		//commandLine();
		
		//newGame("Generik_User_Name");
		
		int n = newGameAddress("Test").length;
		
	}
	
	/*
	 * Returns 3 messages:
	 * 1: Status: "okay"/"error"
	 * 2: id: game-n				/code: <Error Code>
	 * 3: letter: 1/ 2				/Message: "..."
	*/
	public static String[] newGameAddress(String userName){
		
		String WebText = "'status':'oky'," + readFromURL(GameAddress + newGameAddress + userName);
		
		String WebText1 = readFromURL("file:///C:/Users/James/Desktop/MockWebsite/newGame!name=james.html");
		String[] Response = WebText1.substring(1, WebText1.length() - 1).split(",");
		
		String[] Output = new String[3];
		
		Output[0] = Response[0].substring(Response[0].indexOf(":") + 2, Response[0].length() - 1);
		Output[1] = Response[1].substring(Response[1].indexOf(":") + 2, Response[1].length() - 1);
		Output[2] = Response[2].substring(Response[2].indexOf(":") + 1, Response[2].length());
		
		return Output;
	}
	
	/*
	 * Returns 3 messages:
	 * 1: Status: "okay"/"error"
	 * 2: turn: 1/2					/code: <Error Code>
	 * 3: board: [0, 0 , ..., 0]	/Message: "..."
	*/
	public static String[] nextAddress(){
		
		
		String[] Output = new String[3];
		
		return Output;
	}
	
	
	public static void newGame(String userName){
		String Status = "";
		String WebText = readFromURL(GameAddress + newGameAddress + userName);
		String[] Response = WebText.substring(1, WebText.length() - 1).split(",");
		
		Status = Response[0].substring(Response[0].indexOf(":")+3, Response[0].length()-1);
		
		if(Status.equalsIgnoreCase("okay")){
			System.out.println("Status Is Okay, continuing...");
			
			GameID = Response[1].substring(Response[1].indexOf(":")+4, Response[1].length()-1);
			Move = Response[2];//.substring(Response[2].indexOf(":")+3, Response[2].length()-1);
			System.out.println(Status);
			System.out.println(GameID);
			System.out.println(Move);
		}
		else if(Status.equalsIgnoreCase("error")){
			
		}
		else if(WebText.contains("status:")){
			System.out.println("Error Exists in status");
			System.out.println("Unknow Status Report");
			System.out.println("Should be either 'okay' or 'error'");
		}
		else{
			System.out.println("Missing Status Message");
		}
		
	}
	
	public static void getBoard(){
		readFromURL(GameAddress + newGameAddress + "");
	}
	
	public static void initializeBoard(){
		for(int i = 0; i < 9; i++){
			Board.add(0);
		}
	}
	
	
	public static void resetBoard(){
		for(int i = 0; i < Board.size(); i++){
			Board.set(i, 0);
		}
	}
	
	public static void place(int pos){
		readFromURL(GameAddress + moveAddress + pos);
	}
	
	public static void placeAt(int move, int pos){
		if(move < 2){
			move = 0;
			System.out.println("Invalid Move!");
		}
		Board.set(pos, move);
	}
	
	public static String getEmptyPositions(){
		String out = "";
		for(int i = 0; i < Board.size(); i++){
			if(Board.get(i) == 0){
				out = out + (i+1) + ", ";
			}
		}
		return out;
	}
	
	public static void printBoard(){
		System.out.println(Board.get(0) + " , " + Board.get(1) + " , " + Board.get(2));
		System.out.println(Board.get(3) + " , " + Board.get(4) + " , " + Board.get(5));
		System.out.println(Board.get(6) + " , " + Board.get(7) + " , " + Board.get(8));
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
		Board.set(numPosition, numCharacter);
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
		}catch (Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		return output;
	}
}
