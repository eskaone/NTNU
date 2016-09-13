import java.util.*;

class TextEdit {
	private String tekst;
	
	//konstruktoer
	public TextEdit(String tekst) {
		this.tekst = tekst;
	}
	
	//metoder
	public void getText() {
		System.out.println(tekst);
	}
	
	public void getALLCAPSText() {
		System.out.println(tekst.toUpperCase());
	}
	
	public int getWordCount() {
		int wordCount = 0;
		String[] word = tekst.split(" ");
		for (int i = 0; i < word.length; i++) {
			wordCount++;
		}
		return wordCount;
	}
	
	public int getAvgWordLength() { 
		int avgWordLength = 0;
		StringTokenizer analyse = new StringTokenizer(tekst, ".,!?:; ");
		while (analyse.hasMoreTokens()) {
			avgWordLength += analyse.nextToken().length();
		}
		
		return (avgWordLength / getWordCount());
	}
	
	public int getAvgWordPeriod() { 
		int avgWordPeriod = 0;
		int count = 0;
		StringTokenizer analyse = new StringTokenizer(tekst, ".!?:");
		while (analyse.hasMoreTokens()) {
			avgWordPeriod += analyse.nextToken().split(" ").length;
			count++;
		}
		
		return (avgWordPeriod / count);
	}
	
	public void ByttUt() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Hva skal byttes ut?");
		String inputReplaceMe = sc.nextLine();
		System.out.println("Hva skal '" + inputReplaceMe + "' byttes ut med?");
		String inputReplacer = sc.nextLine();
		String nyTekst = tekst.replaceAll(inputReplaceMe, inputReplacer);
		System.out.println("Ny tekst: \n" + nyTekst);
	}
	
}