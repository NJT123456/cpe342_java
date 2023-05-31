package class3;

public class Dog extends Animal {
	private String name;
	static String food = "cat";
	@Override //animal talk
	void talk()
	{
		bark();
	}
	void bark()
	{
		//how dog talk
		System.out.println("Bowwow");
	}
	
	public Dog()
	{
		super(); //use super class constructor
	}
	public Dog(int life)//overload Dog constructor
	{
		super(life); //also use super class constructo
	}	
	public Dog (String Name)
	{
		super();
		this.name = new String(Name);
	}
	public Dog (String Name, int life)
	{
		super(life);
		this.name = new String(Name);
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
		System.out.println("dog eats " + Dog.food);
	}
	public void eat(Cat c)
	{
		System.out.println(name + " eats " + c.name);
	}
}
