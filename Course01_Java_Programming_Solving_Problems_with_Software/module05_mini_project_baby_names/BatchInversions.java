package Course01_Java_Programming_Solving_Problems_with_Software.module05_mini_project_baby_names;

import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

public class BatchInversions {
  private static int RGB_MAX = 255;

  /**
   * 
   * @param colorValue RGB value within range 0-255 to invert
   * @return inverted value within 0-255
   */
  private static int invertColorValue(int colorValue) {
    return RGB_MAX - colorValue;
  }

  /**
   * 
   * @param inImage the ImageResource to invert
   * @return an inverted ImageResource
   */
  public static ImageResource makeInversion(ImageResource inImage) {
    ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
    System.out.println("Inverting image: " + inImage.getFileName());
    for (Pixel pixel : inImage.pixels()) {
      Pixel outPixel = outImage.getPixel(pixel.getX(), pixel.getY());
      outPixel.setRed(invertColorValue(pixel.getRed()));
      outPixel.setGreen(invertColorValue(pixel.getGreen()));
      outPixel.setBlue(invertColorValue(pixel.getBlue()));
    }
    return outImage;
  }

  public static void selectAndConvert() {
    int count = 0;
    String parentDir = "";
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      parentDir = f.getParent();
      ImageResource ir = new ImageResource(f);
      ImageResource invertedImage = makeInversion(ir);
      String fileName = ir.getFileName();
      invertedImage.setFileName(parentDir + "/" + "inverted-" + fileName);
      invertedImage.save();
      count += 1;
    }
    System.out.println(count == 0 ? "No images saved" : "Saved: " + count + " images to " + parentDir);
  }

  public static void main(String[] args) {
    selectAndConvert();
  }
}
