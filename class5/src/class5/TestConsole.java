package class5;

import java.io.Console;

public class TestConsole {
// not working in eclipse, have to run in cmd
	public static void main(String args[]) {
		Console c = System.console();
		c.printf("Enter something : ");
		String s = c.readLine();
		System.out.println(s);
	}
}
