package exinclass;

import java.io.*;
import java.net.Socket;

public class socket {
	public static void main(String arg[]) throws Exception{
		System.out.println("Client start");
		Socket chatSocket = new Socket("127.0.0.1", 5000);
//		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("Enter a string");
//		
//		String str = userInput.readLine();
//		PrintWriter out = new PrintWriter(chatSocket.getOutputStream(),true);
//		out.println(str);
//		BufferedReader in = new BufferedReader(new InputStreamReader(chatSocket.getInputStream()));
//		System.out.println(in.readLine());
		
		PrintWriter writer = new PrintWriter(chatSocket.getOutputStream(),true);
		writer.println("Message");
		BufferedReader in = new BufferedReader(new InputStreamReader(chatSocket.getInputStream()));
		System.out.println(in.readLine());
	}

}
