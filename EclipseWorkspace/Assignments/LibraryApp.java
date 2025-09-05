/**
 * 
 */
import java.time.Year;
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
		System.out.println("\nTest: adding a book when library is full");
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

	// toString method
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

	/**
	 * Constructor for the Library class. currentShelf, currentSlot, and isFull do not need to be
	 * explicitly declared in the constructor because default value is 0 for integers and false for booleans
	 * @param name
	 * @param numberOfShelves
	 * @param shelfCapacity
	 */
	public Library(String name, int numberOfShelves, int shelfCapacity) {
		this.name = name;
		this.numberOfShelves = numberOfShelves;
		this.shelfCapcity = shelfCapacity;
		bookShelf = new Book[numberOfShelves][shelfCapacity];
	}
	
	/**
	 * Getter method for name variable.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds a book to the bookshelf array and adjusts row and column variables based on current position in the array.
	 * I implemented one-step recursion to eliminate repetitive print statements.
	 * Not as elegant as integer division and modulo, but it works.
	 * @param bookToAdd
	 */
	public void addBook(Book bookToAdd) {
		if (bookToAdd == null) {
			System.out.println("Invalid Book.");
			return;
		}
		if (isFull) {
			System.out.printf("Can't add: %s %nThe library is full%n", bookToAdd.toString());
			return;
		}
		if (currentSlot < shelfCapcity) {
			bookShelf[currentShelf][currentSlot] = bookToAdd;
			System.out.printf("added %s at shelf %d, row %d%n", bookToAdd.toString(), currentShelf + 1, currentSlot + 1);
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
	
	/**
	 * Iterates through the bookShelf array up to and including currentShelf. 
	 * Prints the row, column and book details for each index that isn't null.
	 */
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
	
	// currently iterating through the array twice.
	// not sure how to make one iteration work without array list to store oldest books
	// but wait... there's more... 
	// use array[edge_max_books] with counter 
	// or store oldest year as an instance variable of the library?
	/**
	 * public void displayOldest() {
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
	 */
	
	/**
	 * refactored method to display oldest books using an array to store the oldest books.
	 * See commented out method above for original implementation and limitations.
	 */
	public void displayOldest() {
		System.out.printf("Oldest Book in %s:%n", name);
		if (bookShelf[0][0] == null) {
			System.out.println("Library is empty");
			return;
		}
		Book[] oldestBooks = new Book[numberOfShelves * shelfCapcity];  // I need array lists {https://media1.tenor.com/m/47qpxBq_Tw0AAAAd/cat-cat-meme.gif}
		int oldestYear = bookShelf[0][0].getYear();
		int oldestBooksCount = 0;
		for (int i = 0; i <= currentShelf; i++) {
			for (int j = 0; j < bookShelf[0].length; j++) {
				Book currentBook = bookShelf[i][j];
				if (currentBook != null) {
					int currentYear = currentBook.getYear();
					if (currentYear < oldestYear) {
						oldestYear = currentYear;
						oldestBooks[0] = currentBook;
						oldestBooksCount = 1;
					} else if (currentYear == oldestYear) {
						oldestBooks[oldestBooksCount] = currentBook;
						oldestBooksCount++;
					}
				}
			}
		}
		for (int i = 0; i < oldestBooksCount; i++) {
			System.out.println(oldestBooks[i].toString());
		}
	}
	
	/**
	 * Method to print the number of books at each row.
	 */
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
