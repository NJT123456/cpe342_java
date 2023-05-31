package mytools;

import mytools.*;
public class testMyThermo {
	public static void main(String[] args) {
		MyThermometer t = new MyThermometer();
		for (int i=0;i<=100;i++){
			t.setTempC((double)i);
			System.out.printf("%dc = %5.1ff, or %5.1ff\n", i,t.convertC2F((double)i), t.readTempF());
			}
			for(int i = 32; i<=212;i++){
			t.setTempF((double)i);
			System.out.printf("%df = %5.1fc, or %5.1fc\n", i,t.convertF2C((double)i), t.readTempC());
			}
	}
}
