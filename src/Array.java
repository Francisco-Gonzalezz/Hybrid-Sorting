import java.util.Arrays;

public class Array<T> {
	private Comparable<T>[] array;// the array that will be sorted
	private Comparable<T>[] copy;// a copy of the array to compare after sorting--you should never access this in
						// your code!
	private Comparable<T>[] extra;// a temporary array in case you use some version of mergesort
	private int accessCount;

	// constructor that takes an int array
	public Array(Comparable<T>[] array) {
		this.array = array;
		this.accessCount = 0;
	}
	
	public Array(int length) {
		this.array = new Comparable[length];
		this.accessCount = 0;
	}

	// initialize the extra array
	public void initExtra(int size) {
		this.extra = new Comparable[size];
	}

	// gets a String representation of the array
	public String toSstring() {
		return Arrays.toString(array);
	}

	// gets the length of the array
	public int length() {
		return array.length;
	}

	// gets the value at index i
	public Comparable<T> getVal(int i) {
		if (!checkIndex(i))
			return null;
		accessCount++;
		return array[i];
	}

	// sets the value at index i to val
	public void setVal(int i, Comparable<T> val) {
		array[i] = val;
		accessCount++;
	}

	// swaps the values at i and j
	public void swap(int i, int j) {
		Comparable<T> temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		accessCount += 4;
	}

	// returns the accessCount
	public int getAccessCount() {
		return accessCount;
	}
	
	public void resetAccessCount() {
		accessCount = 0;
	}

	// sets the extra array value at index i to val
	public void setExtraVal(int i, Comparable<T> val) {
		extra[i] = val;
		accessCount++;
	}

	// get the size of the extra array
	public int getExtraSize() {
		return extra.length;
	}

	// gets the extra array value at index i
	public Comparable<T> getExtraVal(int i) {
		if (!checkIndex(i))
			return null;
		accessCount++;
		return extra[i];
	}

	// checks that the array is sorted
	public boolean isSorted() {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != copy[i])
				return false;
		}
		return true;
	}

	// checks that the index is in bounds
	private boolean checkIndex(int i) {
		if (i < 0 || i > array.length) {
			System.err.println("Index " + i + " is out of bounds.");
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String build = "Main: [";
		for (int i = 0; i < array.length; i++) {
			if (i < array.length - 1)
				build += array[i] + ", ";
			else
				build += array[i];
		}
		build += "]\n";

		if (this.extra != null) {
			build += "Extra: [";
			for (int i = 0; i < extra.length; i++) {
				if (i < extra.length - 1)
					build += extra[i] + ", ";
				else
					build += extra[i];
			}
			build += "]\n";
		}

		return build;
	}
}
