package com.genesis.tictactoe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements MouseListener, MouseMotionListener, KeyListener {

	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Clicked at: (" + e.getX() + ", " + e.getY() + ");");
		Display.screen.clickPos(e.getX(), e.getY());
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			Display.screen.keyInput("/D");
		}
		if(isAcceptedKey(e.getKeyCode())){
			char c = e.getKeyChar();
			Display.screen.keyInput(String.valueOf(c));
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) { 
		
	}
	
	public boolean isAcceptedKey(int keyCode){
		boolean isValid = false;
		int[] AcceptKeys = new int[]{128, 
				49, 
				50, 
				51, 
				52, 
				53, 
				54, 
				55, 
				56, 
				57, 
				48, 
				45, 
				61, 
				91, 
				93, 
				59, 
				222, 
				520, 
				44, 
				46, 
				47, 
				92, 
				65, 
				66, 
				67, 
				68, 
				69, 
				70, 
				71, 
				72, 
				73, 
				74, 
				75, 
				76, 
				77, 
				78, 
				79, 
				80, 
				81, 
				82, 
				83, 
				84, 
				85, 
				86, 
				87, 
				88, 
				89, 
				83,
				32};
		
		for(int i = 0; i < AcceptKeys.length; i++){
			if(keyCode == AcceptKeys[i]){
				isValid = true;
			}
		}
		return isValid;
	}
}
