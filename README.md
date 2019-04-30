# predict-pm2.5
predict pm25
Graduation Project, for prediction of PM2.5 in Beijing.

1. Raspberry Pi send HTTP request to server, server train the neural network, and send back the trained weight and bias value.
2. Raspberry Pi send HTTP resuqst to TianQiHouBao and Weather Underground , get the information by using python.
3. Run RBFnn to predict the PM2.5 of the next day, show it on 128x64 LCD screen.

## PM2.5:Java code 
Implemented Radial Basis Function Neural Network, and Web crawler, for gathering data from TianQiHouBao and Weather Underground.

## PM25 : C/C++ and Python code.
Run on raspberry Pi,Python for the Web crawler, C++ for the Radial Basis Function Neural Network.

## PM25 Predict Server.
The Http interface the raspberry Pi.
                              
