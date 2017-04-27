package Http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import tools.LogFile;

public class Get {
	
	private String strUrl;
	private int iTimeout;
	
	public Get(String strUrl,int iTimeout)
	{
		this.strUrl=strUrl;
		this.iTimeout=iTimeout;
	}
	
	@SuppressWarnings("finally")
	public InputStream run()
	{
		InputStream inputStream=null;
		try{
			URL url=new URL(this.strUrl);
			HttpURLConnection con=(HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("GET");
			con.setConnectTimeout(this.iTimeout);
			con.connect();
			
			int iErrorCode=con.getResponseCode();
			if(200!=iErrorCode)
			{
				System.out.println("Get page "+this.strUrl+"Error");
				System.out.println("Error code "+iErrorCode);
			}
			else{
				inputStream=con.getInputStream();
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogFile logfile=new LogFile();
			logfile.LogError("[In Http.Get Error with get page] "+this.strUrl);
		}
		finally{
			return inputStream;
		}
	}
}
