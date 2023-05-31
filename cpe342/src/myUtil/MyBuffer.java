package myUtil;

public abstract class MyBuffer {
	final int items[];
	
	//default constructor
	public MyBuffer() {
		this(10);
	}
	
	//constructor with parameter
	public MyBuffer(int x) {
		items = new int[x];
	}
	
	void showBuffer() {
		System.out.printf("{");
		for (int i =0;i<items.length;i++) 
			System.out.printf("%d,", items[i]);
		System.out.println();
		
	}
	
	double average()
	{
		double x=0;
		for(int i = 0;i<items.length;i++) 
			x+=items[i];
		return(items.length>0)?(x/items.length):0.0;
		
	}
}
