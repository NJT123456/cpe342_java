package exinclass;

public class ThreadDemo extends Thread{
	public static void main(String args[]) {
		System.out.println("Printed from the thread of main()");
		ThreadDemo demo = new ThreadDemo();
		demo.start();
	}
	
	public void run() {
		System.out.println("Printed from the thread of run()");
	}

}
