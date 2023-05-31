package Fishs;

import java.awt.*;
import java.io.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Fish implements Serializable {
	private static final long serialVersionUID = 1L;
	public static int count = 0;
	public int clientId;
	public String fishId;
	public int randpic;
	public int x;
	public int y;
	public int speedX;
	public int speedY;
	public ImageIcon imageforleft, imageforright, imageforleft1, imageforright1, imageforright2, imageforleft2;
	public int size;
	public boolean deleted;
	public JPanel fishPanel;

	public Fish(int clientId, int randpic, int x, int y) {
		count++;
		fishId = String.valueOf(clientId) + String.valueOf(count);
		this.clientId = clientId;
		this.randpic = randpic;
		this.x = x;
		this.y = y;
		this.speedX = 5;
		this.speedY = 5;
		try {
			this.imageforright = new ImageIcon(getClass().getResource("fishRight.png"));
			this.imageforright1 = new ImageIcon(getClass().getResource("fishRight1.png"));
			this.imageforright2 = new ImageIcon(getClass().getResource("fish2.png"));
			this.imageforleft = new ImageIcon(getClass().getResource("fishLeft.png"));
			this.imageforleft1 = new ImageIcon(getClass().getResource("fishLeft1.png"));
			this.imageforleft2 = new ImageIcon(getClass().getResource("fishLeft2.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this.size = 50;
		deleted = false;
	}

	public Fish(String fishId, int clientId, int randpic, int x, int y) {
		this.fishId = fishId;
		this.clientId = clientId;
		this.randpic = randpic;
		this.x = x;
		this.y = y;
		this.speedX = 5;
		this.speedY = 5;
		try {
			this.imageforright = new ImageIcon(getClass().getResource("fishRight.png"));
			this.imageforright1 = new ImageIcon(getClass().getResource("fishRight1.png"));
			this.imageforright2 = new ImageIcon(getClass().getResource("fish2.png"));
			this.imageforleft = new ImageIcon(getClass().getResource("fishLeft.png"));
			this.imageforleft1 = new ImageIcon(getClass().getResource("fishLeft1.png"));
			this.imageforleft2 = new ImageIcon(getClass().getResource("fishLeft2.png"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		this.size = 50;
		deleted = false;
	}

	public void update(JPanel fishPanel) {
		x += speedX;
		y += speedY;

		if (y + size > fishPanel.getHeight()) {
			y = fishPanel.getHeight() - size;
			speedY = -speedY;

		} else if (y < 0) {
			y = 0;
			speedY = -speedY;

		}
	}

	public void turnAround() {
		speedX = -speedX;
	}

	public void draw(Graphics g) {
		if (randpic == 0) {
			if (speedX > 0) {
				// move right
				g.drawImage(imageforright.getImage(), x, y, size, size, null);
			} else {
				// move left
				g.drawImage(imageforleft.getImage(), x, y, size, size, null);
			}
		}
		else if (randpic == 1) {
			if (speedX > 0) {
				// move right
				g.drawImage(imageforright1.getImage(), x, y, size, size, null);
			} else {
				// move left
				g.drawImage(imageforleft1.getImage(), x, y, size, size, null);
			}
		}

		else if (randpic == 2) {
			if (speedX > 0) {
				// move right
				g.drawImage(imageforright2.getImage(), x, y, size, size, null);
			} else {
				// move left
				g.drawImage(imageforleft2.getImage(), x, y, size, size, null);
			}
		}else {
			if (speedX > 0) {
				// move right
				g.drawImage(imageforright.getImage(), x, y, size, size, null);
			} else {
				// move left
				g.drawImage(imageforleft.getImage(), x, y, size, size, null);
			}
		}

	}
}
