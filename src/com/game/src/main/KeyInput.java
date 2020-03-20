package com.game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{ // keyadapter class will allow us to handle key pressed.
	
	Game game; // create a game instance since we want it to run on our game.
	
	public KeyInput(Game game) //builder, we want our keyboard input will go into out game
	{
		this.game=game;
	}
	
	public void keyPressed(KeyEvent e) // create the method to to our game class
	{
		game.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) // create the method to to our game class
	{
		game.keyReleased(e);
	}

}
