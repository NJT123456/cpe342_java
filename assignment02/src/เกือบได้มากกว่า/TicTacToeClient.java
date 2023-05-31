package เกือบได้มากกว่า;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class TicTacToeClient extends JFrame {
	public static final long serialVersionUID = 1L;
	public JLabel messageLabel, scoreLabel;
	public JButton[][] boardButtons;
	public JButton resetButton;
	public JPanel boardPanel;
	public JPanel messagePanel, scorePanel;
	public Socket socket;
	public BufferedReader in;
	public PrintWriter out;
	public boolean myTurn;

	public int scoreWin;
	public int scoreLose;
	public int scoreTie;
	public int totalgame;

	public TicTacToeClient() {
		super("Tic Tac Toe");
		messageLabel = new JLabel("");
		scoreLabel = new JLabel("Total of game: 0 Wins: 0 Losses: 0 Ties: 0");
		scorePanel = new JPanel();
		scorePanel.add(scoreLabel);
		boardButtons = new JButton[3][3];
		boardPanel = new JPanel();
		messagePanel = new JPanel();
		messagePanel.add(messageLabel);
		messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		boardPanel.setLayout(new GridLayout(3, 3));
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				boardButtons[row][col] = new JButton("");
				boardButtons[row][col].setFont(new Font("Arial", Font.PLAIN, 64));
				boardButtons[row][col].addActionListener(new ButtonListener(row, col));
				boardPanel.add(boardButtons[row][col]);
			}
		}
		getContentPane().add(scorePanel, BorderLayout.NORTH);
		getContentPane().add(boardPanel, BorderLayout.CENTER);
		getContentPane().add(messagePanel, BorderLayout.SOUTH);
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		try {
			socket = new Socket("localhost", 5000);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			new Thread(new ReceiveThread()).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class ReceiveThread implements Runnable {
		public void run() {
			try {
				String response = in.readLine();
				if (response.startsWith("PLAYER")) {
					char mark = response.charAt(7);
					setTitle("Tic Tac Toe - Player " + mark);
					if (mark == 'X') {
						myTurn = true;
						messageLabel.setText("Your turn");
					} else if (mark == 'O') {
				        myTurn = false;
				        messageLabel.setText("Waiting for opponent to move");
				    }
					while (true) {
						response = in.readLine();
						if (response == null) {
							return;
						}else if(response.equals("RESTART")) {
							resetBoard();
						}
						if (response.startsWith("VALID_MOVE")) {
							int row = Integer.parseInt(response.split(" ")[1]);
							int col = Integer.parseInt(response.split(" ")[2]);
							boardButtons[row][col].setText(String.valueOf(mark));
							boardButtons[row][col].setEnabled(false);
							messageLabel.setText("Waiting for opponent to move...");
							myTurn = true;
						} else if (response.startsWith("OPPONENT_MOVED")) {
							int row = Integer.parseInt(response.split(" ")[1]);
						    int col = Integer.parseInt(response.split(" ")[2]);
						    char opponentMark = mark == 'X' ? 'O' : 'X';
						    boardButtons[row][col].setText(String.valueOf(opponentMark));
						    boardButtons[row][col].setEnabled(false);					    
						} else if (response.startsWith("MESSAGE")) {
							messageLabel.setText(response.substring(8));
						} else if (response.startsWith("VICTORY")) {
							JOptionPane.showMessageDialog(null, "Congratulations! You won!");
							out.println("RESTART");
							messageLabel.setText("Your turn.");
							totalgame++;
							scoreWin++;
							updateScoreLabel();
						} else if (response.startsWith("DEFEAT")) {
							JOptionPane.showMessageDialog(null, "Sorry, you lost.");
							out.println("RESTART");
							totalgame++;
							scoreLose++;
							updateScoreLabel();
						} else if (response.startsWith("TIE")) {
							JOptionPane.showMessageDialog(null, "Tie game.");
							out.println("RESTART");
							totalgame++;
							scoreTie++;
							updateScoreLabel();
						}
						
						
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void resetBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				boardButtons[row][col].setText("");
				boardButtons[row][col].setEnabled(true);
			}
		}
		
	}

	public void updateScoreLabel() {
		scoreLabel.setText("Total of game: " + totalgame + " Wins: " + scoreWin + " Losses: " + scoreLose + " Ties: " + scoreTie);
	}

	public class ButtonListener implements ActionListener {
		public int row;
		public int col;

		public ButtonListener(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public void actionPerformed(ActionEvent event) {
			if (myTurn) {
				out.println("MOVE " + row + " " + col);
			} else {
				out.println("MOVE " + row + " " + col);
	        }
		}
	}

	public static void main(String[] args) {
		TicTacToeClient client = new TicTacToeClient();
		client.setLocationRelativeTo(null);
	}
}
