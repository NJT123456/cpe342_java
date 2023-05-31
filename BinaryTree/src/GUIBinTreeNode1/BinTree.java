package GUIBinTreeNode1;

import java.io.PrintWriter;
import java.io.Serializable;
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
	
	public BinTreeNode<T> getRoot() {
	    return root;
	}
	


	public int getHeight() {
	    return getHeight(root);
	}

	private int getHeight(BinTreeNode<T> node) {
	    if (node == null) {
	        return 0;
	    }
	    int leftHeight = getHeight(node.left);
	    int rightHeight = getHeight(node.right);
	    return 1 + Math.max(leftHeight, rightHeight);
	}
	
	public void printTree(PrintWriter out) {
	    if (root != null) {
	        printTree(root, out);
	    }
	}

	public void printTree(BinTreeNode<T> node, PrintWriter out) {
	    out.println(node.data);
	    if (node.left != null) {
	        printTree(node.left, out);
	    }
	    if (node.right != null) {
	        printTree(node.right, out);
	    }
	}

//	public String toString() {
//	    return root == null ? "null" : root.toString();
//	}
}
