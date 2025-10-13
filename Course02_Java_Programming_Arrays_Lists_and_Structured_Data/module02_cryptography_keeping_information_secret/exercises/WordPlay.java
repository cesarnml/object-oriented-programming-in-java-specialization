package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module02_cryptography_keeping_information_secret.exercises;

public class WordPlay {
  private static final String VOWELS = "aeiouAEIOU";

  /**
   * Returns a boolean indicating whether or not `ch` is a vowel (e.g. A, E, I, O,
   * U, or lowercase version)
   * 
   * @param ch - char to be tested
   * @return boolean result
   */
  public static boolean isVowel(char ch) {

    if (!Character.isAlphabetic(ch)) {
      return false;
    }
    return VOWELS.contains(Character.toString(ch));
  }

  /**
   * Replaces vowels in `phrase` with `ch`
   * 
   * @param phrase - the phrase that will be converted
   * @param ch     - the character that will replace all vowels in phrase
   * @return a String representing the converted phrase
   */
  public static String replaceVowels(String phrase, char ch) {
    // Defending against null phrase
    if (phrase == null)
      return null;
    // Quick exit if blank string
    if (phrase.isBlank())
      return phrase;

    StringBuilder converted = new StringBuilder(phrase.length());

    for (int i = 0; i < phrase.length(); i++) {
      char currentChar = phrase.charAt(i);
      if (isVowel(currentChar)) {
        converted.append(ch);
      } else {
        converted.append(currentChar);
      }
    }
    return converted.toString();
  }

  public static void testReplaceVowel() {
    String testPhrase = "HelloWorld";
    char testChar = '*';
    String result = replaceVowels(testPhrase, testChar);
    System.out.println("Phrase: " + testPhrase);
    System.out.println("Replace vowels with: " + testChar);
    System.out.println("Result: " + result);
  }

  /**
   * Returns a converted phrase, where even index incidents of `ch` in `phrase`
   * are replaced with `*` and odd index incidents are replaced with `+`. All
   * other characters in `phrase` are copied as is..
   * 
   * @param phrase - the phrase to be converted
   * @param ch     - the target character to be converted either to `*` (even
   *               index) or `+` (odd index)
   * @return returns the converted String
   */
  public static String emphasize(String phrase, char ch) {
    if (phrase == null)
      return null;
    if (phrase.isBlank())
      return phrase;

    StringBuilder converted = new StringBuilder(phrase.length());

    for (int i = 0; i < phrase.length(); i++) {
      char currentChar = phrase.charAt(i);
      if (currentChar == ch) {
        if (i % 2 == 0) {
          converted.append('*');
        } else {
          converted.append('+');
        }
      } else {
        converted.append(currentChar);
      }
    }
    return converted.toString();
  }

  public static void testEmphasize() {
    String testPhrase = "dna ctgaaactga";
    char testChar = 'a';

    String result = emphasize(testPhrase, testChar);

    System.out.println("Phrase: " + testPhrase);
    System.out.println("Target char: " + testChar);
    System.out.println("Converted: " + result);

  }

  public static void main(String[] args) {
    // testReplaceVowel();
    testEmphasize();
  }
}
