package PM25Predict;

import java.util.Calendar;
import java.util.Date;

import Database.WeatherDatabase;
import Object.Weather;

public class Normalizate {
	
	private double MIN_PM25=6;
	private double MAX_PM25=393;
	private double PART_PM25=MAX_PM25-MIN_PM25;
	
	private double MIN_AQI=24;
	private double MAX_AQI=429;
	private double PART_AQI=MAX_AQI-MIN_AQI;
	
	private double MIN_AVERAGETEMP=-5.4;
	private double MAX_AVERAGETEMP=31.4;
	private double PART_AVERAGETEMP=MAX_AVERAGETEMP-MIN_AVERAGETEMP;
	
	private double MIN_CO=0.22;
	private double MAX_CO=7.97;
	private double PART_CO=MAX_CO-MIN_CO;
	
	private double MIN_MAXEYESIGHT=1.5;
	private double MAX_MAXEYESIGHT=30;
	private double PART_MAXEYESIGHT=MAX_MAXEYESIGHT-MIN_MAXEYESIGHT;
	
	private double MIN_MAXPRESSURE=1000;
	private double MAX_MAXPRESSURE=1041;
	private double PART_MAXPRESSURE=MAX_MAXPRESSURE-MIN_MAXPRESSURE;
	
	private double MIN_MAXSWEAT=15;
	private double MAX_MAXSWEAT=100;
	private double PART_MAXSWEAT=MAX_MAXSWEAT-MIN_MAXSWEAT;
	
	private double MIN_TEMP=1;
	private double MAX_TEMP=37;
	private double PART_TEMP=MAX_TEMP-MIN_TEMP;
	
	private double MIN_MAXWINSPEED=3.6;
	private double MAX_MAXWINSPEED=57.6;
	private double PART_MAXWINSPEED=MAX_MAXWINSPEED-MIN_MAXWINSPEED;
	
	private double MIN_MEANDEWPOINT=-22;
	private double MAX_MEANDEWPOINT=25.75;
	private double PART_MEANDEWPOINT=MAX_MEANDEWPOINT-MIN_MEANDEWPOINT;
	
	private double MIN_MEANEYESIGHT=0.26;
	private double MAX_MEANEYESIGHT=30;
	private double PART_MEANEYESIGHT=MAX_MEANEYESIGHT-MIN_MEANEYESIGHT;
	
	private double MIN_MEANPRESSURE=127;
	private double MAX_MEANPRESSURE=1038;
	private double PART_MEANPRESSURE=MAX_MEANPRESSURE-MIN_MEANPRESSURE;
	
	private double MIN_MEANSWEAT=1.8;
	private double MAX_MEANSWEAT=91.2;
	private double PART_MEANSWEAT=MAX_MEANSWEAT-MIN_MEANSWEAT;
	
	private double MIN_MEANWINSPEED=0.45;
	private double MAX_MEANWINSPEED=31.1;
	private double PART_MEANWINSPEED=MAX_MEANWINSPEED-MIN_MEANWINSPEED;
	
	private double MIN_MINDEWPOINT=-27;
	private double MAX_MINDEWPOINT=25;
	private double PART_MINDEWPOINT=MAX_MINDEWPOINT-MIN_MINDEWPOINT;
	
	private double MIN_MINEYESIGHT=0.05;
	private double MAX_MINEYESIGHT=30;
	private double PART_MINEYESIGHT=MAX_MINEYESIGHT-MIN_MINEYESIGHT;
	
	private double MIN_MINPRESSURE=994;
	private double MAX_MINPRESSURE=1036;
	private double PART_MINPRESSURE=MAX_MINPRESSURE-MIN_MINPRESSURE;
	
	private double MIN_MINSWEAT=4;
	private double MAX_MINSWEAT=94;
	private double PART_MINSWEAT=MAX_MINSWEAT-MIN_MINSWEAT;
	
	private double MIN_MINTEMP=-15;
	private double MAX_MINTEMP=27;
	private double PART_MINTEMP=MAX_MINTEMP-MIN_MINTEMP;
	
	private double MIN_NO2=11;
	private double MAX_NO2=152;
	private double PART_NO2=MAX_NO2-MIN_NO2;
	
	private double MIN_O3=2;
	private double MAX_O3=164;
	private double PART_O3=MAX_O3-MIN_O3;
	
	private double MIN_PM10=3;
	private double MAX_PM10=429;
	private double PART_PM10=MAX_PM10-MIN_PM10;
	
	private double MIN_SO2=2;
	private double MAX_SO2=53;
	private double PART_SO2=MAX_SO2-MIN_SO2;
	
	public String strNameField[]={"PM25",        "AQI",         "AVERAGETEMP","CO",          "MAXEYESIGHT",
								  "MAXPRESSURE", "MAXSWEAT",    "TEMP",       "MAXWINSPEED", "MEANDEWPOINT",
								  "MEANEYESIGHT","MEANPRESSURE","MEANSWEAT",  "MEANWINDPEED","MINDEWPOINT",
								  "MINEYESIGHT", "MINPRESSURE", "MINSWEAT",   "MINTEMP",     "NO2",
								  "O3",          "PM10",        "SO2",        "RAIN",        "WINDIRSPEED"};
	
	public double doubleMinValue[];
	public double doubleMaxValue[];
	
	public boolean InitNormalizateParam(WeatherDatabase wdb)
	{
		Date date=new Date();
		date.setYear(116);
		date.setMonth(0);
		date.setDate(1);
		for(int i=0;i<365;i++)
		{
			date.setDate(date.getDate()+1);
			Calendar c=Calendar.getInstance();
			c.setTime(date);
			int iYear=c.get(Calendar.YEAR);
			int iMonth=c.get(Calendar.MONTH)+1;
			String strMonth="";
			if(10>iMonth){
				strMonth+="0";
			}
			strMonth+=iMonth;
			int iDay=c.get(Calendar.DAY_OF_MONTH);
			String strDay="";
			if(10>iDay){
				strDay+="0";
			}
			String strDate=iYear+"/"+strMonth+"/"+strDay;
			
			//Weather w=wdb.GetWeatherObject(strDate);
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		return false;
	}
	
}
