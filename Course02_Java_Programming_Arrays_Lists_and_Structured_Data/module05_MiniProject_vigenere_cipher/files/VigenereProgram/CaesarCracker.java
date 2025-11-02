package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module05_MiniProject_vigenere_cipher.files.VigenereProgram;

import edu.duke.*;

public class CaesarCracker {
  char mostCommon;

  public CaesarCracker() {
    mostCommon = 'e';
  }

  public CaesarCracker(char c) {
    mostCommon = c;
  }

  public int[] countLetters(String message) {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    int[] counts = new int[26];
    for (int k = 0; k < message.length(); k++) {
      int dex = alphabet.indexOf(Character.toLowerCase(message.charAt(k)));
      if (dex != -1) {
        counts[dex] += 1;
      }
    }
    return counts;
  }

  public int maxIndex(int[] vals) {
    int maxDex = 0;
    for (int k = 0; k < vals.length; k++) {
      if (vals[k] > vals[maxDex]) {
        maxDex = k;
      }
    }
    return maxDex;
  }

  public int getKey(String encrypted) {
    int[] freqs = countLetters(encrypted);
    int maxDex = maxIndex(freqs);
    int mostCommonPos = mostCommon - 'a';
    int dkey = maxDex - mostCommonPos;
    if (maxDex < mostCommonPos) {
      dkey = 26 - (mostCommonPos - maxDex);
    }
    return dkey;
  }

  public String decrypt(String encrypted) {
    int key = getKey(encrypted);
    CaesarCipher cc = new CaesarCipher(key);
    return cc.decrypt(encrypted);
  }
}

class TestCaesarCracker {
  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module05_MiniProject_vigenere_cipher/";

  private static void tester() {
    String relativePath = "files/VigenereTestData/";
    String filename1 = "titus-small_key5.txt";
    String filename2 = "oslusiadas_key17.txt";

    String[] testCases = { PARENT_DIR + relativePath + filename1, PARENT_DIR + relativePath + filename2 };

    CaesarCracker cc1 = new CaesarCracker();

    char mostCommonPortugueseChar = 'a';
    CaesarCracker cc2 = new CaesarCracker(mostCommonPortugueseChar);

    CaesarCracker[] ccs = { cc1, cc2 };

    for (int i = 0; i < testCases.length; i++) {
      String test = testCases[i];
      CaesarCracker cc = ccs[i];

      FileResource fr = new FileResource(test);
      String encrypted = fr.asString().trim();

      System.out.println("--------------");
      System.out.println("CaesarCracker Test: (filename: " + test + ")");
      System.out.println("with key: " + cc.getKey(encrypted));

      System.out.println("Encrypted Message:\n");
      System.out.println(encrypted);
      System.out.println();

      System.out.println("Decrypted Message:\n");
      String decrypted = cc.decrypt(encrypted);
      System.out.println(decrypted);
      System.out.println("--------------");

      System.out.println("\n\n");
    }
  }

  public static void main(String[] args) {
    tester();
  }
}