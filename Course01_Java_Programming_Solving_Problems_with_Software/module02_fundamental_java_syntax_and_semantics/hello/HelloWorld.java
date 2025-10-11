package Course01_Java_Programming_Solving_Problems_with_Software.module02_fundamental_java_syntax_and_semantics.hello;

import edu.duke.*;

/**
 * HelloWorld class that reads from hello_unicode.txt file
 */
public class HelloWorld {
	public static void runHello() {
		String filePath = "./Course01_Java_Programming_Solving_Problems_with_Software/module02_fundamental_java_syntax_and_semantics/hello/hello_unicode.txt";
		FileResource res = new FileResource(filePath);
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}

	public static void main(String[] args) {
		runHello();
	}
}
