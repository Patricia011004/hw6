import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class EvilHangman {

	private ArrayList<String> wordList;
	private HashSet<Character> previousGuesses;
	private TreeSet<Character> incorrectGuesses; // behaves like a hash set, but orders the entries!
	private Solution solution;
	private Scanner inputScanner;


	public EvilHangman() {
		this("engDictionary.txt");
	}

	public EvilHangman(String filename) {
		try {
			wordList = dictionaryToList(filename);
		} catch (IOException e) {
			System.out.printf(
					"Couldn't read from the file %s. Verify that you have it in the right place and try running again.",
					filename);
			e.printStackTrace();
			System.exit(0); // stop the program--no point in trying if you don't have a dictionary
		}

		previousGuesses = new HashSet<>();
		incorrectGuesses = new TreeSet<>();
		inputScanner = new Scanner(System.in);
		solution = new Solution(wordList);
	}

	public void start() {
		while (true){
			System.out.print("Please enter a letter to start: ");
			String input = inputScanner.next();
			if (input.length() != 1) {
				System.out.println("Please enter a single character.");
			} else {
				recordGuess(input.charAt(0));
				break;
			}
		}

		while (!solution.isSolved()) {
			char guess = promptForGuess();
			recordGuess(guess);
		}
		printVictory();
	}


	private char promptForGuess() {
		while (true) {
			System.out.println("Guess a letter.\n");
			solution.printProgress();
			System.out.println("Incorrect guesses:\n" + incorrectGuesses.toString());
			String input = inputScanner.next();
			if (input.length() != 1) {
				System.out.println("Please enter a single character.");
			} else if (previousGuesses.contains(input.charAt(0))) {
				System.out.println("You've already guessed that.");
			} else {

				return input.charAt(0);
			}
		}
	}

	private void recordGuess(char guess) {
		previousGuesses.add(guess);
		boolean isCorrect = solution.addGuess(guess);
		if (!targetHasLetter()){
			System.out.printf("There is no '%c' in the target word.\n", guess);
			incorrectGuesses.add(guess);
		}
		if (!isCorrect) {
			incorrectGuesses.add(guess);
		}
	}

	private void printVictory() {
		System.out.printf("Congrats! The word was %s%n", solution.getTarget());
	}

	private boolean targetHasLetter(){
		for (char c: solution.getTarget().toCharArray()){
			if (c != '-'){
				return true;
			}
		}
		return false;
	}

	private static ArrayList<String> dictionaryToList(String filename) throws IOException {
		FileInputStream fs = new FileInputStream(filename);
		Scanner scnr = new Scanner(fs);
		ArrayList<String> wordList = new ArrayList<>();
		while (scnr.hasNext()) {
			wordList.add(scnr.next());
		}
		return wordList;
	}

}
