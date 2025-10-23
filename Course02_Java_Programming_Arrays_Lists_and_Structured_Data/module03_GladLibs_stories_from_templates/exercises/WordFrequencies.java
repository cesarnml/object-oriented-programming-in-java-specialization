package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module03_GladLibs_stories_from_templates.exercises;

import java.util.ArrayList;
import edu.duke.FileResource;

public class WordFrequencies {
  private ArrayList<String> myWords;
  private ArrayList<Integer> myFreqs;

  public WordFrequencies() {
    myWords = new ArrayList<String>();
    myFreqs = new ArrayList<Integer>();
  }

  public void findUnique() {
    myWords.clear();
    myFreqs.clear();

    FileResource resource = new FileResource();

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

  public static void tester() {
    WordFrequencies wf = new WordFrequencies();

    wf.findUnique();
    System.out.println("Number of unique words: " + wf.myWords.size());
    for (int i = 0; i < wf.myWords.size(); i++) {
      System.out.println(wf.myFreqs.get(i) + "\t" + wf.myWords.get(i));
    }
    int index = wf.findIndexOfMax();
    System.out.println(
        "The word that occurs most often and its count are: " + wf.myWords.get(index) + " " + wf.myFreqs.get(index));
  }

  public int findIndexOfMax() {
    int max = myFreqs.get(0);
    int maxIndex = 0;
    for (int k = 0; k < myFreqs.size(); k++) {
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
