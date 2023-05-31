package chatserver;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    public static List<PrintWriter> writers = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        System.out.println("Chat server is running.");

        ServerSocket serverSocket = new ServerSocket(5000);

        while (true) {

            Socket clientSocket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            writers.add(writer);

            Thread readingThread = new Thread(new ReadingThread(reader));
            readingThread.start();
        }
    }

    public static class ReadingThread implements Runnable {
        public BufferedReader reader;

        public ReadingThread(BufferedReader reader) {
            this.reader = reader;
        }

        public void run() {
            String message;

            try {
                while ((message = reader.readLine()) != null) {
                	System.out.println(message);

                    for (PrintWriter writer : writers) {
                    	writer.println(message);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading from client: " + e);
            }
        }
    }
}

