package mytools;

public class MyThermometer implements Thermometer {
	public double temperature;
	
	public double readTempF()
	{
		return temperature;
	}
	
	public double readTempC()
	{
		return convertF2C(temperature);
	}
	
	public void setTempF(double f)
	{
		temperature = f;
	}
	
	public void setTempC(double c)
	{
		temperature = convertF2C(c); 
	}
	
	public double convertF2C(double f)
	{
		return (f-32)*5/9;
	}
	
	public double convertC2F(double c)
	{
		return c*9/5+32;
	}
	
}
