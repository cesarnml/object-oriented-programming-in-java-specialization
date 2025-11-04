# External Libraries

This directory contains external JAR files required for the project.

## Required Libraries for Course 3 (Object-Oriented Programming)

### ⚠️ Important Compatibility Notice

**Unfolding Maps 0.9.6 requires Processing 2.2.1**. It is **NOT compatible** with Processing 3.x or 4.x.

- **Unfolding Maps**: v0.9.6 (latest as of 2014)
- **Processing**: v2.2.1 (required)
- **Java**: JDK 7 or 8 recommended for Processing 2.2.1

The Unfolding Maps library has not been updated for Processing 3 or 4, so you must use the older Processing 2.2.1 version.

### Processing 2.2.1

- **Version**: 2.2.1 (specifically required for Unfolding Maps)
- **Files Needed**: `core.jar`
- **Download**: [Processing 2.2.1 Archive](https://github.com/processing/processing/releases?page=5)
  - Or search for "Processing 2.2.1" old releases
- **Description**: Processing core library for graphics and GUI (version 2.x)
- **Installation**:

  1. Download Processing 2.2.1 (NOT the latest version 4.x)
  2. After installation, locate the Processing installation directory:
     - **macOS**: `/Applications/Processing.app/Contents/Java/core/library/`
     - **Windows**: `C:\Program Files\processing-2.2.1\core\library\`
     - **Linux**: `/opt/processing-2.2.1/core/library/` or `~/processing-2.2.1/core/library/`
  3. Copy `core.jar` to this `lib/` directory

### Unfolding Maps 0.9.6

- **Version**: 0.9.6 (for Processing 2.2.1 only)
- **Files**:
  - `Unfolding.jar` (main library)
  - Additional dependencies bundled in the download
- **Download**:
  - [Unfolding Maps 0.9.6 for Processing 2](https://github.com/tillnagel/unfolding/releases/download/v0.9.6/Unfolding_for_processing_0.9.6.zip)
  - Or via Processing 2 IDE: Sketch → Import Library → Add Library → Search "Unfolding Maps"
- **Description**: Library for interactive maps and geo-visualizations (last updated 2014)
- **Installation**:

  **Option 1 - Direct Download:**

  1. Download Unfolding Maps 0.9.6 from the GitHub releases link above
  2. Extract the ZIP archive
  3. Copy all JAR files from the `Unfolding/library/` folder to this project's `lib/` directory

  **Option 2 - Via Processing IDE:**

  1. Open Processing IDE
  2. Go to Sketch → Import Library → Add Library
  3. Search for "Unfolding Maps" and click Install
  4. After installation, find the JARs in Processing's libraries folder:
     - **macOS**: `~/Documents/Processing/libraries/unfoldingmaps/library/`
     - **Windows**: `Documents\Processing\libraries\unfoldingmaps\library\`
     - **Linux**: `~/sketchbook/libraries/unfoldingmaps/library/`
  5. Copy the JAR files to this project's `lib/` directory

### Additional Dependencies

Some Unfolding Maps features may require:

- JSON processing libraries
- HTTP libraries for tile loading

## Usage

### Compiling with Libraries

```bash
javac -cp ".:lib/*" YourClass.java
```

### Running with Libraries

```bash
java -cp ".:lib/*" YourClass
```

### From Repository Root

Since this project runs from the repository root, use:

```bash
javac -cp ".:lib/*" Course03_Object_Oriented_Programming_in_Java/module03_.../YourClass.java
java -cp ".:lib/*" Course03_Object_Oriented_Programming_in_Java.module03_....YourClass
```

## Current Libraries in lib/

**Duke Course Libraries (Included):**

- [x] `apache-csv.jar` - Apache Commons CSV for parsing CSV files
- [x] `courserajava.jar` - Duke's custom Java library (edu.duke.\*)

**Course 3 Libraries (Required - Download separately):**

- [ ] `core.jar` (Processing)
- [ ] `unfolding-maps.jar`
- [ ] Any additional Unfolding Maps dependencies

### Important Note about edu/ and org/ directories

The `edu/` and `org/` directories at the repository root contain **extracted** versions of the classes from `courserajava.jar` and `apache-csv.jar`.

**Recommendation:** These directories are **redundant** since the JAR files (now in `lib/`) already contain both the source (.java) and compiled (.class) files. You can safely remove the `edu/` and `org/` directories to avoid classpath conflicts:

```bash
# Optional cleanup - removes redundant extracted directories
rm -rf edu/ org/
```

All imports like `import edu.duke.*` and `import org.apache.commons.csv.*` will continue to work from the JARs in `lib/`.
