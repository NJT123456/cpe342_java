package cpe342;

public class stackandqueue {
	static int st[];
	static int top;
	static int back;
	static int front;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		st = new int[10];
		int j;
		//Stack
		for(j=0; j<=9; j++) {
			push(j+1);
			add(j+1);
		}
		for(j=0; j<=9; j++){
			System.out.printf("%d,%d\n",pop(),delete());	
		}
		for(j=0; j<=9; j++) {
			push(j+1);
			add(j+1);
		}
		for(j=0; j<=9; j++){
			System.out.printf("%d,%d\n",pop(),delete());	
		}
		
	}
//	 Stack
	static void push(int x){
        if(top >= st.length){
            System.out.println("Stack is full");
            
        } 
        else {
            st[top++] = x;
        }
    }

    static int pop(){
        if(top > 0){
            return st[--top];
        }
        System.out.println("Stack underflow");
        return 0;
    }
	
    // Queue
    static void add(int x) {
    	if (back >= st.length) {
    		System.out.println("Queue is full");
    	}
    	else {
    		st[back++] = x;
    	}
    }
    
    static int delete(){
        if(front == back) {
        	front = back = 0;
        }
        else {       	
        	return st[front++];
        }
        System.out.println("Queue underflow");
        return 0;
    }
}
