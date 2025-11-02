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
    System.out.println("Key: " + Arrays.toString(result));
  }

  public static void breakVigenere() {
    String relativePath = "files/";
    // String filename = "athens_keyflute.txt";
    String filename = "secretmessage1.txt";

    FileResource fr = new FileResource(PARENT_DIR + relativePath + filename);
    String encrypted = fr.asString().trim();
    VigenereBreaker vb = new VigenereBreaker();

    int[] key = vb.tryKeyLength(encrypted, 4, 'e');
    VigenereCipher vc = new VigenereCipher(key);
    String decrypted = vc.decrypt(encrypted);

    System.out.println("--------------");
    System.out.println("Key: " + Arrays.toString(key));
    System.out.println("\n");
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
    breakVigenere();
  }
}
