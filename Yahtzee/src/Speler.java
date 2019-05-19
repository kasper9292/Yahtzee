import java.util.ArrayList;
import java.util.Collections;

public class Speler {
	private String naam;
	private int highScore;
	private ArrayList<Integer> dobbelstenenAL = new ArrayList<Integer>();

	public Speler() {
		for (int i = 0; dobbelstenenAL.size() < 5; i++) { // maak 5 dobbelstenen
			Dobbelsteen dobbelsteen = new Dobbelsteen();
			dobbelstenenAL.add(i, dobbelsteen.getOog());
		}
	}

	public void setWorp(int[] vasthouden) { // if continue gebruiken // speler heeft arraylit van dobbelstene
		// return
	}

	public ArrayList<Integer> getWorp() {
		return dobbelstenenAL; // speler heeft arraylit van dobbelstene
	}

	public void sorteerWorp() {
		Collections.sort(dobbelstenenAL);
	}

	public void setNaam() {
		this.naam = "bob";
	}

	public String getNaam() {
		return naam;
	}

	public int getHighScore() {
		return highScore;
	}

}
