#include "Data.h"
#include "RBFPredict.h"
//#include "display.h"
#include <stdio.h>
#include <time.h>
#include <wiringPi.h>
#include "SSD1306.h"
#include "font.h"

int main()
{
	
	time_t t;
	struct tm *p;
	
	double today_pm25,tomorrow_pm25,past_tomorrow_pm25=-1;
	char stat='c';

	Data d;
	double tt[VALUE_NUMBER];
	char buff[BUFFER_SIZE]={0};
	
	if(wiringPiSetup()<0)
	{
		cout<<"Err"<<endl;
	}
	init(SSD1306_SWITCHCAPVCC);
	run_char("a12.56");
	display();
	while(1)
	{
		time(&t);
		p=localtime(&t);
		if(0==p->tm_min)
		{
			if(7==p->tm_hour)
			{
				system("python Getpollution.py");
				d.GetDataFromFile();
				d.GetValue(tt);

				printf("AM");
				today_pm25=tt[5]; //run download function
				sprintf(buff,"a%.2f",today_pm25);
				run_char(buff);
			}
			else if(19==p->tm_hour)
			{
				printf("PM");
				
				system("python Getpollution.py");
				d.GetDataFromFile();
				d.GetValue(tt);
				if(!d.GetNormalizedValue(tt))
				{
					cout<<"Err"<<endl;
					sprintf(buff,"a%s","11111");
					run_char(buff);
				}
				cout<<setiosflags(ios::fixed); 
				for(int i=0;i<VALUE_NUMBER;i++)
				{
					cout<<i<<endl;
					cout<<tt[i];
				}
				RBFPredict p;
				while(!p.Load());
				p.SetInputValue(tt,VALUE_NUMBER);
				tomorrow_pm25=p.Run()*PART_PM25+MIN_PM25;
				if(-1==past_tomorrow_pm25)
				{
					past_tomorrow_pm25=tomorrow_pm25;
					today_pm25=tt[5]; //run download function
					sprintf(buff,"a%.2f",today_pm25);
					run_char(buff);
					continue;
				}				

				if(tomorrow_pm25>past_tomorrow_pm25)
				{
					stat='b';
				}
				if(tomorrow_pm25==past_tomorrow_pm25)
				{
					stat='c';
				}
				if(tomorrow_pm25<past_tomorrow_pm25)
				{
					stat='d';
				}
				past_tomorrow_pm25=tomorrow_pm25;
				sprintf(buff,"a%.2f;%c",today_pm25,stat);
				run_char(buff);
			}
			
		}
		sleep(1);
	}
	return 0;
}
