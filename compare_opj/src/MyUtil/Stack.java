package MyUtil;

import java.util.*;

public abstract class Stack<T> implements MySortable {

	Object[] items;
	int top;

	public Stack() {
		items = new Object[10];
		top = 0;
	}

	public void push(T x) {
		if (top < items.length)
			items[top++] = x;
	}

	public T pop() {
		if (top > 0)
			return (T) items[--top];
		return null;
	}

	public int size() {
		return top;
	}

	public boolean isEmpty() {
		if (top <= 0)
			return true;
		return false;
	}

	public boolean isFull() {
		if (top >= items.length)
			return true;
		return false;
	}
	
	public void sort() {
		Object t;
	    for (int i = 0; i < items.length - 1; i++) {
	        for (int j = i + 1; j < items.length; j++) {
	        	//max to min (Integer)items[j]) > 0 or min to max (Integer)items[j]) < 0 
	            if (((Integer) items[i]).compareTo((Integer)items[j]) < 0) {
	                t = items[i];
	                items[i] = items[j];
	                items[j] = t;
	            }
	        }
	    }
	}
}
