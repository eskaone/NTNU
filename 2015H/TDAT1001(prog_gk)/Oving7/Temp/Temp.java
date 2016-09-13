import java.util.Random;

class Temp {
	private int hour;
	private int day;
	private int[][] tabellTemp;
	private int[] middelTempDay;
	private int[] middelTempHour;
	private int[] middelTempMonth;
	private int[] tempGroups;
	Random r = new Random();
	
	//konstruktør
	public Temp() {
		tabellTemp = new int[24][31];
		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 24; j++) {
				tabellTemp[j][i] = r.nextInt(50) - 20;
			}
		}
	}
	
	//metoder
	public void getMiddelTempDay() {
		middelTempDay = new int[31];
		for (int i = 0; i < 31; i++) {
			int sum = 0;
			for (int j = 0; j < 24; j++) {
				sum += tabellTemp[j][i];
				middelTempDay[i] = sum / 24;
			}
		}
		for (int i = 0; i < 31; i++) {
			System.out.println("Dag " + (i+1) + ": "  + middelTempDay[i] + " grader");
		}
	}

	public void getMiddelTempHour() {
		middelTempHour = new int[24];
		for (int i = 0; i < 24; i++) {
			int sum = 0;
			for (int j = 0; j < 31; j++) {
				sum += tabellTemp[i][j];
				middelTempHour[i] = sum / 24;
			}
		}
		for (int i = 0; i < 24; i++) {
			System.out.println("Kl. " + (i+1) + ":00 : "  + middelTempHour[i] + " grader");
		}
	}

	public int getMiddelTempMonth() {
		int middelTempMonth = 0;
		int sum = 0;
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 31; j++) {
				sum += tabellTemp[i][j];
			}
		}
		middelTempMonth = sum / 744;
		return middelTempMonth;
	}

	public void getTempGroups() {
		tempGroups = new int[5];
		getMiddelTempDayTEMP();
		for (int i = 0; i < 31; i++) {
			
			if (middelTempDay[i] < -5) {
				tempGroups[0]++;
			} else if (middelTempDay[i] > -6 && middelTempDay[i] < 1) {
				tempGroups[1]++;
			} else if (middelTempDay[i] > 0 && middelTempDay[i] < 6) {
				tempGroups[2]++;
			} else if (middelTempDay[i] > 5 && middelTempDay[i] < 11) {
				tempGroups[3]++;
			} else if (middelTempDay[i] > 10) {
				tempGroups[4]++;
			}
		}
		
		System.out.println("Dager med mindre enn -5 grader: " + tempGroups[0]);
		System.out.println("Dager med mellom -5 og 0 grader: " + tempGroups[1]);
		System.out.println("Dager med mellom 0 og 5 grader: " + tempGroups[2]);
		System.out.println("Dager med mellom 5 og 10 grader: " + tempGroups[3]);
		System.out.println("Dager med mer enn 10 grader: " + tempGroups[4]);
	}




	//Testing
	public void getTabell() {
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 31; j++) {
				System.out.println(tabellTemp[i][j]);
			}
		}
	}

	public void getTabellTest() {
		System.out.println(tabellTemp[23][10]);
	}

	public void getMiddelTest() {
		int gjennomsnitt = 0;
		int sum = 0;
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 31; j++) {
				sum += tabellTemp[i][j];
			}
		}
		gjennomsnitt = sum / 744;
		System.out.println(gjennomsnitt);
	}

	private void getMiddelTempDayTEMP() {
		middelTempDay = new int[31];
		for (int i = 0; i < 31; i++) {
			int sum = 0;
			for (int j = 0; j < 24; j++) {
				sum += tabellTemp[j][i];
				middelTempDay[i] = sum / 24;
			}
		}
	}
}