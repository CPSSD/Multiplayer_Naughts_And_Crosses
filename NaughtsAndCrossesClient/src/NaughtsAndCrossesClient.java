import java.util.ArrayList;
import java.util.Scanner;


public class NaughtsAndCrossesClient {
	
	
	//0 is vacant position
	//1 is occupied by X
	//2 is occupied by O 
	public static ArrayList<Integer> Board;
	public static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args){
		Board  = new ArrayList<Integer>();
		initializeBoard();
		printBoard();
		commandLine();
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
}
