package Course01_Java_Programming_Solving_Problems_with_Software.module03_strings_in_java.StringsSecondAssignments;

public class Debugging1 {
  public static void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
      if (index == -1) {
        break;
      }
      if (index > input.length() - 4) {
        break;
      }
      String found = input.substring(index + 1, index + 4);
      System.out.println(found);
      index = input.indexOf("abc", index + 4);
    }
  }

  public static void test() {
    // findAbc("abcd");
    findAbc("abcdabc");
  }

  public static void main(String[] args) {
    test();
  }
}
