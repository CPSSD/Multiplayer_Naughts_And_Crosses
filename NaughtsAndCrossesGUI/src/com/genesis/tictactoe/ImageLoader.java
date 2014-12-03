package com.genesis.tictactoe;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static boolean isLoaded = false;
	public static BufferedImage[][] images;
	public static BufferedImage boardBackground, titleLogo, curPlayerBackground, buttonBackground, findGameButton, tileBackground, background, playButton, tick, tickBox;

	public static void loadImages() {
		images = new BufferedImage[4][1];

		titleLogo = getImage("/titleLogo.png");
		buttonBackground = getImage("/MenuButton.png");
		playButton = getImage("/playButton.png");
		background = getImage("/background.png");
		curPlayerBackground = getImage("/curPlayerBackground.png");
		tileBackground = getImage("/tileBG.png");
		tick = getImage("/tick.png");
		tickBox = getImage("/tickBox.png");
		
		loadSpriteSheet(images, getImage("/images.png"), 4, 1, 96, 96);

		isLoaded = true;
	}

	public static void loadSpriteSheet(BufferedImage[][] tileArray, BufferedImage spriteSheet, int tilesX, int tilesY, int tileWidth, int tileHeight) {
		for (int i = 0; i < tilesX; i++) {
			for (int j = 0; j < tilesY; j++) {
				tileArray[i][j] = spriteSheet.getSubimage(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
			}
		}
	}

	public static BufferedImage getImage(String location) {
		try {
			URL image = Frame.class.getResource(location);
			if(image != null){
				System.out.println(image);
				return ImageIO.read(image);
			}
			else{
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new BufferedImage(640, 480, 1);
	}
}
