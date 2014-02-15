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


public class LeapController extends Listener{

	 public void onInit(Controller controller) {
	        System.out.println("Initialized");
	    }

	    public void onConnect(Controller controller) {
	        System.out.println("Connected");
	        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
	        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
	        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
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
	        /*System.out.println("Frame id: " + frame.id()
	                         + ", timestamp: " + frame.timestamp()
	                         + ", hands: " + frame.hands().count()
	                         + ", fingers: " + frame.fingers().count()
	                         + ", tools: " + frame.tools().count()
	                         + ", gestures " + frame.gestures().count());*/

	        if (!frame.hands().isEmpty()) {
	            // Get the first hand
	            Hand hand = frame.hands().get(0);

	            // Check if the hand has any fingers
	            FingerList fingers = hand.fingers();
	            if (!fingers.isEmpty()) {
	                // Calculate the hand's average finger tip position
	                Vector avgPos = Vector.zero();
	                for (Finger finger : fingers) {
	                    avgPos = avgPos.plus(finger.tipPosition());
	                }
	                avgPos = avgPos.divide(fingers.count());
	                /*System.out.println("Hand has " + fingers.count()
	                                 + " fingers, average finger tip position: " + avgPos);*/
	            }

	            // Get the hand's sphere radius and palm position
	            /*System.out.println("Hand sphere radius: " + hand.sphereRadius()
	                             + " mm, palm position: " + hand.palmPosition());*/

	            // Get the hand's normal vector and direction
	            Vector normal = hand.palmNormal();
	            Vector direction = hand.direction();

	            // Calculate the hand's pitch, roll, and yaw angles
	           /* System.out.println("Hand pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
	                             + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
	                             + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");*/
	        }

	        GestureList gestures = frame.gestures();
	        for (int i = 0; i < gestures.count(); i++) {
	            Gesture gesture = gestures.get(i);

	            switch (gesture.type()) {
	                case TYPE_CIRCLE:
	                    CircleGesture circle = new CircleGesture(gesture);

	                    // Calculate clock direction using the angle between circle normal and pointable
	                    String clockwiseness;
	                    if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/4) {
	                        // Clockwise if angle is less than 90 degrees
	                        clockwiseness = "clockwise";
	                    } else {
	                        clockwiseness = "counterclockwise";
	                    }

	                    // Calculate angle swept since last frame
	                    double sweptAngle = 0;
	                    if (circle.state() != State.STATE_START) {
	                        CircleGesture previousUpdate = new CircleGesture(controller.frame(1).gesture(circle.id()));
	                        sweptAngle = (circle.progress() - previousUpdate.progress()) * 2 * Math.PI;
	                    }
	                    break;
	                case TYPE_SWIPE:
	                    SwipeGesture swipe = new SwipeGesture(gesture);
	                    System.out.println("Swipe id: " + swipe.id()
	                               + ", " + swipe.state()
	                               + ", position: " + swipe.position()
	                               + ", direction: " + swipe.direction()
	                               + ", speed: " + swipe.speed());
	                    break;
	                case TYPE_SCREEN_TAP:
	                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
	                    System.out.println("Screen Tap id: " + screenTap.id()
	                               + ", " + screenTap.state()
	                               + ", position: " + screenTap.position()
	                               + ", direction: " + screenTap.direction());
	                    break;
	                case TYPE_KEY_TAP:
	                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
	                    System.out.println("Key Tap id: " + keyTap.id()
	                               + ", " + keyTap.state()
	                               + ", position: " + keyTap.position()
	                               + ", direction: " + keyTap.direction());
	                    break;
	                default:
	                    System.out.println("Unknown gesture type.");
	                    break;
	            }	            
	            
	            
	          //Detecting Sign:
	          char v = detectSign(frame);
	           System.out.println("Char is : " + v);

	        if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
	            System.out.println();
	        }
	    }
	    }

	   public char detectSign(Frame frame){
		  
		   char c = 0;
		   Hand hand = frame.hands().get(0);
		   if(isAlphabetV(hand))
		   {
		   return 'v';
		   }
		   else if(isAlphabetY(hand))
		   {
		   return 'y';
		   }
		   else if(isAlphabetL(hand))
		   {
		   return 'L';
		   }
		   else if(isAlphabet5(hand))
		   {
		   return '5';
		   }
		   else if(isAlphabetI(hand))
		   {
		   return 'i';
		   }
		   
		  return c;  
	   }
	   
	   public boolean isAlphabetV(Hand hand)
	   {
		   /*
			Palm orientation : facing front
			Palm angle: vertical to device
			No. of fingers : 2
			Finger 1 angle : 45-90
			Finger 2 angle : 135-90
			Finger 1 angle from palm direction : 0-20
			Finger 2 angle from palm direction : 0-20
			Angle between two fingers : 15-40
			FingerTip Position: y co-ordinate almost same, x will vary.
		    */
		   	
		   	if(hand.fingers().count()==2)
		   	{
		   		Finger index = hand.fingers().leftmost();
		   		Finger middle = hand.fingers().rightmost();
		   		Vector indexVector = index.direction();
		   		Vector middleVector = middle.direction();
		   		
		   		//find angle between index and middle finger
		   		float angle = indexVector.angleTo(middleVector);
				double degrees = Math.toDegrees(angle);
				
				if(degrees > 10 && degrees < 40) {
					//return true;
					////check fingertip  postion

					Vector indexTip =  index.stabilizedTipPosition();
					Vector middleTip = middle.stabilizedTipPosition();

					int Y_ALLOWED_DIFF = 20;
					if(Math.abs(indexTip.getY() - middleTip.getY()) < Y_ALLOWED_DIFF)
					{
						return true;
					}
				}
		   	}
			return false;
	   }
	   public boolean isAlphabetL(Hand hand)
	   {
		   /*
			Palm orientation : facing front
			Palm angle: vertical to device
			No. of fingers : 2
			Finger 1 angle : 180 (horizontal)
			Finger 2 angle : 90 (vertical)
			Finger 1 angle from palm direction : 90
			Finger 2 angle from palm direction : 0
			Angle between two fingers : 90
			The 2nd finger is to the left of the palm's center(x coordinate)
			(all angles are in approx)
		    */
		   	
		   	if(hand.fingers().count()==2)
		   	{
		   		Finger thumb = hand.fingers().leftmost();
		   		Finger index = hand.fingers().rightmost();
		   		
		   		Vector thumbVector = thumb.direction();
		   		Vector indexVector = index.direction();
		   		
		   		//find angle between index and middle finger
		   		float angle = thumbVector.angleTo(indexVector);
				double degrees = Math.toDegrees(angle);
				
				if(degrees >40 && degrees < 110)
				{ 
					float indexX = index.tipPosition().getX();
					float palmX = hand.palmPosition().getX();
					if(indexX < palmX)
					{
						return true;
					}
				}
		   	}
			return false;
	   }
	   
	   public boolean isAlphabetY(Hand hand)
	   {
		   /*
			Palm orientation : facing front
			Palm angle: vertical to device
			No. of fingers : 2
			Finger 1 angle : 180 (horizontal)
			Finger 2 angle : 0 (vertical)
			Finger 1 angle from palm direction : 90
			Finger 2 angle from palm direction : 90
			Angle between two fingers : 180
			The 2nd finger is to the right of the palm's centre(x coordinate)
			(all angles are in approx)
		    */
		   	
		   	if(hand.fingers().count()==2)
		   	{
		   		Finger thumb = hand.fingers().leftmost();
		   		Finger pinky = hand.fingers().rightmost();
		   		Vector thumbVector = thumb.direction();
		   		Vector pinkyVector = pinky.direction();
		   		
		   		//find angle between index and middle finger
		   		float angle = thumbVector.angleTo(pinkyVector);
				double degrees = Math.toDegrees(angle);
				
				if(degrees >45 && degrees < 90)
				{
					float indexX = pinky.tipPosition().getX();
					float palmX = hand.palmPosition().getX();
					if(pinky.tipPosition().getX() > hand.palmPosition().getX())
					{
						return true;
					}
				}
		   	}
			return false;
	   }
	   
	   public boolean isAlphabet5(Hand hand)
	   {
		   /*
			Palm orientation : facing front
			All Pointables Visible
		    */
		   	
		   	if(hand.fingers().count()==5)
		   	{
		   		return true;
		   	}
			return false;
	   }
	   public boolean isAlphabetI(Hand hand)
	   {
		   /*
			Palm orientation : facing front
			Palm angle: vertical to device
			No. of fingers : 1
			
		    */
		   	
		   	if(hand.fingers().count()==1)
		   	{
		   		Finger index = hand.fingers().leftmost();
		   		//Finger index2 = hand.fingers().rightmost();
		   		//Vector indexVector = index.direction();
		   		
		   		if(index.tipPosition().getX() > hand.palmPosition().getX())
				{
					return true;
				}
		   		return true;
		   	}
			return false;
	   }
	   
}