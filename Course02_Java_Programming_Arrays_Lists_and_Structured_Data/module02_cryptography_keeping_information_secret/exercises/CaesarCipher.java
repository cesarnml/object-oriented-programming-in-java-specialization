
package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module02_cryptography_keeping_information_secret.exercises;

import edu.duke.FileResource;

public class CaesarCipher {
  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int ALPHABET_COUNT = 26;
  private int eKey;
  private int dKey;
  private String shiftedAlphabet;

  public CaesarCipher(int key) {
    this.eKey = key;
    this.dKey = ALPHABET_COUNT - this.eKey;
    this.shiftedAlphabet = ALPHABET.substring(key) + ALPHABET.substring(0, key);
  }

  public String encrypt(String input) {
    if (input == null)
      return null;

    if (input.isBlank())
      return input;

    StringBuilder encrypted = new StringBuilder(input.length());

    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);

      if (Character.isAlphabetic(currentChar)) {
        // Convert to uppercase for lookup, preserving the original case
        boolean isUpperCase = Character.isUpperCase(currentChar);
        char upperChar = Character.toUpperCase(currentChar);
        int indexOfChar = ALPHABET.indexOf(upperChar);

        // Get encrypted character and maintain original case
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
    CaesarCipher cc = new CaesarCipher(this.dKey);
    return cc.encrypt(input);
  }

  public static void main(String[] args) {

  }
}

class TestCaesarCipher {
  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int ALPHABET_COUNT = 26;
  private static final int E_INDEX = 4;
  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module02_cryptography_keeping_information_secret/PracticeBreakingCaesarData/";

  public static int[] countLetters(String encrypted) {
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

  public static int maxIndex(int[] values) {
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

  public static String breakCaesarCipher(String input) {
    int[] freq = countLetters(input);
    int maxIndex = maxIndex(freq);
    int eKey = (maxIndex - E_INDEX + ALPHABET_COUNT) % (ALPHABET_COUNT);
    CaesarCipher cc = new CaesarCipher(eKey);
    return cc.decrypt(input);
  }

  public static void simpleTests() {
    String file = PARENT_DIR + "message2.txt";
    FileResource fr = new FileResource(file);
    String input = fr.asString();
    int testKey = 18;
    CaesarCipher cc = new CaesarCipher(testKey);
    String encrypted = cc.encrypt(input);
    System.out.println("----- message ----");
    System.out.println(input);
    System.out.println("------ encrypted w/ key " + testKey + " -----");
    System.out.println(encrypted);
    String decrypted = cc.decrypt(encrypted);
    System.out.println("----- decrypted ------");
    System.out.println(decrypted);
    String autoDecrypted = breakCaesarCipher(encrypted);
    System.out.println("----- autoDecrypted ------");
    System.out.println(autoDecrypted);
  }

  public static void main(String[] args) {
    simpleTests();
  }
}