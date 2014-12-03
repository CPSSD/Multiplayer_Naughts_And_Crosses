package com.genesis.tictactoe.screen;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import com.genesis.tictactoe.Display;
import com.genesis.tictactoe.Frame;
import com.genesis.tictactoe.ImageLoader;
import com.sun.javafx.geom.Rectangle;

public class GameScreen extends Screen {

	public GameScreen() {
		super("Game");
	}

	
	public void pingServer() {
		//Get the board
		//Update the local board
	}

	public Rectangle Pos00, Pos01, Pos02, Pos10, Pos11, Pos12, Pos20, Pos21, Pos22;
	public Rectangle title;
	
	public void render() {
		title = new Rectangle(Frame.fWidth/2 - 125, 25, 250, 50);
		
		Pos00 = new Rectangle(170, 100, 100, 100);
		Pos01 = new Rectangle(270, 100, 100, 100);
		Pos02 = new Rectangle(370, 100, 100, 100);
		Pos10 = new Rectangle(170, 200, 100, 100);
		Pos11 = new Rectangle(270, 200, 100, 100);
		Pos12 = new Rectangle(370, 200, 100, 100);
		Pos20 = new Rectangle(170, 300, 100, 100);
		Pos21 = new Rectangle(270, 300, 100, 100);
		Pos22 = new Rectangle(370, 300, 100, 100);
		
		Display.dbGraphics.setColor(Color.white);
		Display.dbGraphics.setFont(new Font("Arial", 0, 32));
		Display.dbGraphics.drawImage(ImageLoader.background, 0, 0, Frame.fWidth, Frame.fHeight, null);
		Display.dbGraphics.drawImage(ImageLoader.curPlayerBackground, title.x, title.y, title.width, title.height, null);

		Display.dbGraphics.drawImage(ImageLoader.tileBackground, Pos00.x, Pos00.y, Pos00.width, Pos00.height, null);
		Display.dbGraphics.drawImage(ImageLoader.tileBackground, Pos01.x, Pos01.y, Pos01.width, Pos01.height, null);
		Display.dbGraphics.drawImage(ImageLoader.tileBackground, Pos02.x, Pos02.y, Pos02.width, Pos02.height, null);

		Display.dbGraphics.drawImage(ImageLoader.tileBackground, Pos10.x, Pos10.y, Pos10.width, Pos10.height, null);
		Display.dbGraphics.drawImage(ImageLoader.tileBackground, Pos11.x, Pos11.y, Pos11.width, Pos11.height, null);
		Display.dbGraphics.drawImage(ImageLoader.tileBackground, Pos12.x, Pos12.y, Pos12.width, Pos12.height, null);

		Display.dbGraphics.drawImage(ImageLoader.tileBackground, Pos20.x, Pos20.y, Pos20.width, Pos20.height, null);
		Display.dbGraphics.drawImage(ImageLoader.tileBackground, Pos21.x, Pos21.y, Pos21.width, Pos21.height, null);
		Display.dbGraphics.drawImage(ImageLoader.tileBackground, Pos22.x, Pos22.y, Pos22.width, Pos22.height, null);
		

		drawString("Current Turn: ", title.x + title.width/2, title.y + title.height/2);
	}

	public void drawBoard(int[] board, int startX, int startY) {
		/*
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
		*/
	}

	public void clickPos(int x, int y) {
		/*
		int x1 = x - gridX;
		int y1 = y - gridY;
		int tileX = 0, tileY = 0;
		if (x1 > 0 && y1 > 0 && x1 < (tileSize + 4) * 3 && y1 < (tileSize + 4) * 3) {
			tileX = x1 / (tileSize + 4);
			tileY = y1 / (tileSize + 4);
			//placeTile(tileX + tileY * 3);
			System.out.println("Tile Placed..");
		}
		*/
	}

	public static void parseBoard(JSONArray board) {
		/*
		int[] newBoard = new int[board.length()];
		for (int i = 0; i < board.length(); i++) {
			newBoard[i] = board.getInt(i);
		}
		GameScreen.board = newBoard;
		*/
	}
	
	public static void placeTile(int index) {
	/*
		if (board[index] == 0) {
			sendMove(index);
		}*/
	}
	
	public void drawString(String text, int x, int y){
		Display.dbGraphics.drawString(text, x - text.length()*6, y+8);
	}
}
