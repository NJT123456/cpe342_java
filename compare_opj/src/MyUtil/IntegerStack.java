package MyUtil;

public class IntegerStack extends Stack<Integer> implements MySortable {
	
	public static void main(String[] args) {
		
		// test compare
		IntegerStack s;
		s = new IntegerStack();
		for (int i = 0; i < 10; i++) {
			s.push((int) (Math.random() * 20.0));
		}
		
		System.out.println("Before sorting :");
		IntegerStack copy = new IntegerStack(); 
		while (!s.isEmpty()) {
			int item = s.pop();
			System.out.println(item);
			copy.push(item);
		}

		System.out.println("After sorting :");
		
		copy.sort();
		
		while (!copy.isEmpty()) {
			System.out.println(copy.pop());
		}

	}
}