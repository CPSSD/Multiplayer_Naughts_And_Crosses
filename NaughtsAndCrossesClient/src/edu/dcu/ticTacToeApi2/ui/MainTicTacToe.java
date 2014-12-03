package edu.dcu.ticTacToeApi2.ui;

import java.io.IOException;
import java.util.Scanner;

import edu.dcu.ticTacToeApi2.ServerConnector;
import edu.dcu.ticTacToeApi2.TicTacToeClient;

/**
 * Main class
 * @author Jennifer
 *
 */
public class MainTicTacToe {
	public static void main(String[] args) throws IOException {
		TicTacToeClient client = new TicTacToeClient(new ServerConnector());
		TicTacToeMenu menu = new TicTacToeMenu(client);
		menu.showMenu();

	}

}
