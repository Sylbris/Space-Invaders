package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
	
	private double x; // represents the x coordinate of the player.
	private double y;//  represents the y coordinate of the player.
	
	private double velX=0;//will represent the speed on x axis.
	private double velY=0;// speed on y axis.
	
	private BufferedImage player; //create the buffered image for our player.
	
	public Player(double x,double y, Game game) //builder we get the coords and we also get the game so we can pull the spritesheet from it.
	{
		this.x=x;
		this.y=y;
		
		SpriteSheet ss=new SpriteSheet(game.getSpriteSheet()); // create ss to put the spritesheet we created in game class
		
		player=ss.grabImage(1, 1, 32, 32); //grab the correct sprite for our player.
	}

	public void tick()
	{
		x+=velX;//now we can increase our velX each time instead of increasing x itself and that will allow a more smoother movement
		y+=velY;//now we can increase our velY each time instead of increasing y itself and that will allow a more smoother movement
		
		if(x<=0) // limiting our movement so if spaceship tries to leave frame we reset its x value.
			x=0;
		if(x>=(Game.WIDTH*2) - 20)
			x=Game.WIDTH*2 -20;
		if(y<=0)
			y=0;
		if(y>=(Game.HEIGHT*2) - 20)
			y=(Game.HEIGHT*2) -20;
	}
	
	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
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
		g.drawImage(player, (int)x,(int) y, null); //create the graphics for our player
	}
	
}
