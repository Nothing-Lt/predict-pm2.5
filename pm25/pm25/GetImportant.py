import urllib
import os

strLocationFileName='Location'
strImportantFileName='Important'
strURL='http://192.168.43.106:8080/PM25PredictServer/getimport?city='

if not os.path.exists(strLocationFileName):
  print 'No Location'
  exit()
if os.path.exists(strImportantFileName): 
  exit()
strCity='Beijing'
file_t=open('Location')
strCityURL=strURL+file_t.readline()
print strCityURL
urllib.urlretrieve(strCityURL,'Important')
