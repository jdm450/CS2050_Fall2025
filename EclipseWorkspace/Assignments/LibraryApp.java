/**
 * 
 */

/**
 * 
 */
public class LibraryApp {

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
		Library library = new Library("Test Library", numberOfShelves, shelfCapacity);
		library.displayCountPerShelf();
		library.printAllBooks();
		library.displayOldest();
		// Row 0
		library.addBook(null);
		library.addBook(new Book("Unmasking AI", "Joy Buolamwini", 2023));
		library.addBook(new Book("Hello World", "Hannah Fry", 2018));
		library.addBook(new Book("Race After Technology", "Ruha Benjamin", 2019));
		library.addBook(new Book("Deep Learning", "Ian Goodfellow", 2016));
		library.displayCountPerShelf();
		library.printAllBooks();
		library.displayOldest();
		// Row 1
		library.addBook(new Book("Algorithms to Live By", "Brian Christian", 2016));
		library.addBook(new Book("Weapons of Math Destruction", "Cathy O'Neil", 2016));
		library.addBook(new Book("The Mythical Man-Month", "Fred Brooks", 1975));
		library.addBook(new Book("Refactoring", "Martin Fowler", 1999));
		// Row 2
		library.addBook(new Book("The Pragmatic Programmer", "Andrew Hunt & David Thomas", 1999));
		library.addBook(new Book("Peopleware", "Tom DeMarco & Tim Lister", 1987));
		library.addBook(new Book("Computer Lib / Dream Machines", "Ted Nelson", 1975));
		library.displayCountPerShelf();
		library.printAllBooks();
		library.displayOldest();
		System.out.println();
		System.out.println("Test add more books than capacity...");
		library.addBook(new Book("Extra Title", "Extra Author", 2024)); // should trigger "full" message
		library.displayCountPerShelf();
		library.printAllBooks();
		library.displayOldest();

		// test adding more books than capacity
		System.out.println("\nTest adding a book when library is full");
		library.addBook(new Book("Harry Potter and the Half Blood Prince", "JK Rowling", 2005));

	} // end of main method

} // end of driver class

class Book {

	// instance variables
	private String title;
	private String author;
	private int year;

	// constructor
	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}

	// getter methods

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	// to string method
	@Override
	public String toString() {
		String formmatedString = String.format("\"%s\" by %s (%d)", title, author, year);
		return formmatedString;
	}
} // end of Book class

class Library {

	// instance variables
	private String name;
	private Book[][] bookShelf;
	private int numberOfShelves;
	private int shelfCapcity;
	private int currentShelf;
	private int currentSlot;
	private boolean isFull;

	// constructor
	public Library(String name, int numberOfShelves, int shelfCapacity) {
		this.name = name;
		this.numberOfShelves = numberOfShelves;
		this.shelfCapcity = shelfCapacity;
		bookShelf = new Book[numberOfShelves][shelfCapacity];
	}

	public String getName() {
		return name;
	}

	// this works but the logic is hard to follow... maybe try another way?
	public void addBook(Book bookToAdd) {
		if (bookToAdd == null) {
			System.out.println("Invalid Book.");
		} else {
			if (isFull) {
				System.out.printf("Can't add: %s %nThe library is full%n", bookToAdd.toString());
			} else {
				if (currentSlot < shelfCapcity) {
					bookShelf[currentShelf][currentSlot] = bookToAdd;
					System.out.printf("added %s at shelf %d, row %d%n", bookToAdd.toString(), currentShelf + 1,
							currentSlot + 1);
					currentSlot++;
				} else {
					currentShelf++;
					currentSlot = 0;
					if (currentShelf >= numberOfShelves) {
						isFull = true;
					}
					addBook(bookToAdd);
				}
			}
		}
	}

	public void printAllBooks() {
		System.out.println("------------------------------------------------------------\n"
				+ "All books in Test Library\n" + "Shelf   Slot   Book Details\n"
				+ "------------------------------------------------------------\n");
		int count = 0;
		for (int i = 0; i <= currentShelf; i++) {
			for (int j = 0; j < bookShelf[0].length; j++) {
				if (bookShelf[i][j] != null) {
					System.out.printf("%d	%d %s%n", i + 1, j + 1, bookShelf[i][j].toString());
					count++;
				}
			}
		}
		System.out.printf("(%d of %d slots filled)%n", count, (numberOfShelves * shelfCapcity));
		System.out.println("------------------------------------------------------------");
	}
	
	// not sure how to make multiple oldest books work without array list to store oldest books
	// or iterating through the array twice.
	public void displayOldest() {
		if (bookShelf[0][0] == null) {
			System.out.println("Display Oldest: Library is empty");
		} else {
			int oldestYear = bookShelf[0][0].getYear();
			for (int i = 0; i <= currentShelf; i++) {
				for (int j = 0; j < bookShelf[0].length; j++) {
					if (bookShelf[i][j] != null) {
						if (bookShelf[i][j].getYear() < oldestYear) {
							oldestYear = bookShelf[i][j].getYear();
						}
					}
				}
			}
			System.out.printf("Oldest Book in %s%nEarliest Publication Year: %d%n", name, oldestYear);
			for (int i = 0; i <= currentShelf; i++) {
				for (int j = 0; j < bookShelf[0].length; j++) {
					if (bookShelf[i][j] != null) {
						if (bookShelf[i][j].getYear() == oldestYear) {
							System.out.println(bookShelf[i][j].toString());
						}
					} else {
						return;
					}
				}
			}
		}
	}

	public void displayCountPerShelf() {
		for (int i = 0; i < bookShelf.length; i++) {
			int count = 0;
			for (int j = 0; j < bookShelf[0].length; j++) {
				if (bookShelf[i][j] != null) {
					count++;
				}
			}
			System.out.printf("Shelf %d has %d books%n", (i + 1), count);
		}
	}
} // end of Library class
