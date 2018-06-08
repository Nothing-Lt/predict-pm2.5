package WeatherUnderground;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Http.Download;
import Object.Weather;
import tools.LogFile;

public class HistoryWeather {
	
	private String strWeatherUrl=null;
	private String strCityName=null;
	private String strCountryName=null;
	private String strDate=null;
	private String strFileName=null;
	private Calendar calendar=null;
	
	public HistoryWeather(String strCityName,String strCountryName ,Calendar c)
	{
		this.strCityName=strCityName;
		this.strCountryName=strCountryName;
		this.calendar=c;
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
		this.strDate=dateFormat.format(c.getTime());
		
		strWeatherUrl=WeatherUnderground.HOST+this.strDate+"/"+
				      WeatherUnderground.HTML+"?"+
					  WeatherUnderground.CITY_NAME+"="+this.strCityName+"&"+
					  WeatherUnderground.COUNTRY_NAME+"="+this.strCountryName+"&"+
					  WeatherUnderground.DB_ZIP+"="+WeatherUnderground.DB_ZIP_NUM+"&"+
					  WeatherUnderground.DB_MAGIC+"="+WeatherUnderground.DB_MAGIC_NUM+"&"+
					  WeatherUnderground.DB_WMO+"="+WeatherUnderground.DB_WMO_NUM;
		this.strFileName=this.strCityName+(new SimpleDateFormat("yyyyMMdd")).format(c.getTime())+"weather";
		
		System.out.println(strWeatherUrl);
	}
	
	public boolean DownloadWeatherPage()
	{
		File f=new File(this.strFileName);
		if(f.exists()){
			return true;
		}
	
		Download d=new Download(this.strWeatherUrl,this.strFileName);
		d.run();
		return true;
	}
	
	@SuppressWarnings("finally")
	public Weather GetWeatherObjectFromFile()
	{
		Weather w=null;
		w=new Weather();
		w.setStrDate(this.strDate);
		w.setStrLocation(strCityName);
		return w;
	}
	
	private Weather GetWeatherObjectLine_ErrorVersion(LineNumberReader lnr)
	{
		String str=null;
		int hour=0;
		int iStartIndex;
		int iEndIndex;
		double dblDewPoint[] =new double[WeatherUnderground.DATA_NUMBER];
		double dblTemp[]     =new double[WeatherUnderground.DATA_NUMBER];
		double dblPressure[] =new double[WeatherUnderground.DATA_NUMBER];
		double dblEyeSight[] =new double[WeatherUnderground.DATA_NUMBER];
		double dblWindSpeed[]=new double[WeatherUnderground.DATA_NUMBER];
		double dblSweat[]    =new double[WeatherUnderground.DATA_NUMBER];
		
		double MaxDewPoint =Double.MIN_VALUE, MinDewPoint =Double.MAX_VALUE, MeanDewPoint =0, SumDewPoint =0;
		double MaxTemp     =Double.MIN_VALUE, MinTemp     =Double.MAX_VALUE, MeanTemp     =0, SumTemp     =0;
		double MaxPressure =Double.MIN_VALUE, MinPressure =Double.MAX_VALUE, MeanPressure =0, SumPressure =0;
		double MaxEyeSight =Double.MIN_VALUE, MinEyeSight =Double.MAX_VALUE, MeanEyeSight =0, SumEyeSight =0;
		double MaxWindSpeed=Double.MIN_VALUE, MinWindSpeed=Double.MAX_VALUE, MeanWindSpeed=0, SumWindSpeed=0;
		double MaxSweat	   =Double.MIN_VALUE, MinSweat    =Double.MAX_VALUE, MeanSweat    =0, SumSweat    =0;
		
		try {
			while((str=lnr.readLine())!=null&&hour<48)
			{
				//A beginning of a hourly data
				if(str.equals("\t\t<tr class=\"no-metars\">"))
				{
					
					//get temprature
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					if(this.checkNull(str)){
						return null;
					}
					iStartIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_BEGIN);
					iEndIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_END);
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+45, iEndIndex);
						dblTemp[hour]=this.valueOf(str);
					}
					else{
						dblTemp[hour]=Double.MIN_VALUE;
					}
					
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					if(this.checkNull(str)){
						return null;
					}
					iStartIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_BEGIN);
					iEndIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_END);
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+45, iEndIndex);
						dblDewPoint[hour]=this.valueOf(str);
					}
					else{
						dblDewPoint[hour]=Double.MIN_VALUE;
					}
					
					//get sweat
					str=lnr.readLine();
					str=lnr.readLine();
					iStartIndex=str.indexOf("<td >");
					iEndIndex=str.indexOf("%");
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+5, iEndIndex);
						dblSweat[hour]=this.valueOf(str);
					}
					else{
						dblSweat[hour]=Double.MIN_VALUE;
					}
					
					//get pressure
					str=lnr.readLine();
					str=lnr.readLine();
					if(this.checkNull(str)){
						return null;
					}
					iStartIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_BEGIN);
					iEndIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_END);
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+45, iEndIndex);
						dblPressure[hour]=this.valueOf(str);
					}
					else{
						dblPressure[hour]=Double.MIN_VALUE;
					}
					
					//get EyeSight
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					if(this.checkNull(str)){
						return null;
					}
					iStartIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_BEGIN);
					iEndIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_END);
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+45, iEndIndex);
						dblEyeSight[hour]=this.valueOf(str);
					}
					else{
						dblEyeSight[hour]=Double.MIN_VALUE;
					}
					
					//get WindSpeed
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					if(this.checkNull(str)){
						return null;
					}
					iStartIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_BEGIN);
					iEndIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_END);
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+45, iEndIndex);
						dblWindSpeed[hour]=this.valueOf(str);
					}
					else{
						dblWindSpeed[hour]=Double.MIN_VALUE;
					}
					hour++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogFile logfile=new LogFile();
			logfile.LogError("[In WeatherUnderground.HistoryWeather error with read file]"+this.strFileName);
		}
		
		this.fixData(dblSweat);
		this.fixData(dblPressure);
		this.fixData(dblDewPoint);
		this.fixData(dblEyeSight);
		this.fixData(dblWindSpeed);
		this.fixData(dblTemp);
		
		for(int i=0;i<hour;i++)
		{
			SumDewPoint+=dblDewPoint[i];
			if(MaxDewPoint<dblDewPoint[i]){
				MaxDewPoint=dblDewPoint[i];
			}
			else if(MinDewPoint>dblDewPoint[i]){
				MinDewPoint=dblDewPoint[i];
			}
			
			SumTemp+=dblTemp[i];
			if(MaxTemp<dblTemp[i]){
				MaxTemp=dblTemp[i];
			}else if(MinTemp>dblTemp[i]){
				MinTemp=dblTemp[i];
			}
			
			SumPressure+=dblPressure[i];
			if(MaxPressure<dblPressure[i]){
				MaxPressure=dblPressure[i];
			}else if(MinPressure>dblPressure[i]){
				MinPressure=dblPressure[i];
			}
			
			//System.out.println(dblEyeSight[i]);
			SumEyeSight+=dblEyeSight[i];
			if(MaxEyeSight<dblEyeSight[i]){
				MaxEyeSight=dblEyeSight[i];
			}else if(MinEyeSight>dblEyeSight[i]){
				MinEyeSight=dblEyeSight[i];
			}
			
			SumWindSpeed+=dblWindSpeed[i];
			if(MaxWindSpeed<dblWindSpeed[i]){
				MaxWindSpeed=dblWindSpeed[i];
			}else if(MinWindSpeed>dblWindSpeed[i]){
				MinWindSpeed=dblWindSpeed[i];
			}
			
			SumSweat+=dblSweat[i];
			if(MaxSweat<dblSweat[i]){
				MaxSweat=dblSweat[i];
			}else if(MinSweat>dblSweat[i]){
				MinSweat=dblSweat[i];
			}
			
		}
		MeanDewPoint =SumDewPoint /48;
		MeanTemp     =SumTemp     /48;
		MeanPressure =SumPressure /48;
		MeanWindSpeed=SumWindSpeed/48;
		MeanSweat    =SumSweat    /48;
		MeanEyeSight =SumEyeSight /48;
		
		Weather w=new Weather();
		w.setStrDate(this.strDate);
		w.setStrLocation(strCityName);
		w.setMaxTemp_C(MaxTemp);
		w.setMinTemp_C(MinTemp);
		w.setAverrageTemp(MeanTemp);
		
		w.setMinDewPoint(MinDewPoint);
		w.setMeanDewPoint(MeanDewPoint);
		
		w.setMaxEyeSight_KM(MaxEyeSight);
		w.setMinEyeSight_KM(MinEyeSight);
		w.setMeanEyeSight_KM(MeanEyeSight);
		
		w.setMaxPressure_hPa(MaxPressure);
		w.setMinPressure_hPa(MinPressure);
		w.setMeanPressure_hPa(MeanPressure);
		
		w.setMaxWindSpeed_KM(MaxWindSpeed);
		w.setMeanWindSpeed_KM(MeanWindSpeed);
		
		w.setMaxSweat(MaxSweat);
		w.setMinSweat(MinSweat);
		w.setMeanSweat(MeanSweat);

		return w;
	}
	private Weather GetWeatherObjectLine(LineNumberReader lnr)
	{
		String str=null;
		int hour=0;
		int iStartIndex;
		int iEndIndex;
		double dblDewPoint[] =new double[WeatherUnderground.DATA_NUMBER];
		double dblTemp[]     =new double[WeatherUnderground.DATA_NUMBER];
		double dblPressure[] =new double[WeatherUnderground.DATA_NUMBER];
		double dblEyeSight[] =new double[WeatherUnderground.DATA_NUMBER];
		double dblWindSpeed[]=new double[WeatherUnderground.DATA_NUMBER];
		double dblSweat[]    =new double[WeatherUnderground.DATA_NUMBER];
		
		double MaxDewPoint =Double.MIN_VALUE, MinDewPoint =Double.MAX_VALUE, MeanDewPoint =0, SumDewPoint =0;
		double MaxTemp     =Double.MIN_VALUE, MinTemp     =Double.MAX_VALUE, MeanTemp     =0, SumTemp     =0;
		double MaxPressure =Double.MIN_VALUE, MinPressure =Double.MAX_VALUE, MeanPressure =0, SumPressure =0;
		double MaxEyeSight =Double.MIN_VALUE, MinEyeSight =Double.MAX_VALUE, MeanEyeSight =0, SumEyeSight =0;
		double MaxWindSpeed=Double.MIN_VALUE, MinWindSpeed=Double.MAX_VALUE, MeanWindSpeed=0, SumWindSpeed=0;
		double MaxSweat	   =Double.MIN_VALUE, MinSweat    =Double.MAX_VALUE, MeanSweat    =0, SumSweat    =0;
		
		try {
			while((str=lnr.readLine())!=null&&hour<48)
			{
				//A beginning of a hourly data
				if(str.equals("\t\t<tr class=\"no-metars\">"))
				{
					
					//get temprature
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					if(this.checkNull(str)){
						return null;
					}
					iStartIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_BEGIN);
					iEndIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_END);
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+45, iEndIndex);
						dblTemp[hour]=this.valueOf(str);
					}
					else{
						dblTemp[hour]=Double.MIN_VALUE;
					}
					
					//get dewpoint 
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					if(this.checkNull(str)){
						return null;
					}
					iStartIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_BEGIN);
					iEndIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_END);
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+45, iEndIndex);
						dblDewPoint[hour]=this.valueOf(str);
					}
					else{
						dblDewPoint[hour]=Double.MIN_VALUE;
					}
					
					//get sweat
					str=lnr.readLine();
					str=lnr.readLine();
					iStartIndex=str.indexOf("<td >");
					iEndIndex=str.indexOf("%");
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+5, iEndIndex);
						dblSweat[hour]=this.valueOf(str);
					}
					else{
						dblSweat[hour]=Double.MIN_VALUE;
					}
					
					//get pressure
					str=lnr.readLine();
					str=lnr.readLine();
					if(this.checkNull(str)){
						return null;
					}
					iStartIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_BEGIN);
					iEndIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_END);
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+45, iEndIndex);
						dblPressure[hour]=this.valueOf(str);
					}
					else{
						dblPressure[hour]=Double.MIN_VALUE;
					}
					
					//get EyeSight
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					if(this.checkNull(str)){
						return null;
					}
					iStartIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_BEGIN);
					iEndIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_END);
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+45, iEndIndex);
						dblEyeSight[hour]=this.valueOf(str);
					}
					else{
						dblEyeSight[hour]=Double.MIN_VALUE;
					}
					
					//get WindSpeed
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					str=lnr.readLine();
					if(this.checkNull(str)){
						return null;
					}
					iStartIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_BEGIN);
					iEndIndex=str.indexOf(WeatherUnderground.SPLIT_MARK_END);
					if(checkIndex(iStartIndex,iEndIndex)){
						str=str.substring(iStartIndex+45, iEndIndex);
						dblWindSpeed[hour]=this.valueOf(str);
					}
					else{
						dblWindSpeed[hour]=Double.MIN_VALUE;
					}
					hour++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogFile logfile=new LogFile();
			logfile.LogError("[In WeatherUnderground.HistoryWeather error with read file]"+this.strFileName);
		}
		
		this.fixData(dblSweat);
		this.fixData(dblPressure);
		this.fixData(dblDewPoint);
		this.fixData(dblEyeSight);
		this.fixData(dblWindSpeed);
		this.fixData(dblTemp);
		
		for(int i=0;i<hour;i++)
		{
			SumDewPoint+=dblDewPoint[i];
			if(MaxDewPoint<dblDewPoint[i]){
				MaxDewPoint=dblDewPoint[i];
			}
			else if(MinDewPoint>dblDewPoint[i]){
				MinDewPoint=dblDewPoint[i];
			}
			
			SumTemp+=dblTemp[i];
			if(MaxTemp<dblTemp[i]){
				MaxTemp=dblTemp[i];
			}else if(MinTemp>dblTemp[i]){
				MinTemp=dblTemp[i];
			}
			
			SumPressure+=dblPressure[i];
			if(MaxPressure<dblPressure[i]){
				MaxPressure=dblPressure[i];
			}else if(MinPressure>dblPressure[i]){
				MinPressure=dblPressure[i];
			}
			
			//System.out.println(dblEyeSight[i]);
			SumEyeSight+=dblEyeSight[i];
			if(MaxEyeSight<dblEyeSight[i]){
				MaxEyeSight=dblEyeSight[i];
			}else if(MinEyeSight>dblEyeSight[i]){
				MinEyeSight=dblEyeSight[i];
			}
			
			SumWindSpeed+=dblWindSpeed[i];
			if(MaxWindSpeed<dblWindSpeed[i]){
				MaxWindSpeed=dblWindSpeed[i];
			}else if(MinWindSpeed>dblWindSpeed[i]){
				MinWindSpeed=dblWindSpeed[i];
			}
			
			SumSweat+=dblSweat[i];
			if(MaxSweat<dblSweat[i]){
				MaxSweat=dblSweat[i];
			}else if(MinSweat>dblSweat[i]){
				MinSweat=dblSweat[i];
			}
			
		}
		MeanDewPoint =SumDewPoint /48;
		MeanTemp     =SumTemp     /48;
		MeanPressure =SumPressure /48;
		MeanWindSpeed=SumWindSpeed/48;
		MeanSweat    =SumSweat    /48;
		MeanEyeSight =SumEyeSight /48;
		
		Weather w=new Weather();
		w.setStrDate(this.strDate);
		w.setStrLocation(strCityName);
		w.setMaxTemp_C(MaxTemp);
		w.setMinTemp_C(MinTemp);
		w.setAverrageTemp(MeanTemp);
		
		w.setMinDewPoint(MinDewPoint);
		w.setMeanDewPoint(MeanDewPoint);
		
		w.setMaxEyeSight_KM(MaxEyeSight);
		w.setMinEyeSight_KM(MinEyeSight);
		w.setMeanEyeSight_KM(MeanEyeSight);
		
		w.setMaxPressure_hPa(MaxPressure);
		w.setMinPressure_hPa(MinPressure);
		w.setMeanPressure_hPa(MeanPressure);
		
		w.setMaxWindSpeed_KM(MaxWindSpeed);
		w.setMeanWindSpeed_KM(MeanWindSpeed);
		
		w.setMaxSweat(MaxSweat);
		w.setMinSweat(MinSweat);
		w.setMeanSweat(MeanSweat);

		return w;
	}
	
	private void fixData(double data[])
	{
		for(int i=0;i<data.length;i++)
		{
			//System.out.println(data[i]);
			if(Double.MIN_VALUE!=data[i])
			{
				for(int j=i-1; j>=0 && Double.MIN_VALUE==data[j] ;j--)
				{
					data[j]=data[i];
				}
				for(int w=i+1;w<data.length && Double.MIN_VALUE==data[w] ;w++)
				{
					data[w]=data[i];
				}
			}
		}
	}
	
	
	private boolean checkNull(String str)
	{
		if(null==str)
		{
			System.out.println("Error with decode weather status!");
			return true;
		}
		return false;
	}
	
	private Double valueOf(String str)
	{
		if(null==str||str.equals("-")){
			return Double.NaN;
		}
		return Double.valueOf(str);
	}
	
	private boolean checkIndex(int iStartIndex,int iEndIndex)
	{
		if(-1==iStartIndex||-1==iEndIndex)
		{
			return false;
		}
		return true;
	}
	private boolean checkNotErrorVersion(Weather w)
	{	
		boolean bNoError=true;
		if(Double.MIN_VALUE==w.getMeanDewPoint()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getAverrageTemp()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMaxEyeSight_KM()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMaxPressure_hPa()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMaxSweat()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMaxTemp_C()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMaxWindSpeed_KM()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMeanDewPoint()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMeanEyeSight_KM()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMeanPressure_hPa()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMeanWindSpeed_KM()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMinDewPoint()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMinEyeSight_KM()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMinPressure_hPa()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMinSweat()){
			bNoError=false;
		}
		if(Double.MIN_VALUE==w.getMinTemp_C()){
			bNoError=false;
		}
		return bNoError;
	}
}
