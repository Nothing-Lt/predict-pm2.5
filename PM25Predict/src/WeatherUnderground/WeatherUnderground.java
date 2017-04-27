package WeatherUnderground;

public class WeatherUnderground {
	public static final String HOST                  ="https://www.wunderground.com/history/airport/ZBAA/";
	public static final String HTML                  ="DailyHistory.html";
	public static final String CITY_NAME             ="req_city"; 
	public static final String COUNTRY_NAME          ="req_statename"; 
	public static final String DB_ZIP                ="reqdb.zip";
	public static final String DB_ZIP_NUM            ="00000";
	public static final String DB_MAGIC              ="reqdb.magic";
	public static final String DB_MAGIC_NUM          ="1";
	public static final String DB_WMO                ="reqdb.wmo";
	public static final String DB_WMO_NUM            ="54511";
	public static final String SPLIT_STRING          ="Hourly Weather History &amp; Observations";
	public static final String GRAPH_HOST            ="https://www.wunderground.com/cgi-bin/histGraphAll?";
	
	public static final int START_LINE               =900; 
	public static final int DATA_NUMBER              =48;
	
	public static final String SPLIT_MARK_BEGIN      ="<span class=\"wx-data\"><span class=\"wx-value\">";
	public static final String SPLIT_MARK_END        ="</span>";
	
	public static final long VERSION_ERROR_2016_BEGIN=1461381426904L;
	public static final long VERSION_ERROR_2016_END  =1469068628826L;

}
