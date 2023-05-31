package chatserver;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient extends JFrame implements ActionListener {

    public static final long serialVersionUID = 1L;

    public String userName;
    public BufferedReader reader;
    public PrintWriter writer;
    public Socket socket;
    public JTextArea chatArea;
    public JTextField chatField;

    public ChatClient() {

        this.userName = JOptionPane.showInputDialog(this, "Enter your name:");

        chatArea = new JTextArea();
        chatField = new JTextField();
        JButton sendButton = new JButton("Send");
        JButton clearButton = new JButton("Clear");

        setLayout(new BorderLayout());
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(chatField);
        bottomPanel.add(sendButton);
        bottomPanel.add(clearButton);
        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(this);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	chatArea.setText("");
            }
        });

        try {
            this.socket = new Socket("localhost", 5000);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(userName + " connected");
            new Thread(new ReceiveThread()).start();
        } catch (Exception ex) {
            chatArea.append("Error connecting to server: " + ex.getMessage() + "\n");
        }

        setTitle("Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    	writer.println(userName + ": " + chatField.getText()); 
        chatField.setText("");
    }

    public class ReceiveThread implements Runnable {
        public void run() {
            try {
                while (true) {
                    String message = reader.readLine();
                    chatArea.append(message + "\n");
                }
            } catch (Exception ex) {
                chatArea.append("Error receiving message: " + ex.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}


