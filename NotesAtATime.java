//package staffToTab;

public class NotesAtATime {
	private Note[] notes;
	private int measureNum;
	private int orderNum;

	public NotesAtATime(Note[] ns, int mN, int oN) {
		notes = ns;
		measureNum = mN;
		orderNum = oN;
	}

	public Note[] notes() {
		return notes;
	}

	public int measureNum() {
		return measureNum;
	}

	public int orderNum() {
		return orderNum;
	}

}