import java.util.Random;
import java.util.Arrays;
public class SlectionSort {

	public static void main(String[] args) {
		
		// test duplicate array
		// int[] test = {9, 7, 5, 9, 7, 5};
		
		int[] randomArray = new int[10];
		populateArray(randomArray);
		
		System.out.println("Random Array:");
		System.out.println(Arrays.toString(randomArray));
		
		selectionSort(randomArray);
		System.out.println("\nSorted Array:");
		System.out.println(Arrays.toString(randomArray));
		
	}

	public static void populateArray(int[] array) {
		Random random = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(100) + 1; // random integer 1 - 100
		}
	}
	
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			int temp = array[i];
			array[i] = array[minIndex];
			array[minIndex] = temp;
		}
	}

}
