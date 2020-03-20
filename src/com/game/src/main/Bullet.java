package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {

	private double x;
	private double y;
	
	BufferedImage image; //create the buffered image for our bullet.
	
	public Bullet(double x,double y, Game game)
	{
		this.x=x;
		this.y=y;
		
		SpriteSheet ss=new SpriteSheet(game.getSpriteSheet());
		
		image=ss.grabImage(2, 1, 32, 32);
	}
	
	public void tick()
	{
		y-=5;
	}
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void render(Graphics g)
	{
		g.drawImage(image, (int)x,(int)y,null);
	}
}
