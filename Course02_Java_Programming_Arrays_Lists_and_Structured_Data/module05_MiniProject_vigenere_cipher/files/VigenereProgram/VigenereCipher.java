package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module05_MiniProject_vigenere_cipher.files.VigenereProgram;

import edu.duke.*;
import java.util.*;

public class VigenereCipher {
  CaesarCipher[] ciphers;

  public VigenereCipher(int[] key) {
    ciphers = new CaesarCipher[key.length];
    for (int i = 0; i < key.length; i++) {
      ciphers[i] = new CaesarCipher(key[i]);
    }
  }

  public String encrypt(String input) {
    StringBuilder answer = new StringBuilder();
    int i = 0;
    for (char c : input.toCharArray()) {
      int cipherIndex = i % ciphers.length;
      CaesarCipher thisCipher = ciphers[cipherIndex];
      answer.append(thisCipher.encryptLetter(c));
      i++;
    }
    return answer.toString();
  }

  public String decrypt(String input) {
    StringBuilder answer = new StringBuilder();
    int i = 0;
    for (char c : input.toCharArray()) {
      int cipherIndex = i % ciphers.length;
      CaesarCipher thisCipher = ciphers[cipherIndex];
      answer.append(thisCipher.decryptLetter(c));
      i++;
    }
    return answer.toString();
  }

  public String toString() {
    return Arrays.toString(ciphers);
  }
}

class TestVigenereCipher {
  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module05_MiniProject_vigenere_cipher/";

  private static void tester() {
    String relativePath = "files/VigenereTestData/";
    String filename = "titus-small.txt";

    int[] testKey = { 17, 14, 12, 4 };
    VigenereCipher vc = new VigenereCipher(testKey);

    FileResource fr = new FileResource(PARENT_DIR + relativePath + filename);
    String message = fr.asString().trim();
    String encrypted = vc.encrypt(message);
    String decrypted = vc.decrypt(encrypted);

    System.out.println("------------------");
    System.out.println("Original Message:\n");
    System.out.println(message);
    System.out.println("\n");
    System.out.println("Encrypted Message:\n");
    System.out.println(encrypted);
    System.out.println("\n");
    System.out.println("Decrypted Message:\n");
    System.out.println(decrypted);
    System.out.println("------------------");
  }

  public static void main(String[] args) {
    tester();
  }
}