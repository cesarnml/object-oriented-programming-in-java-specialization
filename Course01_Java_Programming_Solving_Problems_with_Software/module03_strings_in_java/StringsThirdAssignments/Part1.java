package Course01_Java_Programming_Solving_Problems_with_Software.module03_strings_in_java.StringsThirdAssignments;

import edu.duke.*;

public class Part1 {
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

  public static StorageResource getAllGenes(String dna) {
    StorageResource geneList = new StorageResource();

    String remainingDna = dna;
    while (true) {
      String gene = findGene(remainingDna);
      if (gene.isEmpty()) {
        return geneList;
      }
      geneList.add(gene);
      int startIndex = remainingDna.indexOf(gene);

      if (gene.length() < remainingDna.length()) {
        remainingDna = remainingDna.substring(startIndex + gene.length());
      }
    }
  }

  public static double cgRatio(String dna) {
    int countCG = 0;
    for (char c : dna.toCharArray()) {
      if (c == 'C' || c == 'c' || c == 'G' || c == 'g') {
        countCG += 1;
      }
    }
    return (double) countCG / dna.length();
  }

  public static int countCTG(String dna) {
    int currentIndex = 0;
    int count = 0;
    while (true) {
      currentIndex = dna.indexOf("CTG", currentIndex);

      if (currentIndex == -1) {
        return count;
      }
      count += 1;
      currentIndex += 3;
    }
  }

  public static void processGenes(StorageResource sr) {
    int countGenes9 = 0;
    int countCG3_5 = 0;
    int longestGene = 0;
    for (String s : sr.data()) {
      // print all the Strings in sr that are longer than 9 characters
      if (s.length() > 60) {
        countGenes9 += 1;
        System.out.println(s);
      }
      if (cgRatio(s) > 0.35) {
        // print the Strings in sr whose C-G-ratio is higher than 0.35
        System.out.println(s);
        countCG3_5 += 1;
      }
      if (s.length() > longestGene) {
        longestGene = s.length();
      }
    }
    // print the number of Strings in sr that are longer than 9 characters
    System.out.println("countGenes9");
    System.out.println(countGenes9);
    // print the number of strings in sr whose C-G-ratio is higher than 0.35
    System.out.println("countGenes3_5");
    System.out.println(countCG3_5);
    // print the length of the longest gene in sr\
    System.out.println("LONGEST");

    System.out.println(longestGene);
  }

  public static void testProcessGenes() {
    // Test 1: DNA with genes longer than 9 characters
    System.out.println("\nTest 1: DNA with genes longer than 9 characters");
    StorageResource sr1 = getAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    System.out.println("Genes found:");
    for (String gene : sr1.data()) {
      System.out.println(gene);
    }
    System.out.println("Processing genes:");
    processGenes(sr1);

    // Test 2: DNA with no genes longer than 9 characters
    System.out.println("\nTest 2: DNA with no genes longer than 9 characters");
    StorageResource sr2 = getAllGenes("ATGATCTAATATGGTGTAA");
    System.out.println("Genes found:");
    for (String gene : sr2.data()) {
      System.out.println(gene);
    }
    System.out.println("Processing genes:");
    processGenes(sr2);

  }

  public static void main(String[] args) {
    // testProcessGenes();
    FileResource fr = new FileResource(
        "Course01_Java_Programming_Solving_Problems_with_Software/module03_strings_in_java/dna/GRch38dnapart.fa");
    String dna = fr.asString();
    System.out.println("COUNTCTG " + countCTG(dna));
    System.out.println("DNA is length: " + dna.length());
    StorageResource genes = getAllGenes(dna);
    System.out.println("SIZE " + genes.size());
    processGenes(genes);

  }
}
