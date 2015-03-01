/**
* @author Monica and harry
*/

public class Tabulator {
	public int bar_num = 8;
	public int line = bar_num/4;
	public static void main(String[] args){
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
	public static void main(String args[]) {
		Tabulator tab = new Tabulator(notes.txt);
		int N = 8;

		tab.drawBoard(N);
		StdDrawPlus.show(100);
	}

	//top line is 80, 70 - y coord is minus 1
	public void plotNote(NotesAtATime n) {
		//i is based on bar
		for (int i = 0; i < n.notes().length; i++) {
			String name = "img/note-" + n.notes()[i].fret + ".png";
			StdDrawPlus.picture(i + .5, j + .5, name, 1, 1);
		}
			    //take y as top line and then subtract - 1 based on which string
		
	}

}

public class drawBoard{
	public int bar_num = 8;
	public int line = bar_num/4;
	public static void main(String[] args){
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
}