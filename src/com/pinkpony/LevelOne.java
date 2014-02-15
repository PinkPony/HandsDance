package com.pinkpony;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class LevelOne extends AbstractGameState{

	Graphics gr;
	Random r;
	public int[] lvl = new int[30];
	public int[] hand = {1,2,3,4,5};
	public Image[] handImg = new Image[5];
	public int currentIndex = 0;
	
	
	public LevelOne()
	{
		lvl = generateLevel();
	}
	
	@Override
	public void tick(Graphics g) {
		gr = g;
		
		gr.clearRect(0, 0, HandsDance.WIDTH, HandsDance.HEIGHT);
		
		gr.drawString(""+lvl[currentIndex], HandsDance.mouse[4], HandsDance.mouse[5]);
		Image curHand = Art.getImageFromFile("hands"+lvl[currentIndex]+1+".png");
		gr.drawImage(curHand, 0, 0, 130, 188, null);
		
		
		
		
		
		currentIndex++;
	}

	public int[] generateLevel()
	{
		int[] ret = new int[30];
		for(int i=0; i<30; i++)
			ret[i] = r.nextInt(5)+1;
		return ret;
	}
	
	
	
}
