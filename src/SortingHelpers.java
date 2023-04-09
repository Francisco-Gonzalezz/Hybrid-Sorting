
public class SortingHelpers {
	
	/**
	 * This method simply sort the array using insertion sort. It does this by going element by element and placing it in the right spot
	 */
	public static void sortInsertionSort(Array array, int startInclusive, int endExclusive) {
		for(int i = startInclusive+1; i < endExclusive; i++) {
			Comparable current = array.getVal(i);
			
			// while the current one is bigger than the one on the left, it goes down
			int j = i - 1;
			while(j >= startInclusive) {
				Comparable temp = array.getVal(j);
				
				// break if it is no longer bigger than
				if(temp.compareTo(current) <= 0)
					break;

				array.setVal(j+1, temp);
				j--;
			}
			array.setVal(j+1, current);
		}
	}
	
	/**
	 * This helper method will merge 2 segments of an array using the merge sort
	 * merge algorithm.
	 */
	public static void merge(Array array, int start, int middleInclusive, int end) {
		// if the section is 1 element long, its already sorted
		if((end+1)-start <= 1)
			return;
		
		int i = start;
		int j = middleInclusive;
		int k = start;
		
		// if there are 2 elements, just swap if necessary
		if((end+1)-start == 2) {
			Comparable iVal = array.getVal(i);
			Comparable jVal = array.getVal(j);
			if(iVal.compareTo(jVal) < 0) {
				array.setVal(j, iVal);
				array.setVal(i, jVal);
			}
			return;
		}
		
		// setup the lengths + extra array
		int aLength = middleInclusive - start;
		int bLength = end-middleInclusive + 1;
		
		array.initExtra(array.length());
		
		// iterate through both arrays and merge them together
		while(i-start < aLength && j-middleInclusive < bLength) {
			Comparable iVal = array.getVal(i);
			Comparable jVal = array.getVal(j);
			
			// if the left number is smaller than the right, place it first
			if(iVal.compareTo(jVal) < 0) {
				array.setExtraVal(k, iVal);
				i++;
			} else {
				array.setExtraVal(k, jVal);
				j++;
			}
			k++;
		}
		
		// fill in missing values
		while(i-start < aLength)
			array.setExtraVal(k++, array.getVal(i++));

		while(j-middleInclusive < bLength)
			array.setExtraVal(k++, array.getVal(j++));
		
		// finally, fill in the changed values into the og array
		for(int t = 0; t < array.length(); t++) {
			if(t >= start && t <= end)
				array.setVal(t, array.getExtraVal(t));
		}
	}

}
