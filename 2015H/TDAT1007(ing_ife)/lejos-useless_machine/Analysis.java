/*************************************************************
	Class Analysis for lejos project, "the Useless machine"
	This class analyzes data from all the sensors
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
import lejos.utility.Stopwatch;

//Java classes
import java.util.Random;

public class Analysis {

	//Attributes
	private PressureReader leverStatus;
	private UltrasonicReader eyes;
	private SoundReader ears;
	private AudioPlayer iPod;
	private Commands executor;
	private Outcomes pusher;
	private Random randomVal;
	private Stopwatch timer = new Stopwatch();

	//Constructor
	public Analysis(PressureReader leverStatus, UltrasonicReader eyes, SoundReader ears, AudioPlayer iPod, Commands executor, Outcomes pusher) {
		this.leverStatus = leverStatus;
		this.eyes = eyes;
		this.ears = ears;
		this.iPod = iPod;
		this.executor = executor;
		this.pusher = pusher;
	}

	//Will loop until sound is registered
	private void waitForInit() throws Exception {
		while(!ears.triggered()) {
			executor.moveLever(0, 200);
		}
	}
	
	/*
	*The method below processes the fact that the lever has been hit
	*It's intended to make the robot seem like it takes rushed decitions
	*/
	private void analyzePressure() throws Exception {
		//Checks wether the lever is on or off
		if(leverStatus.toggled()) {
			/*
			*Switch below is designed to generate a number of seemingly random outcomes
			*In 9 out of 25 outcomes, the bot does a special move
			*There are 10 different moves in total
			*The "default" case contains the standart way to hit the lever, and happens in 14 out of 25 outcomes
			*/

			int randValue = getRandomVal(0, 24);
			switch (randValue) {

			case 1:
				pusher.fastDodgePush();
				break;

			case 2:
				pusher.fastPush();
				break;

			case 3:
				pusher.slowPush();
				break;

			case 4:
				pusher.peekPush();
				break;

			case 5:
				pusher.dodgePush();
				break;

			case 6:
				pusher.delayPush();
				break;

			case 7:
				pusher.cenaPush();
				break;

			case 8:
				pusher.longDelayPush();
				break;

			case 9:
				pusher.holdPush();
				break;

			default:
				pusher.classicPush();
				break;
			}//switch
		}
	}//void

	/*
	*The method analyseSpace uses data from the Ultrasonic sensor
	*It registeres wether someone is attempting to hit the lever
	*At certain outcomes, decided by a random variable, the robot will hide the lever
	*The first 5 lever hits are supposed to go as normal, therefore a counter is placed in the method AnalyzePressure()
	*/
	private void analyzeSpace() throws Exception {
		if (!leverStatus.toggled() && eyes.registered() && timer.elapsed() > 10000 && getRandomVal(0, 4500) < 2) {
			pusher.hideLever(100);
		} else if (!leverStatus.toggled() && eyes.registered() && timer.elapsed() > 10000 && getRandomVal(0, 4500) < 1) {
			pusher.driveAway(650, 800);
		}
	}

	//Sub method that generates a random value within decired interval, momentarily accecible by other classes
	private int getRandomVal(int min, int max) {
		randomVal = new Random();
		return randomVal.nextInt(max - min) + min;
	}

	//Lifts the hidden lever making it available for toggling, thus the game is started
	public void init() throws Exception {
		waitForInit();//Loops until sound is registered
		iPod.getSound("startup.wav");
		executor.moveLever(70, 50);
	}

	//Calls and gathers the other functional methods, which will repeat infinitely
	public void run() throws Exception {
		while(true){
			analyzePressure();
			analyzeSpace();
		}
	}//void

}//class