package PM25Predict;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import Database.WeatherDatabase;
import Http.Download;
import Object.Weather;
import TianQiHouBao.HistoryPollution;
import WeatherUnderground.HistoryWeather;
import tools.LogFile;

public class Data {

	private Calendar calendar=null;
	private String strCityName=null;
	private String strCountryName=null;
	private Weather w;
	String strYear=null;
	String strMonth=null;
	String strDate=null;
	
	public static final double MIN_PM25=6;
	public static final double MAX_PM25=393;
	public static final double PART_PM25=MAX_PM25-MIN_PM25;
	
	public static final double MIN_AQI=24;
	public static final double MAX_AQI=429;
	public static final double PART_AQI=MAX_AQI-MIN_AQI;
	
	public static final double MIN_AVERAGETEMP=-5.4;
	public static final double MAX_AVERAGETEMP=31.4;
	public static final double PART_AVERAGETEMP=MAX_AVERAGETEMP-MIN_AVERAGETEMP;
	
	public static final double MIN_CO=0.22;
	public static final double MAX_CO=7.97;
	public static final double PART_CO=MAX_CO-MIN_CO;
	
	public static final double MIN_MAXEYESIGHT=1.5;
	public static final double MAX_MAXEYESIGHT=30;
	public static final double PART_MAXEYESIGHT=MAX_MAXEYESIGHT-MIN_MAXEYESIGHT;
	
	public static final double MIN_MAXPRESSURE=1000;
	public static final double MAX_MAXPRESSURE=1041;
	public static final double PART_MAXPRESSURE=MAX_MAXPRESSURE-MIN_MAXPRESSURE;
	
	public static final double MIN_MAXSWEAT=15;
	public static final double MAX_MAXSWEAT=100;
	public static final double PART_MAXSWEAT=MAX_MAXSWEAT-MIN_MAXSWEAT;
	
	public static final double MIN_TEMP=1;
	public static final double MAX_TEMP=37;
	public static final double PART_TEMP=MAX_TEMP-MIN_TEMP;
	
	public static final double MIN_MAXWINSPEED=3.6;
	public static final double MAX_MAXWINSPEED=57.6;
	public static final double PART_MAXWINSPEED=MAX_MAXWINSPEED-MIN_MAXWINSPEED;
	
	public static final double MIN_MEANDEWPOINT=-22;
	public static final double MAX_MEANDEWPOINT=25.75;
	public static final double PART_MEANDEWPOINT=MAX_MEANDEWPOINT-MIN_MEANDEWPOINT;
	
	public static final double MIN_MEANEYESIGHT=0.26;
	public static final double MAX_MEANEYESIGHT=30;
	public static final double PART_MEANEYESIGHT=MAX_MEANEYESIGHT-MIN_MEANEYESIGHT;
	
	public static final double MIN_MEANPRESSURE=127;
	public static final double MAX_MEANPRESSURE=1038;
	public static final double PART_MEANPRESSURE=MAX_MEANPRESSURE-MIN_MEANPRESSURE;
	
	public static final double MIN_MEANSWEAT=1.8;
	public static final double MAX_MEANSWEAT=91.2;
	public static final double PART_MEANSWEAT=MAX_MEANSWEAT-MIN_MEANSWEAT;
	
	public static final double MIN_MEANWINSPEED=0.45;
	public static final double MAX_MEANWINSPEED=31.1;
	public static final double PART_MEANWINSPEED=MAX_MEANWINSPEED-MIN_MEANWINSPEED;
	
	public static final double MIN_MINDEWPOINT=-27;
	public static final double MAX_MINDEWPOINT=25;
	public static final double PART_MINDEWPOINT=MAX_MINDEWPOINT-MIN_MINDEWPOINT;
	
	public static final double MIN_MINEYESIGHT=0.05;
	public static final double MAX_MINEYESIGHT=30;
	public static final double PART_MINEYESIGHT=MAX_MINEYESIGHT-MIN_MINEYESIGHT;
	
	public static final double MIN_MINPRESSURE=994;
	public static final double MAX_MINPRESSURE=1036;
	public static final double PART_MINPRESSURE=MAX_MINPRESSURE-MIN_MINPRESSURE;
	
	public static final double MIN_MINSWEAT=4;
	public static final double MAX_MINSWEAT=94;
	public static final double PART_MINSWEAT=MAX_MINSWEAT-MIN_MINSWEAT;
	
	public static final double MIN_MINTEMP=-15;
	public static final double MAX_MINTEMP=27;
	public static final double PART_MINTEMP=MAX_MINTEMP-MIN_MINTEMP;
	
	public static final double MIN_NO2=11;
	public static final double MAX_NO2=152;
	public static final double PART_NO2=MAX_NO2-MIN_NO2;
	
	public static final double MIN_O3=2;
	public static final double MAX_O3=164;
	public static final double PART_O3=MAX_O3-MIN_O3;
	
	public static final double MIN_PM10=3;
	public static final double MAX_PM10=429;
	public static final double PART_PM10=MAX_PM10-MIN_PM10;
	
	public static final double MIN_SO2=2;
	public static final double MAX_SO2=53;
	public static final double PART_SO2=MAX_SO2-MIN_SO2;
	
	public String strNameField[]={"PM25",        "AQI",         "AVERAGETEMP","CO",          "MAXEYESIGHT",
			  "MAXPRESSURE", "MAXSWEAT",    "TEMP",       "MAXWINSPEED", "MEANDEWPOINT",
			  "MEANEYESIGHT","MEANPRESSURE","MEANSWEAT",  "MEANWINDPEED","MINDEWPOINT",
			  "MINEYESIGHT", "MINPRESSURE", "MINSWEAT",   "MINTEMP",     "NO2",
			  "O3",          "PM10",        "SO2",        "RAIN",        "WINDIRSPEED"};

	public double doubleMinValue[];
	public double doubleMaxValue[];
	
	
	public Weather getW() {
		return w;
	}

	public void setW(Weather w) {
		this.w = w;
	}

	public Data(String strCityName,String strCountryName,Date date)
	{
		this.strCityName=strCityName;
		this.strCountryName=strCountryName;
		this.calendar=Calendar.getInstance();
		this.calendar.setTime(date);

		int iYear=this.calendar.get(Calendar.YEAR);
		this.strYear=""+iYear;
		
		String strMonth="";
		int iMonth=this.calendar.get(Calendar.MONTH)+1;
		if(10>iMonth){
			strMonth+="0";
		}
		strMonth+=iMonth;
		this.strMonth=strMonth;
		
		int iDay=this.calendar.get(Calendar.DAY_OF_MONTH);
		String strDay="";
		if(10>iDay){
			strDay+="0";
		}
		strDay+=iDay;
		this.strDate=strDay;
	}
	
	public void DownloadData()
	{		
		HistoryWeather h=new HistoryWeather(this.strCityName,this.strCountryName,this.calendar);
		//h.DownloadWeatherPage();
		this.w=h.GetWeatherObjectFromFile();
		
		if(null==this.w){
			LogFile f=new LogFile();
			f.LogError("[In Data.GetDataSource weather object Error]"+this.calendar.get(Calendar.MONTH)+" "+this.calendar.get(Calendar.DATE));
			return;
		}
		//this.w=new Weather();
		//this.w.setStrDate(strDate);
		//this.w.setStrLocation(strCityName);
		
		HistoryPollution p=new HistoryPollution(this.strCityName,this.calendar);
		p.DownloadPollutionPage();
		this.w=p.GetPollutionFromFile(this.w);
		
		if(null==this.w){
			LogFile f=new LogFile();
			f.LogError("[In Data.GetDataSource pollution object Error]"+this.calendar.get(Calendar.MONTH)+" "+this.calendar.get(Calendar.DATE));
			return;
		}
		
		System.out.println("final:"+w.getMeanDewPoint());
		System.out.println("final:"+w.getAverrageTemp());
		System.out.println("final:"+w.getMaxEyeSight_KM());
		System.out.println("final:"+w.getMaxPressure_hPa());
		System.out.println("final:"+w.getMaxSweat());
		System.out.println("final:"+w.getMaxTemp_C());
		System.out.println("final:"+w.getMaxWindSpeed_KM());
		System.out.println("final:"+w.getMeanDewPoint());
		System.out.println("final:"+w.getMeanEyeSight_KM());
		System.out.println("final:"+w.getMeanPressure_hPa());
		System.out.println("final:"+w.getMeanWindSpeed_KM());
		System.out.println("final:"+w.getMinDewPoint());
		System.out.println("final:"+w.getMinEyeSight_KM());
		System.out.println("final:"+w.getMinPressure_hPa());
		System.out.println("final:"+w.getMinSweat());
		System.out.println("final:"+w.getMinTemp_C());
		System.out.println("final:"+w.getAqi());
		System.out.println("final:"+w.getCo());
		System.out.println("final:"+w.getNo2());
		System.out.println("final:"+w.getO3());
		System.out.println("final:"+w.getPM10());
		System.out.println("final:"+w.getPM25());
		System.out.println("final:"+w.getSo2());
	}
	
	public void InsertDataIntoDatabase(WeatherDatabase wdb)
	{
		if(null==this.w){
			return;
		}

		wdb.InsertWeatherObject(w);
	}
	
	@SuppressWarnings("deprecation")
	public boolean GetDataFromDatabase(WeatherDatabase wdb)
	{
		String strDate="";	
		strDate=strYear+"/"+strMonth+"/"+this.strDate;
		System.out.println(strDate);
		this.w=wdb.GetWeatherObject(strDate,strCityName);
		if(null==this.w){
			return false;
		}
		Date dateTemp=this.calendar.getTime();
		dateTemp.setDate(dateTemp.getDate()+1);
		Calendar calendarTemp=Calendar.getInstance();
		calendarTemp.setTime(dateTemp);
		
		int iYear=calendarTemp.get(Calendar.YEAR);
		int iMonth=calendarTemp.get(Calendar.MONTH)+1;
		strMonth="";
		if(10>iMonth){
			strMonth+="0";
		}
		strMonth+=iMonth;
		int iDay=calendarTemp.get(Calendar.DAY_OF_MONTH);
		String strDay="";
		if(10>iDay){
			strDay+="0";
		}
		strDay+=iDay;
		strDate=iYear+"/"+strMonth+"/"+strDay;
		w.setTarget(wdb.GetPM25(strDate));
		if(null==this.w){
			return false;
		}
		return true;
	}
	
	public double[] GetArray()
	{
		double doubleData[]=new double[25];
		for(int i=0;i<Weather.DATA_NUMBER;i++){
			doubleData[i]=0;
		}
		
		doubleData[0]= w.getAqi();
		doubleData[1]= w.getCo();
		doubleData[2]=w.getNo2();
		doubleData[3]=w.getO3();
		doubleData[4]=w.getPM10();
		doubleData[5]=w.getPM25();
		doubleData[6]=w.getSo2();
		
		return doubleData;
	}
	
	public boolean IsBadData()
	{
		boolean bBadData=false;
		if(null==this.w){
			return true;
		}
		if(Double.MIN_VALUE==w.getAqi()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getAverrageTemp()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getCo()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMaxEyeSight_KM()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMaxPressure_hPa()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMaxSweat()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMaxTemp_C()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMaxWindSpeed_KM()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMeanDewPoint()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMeanEyeSight_KM()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMeanPressure_hPa()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMeanSweat()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMeanWindSpeed_KM()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMinDewPoint()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMinEyeSight_KM()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMinPressure_hPa()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMinSweat()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getMinTemp_C()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getNo2()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getO3()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getPM10()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getPM25()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getSo2()){
			bBadData=true;
		}
		if(Double.MIN_VALUE==w.getTarget()){
			bBadData=true;
		}
		return bBadData;
	}
	
	public Weather GetWeather()
	{
		return this.w;
	}
	
	public double[] GetNormalizatedArray()
	{
		double doubleData[]=new double[25];
		for(int i=0;i<Weather.DATA_NUMBER;i++){
			doubleData[i]=0;
		}
		doubleData[0]= (w.getAqi()             -Data.MIN_AQI)/(Data.PART_AQI);
		doubleData[1]= (w.getCo()              -Data.MIN_CO)/(Data.PART_CO);
		doubleData[2]=(w.getNo2()             -Data.MIN_NO2)/(Data.PART_NO2);
		doubleData[3]=(w.getO3()              -Data.MIN_O3)/(Data.PART_O3);
		doubleData[4]=(w.getPM10()            -Data.MIN_PM10)/(Data.PART_PM10);
		doubleData[5]=(w.getPM25()            -Data.MIN_PM25)/(Data.PART_PM25);
		doubleData[6]=(w.getSo2()             -Data.MIN_SO2)/(Data.PART_SO2);
		
		return doubleData;
	}
	
	public void DownloadHistGraph()
	{
		String strFileName=this.strYear+this.strMonth+this.strDate;
		File f=new File(strFileName+"graph.gif");
		if(!f.exists()){
			String strURL=WeatherUnderground.WeatherUnderground.GRAPH_HOST+"day="+this.strDate+"&"
				      +"year="+this.strYear+"&"
				      +"month="+this.strMonth+"&"
				      +"ID=ZBAA&type=3&width=614";
		
			Download d=new Download(strURL,strFileName+"graph.gif");
			d.run();
		}
		
		try {
			BufferedImage srcImage=ImageIO.read(f);
			BufferedImage dstImage=srcImage.getSubimage(54, 384, 520, 70);
			
			for(int i=69;i>=0;i--){
				for(int j=0;j<520;j++){
					if(Color.RED.getRGB()==dstImage.getRGB(j, i)){
						double dsmall=i*360/68;
						System.out.println("smallest:"+dsmall);
						i=-1;
						break;
					}
				}
			}
			
			for(int i=0;i<70;i++){
				for(int j=0;j<520;j++){
					if(Color.RED.getRGB()==dstImage.getRGB(j, i)){
						double dbig=i*360/68;
						System.out.println("bigest:"+dbig);
						i=71;
						break;
					}
				}
			}
			
			
			FileOutputStream fos = new FileOutputStream(strFileName+".jpg");
			JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(fos);
			encoder.encode(dstImage);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
