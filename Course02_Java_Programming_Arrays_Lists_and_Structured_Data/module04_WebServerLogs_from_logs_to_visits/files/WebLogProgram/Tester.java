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

  public static void main(String[] args) {
    testLogAnalyzer();
  }
}
