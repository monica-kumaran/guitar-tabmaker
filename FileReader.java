//package staffToTab;
/*import staffToTab.Note;
import staffToTab.NotesAtATime;
*/
import java.util.ArrayList;

//reads text files and converts them into an array of NotesAtATime objects.
public class FileReader {

//	private NotesAtATime[] measure_pieces;

	public static NotesAtATime[] getNotes(String filename) {
		In in = new In(filename);

/*		String entirefile = in.readAll();
		Integer.parseInt(entirefile.replaceAll("[\\D]", " "));
*/
//		int[] allTheInts = in.readAllInts();
		String[] allTheInts = in.readAllStrings();

//		return null;

		ArrayList<NotesAtATime> verticals = new ArrayList<NotesAtATime>();

		ArrayList<Note> notes = new ArrayList<Note>();
		int currentPiece_oN = 0;
		int currentPiece_mN = 0;

		for (int i = 0; i < allTheInts.length; i = i + 3) {

			int i_noteNumber = Integer.parseInt(allTheInts[i].substring(1));
			int i_mN = Integer.parseInt(allTheInts[i + 1]);
			int i_oN = Integer.parseInt(allTheInts[i + 2].substring(0, allTheInts[i+2].length() - 1));

			if (currentPiece_mN == i_mN && currentPiece_oN == i_oN) {
				notes.add(new Note(i_noteNumber));
			}
			else {
				verticals.add(new NotesAtATime(notes.toArray(new Note[notes.size()]),
							 					currentPiece_mN, currentPiece_oN));
	
				notes.clear();
				currentPiece_mN = i_mN;
				currentPiece_oN = i_oN;
				notes.add(new Note(i_noteNumber));
			}
		}
		
		verticals.add(new NotesAtATime(notes.toArray(new Note[notes.size()]),
					 					currentPiece_mN, currentPiece_oN));


//		measure_pieces = verticals.toArray();
		return verticals.toArray(new NotesAtATime[verticals.size()]);
	}
}


//Time to thinkkkkk... #, 0, 0 //0
					// #, 0, 0 //0
					// #, 0, 1 //1
					// #, 1, 0 //2
					// #, 2, 0 //3
			//When the oN changes, the NotesAtATime can be made.
			//Can't tell how long measure_pieces will be until the end... 


/*		while in.hasNextLine() {
			String noteLine = in.readLine();
			int note_number = noteLine[1]
		}
*/