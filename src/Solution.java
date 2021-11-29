import java.util.ArrayList;

public class Solution {

	private final WordsFamily wordsFamily;
	public Solution(ArrayList<String> targetWords) {
		wordsFamily = new WordsFamily(targetWords);
	}

	public boolean isSolved() {
		if (wordsFamily.getMaxKey() == null){
			return false;
		}
		for (char c : wordsFamily.getMaxKey().toCharArray()) {
			if (c == '-'){
				return false;
			}
		}
		return true;
	}

	public void printProgress() {
		for (char c : wordsFamily.getMaxKey().toCharArray()) {
			System.out.print(c + " ");
		}
		System.out.println();
	}


	public boolean addGuess(char guess) {
		return wordsFamily.rearrange(guess);
	}

	public String getTarget(){
		return wordsFamily.getMaxKey();
	}

}
