
// Name: Aayush Khanna	
// USC NetID: aayushkh	
// CS 455 PA4
// Spring 2017

import java.util.*;

/**
 * ScoreTable class
 * 
 * Immutable class for storing the Score of each character in a Map. The Scores
 * are Mapped when the ScoreTable class is created via the ScoreTable
 * constructor. The class also computes the score of a given string.
 */

public class ScoreTable {

	private final Map<Character, Integer> alphabet;

	/**
	 * @param alphabet
	 *            A Map that stores the scores of each letter. It is initialized
	 *            via the ScoreTable Constructor.
	 * 
	 */
	public ScoreTable() {
		alphabet = new TreeMap<Character, Integer>();
		alphabet.put('a', 1);
		alphabet.put('b', 3);
		alphabet.put('c', 3);
		alphabet.put('d', 2);
		alphabet.put('e', 1);
		alphabet.put('f', 4);
		alphabet.put('g', 2);
		alphabet.put('h', 4);
		alphabet.put('i', 1);
		alphabet.put('j', 8);
		alphabet.put('k', 5);
		alphabet.put('l', 1);
		alphabet.put('m', 3);
		alphabet.put('n', 1);
		alphabet.put('o', 1);
		alphabet.put('p', 3);
		alphabet.put('q', 10);
		alphabet.put('r', 1);
		alphabet.put('s', 1);
		alphabet.put('t', 1);
		alphabet.put('u', 1);
		alphabet.put('v', 4);
		alphabet.put('w', 8);
		alphabet.put('x', 3);
		alphabet.put('y', 4);
		alphabet.put('z', 10);
	}

	/**
	 * Computes the Score of the String given using the scores stored in the Map
	 * alphabet.
	 */
	public int computeScore(String word) {
		int sum = 0;
		for (int i = 0; i < word.length(); i++) {
			sum = sum + alphabet.get(word.charAt(i));
		}
		return sum;

	}
}