package class5;

import java.util.Scanner;

public class ConsoleScanner {
	public static void main(String[] args) {
		System.out.println("Enter something : ");
		String str;
		Scanner scanIn = new Scanner(System.in);
		str = scanIn.nextLine();
		scanIn.close();
		System.out.println(str);
	}
}
