package เกือบได้มากกว่า;

import java.io.*;
import java.net.*;

public class TicTacToeServerComputer {
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
				out2 = new PrintWriter(socket2.getOutputStream(), true);

				out1.println("PLAYER X");
				out2.println("PLAYER O");
				while (true) {
					String command;
					if (currentPlayer == 1) {
						command = in1.readLine();
					} else {
						command = "MOVE";
					}
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
						}

					} else if (currentPlayer == 2) {
						Move bestMove = getBestMove();
						int row = bestMove.row;
						int col = bestMove.col;
//				        int row = (int)(Math.random() * 3);
//				        int col = (int)(Math.random() * 3);
						if (isValidMove(row, col)) {
							board[row][col] = 'O';
							out2.println("VALID_MOVE " + row + " " + col);
							out1.println("OPPONENT_MOVED " + row + " " + col);
							if (isWinner('O')) {
								out2.println("VICTORY");
								out1.println("DEFEAT");
								resetGame();
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
						
					}
				}

			} catch (IOException e) {
				System.out.println("Error: " + e);
			}
		}

		public void stopGame() {
			Thread.currentThread().interrupt();
		}

		public void restartGame() {
			stopGame();
			Thread newThread = new Thread(this);
			newThread.start();
		}

		public void resetGame() {
			board = new char[3][3];
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					board[row][col] = '-';
				}
			}
//			currentPlayer = 1;
			out1.println("RESTART");
			out2.println("RESTART");
			restartGame();
		}

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

			if (board[0][0] == token && board[1][1] == token && board[2][2] == token) {
				return true;
			}

			if (board[0][2] == token && board[1][1] == token && board[2][0] == token) {
				return true;
			}

			return false;
		}

		public boolean isValidMove(int row, int col) {
			if (row < 0 || row > 2 || col < 0 || col > 2) {
				return false;
			} else if (board[row][col] != '-') {
				return false;
			} else {
				return true;
			}
		}

		public Move getBestMove() {
			int bestScore = Integer.MIN_VALUE;
			Move bestMove = null;
			//ฟังก์ชัน getBestMove() ค้นหาท่าเดินที่ดีที่สุดสำหรับ AI โดยวนลูปตามตารางขนาด 3x3 และทดลองทุกตำแหน่งที่ว่างบนกระดาน โดยตั้งค่าเครื่องหมาย 'O'
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (board[row][col] == '-') {
						board[row][col] = 'O';
						//minimax() เพื่อคำนวณความเป็นไปได้ที่ดีที่สุดสำหรับสถานการณ์นี้ และเมื่อการคำนวณเสร็จสิ้น จะส่งกลับท่าเดินที่ดีที่สุดให้กลับไปยังเกม
						int score = minimax(board, 0, false);
						board[row][col] = '-';
						if (score > bestScore) {
							bestScore = score;
							bestMove = new Move(row, col);
						}
					}
				}
			}

			return bestMove;
		}

		public int minimax(char[][] board, int depth, boolean isMaximizingPlayer) {
			//กรณีที่ผู้ชนะเป็น 'X' จะคืนค่า -1
			if (isWinner('X')) {
				return -1;
			} else if (isWinner('O')) {
				return 1;
			} else if (isBoardFull()) {
				return 0;
			}
			//ฟังก์ชันจะตรวจสอบว่าตอนนี้เป็นเทิร์นของผู้เล่นที่ต้องการชนะสูงสุดหรือไม่ หากเป็นเช่นนั้น (isMaximizingPlayer = true) 
			if (isMaximizingPlayer) {
				int bestScore = Integer.MIN_VALUE;
				for (int row = 0; row < 3; row++) {
					for (int col = 0; col < 3; col++) {
						if (board[row][col] == '-') {
							// ฟังก์ชันจะวนลูปตามตำแหน่งบนกระดานที่ว่าง และทดลองวางเครื่องหมาย 'O' ในตำแหน่งนั้น จากนั้นจะเรียกใช้ฟังก์ชัน minimax() ต่อโดยเพิ่มระดับ depth และเปลี่ยนผู้เล่นที่ต้องการคะแนนสูงสุดเป็น false และเก็บค่าคะแนนที่ดีที่สุดไว้
							board[row][col] = 'O';
							int score = minimax(board, depth + 1, false);
							board[row][col] = '-';
							bestScore = Math.max(score, bestScore);
						}
					}
				}
				return bestScore;
				//ในกรณีที่ไม่ใช่เทิร์นของผู้เล่นที่ต้องการชนะ (isMaximizingPlayer = false) มีกระบวนการที่คล้ายกันกับกรณีของ 'O' 
			} 
			else {
				int bestScore = Integer.MAX_VALUE;
				for (int row = 0; row < 3; row++) {
					for (int col = 0; col < 3; col++) {
						if (board[row][col] == '-') {
							// แต่ความแตกต่างคือในการทดลองวางเครื่องหมายในตำแหน่งว่าง จะเปลี่ยนเครื่องหมายเป็น 'X' และเปลี่ยนผู้เล่นที่ต้องการคะแนนสูงสุดเป็น true
							board[row][col] = 'X';
							int score = minimax(board, depth + 1, true);
							board[row][col] = '-';
							bestScore = Math.min(score, bestScore);
						}
					}
				}
				return bestScore;
			}
		}
	}

	public static class Move {
		public int row;
		public int col;

		public Move(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
