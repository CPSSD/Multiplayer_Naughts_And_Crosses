package com.genesis.tictactoe;

import javax.swing.JFrame;

import com.genesis.tictactoe.screen.GameScreen;

public class Frame {

	public static JFrame frame;
	public static String title = "Tic-Tac-Toe";
	public static int fWidth = 640, fHeight = 480;

	public static Display display;

	public static void main(String[] args) {
		if (args.length >= 1) {
			GameScreen.name = args[0];
		}

		if (args.length >= 2) {
			GameScreen.gameAddress = args[1];
		}
		frame = new JFrame(title);
		display = new Display();

		frame.add(display);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(fWidth, fHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		display.init();

	}
}