package Course01_Java_Programming_Solving_Problems_with_Software.module04_csv_files_and_basic_statistics_in_java.hottestTempMany;

/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
	private static String FILEPATH = "Course01_Java_Programming_Solving_Problems_with_Software/module04_csv_files_and_basic_statistics_in_java/hottestTempMany/data/2015/weather-2015-01-02.csv";

	public static CSVRecord hottestHourInFile(CSVParser parser) {
		// start with largestSoFar as nothing
		CSVRecord largestSoFar = null;
		// For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// If largestSoFar is nothing
			if (largestSoFar == null) {
				largestSoFar = currentRow;
			}
			// Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
				// Check if currentRow’s temperature > largestSoFar’s
				if (currentTemp > largestTemp) {
					// If so update largestSoFar to currentRow
					largestSoFar = currentRow;
				}
			}
		}
		// The largestSoFar is the answer
		return largestSoFar;
	}

	public static void testHottestInDay() {
		FileResource fr = new FileResource(FILEPATH);
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
	}

	public static CSVRecord hottestInManyDays() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
			if (largestSoFar == null) {
				largestSoFar = currentRow;
			}
			// Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
				// Check if currentRow’s temperature > largestSoFar’s
				if (currentTemp > largestTemp) {
					// If so update largestSoFar to currentRow
					largestSoFar = currentRow;
				}
			}
		}
		// The largestSoFar is the answer
		return largestSoFar;
	}

	public static void testHottestInManyDays() {
		CSVRecord largest = hottestInManyDays();
		System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
	}

	public static void main(String[] args) {
		testHottestInManyDays();
		testHottestInDay();
	}
}
