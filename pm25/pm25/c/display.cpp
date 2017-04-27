#include "display.h"

bool Display::InitDisplay()
{
#ifndef UBUNTU_DEBUG
  if (wiringPiSetup() < 0)
    {
      return false ;
    }
  this->lcdFD = lcdInit(2, 16, 4, RS, EN, D0, D1, D2, D3, D0, D1, D2, D3);
  return true;
#else
  return true;
#endif
}

void Display::print(char *buff,int position)
{
#ifndef UBUNTU_DEBUG
  lcdPosition(lcdFD, 0,position);
  lcdPrintf(lcdFD,buff);
#else
  int i=0;
  for(;i<position;i++)
    {
      printf("\n");
    }
  printf("%s\n",buff);
#endif
}
