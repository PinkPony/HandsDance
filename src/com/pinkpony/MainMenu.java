package com.pinkpony;

import java.awt.Color;
import java.awt.Graphics;

import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

public class MainMenu extends AbstractGameState{

	Graphics gr;
	
	public void tick(Graphics g)
	{
		gr = g;
		gr.clearRect(0, 0, HandsDance.WIDTH, HandsDance.HEIGHT);
		gr.setColor(Color.PINK);
		gr.drawString("HandsDance : Pink Pony", 150, 20);
		gr.drawString("HIT ESC TO LEAVE", 10, 20);	
		gr.drawString("IN MENU SCREEN",400,400);
		
		if(HandsDance.mouse[0] != 0)
			play();
		
	}
	
	public void play()
	{
		StateChanger.gameState = GameState.PLAY;
	}

	
}
