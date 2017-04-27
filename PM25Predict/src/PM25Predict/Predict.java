package PM25Predict;

public abstract class Predict {

	protected final String strFileName="C://Important";
	
	protected int HidenNeuralNumber;
	protected int LayerNumber;
	protected int InputLayerNeuralNumber;
	protected int OutputLayerNeuralNumber;
	
	
	abstract public double Run();
	
	abstract public void Train();
	
	abstract public boolean Save();
	
	abstract public boolean Load();
	
	abstract public void ResetWeight_Threadhold();
	
	abstract public void SetInputValue(double doubleInputValue[],double doubleTarget);
	
}
