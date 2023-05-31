package GUIBinTreeNode;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class BinTree<T extends Comparable<T>> implements Serializable {
	public static final long serialVersionUID = 1L;
	BinTreeNode<T> root;
	
	public void add(T o) {
		BinTreeNode<T> newNode = new BinTreeNode<T>(o);
		if (root == null){
			root = newNode;
		}
		else
		{
			BinTreeNode<T> current = root;
			BinTreeNode<T> parent;
			while(true)
			{
				parent = current;
				if (o.compareTo(current.data) < 0)
				{
					current = current.left;
					if(current == null) {
						parent.left = newNode;
						return;
					}
				}
				else
				{
					current = current.right;
					if(current == null)
					{
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}
	
	public boolean search(T o) {
		if(o == null || root == null) {
			return false;
		}
		
		BinTreeNode<T> current = root;
		while (current != null)
		{
			if(current.data.compareTo(o) == 0)
			{
				return true;
			}
			else if (current.data.compareTo(o) < 0)
			{
				current = current.right;
			}
			else
			{
				current = current.left;
			}
		}
		return false;
	}
	
	public boolean delete(T o) {
	    if (root == null || o == null) {
	        return false;
	    }

	    BinTreeNode<T> current = root;
	    BinTreeNode<T> parent = null;
	    boolean isLeftChild = false;

	    // Find the node to delete and its parent
	    while (current != null && current.data.compareTo(o) != 0) {
	        parent = current;
	        if (o.compareTo(current.data) < 0) {
	            current = current.left;
	            isLeftChild = true;
	        } else {
	            current = current.right;
	            isLeftChild = false;
	        }
	    }

	    // Node not found
	    if (current == null) {
	        return false;
	    }

	    // Case 1: Node has no children
	    if (current.left == null && current.right == null) {
	        if (current == root) {
	            root = null;
	        } else if (isLeftChild) {
	            parent.left = null;
	        } else {
	            parent.right = null;
	        }
	    }
	    // Case 2: Node has one child
	    else if (current.right == null) {
	        if (current == root) {
	            root = current.left;
	        } else if (isLeftChild) {
	            parent.left = current.left;
	        } else {
	            parent.right = current.left;
	        }
	    } else if (current.left == null) {
	        if (current == root) {
	            root = current.right;
	        } else if (isLeftChild) {
	            parent.left = current.right;
	        } else {
	            parent.right = current.right;
	        }
	    }
	    // Case 3: Node has two children
	    else {
	        BinTreeNode<T> successor = getSuccessor(current);

	        // Connect the parent of the node to be deleted with its successor
	        if (current == root) {
	            root = successor;
	        } else if (isLeftChild) {
	            parent.left = successor;
	        } else {
	            parent.right = successor;
	        }

	        // Connect the left subtree of the node to be deleted with its successor
	        successor.left = current.left;
	    }

	    return true;
	}

	public BinTreeNode<T> getSuccessor(BinTreeNode<T> node) {
	    BinTreeNode<T> successorParent = node;
	    BinTreeNode<T> successor = node;
	    BinTreeNode<T> current = node.right;

	    while (current != null) {
	        successorParent = successor;
	        successor = current;
	        current = current.left;
	    }

	    // If the successor is not the right child of the node to be deleted,
	    // update the right subtree of the successor's parent
	    if (successor != node.right) {
	        successorParent.left = successor.right;
	        successor.right = node.right;
	    }

	    return successor;
	}
	
	public String print() {
	    StringBuilder sb = new StringBuilder();
	    int maxLevel = getMaxLevel(root);
	    printNodeInternal(Collections.singletonList(root), 1, maxLevel, sb);
	    return sb.toString();
	}

	public void printNodeInternal(List<BinTreeNode<T>> nodes, int level, int maxLevel, StringBuilder sb) {
	    if (nodes.isEmpty() || isAllElementsNull(nodes))
	        return;

	    int floor = maxLevel - level;
	    int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
	    int firstSpaces = (int) Math.pow(2, floor) - 1;
	    int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

	    printWhitespaces(firstSpaces);

	    List<BinTreeNode<T>> newNodes = new ArrayList<>();
	    for (BinTreeNode<T> node : nodes) {
	        if (node != null) {
	            System.out.print(node.data);
	            newNodes.add(node.left);
	            newNodes.add(node.right);
	        } else {
	            newNodes.add(null);
	            newNodes.add(null);
	            System.out.print(" ");
	        }

	        printWhitespaces(betweenSpaces);
	    }
	    System.out.println("");
	    sb.append("\n");
	    for (int i = 1; i <= edgeLines; i++) {
	        for (BinTreeNode<T> node : nodes) {
	            printWhitespaces(firstSpaces - i);
	            if (node == null) {
	                printWhitespaces(edgeLines + edgeLines + i + 1);
	                continue;
	            }

	            if (node.left != null)
	                System.out.print("/");
	            else
	                printWhitespaces(1);

	            printWhitespaces(i + i - 1);

	            if (node.right != null)
	                System.out.print("\\");
	            else
	                printWhitespaces(1);

	            printWhitespaces(edgeLines + edgeLines - i);
	        }

	        System.out.println("");
	    }

	    printNodeInternal(newNodes, level + 1, maxLevel, sb);
	}

	public void printWhitespaces(int count) {
	    for (int i = 0; i < count; i++)
	        System.out.print(" ");
	}

	public boolean isAllElementsNull(List<BinTreeNode<T>> list) {
	    for (Object object : list) {
	        if (object != null)
	            return false;
	    }

	    return true;
	}

	public int getMaxLevel(BinTreeNode<T> node) {
	    if (node == null)
	        return 0;

	    return Math.max(getMaxLevel(node.left), getMaxLevel(node.right)) + 1;
	}
}
