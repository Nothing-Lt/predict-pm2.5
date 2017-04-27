package PM25Predict;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;

public class RBFPredict extends Predict {
	
	private final int LayerNumber=2;
	private final int HidenLayerNeuralNumber=30;
	private final int OutputLayerNeuralNumber=1;
	private final int InputLayerNeuralNumber=7;
	private final double LearnRate=0.02;
	
	double doubleValue[][] =null;
	double doubleWeight[]  =null;
	double doubleCenter[][]=null;
	double doubleDelta[]   =null;
	
	double doubleTarget;
	double doubleResult;
	
	public RBFPredict()
	{
		doubleValue   =new double[this.LayerNumber][];
		doubleValue[1]=new double[this.HidenLayerNeuralNumber];
		
		doubleCenter  =new double[this.HidenLayerNeuralNumber][this.InputLayerNeuralNumber];
		doubleDelta   =new double[this.HidenLayerNeuralNumber];
		
		doubleWeight  =new double[this.HidenLayerNeuralNumber];
	}
	
	@Override
	public void SetInputValue(double doubleInputValue[],double doubleTarget)
	{
		doubleValue[0]=new double[this.InputLayerNeuralNumber];
		for(int i=0;i<this.InputLayerNeuralNumber;i++){
			doubleValue[0][i]=doubleInputValue[i];
		}
		
		this.doubleTarget=doubleTarget;
	}
	
	@Override
	public double Run()
	{
		double doubleResult=0;
		for(int j=0;j<this.HidenLayerNeuralNumber;j++){
			doubleValue[1][j]=this.GetNeuralOutput(this.doubleCenter[j], this.doubleValue[0], this.doubleDelta[j]);
		}
		
		for(int j=0;j<this.HidenLayerNeuralNumber;j++){
			doubleResult+=doubleWeight[j]*doubleValue[1][j];
		}
		
		this.doubleResult=doubleResult;
		return doubleResult;
	}
	
	private double GetNeuralOutput(double doubleCenter[],double doubleInput[],double doubleDelta)
	{
		double doubleResult=Double.MIN_VALUE;
		if(null==doubleCenter||null==doubleInput||doubleCenter.length!=doubleInput.length){
			return doubleResult;
		}
		double doubleTemp=0;
		for(int i=0;i<doubleCenter.length;i++){
			doubleTemp+=((doubleCenter[i]-doubleInput[i])*(doubleCenter[i]-doubleInput[i])); //
		}
		
		doubleTemp=doubleTemp/((doubleDelta*doubleDelta)*2);
		doubleTemp=doubleTemp*-1;
		return Math.exp(doubleTemp);
	}
	
	@Override
	public void Train()
	{
		double doubleError1=-(this.doubleTarget-this.doubleResult);

		double doubleWeightDelta[]=new double[this.HidenLayerNeuralNumber];
		for(int i=0;i<this.HidenLayerNeuralNumber;i++){
			doubleWeightDelta[i]=doubleError1*doubleValue[1][i];
		}
		
		double doubleError2[]=new double[this.HidenLayerNeuralNumber];
		for(int i=0;i<this.HidenLayerNeuralNumber;i++){
			doubleError2[i]=doubleWeight[i]*doubleError1*doubleValue[1][i];
		}
		
		double doubleCenterDelta[]=new double[this.InputLayerNeuralNumber];
		double doubleDeltaDelta[]=new double[this.HidenLayerNeuralNumber];
		for(int i=0;i<this.HidenLayerNeuralNumber;i++){
			for(int j=0;j<this.InputLayerNeuralNumber;j++){
				doubleCenterDelta[j]=0;
			}
			
			for(int j=0;j<this.InputLayerNeuralNumber;j++){
				doubleCenterDelta[j]=doubleValue[0][j]-doubleCenter[i][j];
				doubleCenterDelta[j]=doubleError2[i]*doubleCenterDelta[j]/(doubleDelta[i]*doubleDelta[i]*doubleDelta[i]);
			}
			
			double doubleTemp=0;
			for(int j=0;j<this.InputLayerNeuralNumber;j++){
				doubleTemp+=(doubleValue[0][j]-doubleCenter[i][j])*(doubleValue[0][j]-doubleCenter[i][j]);
			}
			doubleDeltaDelta[i]=doubleError2[i]*doubleTemp/(doubleDelta[i]*doubleDelta[i]*doubleDelta[i]);
			
			doubleWeight[i]=doubleWeight[i]-LearnRate*doubleWeightDelta[i];
			for(int j=0;j<this.InputLayerNeuralNumber;j++){
				doubleCenter[i][j]=doubleCenter[i][j]-LearnRate*doubleCenterDelta[j];
			}
			doubleDelta[i]=doubleDelta[i]-LearnRate*doubleDeltaDelta[i];
		}
	}
	
	@Override
	@SuppressWarnings("finally")
	public boolean Save()
	{
		boolean bReturnValue=false;
		File f=new File(strFileName);
		if(f.exists()){
			f.delete();
		}
		else{
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			FileOutputStream output=new FileOutputStream(f);
			
			String str="";
			for(int i=0;i<doubleWeight.length;i++){	
					str+=doubleWeight[i];
					str+=" ";
			}
			str+="\r\n";
			output.write(str.getBytes());
			
			for(int i=0;i<doubleCenter.length;i++){
				str="";
				for(int j=0;j<doubleCenter[i].length;j++){
					str+=doubleCenter[i][j];
					str+=" ";
				}
				str+="\r\n";
				output.write(str.getBytes());
			}
			
			str="";
			for(int i=0;i<doubleDelta.length;i++){
				str+=doubleDelta[i];
				str+=" ";
			}
			str+="\r\n";
			output.write(str.getBytes());
			output.close();
			bReturnValue=true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return bReturnValue;
		}
	}
	
	@Override
	public boolean Load()
	{
		boolean bReturnValue=false;
		File f=new File(this.strFileName);
		if(!f.exists()){
			return bReturnValue;
		}
		else{
			LineNumberReader lnr=null;
			try {
				@SuppressWarnings("resource")
				FileReader filereader=new FileReader(this.strFileName);
				lnr=new LineNumberReader(filereader);
				String str=lnr.readLine();
				if(null==str){
					return bReturnValue;
				}
				String strValues[]=str.split(" ");
				for(int i=0;i<this.HidenLayerNeuralNumber;i++){
					doubleWeight[i]=Double.valueOf(strValues[i]);
				}
				
				for(int i=0;i<this.HidenLayerNeuralNumber;i++){
					str=lnr.readLine();
					if(null==str){
						return bReturnValue;
					}
					strValues=str.split(" ");
					for(int j=0;j<this.InputLayerNeuralNumber;j++){
						doubleCenter[i][j]=Double.valueOf(strValues[j]);
					}
				}
				
				str=lnr.readLine();
				if(null==str){
					return bReturnValue;
				}
				strValues=str.split(" ");
				for(int i=0;i<this.HidenLayerNeuralNumber;i++){
					doubleDelta[i]=Double.valueOf(strValues[i]);
				}
				lnr.close();
				filereader.close();
				bReturnValue=true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					lnr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					lnr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return bReturnValue;
	}
	
	@Override
	public void ResetWeight_Threadhold()
	{
		
		Random random=new Random();
		for(int i=0;i<this.HidenLayerNeuralNumber;i++){
			for(int j=0;j<this.InputLayerNeuralNumber;j++){
				doubleCenter[i][j]=random.nextDouble();
			}
		}
		
		for(int i=0;i<this.HidenLayerNeuralNumber;i++){
			doubleDelta[i]=random.nextDouble();
		}
		
		for(int i=0;i<this.HidenLayerNeuralNumber;i++){
			doubleWeight[i]=random.nextDouble();
		}
	}

}
