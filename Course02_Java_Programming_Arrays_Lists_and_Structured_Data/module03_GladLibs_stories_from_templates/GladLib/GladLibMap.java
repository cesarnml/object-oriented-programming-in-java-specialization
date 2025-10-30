package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module03_GladLibs_stories_from_templates.GladLib;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private ArrayList<String> usedList;
	private HashMap<String, ArrayList<String>> myMap;
	private HashSet<String> usedCategories;

	private Random myRandom;

	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String parentDir = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module03_GladLibs_stories_from_templates/GladLib/";
	private static String dataSourceDirectory = parentDir + "data";

	public GladLibMap() {
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		usedList = new ArrayList<String>();
		usedCategories = new HashSet<String>();

	}

	public GladLibMap(String source) {
		initializeFromSource(source);
		myRandom = new Random();
		usedList = new ArrayList<String>();
		usedCategories = new HashSet<String>();
	}

	private void initializeFromSource(String source) {
		myMap = new HashMap<String, ArrayList<String>>();

		String[] categories = { "adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit" };

		for (String cat : categories) {
			myMap.put(cat, readIt(source + "/" + cat + ".txt"));
		}
	}

	private String randomFrom(ArrayList<String> source) {
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}

	private String getSubstitute(String label) {
		if (label.equals("number")) {
			return "" + myRandom.nextInt(50) + 5;
		}

		ArrayList<String> substitutes = myMap.get(label);
		if (substitutes == null) {
			return "*UNKNOWN*";
		}
		usedCategories.add(label);
		return randomFrom(substitutes);
	}

	private String processWord(String w) {
		int first = w.indexOf("<");
		int last = w.indexOf(">", first);
		if (first == -1 || last == -1) {
			return w;
		}
		String prefix = w.substring(0, first);
		String suffix = w.substring(last + 1);
		String label = w.substring(first + 1, last);
		String sub = getSubstitute(label);

		// Bit of a hack to guard against infinite loops
		int attempt = 0;
		int maxAttempts = 100;
		while (usedList.contains(sub) && attempt < maxAttempts) {
			sub = getSubstitute(label);
			attempt++;
		}
		usedList.add(sub);
		return prefix + sub + suffix;
	}

	private void printOut(String s, int lineWidth) {
		int charsWritten = 0;
		for (String w : s.split("\\s+")) {
			if (charsWritten + w.length() > lineWidth) {
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w + " ");
			charsWritten += w.length() + 1;
		}
	}

	private String fromTemplate(String source) {
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for (String word : resource.words()) {
				story = story + processWord(word) + " ";
			}
		} else {
			FileResource resource = new FileResource(source);
			for (String word : resource.words()) {
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}

	private ArrayList<String> readIt(String source) {
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for (String line : resource.lines()) {
				list.add(line);
			}
		} else {
			FileResource resource = new FileResource(source);
			for (String line : resource.lines()) {
				list.add(line);
			}
		}
		return list;
	}

	public void makeStory() {
		usedList.clear();
		System.out.println("\n");
		String story = fromTemplate(parentDir + "data/madtemplate2.txt");
		printOut(story, 60);
	}

	public int totalWordsInMap() {
		int totalWords = 0;
		for (ArrayList<String> wordList : myMap.values()) {
			totalWords += wordList.size();
		}
		return totalWords;
	}

	public int totalWordsConsidered() {
		int total = 0;
		for (String category : usedCategories) {
			ArrayList<String> words = myMap.get(category);
			if (words != null) {
				total += words.size();
			}
		}
		return total;
	}

	public static void main(String[] args) {
		GladLibMap gl = new GladLibMap();
		gl.makeStory();
		System.out.println("\nTotal number of substitute words: " + gl.totalWordsInMap());
		System.out.println("Number of substitutes considered " + gl.totalWordsConsidered());
	}
}
