package student;

import java.util.ArrayList;
import java.util.Collections;

public class StudentStack extends Stack<Student> {
	
	public static void main(String[] args) {
		ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("101","Hilly","Billy",3.5));
        students.add(new Student("10001","Billy","Silly",2.5));
        students.add(new Student("101","Lilly","Silly",1.5));
        students.add(new Student("101","Silly","Hilly",3.0));
        
		System.out.println("Sort by ID:");
        Collections.sort(students);
        for(Student s : students) {
            System.out.println(s);
        }
        
        System.out.println("\nSort by firstname:");
        Collections.sort(students, Student.FirstnameComparator);
        for(Student s : students) {
            System.out.println(s);
        }
        
        System.out.println("\nSort by lastname:");
        Collections.sort(students, Student.LastnameComparator);
        for(Student s : students) {
            System.out.println(s);
        }
        
        System.out.println("\nSort by GPA:");
        Collections.sort(students, Student.GpaComparator);
        for(Student s : students) {
            System.out.println(s);
        }
    }
//		//ไม่ออก firstname 
//        StudentStack s = new StudentStack();
//		s.push(new Student("101","Hilly","Billy",3.5));
//		s.push(new Student("10001","Billy","Silly",2.5));
//		s.push(new Student("101","Lilly","Silly",1.5));
//		s.push(new Student("101","Silly","Hilly",3.0));
//		
//		System.out.println("ID sorting :");
//		s.sort();
//
//		while(!s.isEmpty()){
//			System.out.println(s.pop());
//		}
//		
//		// Sort by firstname
//		System.out.println("Firstname sorting :");
//		s.sort(Student.FirstnameComparator);
//		while(!s.isEmpty()){
//			System.out.println(s.pop());
//		}
//		
//		// Sort by lastname
//		System.out.println("Lastname sorting :");
//		s.sort(Student.LastnameComparator);
//		while(!s.isEmpty()){
//			System.out.println(s.pop());
//		}
//		
//		// Sort by GPA
//		System.out.println("GPA sorting :");
//		s.sort(Student.GpaComparator);
//		while(!s.isEmpty()){
//			System.out.println(s.pop());
//		}
//	}

}
