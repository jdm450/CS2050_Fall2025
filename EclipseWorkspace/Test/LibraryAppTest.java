/**
 * 
 */

/**
 * 
 */
public class LibraryAppTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// --- unit test checks for Book ---
		System.out.println("Unit Test Book Class");
		Book unitTestBook = new Book("Unmasking AI", "Joy Buolamwini", 2023);
		System.out.println("getTitle():   " + unitTestBook.getTitle());
		System.out.println("getAuthor():  " + unitTestBook.getAuthor());
		System.out.println("getYear():    " + unitTestBook.getYear());
		System.out.println("stringOfBookDetails():   " + unitTestBook.toString());
		System.out.println();
		System.out.println("Setting up Test Library");
		int numberOfShelves = 3;
		int shelfCapacity = 4;
		System.out.println("Shelves (rows): " + numberOfShelves);
		System.out.println("Slots per shelf (columns): " + shelfCapacity);
		System.out.println("Total capacity: " + (numberOfShelves * shelfCapacity));
		System.out.println();

	}
}

class Book {
	
	// Instance Variables
	private String title;
	private String author;
	private int year;
	
	// Constructor
	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	// Instance methods
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getYear() {
		return year;
	}
	
	@Override
	public String toString() {
		String formattedBook = String.format("\"%s\" by %s (%d)", title, author, year);
		return formattedBook;
	}
}

class Library {
	
	// Instance variables
	private String name;
	private Book[][] bookShelf;
	private int numberOfShelves;
	private int shelfCapacity;
	private int currentShelf;
	private int currentSlot;
	private boolean isFull;
	
	// Constructor
	public Library(String name, int numberOfShelves, int shelfCapacity) {
		if (name == null) {
			this.name = "Library";
		} else {
			this.name = name;
		}
		if (numberOfShelves <= 0 || shelfCapacity <= 0) {
			this.numberOfShelves = 1;
			this.shelfCapacity = 1;
		} else {
			this.numberOfShelves = numberOfShelves;
			this.shelfCapacity = shelfCapacity;
		}
		bookShelf = new Book[numberOfShelves][shelfCapacity];
		this.currentShelf = 0;
		this.currentSlot = 0;
		this.isFull = false;
	}
	
	// Instance methods
	public String getName() {
		return name;
	}
	
}

