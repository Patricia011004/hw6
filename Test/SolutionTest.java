

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class SolutionTest {
    private ArrayList<String> words;

    @Before
    public void init() throws IOException {
        words = TestUtils.dictionaryToList("miniDictionary.txt");
    }

    @Test
    public void isSolved() {
        Solution solution = new Solution(words);
        assertFalse(solution.isSolved());
        solution.addGuess('e');
        assertFalse(solution.isSolved());
        solution.addGuess('h');
        assertFalse(solution.isSolved());
        solution.addGuess('a');
        assertFalse(solution.isSolved());
        solution.addGuess('l');
        assertTrue(solution.isSolved());

    }


    @Test
    public void addGuess() {
        Solution solution = new Solution(words);
        assertTrue(solution.addGuess('e'));
        assertFalse(solution.addGuess('c'));
        assertFalse(solution.addGuess('e'));
        assertFalse(solution.addGuess('f'));
        assertTrue(solution.addGuess('a'));
        assertFalse(solution.addGuess('b'));
        assertFalse(solution.addGuess('a'));
        assertTrue(solution.addGuess('h'));
        assertTrue(solution.addGuess('l'));
        assertFalse(solution.addGuess('a'));

    }

    @Test
    public void getTarget() {
        Solution solution = new Solution(words);
        assertNull(solution.getTarget());
        solution.addGuess('g');
        assertEquals("----", solution.getTarget());
        solution.addGuess('e');
        assertEquals("-e--", solution.getTarget());
        solution.addGuess('c');
        assertEquals("-e--", solution.getTarget());
        solution.addGuess('b');
        assertEquals("be--", solution.getTarget());
        solution.addGuess('b');
        assertEquals("be--", solution.getTarget());
        solution.addGuess('l');
        assertEquals("bel-", solution.getTarget());
        solution.addGuess('t');
        assertEquals("belt", solution.getTarget());

    }
}