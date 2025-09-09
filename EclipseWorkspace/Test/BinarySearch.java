
public class BinarySearch {

	public static void main(String[] args) {
		
		int[] sortedArray = new int[50];
		
		populateArray(sortedArray);
		
		int left = 0;
		int right = sortedArray.length-1;
		int target = 17;
		
		int result = binSearch(target, sortedArray, left, right);
		
		System.out.println(result);

	}

	public static int binSearch(int target, int[] sortedArray, int left, int right) {
		
		if (right >= left) {
			
			int mid = left + (right - left) / 2;
			
			if (target == sortedArray[mid]) {
				return mid;
			}
			
			if (target > sortedArray[mid]) {
				return binSearch(target, sortedArray, mid+1, right);
			}
			
			if (target < sortedArray[mid]) {
				return binSearch(target, sortedArray, left, mid -1);
			}
		}
		return -1;
	}

	public static void populateArray(int[] sortedArray) {
		for (int i =0; i < sortedArray.length; i++) {
			sortedArray[i] = i+1;
		}
		
	}

}
