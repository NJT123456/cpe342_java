package class3;

public class Human extends Animal{
	private String name;
	static String food = "cake";
	@Override //animal talk
	void talk()
	{
		hello();
	}
	void hello()
	{
		//how cat talk
		System.out.println("hello");
	}
	public Human()
	{
		super(); //use super class constructor
	}
	public Human(int life)//overload Dog constructor
	{
		super(life); //also use super class constructo
	}
	public Human (String Name)
	{
		super();
		this.name = new String(Name);
	}
	public Human (String Name, int life)
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
		System.out.println("Human eats " + Human.food);
	}
}
