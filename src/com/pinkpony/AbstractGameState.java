package com.pinkpony;

import java.awt.Graphics;

public abstract class AbstractGameState {
	
	Graphics gr;
	
	public abstract void tick(Graphics g);

}
