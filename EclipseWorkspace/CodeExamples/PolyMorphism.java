/**
 * 
 */

/**
 * PolyMorphism = "POLY" = "MANY"
 * 				  "MORPH" = "SHAPE"
 * 				   Object's can identify as other objects.
 * 				   Object's can be treated as object's of a common superclass
 */
public class PolyMorphism {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Car car = new Car();
		Bike bike = new Bike();
		Boat boat = new Boat();
		
		// since the Car, Bike, and Boat classes share a common superclass
		// they can be treated as objects of the same type
		Vehichle[] vehichles = {car, bike, boat};
		
		for (Vehichle currentVehichle : vehichles) {
			currentVehichle.go();
		}
	}

}

// This is the common superclass.
// because it is an abstract class it can't create any vehicle objects
abstract class Vehichle {
	 
	abstract void go();
}

class Car extends Vehichle {
	
	@Override
	public void go() {
		System.out.println("*The car is driving*");
	}
}

class Bike extends Vehichle {
	
	@Override
	public void go() {
		System.out.println("*The bike is biking");
	}
}

class Boat extends Vehichle {
	
	@Override
	public void go() {
		System.out.println("*The boat is sailing*");
	}
}
