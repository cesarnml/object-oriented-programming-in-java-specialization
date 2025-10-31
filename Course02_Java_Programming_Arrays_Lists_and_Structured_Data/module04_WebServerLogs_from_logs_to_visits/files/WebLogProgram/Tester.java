package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module04_WebServerLogs_from_logs_to_visits.files.WebLogProgram;

/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module04_WebServerLogs_from_logs_to_visits/";

  public static void testLogEntry() {
    LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
    System.out.println(le);
    LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
    System.out.println(le2);
  }

  public static void testLogAnalyzer() {
    LogAnalyzer la = new LogAnalyzer();
    String relativePath = "files/WebLogProgram/short-test_log";
    la.readFile(PARENT_DIR + relativePath);
    la.printAll();
  }

  public static void testUniqueIP() {
    LogAnalyzer la = new LogAnalyzer();
    // String relativePath = "files/WebLogProgram/short-test_log";
    String relativePath = "files/WebLogProgram/weblog2_log";

    la.readFile(PARENT_DIR + relativePath);
    int uniqueIPs = la.countUniqueIPs();
    System.out.println("There are " + uniqueIPs + " IPs");
  }

  public static void testPrintAllHigherThanNum() {
    LogAnalyzer la = new LogAnalyzer();
    String relativePath = "files/WebLogProgram/weblog1_log";
    la.readFile(PARENT_DIR + relativePath);
    la.printAllHigherThanNum(400);
  }

  public static void testUniqueIPVisitsOnDay() {
    // String testDate = "Mar 17";
    // String testDate = "Sep 30";
    String testDate = "Sep 24";

    LogAnalyzer la = new LogAnalyzer();
    // String relativePath = "files/WebLogProgram/weblog-short_log";
    // String relativePath = "files/WebLogProgram/weblog1_log";
    String relativePath = "files/WebLogProgram/weblog2_log";

    la.readFile(PARENT_DIR + relativePath);
    ArrayList<String> uniqueIPVisitsOnDay = la.uniqueIPVisitsOnDay(testDate);

    System.out.println("Unique IPs (" + uniqueIPVisitsOnDay.size() + ") on " + testDate + ":");
    for (String ip : uniqueIPVisitsOnDay)
      System.out.println(ip);
  }

  public static void testCountUniqueIPsInRange() {
    // int low = 200;
    // int high = 299;
    // int low = 300;
    // int high = 399;
    int low = 400;
    int high = 499;

    LogAnalyzer la = new LogAnalyzer();
    // String relativePath = "files/WebLogProgram/short-test_log";
    // String relativePath = "files/WebLogProgram/weblog1_log";
    String relativePath = "files/WebLogProgram/weblog2_log";
    la.readFile(PARENT_DIR + relativePath);
    int count = la.countUniqueIPsInRange(low, high);
    System.out.println("Number of IPs in range (" + low + ", " + high + "): " + count);

  }

  public static void testMostNumberVisitsByIP() {
    LogAnalyzer la = new LogAnalyzer();
    // String relativePath = "files/WebLogProgram/short-test_log";
    // String relativePath = "files/WebLogProgram/weblog1_log";
    String relativePath = "files/WebLogProgram/weblog2_log";

    la.readFile(PARENT_DIR + relativePath);
    HashMap<String, Integer> visitsByIp = la.countVisitsPerIP();
    int count = la.mostNumberVisitsByIP(visitsByIp);
    System.out.println(count);
  }

  public static void testIPsMostVisits() {
    LogAnalyzer la = new LogAnalyzer();
    // String relativePath = "files/WebLogProgram/short-test_log";
    // String relativePath = "files/WebLogProgram/weblog1_log";
    String relativePath = "files/WebLogProgram/weblog2_log";

    la.readFile(PARENT_DIR + relativePath);
    HashMap<String, Integer> visitsByIp = la.countVisitsPerIP();
    ArrayList<String> list = la.iPsMostVisits(visitsByIp);
    System.out.println(list);
  }

  public static void testDayWithMostIPVisits() {
    LogAnalyzer la = new LogAnalyzer();
    // String relativePath = "files/WebLogProgram/short-test_log";
    // String relativePath = "files/WebLogProgram/weblog1_log";
    String relativePath = "files/WebLogProgram/weblog2_log";
    la.readFile(PARENT_DIR + relativePath);
    HashMap<String, Integer> visitsByIp = la.countVisitsPerIP();
    HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
    String dateString = la.dayWithMostIPVisits(iPsForDays);
    System.out.println(dateString);
  }

  public static void testIPsWithMostVisitsOnDay() {
    LogAnalyzer la = new LogAnalyzer();
    // String relativePath = "files/WebLogProgram/short-test_log";
    // String relativePath = "files/WebLogProgram/weblog1_log";
    String relativePath = "files/WebLogProgram/weblog2_log";

    la.readFile(PARENT_DIR + relativePath);
    HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
    // String testDate = "Mar 17";
    String testDate = "Sep 29";

    ArrayList<String> ips = la.iPsWithMostVisitsOnDay(iPsForDays, testDate);
    System.out.println(ips);
  }

  public static void main(String[] args) {
    // testLogAnalyzer();
    // testUniqueIP();
    // testPrintAllHigherThanNum();
    // testUniqueIPVisitsOnDay();
    // testCountUniqueIPsInRange();
    // testMostNumberVisitsByIP();
    // testIPsMostVisits();
    // testDayWithMostIPVisits();
    testIPsWithMostVisitsOnDay();
  }
}
