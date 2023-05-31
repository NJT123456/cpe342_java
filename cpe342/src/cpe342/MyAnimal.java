package cpe342;

public class MyAnimal {
	public static void main(String[] arg) {
		Animal a;
		Cat c;
		a = new Animal();
		c = new Cat();
		// what do we miss here?
		a.talk();
		c.talk(); // cat can’t talk
		c.meow(); // and the fox says meow
	}
}
