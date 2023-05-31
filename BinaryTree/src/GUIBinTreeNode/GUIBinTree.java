package GUIBinTreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUIBinTree {
	public JFrame frame;
	public JTextArea outputTextArea;
	public JTextField inputTextField, searchTextField, filenameTextField;
	public BinTreeforGUI<Integer> tree;

	public GUIBinTree() {
		tree = new BinTreeforGUI<Integer>();

		// frame
		frame = new JFrame("Binary Tree");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        
        // input
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());

		JLabel inputLabel = new JLabel("Input : ");
		inputPanel.add(inputLabel, BorderLayout.WEST);

		inputTextField = new JTextField();
		inputPanel.add(inputTextField, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		//add
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNode();
			}
		});
		buttonPanel.add(addButton);
		//delete
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteNode();
			}
		});
		buttonPanel.add(deleteButton);
		//search
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchNode();
			}
		});
		buttonPanel.add(searchButton);

		inputPanel.add(buttonPanel, BorderLayout.EAST);
		
		searchTextField = new JTextField();
		searchTextField.setEditable(false);
		inputPanel.add(searchTextField, BorderLayout.SOUTH);

		frame.add(inputPanel, BorderLayout.NORTH);
		
		// Output	
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BorderLayout());
		
		JLabel outputLabel = new JLabel("Output : ");
		outputPanel.add(outputLabel, BorderLayout.NORTH);

		outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(outputTextArea);
		//scrollbar
		scrollPane.setPreferredSize(new Dimension(400, 200));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		outputPanel.add(scrollPane, BorderLayout.CENTER);
		frame.add(outputPanel, BorderLayout.CENTER);

		JPanel filePanel = new JPanel();
		//save
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveTree();
			}
		});
		filePanel.add(saveButton);
		//load
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTree();
			}
		});
		filePanel.add(loadButton);

		filenameTextField = new JTextField();
		filenameTextField.setPreferredSize(new Dimension(250, 20));
		filePanel.add(filenameTextField);

		frame.add(filePanel, BorderLayout.SOUTH);
		
		
	}

	public void addNode() {
		String inputValue = inputTextField.getText();
		if(!inputValue.isEmpty())
		{
			int value = Integer.parseInt(inputValue);
			tree.add(value);
			drawTree();
			inputTextField.setText("");
		}
	}

	public void deleteNode() {
		String inputValue = inputTextField.getText();
		if(!inputValue.isEmpty())
		{
			int value = Integer.parseInt(inputValue);
			tree.delete(value);
			drawTree();
			inputTextField.setText("");
		}
    }
	
	public void searchNode() {
	    String inputValue = inputTextField.getText();
	    if(!inputValue.isEmpty())
	    {
	    	
	    	int value = Integer.parseInt(inputValue);
	    	boolean found = tree.search(value);
	    	searchTextField.setText("Searching for node " + value + " : " + Boolean.toString(found));
	    	inputTextField.setText("");
	    }
	}
	
	public void saveTree() {
	    String filename = filenameTextField.getText();
	    if(!filename.isEmpty())
	    {
	    	try {
	    		FileOutputStream fout = new FileOutputStream(filename);
	    		ObjectOutputStream out = new ObjectOutputStream(fout);
	    		out.writeObject(tree);
	    		out.close();
	    		fout.close();
	    		outputTextArea.append("Binary tree saved to file : " + filename + "\n");
	    	}
	    	catch(IOException ex)
	    	{
	    		outputTextArea.append("Failed to save tree to file : " + ex.getMessage());
	    	}
	    }
	}

	public void loadTree() {
	    String filename = filenameTextField.getText();
	    if(!filename.isEmpty())
	    {
	    	try {
	    		FileInputStream fin = new FileInputStream(filename);
	    		ObjectInputStream oin = new ObjectInputStream(fin);
	    		tree = (BinTreeforGUI<Integer>) oin.readObject();
	    		oin.close();
	    		fin.close();
	    		drawTree();
	    		outputTextArea.append("Bianry Tree loaded from file : " + filename + "\n");
	    	}catch(IOException | ClassNotFoundException ex)
	    	{
	    		outputTextArea.append("Failed to load tree from file : " + ex.getMessage()) ;
	    	}
	    }
	}

	public void drawTree() {
	    outputTextArea.setText(tree.print());
	}

	public void show() {
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
	    GUIBinTree guiBinTree = new GUIBinTree();
	    guiBinTree.show();
	}
}

