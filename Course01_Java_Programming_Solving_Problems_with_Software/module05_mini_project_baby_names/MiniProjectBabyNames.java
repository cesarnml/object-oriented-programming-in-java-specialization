package Course01_Java_Programming_Solving_Problems_with_Software.module05_mini_project_baby_names;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class MiniProjectBabyNames {
  private static String PARENT_DIR = "Course01_Java_Programming_Solving_Problems_with_Software/module05_mini_project_baby_names/";
  private static String GRADED_ASSIGNMENT_PATHNAME = "us_babynames/us_babynames_by_year/";
  private static int NAME = 0;
  private static int GENDER = 1;
  private static int COUNT = 2;

  public static void totalBirths(FileResource fr) {
    int totalBirths = 0;
    int totalBoys = 0;
    int totalGirls = 0;
    int countGirlNames = 0;
    int countBoyNames = 0;

    for (CSVRecord rec : fr.getCSVParser(false)) {
      int numBorn = Integer.parseInt(rec.get(COUNT));
      totalBirths += numBorn;
      if (rec.get(1).equals("M")) {
        totalBoys += numBorn;
        countBoyNames += 1;
      } else {
        totalGirls += numBorn;
        countGirlNames += 1;
      }
    }
    System.out.println("total births = " + totalBirths);
    System.out.println("female girls = " + totalGirls);
    System.out.println("male boys = " + totalBoys);
    System.out.println("Number of Girl Names = " + countGirlNames);
    System.out.println("Number of Boy Names = " + countBoyNames);
    System.out.println("Total Names = " + (countBoyNames + countGirlNames));
  }

  public static void testTotalBirths() {
    FileResource fr = new FileResource(PARENT_DIR + GRADED_ASSIGNMENT_PATHNAME + "yob1905.csv");
    totalBirths(fr);
  }

  public static int getRank(int year, String name, String gender) {
    String filePath = PARENT_DIR + GRADED_ASSIGNMENT_PATHNAME + "yob" + year + ".csv";
    FileResource fr = new FileResource(filePath);
    int rank = 0;
    for (CSVRecord rec : fr.getCSVParser(false)) {
      if (rec.get(GENDER).equals(gender)) {
        rank += 1;
        if (rec.get(NAME).equals(name)) {
          return rank;
        }
      }
    }
    return -1;
  }

  public static void testGetRank() {
    int result = getRank(1971, "Frank", "M");
    System.out.println(result);
  }

  public static String getName(int year, int rank, String gender) {
    String name = "NO_NAME";
    int currentRank = 0;
    String filePath = PARENT_DIR + GRADED_ASSIGNMENT_PATHNAME + "yob" + year + ".csv";

    FileResource fr = new FileResource(filePath);
    CSVParser parser = fr.getCSVParser(false);

    for (CSVRecord row : parser) {
      String currentGender = row.get(GENDER);
      if (currentGender.equals(gender)) {
        currentRank += 1;
        if (currentRank == rank) {
          return row.get(NAME);
        }
      }
    }
    return name;
  }

  public static void testGetName() {
    String result = getName(1982, 450, "M");
    System.out.println(result);
  }

  public static void whatIsNameInYear(String name, int year, int newYear, String gender) {
    int targetRank = getRank(year, name, gender);
    String newName = getName(newYear, targetRank, gender);
    String pronoun = gender.equals("M") ? "he" : "she";

    System.out
        .println(name + " born in " + year + " would be " + newName + " if " + pronoun + " was born in " + newYear);
  }

  public static void testWhatIsNameInYear() {
    whatIsNameInYear("Owen", 1974, 2014, "M");
  }

  public static int yearOfHighestRank(String name, String gender) {
    int YEAR_NOT_FOUND = -1;
    int highestRank = Integer.MAX_VALUE;
    int yearOfLargestRank = Integer.MIN_VALUE;

    DirectoryResource dr = new DirectoryResource();

    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      int currentRank = 0;
      for (CSVRecord row : fr.getCSVParser(false)) {
        String currentGender = row.get(GENDER);
        if (currentGender.equals(gender)) {
          currentRank += 1;
          String currentName = row.get(NAME);
          if (currentName.equals(name) && currentRank < highestRank) {
            highestRank = currentRank;
            yearOfLargestRank = Integer.parseInt(f.getName().substring(3, 7));
          }
        }
      }
    }
    return yearOfLargestRank > 0 ? yearOfLargestRank : YEAR_NOT_FOUND;
  }

  public static void testYearOfHighestRank() {
    int result = yearOfHighestRank("Mich", "M");
    System.out.println(result);
  }

  public static double getAverageRank(String name, String gender) {
    int sumRank = 0;
    int numberOfAppearances = 0;

    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      int currentYear = Integer.parseInt(f.getName().substring(3, 7));
      int currentRank = getRank(currentYear, name, gender);
      if (currentRank != -1) {
        sumRank += currentRank;
        numberOfAppearances += 1;
      }
    }

    return numberOfAppearances == 0 ? -1 : ((double) sumRank) / numberOfAppearances;
  }

  public static void testGetAverageRank() {
    double result = getAverageRank("Robert", "M");
    System.out.println(result);
  }

  public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
    int targetRank = getRank(year, name, gender);

    if (targetRank == -1) {
      return 0;
    }

    int totalBirths = 0;
    FileResource fr = new FileResource(PARENT_DIR + GRADED_ASSIGNMENT_PATHNAME + "yob" + year + ".csv");

    int currentRank = 0;
    for (CSVRecord row : fr.getCSVParser(false)) {
      if (row.get(GENDER).equals(gender)) {
        currentRank += 1;
        if (currentRank < targetRank) {
          totalBirths += Integer.parseInt(row.get(COUNT));
        }
      }
    }
    return totalBirths;
  }

  public static void testGetTotalBirthsRankedHigher() {
    int result = getTotalBirthsRankedHigher(1990, "Drew", "M");
    System.out.println(result);
  }

  public static void main(String[] args) {
    // testTotalBirths();
    // testGetRank();
    // testGetName();
    // testWhatIsNameInYear();
    // testYearOfHighestRank();
    // testGetAverageRank();
    testGetTotalBirthsRankedHigher();
  }
}
