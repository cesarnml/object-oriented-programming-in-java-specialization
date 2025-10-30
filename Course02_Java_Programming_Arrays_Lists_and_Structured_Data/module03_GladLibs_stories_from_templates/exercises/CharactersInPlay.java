package Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module03_GladLibs_stories_from_templates.exercises;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
  private static final int MIN_FREQUENCY = 5;
  private static final String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module03_GladLibs_stories_from_templates/";
  private ArrayList<String> characters;
  private ArrayList<Integer> frequency;

  public CharactersInPlay() {
    characters = new ArrayList<String>();
    frequency = new ArrayList<Integer>();
  }

  private void update(String person) {
    if (!characters.contains(person)) {
      characters.add(person);
      frequency.add(1);
    } else {
      int index = characters.indexOf(person);
      int value = frequency.get(index);
      frequency.set(index, value + 1);
    }
  }

  private void findAllCharacters() {
    characters.clear();
    frequency.clear();

    // String filename = PARENT_DIR + "ProgrammingRandomStoryData/macbethSmall.txt";
    // String filename = PARENT_DIR + "ProgrammingRandomStoryData/macbeth.txt";
    // String filename = PARENT_DIR + "PracticeGladLibsData/likeit.txt";
    String filename = PARENT_DIR + "QuizGladLibsData/errors.txt";

    FileResource fr = new FileResource(filename);

    for (String line : fr.lines()) {
      int indexOfDelimiter = line.indexOf(".");
      if (indexOfDelimiter != -1) {
        String currentName = line.substring(0, indexOfDelimiter);
        update(currentName);
      }
    }
  }

  private void tester() {
    findAllCharacters();
    for (int i = 0; i < characters.size(); i++) {
      if (frequency.get(i) > MIN_FREQUENCY) {
        System.out.println(characters.get(i) + "\t" + "shows up " + frequency.get(i));
      }
    }
    charactersWithNumParts(10, 15);
  }

  private void charactersWithNumParts(int num1, int num2) {
    System.out.println("--------NUMBER OF PARTS ----------");
    for (int i = 0; i < frequency.size(); i++) {
      int currentNumOfParts = frequency.get(i);
      if (currentNumOfParts >= num1 && currentNumOfParts <= num2) {
        System.out.println(characters.get(i) + "\tappears\t" + frequency.get(i) + "\ttimes");
      }
    }
  }

  public static void main(String[] args) {
    CharactersInPlay cip = new CharactersInPlay();
    cip.tester();
  }
}
