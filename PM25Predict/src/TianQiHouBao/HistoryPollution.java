package TianQiHouBao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Calendar;

import Http.Download;
import Object.Weather;
import tools.LogFile;

public class HistoryPollution {
	
	String strCityName;
	String strFileName;
	String strDateLog;
	String strYear="";
	String strMonth="";
	String strDay="";
	String strUrl;
	
	public HistoryPollution(String strCityName,Calendar c)
	{
		this.strCityName=strCityName;
		
		this.strYear+=c.get(Calendar.YEAR);
		int iMonth=c.get(Calendar.MONTH)+1;
		int iDate=c.get(Calendar.DATE);
		if(10>iMonth){
			this.strMonth="0";
		}
		if(10>iDate){
			this.strDay="0";
		}
		
		this.strMonth+=iMonth;
		this.strDay+=c.get(Calendar.DAY_OF_MONTH);
		this.strDateLog=this.strYear+"-"+this.strMonth+"-"+this.strDay;

		String strDate=this.strYear+this.strMonth;
		this.strUrl=TianQiHouBao.HOST+
				  	this.strCityName+"-"+
				  	strDate+TianQiHouBao.HTML;
		this.strFileName=this.strCityName+strDate+"pollution";
		System.out.println(strFileName);
	}
	
	public boolean DownloadPollutionPage()
	{
		
		File f=new File(this.strFileName);
		if(f.exists()){
			return true;
		}
		
		System.out.println(this.strUrl);
		Download d=new Download(strUrl, this.strFileName);
		d.run();
		return true;
	}
	
	
	@SuppressWarnings("finally")
	public Weather GetPollutionFromFile(Weather w)
	{
		
		String str=null;
		try {
			FileReader fr=new FileReader(this.strFileName);
			LineNumberReader lnr=new LineNumberReader(fr);
			
			while(null!=(str=lnr.readLine())){
				if(-1!=(str.indexOf(this.strDateLog))){
					break;
				}
			}
			if(null!=str){
				System.out.println("Finede amazing");
				w=this.GetPollutionFromLine(lnr, w);
			}
			lnr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogFile logfile=new LogFile();
			logfile.LogError("[In HistoryPollution.GetPollutionFromFile file NOT FOUND]"+this.strFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogFile logfile=new LogFile();
			logfile.LogError("[In HistoryPollution.GetPollutionFromFile Error with read file]"+this.strFileName);
		}
		finally{
			return w;
		}
	}
	
	@SuppressWarnings("finally")
	private Weather GetPollutionFromLine(LineNumberReader lnr,Weather w)
	{
		String str=null;
		int iStartIndex=-1;
		int iEndIndex=-1;
		try {
			lnr.readLine();
			lnr.readLine();
			lnr.readLine();
			lnr.readLine();
			str=lnr.readLine();
			if(null==str){
				return w;
			}
	
			for(int i=0;i<str.length();i++){
				if(' '!=str.charAt(i)){
					iStartIndex=i;
					break;
				}
			}
			iEndIndex=str.indexOf("</td>");
			if(-1==iStartIndex||-1==iEndIndex){
				return w;
			}
			str=str.substring(iStartIndex, iEndIndex);
			w.setAqi(Double.valueOf(str));
			
			lnr.readLine();
			str=lnr.readLine();
			if(null==str){
				return w;
			}
			iStartIndex=str.indexOf("<td>");
			iEndIndex=str.indexOf("</td>");
			if(-1==iStartIndex||-1==iEndIndex){
				return w;
			}
			str=str.substring(iStartIndex+4,iEndIndex);
			w.setPM25(Double.valueOf(str));
			
			str=lnr.readLine();
			if(null==str){
				return w;
			}
			iStartIndex=str.indexOf("<td>");
			iEndIndex=str.indexOf("</td>");
			if(-1==iStartIndex||-1==iEndIndex){
				return w;
			}
			str=str.substring(iStartIndex+4,iEndIndex);
			w.setPM10(Double.valueOf(str));
			
			lnr.readLine();
			str=lnr.readLine();
			if(null==str){
				return w;
			}
			iStartIndex=str.indexOf("<td>");
			iEndIndex=str.indexOf("</td>");
			if(-1==iStartIndex||-1==iEndIndex){
				return w;
			}
			str=str.substring(iStartIndex+4,iEndIndex);
			w.setNo2(Double.valueOf(str));
			
			str=lnr.readLine();
			if(null==str){
				return w;
			}
			iStartIndex=str.indexOf("<td>");
			iEndIndex=str.indexOf("</td>");
			if(-1==iStartIndex||-1==iEndIndex){
				return w;
			}
			str=str.substring(iStartIndex+4,iEndIndex);
			w.setSo2(Double.valueOf(str));
			
			str=lnr.readLine();
			if(null==str){
				return w;
			}
			iStartIndex=str.indexOf("<td>");
			iEndIndex=str.indexOf("</td>");
			if(-1==iStartIndex||-1==iEndIndex){
				return w;
			}
			str=str.substring(iStartIndex+4,iEndIndex);
			w.setCo(Double.valueOf(str));
			
			str=lnr.readLine();
			if(null==str){
				return w;
			}
			iStartIndex=str.indexOf("<td>");
			iEndIndex=str.indexOf("</td>");
			if(-1==iStartIndex||-1==iEndIndex){
				return w;
			}
			str=str.substring(iStartIndex+4,iEndIndex);
			w.setO3(Double.valueOf(str));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogFile logfile=new LogFile();
			logfile.LogError("[In HistoryPollution.GetPollutionFromLine Error with read file]"+this.strFileName);
		}
		finally{
			return w;
		}
	}
}
