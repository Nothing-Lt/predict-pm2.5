package Http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import tools.LogFile;

public class Download {
	
	private String strUrl=null;
	private String strFileName=null;
	
	public Download(String strUrl,String strFileName)
	{
		this.strUrl=strUrl;
		this.strFileName=strFileName;
	}
	
	@SuppressWarnings("finally")
	public boolean run()
	{
		boolean bReturnValue=false;
		try {
			System.out.println("Start to get page "+this.strUrl);
			
			Get g=new Get(this.strUrl,Http.TIMEOUT);
			InputStream input=g.run();
			if(null==input){
				return bReturnValue;
			}
			
			FileOutputStream output=new FileOutputStream(this.strFileName);
			byte buff[]=new byte[1024];
			int length=-1;
			while((length=input.read(buff))!=-1)
			{
				System.out.println("Readed length:"+length);
				output.write(buff,0,length);
			}
			output.close();
			input.close();
			bReturnValue=true;
			System.out.println("Page Downloading has complite!");
			System.out.println("Saved into file:"+this.strFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogFile logfile=new LogFile();
			logfile.LogError("[In Http.Download Error with download page]"+this.strUrl);
		}
		finally{
			return bReturnValue;
		}
	}
	
}
