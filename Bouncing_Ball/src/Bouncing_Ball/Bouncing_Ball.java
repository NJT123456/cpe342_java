package Bouncing_Ball;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Bouncing_Ball extends JFrame {
    public ArrayList<Ball> balls; // สร้าง arraylist เพื่อเก็บลูกบอล
    public Timer timer; // Timer เพื่ออัปเดตตำแหน่งของบอล
    public JPanel ballPanel; // Panel สำหรับลูกบอล

    public Bouncing_Ball() {
        balls = new ArrayList<>();
        ballPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // วาดลูกบอลบน panel โดยใช้บอลที่เก็บไว้ใน balls ที่เป็น arraylist
                for (Ball ball : balls) {
                    ball.draw(g);
                }
            }
        };
        // เพิ่มบอลในหน้าต่าง
        add(ballPanel);
        
        // add mouse listeners เพื่อตรวจสอบการคลิกเมาส์
        ballPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	// ตลิกเมาส์ ซ้าย คือ button 1 
            	// คลิกเมาส์ กลาง คือ button 2
            	// คลิกเมาส์ ขวา คือ button 3
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // สร้าง ball ใหม่ โดย x และ y เป็นพิกัดของ ball ที่กดปุ่ม และส่ง ballpanelที่เป็นพารามิเตอร์ที่ใข้วาดบอลบนหน้าต่าง
                    Ball newBall = new Ball(e.getX(), e.getY(), ballPanel);
                    balls.add(newBall);
                } 
                // คลิกขวา และบอลต้องมีอยู่ในlist หรือ บอลต้องไม่ว่างเปล่าถึงจะลบ โดย 0 คือ ตำแหน่งของ arraylist ตำแหน่งแรก
                else if (e.getButton() == MouseEvent.BUTTON3 && !balls.isEmpty()) {
                    balls.remove(0);
                }
            }
        });
        // กำหนดหน้าต่างของเกมเป็น 500 * 400 pixel
        setSize(500, 400);
        // หน้าต่างแสดงขึ้นบนจอ
        setVisible(true);
        // กำหนดให้เกิดเหตุการทุก 10 มิลลิวินาทีหรือ 60 เฟรมต่อนาที และให้ทำงานใน ActionListener ซึ่งเป็นตัวรับเหตุการ
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // อัปเดตตำแหน่งลูกบอลทุกตัวใน arraylist
                for (Ball ball : balls) {
                    ball.update();
                }
                // วาดลูกบอลที่อัปเดตใหม่ขุึ้นบนpanel
                ballPanel.repaint();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        Bouncing_Ball Bouncing_Ball = new Bouncing_Ball();
    }
}


