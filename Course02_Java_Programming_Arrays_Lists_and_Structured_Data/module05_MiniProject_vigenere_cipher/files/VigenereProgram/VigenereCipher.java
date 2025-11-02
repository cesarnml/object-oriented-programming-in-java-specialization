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
