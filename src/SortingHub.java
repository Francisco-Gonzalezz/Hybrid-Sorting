import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SortingHub {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Welcome to Sorting Hub!\nWe have implemented two algorithms: TimSort and Advanced QuickSort!");
		System.out.println("Please choose a sorting algorithm\n(tim / advanced)");

		Scanner scanner = new Scanner(System.in);

		// ask which algorithm to use
		String type = scanner.nextLine();
		if (!type.equals("tim") && !type.equals("advanced")) {
			System.out.println("\"" + type + "\" is not 'tim' or 'advanced'...");
			return;
		}

		// ask what datatype to use
		System.out.println(type + " sort it is! Now, what type of data do you want to sort?\n(integer / string)");
		String dataset = scanner.nextLine();
		if (!dataset.equals("integer") && !dataset.equals("string")) {
			System.out.println("\"" + dataset + "\" is not 'integer' or 'string'");
			return;
		}

		// create the array based on the datatype
		Array array;
		if (dataset.equals("integer"))
			array = createIntegerArrayFromCommandLine(scanner, type);
		else
			array = createStringArrayFromCommandLine(scanner, type);

		// print out the array if possible
		System.out.println("Here is your original array:");
		System.out.println(array);

		// actually sort it
		long start = System.currentTimeMillis();
		
		System.out.println("\nSorting...");
		if (type.equals("tim"))
			TimSort.sortTimSort(array);
		else
			AdvancedQuickSort.sortAdvancedQuickSort(array);
		
		long end = System.currentTimeMillis();

		// print results
		System.out.println("----------");
		System.out.println(array);
		System.out.println("Time:         " + (end - start) + "ms");
		System.out.println("Access Count: " + array.getAccessCount());
		System.out.println("----------");

	}

	static Array<Integer> createIntegerArrayFromCommandLine(Scanner scanner, String type) {
		System.out.println("How large do you want the array to be?" + (type.equals("tim")
				? "\n(Insertion sort only happens on subsets <= " + TimSort.MIN_LEVEL_UNTIL_INSERTION + ")"
				: ""));
		int length = scanner.nextInt();
		System.out.println("Creating the array...");

		return createNumberArray(length, -length, length);
	}

	static Array<String> createStringArrayFromCommandLine(Scanner scanner, String type) throws FileNotFoundException {
		System.out.println("Please give the file path to a file containing a list of words, each on a new line");
		String path = scanner.nextLine();
		System.out.println("Creating the array...");

		return createStringArray(path);
	}

	static Array<Integer> createNumberArray(int length, int minInclusive, int maxExclusive) {
		Array<Integer> array = new Array<Integer>(length);
		for (int i = 0; i < array.length(); i++) {
			array.setVal(i, (int) Math.floor((Math.random() * (maxExclusive - minInclusive)) + minInclusive));
		}
		array.resetAccessCount();
		return array;
	}

	static Array<String> createStringArray(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));

		ArrayList<String> temp = new ArrayList<String>();
		while (scanner.hasNext()) {
			temp.add(scanner.nextLine());
		}

		Array<String> array = new Array<String>(temp.size());
		for (int i = 0; i < temp.size(); i++) {
			array.setVal(i, temp.get(i));
		}

		scanner.close();
		return array;
	}
}
