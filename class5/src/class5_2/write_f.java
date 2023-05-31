package class5_2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class write_f {
	public static void main(String[] args) throws Exception {
		PrintWriter pw;
		pw = new PrintWriter(new FileWriter("f.txt"));
//		X = read an integer from console
		Scanner x = new Scanner(System.in);
		System.out.print("Enter a number : ");
		int num = x.nextInt();
		
//		Repeat X times, Random number between 32 to 212
		Random rand = new Random();
		for (int i = 0; i <= num ; i++) {
			// start + generator.nextInt(end - start + 1);
			int random = 32+rand.nextInt(212-32+1);
			System.out.print(random+ "\n");
			pw.write(Integer.toString(random) + "\n");
		}
		pw.close(); // check content of dest.txt before and after uncomment this line
	}
}
