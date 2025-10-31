package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module04_WebServerLogs_from_logs_to_visits.files.WebLogProgram;

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

  public HashMap<String, Integer> countVisitsPerIP() {
    HashMap<String, Integer> visitsByIP = new HashMap<>();
    for (LogEntry le : records) {
      String ip = le.getIpAddress();
      visitsByIP.put(ip, visitsByIP.getOrDefault(ip, 0) + 1);
    }
    return visitsByIP;
  }

  public int mostNumberVisitsByIP(HashMap<String, Integer> visitsByIP) {
    int maxCount = 0;
    for (String key : visitsByIP.keySet()) {
      int count = visitsByIP.get(key);
      if (count > maxCount) {
        maxCount = count;
      }
    }
    return maxCount;
  }

  public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visitsByIP) {
    int maxCount = mostNumberVisitsByIP(visitsByIP);
    ArrayList<String> ipList = new ArrayList<>();

    for (String key : visitsByIP.keySet()) {
      int count = visitsByIP.get(key);
      if (count == maxCount) {
        ipList.add(key);
      }
    }
    return ipList;
  }

  public HashMap<String, ArrayList<String>> iPsForDays() {
    HashMap<String, ArrayList<String>> iPsForDays = new HashMap<>();

    for (LogEntry le : records) {
      String dateString = dateFormatter.format(le.getAccessTime());
      String ip = le.getIpAddress();

      if (!iPsForDays.containsKey(dateString)) {
        iPsForDays.put(dateString, new ArrayList<String>());
      }
      ArrayList<String> iPs = iPsForDays.get(dateString);
      iPs.add(ip);
    }

    return iPsForDays;
  }

  public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays) {
    String dayWithMostVisits = "";
    int maxUniqueVisits = 0;
    for (String key : iPsForDays.keySet()) {
      int count = iPsForDays.get(key).size();
      if (count > maxUniqueVisits) {
        maxUniqueVisits = count;
        dayWithMostVisits = key;
      }
    }
    return dayWithMostVisits;
  }

  public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String date) {
    ArrayList<String> ips = new ArrayList<>();
    ArrayList<String> iPsOnDate = iPsForDays.get(date);
    HashMap<String, Integer> visitsByIp = new HashMap<>();

    for (String ip : iPsOnDate) {
      visitsByIp.put(ip, visitsByIp.getOrDefault(ip, 0) + 1);
    }

    int maxVisits = mostNumberVisitsByIP(visitsByIp);

    for (String key : visitsByIp.keySet()) {
      int count = visitsByIp.get(key);
      if (count == maxVisits) {
        ips.add(key);
      }
    }

    return ips;
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
