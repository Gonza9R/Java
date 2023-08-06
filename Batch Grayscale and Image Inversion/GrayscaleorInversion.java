import edu.duke.*;
import java.io.*;

public class GrayscaleorInversion {
    public ImageResource GrayFilter(ImageResource inImage){
        ImageResource outImage= new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel p: outImage.pixels()){
            Pixel inPixel=inImage.getPixel(p.getX(),p.getY());
            int average=(inPixel.getRed()+inPixel.getGreen()+inPixel.getBlue())/3;
            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
        
        return outImage;
    }
    public void BatchGrayImages() {
        DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		ImageResource image = new ImageResource(f);
		String fname = image.getFileName();
		String newName = "gray-" + fname;
		image=GrayFilter(image);
		image.setFileName(newName);
		//image.draw();
		image.save();
	}
     }
    
    public ImageResource InversionFilter(ImageResource inImage){
        ImageResource outImage= new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel p: outImage.pixels()){
            Pixel inPixel=inImage.getPixel(p.getX(),p.getY());
            p.setRed(255-inPixel.getRed());
            p.setGreen(255-inPixel.getGreen());
            p.setBlue(255-inPixel.getBlue());
        }
        
        return outImage;
    }
    public void BatchInversionsImages() {
        DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		ImageResource image = new ImageResource(f);
		String fname = image.getFileName();
		String newName = "Inverted-" + fname;
		image=InversionFilter(image);
		image.setFileName(newName);
		image.draw();
		image.save();
	}
     }
}
