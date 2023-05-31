package class5_1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ScanAFile {
// no file and error handling here also
	public static void main(String[] args) throws Exception{
		Scanner scn = new Scanner(new FileReader("test.txt"));
		while (scn.hasNext()) {
			System.out.println(scn.next());
		}
	}
}
