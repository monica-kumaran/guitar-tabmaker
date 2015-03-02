/**
* @author Monica and Harry
*/

import java.util.*;

public class Tabulator {
	public int bar_num = 8;
	public int line = bar_num/4;
	public NotesAtATime[] allnotes;

	public static void main(String[] args){
		Tabulator t = new Tabulator();
		t.allnotes = FileReader.getNotes("notes.txt");
		t.drawBoard();


	}

	public void drawBoard(){
		StdDraw.setCanvasSize(800,900);
		StdDraw.setXscale(0,30);
		StdDraw.setYscale(0,90);
	
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

		//take every four measures and then plot into line
		//switch y (drawLine) every four notesatatime
		//plotNotes takes care of x
		
		int y = 80;
		double x;
		ArrayList<NotesAtATime> plotting = new ArrayList<NotesAtATime>();
		System.out.println("All Notes length: " + allnotes.length);
		for (int i = 0; i < allnotes.length; i++) {
			if (allnotes[i].measureNum() == 0 || allnotes[i].measureNum()%4 != 0) {
				plotting.add(allnotes[i]);
				System.out.println("inside if statement, measureNum = " + allnotes[i].measureNum());
			}
			else {
				x = (7.5 * allnotes[i].measureNum());
				plotNotes(plotting, y);
				y = y - 10;
				plotting.clear();
				plotting.add(allnotes[i]);
				System.out.println("Look, ma, I'm plotting!");
			}
		}
		if (plotting.size() != 0) {
			System.out.print("y is "+ y);
			plotNotes(plotting, y);
			plotting.clear();
			System.out.println("Look, ma, I'm still plotting!");
		}
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

	//x is measure number.
	//y is 80 or 70 or cetera depending on line number. 
	public void plotNotes(ArrayList <NotesAtATime> noteline, int y) {
/*		StdDraw.setCanvasSize(800,900);
		StdDraw.setXscale(0,30);
		StdDraw.setYscale(0,90);
*/
		System.out.println("Haiiiii");
//		StdDraw.setPenColor(StdDraw.WHITE);
		for (int j = 0; j < noteline.size(); j++) {
			NotesAtATime n = noteline.get(j);
			for (int i = 0; i < n.notes().length; i++) {
				String name = "img/note-" + n.notes()[i].fret + ".png";

				double x = (7.5 * n.measureNum());

				StdDraw.picture(x + n.orderNum() + 1, y - n.notes()[i].string, name, 1, 1);

				System.out.print("y is "+ y);
				System.out.print(x + n.orderNum() + 1 + ", ");
				System.out.println(y - n.notes()[i].string);
			}
//			System.out.println("Oho " + j);
		}
		//take y as top line and then subtract - 1 based on which string	
	}

}
