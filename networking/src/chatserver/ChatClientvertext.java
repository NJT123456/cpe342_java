package chatserver;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClientvertext {
	public String userName;
	public BufferedReader reader;
	public PrintWriter writer;
	public Socket socket;

	public ChatClientvertext() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your name : ");
		this.userName = scanner.nextLine();
		
		try {
			this.socket = new Socket("localhost", 5000);
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println(userName + " connected");
			new Thread(new ReceiveThread()).start();
			
			while(true) {
				String message = scanner.nextLine();
				sendMessage(message);
			}
		} catch (Exception ex) {
			System.err.println("Error connecting to server: " + ex.getMessage());
		}

	}

	public void sendMessage(String message) {
		writer.println(userName + " : " + message);
	}

	public class ReceiveThread implements Runnable {
		public void run() {
			try {
				while (true) {
					String message = reader.readLine();
					System.out.println(message + "\n");
				}
			} catch (Exception ex) {
				System.err.println("Error receiving message : " + ex.getMessage());
			}
		}
	}

	public static void main(String args[]) {
		new ChatClientvertext();
	}
}
