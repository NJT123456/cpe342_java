package GUIBinTreeNode1;

import java.io.*;

public class TestBinTree {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		BinTree<Integer> mt = new BinTree<Integer>();
		mt.add(10); 
		mt.add(20); 
		mt.add(15); 
		mt.add(5); 
		mt.add(1);
		
		//print tree
		System.out.println("Tree after adding nodes:");
		System.out.println(mt);

		// Remove a node
		mt.delete(20);
		
		// Print tree
		System.out.println("\nTree after removing node:");
		System.out.println(mt);
		
		// Search for a node
		System.out.println("\nSearching for node 10:");
		System.out.println(mt.search(10)); // true
		
		System.out.println("\nSearching for node 25:");
		System.out.println(mt.search(25)); // false
		
		//Save Object
		File f = new File("binarytree.txt");
        FileOutputStream fout = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(mt);
        oos.close();
        System.out.println("\nDone");

        // Load Object
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        BinTree<Integer> mtFromFile = (BinTree<Integer>) ois.readObject();
        ois.close();

        // Print binary tree object read from file
        System.out.println("\nBinary tree object read from file:");
        System.out.println(mtFromFile);
	
	}
}
