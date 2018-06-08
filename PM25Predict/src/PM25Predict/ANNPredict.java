package PM25Predict;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;

public class ANNPredict extends Predict{
	
	public static final int HidenNeuralNumber=25;//should test from 5 to 15
	public static final int LayerNumber=2;
	public static final int InputLayerNeuralNumber=25;
	public static final int OutputLayerNeuralNumber=1;
	
	private double doubleWeight[][][];
	private double doubleThreadhold[][];
	private double doubleValue[][];
	private double doubleError[][];
	private double doubleWeightDelta[][][];
	private double doubleThreadholdDelta[][];
	private double doubleMobp=0.7;
	private double doubleRate=0.15;
	
	private double doubleTarget;
	private double doubleResult;
	
	public ANNPredict()
	{
		this.doubleWeight 			 =new double[ANNPredict.LayerNumber][][];
		this.doubleWeight[0]		 =new double[ANNPredict.InputLayerNeuralNumber][ANNPredict.HidenNeuralNumber];
		this.doubleWeight[1]		 =new double[ANNPredict.HidenNeuralNumber][ANNPredict.OutputLayerNeuralNumber];
		
		this.doubleThreadhold 		 =new double[ANNPredict.LayerNumber][];
		this.doubleThreadhold[0]	 =new double[ANNPredict.HidenNeuralNumber];
		this.doubleThreadhold[1]	 =new double[ANNPredict.OutputLayerNeuralNumber];
		
		this.doubleWeightDelta		 =new double[ANNPredict.LayerNumber][][];
		this.doubleWeightDelta[0]	 =new double[ANNPredict.InputLayerNeuralNumber][ANNPredict.HidenNeuralNumber];
		this.doubleWeightDelta[1]	 =new double[ANNPredict.HidenNeuralNumber][ANNPredict.OutputLayerNeuralNumber];
		
		this.doubleThreadholdDelta	 =new double[ANNPredict.LayerNumber][];
		this.doubleThreadholdDelta[0]=new double[ANNPredict.HidenNeuralNumber];
		this.doubleThreadholdDelta[1]=new double[ANNPredict.OutputLayerNeuralNumber];

		this.doubleError			 =new double[ANNPredict.LayerNumber][];
		this.doubleError[0]			 =new double[ANNPredict.HidenNeuralNumber];
		this.doubleError[1]			 =new double[ANNPredict.OutputLayerNeuralNumber];
	}
	
	public void SetInputValue(double doubleInput[],double doubleTarget)
	{
		doubleValue=new double[ANNPredict.LayerNumber][];
		doubleValue[0]=new double[doubleInput.length];

		for(int iIndex=0;iIndex<ANNPredict.InputLayerNeuralNumber;iIndex++){
			doubleValue[0][iIndex]=doubleInput[iIndex];
		}
		
		this.doubleValue[1]=new double[ANNPredict.HidenNeuralNumber];
		this.doubleTarget=doubleTarget;		
	}
	
	public void ResetWeight_Threadhold()
	{
		
		Random random=new Random();
		for(int i=0;i<ANNPredict.LayerNumber;i++){
			for(int j=0;j<doubleWeight[i].length;j++){
				for(int k=0;k<doubleWeight[i][j].length;k++){
					doubleWeight[i][j][k]=random.nextDouble();
					System.out.println(doubleWeight[i][j][k]+" ");
				}
			}
			
			for(int j=0;j<doubleThreadhold[i].length;j++){
				doubleThreadhold[i][j]=random.nextDouble();
			}
		}
	}
	
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
			for(int i=0;i<doubleWeight.length;i++){
				for(int j=0;j<doubleWeight[i].length;j++){
					String str="";
					for(int k=0;k<doubleWeight[i][j].length;k++){
						str+=doubleWeight[i][j][k];
						str+=" ";
					}
					str+="\r\n";
					output.write(str.getBytes());
				}
			}
			
			for(int i=0;i<doubleThreadhold.length;i++){
				String str="";
				for(int j=0;j<doubleThreadhold[i].length;j++){
					str+=doubleThreadhold[i][j];
					str+=" ";
				}
				str+="\r\n";
				output.write(str.getBytes());
			}
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
				String str;
				for(int i=0;i<doubleWeight.length;i++){
					for(int j=0;j<doubleWeight[i].length;j++){
						str=lnr.readLine();
						if(null==str){
							bReturnValue=false;
						}
						String strValue[]=str.split(" ");
						for(int k=0;k<doubleWeight[i][j].length;k++){
							doubleWeight[i][j][k]=Double.valueOf(strValue[k]);
						}
					}	
				}

				for(int i=0;i<doubleThreadhold.length;i++){
					str=lnr.readLine();
					if(null==str){
						bReturnValue=false;
						return bReturnValue;
					}
					
					String strValue[]=str.split(" ");
					for(int j=0;j<doubleThreadhold[i].length;j++){
						doubleThreadhold[i][j]=Double.valueOf(strValue[j]);
					}
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
	
	public void Show()
	{
		for(int i=0;i<doubleWeight.length;i++){
			for(int j=0;j<doubleWeight[i].length;j++){
				for(int k=0;k<doubleWeight[i][j].length;k++){
					System.out.print(doubleWeight[i][j][k]+" ");
				}
				System.out.println("");
			}
		}
		
		for(int i=0;i<doubleThreadhold.length;i++){
			for(int j=0;j<doubleThreadhold[i].length;j++){
				System.out.print(doubleThreadhold[i][j]);
			}
			System.out.println("");
		}
	}
	
	public double Run()
	{
		double dReturnValue=Double.MIN_VALUE;
		
		// compute hiden layer value
		for(int j=0;j<ANNPredict.HidenNeuralNumber;j++){
			double result=doubleThreadhold[0][j];
			for(int i=0;i<ANNPredict.InputLayerNeuralNumber;i++){
				result+=(doubleValue[0][i]*doubleWeight[0][i][j]);
			}
			doubleValue[1][j]=1/(1+Math.exp(-result));      //logsig
			//doubleValue[1][j]=result;                       //pureline 
			//doubleValue[1][j]=2/(1+Math.exp(-2*result))-1;    //tansig
		}
		
		dReturnValue=this.doubleThreadhold[1][0];
		for(int i=0;i<ANNPredict.HidenNeuralNumber;i++){
			dReturnValue+=(doubleValue[1][i]*doubleWeight[1][i][0]);
		}
		this.doubleResult=dReturnValue;//=1/(1+Math.exp(-dReturnValue));
		return dReturnValue;
	}
	
	public void Train()
	{	
		doubleError[1][0]=this.doubleTarget-this.doubleResult;
		for(int i=ANNPredict.LayerNumber-1;i>=0;i--){
			for(int j=0;j<doubleWeight[i].length;j++){
				double z=0;
				for(int k=0;k<doubleWeight[i][j].length;k++){
					z=doubleError[i][k]*doubleWeight[i][j][k];
					doubleWeightDelta[i][j][k]=this.doubleMobp*doubleWeightDelta[i][j][k]+this.doubleRate*doubleError[i][k]*doubleValue[i][j];
					doubleWeight[i][j][k]+=doubleWeightDelta[i][j][k];
				}
				if(i>0){
					doubleError[i-1][j]=z*doubleValue[i][j]*(1-doubleValue[i][j]);
				}
			}
			
			for(int j=0;j<doubleThreadhold[i].length;j++){
				this.doubleThreadholdDelta[i][j]=this.doubleMobp*doubleThreadholdDelta[i][j]+this.doubleRate*doubleError[i][j];
				this.doubleThreadhold[i][j]+=this.doubleThreadholdDelta[i][j];
			}
		}
	}

}
