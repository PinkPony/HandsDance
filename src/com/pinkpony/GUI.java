package com.pinkpony;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame{
	private static final long serialVersionUID = 6411499808530678723L;

	public JLabel label;
	public Button exit;

	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	public GUI(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.label = new JLabel("HandsDance : Pink Pony Edition");
		this.add(label);
		
	/*	this.exit = new Button("Close");
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		this.add(exit);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setBounds(0, 0, screenSize.width, screenSize.height);
		*/
		setVisible(true);

		
		
		
	}
/*
	public class LeapListener extends Listener{
		JLabel label;

		LeapListener(JLabel label){
			this.label = label;
		}

		@Override
		public void onInit(Controller controller){

		}

		@Override
		public void onExit(Controller controller){

		}

		@Override
		public void onConnect(Controller controller){
			System.out.println("bin da");
			controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
			controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
			controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
			controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		}

		@Override
		public void onDisconnect(Controller controller){

		}

		@Override
		public void onFrame(Controller controller){
			
		}
		
	}
*/
	public static void main(String[] args) {
		final GUI gui = new GUI();
		
		
		
		JButton btn1 = new JButton("Full-Screen");
	    btn1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	device.setFullScreenWindow(gui);
	        }
	    });
	    JButton btn2 = new JButton("Normal");
	    btn2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            device.setFullScreenWindow(null);
	        }
	    });
	    
	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    panel.add(btn1);
	    panel.add(btn2);
	    gui.add(panel);
	    gui.pack();
	    
	}
}