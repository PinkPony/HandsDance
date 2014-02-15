package com.pinkpony;

import java.awt.Color;
import java.awt.Graphics;

public class StateChanger {

	Graphics gr;
	public static GameState gameState;
	private MainMenu mainmenu;
	private AbstractGameState play;
	private AbstractGameState pause;
	private AbstractGameState levelOne;
	
	
	public StateChanger()
	{
		mainmenu = new MainMenu();
		play = new Play();
		pause = new Pause();
		gameState = GameState.MENU;
	}
	
	public void tick(Graphics g)
	{
		gr = g;
		gr.clearRect(0, 0, HandsDance.WIDTH, HandsDance.HEIGHT);
		gr.setColor(Color.PINK);
		gr.drawString("HandsDance : Pink Pony", 150, 20);
		gr.drawString("HIT ESC TO LEAVE", 10, 20);	
	
		switch(gameState)
		{
			case MENU:
				mainmenu.tick(gr);
				break;
			case PAUSED:
				pause.tick(gr);
				break;
			case PLAY:
				play.tick(gr);
				break;	
			case LEVELONE:
				levelOne.tick(gr);
				break;
				
		}
		
		
		
	}
	
}
