import java.util.ArrayList;
import java.util.HashMap;


public class WordsFamily {
    private HashMap<String, ArrayList<String>> familyMap;
    private ArrayList<String> wordsList;
    private String maxKey;
    private int maxLength;
    public WordsFamily(ArrayList<String> words) {
        familyMap = new HashMap<>();
        wordsList = words;
        maxKey = null;
        maxLength = -1;
    }

    public boolean rearrange(char c){
        String prevKey = "";
        if (!familyMap.isEmpty()){
            wordsList = familyMap.get(maxKey);
            maxLength = -1;
            prevKey = maxKey;
            maxKey = null;
            familyMap.clear();
        }
        boolean hasChange = false;
        for (String word: wordsList){
            char[] partialSolution;
            if (prevKey.equals("")){
                partialSolution = new char[word.length()];
                for (int i = 0; i < word.length(); i++) {
                    partialSolution[i] = '-';
                }
            }else {
                partialSolution = prevKey.toCharArray();
            }
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == c && partialSolution[i] == '-') {
                    partialSolution[i] = c;
                    hasChange = true;
                }
            }

            String key = String.valueOf(partialSolution);
            familyMap.computeIfAbsent(key, k -> new ArrayList<String>());
            ArrayList<String> list =  familyMap.get(key);
            list.add(word);
            if (list.size() > maxLength || (list.size() == maxLength && key.contains(String.valueOf(c)))){
                maxKey = key;
                maxLength = list.size();
            }
        }
        wordsList = familyMap.get(maxKey);
        return hasChange;
    }

    public String getMaxKey() {
        return maxKey;
    }

}
