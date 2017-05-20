import datetime
import urllib
import re

#http://www.tianqihoubao.com/aqi/beijing-201607.html
strURL="http://www.tianqihoubao.com/aqi/"
strHTML=".html"
strFileName="TianQiHouBao"

value=[0.01]*7

# get time
def get_time():
	time_current    = datetime.datetime.now()
	time_before     = time_current + datetime.timedelta(days=-1)
	str_time_before = time_before.strftime('%Y%m')
	strDate         = time_before.strftime('%Y%mm%dd')
	return str_time_before

#get location including:city name and country name
def get_location():
    strlist=['city','country']
    reader=open('Location','r')
    for i in range(1,3):
        strlist[i-1]=reader.readline()
	strlist[i-1]=strlist[i-1].strip().lstrip().rstrip()
    return strlist

#http request download page
def get_TianQiHouBao(str_time,str_city):
	str_url=strURL+str_city+"-"+str_time+strHTML
	urllib.urlretrieve(str_url,strFileName)
	print str_url

#use pattern get value including :AQI,PM10,PM2.5,So2,Co2,O3,No2
def get_value():
	time_current    = datetime.datetime.now()
	time_before     = time_current + datetime.timedelta(days=-1)
	strDate         = time_before.strftime('%Y-%m-%d')
	pattern = re.compile(r'\W*(\d{4}-\d{2}-\d{2})</td>')
	file_t=open(strFileName)
	for i in range(0,1000):
		str=file_t.readline()
		match=pattern.match(str)
		if match:
			str=match.group(0)
			str=str.strip().lstrip().rstrip()
			if strDate + '</td>' == str:
				file_t.readline()
				file_t.readline()
				file_t.readline()
				file_t.readline()
				str=file_t.readline()
				index=str.find('</td>')
				str=str[0:index]
				value[0]=float(str) # get AQI
				file_t.readline()   # Rate useless
				str=file_t.readline() 
				start_index=str.find('<td>')+4
				end_index=str.find('</td>')
				str=str[start_index:end_index]
				value[1]=float(str) # PM2.5
				str=file_t.readline() 
				start_index=str.find('<td>')+4
				end_index=str.find('</td>')
				str=str[start_index:end_index]
				value[2]=float(str) #PM10
				file_t.readline()
				str=file_t.readline() 
				start_index=str.find('<td>')+4
				end_index=str.find('</td>')
				str=str[start_index:end_index]
				value[3]=float(str) #No2
				str=file_t.readline() 
				start_index=str.find('<td>')+4
				end_index=str.find('</td>')
				str=str[start_index:end_index]
				value[4]=float(str) #So2
				str=file_t.readline() 
				start_index=str.find('<td>')+4
				end_index=str.find('</td>')
				str=str[start_index:end_index]
				value[5]=float(str) #Co
				str=file_t.readline() 
				start_index=str.find('<td>')+4
				end_index=str.find('</td>')
				str=str[start_index:end_index]
				value[6]=float(str) #O3
	return
		
#for test
get_TianQiHouBao(get_time(),get_location()[0])
get_value()
output=open('Data','w')
for i in range(0,6):
	output.write(str(value[i])+'\n')
output.write(str(value[6]))
