package fishnoserver;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Bouncing_Fish extends JFrame {
    public ArrayList<Fish> fishs;
    public Timer timer;
    public JPanel fishPanel;
    public Bouncing_Fish() {    
    	
    	setTitle("Bouncing fish");
        setSize(500, 400);
        setVisible(true);
        
        fishs = new ArrayList<>();
        fishPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Fish fish : fishs) {
                    fish.draw(g);
                }
            }
        };
        
        add(fishPanel);
        
        fishPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                	Fish newfish = new Fish(e.getX(), e.getY(), fishPanel);
                    fishs.add(newfish);
                } 

                else if (e.getButton() == MouseEvent.BUTTON3 && !fishs.isEmpty()) {
                    fishs.remove(0);
                }
            }
        });
        
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Fish fish : fishs) {
                    fish.update();
                }
                fishPanel.repaint();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        Bouncing_Fish Bouncing_Fish = new Bouncing_Fish();
    }
}

