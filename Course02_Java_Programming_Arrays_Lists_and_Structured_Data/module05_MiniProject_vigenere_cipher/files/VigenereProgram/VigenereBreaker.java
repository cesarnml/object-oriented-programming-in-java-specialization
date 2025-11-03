package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module05_MiniProject_vigenere_cipher.files.VigenereProgram;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module05_MiniProject_vigenere_cipher/";

  public String sliceString(String message, int whichSlice, int totalSlices) {
    StringBuilder sb = new StringBuilder();
    for (int i = whichSlice; i < message.length(); i = i + totalSlices) {
      sb.append(message.charAt(i));
    }
    return sb.toString();
  }

  private static void testSliceString() {
    String testMessage = "abcdefghijklm";
    VigenereBreaker vb = new VigenereBreaker();
    String result = vb.sliceString(testMessage, 0, 3);
    System.out.println(result);
    result = vb.sliceString(testMessage, 1, 3);
    System.out.println(result);
    result = vb.sliceString(testMessage, 2, 3);
    System.out.println(result);
    result = vb.sliceString(testMessage, 1, 4);
    System.out.println(result);
    result = vb.sliceString(testMessage, 2, 5);
    System.out.println(result);
  }

  public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
    int[] key = new int[klength];
    CaesarCracker cc = new CaesarCracker(mostCommon);
    for (int i = 0; i < klength; i++) {
      String sliceString = this.sliceString(encrypted, i, klength);
      int sliceKey = cc.getKey(sliceString);
      key[i] = sliceKey;
    }
    return key;
  }

  private static void testTryKeyLength() {
    String relativePath = "files/VigenereTestData/";
    String filename = "athens_keyflute.txt";
    FileResource fr = new FileResource(PARENT_DIR + relativePath + filename);
    String encrypted = fr.asString().trim();
    VigenereBreaker vb = new VigenereBreaker();
    int[] result = vb.tryKeyLength(encrypted, 5, 'e');
    // int[] result = vb.tryKeyLength(encrypted, 38, 'e');

    System.out.println("Key: " + Arrays.toString(result));
  }

  public HashSet<String> readDictionary(FileResource fr) {
    HashSet<String> dictionary = new HashSet<String>();

    for (String word : fr.lines()) {
      dictionary.add(word.toLowerCase());
    }

    return dictionary;
  }

  public int countWords(String message, HashSet<String> dictionary) {
    int count = 0;

    for (String word : message.split("\\W+")) {

      if (dictionary.contains(word.toLowerCase())) {
        count++;
      }
    }

    return count;
  }

  public String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommon) {
    int maxRealWords = 0;
    int[] bestKey = {};

    VigenereBreaker vb = new VigenereBreaker();

    for (int keyLength = 1; keyLength <= 100; keyLength++) {
      int[] key = vb.tryKeyLength(encrypted, keyLength, mostCommon);
      VigenereCipher vc = new VigenereCipher(key);
      String decrypted = vc.decrypt(encrypted);
      int wordCount = vb.countWords(decrypted, dictionary);
      if (wordCount > maxRealWords) {
        maxRealWords = wordCount;
        bestKey = key;
      }
    }

    System.out.println("------------------");
    System.out.println("Best key: (length: " + bestKey.length + ")");
    System.out.println(Arrays.toString(bestKey));
    System.out.println("with a max real words of: " + maxRealWords);
    System.out.println("------------------");

    VigenereCipher vc = new VigenereCipher(bestKey);
    return vc.decrypt(encrypted);
  }

  public char mostCommonCharIn(HashSet<String> dictionary) {
    HashMap<Character, Integer> letterFrequencyMap = new HashMap<>();
    for (String word : dictionary) {
      for (int i = 0; i < word.length(); i++) {
        char currentChar = word.charAt(i);
        int currentValue = letterFrequencyMap.getOrDefault(currentChar, 0);
        letterFrequencyMap.put(currentChar, currentValue + 1);
      }
    }
    int max = 0;
    char mostCommon = 'e'; // default fallback
    for (Character letter : letterFrequencyMap.keySet()) {
      int currentCount = letterFrequencyMap.get(letter);
      if (currentCount > max) {
        max = currentCount;
        mostCommon = letter;
      }
    }
    return mostCommon;
  }

  public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
    int maxWordCount = 0;
    String encryptionLanguage = "";
    String bestDecrypted = "";
    for (String language : languages.keySet()) {
      HashSet<String> dictionary = languages.get(language);
      char mostCommon = mostCommonCharIn(dictionary);
      String decrypted = this.breakForLanguage(encrypted, dictionary, mostCommon);
      int currentWordCount = this.countWords(decrypted, dictionary);
      if (currentWordCount > maxWordCount) {
        maxWordCount = currentWordCount;
        encryptionLanguage = language;
        bestDecrypted = decrypted;
      }
    }
    System.out.println("------------------");
    System.out.println("Message encrypted using: " + encryptionLanguage);
    System.out.println("\n");
    System.out.println("ENCRYPTED MESSAGE");
    System.out.println(encrypted);
    System.out.println("\n");
    System.out.println("DECRYPTED MESSAGE");
    System.out.println(bestDecrypted);

    return bestDecrypted;
  }

  public void breakVigenere() {
    // String relativePath1 = "files/VigenereTestData/";
    // String relativePath1 = "files/";
    String relativePath1 = "files/VigenereProgram/messages/";
    String relativePath2 = "files/VigenereProgram/dictionaries/";

    // String filename1 = "secretmessage2.txt";
    // String filename1 = "athens_keyflute.txt";
    // String filename1 = "secretmessage3.txt";
    String filename1 = "secretmessage4.txt";

    // String filename1 = "secretmessage1.txt";
    // String filename2 = "English";
    String[] languages = { "Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish" };
    int testKeyLength = 4;
    char testMostCommon = 'e';

    FileResource fr1 = new FileResource(PARENT_DIR + relativePath1 + filename1);
    String encrypted = fr1.asString().trim();

    // FileResource fr2 = new FileResource(PARENT_DIR + relativePath2 + filename2);
    HashMap<String, HashSet<String>> dictionaries = new HashMap<>();
    for (String language : languages) {
      FileResource fr = new FileResource(PARENT_DIR + relativePath2 + language);
      HashSet<String> dictionary = this.readDictionary(fr);
      dictionaries.put(language, dictionary);
    }

    String decrypted = this.breakForAllLangs(encrypted, dictionaries);

    // HashSet<String> dictionary = this.readDictionary(fr2);
    // String decrypted = this.breakForLanguage(encrypted, dictionary);

    // int[] key = this.tryKeyLength(encrypted, testKeyLength, testMostCommon);
    // VigenereCipher vc = new VigenereCipher(key);
    // String decrypted = vc.decrypt(encrypted);

    // System.out.println("--------------");
    // // System.out.println("Key: " + Arrays.toString(key));
    // // System.out.println("\n");
    System.out.println("Encrypted Message:\n");
    System.out.println(encrypted);
    System.out.println("\n");
    System.out.println("Decrypted Message:\n");
    System.out.println(decrypted);
    System.out.println("--------------");
  }

  public static void main(String[] args) {
    // testSliceString();
    // testTryKeyLength();
    VigenereBreaker vb = new VigenereBreaker();
    vb.breakVigenere();
  }
}
