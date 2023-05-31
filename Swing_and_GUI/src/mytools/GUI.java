package mytools;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import mytools.MyThermometer;

public class GUI extends JFrame {
	public JTextField inputField;
	public JLabel inputSystemLabel;
	public JCheckBox checkBox;
	public JButton convertButton;
	public JTextField outputField;
	public boolean isCelsius = true; // Set the default input system to Celsius
	public MyThermometer t = new MyThermometer();
	public GUI() {
        // Create the components
        inputField = new JTextField(10);
        inputSystemLabel = new JLabel("Celsius");
        checkBox = new JCheckBox("Fahrenheit");
        convertButton = new JButton("Convert");
        outputField = new JTextField(10);
        outputField.setEditable(false); // Set the output field to read-only
        
        // Add action listeners to the checkbox and convert button
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isCelsius = !isCelsius; // Toggle the input system
                inputSystemLabel.setText(isCelsius ? "Celsius" : "Fahrenheit"); // Update the input system label
            }
        });
        
        convertButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
            	// from add action listeners to the checkbox will convert f2c or c2f
            	double input = Double.parseDouble(inputField.getText());
            	double output = isCelsius ? t.convertC2F (input) : t.convertF2C(input);
            	outputField.setText(String.format("%.2f", output)); // Display the output 
            }
        });
        
        // Add the components to the frame
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(checkBox);
        panel.add(inputField);       
        panel.add(inputSystemLabel);      
        panel.add(convertButton);
        panel.add(outputField);
        
        add(panel);
        
        // Set the frame properties
        setTitle("Temperature Converter");
        setSize(300, 100);
        setVisible(true);
    }

	public static void main(String[] args) {
		new GUI();
	}
}
