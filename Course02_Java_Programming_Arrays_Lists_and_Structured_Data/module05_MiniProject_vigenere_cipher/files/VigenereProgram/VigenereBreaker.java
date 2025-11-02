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
    // int[] result = vb.tryKeyLength(encrypted, 5, 'e');
    int[] result = vb.tryKeyLength(encrypted, 38, 'e');

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

  public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
    int maxRealWords = 0;
    char mostCommon = 'e';
    int[] bestKey = {};

    VigenereBreaker vb = new VigenereBreaker();

    for (int keyLength = 38; keyLength <= 100; keyLength++) {
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

    VigenereCipher vc = new VigenereCipher(bestKey);
    return vc.decrypt(encrypted);
  }

  public void breakVigenere() {
    // String relativePath1 = "files/VigenereTestData/";
    String relativePath1 = "files/";
    String relativePath2 = "files/VigenereProgram/dictionaries/";

    String filename1 = "secretmessage2.txt";
    // String filename1 = "secretmessage1.txt";
    String filename2 = "English";

    int testKeyLength = 4;
    char testMostCommon = 'e';

    FileResource fr1 = new FileResource(PARENT_DIR + relativePath1 + filename1);
    String encrypted = fr1.asString().trim();

    FileResource fr2 = new FileResource(PARENT_DIR + relativePath2 + filename2);
    HashSet<String> dictionary = this.readDictionary(fr2);

    String decrypted = this.breakForLanguage(encrypted, dictionary);

    // int[] key = this.tryKeyLength(encrypted, testKeyLength, testMostCommon);
    // VigenereCipher vc = new VigenereCipher(key);
    // String decrypted = vc.decrypt(encrypted);

    // System.out.println("--------------");
    // // System.out.println("Key: " + Arrays.toString(key));
    // // System.out.println("\n");
    // System.out.println("Encrypted Message:\n");
    // System.out.println(encrypted);
    // System.out.println("\n");
    // System.out.println("Decrypted Message:\n");
    // System.out.println(decrypted);
    // System.out.println("--------------");

  }

  public static void main(String[] args) {
    // testSliceString();
    // testTryKeyLength();
    VigenereBreaker vb = new VigenereBreaker();
    vb.breakVigenere();
  }
}
