package com.pinkpony;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class MainMenu extends AbstractGameState{

	Graphics gr;
	
	public void tick(Graphics g)
	{
		gr = g;
		gr.clearRect(0, 0, HandsDance.WIDTH, HandsDance.HEIGHT);
		
		Image mainlogo = Art.getImageFromFile("fullmenuscreen.png");
		gr.drawImage(mainlogo, 0, 0, HandsDance.WIDTH, HandsDance.HEIGHT, null);
		
		
		gr.setColor(Color.PINK);
		gr.drawString("HandsDance : Pink Pony", 150, 20);
		gr.drawString("HIT ESC TO LEAVE", 10, 20);	
		//gr.drawString("IN MENU SCREEN",400,400);
		
		
		int mx = HandsDance.mouse[4];
		int my = HandsDance.mouse[5];
		String pos = "x:"+mx + " y:"+my;
		System.out.println(pos);
		
		gr.drawString(pos, mx, my);
		boolean xplay = HandsDance.mouse[4] >= 598 && HandsDance.mouse[4] <= 803;
		boolean yplay = HandsDance.mouse[5] >= 577 && HandsDance.mouse[5] <= 695;
		if(xplay && yplay && HandsDance.mouse[0] != 0)
			play();
		
	}
	
	public void play()
	{
		StateChanger.gameState = GameState.PLAY;
	}

	
}
