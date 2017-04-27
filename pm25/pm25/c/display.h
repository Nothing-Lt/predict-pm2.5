#ifndef DISPLAY_H
#define DISPLAY_H

//#define UBUNTU_DEBUG

#include <stdio.h>
#include <string.h>
#ifndef UBUNTU_DEBUG
#include <wiringPi.h>
#include <lcd.h>
#endif

#define RS 3
#define EN 14
#define D0 4
#define D1 12
#define D2 13
#define D3 6

class Display
{
 public:
  //Display();
  //~Display();
  bool InitDisplay();
  void print(char *,int);
 private:
  int lcdFD;
};
#endif
