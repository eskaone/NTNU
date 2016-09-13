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


public class Main{
	public static void main (String[] arg) throws Exception  {
		
		// Definerer sensorer:
		Brick brick = BrickFinder.getDefault();
		Port s1 = brick.getPort("S1"); // EV3-uttrasonicsensor
		Port s3 = brick.getPort("S3"); // EV3-trykksensor_Rettfram
		Port s4 = brick.getPort("S4"); // EV3-trykksensor_HOYRE
		EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(s1); // EV3-uttrasonicsensor
		EV3TouchSensor trykkSensorRettfram = new EV3TouchSensor(s3); // EV3-trykksensor_Rettfram
		EV3TouchSensor trykkSensorH = new EV3TouchSensor(s4); // EV3-trykksensor_HOYRE
		
		/* Definerer en ultrasonisk-sensor */
		SampleProvider ultrasonicLeser = ultrasonicSensor.getDistanceMode();
		float[] ultrasonicSample = new float[ultrasonicLeser.sampleSize()]; // tabell som inneholder avlest verdi
		
		/* Definerer en trykksensor */
		SampleProvider trykkLeserH = trykkSensorH; // 1 eller 0
		float[] trykkSampleH = new float[trykkLeserH.sampleSize()]; // tabell som inneholder avlest verdi
		
		SampleProvider trykkLeserRettfram = trykkSensorRettfram; // 1 eller 0
		float[] trykkSampleRettfram = new float[trykkLeserRettfram.sampleSize()]; // tabell som inneholder avlest verdi
		
		// Registrerer differentialPilot
		DifferentialPilot pilot = new DifferentialPilot(56, 120, Motor.B, Motor.C, false);
		pilot.setTravelSpeed(200);
		pilot.setRotateSpeed(100);
		int distance = 100;
		
		// Kjør roboten
		boolean kjor = true;
		while (kjor) {
			
			// Ultrasonic ting
			ultrasonicLeser.fetchSample(ultrasonicSample, 0);
			if(ultrasonicSample[0] < 0.2) {
				pilot.rotateRight();
			} else {
				pilot.forward();
			}
			
			// Trykksensor ting
			trykkSensorH.fetchSample(trykkSampleH, 0);
			if (trykkSampleH[0] > 0){
				if(ultrasonicSample[0] < 0.2) {
				pilot.rotateLeft();
			} else {
				pilot.forward();
			}
			} else if (trykkSampleRettfram[0] > 0) {
				pilot.backward();
			} else {
				pilot.forward();
			}
		}
		
		System.out.println("Avsluttet");
		Thread.sleep(300);
		
	}
}