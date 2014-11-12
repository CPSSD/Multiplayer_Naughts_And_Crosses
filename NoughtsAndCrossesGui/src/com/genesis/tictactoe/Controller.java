package com.genesis.tictactoe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.genesis.tictactoe.screen.GameScreen;

public class Controller implements MouseListener, MouseMotionListener {

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

}
