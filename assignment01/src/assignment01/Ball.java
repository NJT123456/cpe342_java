package assignment01;

import java.awt.*;
import javax.swing.*;

public class Ball {
	public int x;
	public int y; 
	public int speedX; 
	public int speedY;
	public Color color;
	public JPanel ballPanel;
	public int size;

    public Ball(int x, int y, JPanel ballPanel) {
    	// ถ้า speedX เท่ากับ 0 จะทำให้บอลวิ่งในแนวเดียวเท่านั้นไม่ไปทิศทางอื่น คิดเป็นแกนเมื่อบอลอยู่จุด0 บอลก็เด้งซ้ายขวาในแนวแกน 0
        this.x = x;
        this.y = y;
        this.speedX = (int) (Math.random() * 10)+1;
        this.speedY = (int) (Math.random() * 10)+1;
        this.color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
                (int) (Math.random() * 256));
        this.ballPanel = ballPanel;
        this.size = (int)(Math.random()*50)+1;
    }

    public void update() {
        x += speedX;
        y += speedY;
        
        if(x+size > ballPanel.getWidth()) {
        	x = ballPanel.getWidth()-size;
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
//        if (x < 0 || x + size > ballPanel.getWidth()) {
//            speedX = -speedX;
//            x = ballPanel.getWidth()-size;
//        }
//        if (y < 0 || y + size > ballPanel.getHeight()) {
//            speedY = -speedY;
//            y = ballPanel.getHeight()-size;
//        }
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }
}
