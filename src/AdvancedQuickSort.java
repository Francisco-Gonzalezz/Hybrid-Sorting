
public class AdvancedQuickSort {

	public static int MIN_LEVEL_UNTIL_INSERTION = 10;
	
	public static void sortAdvancedQuickSort(Array array) {
		quicksort(array, 0, array.length() - 1);
	}
	
	private static void quicksort( Array array, int start, int end ) {
		if(end-start <= MIN_LEVEL_UNTIL_INSERTION)
			SortingHelpers.sortInsertionSort(array, start, end);
		
		else if (start < end ){
			int pivotIndex = makePivot( array, start, end );
			// Continue to do quicksort on the left side
			quicksort( array, start, pivotIndex - 1 );
			// Continue to do quicksort and the right side
			quicksort( array, pivotIndex + 1, end );
		}
	}

	private static int makePivot( Array array, int start, int end ){
		// For this case of quicksort we will have the pivot always be the end index.
		Comparable val = array.getVal( end );

		int indexHelper = (start - 1);

		for (int index = start; index <= end - 1; index++) {
			// If current element is less than pivot value
			array.madeComparison();
			if (array.getVal( index ).compareTo( val ) < 0 ) {
				indexHelper++;
				swap(array, indexHelper, index);
			}
		}
		swap(array, indexHelper + 1, end );
		return (indexHelper + 1);
	}

	private static void swap( Array array, int indexOne, int indexTwo ) {
		Comparable val = array.getVal( indexTwo );
		array.setVal( indexTwo, array.getVal( indexOne ) );
		array.setVal( indexOne, val );
	}
}
