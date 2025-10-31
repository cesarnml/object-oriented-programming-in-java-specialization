package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module04_WebServerLogs_from_logs_to_visits.files.WebLogProgram;

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import edu.duke.FileResource;

public class LogAnalyzer {
  private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd", Locale.US);
  private ArrayList<LogEntry> records;

  public LogAnalyzer() {
    records = new ArrayList<LogEntry>();
  }

  public void readFile(String filename) {
    FileResource fr = new FileResource(filename);
    for (String line : fr.lines()) {
      records.add(WebLogParser.parseEntry(line));
    }
  }

  public int countUniqueIPs() {
    ArrayList<String> uniqueIPs = new ArrayList<String>();
    for (LogEntry le : records) {
      String ip = le.getIpAddress();
      if (!uniqueIPs.contains(ip)) {
        uniqueIPs.add(ip);
      }
    }
    return uniqueIPs.size();
  }

  public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
    ArrayList<String> uniqueIPs = new ArrayList<String>();

    for (LogEntry le : records) {
      String currentDate = dateFormatter.format(le.getAccessTime());
      String currentIP = le.getIpAddress();
      if (currentDate.equals(someday) && !uniqueIPs.contains(currentIP)) {
        uniqueIPs.add(currentIP);
      }
    }
    return uniqueIPs;
  }

  public int countUniqueIPsInRange(int low, int high) {
    ArrayList<String> uniqueIPs = new ArrayList<>();
    for (LogEntry le : records) {
      int statusCode = le.getStatusCode();
      String ip = le.getIpAddress();
      if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ip)) {
        uniqueIPs.add(ip);
      }
    }
    return uniqueIPs.size();
  }

  public void printAllHigherThanNum(int num) {
    for (LogEntry le : records) {
      if (le.getStatusCode() > num) {
        System.out.println(le);
      }
    }
  }

  public void printAll() {
    for (LogEntry le : records) {
      System.out.println(le);
    }
  }
}
