package com.genesis.tictactoe.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.genesis.tictactoe.Display;
import com.genesis.tictactoe.Frame;
import com.genesis.tictactoe.ImageLoader;

public class HostScreen extends Screen {

	public String key = "rtyui95e67";
	
	public HostScreen() {
		super("Game");
		
		//connect code
	}
	
	public Rectangle title, text, secretKey, game, menu;
	
	public void render() {
		
		title = new Rectangle(Frame.fWidth/2 - 160, 100, 320, 48);
		text = new Rectangle(Frame.fWidth/2 - 160, 150, 320, 48);
		secretKey = new Rectangle(Frame.fWidth/2 - 160, 205, 320, 48);
		game = new Rectangle(Frame.fWidth/2 - 160, 260, 150, 48);
		menu = new Rectangle(Frame.fWidth/2 + 10, 260, 150, 48);
		
		BufferedImage black = new BufferedImage(secretKey.width, secretKey.height-10, BufferedImage.TYPE_BYTE_GRAY);
		
		Display.dbGraphics.setColor(Color.blue);
		Display.dbGraphics.drawImage(ImageLoader.background, 0, 0, Frame.fWidth, Frame.fHeight, null);
		
		Display.dbGraphics.setColor(Color.white);
		Display.dbGraphics.setFont(new Font("Arial", Font.BOLD, 16));
		
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, title.x, title.y, title.width, title.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, text.x, text.y, text.width, text.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, game.x, game.y, game.width, game.height, null);
		Display.dbGraphics.drawImage(ImageLoader.buttonBackground, menu.x, menu.y, menu.width, menu.height, null);
		
		Display.dbGraphics.drawImage(black, secretKey.x, secretKey.y, secretKey.width, secretKey.height, null);
		
		drawString("Private Game Host", title.x + (int)(title.width/2), title.y + (title.height/2));
		drawString("Secret id code is:", text.x + (int)(text.width/2), text.y + (text.height/2));
		drawString(key, secretKey.x + (int)(secretKey.width/2), secretKey.y + (secretKey.height/2));
		drawString("Start Game", game.x + (int)(game.width/2), game.y + (game.height/2));
		drawString("Main Menu", menu.x + (int)(menu.width/2), menu.y + (menu.height/2));
	}
	
	public void drawString(String text, int x, int y){
		Display.dbGraphics.drawString(text, x - (text.length()*4), y + 5);
	}

	public void clickPos(int x, int y) {
		Rectangle p = new Rectangle(x, y, 1, 1);
		
		if (p.intersects(game)) {
			Display.screen = new GameScreen();
		}
		if (p.intersects(menu)) {
			Display.screen = new MenuScreen();
		}
	}
}
