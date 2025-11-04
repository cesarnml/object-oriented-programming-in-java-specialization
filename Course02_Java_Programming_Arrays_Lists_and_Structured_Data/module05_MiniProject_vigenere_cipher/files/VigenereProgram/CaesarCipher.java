package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module05_MiniProject_vigenere_cipher.files.VigenereProgram;

import edu.duke.*;

public class CaesarCipher {
  private String alphabet;
  private String shiftedAlphabet;
  private int theKey;

  public CaesarCipher(int key) {
    theKey = key;
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    alphabet = alphabet + alphabet.toLowerCase();
    shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
  }

  private char transformLetter(char c, String from, String to) {
    int idx = from.indexOf(c);
    if (idx != -1) {
      return to.charAt(idx);
    }
    return c;
  }

  public char encryptLetter(char c) {
    return transformLetter(c, alphabet, shiftedAlphabet);
  }

  public char decryptLetter(char c) {
    return transformLetter(c, shiftedAlphabet, alphabet);
  }

  private String transform(String input, String from, String to) {
    StringBuilder sb = new StringBuilder(input);
    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      c = transformLetter(c, from, to);
      sb.setCharAt(i, c);
    }
    return sb.toString();
  }

  public String encrypt(String input) {
    return transform(input, alphabet, shiftedAlphabet);
  }

  public String decrypt(String input) {
    return transform(input, shiftedAlphabet, alphabet);
  }

  public String toString() {
    return "" + theKey;
  }
}

class TestCaesarCipher {

  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module05_MiniProject_vigenere_cipher/";

  private static void tester() {
    int testKey = 6;
    String testLetter1 = "C";
    String testLetter2 = "e";
    String testMessage1 = "Cesar";

    String relativePath = "files/VigenereTestData/titus-small.txt";
    FileResource fr = new FileResource(PARENT_DIR + relativePath);
    String testMessage2 = fr.asString().trim();

    String[] testCases = { testLetter1, testLetter2, testMessage1, testMessage2 };

    CaesarCipher cc = new CaesarCipher(testKey);

    for (String testString : testCases) {
      System.out.println("--------------");

      System.out.println("Encryption Test with key = " + testKey);
      System.out.println("Original Message:\n");
      System.out.println(testString);
      System.out.println();

      System.out.println("Encrypted Message:\n");
      String encrypted = cc.encrypt(testString);
      System.out.println(encrypted);
      System.out.println();

      System.out.println("Decrypted Message:\n");
      String decrypted = cc.decrypt(encrypted);
      System.out.println(decrypted);
      System.out.println("--------------");

      System.out.println("\n\n");
    }

  }

  public static void main(String[] args) {
    tester();
  }
}
