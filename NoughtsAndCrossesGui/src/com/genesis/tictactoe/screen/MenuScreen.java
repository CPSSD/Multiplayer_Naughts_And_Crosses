package com.genesis.tictactoe.screen;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import com.genesis.tictactoe.Display;
import com.genesis.tictactoe.Frame;
import com.genesis.tictactoe.ImageLoader;

public class MenuScreen extends Screen {

	int x = 0;
	public Rectangle playButton, exitButton, title;

	public MenuScreen() {
		super("Menu");
	}

	public void init() {
		title = new Rectangle((int) (Frame.fWidth / 2 - 96 * 2), 60, (int) (96 * 4), 120);
		playButton = new Rectangle((int) (Frame.fWidth / 2 - 96 * 1.25), 240, (int) (96 * 2.5), 48);
		exitButton = new Rectangle((int) (Frame.fWidth / 2 - 96 * 1.25), 320, (int) (96 * 2.5), 48);
	}

	public void render() {
		Display.dbGraphics.setColor(Color.blue);
		Display.dbGraphics.drawImage(ImageLoader.background, 0, 0, Frame.fWidth, Frame.fHeight, null);
		Display.dbGraphics.drawImage(ImageLoader.playButton, playButton.x, playButton.y, playButton.width, playButton.height, null);
		Display.dbGraphics.drawImage(ImageLoader.curPlayerBackground, exitButton.x, exitButton.y, exitButton.width, exitButton.height, null);
	//	Display.dbGraphics.drawImage(ImageLoader.curPlayerBackground, title.x, title.y, title.width, title.height, null);
		Display.dbGraphics.drawRect(title.x, title.y, title.width, title.height);
		init();   
	}

	public void clickPos(int x, int y) {
		Rectangle p = new Rectangle(x, y, 1, 1);
		if(p.intersects(playButton)){
			Display.screen  = new GameScreen();
		}if(p.intersects(exitButton)){
			System.exit(0);
		}
	}

}
