import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.robotics.navigation.DifferentialPilot;
// Kanskje bruke dette
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureDetectorAdapter;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import java.util.Random;


public class Main{
	public static void main (String[] arg) throws Exception  {

		// Definerer sensorer:
		Brick brick = BrickFinder.getDefault();
		Port s1 = brick.getPort("S1"); // EV3-uttrasonicsensor
		Port s3 = brick.getPort("S3"); // EV3-trykksensor
		Port s4 = brick.getPort("S4"); // EV3-trykksensor
		EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(s1); // EV3-uttrasonicsensor

		/* Definerer en ultrasonisksensor */
		SampleProvider ultrasonicLeser = ultrasonicSensor.getDistanceMode();
		float[] ultrasonicSample = new float[ultrasonicLeser.sampleSize()]; // tabell som inneholder avlest verdi

		// Registrerer differentialPilot
		DifferentialPilot pilot = new DifferentialPilot(56, 120, Motor.B, Motor.C, false);
		pilot.setTravelSpeed(80);
		pilot.setRotateSpeed(100);

		// Kjør roboten
		boolean kjor = true;
		while (kjor) {
		java.util.Random r = new java.util.Random();
		int rot = r.nextInt(2);
			// Unngå hindringer logikk
			ultrasonicLeser.fetchSample(ultrasonicSample, 0);
				if(ultrasonicSample[0] < 0.25) {
					if (rot == 1) {
						pilot.rotateLeft();
					} else {
						pilot.rotateRight();
					}
				} else {
					pilot.forward();
				}
			}

		}

	}