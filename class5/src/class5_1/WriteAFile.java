package class5_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;

public class WriteAFile {
// handle run-time error also
	public static void main(String[] args) throws Exception {
		PrintWriter pw;
		pw = new PrintWriter(new FileWriter("test.txt"));
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		System.out.println("enter something :");
		while (!(s = br.readLine()).isEmpty()) {
			pw.println(s);
			System.out.println("enter something :");
		}
		pw.close(); // check content of dest.txt before and after uncomment this line
	}
}
