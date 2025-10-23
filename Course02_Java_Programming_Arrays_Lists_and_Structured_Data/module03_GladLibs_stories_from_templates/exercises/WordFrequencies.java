package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module03_GladLibs_stories_from_templates.exercises;

import java.util.ArrayList;
import edu.duke.FileResource;

public class WordFrequencies {
  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module03_GladLibs_stories_from_templates/";
  private ArrayList<String> myWords;
  private ArrayList<Integer> myFreqs;

  public WordFrequencies() {
    myWords = new ArrayList<String>();
    myFreqs = new ArrayList<Integer>();
  }

  public void findUnique() {
    myWords.clear();
    myFreqs.clear();

    // String filename = PARENT_DIR +
    // "ProgrammingRandomStoryData/testwordfreqs.txt";
    String filename = PARENT_DIR + "PracticeGladLibsData/likeit.txt";

    FileResource resource = new FileResource(filename);

    for (String s : resource.words()) {
      s = s.toLowerCase();
      int index = this.myWords.indexOf(s);
      if (index == -1) {
        this.myWords.add(s);
        this.myFreqs.add(1);
      } else {
        int freq = this.myFreqs.get(index);
        this.myFreqs.set(index, freq + 1);
      }
    }
  }

  // Add getter methods for proper encapsulation
  public int getUniqueCount() {
    return myWords.size();
  }

  public String getWord(int index) {
    return myWords.get(index);
  }

  public int getFrequency(int index) {
    return myFreqs.get(index);
  }

  public static void tester() {
    WordFrequencies wf = new WordFrequencies();

    wf.findUnique();
    System.out.println("Number of unique words: " + wf.getUniqueCount());
    // for (int i = 0; i < wf.getUniqueCount(); i++) {
    // System.out.println(wf.getFrequency(i) + "\t" + wf.getWord(i));
    // }
    int index = wf.findIndexOfMax();
    if (index != -1) {
      System.out.println(
          "The word that occurs most often and its count are: " + wf.getWord(index) + "\t" + wf.getFrequency(index));
    }
  }

  public int findIndexOfMax() {
    if (myFreqs.isEmpty()) {
      return -1; // Handle empty case
    }
    int max = myFreqs.get(0);
    int maxIndex = 0;
    for (int k = 1; k < myFreqs.size(); k++) {
      if (myFreqs.get(k) > max) {
        max = myFreqs.get(k);
        maxIndex = k;
      }
    }
    return maxIndex;
  }

  public static void main(String[] args) {
    tester();
  }
}
