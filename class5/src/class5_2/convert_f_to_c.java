package class5_2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;

public class convert_f_to_c {
	public static void main(String[] args) throws Exception{
		BufferedReader f;
		f = new BufferedReader(new FileReader("f.txt"));
		File c = new File("C:\\Users\\USER\\Desktop\\java\\class5\\src\\class5_2\\c.txt");
//		Scanner fr = new Scanner(f);
		PrintWriter cw = new PrintWriter(c);
		int total = 0, count = 0;
		while(f.ready())
		{
			int frh = Integer.parseInt(f.readLine());
			int cs = (frh-32)*5/9;
			System.out.print(cs+ "\n");
			cw.write(Integer.toString(cs) + "\n");
			
			total += frh;
			count++;
		}
		f.close();
		cw.close();
		
		double avgf = (double) total / count;
		System.out.print("Average Fahrenheit : " + avgf);
	}
}

