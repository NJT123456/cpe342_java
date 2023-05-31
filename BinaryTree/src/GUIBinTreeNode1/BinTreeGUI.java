package GUIBinTreeNode1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class BinTreeGUI{
	public JFrame frame;
	public JTextField inputTextField, searchTextField, filenameTextField;
	public BinTree<Integer> tree;

	public BinTreeGUI() {
		tree = new BinTree<Integer>();

		// Main frame
        frame = new JFrame("Binary Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());

		JLabel inputLabel = new JLabel("Input : ");
		inputPanel.add(inputLabel, BorderLayout.NORTH);

		inputTextField = new JTextField();
		inputPanel.add(inputTextField, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();

		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNode();
			}
		});
		buttonPanel.add(addButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteNode();
			}
		});
		buttonPanel.add(deleteButton);

		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchNode();
			}
		});
		buttonPanel.add(searchButton);

		inputPanel.add(buttonPanel, BorderLayout.EAST);

		JLabel searchLabel = new JLabel("Search result : ");
		inputPanel.add(searchLabel, BorderLayout.SOUTH);

		searchTextField = new JTextField();
		searchTextField.setEditable(false);
		inputPanel.add(searchTextField, BorderLayout.SOUTH);

		frame.add(inputPanel, BorderLayout.NORTH);
		
		JPanel filePanel = new JPanel();
	    filenameTextField = new JTextField();
	    filenameTextField.setPreferredSize(new Dimension(250, 20));
	    filePanel.add(filenameTextField);

		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTree();
			}
		});
		filePanel.add(loadButton);

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveTree();
			}
		});
		filePanel.add(saveButton);

		
		frame.add(filePanel, BorderLayout.SOUTH);      
	}

	public void addNode() {
		String input = inputTextField.getText();
		if (!input.isEmpty()) {
			int value = Integer.parseInt(input);
			tree.add(value);
			drawTree();
			inputTextField.setText("");
		}
	}

	public void deleteNode() {
        String input = inputTextField.getText();
        if (!input.isEmpty()) {
            int value = Integer.parseInt(input);
            boolean success = tree.delete(value);
            if (success)
            {
            	drawTree();
            	inputTextField.setText("");
            }
        }
    }
	
	public void searchNode() {
	    String input = inputTextField.getText();
	    if (!input.isEmpty()) {
	        int value = Integer.parseInt(input);
	        boolean found = tree.search(value);
	        if (found) {
	            searchTextField.setText("Value " + value + " found.");
	        } else {
	            searchTextField.setText("Value " + value + " not found.");
	        }
	        inputTextField.setText("");
	    }
	}

	public void loadTree() {
	    String filename = filenameTextField.getText();
	    if (!filename.isEmpty()) {
	        try {
	            FileInputStream fileIn = new FileInputStream(filename);
	            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	            tree = (BinTree<Integer>) objectIn.readObject();
	            objectIn.close();
	            fileIn.close();
	            drawTree();
	        } catch (IOException ex) {
	        	System.err.println("Failed to load tree from file: " + ex.getMessage());
	        } catch (ClassNotFoundException ex) {
	        	System.err.println("Failed to load tree from file: " + ex.getMessage());
	        }
	    }
	}

	public void saveTree() {
	    String filename = filenameTextField.getText();
	    if (!filename.isEmpty()) {
	        try {
	            FileOutputStream fileOut = new FileOutputStream(filename);
	            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	            objectOut.writeObject(tree);
	            objectOut.close();
	            fileOut.close();
	            
	        } catch (IOException ex) {
	        	System.err.println("Failed to save tree to file: " + ex.getMessage());
	        }
	    }
	}
	
	public void drawTree() {
	    if (tree.getRoot() != null) {
	    	JPanel panel = new JPanel() 
	    	{
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                Graphics2D g2d = (Graphics2D) g;
	                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                int height = tree.getHeight();
	                int width = (int) Math.pow(2, height - 1) * 60;
	                int startX = getWidth() / 2 - width / 2;
	                int startY = 50;
	                int radius = 10;
	                drawNode(g2d, tree.getRoot(), startX, startY, radius, width);
	            }

	            public void drawNode(Graphics2D g2d, BinTreeNode<Integer> node, int x, int y, int r, int w) {
	                g2d.drawString(String.valueOf(node.data), x - 5, y + 5);
	                if (node.left != null) {
	                    int leftX = x - w / 4;
	                    int leftY = y + 40;
	                    g2d.drawLine(x, y + r, leftX, leftY - r);
	                    drawNode(g2d, node.left, leftX, leftY, r, w / 2);
	                }
	                if (node.right != null) {
	                    int rightX = x + w / 4;
	                    int rightY = y + 40;
	                    g2d.drawLine(x, y + r, rightX, rightY - r);
	                    drawNode(g2d, node.right, rightX, rightY, r, w / 2);
	                }
	            }
	        };

	        JScrollPane scrollPane = new JScrollPane(panel);
	        
	        frame.add(scrollPane, BorderLayout.CENTER);
	        frame.revalidate();
	        frame.repaint();

	    }
	}



//	public void drawTree() {
////	    outputTextArea.setText(tree.toString());
////	    tree.traverseInOrder((value) -> {
////	        outputTextArea.append(value.toString());
////	        outputTextArea.append("\n");
////	    });
//	}

	public void show() {
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
	    BinTreeGUI guiBinTree = new BinTreeGUI();
	    guiBinTree.show();
	}

}

