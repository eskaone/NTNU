class Brok {
	private int teller;
	private int nevner;
	
	
	//konstruktør
	public Brok(int teller, int nevner) {
		if (nevner == 0) {
			throw new IllegalArgumentException("Kan ikke dele med 0.");
		}
		this.teller = teller;
		this.nevner = nevner;
	}
	
	public Brok(int teller) {
		this.teller = teller;
		this.nevner = 1;
	}
	
	//metoder
	public int getTeller() {
		return teller;
	}
	
	public int getNevner() {
		return nevner;
	}
	
	
	//summere
	public void add(Brok b) {
		this.teller = (this.teller * b.getNevner()) + (this.nevner * b.getTeller());
		if (this.nevner == b.getNevner()) {
			this.nevner = b.getNevner();
		} else {
			this.nevner *= b.getNevner();
		}
	}
	
	public void sub(int teller, int nevner) {
		this.teller = (this.teller * nevner) - (this.nevner * teller);
		if (this.nevner == nevner) {
			this.nevner = nevner;
		} else {
			this.nevner *= nevner;
		}
	}
	
	public void mult(int teller, int nevner) {
		this.teller *= teller;
		this.nevner *= nevner;
	}
	
	public void div(int teller, int nevner) {
		this.teller *= nevner;
		this.nevner *= teller;
	}
}