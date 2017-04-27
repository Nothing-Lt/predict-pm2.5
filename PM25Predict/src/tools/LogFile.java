package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LogFile {
	
	private String strLogFileName="logfile.txt";
	private final String ENTER="\r\n";
	
	public LogFile()
	{
		File fileLogFile=new File(this.strLogFileName);
		if(!fileLogFile.exists()){
			try {
				fileLogFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void LogError(String strError)
	{
		try {
			RandomAccessFile writer=new RandomAccessFile(this.strLogFileName,"rws");		
			writer.seek(writer.length());
			
			strError+=this.ENTER;
			writer.write(strError.getBytes());
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
