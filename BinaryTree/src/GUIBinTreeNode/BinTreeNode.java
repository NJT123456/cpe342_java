package GUIBinTreeNode;

import java.io.Serializable;

public class BinTreeNode<T extends Comparable<T>> implements Serializable, Comparable<BinTreeNode<T>> {

	public static final long serialVersionUID = 1L;
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
	
	public T getData() {
	    return data;
	}
	
	public BinTreeNode<T> getLeft() {
        return left;
    }
    
    public BinTreeNode<T> getRight() {
        return right;
    }   
}
