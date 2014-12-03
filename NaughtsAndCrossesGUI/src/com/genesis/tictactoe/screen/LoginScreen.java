package com.genesis.tictactoe.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JTextField;

import com.genesis.tictactoe.Display;
import com.genesis.tictactoe.Frame;
import com.genesis.tictactoe.ImageLoader;
import com.genesis.tictactoe.game.TicTacToeData;

public class LoginScreen extends Screen {

	private String username = "";
	public String key = "rtyui95e67";
	
	public LoginScreen() {
		super("Game");
	}
	
	public Rectangle title, loginText, userInput, exit, menu;
	
	public void render() {

		TextField localTextField = new TextField("my game text", 20);
		
		title = new Rectangle((int) (Frame.fWidth / 2 - 96 * 2), 40, (int) (96 * 4), 120);
		loginText = new Rectangle(Frame.fWidth/2 - 160, 200, 320, 50);
		userInput = new Rectangle(Frame.fWidth/2 - 160, 255, 320, 50);
		menu = new Rectangle(Frame.fWidth/4 -120, 310, 240, 50);
		exit = new Rectangle(3*Frame.fWidth/4 -120, 310, 240, 50);
		
		BufferedImage black = new BufferedImage(userInput.width, userInput.height-10, BufferedImage.TYPE_BYTE_GRAY);
		
		Display.dbGraphics.setColor(Color.blue);
		Display.dbGraphics.drawImage(ImageLoader.background, 0, 0, Frame.fWidth, Frame.fHeight, null);
		
		Display.dbGraphics.setColor(Color.white);
		Display.dbGraphics.setFont(new Font("Arial", Font.BOLD, 16));
		
		Display.dbGraphics.drawImage(ImageLoader.titleLogo, title.x, title.y, title.width, title.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, loginText.x, loginText.y, loginText.width, loginText.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, exit.x, exit.y, exit.width, exit.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, menu.x, menu.y, menu.width, menu.height, null);
		

		Display.dbGraphics.drawImage(black, userInput.x, userInput.y, userInput.width, userInput.height, null);
		
		drawString("Choose Username:", loginText.x + loginText.width/2, loginText.y + loginText.height/2);
		drawString("Quit Game", exit.x + exit.width/2, exit.y + exit.height/2);
		drawString("Login", menu.x + menu.width/2, menu.y + menu.height/2);
		
		drawString(username, userInput.x + userInput.width/2, userInput.y + userInput.height/2);
	}
	
	public void drawString(String text, int x, int y){
		Display.dbGraphics.drawString(text, x - (text.length()*4), y + 5);
	}

	public void clickPos(int x, int y) {
		Rectangle p = new Rectangle(x, y, 1, 1);
		
		if (p.intersects(exit)) {
			System.exit(0);
		}
		if (p.intersects(menu)) {
			Display.screen = new MenuScreen();
			if(username.length() > 0){
				TicTacToeData.username = username;
			}
		}
	}

	public void pingServer() {
		
	}
	
	public void keyInput(String e){
		if(e.equalsIgnoreCase("/D")){
			if(username.length() > 0){
				username = username.substring(0, username.length() - 1);
			}
		}
		else{
			username = username + e;
		}
	}
}
