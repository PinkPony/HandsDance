package com.pinkpony;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

<<<<<<< HEAD
import com.leapmotion.leap.Controller;

=======
>>>>>>> 7ee57336d345fab21aba43e1455ee14349947e91
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
<<<<<<< HEAD
		Image curHand = Art.getImageFromFile("/images/hands1"+".png");
		gr.drawImage(curHand, 0, 0, 130, 188, null);
		
		LeapController leapController = new LeapController();
		Controller controller = new Controller();
		
		controller.addListener(leapController);
=======
		Image curHand = Art.getImageFromFile("hands"+lvl[currentIndex]+1+".png");
		gr.drawImage(curHand, 0, 0, 130, 188, null);
		
		
		
		
>>>>>>> 7ee57336d345fab21aba43e1455ee14349947e91
		
		currentIndex++;
	}

	public int[] generateLevel()
	{
<<<<<<< HEAD
		int[] ret = {1,2,3,4,5};
=======
		int[] ret = new int[30];
		for(int i=0; i<30; i++)
			ret[i] = r.nextInt(5)+1;
>>>>>>> 7ee57336d345fab21aba43e1455ee14349947e91
		return ret;
	}
	
	
	
}
