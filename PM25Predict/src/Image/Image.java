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
		
	}
}
