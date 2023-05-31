package exinclass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class serversocket {

	public static void main(String[] args) throws Exception{
		System.out.println("Waiting for clients...");
		ServerSocket serverSock = new ServerSocket(5000);
		Socket sock = serverSock.accept();
		System.out.println("Connection");
//		InputStreamReader stream = new InputStreamReader(sock.getInputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		String message = in.readLine();
		PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
		out.println("Server says : " + message);
		

	}

}
