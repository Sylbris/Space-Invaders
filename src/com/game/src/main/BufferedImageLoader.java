package com.game.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader { // will load our bufferedimage

	private BufferedImage image; //creating our image
	
	public BufferedImage loadImage(String path) throws IOException { // function to load image
		image=ImageIO.read(getClass().getResource(path)); 
		return image;
	}
}
