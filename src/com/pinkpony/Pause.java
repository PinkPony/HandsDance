package com.pinkpony;

import java.awt.Color;
import java.awt.Graphics;

public class Pause extends AbstractGameState{

	Graphics gr;
	
	@Override
	public void tick(Graphics g)
	{
		gr = g;
		
		gr.clearRect(0, 0, HandsDance.WIDTH, HandsDance.HEIGHT);
		gr.setColor(Color.PINK);
		gr.drawString("HandsDance : Pink Pony", 150, 20);
		gr.drawString("HIT ESC TO LEAVE", 10, 20);	
		gr.drawString("IN PAUSE SCREEN",400,400);
	}
}
