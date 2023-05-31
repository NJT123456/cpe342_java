package ex2;

public class MyApp {

	public static void main(String[] args) {
		int i;
		Stack st;
		Queue q;
		st = new Stack(10);
		q = new Queue(10);
		
		for(i=0;i<9;i++)
		{
			st.push(i);
			q.add(i);
		}
		for(i=0;i<9;i++)
		{
			System.out.printf("%d,%d\n",st.pop(),q.delete());
		}
		for(i=0;i<9;i++)
		{
			st.push(i);
			q.add(i);
		}
		for(i=0;i<9;i++)
		{
			System.out.printf("%d,%d\n",st.pop(),q.delete());
		}
	}

}
