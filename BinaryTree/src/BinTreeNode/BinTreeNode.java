package BinTreeNode;

import java.io.Serializable;

public class BinTreeNode<T extends Comparable<T>> implements Serializable, Comparable<BinTreeNode<T>> {

	private static final long serialVersionUID = 1L;
	T data;
	BinTreeNode<T> left;
	BinTreeNode<T> right;

	public BinTreeNode(T o) {
		data = o;
		left = null;
		right = null;
	}
	
	public int compareTo(BinTreeNode<T> o) {
		return this.data.compareTo(o.data);
	}

	public String toString() {
		return "data: " + data.toString() + ((left != null) ? " left: " + left.toString() : "")
				+ ((right != null) ? " right: " + right.toString() : "");
	}
		
}
