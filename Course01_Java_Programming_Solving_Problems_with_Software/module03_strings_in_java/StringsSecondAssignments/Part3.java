package Course01_Java_Programming_Solving_Problems_with_Software.module03_strings_in_java.StringsSecondAssignments;

public class Part3 {
  static int CODON_LENGTH = 3;

  public static int findStopCodon(String dna, int startIndex, String stopCodon) {
    int lengthOfDna = dna.length();
    String normalizedDna = dna.toLowerCase();
    String normalizedStopCodon = stopCodon.toLowerCase();
    int currentIndex = startIndex;

    while (true) {
      int stopIndex = normalizedDna.indexOf(normalizedStopCodon, currentIndex);

      if (stopIndex == -1) {
        return lengthOfDna;
      } else {
        int diff = stopIndex - startIndex;

        if (diff % CODON_LENGTH == 0) {
          return stopIndex;
        } else {
          currentIndex = stopIndex + 1;
        }
      }
    }
  }

  public static String findGene(String dna) {
    String startCodon = "ATG";
    String normalizedDna = dna.toLowerCase();
    String normalizedStartCodon = startCodon.toLowerCase();
    int normalizedDnaLength = normalizedDna.length();

    int startIndex = normalizedDna.indexOf(normalizedStartCodon);

    if (startIndex == -1) {
      return "";
    }
    // stopCodon: "TAA", "TAG", "TGA"
    int taaIndex = findStopCodon(normalizedDna, startIndex, "TAA");
    int tagIndex = findStopCodon(normalizedDna, startIndex, "TAG");
    int tgaIndex = findStopCodon(normalizedDna, startIndex, "TGA");

    int minStopIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));

    if (minStopIndex == normalizedDnaLength) {
      return "";
    } else {
      return dna.substring(startIndex, minStopIndex + CODON_LENGTH);
    }
  }

  public static void printAllGenes(String dna) {
    String remainingDna = dna;
    while (true) {
      String gene = findGene(remainingDna);
      if (gene.isEmpty()) {
        break;
      }
      System.out.println(gene);
      int startIndex = remainingDna.indexOf(gene);

      if (gene.length() < remainingDna.length()) {
        remainingDna = remainingDna.substring(startIndex + gene.length());
      } else {
        break;
      }
    }
  }

  public static int countGenes(String dna) {
    int count = 0;
    String remainingDna = dna;
    while (true) {
      String gene = findGene(remainingDna);
      if (gene.isEmpty()) {
        return count;
      }
      count += 1;

      int startIndex = remainingDna.indexOf(gene);
      if (gene.length() < remainingDna.length()) {
        remainingDna = remainingDna.substring(startIndex + gene.length());
      } else {
        return count;
      }
    }
  }

  public static void main(String[] args) {
    int count1 = countGenes("ATGTAAGATGCCCTAGT");
    System.out.println(count1);
  }
}
