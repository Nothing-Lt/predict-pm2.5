#ifndef DATA_H
#define DATA_H

#include "main.h"

class Data
{
public:
  Data();
  bool GetDataFromFile();
  bool GetValue(double doubleValue[VALUE_NUMBER]);
  bool FileReaded();
  bool GetNormalizedValue(double doubleValue[VALUE_NUMBER]);
  
private:
  bool   boolFileReaded;
  double doubleValue[VALUE_NUMBER];
  string strDataFileName;
};

#define MIN_PM25 6
#define MAX_PM25 393
#define PART_PM25 MAX_PM25-MIN_PM25

#define MIN_AQI 24
#define MAX_AQI 429
#define PART_AQI MAX_AQI-MIN_AQI

#define MIN_AVERAGETEMP -5.4
#define MAX_AVERAGETEMP 31.4
#define PART_AVERAGETEMP MAX_AVERAGETEMP-MIN_AVERAGETEMP

#define MIN_CO 0.22
#define MAX_CO 7.97
#define PART_CO MAX_CO-MIN_CO

#define MIN_MAXEYESIGHT 1.5
#define MAX_MAXEYESIGHT 30
#define PART_MAXEYESIGHT MAX_MAXEYESIGHT-MIN_MAXEYESIGHT

#define MIN_MAXPRESSURE 1000
#define MAX_MAXPRESSURE 1041
#define PART_MAXPRESSURE MAX_MAXPRESSURE-MIN_MAXPRESSURE

#define MIN_MAXSWEAT 15
#define MAX_MAXSWEAT 100
#define PART_MAXSWEAT MAX_MAXSWEAT-MIN_MAXSWEAT

#define MIN_TEMP 1
#define MAX_TEMP 37
#define PART_TEMP MAX_TEMP-MIN_TEMP

#define MIN_MAXWINSPEED 3.6
#define MAX_MAXWINSPEED 57.6
#define PART_MAXWINSPEED MAX_MAXWINSPEED-MIN_MAXWINSPEED

#define MIN_MEANDEWPOINT -22
#define MAX_MEANDEWPOINT 25.75 
#define PART_MEANDEWPOINT MAX_MEANDEWPOINT-MIN_MEANDEWPOINT 

#define MIN_MEANEYESIGHT 0.26
#define MAX_MEANEYESIGHT 30
#define PART_MEANEYESIGHT MAX_MEANEYESIGHT-MIN_MEANEYESIGHT

#define MIN_MEANPRESSURE 127
#define MAX_MEANPRESSURE 1038
#define PART_MEANPRESSURE MAX_MEANPRESSURE-MIN_MEANPRESSURE

#define MIN_MEANSWEAT 1.8
#define MAX_MEANSWEAT 91.2 
#define PART_MEANSWEAT MAX_MEANSWEAT-MIN_MEANSWEAT

#define MIN_MEANWINSPEED 0.45
#define MAX_MEANWINSPEED 31.1 
#define PART_MEANWINSPEED MAX_MEANWINSPEED-MIN_MEANWINSPEED
 
#define MIN_MINDEWPOINT -27
#define MAX_MINDEWPOINT 25
#define PART_MINDEWPOINT MAX_MINDEWPOINT-MIN_MINDEWPOINT

#define MIN_MINEYESIGHT 0.05 
#define MAX_MINEYESIGHT 30
#define PART_MINEYESIGHT MAX_MINEYESIGHT-MIN_MINEYESIGHT 
 
#define MIN_MINPRESSURE 994 
#define MAX_MINPRESSURE 1036
#define PART_MINPRESSURE MAX_MINPRESSURE-MIN_MINPRESSURE
 
#define MIN_MINSWEAT 4
#define MAX_MINSWEAT 94
#define PART_MINSWEAT MAX_MINSWEAT-MIN_MINSWEAT

#define MIN_MINTEMP -15
#define MAX_MINTEMP 27
#define PART_MINTEMP MAX_MINTEMP-MIN_MINTEMP
 
#define MIN_NO2 11 
#define MAX_NO2 152 
#define PART_NO2 MAX_NO2-MIN_NO2

#define MIN_O3 2
#define MAX_O3 164
#define PART_O3 MAX_O3-MIN_O3

#define MIN_PM10 3
#define MAX_PM10 429
#define PART_PM10 MAX_PM10-MIN_PM10
 
#define MIN_SO2 2
#define MAX_SO2 53
#define PART_SO2 MAX_SO2-MIN_SO2

#endif