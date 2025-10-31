# Copilot Instructions for Object-Oriented Programming in Java Specialization

## Project Overview

This is a coursework repository for Duke University's "Object Oriented Programming in Java" Specialization. It contains exercises and mini-projects organized by course and module, progressing from basic Java syntax to advanced data structures.

## Architecture & Organization

### Directory Structure Pattern

```
CourseXX_<CourseName>/
  moduleXX_<module_topic>/
    exercises/          # Practice assignments
    <project_name>/     # Specific project folders
    data/              # Input files (CSV, text, images)
    *.pdf              # Assignment descriptions
```

Each exercise/project is self-contained with its own data files relative to its module directory.

### Package Naming Convention

All Java files use deep package structures that mirror the filesystem:

```java
package Course01_Java_Programming_Solving_Problems_with_Software.module03_strings_in_java.StringsFirstAssignments;
```

This is **required** - the package name must exactly match the directory path from the repository root.

### File Path References

Since the project runs from the repository root, all file paths in code use relative paths from root:

```java
private static String PARENT_DIR = "Course02_Java_Programming_Arrays_Lists_and_Structured_Data/module03_GladLibs_stories_from_templates/";
String filename = PARENT_DIR + "data/madtemplate2.txt";
```

**Never** use file selectors or absolute paths - hardcode relative paths from repository root.

## Duke Education Library (`edu.duke`)

### Core Resource Classes

The `edu/duke/` directory contains custom educational libraries fundamental to all exercises:

- **`FileResource`**: Primary file I/O class. Constructor takes relative path from repository root.

  ```java
  FileResource fr = new FileResource("Course01_../module04_../exports/data.csv");
  for (String line : fr.lines()) { /* process */ }
  for (String word : fr.words()) { /* process */ }
  String content = fr.asString();
  ```

- **`URLResource`**: Same interface as FileResource but for web URLs
- **`DirectoryResource`**: Iterate over files in a directory
- **`ImageResource`**: Read/manipulate images pixel-by-pixel
- **`StorageResource`**: ArrayList wrapper for storing strings

### CSV Processing

Uses Apache Commons CSV via `FileResource.getCSVParser()`:

```java
FileResource fr = new FileResource(filePath);
CSVParser parser = fr.getCSVParser();
for (CSVRecord record : parser) {
    String value = record.get("ColumnName");
}
```

**Important**: Create a new parser for each iteration - parsers are single-use.

## Code Conventions

### Testing Pattern

Most classes follow this pattern:

```java
public class ClassName {
    // Instance fields and methods

    private static void testMethodName() {
        // Test code here - creates instances and calls methods
    }

    public static void main(String[] args) {
        testMethodName();
    }
}
```

Some use `tester()` method names instead of specific test names. `main()` always calls the test method(s).

### Common Patterns

1. **String processing**: Extensive use of `indexOf()`, `substring()`, `toLowerCase()`, `contains()`
2. **Collections**:
   - `ArrayList<String>` for ordered collections
   - `HashMap<String, ArrayList<String>>` for category-based data
   - `HashSet<String>` for tracking uniqueness
3. **Data validation**: Use of `null` checks, especially with CSVRecord fields
4. **Iteration**: Enhanced for loops (`for (Type item : collection)`) preferred over index-based loops

### Static Constants

Declare file paths and configuration as `private static final` constants at class top:

```java
private static final String PARENT_DIR = "Course02_.../module03_.../";
private static final int CODON_LENGTH = 3;
```

## Running Code

### Execution Context

- **Working Directory**: Always the repository root (`/Users/cesar/coursera/object-oriented-programming-in-java-specialization`)
- **Classpath**: Repository root
- **Java Version**: Java 17 (via SDKMAN)

### Run Commands

Execute using fully qualified class names:

```bash
java -cp . Course02_Java_Programming_Arrays_Lists_and_Structured_Data.module03_GladLibs_stories_from_templates.exercises.WordFrequencies
```

The IDE (BlueJ/VS Code) handles this, but when debugging classpath issues, ensure:

1. Package declaration matches directory structure exactly
2. File paths are relative to repository root
3. Running from repository root directory

## Key Anti-Patterns to Avoid

- ❌ Don't use `FileSelector` dialogs - hardcode paths
- ❌ Don't reuse CSVParser objects - create fresh for each iteration
- ❌ Don't use absolute paths - always relative from repository root
- ❌ Don't skip package declarations - they must mirror directory structure
- ❌ Don't use separate test frameworks - use `tester()` or `testXxx()` static methods

## Data Flow Examples

Typical exercise workflow:

1. Read data file using `FileResource` with hardcoded relative path
2. Process line-by-line or word-by-word using enhanced for loops
3. Store results in `ArrayList` or `HashMap`
4. Output results via `System.out.println()` in test methods
5. Validate against expected output mentioned in PDF assignment descriptions
