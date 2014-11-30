package edu.dcu.ticTacToeApi2.ui;

import java.io.IOException;
import java.util.Scanner;

import edu.dcu.ticTacToeApi2.ServerConnector;
import edu.dcu.ticTacToeApi2.TicTacToeClient;

public class MainTicTacToe {
	public static void main(String[] args) throws IOException {
		TicTacToeClient client = new TicTacToeClient(new ServerConnector());
		TicTacToeMenu menu = new TicTacToeMenu(client);
		menu.showMenu();
//		Scanner in = new Scanner(System.in);
//		boolean exitMenu = false;
//
//		do {
//			System.out.println("Do you want to see the game menu y/n");
//			String showMenu = in.next();
//			
//			if(showMenu.equalsIgnoreCase("y")){
//				menu.showMenu();
//				exitMenu = false;
//			}else{
//				System.out.println("Good bye");
//				exitMenu = true;
//			}
//		} while (exitMenu);
	}

}
