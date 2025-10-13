/**
 * Illustrate Character methods
 * 
 * @author Duke Software Team
 * @version (a version number or a date)
 */
package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module02_cryptography_keeping_information_secret.CharacterDemo;

public class CharacterDemo {
    public void digitTest() {
        String test = "ABCabc0123456789';#!";
        for (int k = 0; k < test.length(); k++) {
            char ch = test.charAt(k);
            if (Character.isDigit(ch)) {
                System.out.println(ch + " is a digit");
            }
            if (Character.isAlphabetic(ch)) {
                System.out.println(ch + " is alphabetic");
            }
            if (ch == '#') {
                System.out.println(ch + " #hashtag");
            }
        }
    }

    public void conversionTest() {
        String test = "ABCDEFabcdef123!#";
        for (int k = 0; k < test.length(); k++) {
            char ch = test.charAt(k);
            char uch = Character.toUpperCase(ch);
            char lch = Character.toLowerCase(ch);
            System.out.println(ch + " " + uch + " " + lch);
        }
    }

    public static void main(String[] args) {
        System.out.println("Running Character Demo");

        CharacterDemo demo = new CharacterDemo();

        System.out.println("\n--- Running Digit and Alphabetic Test ---");
        demo.digitTest();

        System.out.println("\n--- Running Case Conversion Test ---");
        demo.conversionTest();
    }
}
