package com.game.src.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) // builder. 
	{
		this.image=image;
	}
	public BufferedImage grabImage(int col, int row,int width, int height) // takes the image from the sprite sheet	
	{
		BufferedImage img=image.getSubimage((col*32)-32, (row*32)-32, width, height);// create a new image from the decided spritesheet. since each tile is 32x32 we need to multiply each choice say 1,1 by 32 and substract 32 to get the currect tile.
		return img;
	}

}
