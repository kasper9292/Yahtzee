import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Yahtzee {

	private Speler speler = new Speler();
	private boolean game = true;
	private int[] vasthouden = { 0, 0, 0, 0, 0 };
	private ArrayList<Integer> worp = new ArrayList<Integer>();
	private ArrayList<String> opties = new ArrayList<String>();
	private ArrayList<String> optiesPunten = new ArrayList<String>();

	public static void main(String[] args) {
		new Yahtzee().startSpel(); // maakt een yahtzee class die het spel begint
	}

	public void startSpel() {
		System.out.println("Welkom bij Yahtzee!");
		//System.out.println("De top 5 scores komen in de highscorelijst.");
		//System.out.println("Highscorelijst: bob[21], jan[12], piet[10]");
		System.out.println("helaas nog niet af, alleen dobbelstenen rollen en punten tellen zit er nu in");

		System.out.println("\nHet spel begint:");
		while (game) {
			speler.setWorp(vasthouden);
			System.out.print("[speler] werpt: ");
			worp = speler.getWorp();
			for (int w : worp) { // (type var : arraylist)
				System.out.print("[" + w + "]");
			}

//			for (int i = 0; i < 3; i++) {
//				System.out.println("\nKies welke posities van 1 tot 5 u opnieuw wilt rollen of (n) om niets te rollen: ");
//				System.out.print("Typ hier uw keuze: ");
//				String input = getInput();
//				if (input.equals("n")) {
//					break;
//				} else {
//					for (int j = 0; j < input.length(); j++) {
//						vasthouden[j] = Integer.parseInt(input.substring(j, j));
//					}
//				}
//				for (int AA : vasthouden) {
//					System.out.print(AA);
//				}
//			}

			getOpties();
			//System.out.println("nep Uw opties zijn (v)vasthouden, (1)enen, (2)kleine straat, (3)drie gelijke");
			// getInput();

			game = false;
		}

	}

	public String getInput() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		return input;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getOpties() {
		opties.clear();
		optiesPunten.clear();
		Collections.sort(worp);
		int optieNR = 0;
//		worp.clear();
//		worp.add(0, 3);
//		worp.add(1, 3);
//		worp.add(2, 4);
//		worp.add(3, 5);
//		worp.add(4, 6);

		// check voor grote straat
		if (worp.get(0) + 1 == worp.get(1) && worp.get(1) + 1 == worp.get(2) && worp.get(2) + 1 == worp.get(3) && worp.get(3) + 1 == worp.get(4)) {
			optieNR++;
			opties.add("(" + optieNR + ") grote straat");
			optiesPunten.add("40");
		}		
		
		// check voor kleine straat
		else {
			ArrayList<Integer> tempWorp = new ArrayList<Integer>(); // tijdelijke worp lijst om aan te passen
			tempWorp = (ArrayList<Integer>) worp.clone(); // @SuppressWarnings("unchecked")
			for (int i = 1; i <= 6; i++) {
				if (tempWorp.contains(i) && Collections.frequency(tempWorp, i) == 2) { // verwijder dubbelle (kan niet in kleine straat)
					tempWorp.remove(tempWorp.indexOf(i));
				}
			}
			if (tempWorp.size() == 5) {
				if ((tempWorp.get(0) + 1 == tempWorp.get(1) && tempWorp.get(1) + 1 == tempWorp.get(2) && tempWorp.get(2) + 1 == tempWorp.get(3))
				|| (tempWorp.get(1) + 1 == tempWorp.get(2) && tempWorp.get(2) + 1 == tempWorp.get(3) && tempWorp.get(3) + 1 == tempWorp.get(4))) {
					optieNR++;
					opties.add("(" + optieNR + ") kleine straat");
					optiesPunten.add("30");
				}
			}
			else if (tempWorp.size() == 4) {
				if ((tempWorp.get(0) + 1 == tempWorp.get(1) && tempWorp.get(1) + 1 == tempWorp.get(2) && tempWorp.get(2) + 1 == tempWorp.get(3))) {
					optieNR++;
					opties.add("(" + optieNR + ") kleine straat");
					optiesPunten.add("30");
				}
			}
		}
		
		// check voor fullhouse
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				if (Collections.frequency(worp, i) == 3 && Collections.frequency(worp, j) == 2) {
					optieNR++;
					opties.add("(" + optieNR + ") full house");
					optiesPunten.add("25");
				}
			}
		}
		
		// check voor drie gelijke, vier gelijke of Yahtzee!
		for (int i = 1; i <= 6; i++) {
			if (worp.contains(i) && Collections.frequency(worp, i) >= 3) { // eerst checken voor contains om onnodige loops te voorkomen
				optieNR++;
				switch (Collections.frequency(worp, i)) {
				case 3: opties.add("(" + optieNR + ") drie gelijke"); optiesPunten.add(Integer.toString(somWorp())); break;
				case 4: opties.add("(" + optieNR + ") vier gelijke"); optiesPunten.add(Integer.toString(somWorp())); break;
				case 5: opties.add(0, "(" + optieNR + ") Yahtzee!"); optiesPunten.add(0, "50"); break;
				}				
			}
		}

		// check voor kans
		optieNR++;
		opties.add("(" + optieNR + ") kans");
		optiesPunten.add(Integer.toString(somWorp()));

		// check voor zelfdes
		for (int i = 1; i <= 6; i++) {
			if (worp.contains(i)) {
				optieNR++;
				optiesPunten.add(Integer.toString(Collections.frequency(worp, i) * i));
				switch (i) {
				case 6: opties.add("(" + optieNR + ") zessen"); break;
				case 5: opties.add("(" + optieNR + ") vijfen"); break;
				case 4: opties.add("(" + optieNR + ") vieren"); break;
				case 3: opties.add("(" + optieNR + ") drieën"); break;
				case 2: opties.add("(" + optieNR + ") tweeën"); break;
				case 1: opties.add("(" + optieNR + ") enen"); 	break;
				}
			}
		}

		System.out.println("Opties: " + opties);
		System.out.println("[punten per optie: " + optiesPunten + "]");

		return opties;
	}
	
	public int somWorp() {
		int som = 0;
		for (int w : worp) { // (type var : arraylist)
			som += w;
		}
		return som;
	}
}
