package module03_strings_in_java.StringsSecondAssignments;

public class Part2 {
  // Write howMany(String stringA, String stringB)
  public static int howMany(String stringA, String stringB) {
    int currentIndex = 0;
    int count = 0;
    while (true) {
      int matchIndex = stringB.indexOf(stringA, currentIndex);
      if (matchIndex == -1) {
        return count;
      } else {
        count += 1;
        currentIndex = matchIndex + stringA.length();
      }
    }
  }

  public static void main(String[] args) {
    int count1 = howMany("GAA", "ATGAACGAATTGAATC");
    System.out.println(count1);
    int count2 = howMany("AA", "ATAAAA");
    System.out.println(count2);
  }
}
