package com.genesis.tictactoe;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.genesis.tictactoe.screen.*;

public class Display extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static BufferedImage dbImage;
	public static Graphics dbGraphics;
	public static Thread thread;
	public static Screen screen;
	public static boolean running = false;

	public void init() {
		thread = new Thread(this);
		thread.run();
	}

	public void run() {
		double startTime = System.currentTimeMillis();
		double curTime = 0;
		int tick = 0;

		addMouseListener(new Controller());
		ImageLoader.loadImages();
		screen = new MenuScreen();

		running = true;
		while (running) {
			curTime = System.currentTimeMillis();
			if (curTime - startTime > 1000.0 / 60) {
				tick++;
				if (tick >= 60) {
					pingServer();
					tick = 0;
				}
				repaint();
				startTime = curTime;
			}
		}
	}

	public void pingServer() {
		// Server Grabbing Stuff Here...
	}

	public void paint(Graphics g) {
		dbImage = (BufferedImage) createImage(Frame.fWidth, Frame.fHeight);
		dbGraphics = dbImage.getGraphics();

		if (running) {
			screen.render();
		}

		g.drawImage(dbImage, 0, 0, Frame.fWidth, Frame.fHeight, null);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public static void drawImage(BufferedImage img, int x, int y, int width, int height) {
		dbGraphics.drawImage(img, x, y, width, height, null);
	}

}
