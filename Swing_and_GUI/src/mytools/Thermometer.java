package mytools;

public interface Thermometer {
	public double readTempF();
	public double readTempC();
	public void setTempF(double f);
	public void setTempC(double c);
	public double convertF2C(double f);
	public double convertC2F(double c);
}
