package myUtil;

public class Queue extends MyBuffer {
	int head,tail;
	public Queue()
	{
		this(10);
	}
	public Queue(int x)
	{
		super(x);
		head=0;
		tail=0;
	}
	public void add(int x){
		// add this queue
		if (tail >= items.length) {
    		System.out.println("Queue is full");
    	}
    	else {
    		items[tail++] = x;
//    		System.out.printf("%d\n",x);
    	}
	}
	public int delete(){
		// delete this queue
		if(head == tail) 
			head = tail = 0;
        
        else 
        	return items[head++];	
		
		
        System.out.println("Queue underflow");
        return 0;
	}
}
