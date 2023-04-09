
public class TimSort {

	public static int MIN_LEVEL_UNTIL_INSERTION = 64;

	/**
	 * This method will sort an array based on the Tim sort algorithm
	 */
	public static void sortTimSort(Array array) {
		timSortHelper(array, 0, array.length());
	}

	/**
	 * This method will sort the array based on Tim sort recursively! It uses the start and end and will use insertion sort if the array is below
	 * the threshold, otherwise it'll break it down using merge sort until the threshold
	 */
	private static void timSortHelper(Array array, int startInclusive, int endExclusive) {
		if(endExclusive - startInclusive <= 0)
			return;
		
		// insertion sort part
		if(endExclusive - startInclusive <= MIN_LEVEL_UNTIL_INSERTION) {
			SortingHelpers.sortInsertionSort(array, startInclusive, endExclusive);
			return;
		}
		
		// merge sort part
		int middle = (int) Math.ceil((endExclusive + startInclusive)/2);
		timSortHelper(array, startInclusive, middle);
		timSortHelper(array, middle, endExclusive);
		
		SortingHelpers.merge(array, startInclusive, middle, endExclusive-1);
	}
}
