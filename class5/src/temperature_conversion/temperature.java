package temperature_conversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class temperature {
	public static void main(String[] args) throws Exception {
		// create a fahrenheit file 
		File file = new File("f.txt");
		
		// random number of fahrenheit in file
		PrintWriter pw;
		pw = new PrintWriter(new FileWriter("f.txt"));
		
//		X = read an integer from console
		Scanner x = new Scanner(System.in);
		System.out.print("Enter a number to generate : ");
		int num = x.nextInt();
		double sum = 0;
		
//		Repeat X times, Random number between 32 to 212
		Random rand = new Random();
		for (int i = 0; i < num ; i++) {
			// start + generator.nextInt(end - start + 1)
			int random = 32+rand.nextInt(212-32+1);
			pw.print(random+"\n");
			sum += random;
		}
		pw.close();

		//read from f.txt
		BufferedReader f;
		f = new BufferedReader(new FileReader("f.txt"));
		System.out.println("Fahrenheit temperatures:");
        while(f.ready())
        {
            System.out.println(f.readLine());
        }
        f.close();
        
		//create a celsius file 
		File c = new File("c.txt");
		
		// convert fahrenheit to celsius
		BufferedReader f2;
		f2 = new BufferedReader(new FileReader("f.txt"));
		PrintWriter cw = new PrintWriter(c);
		System.out.println("\nCelsius temperatures:");
		while(f2.ready())
		{
			double frh = Integer.parseInt(f2.readLine());
			double cs = (frh-32)*5/9;
			System.out.printf("%.2f%n",cs);
			cw.printf("%.2f%n",cs);
		}
		f.close();
		cw.close();
		
		double avgf = sum/num;
		System.out.print("Average Fahrenheit : " + avgf);
	  }
}
