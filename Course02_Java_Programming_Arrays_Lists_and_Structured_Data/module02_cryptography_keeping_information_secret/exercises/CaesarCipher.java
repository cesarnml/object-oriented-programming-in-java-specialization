
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
    String testPhrase = "First Legion";
    int testKey = 17;
    String encrypted = encrypt(testPhrase, testKey);
    System.out.println("Message: " + testPhrase);
    System.out.println("key is " + testKey + "\n" + encrypted);
  }

  public static String encryptTwoKeys(String input, int key1, int key2) {
    StringBuilder encrypted = new StringBuilder(input.length());
    return encrypted.toString();
  }

  public static void testEncryptTwoKeys() {

  }

  public static void main(String[] args) {
    testEncrypt();
    // testEncryptTwoKeys();
  }
}
