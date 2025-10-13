
package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module02_cryptography_keeping_information_secret.exercises;

public class CaesarCipher {
  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  public static String encrypt(String input, int key) {
    if (input == null)
      return null;

    if (input.isBlank())
      return input;

    String shiftedAlphabet = ALPHABET.substring(key) + ALPHABET.substring(0, key);
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

  public static void testEncrypt() {
    String testPhrase = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
    int testKey = 15;
    String encrypted = encrypt(testPhrase, testKey);
    System.out.println("Message: " + testPhrase);
    System.out.println("key is " + testKey + "\n" + encrypted);
  }

  public static String encryptTwoKeys(String input, int key1, int key2) {
    if (input == null)
      return null;

    if (input.isBlank())
      return input;

    // Create shifted alphabets for both keys
    String shiftedAlphabet1 = ALPHABET.substring(key1) + ALPHABET.substring(0, key1);
    String shiftedAlphabet2 = ALPHABET.substring(key2) + ALPHABET.substring(0, key2);
    StringBuilder encrypted = new StringBuilder(input.length());

    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);

      if (Character.isAlphabetic(currentChar)) {
        boolean isUpperCase = Character.isUpperCase(currentChar);
        char upperChar = Character.toUpperCase(currentChar);
        int indexOfChar = ALPHABET.indexOf(upperChar);

        // Choose the right shifted alphabet based on character position
        String currentShiftedAlphabet = (i % 2 == 0) ? shiftedAlphabet1 : shiftedAlphabet2;

        // Encrypt the character
        char encryptedChar = currentShiftedAlphabet.charAt(indexOfChar);
        if (!isUpperCase) {
          encryptedChar = Character.toLowerCase(encryptedChar);
        }
        encrypted.append(encryptedChar);
      } else {
        // Non-English letters and non-alphabetic characters remain unchanged
        encrypted.append(currentChar);
      }
    }
    return encrypted.toString();
  }

  public static void testEncryptTwoKeys() {
    String testPhrase = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
    int key1 = 8;
    int key2 = 21;
    String encrypted = encryptTwoKeys(testPhrase, key1, key2);
    System.out.println("Message: " + testPhrase);
    System.out.println("Key1 is " + key1 + ", Key2 is " + key2);
    System.out.println("Encrypted: " + encrypted);

    // Additional test with uppercase and lowercase letters, and special characters
    testPhrase = "Hello, World!";
    key1 = 8;
    key2 = 21;
    encrypted = encryptTwoKeys(testPhrase, key1, key2);
    System.out.println("\nMessage: " + testPhrase);
    System.out.println("Key1 is " + key1 + ", Key2 is " + key2);
    System.out.println("Encrypted: " + encrypted);
  }

  public static void main(String[] args) {
    // testEncrypt();
    testEncryptTwoKeys();
  }
}
