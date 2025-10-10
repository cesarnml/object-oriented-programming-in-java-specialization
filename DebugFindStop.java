public class DebugFindStop {
  public static int findStopCodon(String dna, int startIndex, String stopCodon) {
    int lengthOfDna = dna.length();
    String normalizedDna = dna.toLowerCase();
    String normalizedStopCodon = stopCodon.toLowerCase();

    int stopIndex = normalizedDna.indexOf(normalizedStopCodon, startIndex);
    System.out.println("DNA: " + normalizedDna);
    System.out.println("Stop codon: " + normalizedStopCodon);
    System.out.println("Start index: " + startIndex);
    System.out.println("Stop index: " + stopIndex);

    if (stopIndex == -1) {
      return lengthOfDna;
    } else {
      int diff = stopIndex - startIndex;
      System.out.println("Diff: " + diff);
      System.out.println("Diff % 3: " + (diff % 3));

      if (diff % 3 == 0) {
        return stopIndex;
      }

      return lengthOfDna;
    }
  }

  public static void main(String[] args) {
    String dna5 = "atgaaatga";
    int start5 = dna5.indexOf("atg");
    int result5 = findStopCodon(dna5, start5, "TGA");
    System.out.println("Result: " + result5);
  }
}