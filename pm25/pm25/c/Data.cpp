#include "Data.h"

Data::Data()
{
  this->boolFileReaded=false;
  this->strDataFileName="Data";
}

bool Data::GetValue(double doubleValue[VALUE_NUMBER])
{
  int i=0;
  
  if(!this->boolFileReaded)
    {
      return false;
    }
  
  for(;i<VALUE_NUMBER;i++)
    {
      doubleValue[i]=this->doubleValue[i];
    }
  
  return true;
}

bool Data::FileReaded()
{
  return this->boolFileReaded;
}

bool Data::GetDataFromFile()
{
  bool boolReturnValue=false;
  int iIndex=0;
  char buff[BUFFER_SIZE];
  
  ifstream ifFile;
  ifFile.open(this->strDataFileName.data());
  if(!ifFile.is_open())
    {
      return boolReturnValue;
    }
  
  for(iIndex=0;iIndex<VALUE_NUMBER;iIndex++)
    {
      ifFile.getline(buff,BUFFER_SIZE);
      this->doubleValue[iIndex]=atof(buff);
    }
  this->boolFileReaded=true;
  
  ifFile.close();
  return false;
}

bool Data::GetNormalizedValue(double doubleValue[VALUE_NUMBER])
{
  if(!this->boolFileReaded)
    {
      return this->boolFileReaded;
    }
  doubleValue[0]=(this->doubleValue[0]-MIN_AQI)/(PART_AQI);
  doubleValue[1]=(this->doubleValue[1]-MIN_CO)/(PART_CO);
  doubleValue[2]=(this->doubleValue[2]-MIN_NO2)/(PART_NO2);
  doubleValue[3]=(this->doubleValue[3]-MIN_O3)/(PART_O3);
  doubleValue[4]=(this->doubleValue[4]-MIN_PM10)/(PART_PM10);
  doubleValue[5]=(this->doubleValue[5]-MIN_PM25)/(PART_PM25);
  doubleValue[6]=(this->doubleValue[6]-MIN_SO2)/(PART_SO2);
  return true;
}
