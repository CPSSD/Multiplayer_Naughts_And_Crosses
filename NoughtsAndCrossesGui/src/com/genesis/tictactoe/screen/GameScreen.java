package com.genesis.tictactoe.screen;

import java.awt.Color;
import java.awt.Font;

import com.genesis.tictactoe.Display;
import com.genesis.tictactoe.Frame;
import com.genesis.tictactoe.ImageLoader;

public class GameScreen extends Screen {

	static int[] board;
	static int gridX, gridY;
	static int tileSize, turn = 1;

	public GameScreen() {
		super("Game");
		board = new int[9];
	}

	public void render() {
		Display.dbGraphics.setColor(Color.white);

		tileSize = 96;
		int startX = (int) ((640 / 2) - (tileSize * 1.5));
		int startY = (int) ((480 / 2) - (tileSize * 1.2));
		Display.dbGraphics.drawImage(ImageLoader.background, 0, 0, Frame.fWidth, Frame.fHeight, null);
		Display.dbGraphics.drawImage(ImageLoader.curPlayerBackground, startX, startY-tileSize, tileSize*3, tileSize/2, null);
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

	public static void placeTile(int index) {
		board[index] = turn;
		if (turn == 1) {
			turn = 2;
		} else {
			turn = 1;
		}
	}
}
