/**
 * 
 */

/**
 * 
 */
public class PolymorphismPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Student student = new Student("Jeremy", 3.5);
		Teacher teacher = new Teacher("Professor Snape", 50);
		
		Person[] peopleArray = {student, teacher};
		
		for (Person people : peopleArray) {
			people.showInfo();
		}
	}
}

abstract class Person {
	
	String name;
	
	public Person(String name) {
		this.name = name;
	}
	
	abstract void showInfo();
}

class Student extends Person {
	
	double gpa;
	
	public Student(String name, double gpa) {
		super(name);
		this.gpa = gpa;
	}
	
	@Override
	public void showInfo() {
		System.out.println(name + " has a " + gpa + " gpa");
	}
}

class Teacher extends Person {
	
	int salary;
	
	public Teacher(String name, int salary) {
		super(name);
		this.salary = salary;
	}
	
	@Override
	public void showInfo() {
		System.out.println(name + " is paid " + salary + "$");
	}
}
