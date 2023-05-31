package exinclass;

public class RunThreads2Thread implements Runnable {
	public void run() {
		for(int i = 0; i < 25; i++) {
			String myName = Thread.currentThread().getName();
			System.out.println(myName + " is running");
		}
	}
	
	public static void main(String arg[]) throws InterruptedException{
		Runnable r = new RunThreads2Thread();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();
		// waiting for child
		t1.join();
		t2.join();
	}

}
