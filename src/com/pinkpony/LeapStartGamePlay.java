package com.pinkpony;

import com.leapmotion.*;
import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.KeyTapGesture;
import com.leapmotion.leap.ScreenTapGesture;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.Listener;

public class LeapStartGamePlay extends Listener{

	 public void onInit(Controller controller) {
	        System.out.println("Initialized");
	    }

	    public void onConnect(Controller controller) {
	        System.out.println("Connected");
	       
	        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	    }

	    public void onDisconnect(Controller controller) {
	        //Note: not dispatched when running in a debugger.
	        System.out.println("Disconnected");
	    }

	    public void onExit(Controller controller) {
	        System.out.println("Exited");
	    }
	    
	    public void onFrame(Controller controller) {
	        // Get the most recent frame and report some basic information
	    	
	        Frame frame = controller.frame();
	        if (!frame.hands().isEmpty()) {
	            // Get the first hand
	            Hand hand = frame.hands().get(0);

	            // Check if the hand has any fingers
	            FingerList fingers = hand.fingers();
	        }

	        GestureList gestures = frame.gestures();
	        for (int i = 0; i < gestures.count(); i++) {
	            Gesture gesture = gestures.get(i);

	            switch (gesture.type()) {
	    
	            case TYPE_KEY_TAP:
                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
                    System.out.println("Key Tap id: " + keyTap.id()
                               + ", " + keyTap.state()
                               + ", position: " + keyTap.position()
                               + ", direction: " + keyTap.direction());
                    if(keyTap.id()!=-1)
                    {
                    	StateChanger.gameState = GameState.PLAY;
                    	
                    }
                    break;
	                default:
	                    System.out.println("Unknown gesture type.");
	                    break;
	            }	            
	            
	        }
	    }
	
}
