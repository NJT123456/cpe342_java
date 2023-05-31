package assignment01;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Bouncing_Ball extends JFrame {
    public ArrayList<Ball> balls;
    public Timer timer;
    public JPanel ballPanel;
    public Bouncing_Ball() {    
    	
    	setTitle("Bouncing Ball");
        setSize(500, 400);
        setVisible(true);
        
        balls = new ArrayList<>();
        ballPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Ball ball : balls) {
                    ball.draw(g);
                }
            }
        };
        
        add(ballPanel);
        
        ballPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Ball newBall = new Ball(e.getX(), e.getY(), ballPanel);
                    balls.add(newBall);
                } 

                else if (e.getButton() == MouseEvent.BUTTON3 && !balls.isEmpty()) {
                    balls.remove(0);
                }
            }
        });
        
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Ball ball : balls) {
                    ball.update();
                }
                ballPanel.repaint();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        Bouncing_Ball Bouncing_Ball = new Bouncing_Ball();
    }
}

