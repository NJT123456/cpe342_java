package เกือบได้มากกว่า;

import java.io.*;
import java.net.*;

public class TicTacToeServer {
	public static int playerCount = 0;

	public static void main(String[] args) throws Exception {
		System.out.println("Tic Tac Toe Server is running.");

		ServerSocket serverSocket = new ServerSocket(5000);

		while (true) {
			Socket socket1 = serverSocket.accept();
			System.out.println("Player X connected.");
			Socket socket2 = serverSocket.accept();
			System.out.println("Player O connected.");
			Thread thread = new Thread(new Game(socket1, socket2));
			thread.start();
		}
	}

	public static class Game implements Runnable {

		public Socket socket1;
		public Socket socket2;
		public char[][] board = new char[3][3];
		public int currentPlayer = 1;
		public PrintWriter out1;
		public PrintWriter out2;

		public Game(Socket socket1, Socket socket2) {
			this.socket1 = socket1;
			this.socket2 = socket2;

			playerCount += 2;
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					board[row][col] = '-';
				}
			}
		}

		public void run() {
			try {
				BufferedReader in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
				out1 = new PrintWriter(socket1.getOutputStream(), true);
				BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
				out2 = new PrintWriter(socket2.getOutputStream(), true);

				out1.println("PLAYER X");
				out2.println("PLAYER O");

				while (true) {
					String command = in1.readLine();
					if (command == null) {
						return;
					} else if (command.equals("RESTART")) {
						// Reset game state
						resetGame();
					} else if (command.startsWith("MOVE ")) {
						
						int row = Integer.parseInt(command.split(" ")[1]);
						int col = Integer.parseInt(command.split(" ")[2]);

						if (currentPlayer == 1) {
							if (isValidMove(row, col)) {
								board[row][col] = 'X';
								out1.println("VALID_MOVE " + row + " " + col);
								out2.println("OPPONENT_MOVED " + row + " " + col);
								if (isWinner('X')) {
									out1.println("VICTORY");
									out2.println("DEFEAT");
								} else if (isBoardFull()) {
									out1.println("TIE");
									out2.println("TIE");
								} else {
									currentPlayer = 2;
									out1.println("");
									out2.println("MESSAGE Opponent's turn.");
								}
							} else {
								out1.println("");
								out2.println("MESSAGE Invalid move.");
							}
						}else {
							out1.println("MESSAGE It's not your turn.");
							out2.println("");
						}

					}

					command = in2.readLine();
					if (command == null) {
						return;
					} else if (command.equals("RESTART")) {
						// Reset game state
						resetGame();
					} else if (command.startsWith("MOVE")) {
						int row = Integer.parseInt(command.split(" ")[1]);
						int col = Integer.parseInt(command.split(" ")[2]);

						if (currentPlayer == 2) {
							if (isValidMove(row, col)) {
								board[row][col] = 'O';
								out2.println("VALID_MOVE " + row + " " + col);
								out1.println("OPPONENT_MOVED " + row + " " + col);
								if (isWinner('O')) {
									out2.println("VICTORY");
									out1.println("DEFEAT");

								} else if (isBoardFull()) {
									out2.println("TIE");
									out1.println("TIE");
								} else {
									// สลับเล่น
									currentPlayer = 1;
									out2.println("");
									out1.println("MESSAGE Opponent's turn.");
								}
							} else {
								out2.println("");
								out1.println("MESSAGE Invalid move.");
							}
						}else {
							out1.println("");
							out2.println("MESSAGE It's not your turn.");
						}

					}
				}
			} catch (IOException e) {
				System.out.println("Error: " + e);
			}
		}

		public void resetGame() {
			board = new char[3][3];
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					board[row][col] = '-';
				}
			}
			out1.println("RESTART");
			out2.println("RESTART");
			Thread.currentThread().interrupt();
			Thread newThread = new Thread(this);
			newThread.start();
		}
		//ref : https://junilearning.com/blog/coding-projects/java-beginner-tic-tac-toe-tutorial/
		public boolean isBoardFull() {
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (board[row][col] == '-') {
						return false;
					}
				}
			}
			return true;
		}

		public boolean isWinner(char token) {
			for (int row = 0; row < 3; row++) {
				if (board[row][0] == token && board[row][1] == token && board[row][2] == token) {
					return true;
				}
			}

			for (int col = 0; col < 3; col++) {
				if (board[0][col] == token && board[1][col] == token && board[2][col] == token) {
					return true;
				}
			}
			//diagonals right
			if (board[0][0] == token && board[1][1] == token && board[2][2] == token) {
				return true;
			}
			//diagonals left
			if (board[0][2] == token && board[1][1] == token && board[2][0] == token) {
				return true;
			}

			return false;
		}

		public boolean isValidMove(int row, int col) {
			if (row < 0 || row > 2 || col < 0 || col > 2) {
				return false;//แสดงว่าที่อยู่ที่กำหนดไม่อยู่ในขอบเขตที่ถูกต้องของกระดาน 3x3
			} else if (board[row][col] != '-') {
				return false;//แสดงว่าช่องดังกล่าวไม่ว่าง และไม่สามารถเลือกได้
			} else {
				return true;//แสดงว่าการเลือกที่อยู่ในแถวและคอลัมน์ที่กำหนดถูกต้องและสามารถเลือกได้
			}
		}
	}
}
