/**
 * Count common words in Shakespeare's works
 * 
 * @author Duke Software Team
 * @version 1.0
 */
package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module02_cryptography_keeping_information_secret.CommonWords;

import edu.duke.*;

public class CommonWords {
	private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module02_cryptography_keeping_information_secret/CommonWords/";

	public static String[] getCommon() {
		FileResource resource = new FileResource(PARENT_DIR + "data/common.txt");
		String[] common = new String[20];
		int index = 0;
		for (String s : resource.words()) {
			common[index] = s;
			index += 1;
		}
		return common;
	}

	public int indexOf(String[] list, String word) {
		for (int k = 0; k < list.length; k++) {
			if (list[k].equals(word)) {
				return k;
			}
		}
		return -1;
	}

	public void countWords(FileResource resource, String[] common, int[] counts) {
		for (String word : resource.words()) {
			word = word.toLowerCase();
			int index = indexOf(common, word);
			if (index != -1) {
				counts[index] += 1;
			}
		}
	}

	void countShakespeare() {
		// String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt",
		// "likeit.txt", "macbeth.txt", "romeo.txt"};
		String[] plays = { "small.txt" };
		String[] common = getCommon();
		int[] counts = new int[common.length];
		for (int k = 0; k < plays.length; k++) {
			FileResource resource = new FileResource("data/" + plays[k]);
			countWords(resource, common, counts);
			System.out.println("done with " + plays[k]);
		}

		for (int k = 0; k < common.length; k++) {
			System.out.println(common[k] + "\t" + counts[k]);
		}
	}
}
