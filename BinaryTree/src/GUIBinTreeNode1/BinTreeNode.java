package GUIBinTreeNode1;

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

//	public String toString() {
//	    StringBuilder sb = new StringBuilder();
//	    toStringInOrder(sb, " ", " ", true);
//	    return sb.toString();
//	}
//
//	public void toStringInOrder(StringBuilder sb, String padding, String pointer, boolean isRoot) {
//	    if (this.right != null) {
//	        this.right.toStringInOrder(sb, padding + "    ", "  /", false);
//	    }
//	    sb.append(padding);
//	    if (isRoot) {
//	        sb.append(this.data).append("\n");
//	    } else {
//	        sb.append(pointer).append(this.data).append("\n");
//	    }
//	    if (this.left != null) {
//	        this.left.toStringInOrder(sb, padding + "    ", "  \\", false);
//	    }
//	}

}
