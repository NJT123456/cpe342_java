package student;
import java.util.Comparator;
public class Student implements Comparable<Student> {
    String id;
    String firstname;
    String lastname;
    double gpa;

    public Student(String id, String firstname, String lastname, double gpa) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public double getGpa() {
        return gpa;
    }

    public String toString() {
        return "ID: " + id + " Name: " + firstname + " " + lastname + " GPA: " + gpa;
    }

    public int compareTo(Student o) {
    	return Integer.parseInt(this.id)-Integer.parseInt(o.id);
    	}

    public static Comparator<Student> FirstnameComparator = new Comparator<Student>() {
        public int compare(Student s1, Student s2) {
            return s1.getFirstname().compareTo(s2.getFirstname());
        }
    };

    public static Comparator<Student> LastnameComparator = new Comparator<Student>() {
        public int compare(Student s1, Student s2) {
            return s1.getLastname().compareTo(s2.getLastname());
        }
    };

    public static Comparator<Student> GpaComparator = new Comparator<Student>() {
        public int compare(Student s1, Student s2) {
        	//compare min to max return Double.compare(s1.getGpa(), s2.getGpa());
            return Double.compare(s2.getGpa(), s1.getGpa());
        }
    };
}
