# External Libraries

This directory contains external JAR files required for the project.

## Required Libraries for Course 3 (Object-Oriented Programming)

### Processing

- **Files Needed**: 
  - `core.jar` (required)
  - `gluegen-rt.jar` (optional, for OpenGL support)
  - `jogl-all.jar` (optional, for OpenGL support)
- **Download**: [Processing Downloads](https://processing.org/download)
- **Description**: Processing core library for graphics and GUI
- **Installation**:
  1. Download and install the Processing IDE (the JARs are not available separately)
  2. After installation, locate the Processing installation directory:
     - **macOS**: `/Applications/Processing.app/Contents/Java/core/library/`
     - **Windows**: `C:\Program Files\processing-<version>\core\library\`
     - **Linux**: `/opt/processing-<version>/core/library/` or `~/processing-<version>/core/library/`
  3. Copy `core.jar` (and optionally the OpenGL JARs) to this `lib/` directory
  
  **Alternative**: You can also find the JARs in Processing's application folder after launching it once.

### Unfolding Maps

- **Files**:
  - `unfolding-maps.jar` (or `Unfolding.jar`)
  - Additional dependencies may be bundled
- **Download**: 
  - [Unfolding Maps Releases](http://unfoldingmaps.org/downloads/)
  - Or via Processing IDE: Sketch → Import Library → Add Library → Search "Unfolding Maps"
- **Description**: Library for interactive maps and geo-visualizations
- **Installation**:
  
  **Option 1 - Direct Download:**
  1. Download the Unfolding Maps library from the website
  2. Extract the archive
  3. Copy all JAR files from the `lib/` folder to this project's `lib/` directory
  
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
