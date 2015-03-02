/**
* @author Monica and Harry
*/

public class Tabulator {
	public int bar_num = 8;
	public int line = bar_num/4;
	public NotesAtATime[] allnotes;

	public static void main(String[] args){
		allnotes = FileReader.getNotes("some file");
		drawBoard();


	}

	public static void drawBoard(){
		StdDraw.setCanvasSize(800,900);
		StdDraw.setXscale(0,30);
		StdDraw.setYscale(0,90);

		//take every four notesatatime and then plot into line
		//switch y (drawLine) every four notesatatime
		//plotNotes takes care of x
		int i = 0;
		while (allnotes[i].measureNum() < *multiple of 4*) {
			//give group of four measures, y of top line, and beginning x (always 20)
			NotesAtATime[] plotting = {allnotes[i], allnotes[i+1], allnotes[], allnotes[]};
			plotNotes( , 80, 20);
			i = i + 1;
		}
	
		drawLine(80);
		drawLine(70);
		drawLine(60);
		drawLine(50);
		drawLine(40);
		drawLine(30);
		drawLine(20);
		StdDraw.setPenRadius(0.006);
		drawBar(80);
		drawBar(70);
		drawBar(60);
		drawBar(50);
		drawBar(40);
		drawBar(30);
		drawBar(20);
	}

	public static void drawLine(int y0){
		StdDraw.line(0,y0,30,y0);
		StdDraw.line(0,y0-1,30,y0-1);
		StdDraw.line(0,y0-2,30,y0-2);
		StdDraw.line(0,y0-3,30,y0-3);
		StdDraw.line(0,y0-4,30,y0-4);
		StdDraw.line(0,y0-5,30,y0-5);

	}

	public static void drawBar(int y0){
		StdDraw.line(0.1,y0,0.1,y0-5);
		StdDraw.line(7.5,y0,7.5,y0-5);
		StdDraw.line(15,y0,15,y0-5);
		StdDraw.line(22.5,y0,22.5,y0-5);
		StdDraw.line(29.9,y0,29.9,y0-5);
	}

	//top line is 80, 70 - y coord is minus 1

	//plots all notes in one line
	//notesatatime is basically one note!
	public void plotNotes(NotesAtATime[] noteline, int x, int y) {
		for (int j = 0; j < noteline.length; j++) {
			NotesAtATime n = noteline[j];
			for (int i = 0; i < n.notes().length; i++) {
				String name = "img/note-" + n.notes()[i].fret + ".png";
				StdDrawPlus.picture(x + n.orderNum() + 1, y - n.notes()[i].string, name, 1, 1);
			}
		}
		//take y as top line and then subtract - 1 based on which string	
	}

}
