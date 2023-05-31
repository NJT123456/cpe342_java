package student;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class StudentStackDisplay extends JFrame {
    public ArrayList<Student> stack;
    public JTextArea displayArea;
    public JTextField idField, firstnameField, lastnameField, gpaField;

    public StudentStackDisplay() {
        stack = new ArrayList<Student>();
             
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(1, 8));
        idField = new JTextField();
        firstnameField = new JTextField();
        lastnameField = new JTextField();
        gpaField = new JTextField();
        inputPanel.add(new JLabel("ID"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("First Name"));
        inputPanel.add(firstnameField);
        inputPanel.add(new JLabel("Last Name"));
        inputPanel.add(lastnameField);
        inputPanel.add(new JLabel("GPA"));
        inputPanel.add(gpaField);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton pushButton = new JButton("Push");
        pushButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                push();
            }
        });
        
        JButton popButton = new JButton("Pop by ID");
        popButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pop();
            }
        });
        
        JButton sortgpaButton = new JButton("Sort by GPA");
        sortgpaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sort();
            }
        });
        
        JButton sortidButton = new JButton("Sort by ID");
        sortidButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortById();
            }
        });
        
        JButton sortfirstnameButton = new JButton("Sort by First Name");
        sortfirstnameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortByFirstName();
            }
        });
        
        JButton sortlastnameButton = new JButton("Sort by Last Name");
        sortlastnameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortByLastName();
            }
        });
        
        buttonPanel.add(pushButton);
        buttonPanel.add(popButton);
        buttonPanel.add(sortgpaButton);
        buttonPanel.add(sortidButton);
        buttonPanel.add(sortfirstnameButton);
        buttonPanel.add(sortlastnameButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Add initial Student objects to the stack
        stack.add(new Student("101","Hilly","Billy",3.5));
        stack.add(new Student("10001","Billy","Silly",2.5));
        stack.add(new Student("101","Lilly","Silly",1.5));
        stack.add(new Student("101","Silly","Hilly",3.0));

        // Display initial Student objects in the GUI
        updateDisplay();
        
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void push() {
        String id = idField.getText();
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        double gpa = Double.parseDouble(gpaField.getText());
        stack.add(new Student(id, firstname, lastname, gpa));
        updateDisplay();
        clearFields();
    }

    public void pop() {
    	String id = idField.getText();
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i).getId().equals(id)) {
                stack.remove(i);
                updateDisplay();
                clearFields();
                return;
            }
        }
    }

    public void sort() {
        Collections.sort(stack, Student.GpaComparator);
        updateDisplay();
    }
    
    public void sortById() {
        Collections.sort(stack);
        updateDisplay();
    }

    public void sortByFirstName() {
        Collections.sort(stack, Student.FirstnameComparator);
        updateDisplay();
    }

    public void sortByLastName() {
        Collections.sort(stack, Student.LastnameComparator);
        updateDisplay();
    }

    public void updateDisplay() {
        displayArea.setText("");
        for (Student s : stack) {
            displayArea.append(s.toString() + "\n");
        }
    }

    public void clearFields() {
        idField.setText("");
        firstnameField.setText("");
        lastnameField.setText("");
        gpaField.setText("");
    }

    public static void main(String[] args) {
        new StudentStackDisplay();
    }
}
