/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
package Course01_Java_Programming_Solving_Problems_with_Software.module05_mini_project_baby_names.babyNamesTotals;

import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
	private static String PARENT_DIR = "Course01_Java_Programming_Solving_Problems_with_Software/module05_mini_project_baby_names/babyNamesTotals/";

	public static void printNames() {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num Born " + rec.get(2));
			}
		}
	}

	public static void totalBirths(FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
			} else {
				totalGirls += numBorn;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
	}

	public static void testTotalBirths() {
		// FileResource fr = new FileResource();
		FileResource fr = new FileResource(PARENT_DIR + "data/yob2014.csv");
		totalBirths(fr);
	}

	public static void main(String[] args) {
		testTotalBirths();
	}
}
