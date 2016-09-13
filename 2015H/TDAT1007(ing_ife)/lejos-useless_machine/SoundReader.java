/*************************************************************
	Class SoundReader for lejos project, "the Useless Machine"
	This class processes data from the sound sensor
	By developers Elias, Kristoffer, Ole Kristian and Haakon
**************************************************************/

//Lejos classes
import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;

public class SoundReader {

	private float[] soundSample;
	private SampleProvider soundReader;
	private	NXTSoundSensor soundSensor;
	private Port port;

	//Constructor
	public SoundReader(Port port) {
		this.port = port;
		soundSensor = new NXTSoundSensor(port);
		soundReader = soundSensor.getDBAMode();
		soundSample = new float[soundReader.sampleSize()];
	}

	//Returns true if it registers a sound above a certain decibel level
	public boolean triggered() {
		soundReader.fetchSample(soundSample, 0);
		return soundSample[0] > 0.9;
	}
	
}