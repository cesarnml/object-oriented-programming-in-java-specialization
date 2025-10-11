/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
package Course01_Java_Programming_Solving_Problems_with_Software.module04_csv_files_and_basic_statistics_in_java.exports;

import edu.duke.*;

import org.apache.commons.csv.*;

public class WhichCountriesExport {
	private static String FILE_PATH = "Course01_Java_Programming_Solving_Problems_with_Software/module04_csv_files_and_basic_statistics_in_java/exports/exportdata.csv";
	private static String FILE_PATH_TEST = "Course01_Java_Programming_Solving_Problems_with_Software/module04_csv_files_and_basic_statistics_in_java/exports/exportsmall.csv";
	private static String FILE_PATH_SMALL_TEST = "Course01_Java_Programming_Solving_Problems_with_Software/module04_csv_files_and_basic_statistics_in_java/exports_data/exports_small.csv";

	public static void listExporters(CSVParser parser, String exportOfInterest) {
		// for each row in the CSV File
		for (CSVRecord record : parser) {
			// Look at the "Exports" column
			String export = record.get("Exports");
			// Check if it contains exportOfInterest
			if (export.contains(exportOfInterest)) {
				// If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}

	public static void whoExportsCoffee() {

		FileResource fr = new FileResource(FILE_PATH);
		CSVParser parser = fr.getCSVParser();
		listExporters(parser, "coffee");
	}

	public static String countryInfo(CSVParser parser, String country) {
		String result = "";
		for (CSVRecord record : parser) {
			if (record.get("Country").equals(country)) {
				result = record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
			}
		}
		if (result.isEmpty()) {
			return "NOT FOUND";
		}
		return result;
	}

	public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		for (CSVRecord record : parser) {
			String exports = record.get("Exports");
			if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
				System.out.println(record.get("Country"));
			}
		}
	}

	public static int numberOfExporters(CSVParser parser, String exportItem) {
		int count = 0;
		for (CSVRecord record : parser) {
			if (record.get("Exports").contains(exportItem)) {
				count += 1;
			}
		}
		return count;
	}

	public static void bigExporters(CSVParser parser, String amount) {
		int amountLength = amount.length();
		for (CSVRecord record : parser) {
			String currentAmount = record.get("Value (dollars)");
			if (currentAmount.length() > amountLength) {
				System.out.println(record.get("Country") + " " + currentAmount);
			}
		}
	}

	public static void tester() {
		FileResource fr = new FileResource(FILE_PATH);
		CSVParser parser = fr.getCSVParser();
		// String result = countryInfo(parser, "Nauru");
		// System.out.println(result);
		// listExportersTwoProducts(parser, "cotton", "flowers");
		// parser = fr.getCSVParser();
		// String exportItem = "cocoa";
		// int exporterCount = numberOfExporters(parser, exportItem);
		// System.out.println("Number of Exporters (" + exportItem + "): " +
		// exporterCount);
		parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999,999");
	}

	public static void main(String[] args) {
		tester();
	}
}
