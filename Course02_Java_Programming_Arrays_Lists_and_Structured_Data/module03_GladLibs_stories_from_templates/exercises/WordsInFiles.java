package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module03_GladLibs_stories_from_templates.exercises;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {
  private HashMap<String, ArrayList<String>> wordToFileMap;

  public WordsInFiles() {
    wordToFileMap = new HashMap<String, ArrayList<String>>();
  }

  public HashMap<String, ArrayList<String>> getWordToFileMap() {
    return wordToFileMap;
  }

  private void addWordsFromFile(File f) {
    if (!f.isFile())
      return;

    FileResource fr = new FileResource(f);
    for (String word : fr.words()) {
      String filename = f.getName();
      ArrayList<String> files = wordToFileMap.get(word);

      if (files == null) {
        files = new ArrayList<String>();
        wordToFileMap.put(word, files);
      }

      if (!files.contains(filename)) {
        files.add(filename);
      }
    }
  }

  private void buildWordFileMap() {
    wordToFileMap.clear();
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      addWordsFromFile(f);
    }
  }

  private int maxNumber() {
    int maxCount = 0;
    for (String key : wordToFileMap.keySet()) {
      int currentCount = wordToFileMap.get(key).size();
      if (currentCount > maxCount) {
        maxCount = currentCount;
      }
    }
    return maxCount;
  }

  public ArrayList<String> wordsInNumFiles(int number) {
    ArrayList<String> foundWords = new ArrayList<>();
    for (String key : wordToFileMap.keySet()) {
      if (wordToFileMap.get(key).size() == number) {
        foundWords.add(key);
      }
    }
    return foundWords;
  }

  public void printFilesIn(String word) {
    ArrayList<String> files = wordToFileMap.get(word);
    if (files == null) {
      System.out.println("word (" + word + ") not found");
    } else {
      System.out.println(word + " appears in:");
      for (String filename : files) {
        System.out.println("  " + filename);
      }
    }
  }

  private static void tester() {
    WordsInFiles wif = new WordsInFiles();
    System.out.println("wordToFileMap building...");
    wif.buildWordFileMap();

    int maxFiles = wif.maxNumber();
    System.out.println("\nMaximum number of files any word appears in: " + maxFiles);
    // Find words that appear in maximum number of files
    ArrayList<String> maxWords = wif.wordsInNumFiles(maxFiles);
    System.out.println("\nWords appearing in " + maxFiles + " files:");

    // Print filenames for each word
    for (String word : maxWords) {
      System.out.println("\n" + word + ":");
      wif.printFilesIn(word);
    }

    // Optional: Print complete map (if not too large)
    System.out.println("\n--- Complete Word Map ---");
    for (String key : wif.getWordToFileMap().keySet()) {
      System.out.println(key + " : " + wif.getWordToFileMap().get(key));
    }
  }

  public static void main(String[] args) {
    tester();
  }
}
