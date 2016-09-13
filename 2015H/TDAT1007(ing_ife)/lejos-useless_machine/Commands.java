/*************************************************************
	Class Commands for lejos project, "the Useless machine"
	This class operates all of the robot's physical movements
	By developers Elias, Kristoffer, Ole Kristian and Haakon
**************************************************************/

//Lejos classes
import lejos.utility.*;
import lejos.hardware.motor.*; // Importing all motor classes
import lejos.hardware.lcd.*; // Importing all LCD screen classes
import lejos.hardware.*; // Importing all hardware classes
import lejos.hardware.port.Port; // Importing Port class
import lejos.hardware.ev3.EV3; // Importing EV3 class
import lejos.robotics.SampleProvider; // Importing SampleProvider class
import lejos.hardware.sensor.*; // Importing all sensor classes
import lejos.robotics.navigation.DifferentialPilot; // Importing DifferentialPilot class

//Java classes
import java.io.*;

public class Commands {

	// This method drives the robot to a desired distance
	public void drive(int distance, int speed) throws Exception {

		// Motor.C

		Motor.C.setSpeed(speed);
		Motor.C.rotateTo(distance, false); // Drive one direction, to given position
		Motor.C.rotateTo(0, false); // Return to original position
	}
	//Moves the "arm" to a desired position, at a desired speed
	public void moveArm(int rotation, int motorspeed, boolean immediateReturn) throws Exception {
		Motor.B.setSpeed(motorspeed);
		Motor.B.rotateTo(rotation, immediateReturn); // Top: rotateTo(-100)
	}
	// This method moves the lever to a desired position
	public void moveLever(int rotation, int motorspeed) throws Exception {
		Motor.D.setSpeed(motorspeed);
		Motor.D.rotateTo(rotation); // Top: rotateTo(70)
	}

	//Give the robot a timed delay before it hits the lever.
	public void sleep (long ms) throws Exception {
		Delay.msDelay(ms);
	}

}//class