package class3;

public abstract class Animal {
	int life;
	public static void eat()
	{
		System.out.println("Animal eats food");
	}
	public Animal()
	{
		this(100); //use this constructor
	}
	public Animal(int life)
	{
		this.life = life;
	}
	abstract void talk();

}
