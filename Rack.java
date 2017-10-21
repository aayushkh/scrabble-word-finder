
// Name: Aayush Khanna	
// USC NetID: aayushkh	
// CS 455 PA4
// Spring 2017

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Rack Class
 * 
 * A Rack of Scrabble tiles which has functions to 1. Find all Anagrams of an
 * array of strings, 2. Display the array of strings in decreasing order of
 * Score and then ascending order of String, 3. Find all possible subsets of a
 * word using a recursive function. Note : This function does not defines any
 * variables. It only provides a bunch of functions which help to find all
 * possible words we can make from the Scrable dictionary.
 */

public class Rack {

	private final static int ZERO = 0;
	private final static int ONE = 1;

	/**
	 * Finds all the anagrams for a string by looking up the AnagramDicttionary
	 * using the getAnagramsOf function. Complexity for this is O(1) since it
	 * looks up a Map called dictionaryMap.
	 */
	public static ArrayList<String> allValidAnagrams(ArrayList<String> unique) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < unique.size(); i++) {
			ArrayList<String> temp = new ArrayList<String>();
			AnagramDictionary ad = new AnagramDictionary();
			System.out.println("Current subset : " + unique.get(i));
			temp = ad.getAnagramsOf(unique.get(i));
			System.out.println("Temps : " + temp);
			for (int j = 0; j < temp.size(); j++) {
				result.add(temp.get(j));
				System.out.println("Result : " + result);
			}
		}
		return result;
	}

	/**
	 * Displays the string in the required order in decreasing order of Score
	 * and then ascending order of String.
	 */

	public void Display(ArrayList<String> result) {
		Map<Integer, ArrayList<String>> resultMap = new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder());
		ArrayList<String> temp = new ArrayList<String>();
		ScoreTable st = new ScoreTable();
		for (int i = 0; i < result.size(); i++) {
			int score = st.computeScore(result.get(i));
			temp = resultMap.containsKey(score) ? resultMap.get(score) : new ArrayList<String>();
			temp.add(result.get(i));
			resultMap.put(score, temp);
		}
		for (Map.Entry<Integer, ArrayList<String>> entry : resultMap.entrySet()) {
			ArrayList<String> str = entry.getValue();
			Collections.sort(str); // Sorting words with the same score in
									// alphabetical order
			for (int i = 0; i < str.size(); i++) {
				System.out.println(entry.getKey() + ": " + str.get(i));
			}
		}
	}

	/**
	 * Wrapper method for the private allSubsets function. This function makes
	 * the unique, mult and k parameters for the helper function.
	 * 
	 * @param tempWord
	 *            stores the character as key and number of instances of the
	 *            character as value in the Map.
	 * @param mult
	 *            stores the number of instances of the character in an int
	 *            array.
	 * @param startPos
	 *            starting position set to 0 (beginning of the string).
	 */

	public ArrayList<String> allSubsets(String str) {
		String unique = "";
		Map<Character, Integer> tempWord = new TreeMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			char letter = str.charAt(i);
			int count = tempWord.containsKey(letter) ? tempWord.get(letter) : 0;
			tempWord.put(letter, count + ONE);
		}
		int[] mult = new int[tempWord.size()];
		int index = ZERO;
		for (Map.Entry<Character, Integer> mapEntry : tempWord.entrySet()) {
			unique = unique + mapEntry.getKey();
			mult[index] = mapEntry.getValue();
			index++;
		}
		int startPos = ZERO;
		return allSubsets(unique, mult, startPos);
	}

	/**
	 * Finds all subsets of the multiset starting at position k in unique and
	 * mult. unique and mult describe a multiset such that mult[i] is the
	 * multiplicity of the char unique.charAt(i). PRE: mult.length must be at
	 * least as big as unique.length()
	 * 
	 * @param unique
	 *            a string of unique letters
	 * @param mult
	 *            the multiplicity of each letter from unique.
	 * @param k
	 *            the smallest index of unique and mult to consider.
	 * @return all subsets of the indicated multiset
	 * @author Claire Bono
	 */
	private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
		ArrayList<String> allCombos = new ArrayList<>();

		if (k == unique.length()) { // multiset is empty
			allCombos.add("");
			return allCombos;
		}

		// get all subsets of the multiset without the first unique char
		ArrayList<String> restCombos = allSubsets(unique, mult, k + ONE);

		// prepend all possible numbers of the first char (i.e., the one at
		// position k)
		// to the front of each string in restCombos. Suppose that char is
		// 'a'...

		String firstPart = ""; // in outer loop firstPart takes on the values:
								// "", "a", "aa", ...
		for (int n = 0; n <= mult[k]; n++) {
			for (int i = 0; i < restCombos.size(); i++) { // for each of the
															// subsets
															// we found in the
															// recursive call
				// create and add a new string with n 'a's in front of that
				// subset
				allCombos.add(firstPart + restCombos.get(i));
			}
			firstPart += unique.charAt(k); // append another instance of 'a' to
											// the first part
		}
		return allCombos;
	}

}
