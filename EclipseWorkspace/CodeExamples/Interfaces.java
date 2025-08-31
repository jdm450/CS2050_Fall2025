/**
 * 
 */

/**
 * 
 */
public class Interfaces {
	
	// Interface = a blueprint for a class that specifies a set of abstract methods
	// 			   that implementing classes must define.
	// 			   Supports multiple inheritance-like behavior

	public static void main(String[] args) {
		
		Fish fish = new Fish();
		fish.hunt();
		fish.flee();
	}
}

// defining abstract methods for the Predator and Prey interfaces

interface Predator {
	
	public abstract void hunt();
}

interface Prey {
	
	public abstract void flee();
}

// The fish class is implementing and overriding abstract methods from the predator and prey interfaces
// while the override keyword is not mandatory, it is best practice to include. 
class Fish implements Prey, Predator {
	
	@Override
	public void flee() {
		System.out.println("*The fish is swimming away*");
	}
	
	@Override
	public void hunt() {
		System.out.println("*The fish is hunting*");
	}
}
