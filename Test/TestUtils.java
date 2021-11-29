import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestUtils {
    public static ArrayList<String> dictionaryToList(String filename) throws IOException {
        FileInputStream fs = new FileInputStream(filename);
        Scanner scnr = new Scanner(fs);
        ArrayList<String> wordList = new ArrayList<>();
        while (scnr.hasNext()) {
            wordList.add(scnr.next());
        }
        return wordList;
    }
}
