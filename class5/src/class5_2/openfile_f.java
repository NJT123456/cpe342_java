package class5_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class openfile_f {
	public static void main(String[] args) throws Exception{
		BufferedReader f;
// where is this test.txt?
		f = new BufferedReader(new FileReader("f.txt"));
		while (f.ready()) {
			System.out.println(f.readLine());
		}
	}
}
