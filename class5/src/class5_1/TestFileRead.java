package class5_1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
public class TestFileRead {
// this has errors, you know how to catch exceptions?
	public static void main(String[] args) throws Exception{
		BufferedReader br;
// where is this test.txt?
		br = new BufferedReader(new FileReader("test.txt"));
		while (br.ready()) {
			System.out.println(br.readLine());
		}
	}
}
