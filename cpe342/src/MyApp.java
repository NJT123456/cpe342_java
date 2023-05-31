import myUtil.*;
import java.util.Random;

public class MyApp {

	public static void main(String[] args) {
		int i;
		Stack st;
		Queue q;
		st = new Stack(10);
		q = new Queue(10);
		Random rand = new Random();
//		System.out.println("Push one time, Add one time");
		for (i = 0; i < 11; i++) {
			st.push(rand.nextInt(10));
			q.add(rand.nextInt(10));
		}
//		System.out.println("Pop, Delete");
		for (i = 0; i < 11; i++) {
			System.out.printf("%d,%d\n", st.pop(), q.delete());
		}
//		System.out.println("Push two time, Add two time");
		for (i = 0; i < 11; i++) {
			st.push(rand.nextInt(10));
			q.add(rand.nextInt(10));
		}
//		System.out.println("Pop, Delete");
		for (i = 0; i < 11; i++) {
			System.out.printf("%d,%d\n", st.pop(), q.delete());
		}
//		System.out.println("Push three time, Add three time");
		for (i = 0; i < 11; i++) {
			st.push(rand.nextInt(10));
			q.add(rand.nextInt(10));
		}
//		System.out.println("Pop, Delete");
		for (i = 0; i < 11; i++) {
			System.out.printf("%d,%d\n", st.pop(), q.delete());
		}
	}

}
