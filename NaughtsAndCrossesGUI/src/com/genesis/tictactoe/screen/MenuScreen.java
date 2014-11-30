package com.genesis.tictactoe.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import com.genesis.tictactoe.Display;
import com.genesis.tictactoe.Frame;
import com.genesis.tictactoe.ImageLoader;

public class MenuScreen extends Screen {

	int x = 0;
	public Rectangle playButton, hostButton, joinButton, exitButton, settingsButton, title;

	public MenuScreen() {
		super("Menu");
	}

	public void init() {
		title = new Rectangle((int) (Frame.fWidth / 2 - 96 * 2), 40, (int) (96 * 4), 120);
		
		int buttonX = 240;
		int buttonY = 50;
		
		playButton = new Rectangle(Frame.fWidth/4 - buttonX/2	, 200, buttonX, buttonY);
		hostButton = new Rectangle(3*Frame.fWidth/4 -buttonX/2	, 200, buttonX, buttonY); //
		joinButton = new Rectangle(Frame.fWidth/4 - buttonX/2	, 260, buttonX, buttonY); //
		settingsButton = new Rectangle(3*Frame.fWidth/4 -buttonX/2	, 260, buttonX, buttonY); 
		exitButton = new Rectangle(Frame.fWidth/2 -buttonX/2	, 360, buttonX, buttonY); 
	}

	public void render() {
		Display.dbGraphics.setColor(Color.blue);
		Display.dbGraphics.drawImage(ImageLoader.background, 0, 0, Frame.fWidth, Frame.fHeight, null);
		Display.dbGraphics.drawImage(ImageLoader.titleLogo, title.x, title.y, title.width, title.height, null);
		
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, playButton.x, playButton.y, playButton.width, playButton.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, hostButton.x, hostButton.y, hostButton.width, hostButton.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, joinButton.x, joinButton.y, joinButton.width, joinButton.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, exitButton.x, exitButton.y, exitButton.width, exitButton.height, null);
		
		Display.dbGraphics.setColor(Color.white);
		Display.dbGraphics.setFont(new Font("Arial", Font.BOLD, 16));
		
		drawString("Play Random Game  "	, playButton.x + (int)(playButton.width/2)			, playButton.y + (playButton.height/2));
		drawString("Host Private Game"	, hostButton.x + (int)(hostButton.width/2)			, hostButton.y + (hostButton.height/2));
		drawString("Join Private Game"	, joinButton.x + (int)(joinButton.width/2)			, joinButton.y + (joinButton.height/2));
		drawString("Settings"			, settingsButton.x + (int)(settingsButton.width/2)	, settingsButton.y + (settingsButton.height/2));
		drawString("Exit"				, exitButton.x + (int)(exitButton.width/2)			, exitButton.y + (exitButton.height/2));
		
		init();
	}
	
	public void drawString(String text, int x, int y){
		Display.dbGraphics.drawString(text, x - (text.length()*4), y + 5);
	}

	public void clickPos(int x, int y) {
		Rectangle p = new Rectangle(x, y, 1, 1);
		
		if (p.intersects(playButton)) {
			Display.screen = new GameScreen();
		}
		if (p.intersects(hostButton)) {
			Display.screen = new HostScreen();
		}
		if (p.intersects(joinButton)) {
			System.out.println("joinButton");
		}
		if (p.intersects(settingsButton)) {
			System.out.println("settings");
		}
		if (p.intersects(exitButton)) {
			System.exit(0);
		}
	}

}
