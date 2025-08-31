/**
 * 
 */

/**
 * 
 */
public class Inheritance {

	public static void main(String[] args) {
		
		Person person = new Person("Jon", "Jones");
		System.out.println(person.getName());
		
		Student student = new Student("Todd", "Michael", 2.7);
		student.showGPA();

	} // end of main method

} // end of main class

class Person {
	
	private String first;
	private String last;
	
	Person(String first, String last) {
		this.first = first;
		this.last = last;
	}
	
	public String getName() {
		String name = first + " " + last;
		return name;
	}
}

// The extends keyword is used to make the student class a subclass of the person class
// and inherit the person classes attributes
class Student extends Person{
	
	private double gpa;
	
	public Student(String first, String last, double gpa) {
		
		// super keyword is used to construct a student object with the inherited variables from the person class 
		super(first, last);
		this.gpa = gpa;
	}
	
	public void showGPA() {
		System.out.println(getName() + "'s GPA is: " + this.gpa);
	}
}
