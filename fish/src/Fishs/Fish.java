package Fishs;

public class Fish 
{ 
	int x = 3;

	  @Override
	  public String toString() {
	    return "" + x;
	  }

	  public static void main(String[] args) {
		  Fish x = new Fish();
	    System.out.println(x);
	  }

} 
