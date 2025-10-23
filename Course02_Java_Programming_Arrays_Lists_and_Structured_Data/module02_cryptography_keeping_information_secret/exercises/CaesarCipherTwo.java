package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module02_cryptography_keeping_information_secret.exercises;

import edu.duke.FileResource;

public class CaesarCipherTwo {
  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int ALPHABET_COUNT = 26;
  private static final int E_INDEX = 4;
  private int eKey1;
  private int dKey1;
  private int eKey2;
  private int dKey2;
  private String shiftedAlphabet1;
  private String shiftedAlphabet2;

  public CaesarCipherTwo(int key1, int key2) {
    this.eKey1 = key1;
    this.eKey2 = key2;
    this.dKey1 = ALPHABET_COUNT - this.eKey1;
    this.dKey2 = ALPHABET_COUNT - this.eKey2;
    this.shiftedAlphabet1 = ALPHABET.substring(this.eKey1) + ALPHABET.substring(0, this.eKey1);
    this.shiftedAlphabet2 = ALPHABET.substring(this.eKey2) + ALPHABET.substring(0, this.eKey2);
  }

  private String halfOfString(String message, int start) {
    StringBuilder sb = new StringBuilder();

    if (message == null)
      return null;

    for (int i = start; i < message.length(); i += 2) {
      sb.append(message.charAt(i));
    }

    return sb.toString();
  }

  public String encrypt(String input) {
    if (input == null || input.isBlank()) {
      return input;
    }

    StringBuilder encrypted = new StringBuilder(input.length());

    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);

      if (Character.isAlphabetic(currentChar)) {
        boolean isUpperCase = Character.isUpperCase(currentChar);
        char upperChar = Character.toUpperCase(currentChar);
        int indexOfChar = ALPHABET.indexOf(upperChar);

        // Use shiftedAlphabet1 for even indices, shiftedAlphabet2 for odd
        String shiftedAlphabet = (i % 2 == 0) ? this.shiftedAlphabet1 : this.shiftedAlphabet2;
        char encryptedChar = shiftedAlphabet.charAt(indexOfChar);

        if (!isUpperCase) {
          encryptedChar = Character.toLowerCase(encryptedChar);
        }
        encrypted.append(encryptedChar);
      } else {
        encrypted.append(currentChar);
      }
    }

    return encrypted.toString();
  }

  public String decrypt(String input) {
    CaesarCipherTwo cct = new CaesarCipherTwo(this.dKey1, this.dKey2);
    return cct.encrypt(input);
  }
}

class TestCaesarCipherTwo {
  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int ALPHABET_COUNT = 26;
  private static final int E_INDEX = 4;
  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module02_cryptography_keeping_information_secret/PracticeBreakingCaesarData/";

  private static String halfOfString(String message, int start) {
    StringBuilder sb = new StringBuilder();

    if (message == null)
      return null;

    for (int i = start; i < message.length(); i += 2) {
      sb.append(message.charAt(i));
    }

    return sb.toString();
  };

  private static int[] countLetters(String encrypted) {
    int[] freq = new int[ALPHABET_COUNT];

    if (encrypted == null || encrypted.isBlank())
      return freq;

    for (char ch : encrypted.toCharArray()) {
      if (Character.isLetter(ch)) {
        int indexOfChar = ALPHABET.indexOf(Character.toUpperCase(ch));
        freq[indexOfChar] += 1;
      }
    }

    return freq;
  }

  private static int maxIndex(int[] values) {
    int maxIndex = -1;
    int maxValue = 0;
    for (int i = 0; i < values.length; i++) {
      if (values[i] > maxValue) {
        maxValue = values[i];
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  private static int getKey(String s) {
    int[] freq = countLetters(s);
    int maxIndex = maxIndex(freq);
    return (maxIndex - E_INDEX + ALPHABET_COUNT) % ALPHABET_COUNT;
  }

  private static String breakCaesarCipher(String input) {
    String evenMessage = halfOfString(input, 0);
    String oddMessage = halfOfString(input, 1);
    int eKey1 = getKey(evenMessage);
    int eKey2 = getKey(oddMessage);
    System.out.println(eKey1 + " " + eKey2);
    CaesarCipherTwo cct = new CaesarCipherTwo(eKey1, eKey2);
    return cct.decrypt(input);
  }

  private static void simpleTests() {
    int testKey1 = 14;
    int testKey2 = 24;
    String file = PARENT_DIR + "mysteryTwoKeysPractice.txt";
    FileResource fr = new FileResource(file);
    String input = fr.asString();
    CaesarCipherTwo cct = new CaesarCipherTwo(testKey1, testKey2);

    String encrypted = cct.encrypt(input);
    String decrypted = cct.decrypt(encrypted);

    System.out.println("----- FILENAME -----");
    System.out.println(file);
    System.out.println("----- INPUT ------");
    System.out.println(input);
    System.out.println("----- ENCRYPTED w/ " + testKey1 + " and " + testKey2 + " ------");
    System.out.println(encrypted);
    System.out.println("----- DECRYPTED ------");
    System.out.println(decrypted);
    System.out.println("------ AUTO-DECRYPTED ------");
    String autoDecrypted = breakCaesarCipher(input);
    System.out.println(autoDecrypted);
  };

  public static void main(String[] args) {
    simpleTests();
  }
}
