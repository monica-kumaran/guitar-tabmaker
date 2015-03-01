//import staffToTab.Note;
//import staffToTab.FileReader;

//import staffToTab.NotesAtATime;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class FileReaderTest {

	@Test
	public void basicTest() {
		NotesAtATime[] test = FileReader.getNotes("notes.txt");

		NotesAtATime a_0_0 = test[0];
		assertEquals(0, a_0_0.notes()[0].number());
		assertEquals("C", a_0_0.notes()[0].toString());

		NotesAtATime b_2_0 = test[8];
		assertEquals(2, b_2_0.measureNum());

		assertEquals(16, test.length);
	}


    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(FileReaderTest.class);
    }

}