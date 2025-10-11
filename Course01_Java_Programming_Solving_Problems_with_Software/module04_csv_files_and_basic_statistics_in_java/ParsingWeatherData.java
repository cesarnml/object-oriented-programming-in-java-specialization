package Course01_Java_Programming_Solving_Problems_with_Software.module04_csv_files_and_basic_statistics_in_java;

import java.io.File;

import javax.annotation.processing.Filer;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class ParsingWeatherData {
  private static String FILEPATH = "Course01_Java_Programming_Solving_Problems_with_Software/module04_csv_files_and_basic_statistics_in_java/nc_weather/2014/weather-2014-10-17.csv";
  private static String FILEPATH_PRACTICE = "Course01_Java_Programming_Solving_Problems_with_Software/module04_csv_files_and_basic_statistics_in_java/nc_weather/2014/weather-2014-04-01.csv";
  private static String DATE_UTC = "DateUTC";
  private static String TEMP = "TemperatureF";
  private static String HUMIDITY = "Humidity";
  private static String BAD_TEMP_VALUE = "-9999";
  private static String BAD_HUMIDITY_VALUE = "N/A";

  public static CSVRecord compareTemperature(CSVRecord lowestTempRow, CSVRecord currentRow) {
    double lowestTemp = Double.parseDouble(lowestTempRow.get(TEMP));
    double currentTemp = Double.parseDouble(currentRow.get(TEMP));
    if (currentTemp < lowestTemp && !currentRow.get(TEMP).equals(BAD_TEMP_VALUE)) {
      lowestTempRow = currentRow;
    }
    return lowestTempRow;
  }

  public static CSVRecord coldestHourInFile(CSVParser parser) {
    CSVRecord lowestTemperatureRow = null;
    for (CSVRecord currentRow : parser) {
      if (lowestTemperatureRow == null && !currentRow.get(TEMP).equals(BAD_TEMP_VALUE)) {
        lowestTemperatureRow = currentRow;
      } else {
        lowestTemperatureRow = compareTemperature(lowestTemperatureRow, currentRow);
      }
    }
    return lowestTemperatureRow;
  }

  public static String fileWithColdestTemperature() {
    DirectoryResource dr = new DirectoryResource();

    double coldestTemperature = Double.POSITIVE_INFINITY;
    File coldestTempFile = null;

    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      CSVParser parser = fr.getCSVParser();
      CSVRecord coldestRecord = coldestHourInFile(parser);
      double currentTemperature = Double.parseDouble(coldestRecord.get(TEMP));

      if (Double.parseDouble(coldestRecord.get(TEMP)) < coldestTemperature) {
        coldestTemperature = currentTemperature;
        coldestTempFile = f;
      }
    }
    return coldestTempFile.getAbsolutePath();
  }

  public static void testFileWithColdestTemperature() {
    String filePath = fileWithColdestTemperature();
    String fileName = new File(filePath).getName();

    FileResource fr = new FileResource(filePath);
    CSVParser parser = fr.getCSVParser();

    CSVRecord coldestRecord = coldestHourInFile(parser);
    String lowestTemp = coldestRecord.get(TEMP);

    System.out.println("Coldest day was in file " + fileName);
    System.out.println("Coldest temperature on that day was " + lowestTemp);
    System.out.println("All the temperatures on the coldest day were:");

    parser = fr.getCSVParser();
    for (CSVRecord row : parser) {
      System.out.println(row.get(DATE_UTC) + ": " + row.get(TEMP));
    }
  }

  public static void testColdestHourInFile() {
    CSVParser parser = new FileResource(FILEPATH_PRACTICE).getCSVParser();
    CSVRecord record = coldestHourInFile(parser);
    Double lowestTemp = Double.parseDouble(record.get(TEMP));
    System.out.println("coldest temperature was " + lowestTemp + " at " + record.get(DATE_UTC));
  }

  public static CSVRecord lowestHumidityInFile(CSVParser parser) {
    CSVRecord lowestHumidityRow = null;
    Double lowestHumidityValue = Double.POSITIVE_INFINITY;

    for (CSVRecord currentRow : parser) {
      String currentHumidity = currentRow.get(HUMIDITY);
      if (!currentHumidity.equals(BAD_HUMIDITY_VALUE) && Double.parseDouble(currentHumidity) < lowestHumidityValue) {
        lowestHumidityRow = currentRow;
        lowestHumidityValue = Double.parseDouble(currentHumidity);
      }
    }
    return lowestHumidityRow;
  }

  public static void testLowestHumidityInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord record = lowestHumidityInFile(parser);
    System.out.println("Lowest Humidity was " + record.get(HUMIDITY) + " at " + record.get(DATE_UTC));
  }

  public static CSVRecord lowestHumidityInManyFiles() {
    CSVRecord lowestRecord = null;

    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      CSVParser parser = fr.getCSVParser();
      CSVRecord currentRecord = lowestHumidityInFile(parser);

      if (lowestRecord == null) {
        lowestRecord = currentRecord;
      } else {
        Double currentHumidityValue = Double.parseDouble(currentRecord.get(HUMIDITY));
        Double lowestHumidityValue = Double.parseDouble(lowestRecord.get(HUMIDITY));
        if (currentHumidityValue < lowestHumidityValue) {
          lowestRecord = currentRecord;
        }
      }
    }
    return lowestRecord;
  }

  public static void testLowestHumidityInManyFiles() {
    CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();
    System.out.println(
        "Lowest Humidity was " + lowestHumidityRecord.get(HUMIDITY) + " at " + lowestHumidityRecord.get(DATE_UTC));
  }

  public static double averageTemperatureInFile(CSVParser parser) {
    double sum = 0.0;
    int count = 0;

    for (CSVRecord currentRow : parser) {
      String currentTemp = currentRow.get(TEMP);
      if (!currentTemp.equals(BAD_TEMP_VALUE)) {
        sum += Double.parseDouble(currentTemp);
        count += 1;
      }
    }
    return sum / count;
  }

  public static void testAverageTemperatureInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();

    double averageTemp = averageTemperatureInFile(parser);
    System.out.println("Average temperature in file is " + averageTemp);
  }

  public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
    double sum = 0.0;
    int count = 0;
    for (CSVRecord currentRow : parser) {
      String currentHumidity = currentRow.get(HUMIDITY);
      String currentTemp = currentRow.get(TEMP);
      if (currentHumidity.equals(BAD_HUMIDITY_VALUE) || Double.parseDouble(currentHumidity) < value) {
        continue;
      }
      if (currentTemp.equals(BAD_TEMP_VALUE)) {
        continue;
      }
      sum += Double.parseDouble(currentTemp);
      count += 1;
    }
    if (count == 0) {
      return Double.NEGATIVE_INFINITY;
    } else {
      return sum / count;
    }
  }

  public static void testAverageTemperatureWithHighHumidityInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double result = averageTemperatureWithHighHumidityInFile(parser, 80);
    System.out.println("Average Temp when high Humidity is " + result);
  }

  public static void main(String[] args) {
    // testColdestHourInFile();
    testFileWithColdestTemperature();
    // testLowestHumidityInFile();
    // testLowestHumidityInManyFiles();
    // testAverageTemperatureInFile();
    // testAverageTemperatureWithHighHumidityInFile();
  }
}
