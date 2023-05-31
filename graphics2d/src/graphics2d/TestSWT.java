package graphics2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestSWT {
	private JTextField textField;

	public static void main(String[] args) {
		TestSWT test = new TestSWT();
		test.initialize();

	}

	public void initialize() {
        // Create a new JFrame
        JFrame frame = new JFrame();

        // Set the size and position of the frame
        frame.setBounds(100, 100, 450, 300);

        // Set the default close operation of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout of the content pane to null
        frame.getContentPane().setLayout(null);

        // Create a new JInternalFrame
        final JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");

        // Add a mouse listener to the content pane of the JInternalFrame
        internalFrame.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                // Set the text of a text field to the string representation of the JInternalFrame
                textField.setText(internalFrame.toString());
            }
        });

        // Set the size and position of the JInternalFrame
        internalFrame.setBounds(10, 55, 420, 200);

        // Add the JInternalFrame to the content pane of the JFrame
        frame.getContentPane().add(internalFrame);

        // Set the layout of the content pane of the JInternalFrame to null
        internalFrame.getContentPane().setLayout(null);

        // Create a new text field and add it to the content pane of the JFrame
        textField = new JTextField();
        textField.setBounds(10, 10, 200, 20);
        frame.getContentPane().add(textField);

        // Make the frame visible
        frame.setVisible(true);
        
        //Add Components
        Points p = new Points(10,10,2,Color.red);
        p.setBounds(p.getX(), p.getY(), p.getSize().height, p.getSize().width);
        internalFrame.getContentPane().add(p);
        
        final Circle cir = new Circle(0,0,20,Color.blue);
        cir.setBounds(cir.getX(), cir.getY(), cir.getDiameter(), cir.getDiameter());
        internalFrame.getContentPane().add(cir);
        internalFrame.setVisible(true);
        
        // point of text here java
        JPanel panel = new JPanel();
        panel.setBounds(10, 0, 414, 54);
        frame.getContentPane().add(panel);
        JLabel lblNewLabel = new JLabel("New label");
        panel.add(lblNewLabel);
        panel.add(textField);
        textField.setColumns(25);
        textField.setText("here "+p.getLocation());
        
        ActionListener listener = new ActionListener()
        {
        	int dx = 1; // change in x position
    	    int dy = 1; // change in y position
        	@Override
        	public void actionPerformed(ActionEvent e) 
        	{
        		// TODO Auto-generated method stub
        		// add getcontentpane คือทำให้คำนวณ x และ y สูงสุดของลูกบอล
        		Dimension size = internalFrame.getContentPane().getSize();
                Insets insets = internalFrame.getContentPane().getInsets();
        		int w = size.width - insets.left - insets.right;
        		int h = size.height - insets.top - insets.bottom;
        		int x = cir.getX() + dx;
        	    int y = cir.getY() + dy;
        	    
        	    if (x + cir.getDiameter() > w) {
        	        x = w - cir.getDiameter();
        	        dx = -dx;
        	    }
        	    else if (x < 0) {
        	        x = 0;
        	        dx = -dx;
        	    }
        	    // top left
        	    else if (y + cir.getDiameter() > h) {
        	        y = h - cir.getDiameter();
        	        dy = -dy;
        	    }
        	    // top right
        	    if (y < 0) {
        	        y = 0;
        	        dy = -dy;
        	    }

        	    cir.setLocation(x, y);
        	}
        	
        };
        Timer displayTimer = new Timer(10, listener);
        	displayTimer.start();
        }
}
