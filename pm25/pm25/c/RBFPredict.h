#ifndef PREDICT_H
#define PREDICT_H
#include <math.h>
#include "main.h"

class RBFPredict
{
 public:
  RBFPredict();
  void SetInputValue(double doubleInputValue[],int intLength);
  double Run();
  bool Load();
  ~RBFPredict();
 private:
  string strFileName;
  double *doubleCenter;
  double *doubleDelta;
  double *doubleValue;
  double *doubleWeight;
  
  double GetNeuralOutput(int intNeural);
};

#define LAYER_NUMBER 2
#define INPUT_LAYER_NEURAL_NUMBER 7
#define HIDEN_LAYER_NEURAL_NUMBER 30

#define GET_VALUE(center,x,y) center[x*INPUT_LAYER_NEURAL_NUMBER+y]

#endif
