package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{ //when a thread is called or starts it calls the run method.
	
	private static final long serialVersionUID = -3833526889802087977L;
	public static final int WIDTH=320;//Will be used as our games width
	public static final int HEIGHT=	WIDTH/12*9;//Will be used as our games height.
	public static final int SCALE=2; // scale factor, if we change it the game would grow bigger.
	public final String titles="Space Invaders"; //title of game.
	
	private boolean running=false; // this represent the state of the game.
	private Thread thread; //creating the game thread
	
	private BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB); //loading the image in the size of the window.
	private BufferedImage spriteSheet=null;//creating the image for our sprite sheet
	private BufferedImage background=null;//creating the image for background
	
	private boolean is_shooting=false;
	
	private Player p;//create the player
	private Controller c;//create the controller.
	
	public void init() // function to load the image
	{
		requestFocus();//focus on the game without clickin on it
		BufferedImageLoader loader=new BufferedImageLoader();//make a new loaded so we can load the image into it
		try {
			spriteSheet=loader.loadImage("/spritesheet1.png"); //loading the file if not load an error
			background=loader.loadImage("/background.png"); //loading your background
			
		}catch(IOException e)
		{
			e.printStackTrace(); 
		}
		
		addKeyListener(new KeyInput(this));//this refers to game
		
		p=new Player(200,200,this); // this refers to game
		c=new Controller(this);//reset controller.
		
		SpriteSheet ss=new SpriteSheet(spriteSheet);//create the sprite sheet	and use our loaded spriteSheet
	}
	
	private synchronized void start() // this will start our game , as in start our thread. Synchronized for dealing with threads.
	{
		if(running) // if the game runs then , everything is fine. we would only want the game to start if its not running.
		{
			return;
		}
		running=true; // if the game is not running means running equals false then set it to true than the run method can start our game.
		thread=new Thread(this); // init our thread.
		thread.start();//starts our thread.
	}

	private synchronized void stop() // similar to start but a stop function to our game.
	{
		if(!running)//if the game is already not running then everything is fine.
		{
			return;
		}
		running=false;
		try {//do this
			thread.join();
		} catch (InterruptedException e) {//if you fail print this error.
			
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run() { //unimplemented method of Runnable, this will be the heart of the game loop.
		init();
		long lastTime=System.nanoTime(); //returns the time in nanoseconds
		final double amountOfTicks=60.0; //this will be our fps, everytime it goes it updates 60 times.
		double ns=1000000000/amountOfTicks; //1000000000 is exactly one second in nanoseconds
		double delta=0;//calculates the time passed.
		
		while(running)
		{
			long now=System.nanoTime(); // this is created to measure the diffrence between now and lastTime. so now-lastTime
			delta+=(now - lastTime) /ns; //so if one second passed difference will be 1,000,000,000 . ns =16,666,666.6666 , delta will equal to 1,000,000,000 / ns = 60 
			lastTime=now;//we start over.
			if(delta>=1)
			{
				tick(); // this will happen 60 times a second thanks to our game loop
				delta--;
			}
			render();
		}
		stop();//after the game finished running means while loop is finished we want our game to stop.
	}
	
	private void tick() // a method to update all in the game.
	{
		p.tick(); // updates the player. all of tick actuall methods are in the player. since each object updates differently.
		c.tick();//update the controll same way like player.
	}
	private void render() // a method to render the game.
	{
		BufferStrategy bs=this.getBufferStrategy();// this refers to canvas , get returns bufferstrategy used by bs
		if(bs==null) // if buffer hasn't been created , were creating it;
		{
			createBufferStrategy(3); // 3 means were creating 3 buffers. a buffer is like a hidden screen that were loading our things first there before it loads to our screen.
			return;
		}
		Graphics g=bs.getDrawGraphics(); //creates our graphics for our buffers
		////////////////////////////////////////////////////////////////////////
		
		g.drawImage(image,0,0,getWidth(),getHeight(),this); //draws the image.
		
		g.drawImage(background,0,0,null); //draws the image.
		
		p.render(g); // go to player and render(draw the image and set the location)
		c.render(g);// go to controller and render(draw the image and set the location)
		
		////////////////////////////////////////////////////////////////////////
		g.dispose();//disposes our graphics. why? if not the image will remain. we want to delete it each time.
		bs.show(); // show our bufferstrat
	}
	public void keyPressed(KeyEvent e)
	{
		int key=e.getKeyCode();//gets the key code of pressed key the ASCII value that is
		
		if(key==KeyEvent.VK_RIGHT) // So if the key pressed is RIGHT , VK is Virtual keyboard
		{
			p.setVelX(5); 
		}
		else if(key==KeyEvent.VK_LEFT) // So if the key pressed is RIGHT , VK is Virtual keyboard
		{
			p.setVelX(-5);
		}
		else if(key==KeyEvent.VK_DOWN) // So if the key pressed is RIGHT , VK is Virtual keyboard
		{
			p.setVelY(5); // since y is upsidedown increasing the y value makes the object go down.
		}
		else if(key==KeyEvent.VK_UP) // So if the key pressed is RIGHT , VK is Virtual keyboard
		{
			p.setVelY(-5);
		}
		else if(key==KeyEvent.VK_SPACE && is_shooting==false) {
			is_shooting=true;
			c.addBullet(new Bullet(p.getX(),p.getY(),this));
			
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_RIGHT) // So if the key pressed is RIGHT , VK is Virtual keyboard
		{
			p.setVelX(0);
		}
		else if(key==KeyEvent.VK_LEFT) // So if the key pressed is RIGHT , VK is Virtual keyboard
		{
			p.setVelX(0);
		}
		else if(key==KeyEvent.VK_DOWN) // So if the key pressed is RIGHT , VK is Virtual keyboard
		{
			p.setVelY(0); // since y is upsidedown increasing the y value makes the object go down.
		}
		else if(key==KeyEvent.VK_UP) // So if the key pressed is RIGHT , VK is Virtual keyboard
		{
			p.setVelY(0);
		}
		
		else if(key==KeyEvent.VK_SPACE) {
			is_shooting=false;
			
		}

		
	}
	
	public static void main(String args [])
	{
		Game game=new Game(); //creating a new instance of the game.
		
		game.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));//creating the dimensions for our window.
		game.setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));//creating the dimensions for our window.
		game.setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));//creating the dimensions for our window.
		
		JFrame frame=new JFrame(game.titles); //creating the frame of our game, and accessing game.titles to get the name of the game.
		frame.add(game);//adding the game we created into our frame.
		frame.pack();//causing the frame to fit its subcomponents in that case the dimension we created
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows you to close the game while pressing esc.
		frame.setResizable(false); //blocks the user from changin the size of the window.
		frame.setLocationRelativeTo(null);//
		frame.setVisible(true);//allows the frame to be visible.
		
	    game.start(); // in order for the game to start we need to call the method.
	}

	public BufferedImage getSpriteSheet() { //we need it to get spritesheet
		return spriteSheet;
	}

}
