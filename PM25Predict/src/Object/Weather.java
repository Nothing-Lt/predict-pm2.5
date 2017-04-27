package Object;

public class Weather {

	public static final int DATA_NUMBER=25;
	
	private String strDate;
	private String strLocation;
	private double MeanPressure_hPa;
	private double MinPressure_hPa;
	private double MaxPressure_hPa;
	private double MaxEyeSight_KM;
	private double MinEyeSight_KM;
	private double MeanEyeSight_KM;
	private double MaxWindSpeed_KM;
	private double MeanWindSpeed_KM;
	private double AverrageTemp;
	private double Rain_MM;
	private double WindDirDegrees;
	private double PM25;
	private double PM10;
	private double MaxTemp_C;
	private double MinTemp_C;
	private double MeanDewPoint;
	private double MinDewPoint;
	private double MaxSweat;
	private double MeanSweat;
	private double MinSweat;
	private double aqi;
	private double Co;
	private double No2;
	private double So2;
	private double O3;
	
	private double Target;
	
	public double getTarget() {
		return Target;
	}
	public void setTarget(double target) {
		Target = target;
	}
	public double getMeanPressure_hPa() {
		return MeanPressure_hPa;
	}
	public void setMeanPressure_hPa(double meanPressure_hPa) {
		this.MeanPressure_hPa = meanPressure_hPa;
	}
	public double getMinPressure_hPa() {
		return MinPressure_hPa;
	}
	public void setMinPressure_hPa(double minPressure_hPa) {
		this.MinPressure_hPa = minPressure_hPa;
	}
	public double getMaxPressure_hPa() {
		return MaxPressure_hPa;
	}
	public void setMaxPressure_hPa(double maxPressure_hPa) {
		this.MaxPressure_hPa = maxPressure_hPa;
	}
	public double getMaxEyeSight_KM() {
		return MaxEyeSight_KM;
	}
	public double getO3() {
		return O3;
	}
	public void setO3(double o3) {
		O3 = o3;
	}
	public void setMaxEyeSight_KM(double maxEyeSight_KM) {
		this.MaxEyeSight_KM = maxEyeSight_KM;
	}
	public double getMinEyeSight_KM() {
		return MinEyeSight_KM;
	}
	public void setMinEyeSight_KM(double minEyeSight_KM) {
		this.MinEyeSight_KM = minEyeSight_KM;
	}
	public double getMeanEyeSight_KM() {
		return MeanEyeSight_KM;
	}
	public void setMeanEyeSight_KM(double meanEyeSight_KM) {
		this.MeanEyeSight_KM = meanEyeSight_KM;
	}
	public double getMaxWindSpeed_KM() {
		return MaxWindSpeed_KM;
	}
	public void setMaxWindSpeed_KM(double maxWindSpeed_KM) {
		this.MaxWindSpeed_KM = maxWindSpeed_KM;
	}
	public double getMeanWindSpeed_KM() {
		return MeanWindSpeed_KM;
	}
	public void setMeanWindSpeed_KM(double meanWindSpeed_KM) {
		this.MeanWindSpeed_KM = meanWindSpeed_KM;
	}
	public double getRain_MM() {
		return Rain_MM;
	}
	public void setRain_MM(double rain_MM) {
		this.Rain_MM = rain_MM;
	}
	public double getWindDirDegrees() {
		return WindDirDegrees;
	}
	public void setWindDirDegrees(double windDirDegrees) {
		this.WindDirDegrees = windDirDegrees;
	}
	public double getPM25() {
		return PM25;
	}
	public void setPM25(double pM25) {
		this.PM25 = pM25;
	}
	public double getPM10() {
		return PM10;
	}
	public void setPM10(double pM10) {
		this.PM10 = pM10;
	}
	public double getMaxTemp_C() {
		return MaxTemp_C;
	}
	public void setMaxTemp_C(double maxTemp_C) {
		this.MaxTemp_C = maxTemp_C;
	}
	public double getMinTemp_C() {
		return MinTemp_C;
	}
	public void setMinTemp_C(double minTemp_C) {
		this.MinTemp_C = minTemp_C;
	}
	public double getMeanDewPoint() {
		return MeanDewPoint;
	}
	public void setMeanDewPoint(double meanDewPoint) {
		this.MeanDewPoint = meanDewPoint;
	}
	public double getMinDewPoint() {
		return MinDewPoint;
	}
	public void setMinDewPoint(double minDewPoint) {
		this.MinDewPoint = minDewPoint;
	}
	public double getMaxSweat() {
		return MaxSweat;
	}
	public void setMaxSweat(double maxSweat) {
		this.MaxSweat = maxSweat;
	}
	public double getMeanSweat() {
		return MeanSweat;
	}
	public void setMeanSweat(double meanSweat) {
		this.MeanSweat = meanSweat;
	}
	public double getMinSweat() {
		return MinSweat;
	}
	public void setMinSweat(double minSweat) {
		this.MinSweat = minSweat;
	}
	public double getAqi() {
		return aqi;
	}
	public void setAqi(double aqi) {
		this.aqi = aqi;
	}
	public double getCo() {
		return Co;
	}
	public void setCo(double co) {
		this.Co = co;
	}
	public double getNo2() {
		return No2;
	}
	public void setNo2(double no2) {
		this.No2 = no2;
	}
	public double getSo2() {
		return So2;
	}
	public void setSo2(double so2) {
		this.So2 = so2;
	}
	public double getAverrageTemp() {
		return AverrageTemp;
	}
	public void setAverrageTemp(double averrageTemp) {
		this.AverrageTemp = averrageTemp;
	}
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getStrLocation() {
		return strLocation;
	}
	public void setStrLocation(String strLocation) {
		this.strLocation = strLocation;
	}
}
