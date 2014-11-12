package com.genesis.tictactoe.screen;

import com.genesis.tictactoe.Display;

public class Screen {

	String type;

	public Screen(String type) {
		this.type = type;
		init();
	}

	public void init() {

	}

	public void render() {
		Display.dbGraphics.drawString("Error: Unknown Screen Type", 64, 64);
	}
	
	public void clickPos(int x, int y){
		//Deal with Positions being clicked on each screen.
	}

}
