package Database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Object.Weather;


public class WeatherDatabase {

	String strDatabaseName="jdbc:mysql://localhost:3306/mysql";
	String strDriverName="com.mysql.jdbc.Driver";
	String strUserName="root";
	String strPassword="123456";
	
	private Statement statSql;
	private Connection conn;
	
	public boolean Connect()
	{
		try {
			Class.forName(this.strDriverName);
			this.conn = (Connection) DriverManager.getConnection(this.strDatabaseName,
																	  this.strUserName,
																	  this.strPassword);
			if(null==conn){
				System.out.println("Connection Error");
				return false;
			}
			//this.conn.setAutoCommit(false);
			this.statSql=(Statement) this.conn.createStatement();
			if(null==this.statSql){
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
	
	public boolean CreateWeatherTable()
	{
		String sql="CREATE TABLE WEATHER (DAYDATE VARCHAR(20),"+
				"LOCATION      VARCHAR(30),"+
                "AQI           DOUBLE,"+
                "CO            DOUBLE,"+
                "NO2           DOUBLE,"+
                "O3            DOUBLE,"+
                "SO2           DOUBLE,"+
                "PM10          DOUBLE,"+
                "PM25          DOUBLE,"+
                "PRIMARY KEY(DAYDATE,LOCATION));";
                
                try {
					this.statSql.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
                return true;
	}
	
	public boolean DropWeathertable()
	{
		String sql="DROP TABLE WEATHER;";
		try {
			this.statSql.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean InsertWeatherObject(Weather w)
	{
		String sql="INSERT IGNORE INTO WEATHER VALUES ('"+w.getStrDate()    +"',"+    //DAY_DATE
										  "'"+w.getStrLocation()     +"',"+
											  w.getAqi()             +","+              //AQI
											  w.getCo()              +","+               //CO
											  w.getNo2()             +","+              //NO2
											  w.getO3()              +","+               //O3
											  w.getSo2()             +","+              //SO2
											  w.getPM10()            +","+             //PM10
											  w.getPM25()            +");";            //PM25

		try {
			this.statSql.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("finally")
	public Weather GetWeatherObject(String strDate,String strLocation)
	{
		Weather w=null;
		
		String sql="SELECT * FROM WEATHER WHERE DAYDATE='"+strDate+"' AND LOCATION='"+strLocation+"';";
		try {
			ResultSet result=this.statSql.executeQuery(sql);
			if(null!=result&&result.next()){
				w=new Weather();
				w.setStrDate(strDate);
				w.setStrLocation("LOCATION");
				w.setAqi(result.getDouble("AQI"));
				w.setCo(result.getDouble("CO"));
				w.setNo2(result.getDouble("NO2"));
				w.setO3(result.getDouble("O3"));
				w.setPM10(result.getDouble("PM10"));
				w.setPM25(result.getDouble("PM25"));
				w.setSo2(result.getDouble("SO2"));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return w;
		}
	}
	
	@SuppressWarnings("finally")
	public double GetPM25(String strDate)
	{
		double doubleReturnValue=Double.MIN_VALUE;
		String sql="SELECT PM25 FROM WEATHER WHERE DAYDATE='"+strDate+"'";
		try {
			ResultSet result=statSql.executeQuery(sql);
			if(null!=result&&result.next()){
				doubleReturnValue=result.getDouble("PM25");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return doubleReturnValue;
		}
	}
	
	public void Disconnect()
	{
		try {
			this.statSql.close();
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
