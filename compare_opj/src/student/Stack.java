package student;

import java.util.*;

public abstract class Stack<T extends Comparable<T>> implements MySortable {

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
	    T t;
	    for (int i = 0; i < size() - 1; i++) {
	        for (int j = i + 1; j < size(); j++) {
	            if (((T) items[i]).compareTo((T) items[j]) < 0) {
	                t = (T) items[i];
	                items[i] = items[j];
	                items[j] = t;
	            }
	        }
	    }
	}
	
	public void sort(Comparator<T> c) {
		T t;
        for (int i = 0; i < size() - 1; i++) {
            for (int j = i + 1; j < size(); j++) {
                if (c.compare((T) items[i], (T) items[j]) > 0) {
                    t = (T) items[i];
                    items[i] = items[j];
                    items[j] = t;
                }
            }
        }
    }
	
}
