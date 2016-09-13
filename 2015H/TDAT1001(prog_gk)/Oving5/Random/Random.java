class Random {
	private double randomDesimal;
	private int randomHeltall;
	java.util.Random r = new java.util.Random();
	
	
	public double getRandomDesimal(double nedreDesimal, double ovreDesimal) {
		randomDesimal = (r.nextDouble()*(ovreDesimal-nedreDesimal)) + nedreDesimal;
		return randomDesimal;
	}
	
	public int getRandomHeltall(int nedreHeltall, int ovreHeltall) {
		randomHeltall = r.nextInt(ovreHeltall-nedreHeltall) + nedreHeltall;
		return randomHeltall;
	}
	
}