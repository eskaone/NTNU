/*************************************************************
	Class Audioplayer for lejos project, "the Useless machine"
	This class has responsibility for playing audio files
	By developers Elias, Kristoffer, Ole Kristian and Haakon
**************************************************************/

//Lejos classes
import lejos.hardware.Brick;
import lejos.hardware.port.Port;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.*;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.robotics.pathfinding.Path;
import lejos.hardware.Sounds.*;
import lejos.hardware.Audio.*;
import lejos.remote.ev3.*;
import lejos.hardware.Sound;

//Java classes
import java.io.File;

public class AudioPlayer {
	private int masterVolume;
	File file;
	String filename;

	// Constructor
	public AudioPlayer(int masterVolume) {
		this.masterVolume = masterVolume;
		Sound.setVolume(masterVolume);
	}
	
	//Generates sound value from file
	private static int generateSound(File file) throws Exception {
		return Sound.playSample(file);
	}
	
	//Set sound at desired sound file.
	private void setSound(String name) {
		filename = name;
	}

	//Runs sound sample, catching exceptions
	public void getSound(String name) {
		setSound(name);
		Runnable task = new Runnable() {
			public void run() {
				try {
					file = new File(filename);
					int filelength = generateSound(file);
				} catch (Exception e){
					System.out.println(e);
				}
			}
		};

		new Thread(task).start();//Start thread
	}//void

}