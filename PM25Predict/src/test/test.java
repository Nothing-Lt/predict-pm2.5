package test;

import java.util.Date;

import Database.WeatherDatabase;
import Image.Image;
import PM25Predict.Data;
import PM25Predict.Predict;
import PM25Predict.RBFPredict;
import PM25Predict.ANNPredict;

public class test {

	public static void main(String[] args) {

		RBFtrain(2,7,30,1,0.02);
		RBFtets();
	}
	
	
	static void getgraph()
	{
		Date date=new Date();
		//for(int i=1;i<365;i++){
			date.setYear(116);
			date.setMonth(1);
			date.setDate(3);
			Data d=new Data("Beijing","China",date);
			d.DownloadHistGraph();
		//}
	}
	public static void getData(String strCity)
	{
		WeatherDatabase wdb=new WeatherDatabase();
		wdb.Connect();
		//wdb.CreateWeatherTable();
		Date date=new Date();
		for(int i=1;i<150;i++){
			date.setYear(117);
			date.setMonth(0);
			date.setDate(i);
			Data d=new Data(strCity,"China",date);
			d.DownloadData();
			d.InsertDataIntoDatabase(wdb);
		}
	}
	
	
	static public  void RBFtrain()
	{
		Predict p=new RBFPredict(2,7,30,1,0.02);
		p.ResetWeight_Threadhold();
		
		WeatherDatabase wdb=new WeatherDatabase();
		wdb.Connect();

		for(int j=0;j<150;j++){
			Date date=new Date();
			for(int i=0;i<365;i++){
				if(0==i%3){
				continue;
				}
				date.setYear(116);
				date.setMonth(0);
				date.setDate(i);
				Data d=new Data("Beijing","China",date);
				d.GetDataFromDatabase(wdb);
				if(d.IsBadData()){
					System.out.println("invalid");
					continue;
				}
				double doubleTarget=(d.GetWeather().getTarget()-6)/(393-6);
				p.SetInputValue(d.GetNormalizatedArray(), doubleTarget);
			
				p.Run();
				p.Train();
			}
			p.Save();
		}
		wdb.Disconnect();
	}
	
	@SuppressWarnings("deprecation")
	static void RBFtets()
	{
		double doubleResult[]=new double[365]; 
		double doubleTarget[]=new double[365];
	
		Date date=new Date();
		WeatherDatabase wdb=new WeatherDatabase();
		wdb.Connect();
		for(int i=0;i<140;i++)
		{
			/*if(i%3!=0){
				continue;
			}*/
			date.setYear(117);
			date.setMonth(0);
			date.setDate(i);
			Data d=new Data("Beijing","China",date);
			d.GetDataFromDatabase(wdb);
			if(d.IsBadData()){
				System.out.println("invalid data!!!");
				continue;
			}
			Predict p=new RBFPredict();
			p.Load();
			
			p.SetInputValue(d.GetNormalizatedArray(), ((d.GetWeather().getTarget()-6)/(393-6)));	
			double doubleTemp=p.Run()*Data.PART_PM25;
			doubleResult[i]=doubleTemp;
			doubleTarget[i]=d.GetWeather().getTarget();
			System.out.println("Result:"+doubleTemp+"target:"+d.GetWeather().getTarget()+"today pm25:"+d.GetWeather().getPM25());
		}
		Image img=new Image("compare.jpg");
		img.DrawImage(doubleResult, doubleTarget);
	}
	
	
	@SuppressWarnings("deprecation")
	static void ANNtrain()
	{
		Predict p=new ANNPredict();
		p.ResetWeight_Threadhold();
		
		WeatherDatabase wdb=new WeatherDatabase();
		wdb.Connect();

		for(int j=0;j<1000;j++){
			Date date=new Date();
			for(int i=0;i<365;i++){
				if(0==i%3){
				continue;
				}
				date.setYear(116);
				date.setMonth(0);
				date.setDate(i);
				Data d=new Data("Beijing","China",date);
				d.GetDataFromDatabase(wdb);
				if(d.IsBadData()){
					continue;
				}
				double doubleTarget=(d.GetWeather().getTarget()-6)/(393-6);
				p.SetInputValue(d.GetNormalizatedArray(), doubleTarget);
			
				p.Run();
				p.Train();
			}
			p.Save();
		}
		wdb.Disconnect();
	}
	
	@SuppressWarnings("deprecation")
	static void ANNtets()
	{
		double doubleResult[]=new double[365]; 
		double doubleTarget[]=new double[365];
	
		Date date=new Date();
		WeatherDatabase wdb=new WeatherDatabase();
		wdb.Connect();
		for(int i=0;i<100;i++)
		{
			date.setYear(117);
			date.setMonth(0);
			date.setDate(i);
			Data d=new Data("Beijing","China",date);
			d.GetDataFromDatabase(wdb);
			if(d.IsBadData()){
				System.out.println("invalid data!!!");
				continue;
			}
			Predict p=new ANNPredict();
			p.Load();
			
			p.SetInputValue(d.GetNormalizatedArray(), ((d.GetWeather().getTarget()-6)/(393-6)));	
			double doubleTemp=p.Run()*Data.PART_PM25;
			doubleResult[i]=doubleTemp;
			doubleTarget[i]=d.GetWeather().getTarget();
			System.out.println("Result:"+doubleTemp+"shouldbe:"+d.GetWeather().getTarget());
		}
		Image img=new Image("compare.jpg");
		img.DrawImage(doubleResult, doubleTarget);
	}
	
}
