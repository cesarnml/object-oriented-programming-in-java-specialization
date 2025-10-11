package Course01_Java_Programming_Solving_Problems_with_Software.module03_strings_in_java.StringsFirstAssignments;

public class Part2 {
  public String findSimpleGene(String dna, String startCodon, String stopCodon) {

    int codonLength = 3;

    String normalizedDna = dna.toUpperCase();
    String normalizedStartCodon = startCodon.toUpperCase();
    String normalizedStopCodon = stopCodon.toUpperCase();

    int startIndex = normalizedDna.indexOf(normalizedStartCodon);

    if (startIndex == -1) {
      return "";
    }

    int stopIndex = normalizedDna.indexOf(normalizedStopCodon, startIndex + 3);
    if (stopIndex == -1) {
      return "";
    }

    String potentialGene = dna.substring(startIndex, stopIndex + 3);

    if (potentialGene.length() % codonLength == 0) {
      return potentialGene;
    } else {
      return "";
    }
  }

  public void testSimpleGene() {
    String startCodon = "ATG";
    String stopCodon = "TAA";
    // DNA with no ATG start codon
    String dna1 = "CCCTTTTAA";
    String expected1 = "";

    // DNA with a start codon but no TAA stop codon
    String dna2 = "ATGCCCCGGG";
    String expected2 = "";

    // DNA with neither ATG nor TAA codons
    String dna3 = "CCCGGGCCC";
    String expected3 = "";

    // DNA with a valid gene (ATG...TAA and length multiple of 3)
    String dna4 = "CCCATGAAATAA";
    String expected4 = "ATGAAATAA";

    // DNA with ATG and TAA but length between them is not a multiple of 3
    String dna5 = "GGATGAATAA";
    String expected5 = "";

    // DNA entirely in lowercase containing a valid gene
    String dna6 = "cccatgaaataa";
    String expected6 = "atgaaataa";

    System.out.println("DNA is: " + dna1);
    System.out.println("GENE result: " + findSimpleGene(dna1, startCodon, stopCodon) + " expected: " + expected1);
    System.out.println("DNA is: " + dna2);
    System.out.println("GENE result: " + findSimpleGene(dna2, startCodon, stopCodon) + " expected: " + expected2);
    System.out.println("DNA is: " + dna3);
    System.out.println("GENE result: " + findSimpleGene(dna3, startCodon, stopCodon) + " expected: " + expected3);
    System.out.println("DNA is: " + dna4);
    System.out.println("GENE result: " + findSimpleGene(dna4, startCodon, stopCodon) + " expected: " + expected4);
    System.out.println("DNA is: " + dna5);
    System.out.println("GENE result: " + findSimpleGene(dna5, startCodon, stopCodon) + " expected: " + expected5);
    System.out.println("DNA is: " + dna6);
    System.out.println("GENE result: " + findSimpleGene(dna6, startCodon, stopCodon) + " expected: " + expected6);
  }

  public static void main(String[] args) {
    Part2 p2 = new Part2();
    p2.testSimpleGene();
  }
}
