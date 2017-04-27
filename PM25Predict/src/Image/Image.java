package Image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Image {
	String strImageName=null;
	
	public Image(String strImageName)
	{
		this.strImageName=strImageName;
	}
	
	public void DrawImage(double d[],double b[])
	{
		String strd="";
		String strb="";
		for(int i=0;i<d.length;i++)
		{
			strd+=d[i]+" ";
			strb+=b[i]+" ";
		}
		strd+="\r\n";
		try {
			FileOutputStream fileo=new FileOutputStream("c:\\dd");
			fileo.write(strd.getBytes());
			fileo.write(strb.getBytes());
			fileo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*int yPosition=0;
		BufferedImage img=new BufferedImage(1000, 300, BufferedImage.TYPE_INT_RGB);
		
		for(int i=0;i<1000;i++){
			img.setRGB(i, 150, Color.WHITE.getRGB());
		}
		
		for(int i=0;i<365;i++){
			yPosition=(((int)d[i])+400)/4;
			img.setRGB(i*2, yPosition, Color.RED.getRGB());
			img.setRGB(i*2, yPosition+1, Color.RED.getRGB());
			yPosition=(((int)b[i])+400)/4;
			img.setRGB(i*2, yPosition, Color.GREEN.getRGB());
			img.setRGB(i*2, yPosition+1, Color.GREEN.getRGB());
		}
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(this.strImageName);
			JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(fos);
			encoder.encode(img);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImageFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		*/
	}
}
