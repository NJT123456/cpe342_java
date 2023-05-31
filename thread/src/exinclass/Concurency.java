package exinclass;

public class Concurency implements Runnable{
	//ดูว่ากำลังทำอันไหนอยู่
	public void run(){
		try {
			Thread.sleep(3000);
			System.out.println("Do my thing!");
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String arg[]) {
		//Create an instance Runnable
		Runnable r = new Concurency();
		//Create a Thread instance
		Thread t = new Thread(r);
		//Run the thread
		t.start();
		System.out.println("Do main thing!");
	}
}

