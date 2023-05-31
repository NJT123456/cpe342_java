package fishnoserver;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Fish implements Serializable {
    private static final long serialVersionUID = 1L;
    public int x;
    public int y;
    public int speedX;
    public int speedY;
    public BufferedImage imageforleft,imageforright;
    public JPanel ballPanel;
    public int size;

    public Fish(int x, int y, JPanel ballPanel) {
        this.x = x;
        this.y = y;
        this.speedX = 20;
        this.speedY = 20;
        try {
            this.imageforleft = ImageIO.read(new File("fishRight.png"));
            this.imageforright = ImageIO.read(new File("fishLeft.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.ballPanel = ballPanel;
        this.size = 50;
    }

    public void update() {
        x += speedX;
        y += speedY;

        if (x + size > ballPanel.getWidth()) {
            x = ballPanel.getWidth() - size;
            speedX = -speedX;

        } else if (x < 0) {
            x = 0;
            speedX = -speedX;

        } 
        else if (y + size > ballPanel.getHeight()) {
            y = ballPanel.getHeight() - size;
            speedY = -speedY;

        } else if (y < 0) {
            y = 0;
            speedY = -speedY;

        }
    }

    public void draw(Graphics g) {
        if (speedX > 0) {
        	// move right
            g.drawImage(imageforleft, x, y, size, size, null);
        } else {
        	// move left
            g.drawImage(imageforright, x, y, size, size, null);
        } 
    }

}

