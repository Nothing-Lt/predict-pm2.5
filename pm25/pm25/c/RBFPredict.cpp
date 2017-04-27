#include "RBFPredict.h"

RBFPredict::RBFPredict()
{	
  this->strFileName="Important";
  this->doubleCenter=(double *)malloc(sizeof(double)*HIDEN_LAYER_NEURAL_NUMBER*INPUT_LAYER_NEURAL_NUMBER);
  this->doubleDelta=(double*)malloc(sizeof(double)*HIDEN_LAYER_NEURAL_NUMBER);
  this->doubleValue=(double*)malloc(sizeof(double)*(INPUT_LAYER_NEURAL_NUMBER+HIDEN_LAYER_NEURAL_NUMBER));
  this->doubleWeight=(double*)malloc(sizeof(double)*HIDEN_LAYER_NEURAL_NUMBER);
}

RBFPredict::~RBFPredict()
{
  free(this->doubleCenter);
  free(this->doubleDelta);
  free(this->doubleValue);
  free(this->doubleWeight);
}

bool RBFPredict::Load()
{
  char *buff=(char*)malloc(BUFFER_SIZE);
  bool boolReturnValue=false;
  int iIndex=0;
  int j=0;
  char *p=NULL;
  
  ifstream ifImportant;
  ifImportant.open(this->strFileName.data());
  if(!ifImportant)
    {
      cout<<"getting important file!"<<endl;
      system("python GetImportant.py");
    }
  if(!ifImportant.is_open())
    {
      return boolReturnValue;
    }
  
  //weight
  cout<<"weight"<<endl;
  ifImportant.getline(buff,BUFFER_SIZE);
  p=strtok(buff," ");
  cout<<p<<endl;
  this->doubleWeight[0]=atof(p);
  for(iIndex=1;iIndex<HIDEN_LAYER_NEURAL_NUMBER && NULL!=p;iIndex++)
    {
      p=strtok(NULL," ");
      if(NULL==p)
	{
	  return boolReturnValue;
	}
      cout<<p<<endl;
      this->doubleWeight[iIndex]=atof(p);
    }
  
  //center
  cout<<"center"<<endl;
  for(iIndex=0;iIndex<HIDEN_LAYER_NEURAL_NUMBER;iIndex++)
    {
      ifImportant.getline(buff,BUFFER_SIZE);
      p=strtok(buff," ");
      cout<<p<<endl;
      GET_VALUE(this->doubleCenter,iIndex,0)=atof(p);
      for(j=1;j<INPUT_LAYER_NEURAL_NUMBER && NULL!=p;j++)
	{
	  p=strtok(NULL," ");
	  if(NULL==p)
	    {
	      return boolReturnValue;
	    }
	  cout<<p<<endl;
	  GET_VALUE(this->doubleCenter,iIndex,j)=atof(p);
	}
    }
  
  //delta
  cout<<"delta:"<<endl;
  ifImportant.getline(buff,BUFFER_SIZE);
  p=strtok(buff," ");
  cout<<p<<endl;
  this->doubleDelta[0]=atof(p);
  for(iIndex=1;iIndex<HIDEN_LAYER_NEURAL_NUMBER && NULL!=p;iIndex++)
    {
      p=strtok(NULL," ");
      if(NULL==p)
	{
	  return boolReturnValue;
	}
      cout<<p<<endl;
      this->doubleDelta[iIndex]=atof(p);
    }
  
  free(buff);
  boolReturnValue=true;
  return boolReturnValue;
}

void RBFPredict::SetInputValue(double doubleInputValue[],int intLength)
{
  int iIndex=0;
  
  if(intLength!=INPUT_LAYER_NEURAL_NUMBER)
    {
      return;
    }
  
  for(iIndex=0;iIndex<INPUT_LAYER_NEURAL_NUMBER;iIndex++)
    {
      GET_VALUE(this->doubleValue,0,iIndex)=doubleInputValue[iIndex];
    }
}

double RBFPredict::GetNeuralOutput(int intNeural)
{
  double doubleResult=0;
  
  int iIndex=0;
  for(;iIndex<INPUT_LAYER_NEURAL_NUMBER;iIndex++)
    {
      doubleResult+=(GET_VALUE(this->doubleValue,0,iIndex)-GET_VALUE(this->doubleCenter,intNeural,iIndex))*(GET_VALUE(this->doubleValue,0,iIndex)-GET_VALUE(this->doubleCenter,intNeural,iIndex));
    }
  doubleResult=-1*doubleResult/(2*(this->doubleDelta[intNeural]*this->doubleDelta[intNeural]));
  cout<<doubleResult<<endl;
  doubleResult=exp(doubleResult);
  return doubleResult;
}

double RBFPredict::Run()
{
  double doubleResult=0;
  
  int iIndex=0;
  for(iIndex=0;iIndex<HIDEN_LAYER_NEURAL_NUMBER;iIndex++)
    {
      GET_VALUE(this->doubleValue,1,iIndex)=this->GetNeuralOutput(iIndex);
      doubleResult+=(this->doubleWeight[iIndex]*GET_VALUE(this->doubleValue,1,iIndex));
      cout<<doubleResult<<endl;
    }
  
  return doubleResult;
}
