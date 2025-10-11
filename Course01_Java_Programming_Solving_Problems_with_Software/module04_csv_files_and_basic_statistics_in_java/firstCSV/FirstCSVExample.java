/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
package Course01_Java_Programming_Solving_Problems_with_Software.module04_csv_files_and_basic_statistics_in_java.firstCSV;

import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstCSVExample {
	public static void readFood() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		for (CSVRecord record : parser) {
			System.out.print(record.get("Name") + " ");
			System.out.print(record.get("Favorite Color") + " ");
			System.out.println(record.get("Favorite Food"));
		}
	}

	public static void main(String[] args) {
		readFood();
	}
}
