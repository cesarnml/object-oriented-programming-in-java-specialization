package Course01_Java_Programming_Solving_Problems_with_Software.module03_strings_in_java.StringsFirstAssignments;

public class Part3 {
  public boolean twoOccurrences(String stringA, String stringB) {
    // Overlapping matches do not count as separate occurrences; after each match
    // the search resumes where the previous match ends.
    int occurrenceCount = 0;
    int searchStartIndex = 0;

    while (true) {
      int matchIndex = stringB.indexOf(stringA, searchStartIndex);
      if (matchIndex == -1) {
        return false;
      }

      occurrenceCount++;
      if (occurrenceCount >= 2) {
        return true;
      }

      searchStartIndex = matchIndex + stringA.length();
    }
  }

  public String lastPart(String stringA, String stringB) {
    int indexOfFirstOccurrence = stringB.indexOf(stringA);

    if (indexOfFirstOccurrence == -1) {
      return stringB;
    }

    return stringB.substring(indexOfFirstOccurrence + stringA.length());
  }

  public void testing() {
    String stringA1 = "AA";
    String stringB1 = "CGTACGT";
    boolean expected1 = false; // no occurrences

    String stringA2 = "AT";
    String stringB2 = "ATCG";
    boolean expected2 = false; // exactly one occurrence

    String stringAOverlap = "ana";
    String stringBOverlap = "banana";
    boolean expectedOverlap = false; // overlapping matches are ignored

    String stringA3 = "GAA";
    String stringB3 = "GAAGAAT";
    boolean expected3 = true; // exactly two occurrences

    String stringA4 = "CAT";
    String stringB4 = "CATCATCAT";
    boolean expected4 = true; // three occurrences still count as two or more

    System.out.println("twoOccurrences(\"" + stringA1 + "\", \"" + stringB1 + "\") => "
        + twoOccurrences(stringA1, stringB1) + " expected: " + expected1);
    System.out.println("twoOccurrences(\"" + stringA2 + "\", \"" + stringB2 + "\") => "
        + twoOccurrences(stringA2, stringB2) + " expected: " + expected2);
    System.out.println("twoOccurrences(\"" + stringA3 + "\", \"" + stringB3 + "\") => "
        + twoOccurrences(stringA3, stringB3) + " expected: " + expected3);
    System.out.println("twoOccurrences(\"" + stringA4 + "\", \"" + stringB4 + "\") => "
        + twoOccurrences(stringA4, stringB4) + " expected: " + expected4);
    System.out.println("twoOccurrences(\"" + stringAOverlap + "\", \"" + stringBOverlap + "\") => "
        + twoOccurrences(stringAOverlap, stringBOverlap) + " expected: " + expectedOverlap);

    String stringA5 = "an";
    String stringB5 = "banana";
    String expectedLastPart1 = "ana"; // substring following first occurrence

    String stringA6 = "zoo";
    String stringB6 = "forest";
    String expectedLastPart2 = "forest"; // stringA not present

    String stringA7 = "ta";
    String stringB7 = "beta";
    String expectedLastPart3 = ""; // occurs at the end

    System.out.println("lastPart(\"" + stringA5 + "\", \"" + stringB5 + "\") => \"" + lastPart(stringA5, stringB5)
        + "\" expected: \"" + expectedLastPart1 + "\"");
    System.out.println("lastPart(\"" + stringA6 + "\", \"" + stringB6 + "\") => \"" + lastPart(stringA6, stringB6)
        + "\" expected: \"" + expectedLastPart2 + "\"");
    System.out.println("lastPart(\"" + stringA7 + "\", \"" + stringB7 + "\") => \"" + lastPart(stringA7, stringB7)
        + "\" expected: \"" + expectedLastPart3 + "\"");
  }

  public static void main(String[] args) {
    Part3 part3 = new Part3();
    part3.testing();
  }
}
