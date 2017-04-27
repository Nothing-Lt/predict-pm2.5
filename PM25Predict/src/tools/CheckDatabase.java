package tools;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class CheckDatabase {
	

	static String strDatabaseName="jdbc:mysql://localhost:3306/mysql";
	static String strDriverName="com.mysql.jdbc.Driver";
	static String strUserName="root";
	static String strPassword="123456";
	
	static public Statement statSql;
	static public Connection conn;
	
	static boolean Connect()
	{
		try {
			Class.forName(strDriverName);
			conn = (Connection) DriverManager.getConnection(strDatabaseName,
																	  strUserName,
																	  strPassword);
			if(null==conn){
				System.out.println("Connection Error");
				return false;
			}
			//this.conn.setAutoCommit(false);
			statSql=(Statement) conn.createStatement();
			if(null==statSql){
				System.out.println("Statement Error");
				return false;
			}
			return true;
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error with QSLException");
			return false;
				
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error with ClassFoundException");
			return false;
		}
	}	
	
	public static void main(String arg[])
	{
		LogBadData();
		/*String strSelect="SELECT * FROM WEATHER;";
		String str="";
		Connect();
		try {
			ResultSet result=statSql.executeQuery(strSelect);
			while(result.next()){
				str+=result.getString("DAYDATE")+",\t";
				str+=result.getDouble("MEANDEWPOINT")+",\t";
				str+=result.getDouble("AVERRAGETEMP")+",\t";
				str+=result.getDouble("MAXEYESIGHT")+",\t";
				str+=result.getDouble("MAXPRESSURE")+",\t";
				str+=result.getDouble("MAXSWEAT")+",\t";
				str+=result.getDouble("MAXTEMP")+",\t";
				str+=result.getDouble("MAXWINSPEED")+",\t";
				str+=result.getDouble("RAIN")+",\t";
				str+=result.getDouble("WINDIRDEGREE")+",\t";
				str+=result.getDouble("MEANEYESIGHT")+",\t";
				str+=result.getDouble("MEANPRESSURE")+",\t";
				str+=result.getDouble("MEANWINDSPEED")+",\t";
				str+=result.getDouble("MINDEWPOINT")+",\t";
				str+=result.getDouble("MINEYESIGHT")+",\t";
				str+=result.getDouble("MINPRESSURE")+",\t";
				str+=result.getDouble("MINSWEAT")+",\t";
				str+=result.getDouble("MINTEMP")+",\t";
				str+=result.getDouble("AQI")+",\t";
				str+=result.getDouble("CO")+",\t";
				str+=result.getDouble("NO2")+",\t";
				str+=result.getDouble("O3")+",\t";
				str+=result.getDouble("SO2")+",\t";
				str+=result.getDouble("PM10")+",\t";
				str+=result.getDouble("PM25")+";";
				System.out.println(str);
				str="";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	
	
	static void LogBadData()
	{
		String strSelect="SELECT * FROM WEATHER;";
		String strDate;
		Connect();
		try {
			ResultSet result=statSql.executeQuery(strSelect);
			while(result.next()){
				strDate="[Bad Data]"+result.getString("DAYDATE");
				if(Double.MIN_VALUE==result.getDouble("MEANDEWPOINT")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("AVERRAGETEMP")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MAXEYESIGHT")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MAXPRESSURE")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MAXSWEAT")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MAXTEMP")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MAXWINSPEED")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("RAIN")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("WINDIRDEGREE")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MINSWEAT")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MINPRESSURE")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MINEYESIGHT")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MINDEWPOINT")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MEANWINDSPEED")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MEANPRESSURE")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("MEANEYESIGHT")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("PM25")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("PM10")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("SO2")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("O3")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("NO2")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("CO")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
				if(Double.MIN_VALUE==result.getDouble("AQI")){
					LogFile logfile=new LogFile();
					logfile.LogError(strDate);
					continue;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
