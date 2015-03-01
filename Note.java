//package staffToTab;

/* Each Note holds a number, string number, and fret number */
public class Note {

	public final int ID_NUMBER;
	private String[] translation;

	/* Form --> noteArray[string][fret] = note number */
	public static int[][] noteArray;
	public int string;
	public int fret;

	public Note(int id) {
		ID_NUMBER = id;
		translation = makeTranslation();
		if (noteArray==null) {
			noteArray = makeNoteArray();
		}
		for (int i = noteArray.length-1; i>=0; i--) {
			for (int j = 0 ; j < noteArray[0].length; j++) { 
				if (noteArray[i][j] == number()) {
					string = i;
					fret = j;
					break;
				}
			}
		}
	}

	public int number() {
		return ID_NUMBER;
	}

	private static String[] makeTranslation() {
		String[] trans = new String[12];
		trans[0] = "C";
		trans[1] = "C#";
		trans[2] = "D";
		trans[3] = "D#";
		trans[4] = "E";
		trans[5] = "F";
		trans[6] = "F#";
		trans[7] = "G";
		trans[8] = "G#";
		trans[9] = "A";
		trans[10] = "A#";
		trans[11] = "B";

		return trans;
	}

	public boolean sharps = true;

	public String toString() {
		int letter_num;
		if (ID_NUMBER >= 0)
			letter_num = ID_NUMBER%12;
		else {
			int multiplier = (int) (ID_NUMBER/12) + 1;
			letter_num = ID_NUMBER + (12 * multiplier);
		}

		if (sharps)
			return translation[letter_num];
		else {
			String name;
			switch(letter_num) {
				case 1: 
					name = "D♭";
					break;
				case 3:
					name = "E♭";
					break;
				case 6:
					name = "G♭";
					break;
				case 8:
					name = "A♭";
					break;
				case 10:
					name = "B♭";
					break;
				default:
					name = translation[letter_num];
					break;
				}
			return name;
		}
	}

	public static int[][] makeNoteArray() {
		// notes[i][j]
		int first = -8; // starts at -8 (E note)
		int[][] notes = new int[6][5];
		for (int i = notes.length-1; i>=0; i--) {
			for (int j = 0 ; j < notes[0].length; j++) {
				if ((i==2)&&(j==4)) {
					notes[i][j] = 101; //dont want to include that
				}
				else {
					notes[i][j] = first;
					first += 1;
				}
			}
		}
		return notes;
		
	}
}