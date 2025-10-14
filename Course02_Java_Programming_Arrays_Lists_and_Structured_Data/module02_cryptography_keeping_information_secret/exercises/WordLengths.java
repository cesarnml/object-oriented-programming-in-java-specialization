package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module02_cryptography_keeping_information_secret.exercises;

import edu.duke.FileResource;

public class WordLengths {
  private static final int MAX_LETTER_COUNT = 30;

  public static void countWordLengths(FileResource resource, int[] counts) {
    for (String word : resource.words()) {
      // Guard against counting word of length 0
      if (word.isEmpty()) {
        continue;
      }

      int nonLetterCount = 0;

      // Check if first character in word should be omitted in length calculation
      if (!Character.isLetter(word.charAt(0))) {
        nonLetterCount += 1;
      }

      // Check if last character in word should be omitted in length calculation (for
      // words of length > 1)
      if (!Character.isLetter(word.charAt(word.length() - 1)) && word.length() > 1) {
        nonLetterCount += 1;
      }
      int effectiveLength = word.length() - nonLetterCount;

      // Guard against madness
      if (effectiveLength < 1)
        continue;

      // Words of length equal or greater than MAX_LETTER_COUNT are lumped together
      if (effectiveLength >= MAX_LETTER_COUNT) {
        counts[MAX_LETTER_COUNT] += 1;
      } else {
        counts[effectiveLength] += 1;
      }
    }
  }

  public static void testCountWordLengths() {
    FileResource fr = new FileResource();
    int[] counts = new int[MAX_LETTER_COUNT + 1];
    countWordLengths(fr, counts);
    for (int i = 1; i < counts.length; i++) {
      System.out.println("Words of length\t" + i + "\tappeared\t" + counts[i] + "\ttimes.");
    }
    int mostCommonWordLength = indexOfMax(counts);
    System.out.println("The most common word length was: " + mostCommonWordLength + " (" + counts[mostCommonWordLength]
        + " occurrences)");
  }

  public static int indexOfMax(int[] values) {
    int maxIndex = -1;
    int maxCount = 0;
    for (int i = 1; i < values.length; i++) {
      if (values[i] > maxCount) {
        maxCount = values[i];
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  public static void main(String[] args) {
    testCountWordLengths();
  }
}
