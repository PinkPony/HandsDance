package com.pinkpony;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class HandsDance extends JFrame implements KeyListener, MouseListener{

	private static final long serialVersionUID = 2276157109184160429L;
	
	private GraphicsDevice vc;
	
	boolean leave = false;
	Graphics buf;
	Image iBuf;
	static StateChanger g;
    public static int[] mouse = new int[5];
	
	Dimension size;
	
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	public HandsDance()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();
		
		size = new Dimension(WIDTH, HEIGHT);
    	setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        // setMaximumSize(size);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SSB");
        
        try{
        setUndecorated(true);
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
        
		setResizable(false);
        
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
		
		int data[] = new int[WIDTH*HEIGHT]; 
		int i = 0; 
		for(int y = 0; y < HEIGHT; y++){ 
		int red = (y * 255) / (HEIGHT - 1); 
		for(int x = 0; x < WIDTH; x++){ 
		int green = (x * 255) / (WIDTH - 1); 
		int blue = 128; 
		data[i++] = (red << 16) | (green << 8) | blue; 
		} 
		} 

		iBuf = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
		((BufferedImage) iBuf).setRGB(0, 0, WIDTH, HEIGHT, data, 0, WIDTH);
		
		buf = iBuf.getGraphics();

		this.addKeyListener(this);
		this.addMouseListener(this);
	}
	
	public static void main(String[] args)
	{
		DisplayMode dm = new DisplayMode(WIDTH,HEIGHT,16, DisplayMode.REFRESH_RATE_UNKNOWN);
		HandsDance oF = new HandsDance();
		g = new StateChanger();
		oF.run(dm);
	}

	public void run(DisplayMode dm)
	{
		setBackground(Color.PINK);
		setForeground(Color.WHITE);
		
		try
		{
			setFullScreen(dm);
			
			while(true)
			{
				if(leave){
					restoreScreen();
					break;
				}
				g.tick(buf);
				repaint();
				Thread.sleep(20);
			}
		}catch(Exception e)
		{
			
		}finally
		{
			restoreScreen();
		}
	}
	
	public void setFullScreen(DisplayMode dm)
	{
		vc.setFullScreenWindow(this);

		if(dm != null && vc.isDisplayChangeSupported())
		{
			try
			{
				vc.setDisplayMode(dm);
			}catch(Exception e)
			{
				//e.printStackTrace();
			}
		}
	}

	public void paint(Graphics g)
	{
		g.drawImage(iBuf, 0, 0, size.width, size.height, this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			leave = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public Window getFullScreenWindow()
	{
		return vc.getFullScreenWindow();
	}
	public void restoreScreen()
	{
		Window w = vc.getFullScreenWindow();
		if(w != null)
			w.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mouse[3] = 1;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mouse[3] = 0;
	}

	public void mousePressed(MouseEvent arg0) {
        mouse[0] = arg0.getButton();
        mouse[1] = arg0.getX();
        mouse[2] = arg0.getY();
    }

    public void mouseReleased(MouseEvent arg0) {
        mouse[0] = 0;
        mouse[1] = 0;
        mouse[2] = 0;
    }
}
