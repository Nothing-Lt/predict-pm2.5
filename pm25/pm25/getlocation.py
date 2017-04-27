import urllib
import json

strFileName="Location"
strURL="http://192.168.1.112:8080/PM25PredictServer/getlocation"

con=urllib.urlopen(strURL)
str_json=con.read()
json_obj=json.loads(str_json)
str_temp=json_obj['region']
strCity='Beijing'
if str_temp == "Beijing":
    str_city=str_temp
elif str_temp == "Chongqing":
    str_city=str_temp
elif str_temp == "Shanghai":
    str_city=str_temp
elif str_temp == "Tianjin":
    str_city=str_temp
else:
    str_city=json_obj['city']
file_t=open('Location','w')
file_t.write(str_city+'\n')
file_t.write('China')
file_t.close()

