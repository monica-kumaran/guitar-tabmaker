/**
* @author Monica and Harry
*/

import java.util.*;

public class Tabulator {
	public int bar_num = 8;
	public int line = bar_num/4;
	public static NotesAtATime[] allnotes;

	public static void main(String[] args){
		allnotes = FileReader.getNotes("notes.txt");
		drawBoard();


	}

	public static void drawBoard(){
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
		
		int i = 0;
		int y = 80;
		ArrayList<NotesAtATime> plotting = new ArrayList<NotesAtATime>();
		
		while (i < allnotes.length) {
			//groups it per measure
			//adds notes of next four measures

			//adds all notes w/ measure 0 or measure which is not mult. of 4
			while (allnotes[i].measureNum() == 0 || allnotes[i].measureNum()%4 != 0) {
				plotting.add(allnotes[i]);
				i++;
			}
			
			//plots notes on line, moves to next line, then increments to next note
			plotNotes(plotting, y, 20);
			y = y - 10;
			plotting.clear();
			i++;
			
			//adds the next measure of notes, b/c otherwise not added by first loop
			while (allnotes[i].measureNum()%4 == 0) {
				plotting.add(allnotes[i]);
				i++;
			}
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
	public static void plotNotes(ArrayList <NotesAtATime> noteline, int x, int y) {
		for (int j = 0; j < noteline.size(); j++) {
			NotesAtATime n = noteline.get(j);
			for (int i = 0; i < n.notes().length; i++) {
				String name = "img/note-" + n.notes()[i].fret + ".png";
				StdDraw.picture(x + n.orderNum() + 1, y - n.notes()[i].string, name, 1, 1);
			}
		}
		//take y as top line and then subtract - 1 based on which string	
	}

}
