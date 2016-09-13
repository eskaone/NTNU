/*************************************************************
	Class ColorReader for lejos project, "the Useless machine"
	This class processes of data from the touch sensor
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

public class PressureReader {

	private float[] touchSample;
	private EV3TouchSensor touchSensor;
	private SampleProvider touchReader;
	private Port port;

	//constructor
	public PressureReader(Port port) {
		this.port = port;
		touchSensor = new EV3TouchSensor(port);
		touchReader = touchSensor;
		touchSample = new float[touchReader.sampleSize()]; // Register float table for EV3-touchsensor sample values
	}

	//Checks wether the touch sensor has been hit or not
	public boolean toggled() throws Exception {
		touchReader.fetchSample(touchSample, 0); // Save values to first position of the EV3-uttrasonicsensor float table
		return touchSample[0] > 0;
	}

}//class