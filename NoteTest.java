//package staffToTab;
/**
* @author Paige
*/
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class NoteTest {

	@Test
	public void basicTest() {

		Note middleC = new Note(0);
		assertEquals(0, middleC.number());
		assertEquals(0, middleC.ID_NUMBER);
		assertEquals("C", middleC.toString());
	}

	@Test
	public void moreInvolvedTest() {

		Note b_flat = new Note(-2);
		assertEquals(-2, b_flat.number());
		assertEquals("A#", b_flat.toString());
		
		b_flat.sharps = false;
		assertEquals("B♭", b_flat.toString());
	}


	@Test
    public void testNoteArray() {
    	Note a = new Note(-8);
		assertEquals(-8, Note.noteArray[5][0]);
		assertEquals(-7, Note.noteArray[5][1]);
		assertEquals(-3, Note.noteArray[4][0]);
		assertEquals(16, Note.noteArray[0][0]);      
    }

    @Test
    public void testNote2() {
		Note e = new Note(-8);
		assertEquals(5, e.string);
		assertEquals(0, e.fret);
		
		Note c = new Note(0);
		assertEquals(4, c.string);
		assertEquals(3, c.fret);
		
		Note geegee = new Note(20);
		assertEquals(0, geegee.string);
		assertEquals(4, geegee.fret);

	   
    }

    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(NoteTest.class);
    }

}