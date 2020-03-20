package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {

	private LinkedList<Bullet> b=new LinkedList<Bullet>(); //creates a linkedlist of bullets
	
	Game game;//create the game instance.
	
	Bullet TempBullet; //creates a temp bullet object
	
	public Controller(Game game)
	{
		this.game=game;
		

	}
	public void tick()
	{
		for(int i=0;i<b.size();i++)//goes over all the bullets in the linkedlist
		{
			TempBullet=b.get(i);//our temp bullet will be the first object in the list.
			
			if(TempBullet.getY()<0)
				removeBullet(TempBullet);
			
			TempBullet.tick();// each object will tick again.
		}
	}
	
	public void render(Graphics g)
	{
		for(int i=0;i<b.size();i++)//goes over all the bullets in the linkedlist
		{
			TempBullet=b.get(i);//our temp bullet will be the first object in the list.
			
			TempBullet.render(g);//each bullet will render in our bullet class
		}
	}
	public void addBullet(Bullet block)
	{
		b.add(block);
	}
	
	public void removeBullet(Bullet block)
	{
		b.remove(block);
	}
}
