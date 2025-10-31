package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module04_WebServerLogs_from_logs_to_visits.files.WebLogProgram;

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
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

  public void printAll() {
    for (LogEntry le : records) {
      System.out.println(le);
    }
  }
}
