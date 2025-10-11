package Course01_Java_Programming_Solving_Problems_with_Software.module04_csv_files_and_basic_statistics_in_java.hottestTemp;

/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVMax {
	private static String FILEPATH = "Course01_Java_Programming_Solving_Problems_with_Software/module04_csv_files_and_basic_statistics_in_java/hottestTemp/data/2015/weather-2015-01-01.csv";

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
			// The largestSoFar is the answer
		}
		return largestSoFar;
	}

	public static void testHottestInDay() {
		FileResource fr = new FileResource(FILEPATH);
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
	}

	public static void main(String[] args) {
		testHottestInDay();
	}
}