import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class SpellCheck {
   	// We could have also used TreeSet for the dictionary
	private HashSet<String> dictionary = new HashSet<String>();
	private TreeSet<String> miss_spelled_words = new TreeSet<String>();

	public SpellCheck() throws FileNotFoundException {
		// Add all of the words from "dictionary.txt" to the dictionary HashSet
		Scanner fileInput = new Scanner(new File("SpellDictionary.txt"));
		while (fileInput.hasNextLine()) {
			dictionary.add(fileInput.nextLine());
		}
		fileInput.close();

	}

	public void checkSpelling(String fileName) throws FileNotFoundException {
		System.out.println("======== Spell checking " + fileName + " =========");
		// Clear miss_spelled_words
		miss_spelled_words.clear();
		// Read in each line from "fileName"
		Scanner fileInput = new Scanner(new File(fileName));
		Scanner kbInput = new Scanner(System.in);
		// For each line, break the line into words using split method

	

		int lineNumber = 1;
		boolean linePrint;

		while (fileInput.hasNextLine()) {
			String[] words = fileInput.nextLine().split(" +|\\p{Punct}");
			linePrint = true;

			for (String word : words) {
				if (word.isEmpty()) {
					continue;
				}
				word = word.toLowerCase();
				if (!Character.isLetter(word.charAt(0))){
					continue;
				}
				if (dictionary.contains(word) || miss_spelled_words.contains(word)) {
					continue;
				}
				if (word.endsWith("s")) {
					word = word.substring(0, word.length()-1);
					if(dictionary.contains(word) || miss_spelled_words.contains(word)) {
						continue;
					}
				}
				if (linePrint) {
					System.out.println("line number: " + lineNumber);
					linePrint = false;
				}
				System.out.println(word + "  add to dictionary? y / n");
				if (kbInput.nextLine().charAt(0) == 'y') {
					dictionary.add(word);
				} else {
					miss_spelled_words.add(word);
				}

			}

			lineNumber +=1;
		}
		fileInput.close();
		kbInput.close();



	}

	public void dump_miss_spelled_words() {
		// Print out the miss-spelled words
		System.out.println("======Miss-spelled-words======");
			for (String word : miss_spelled_words) {
				System.out.println(word);
			}

	}

	public static void main(String[] args) {
		try {
			SpellCheck spellCheck = new SpellCheck();

			spellCheck.checkSpelling("SpellMexico.txt");
			spellCheck.dump_miss_spelled_words();
			spellCheck.checkSpelling("SpellDictionary.txt");
			spellCheck.dump_miss_spelled_words();

		} catch (
		FileNotFoundException e) {
			System.out.println(e);
		}
	}
} 

