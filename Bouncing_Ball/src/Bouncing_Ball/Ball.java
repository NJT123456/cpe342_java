package Bouncing_Ball;

import java.awt.*;
import javax.swing.*;

public class Ball {
	public int x; // x y เป็นตัวแปรพิกัดของลูกบอล
	public int y; 
	public int speedX; 
	public int speedY;
	public Color color;
	public JPanel ballPanel; //ref ไปยัง jpanel ที่ใช้วาดลูกบอล
	public int size;

    public Ball(int x, int y, JPanel ballPanel) {
        this.x = x;
        this.y = y;
        // กำหนัดความเร็วแบบสุ่มของลูกบอล
        this.speedX = (int) (Math.random() * 10);
        this.speedY = (int) (Math.random() * 10);
        // กำหนดเพื่อสุ่มสีที่เป็นแม่สีคือ แดง เขีบว น้ำเงิน
        this.color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
                (int) (Math.random() * 256));
        this.ballPanel = ballPanel;
        this.size = (int)(Math.random()*50)+1;
    }

    // อัปเดตตำแหน่งของลูกบอลและตรวจสอบการเด้งของบอล
    public void update() {
    	x += speedX;
        y += speedY;
        
        if(x+size > ballPanel.getWidth()) {
        	x = 0;
        	speedX = -speedX;
        	
        }
        else if(x < 0) {
        	x=0;
        	speedX = -speedX;
        	
        }
        else if(y + size > ballPanel.getHeight()) {
        	y = ballPanel.getHeight() - size;
        	speedY = -speedY;
        	
        }
        else if (y <0) {
        	y = 0;
        	speedY = -speedY;
        	
        }
    }
    //กำหนดสี, พิกัดและขนาดของบอล
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }
}


