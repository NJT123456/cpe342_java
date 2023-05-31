package Fish;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Fish implements Serializable {
    private static final long serialVersionUID = 1L;
    public static int count = 0;
    public int clientId;
    public String fishId;
    public int x;
    public int y;
    public int speedX;
    public int speedY;
    public transient BufferedImage imageforleft,imageforright;
    public int size;
    public boolean deleted;

    public Fish(int clientId,int x, int y) {
        count++;
        fishId = String.valueOf(clientId)+String.valueOf(count);
        this.clientId = clientId;
        this.x = x;
        this.y = y;
        this.speedX = 5;
        try {
            this.imageforright = ImageIO.read(getClass().getResource("fishRight.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.size = 50;
        deleted = false;
    }
    public Fish(String fishId,int clientId,int x, int y) {
        this.fishId = fishId;
        this.clientId = clientId;
        this.x = x;
        this.y = y;
        this.speedX = 5;
        try {
            this.imageforright = ImageIO.read(getClass().getResource("fishRight.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.size = 50;
        deleted = false;
    }

    public void update() {
        x += speedX;
    }
    
    
    public void move(){
        speedX = -speedX;
    }

    public void draw(Graphics g) {
        if (speedX > 0) {
        	// move right
            g.drawImage(imageforright, x, y, size, size, null);
        } 
    }

}


