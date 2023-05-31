package tictactoegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    private JButton[][] buttons;
    private JLabel player1ScoreLabel, player2ScoreLabel, playerTurnLabel, playTiesLabel, numPlaysLabel;
    private int player1Score, player2Score, currentPlayer, numPlays, numTies, totalplays;

    public TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel boardPanel = new JPanel(new GridLayout(3,3));
        buttons = new JButton[3][3];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        JPanel scorePanel = new JPanel(new GridLayout(5,1));
        numPlaysLabel = new JLabel("Number of Plays : " + totalplays);
        player1ScoreLabel = new JLabel("Player 1: " + player1Score);
        player2ScoreLabel = new JLabel("Player 2: " + player2Score);
        playTiesLabel = new JLabel("Ties : " + numTies);
        playerTurnLabel = new JLabel("Player 1's turn");
        scorePanel.add(numPlaysLabel);
        scorePanel.add(player1ScoreLabel);
        scorePanel.add(player2ScoreLabel);
        scorePanel.add(playTiesLabel);
        scorePanel.add(playerTurnLabel);

        getContentPane().add(boardPanel, BorderLayout.CENTER);
        getContentPane().add(scorePanel, BorderLayout.EAST);
        setSize(400, 400);
        setVisible(true);

        currentPlayer = 1;
        numPlays = 0;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        if (!button.getText().equals("")) {
            return;
        }
//https://junilearning.com/blog/coding-projects/java-beginner-tic-tac-toe-tutorial/
        if (currentPlayer == 1) {
            button.setText("X");
            playerTurnLabel.setText("Player 2's turn");
        } else {
            button.setText("O");
            playerTurnLabel.setText("Player 1's turn");
        }

        if (checkForWinner()) {
            if (currentPlayer == 1) {
                player1Score++;
                JOptionPane.showMessageDialog(null, "Player 1 wins!");
            } else {
                player2Score++;
                JOptionPane.showMessageDialog(null, "Player 2 wins!");
            }
            totalplays++;
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(null, "Tie game!");
            totalplays++;
            numTies++;
            resetBoard();
        } else {
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
    }

    private boolean checkForWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }
        //row
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) &&board[i][0].equals(board[i][2]) &&!board[i][0].equals("")) {
                return true;
            }
            //column
            if (board[0][i].equals(board[1][i]) &&board[0][i].equals(board[2][i]) &&!board[0][i].equals("")) {
                return true;
            }
        }
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("")) {
        	return true;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("")) {
        return true;
        }
        return false;
    }
    
    public boolean isBoardFull() {
    	String[][] board = new String[3][3];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }
        
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col].equals("")) {
					return false;
				}
			}
		}
		return true;
	}

    private void resetBoard() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 1;
        numPlays = 0;
        numPlaysLabel.setText("Total Plays : " + totalplays);
        player1ScoreLabel.setText("Player 1: " + player1Score);
        player2ScoreLabel.setText("Player 2: " + player2Score);
        playTiesLabel.setText("Ties : " + numTies);
        playerTurnLabel.setText("Player 1's turn");
    }

    public static void main(String[] args) {
    	TicTacToe game = new TicTacToe();
    }
}
