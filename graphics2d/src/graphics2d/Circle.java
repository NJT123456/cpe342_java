package graphics2d;

import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {
	private Color color;
	private int diameter;
	public Circle()
	{
		this(0,0,10,Color.blue);
	}
	
	public Circle(int x,int y, int d, Color c)
	{
		setLocation(x, y);
		this.diameter = d;
		this.color = c;
	}
	
	public Circle(int x,int y, int d)
	{
		this(x,y,d,Color.blue);
	}
	
	public Circle(int x,int y)
	{
		this(x,y,10,Color.blue);
	}
	
	public void setColor(Color c)
	{
		color = c;
	}
	
	public int getDiameter()
	{
		return diameter;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.fillOval(0, 0, diameter, diameter);
	}
}
