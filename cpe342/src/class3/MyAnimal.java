package class3;

public class MyAnimal {

	public static void main(String[] args) {
		Animal[] a;
		Dog d;
		Cat c;
		Human h;
		//จำนวน array ต้องเท่ากับจำนวนที่เพิ่มในarray
		a = new Animal[3];
		// 100 is life
		a[0] = d = new Dog("lessy", 100);
		a[1] = c = new Cat("fido", 100);
		a[2] = h = new Human("Jinjutha", 100);
		// ขึ้น หมาก่อนเพราะใน อาเรย์หมาขึ้นก่อน
		for(Animal x:a)
		{
			if(x instanceof Cat)
			{
				x.talk();
				c.eat();
			}
			else if(x instanceof Dog)
			{
				d.bark();
				d.eat();
				d.eat(c);
			}
			else
			{
				x.talk();
				h.eat();
			}
		}
	}

}
