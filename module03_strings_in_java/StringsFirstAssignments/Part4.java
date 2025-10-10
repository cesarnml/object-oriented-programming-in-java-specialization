package module03_strings_in_java.StringsFirstAssignments;

import edu.duke.URLResource;

public class Part4 {

  public void getYouTubeLinks() {
    String url = "https://www.dukelearntoprogram.com/course2/data/manylinks.html";
    URLResource ur = new URLResource(url);

    for (String word : ur.words()) {
      String target = "youtube.com";
      String normalizedWord = word.toLowerCase();
      int indexOfTarget = normalizedWord.indexOf(target);
      int lengthOfTarget = target.length();
      if (indexOfTarget != -1) {
        int indexOfLastQuote = normalizedWord.indexOf("\"", indexOfTarget + lengthOfTarget);
        int indexOfFirstQuote = normalizedWord.lastIndexOf("\"", indexOfTarget - 1);
        if (indexOfFirstQuote != -1 && indexOfLastQuote != -1) {
          System.out.println(word.substring(indexOfFirstQuote + 1, indexOfLastQuote));
        }
      }
    }
  }

  public static void main(String[] args) {
    Part4 p4 = new Part4();
    p4.getYouTubeLinks();
  }
}
