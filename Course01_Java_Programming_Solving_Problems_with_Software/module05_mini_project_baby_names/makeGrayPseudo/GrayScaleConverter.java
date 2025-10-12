/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
package Course01_Java_Programming_Solving_Problems_with_Software.module05_mini_project_baby_names.makeGrayPseudo;

import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
	// I started with the image I wanted (inImage)
	public static ImageResource makeGray(ImageResource inImage) {
		// I made a blank image of the same size

		// for each pixel in outImage

		// look at the corresponding pixel in inImage

		// compute inPixel's red + inPixel's blue + inPixel's green
		// divide that sum by 3 (call it average)

		// set pixel's red to average

		// set pixel's green to average

		// set pixel's blue to average

		// outImage is your answer
	}

	public static void testGray() {
		ImageResource ir = new ImageResource();
		ImageResource gray = makeGray(ir);
		gray.draw();
	}

	public static void main(String[] args) {
		testGray();
	}
}
