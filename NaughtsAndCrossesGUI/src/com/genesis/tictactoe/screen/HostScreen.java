package com.genesis.tictactoe.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.genesis.tictactoe.Display;
import com.genesis.tictactoe.Frame;
import com.genesis.tictactoe.ImageLoader;
import com.genesis.tictactoe.game.TicTacToeData;

public class HostScreen extends Screen {

	private String strDescription = "";
	private String strPin = "";
	private boolean isPrivate = false;
	
	public int TYPING_CURRENT = 0;
	
	public int TYPING_DESCRIPTION = 0;
	public int TYPING_PIN = 1;
	
	public int[][] descriptionDim = new int[][]{{320,352},{40,44}};
	public int[][] pinDim = new int[][]{{150, 165},{40, 44}};
	
	public HostScreen() {
		super("Game");
		
		//connect code
	}
	
	public Rectangle title, textDescription, description, description1, textPrivateMatch, privateMatch, textPin, pin, pin1, game, menu;
	
	public void render() {
		
		title = new Rectangle(Frame.fWidth/2 - 225, 20, 450, 70);
		textDescription = new Rectangle(Frame.fWidth/2 - 160, 100, 320, 40);
		
		description = new Rectangle(Frame.fWidth/2 - 160, 145, 320, 40);
		description1 = new Rectangle(Frame.fWidth/2 - 172, 145, 352, 44);
		
		textPrivateMatch = new Rectangle(100, 200, 300, 40);
		privateMatch = new Rectangle(450, 200, 40, 40);
		textPin = new Rectangle(100, 250, 300, 40);
		
		pin = new Rectangle(450, 250, 150, 40);	//TODO
		pin1 = new Rectangle(443, 246, 165, 44);
		
		game = new Rectangle(Frame.fWidth/4 - 120, 310, 240, 50);
		menu = new Rectangle(3*Frame.fWidth/4 - 120, 310, 240, 50); 
		
		BufferedImage black = new BufferedImage(description.width, description.height-10, BufferedImage.TYPE_BYTE_GRAY);
		
		Display.dbGraphics.setColor(Color.blue);
		Display.dbGraphics.drawImage(ImageLoader.background, 0, 0, Frame.fWidth, Frame.fHeight, null);
		
		Display.dbGraphics.setColor(Color.white);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, title.x, title.y, title.width, title.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, textDescription.x, textDescription.y, textDescription.width, textDescription.height, null);
		
		if(TYPING_CURRENT == TYPING_DESCRIPTION){
			Display.dbGraphics.drawImage(black, description1.x, description1.y, description1.width, description1.height, null);
		}
		else{
			Display.dbGraphics.drawImage(black, description.x, description.y, description.width, description.height, null);
		}
		
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, textPrivateMatch.x, textPrivateMatch.y, textPrivateMatch.width, textPrivateMatch.height, null);
		Display.dbGraphics.drawImage(ImageLoader.tickBox, privateMatch.x, privateMatch.y, privateMatch.width, privateMatch.height, null);
		if(isPrivate){
			Display.dbGraphics.drawImage(ImageLoader.tick, privateMatch.x, privateMatch.y, privateMatch.width, privateMatch.height, null);
			Display.dbGraphics.drawImage(ImageLoader.buttonBackground, textPin.x, textPin.y, textPin.width, textPin.height, null);
			if(TYPING_CURRENT == TYPING_PIN){
				Display.dbGraphics.drawImage(black, pin1.x, pin1.y, pin1.width, pin1.height, null);
			}
			else{
				Display.dbGraphics.drawImage(black, pin.x, pin.y, pin.width, pin.height, null);
			}
		}
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, game.x, game.y, game.width, game.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, menu.x, menu.y, menu.width, menu.height, null);

		Display.dbGraphics.setFont(new Font("Arial", Font.BOLD, 30));
		drawString("Game Host", title.x + (int)(title.width/2)-20, title.y + (title.height/2));

		Display.dbGraphics.setFont(new Font("Arial", Font.BOLD, 20));
		drawString("Game Description:", textDescription.x + (textDescription.width/2), textDescription.y + (textDescription.height/2));
		drawString(strDescription, description.x + (description.width/2), description.y + (description.height/2));
		drawString("Private Game:", textPrivateMatch.x + (textPrivateMatch.width/2), textPrivateMatch.y + (textPrivateMatch.height/2));
		if(isPrivate){
			drawString("Pin (For Private Games):", textPin.x + (textPin.width/2), textPin.y + (textPin.height/2));
			drawString(strPin, pin.x + (pin.width/2), pin.y + (pin.height/2));
		}
		drawString("Start Game", game.x + (game.width/2), game.y + (game.height/2));
		drawString("Main Menu", menu.x + (menu.width/2), menu.y + (menu.height/2));
	}
	
	public void drawString(String text, int x, int y){
		Display.dbGraphics.drawString(text, x - (text.length()*5), y + 5);
	}
	
	public void clickPos(int x, int y) {
		Rectangle p = new Rectangle(x, y, 1, 1);
		
		if (p.intersects(game)) {
			TicTacToeData.description = strDescription;
			if(strPin.length() != 0){
				TicTacToeData.pin = strPin;
			}
			if(isPrivate){
				TicTacToeData.privateGame = 1;
			}
			strDescription = "";
			strPin = "";
			
			TicTacToeData.startGame();
			Display.screen = new GameScreen();
		}
		if (p.intersects(menu)) {
			Display.screen = new MenuScreen();
			strDescription = "";
		}
		if(p.intersects(privateMatch)){
			System.out.println("Private Game");
			isPrivate = !isPrivate;
		}
		if(p.intersects(description)){
			TYPING_CURRENT = TYPING_DESCRIPTION;
		}
		if(p.intersects(pin)){
			TYPING_CURRENT = TYPING_PIN;
		}
	}
	
	public void keyInput(String e){
		if(TYPING_CURRENT == TYPING_DESCRIPTION){
			if(e.equalsIgnoreCase("/D")){
				if(strDescription.length() > 0){
					strDescription = strDescription.substring(0, strDescription.length() - 1);
				}
			}
			else{
				strDescription = strDescription + e;
			}
		}
		else if(TYPING_CURRENT == TYPING_PIN){
			if(e.equalsIgnoreCase("/D")){
				if(strPin.length() > 0){
					strPin = strPin.substring(0, strPin.length() - 1);
				}
			}
			else if(strPin.length() < 4){
				for(int i = 0; i < 10; i++){
					if(e.equalsIgnoreCase(String.valueOf(i))){
						strPin = strPin + e;
					}
				}
			}
		}
		
	}
}
