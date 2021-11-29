
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;


public class WordsFamilyTest {

    private ArrayList<String> words;
    @Before
    public void init() throws IOException {
        words = TestUtils.dictionaryToList("miniDictionary.txt");
    }

    @org.junit.Test
    public void rearrange() {
        WordsFamily family = new WordsFamily(words);
        assertFalse(family.rearrange('g'));
        assertFalse(family.rearrange('m'));
        assertTrue(family.rearrange('e'));
        assertFalse(family.rearrange('e'));
        assertTrue(family.rearrange('b'));
        assertTrue(family.rearrange('l'));
        assertTrue(family.rearrange('t'));
        assertFalse(family.rearrange('b'));
    }

    @org.junit.Test
    public void getMaxKey() {
        WordsFamily family = new WordsFamily(words);
        assertNull(family.getMaxKey());
        family.rearrange('g');
        assertEquals("----", family.getMaxKey());
        family.rearrange('e');
        assertEquals("-e--", family.getMaxKey());
        family.rearrange('e');
        assertEquals("-e--", family.getMaxKey());
        family.rearrange('l');
        assertEquals("-el-", family.getMaxKey());
        family.rearrange('b');
        assertEquals("bel-", family.getMaxKey());
        family.rearrange('t');
        assertEquals("belt", family.getMaxKey());
    }
}