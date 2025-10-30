package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module03_GladLibs_stories_from_templates.exercises;

import java.util.HashMap;

import edu.duke.FileResource;

public class CodonCount {
  private static final int CODON_LENGTH = 3;
  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module03_GladLibs_stories_from_templates/";
  private HashMap<String, Integer> codonHashMap = new HashMap<String, Integer>();

  public CodonCount(int start, String dna) {
    buildCodonMap(start, dna);
  }

  private void buildCodonMap(int start, String dna) {
    codonHashMap.clear();
    for (int i = start; i + CODON_LENGTH <= dna.length(); i = i + CODON_LENGTH) {
      String currentCodon = dna.substring(i, i + CODON_LENGTH).toUpperCase();
      codonHashMap.put(currentCodon, codonHashMap.getOrDefault(currentCodon, 0) + 1);
    }

  }

  public int getUniqueCodonCount() {
    return codonHashMap.size();
  }

  public String getMostCommonCodon() {
    if (codonHashMap.isEmpty()) {
      return null;
    }

    int maxCount = 0;
    String maxCountKey = null;
    for (String key : codonHashMap.keySet()) {
      int currentCount = codonHashMap.get(key);
      if (currentCount > maxCount) {
        maxCount = currentCount;
        maxCountKey = key;
      }
    }
    return maxCountKey;
  }

  public int getCodonCount(String codon) {
    return codonHashMap.getOrDefault(codon, 0);
  }

  public void printCodonCounts(int start, int end) {
    System.out.println("---- Codons with counts >" + start + " and <" + end);
    for (String key : codonHashMap.keySet()) {
      int currentCount = codonHashMap.get(key);
      if (currentCount >= start && currentCount <= end) {
        System.out.println("Codon: " + key + " = " + currentCount);
      }
    }
  }

  private static void testCodonCount() {
    // int start = 1;
    // int end = 5;
    int start = 6;
    int end = 7;
    // String filename = "ProgrammingImprovingGladLibsData/smalldna.txt";
    // String filename = "PracticeGladLibsData/dnaMystery1";
    String filename = "QuizGladLibsData/dnaMystery2";

    String testFile = PARENT_DIR + filename;
    FileResource fr = new FileResource(testFile);
    String testDna = fr.asString().trim();

    for (int i = 0; i < CODON_LENGTH; i++) {
      CodonCount cc = new CodonCount(i, testDna);
      String mostCommonCodon = cc.getMostCommonCodon();
      System.out
          .println("Reading frame starting with " + i + " results in " + cc.getUniqueCodonCount() + " unique codons");
      System.out
          .println("and most common codon is " + mostCommonCodon + " with count " + cc.getCodonCount(mostCommonCodon));
      cc.printCodonCounts(start, end);
    }
  }

  public static void main(String[] args) {
    testCodonCount();
  }
}
