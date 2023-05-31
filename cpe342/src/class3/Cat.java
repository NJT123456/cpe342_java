package class3;

public class Cat extends Animal{
	String name;
	static String food = "mice";
	@Override //animal talk
	void talk()
	{
		meow();
	}
	void meow()
	{
		//how cat talk
		System.out.println("meow");
	}
	public Cat()
	{
		super(); //use super class constructor
	}
	public Cat(int life)//overload Dog constructor
	{
		super(life); //also use super class constructo
	}
	public Cat (String Name)
	{
		super();
		this.name = new String(Name);
	}
	public Cat (String Name, int life)
	{
		super(life);
		this.name = new String (Name);
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public static void eat()
	{
		System.out.println("cat eats " + Cat.food);
	}
}
