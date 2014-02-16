package com.pinkpony;

import com.leapmotion.leap.Controller;

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

		Image curHand = Art.getImageFromFile("/images/hands1"+".png");
		gr.drawImage(curHand, 0, 0, 130, 188, null);
		
		LeapController leapController = new LeapController();
		Controller controller = new Controller();
		
		controller.addListener(leapController);
		
		currentIndex++;
	}

	public int[] generateLevel()
	{
		int[] ret = {1,2,3,4,5};
		return ret;
	}
	
	
	
}
