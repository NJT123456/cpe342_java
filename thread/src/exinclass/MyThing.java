package exinclass;

public class MyThing implements Runnable{

	public void run(){
		System.out.println("Do my thing!");
	}
	
	public static void main(String arg[]) {
		//Create an instance Runnable
		Runnable r = new MyThing();
		//Create a Thread instance
		Thread t = new Thread(r);
		//Run the thread
		t.start();
		System.out.println("Do main thing!");
	}
}
