package Course01_Java_Programming_Solving_Problems_with_Software.module04_csv_files_and_basic_statistics_in_java.hottestTempPseudo;

/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
	public static CSVRecord hottestHourInFile(CSVParser parser) {
		// start with largestSoFar as nothing

		// For each row (currentRow) in the CSV File

		// If largestSoFar is nothing

		// Otherwise

		// Check if currentRow’s temperature > largestSoFar’s

		// If so update largestSoFar to currentRow

		// The largestSoFar is the answer

	}

	public static void testHottestInDay() {
		FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
	}

	public static void main(String[] args) {
		testHottestInDay();
	}
}
