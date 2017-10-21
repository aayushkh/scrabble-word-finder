
// Name: Aayush Khanna	
// USC NetID: aayushkh	
// CS 455 PA4
// Spring 2017

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * WordFinder class
 * 
 * How to call it from the command line:
 * 
 * java WordFinder [fileName], this calls the "fileName" dictionary -or- java
 * WordFinder , this calls the sowpods dictionary
 * 
 * If [fileName] does not exist then we return a FileNotFoundException.
 */

public class WordFinder {

	public static void main(String[] args) {
		String fileName = "";
		try {
			if (args.length < 1) {
				fileName = "sowpods.txt";
				execute(fileName);
			} else {
				fileName = args[0];
				execute(fileName);
			}
		} catch (FileNotFoundException exception) {
			System.out.println("File not found. Name of the file : " + fileName);
		} catch (IOException exception) {
			System.out.println("IO Exception");
			exception.printStackTrace();
		}
	}

	/**
	 * Reads in the dictionary words from the file whose name is given and
	 * creates a Map from it.
	 * 
	 * @param fileName
	 *            the name of a file to read from (file format shown in class
	 *            comments, above) Displays a list of possible words we can make
	 *            from the rack. Rack is taken in as an input using a scanner.
	 * 
	 * @throws FileNotFoundException
	 *             if there's no such file (subclass of IOException)
	 * @throws IOException
	 *             (hook given in case you want to do more error-checking)
	 */

	private static void execute(String fileName) throws IOException {
		AnagramDictionary ad = new AnagramDictionary(fileName);
		Rack rack = new Rack();
		Scanner in = new Scanner(System.in);
		String readLine = "";
		System.out.println("Type . to quit.");
		boolean checker = true;
		// loop to take in multiple rack lines
		while (checker) {
			System.out.print("Rack? ");
			readLine = in.nextLine();
			readLine = readLine.toLowerCase();
			String extractedLine = readLine.replaceAll("[^a-zA-Z]+", "");
			if (!(readLine.equals("."))) {
				ArrayList<String> subsets = rack.allSubsets(extractedLine);
				ArrayList<String> anagrams = new ArrayList<String>();
				for (int i = 0; i < subsets.size(); i++) {
					ArrayList<String> temp = new ArrayList<String>();
					temp = ad.getAnagramsOf(subsets.get(i));
					for (int j = 0; j < temp.size(); j++) {
						anagrams.add(temp.get(j));
					}
				}
				System.out.println("We can make " + anagrams.size() + " words from " + "\"" + readLine + "\"");
				if (anagrams.size() != 0) {
					System.out.println("All of the words with their scores (sorted by score): ");
				}
				rack.Display(anagrams);
			} else {
				checker = false;
			}
		}
		in.close();
	}

}
