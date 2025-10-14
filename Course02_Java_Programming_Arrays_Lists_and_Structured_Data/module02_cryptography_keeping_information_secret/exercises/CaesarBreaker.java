package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module02_cryptography_keeping_information_secret.exercises;

import edu.duke.FileResource;

/**
 * CaesarBreaker class to decrypt messages encrypted with the CaesarCipher. It
 * includes methods for statistical analysis of letter frequencies to break the
 * cipher.
 */
public class CaesarBreaker {
  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module02_cryptography_keeping_information_secret/ProgrammingBreakingCaesarData/";
  private static final int MAX_KEY = 26;
  private static final int E_INDEX = 4; // 'e' is the 5th letter (index 4) in the alphabet
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  /**
   * Decrypt a message using a known key
   * 
   * @param encrypted The encrypted message
   * @param dKey      The decryption key
   * @return The decrypted message
   */
  public static String decrypt(String encrypted, int dKey) {
    return CaesarCipher.encrypt(encrypted, dKey);
  }

  public static void testDecrypt() {
    int testEKey = 5;
    int testDKey = MAX_KEY - testEKey;
    FileResource fr = new FileResource(PARENT_DIR + "message2.txt");
    String encrypted = CaesarCipher.encrypt(fr.asString(), testEKey);
    String decrypted = decrypt(encrypted, testDKey);
    System.out.println("------ MESSAGE (encrypted) ----");
    System.out.println(encrypted);
    System.out.println("------ MESSAGE (decrypted) ----");
    System.out.println(decrypted);
  }

  public static int[] countLetters(String encrypted) {
    int[] freq = new int[MAX_KEY];

    if (encrypted == null || encrypted.isBlank())
      return freq;

    for (char ch : encrypted.toCharArray()) {
      if (Character.isLetter(ch)) {
        int indexOfChar = ALPHABET.indexOf(Character.toLowerCase(ch));
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

  public static String halfOfString(String message, int start) {
    StringBuilder sb = new StringBuilder();

    if (message == null)
      return null;

    for (int i = start; i < message.length(); i += 2) {
      sb.append(message.charAt(i));
    }

    return sb.toString();
  }

  public static void testHalfOfString() {
    int testStart0 = 0;
    int testStart1 = 1;
    String result0 = halfOfString("Qbkm Zgis", testStart0);
    String result1 = halfOfString("Qbkm Zgis", testStart1);
    System.out.println(result0);
    System.out.println(result1);
  }

  public static int getKey(String s) {
    int[] freq = countLetters(s);
    int assumedEIndex = maxIndex(freq);
    int key = assumedEIndex - E_INDEX;
    // wrap around if calculated key is negative
    return key >= 0 ? key : MAX_KEY + key;
  }

  public static String decryptTwoKeys(String encrypted) {

    if (encrypted == null)
      return "No message to decrypt.";

    if (encrypted.isBlank())
      return encrypted;

    String oddString = halfOfString(encrypted, 0);
    String evenString = halfOfString(encrypted, 1);

    int oddKey = getKey(oddString);
    int evenKey = getKey(evenString);

    int oddDKey = MAX_KEY - oddKey;
    int evenDKey = MAX_KEY - evenKey;

    System.out.println("Odd character encryption key is: " + oddKey + " Corresponding decrypt key: " + oddDKey);
    System.out.println("Even character encryption key is: " + evenKey + " Corresponding decrypt key: " + evenDKey);

    return CaesarCipher.encryptTwoKeys(encrypted, oddDKey, evenDKey);
  }

  public static void testDecryptTwoKeys() {
    String FILENAME = "wordsLotsOfEsEncrypted.txt";
    FileResource fr = new FileResource(PARENT_DIR + FILENAME);
    String decrypted = decryptTwoKeys(fr.asString());
    System.out.println("-------- Decrypted message---------");
    System.out.println(decrypted);
  }

  public static void main(String[] args) {
    testDecryptTwoKeys();
  }
}
