package Course01_Java_Programming_Solving_Problems_with_Software.module03_strings_in_java.StringsFirstAssignments;

public class Part1 {
  public String findSimpleGene(String dna) {
    String startCodon = "ATG";
    String stopCodon = "TAA";

    int codonLength = 3;

    int startIndex = dna.indexOf(startCodon);

    if (startIndex == -1) {
      return "";
    }

    int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
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

    // Finding a Gene in DNA
    String dna6 = "AAATGCCCTAACTAGATTAAGAAACC";

    System.out.println("DNA is: " + dna1);
    System.out.println("dna1 result: " + findSimpleGene(dna1) + " expected: " + expected1);
    System.out.println("DNA is: " + dna2);
    System.out.println("dna2 result: " + findSimpleGene(dna2) + " expected: " + expected2);
    System.out.println("DNA is: " + dna3);
    System.out.println("dna3 result: " + findSimpleGene(dna3) + " expected: " + expected3);
    System.out.println("DNA is: " + dna4);
    System.out.println("dna4 result: " + findSimpleGene(dna4) + " expected: " + expected4);
    System.out.println("DNA is: " + dna5);
    System.out.println("dna5 result: " + findSimpleGene(dna5) + " expected: " + expected5);
    System.out.println("DNA is: " + dna6);
    System.out.println("dna6 result: " + findSimpleGene(dna6));
  }

  public static void main(String[] args) {
    Part1 p1 = new Part1();
    p1.testSimpleGene();
  }
}
