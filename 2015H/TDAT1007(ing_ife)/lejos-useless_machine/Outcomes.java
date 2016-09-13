/*************************************************************
	Class Outcomes for lejos project, "the Useless Machine"
	This class handles various functional outcomes for the useless machine
	By developers Elias, Kristoffer, Ole Kristian and Haakon
**************************************************************/

//Lejos classes
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.Brick;
import lejos.hardware.port.Port;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;

public class Outcomes {
	private Commands executor;
	private AudioPlayer iPod;

	//Constructor
	public Outcomes(Commands executor, AudioPlayer iPod) {
		this.executor = executor;
		this.iPod = iPod;
	}

	//The robot hides the lever from itself while it tries to toggle it
	private void dodge(long ms) throws Exception {
		executor.sleep(ms);
		executor.moveArm(-90, 350, true);
		executor.moveLever(35, 300);
		executor.moveArm(0, 200, false);
		executor.moveLever(70, 25);
	}
	
	//The robot lifts the arm a small distance to make it appear like it peeks at the lever
	private void peek() throws Exception {
		executor.moveArm(-50, 200, false);
		executor.sleep(1750);
		executor.moveArm(0, 350, false);
		executor.sleep(750);
	}

	//Method classicPush is the "normal" hit lever outcome
	public void classicPush() throws Exception {
		executor.moveArm(-100, 200, false);
		executor.moveArm(0, 200, false);
	}

	//Methods slowPush and fastPush, push the lever at different speeds
	public void slowPush() throws Exception {
		executor.moveArm(-45, 250, false);
		executor.moveArm(-100, 40, false);
		executor.moveArm(0, 80, false);
	}
	
	public void fastPush() throws Exception {
		executor.moveArm(-100, 350, true);
		executor.sleep(125);
		iPod.getSound("wah.wav");
		executor.sleep(125);
		executor.moveArm(0, 400, false);
	}

	//The robot peeks first, then retires. After a delay, it hits the lever.
	public void peekPush() throws Exception {
		iPod.getSound("jump.wav");
		peek();
		fastPush();
	}

	//The lever is pushed after a delay
	public void delayPush() throws Exception {
		//Wait for a second, then hit the lever normally
		executor.sleep(1000);
		executor.moveArm(-50, 350, false);
		executor.sleep(1500);
		executor.moveArm(-100, 50, true);
		executor.sleep(650);
		iPod.getSound("coin.wav");
		//wait a bit on the top before returning
		executor.sleep(1500);
		executor.moveArm(0, 350, false);
	}
	
	//The lever is pushed after a longer delay, to make it seem like the user has won.
	public void longDelayPush() throws Exception {
		//Wait for nearly a second before executing delayPush
		executor.sleep(750);
		delayPush();
	}

	//In methods dodgePush and fastDodgePush, the bot is unable to hit the lever, as it hides the lever from itself
	public void dodgePush() throws Exception {
		dodge(750);
		peekPush();
	}
	
	public void fastDodgePush() throws Exception {
		dodge(150);
		executor.sleep(400);
		fastPush();
	}

	//The robot goes into a "crazy mode", playing music while doing several attempts to hit the lever.
	public void cenaPush() throws Exception {
		//Plays John Cena song
		iPod.getSound("cena.wav");
		executor.sleep(1550);//wait for 1,5 seconds
		//Dodge a few times so the bot can't hit the lever
		dodge(750);
		dodge(100);
		dodge(50);
		dodge(10);
		dodge(0);
		dodge(0);
		//Eventually it hits the lever to normalize it
		classicPush();
	}

	//The robot peeks at the lever, then it hits and holds the lever for a second
	public void holdPush() throws Exception {
		executor.moveArm(-100, 300, false);
		executor.sleep(1750);
		executor.moveArm(0, 250, false);
	}

	//Hides the lever from user
	public void hideLever(long ms) throws Exception {
		iPod.getSound("pipe.wav");
		executor.moveLever(0, 300);
		executor.sleep(ms);
		executor.moveLever(70, 150);
	}

	//The robot "dodges" its user by switching position, hence it prevents the lever from being hit.
	public void driveAway(int distance, int speed) throws Exception {
		iPod.getSound("pokemon.wav");
		executor.drive(distance, speed);
	}

	//Play soundsamples
	public void playSample(String filename) throws Exception {
		iPod.getSound(filename);
	}

}//class
