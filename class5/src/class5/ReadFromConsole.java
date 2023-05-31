package class5;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadFromConsole {
	public static void main(String[] args) {
		System.out.println("Enter something : ");
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String s = bufferRead.readLine();
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
