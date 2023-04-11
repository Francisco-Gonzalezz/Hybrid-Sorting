import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SortingHub {

	/*
	 *	Main, runs the ui for the tester 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(" -- Welcome to the Sorting Hub! -- \n   TimSort & Advanced Quicksort!");

		Scanner scanner = new Scanner(System.in);

		boolean loop = true;
		while (loop) {
			System.out.println("\nPlease choose a sorting algorithm: (tim / advanced / compare)");

			// ask which algorithm to use
			String type = scanner.nextLine();
			if (!type.equals("tim") && !type.equals("advanced") && !type.equals("compare")) {
				System.out.println("\"" + type + "\" is not 'tim', 'advanced' or 'compare'... Exiting!");
				return;
			}

			// ask what datatype to use
			System.out.println("\nWhat type of data do you want to sort?\n(integer / string)");
			String dataset = scanner.nextLine();
			if (!dataset.equals("integer") && !dataset.equals("string")) {
				System.out.println("\"" + dataset + "\" is not 'integer' or 'string' Exiting!");
				return;
			}
			
			// run the algorithms
			if(type.equals("compare"))
				handleComparisonSorting(scanner, type, dataset);
			else
				handleSingleSorting(scanner, type, dataset);

			System.out.println("Run again? (yes/no)");

			// run again?
			scanner.nextLine();
			loop = scanner.nextLine().equals("yes");
		}
	}

	/*
	 *	This method will handle for single sorting
	 */
	private static void handleSingleSorting(Scanner scanner, String type, String dataset) throws FileNotFoundException {
		// create the array based on the datatype
		Array array;
		if (dataset.equals("integer"))
			array = createIntegerArrayFromCommandLine(scanner, type);
		else
			array = createStringArrayFromCommandLine(scanner, type);

		// print out the array
		System.out.println("\nUnsorted Array:");
		System.out.println(array);

		// actually sort it
		System.out.println("\nSorting...");
		long start = System.currentTimeMillis();

		if (type.equals("tim"))
			TimSort.sortTimSort(array);
		else
			AdvancedQuickSort.sortAdvancedQuickSort(array);

		long end = System.currentTimeMillis();

		// print results
		System.out.println(array);
		System.out.println("----------");
		System.out.println("Time:         " + (end - start) + "ms");
		System.out.println("Access Count: " + array.getAccessCount());
		System.out.println("Comparisons:  " + array.getComparisonCount());
		System.out.println("----------\n\n");
	}

	/*
	 *	This method will handle the comparisons between algorithms
	 */
	private static void handleComparisonSorting(Scanner scanner, String type, String dataset) throws FileNotFoundException {
		Array template;
		if (dataset.equals("integer"))
			template = createIntegerArrayFromCommandLine(scanner, type);
		else
			template = createStringArrayFromCommandLine(scanner, type);
		
		Array tim = template.clone();
		Array adv = template.clone();
		
		// print out the array
		System.out.println("\nUnsorted Array:");
		System.out.println(tim);

		// actually sort it
		System.out.println("\nSorting Tim...");
		long startTim = System.currentTimeMillis();
		TimSort.sortTimSort(tim);
		long endTim = System.currentTimeMillis();

		System.out.println("Sorting Advanced...\n");
		long startAdv = System.currentTimeMillis();
		AdvancedQuickSort.sortAdvancedQuickSort(adv);
		long endAdv = System.currentTimeMillis();

		System.out.println("TIM SORT: ");
		System.out.println(tim);
		System.out.println("----------");
		System.out.println("Time:         " + (endTim - startTim) + "ms");
		System.out.println("Access Count: " + tim.getAccessCount());
		System.out.println("Comparisons:  " + tim.getComparisonCount());
		System.out.println("----------\n\n");

		System.out.println("ADVANCED QUICK SORT: ");
		System.out.println(adv);
		System.out.println("----------");
		System.out.println("Time:         " + (endAdv - startAdv) + "ms");
		System.out.println("Access Count: " + adv.getAccessCount());
		System.out.println("Comparisons:  " + adv.getComparisonCount());
		System.out.println("----------\n\n");
		
		System.out.println("Time Winner:         " + ((endTim - startTim) > (endAdv - startAdv) ? "Advanced Sort!" : "Tim Sort!"));
		System.out.println("Access Count Winner: " + (tim.getAccessCount() > adv.getAccessCount() ? "Advanced Sort!" : "Tim Sort!"));
		System.out.println("Comparisons Winner:  " + (tim.getComparisonCount() > adv.getComparisonCount() ? "Advanced Sort!" : "Tim Sort!"));
		System.out.println("\n");
	}

	/*
	 *	This method will handle the integer datatype
	 */
	private static Array<Integer> createIntegerArrayFromCommandLine(Scanner scanner, String type) {
		System.out.println("\nArray length?"
				+ (type.equals("tim") ? " (Insertion sort on subsets <= " + TimSort.MIN_LEVEL_UNTIL_INSERTION + ")"
						: " (Insertion sort on subsets <= " + AdvancedQuickSort.MIN_LEVEL_UNTIL_INSERTION + ")"));
		int length = scanner.nextInt();

		return createNumberArray(length, -length, length);
	}

	/*
	 *	This method will handle the string datatype
	 */
	private static Array<String> createStringArrayFromCommandLine(Scanner scanner, String type)
			throws FileNotFoundException {
		System.out.println("\nPlease give the file path to a file containing a list of words, each on a new line");
		String path = scanner.nextLine();

		return createStringArray(path);
	}

	/*
	 *	This method will create a random number array
	 */
	private static Array<Integer> createNumberArray(int length, int minInclusive, int maxExclusive) {
		Array<Integer> array = new Array<Integer>(length);
		for (int i = 0; i < array.length(); i++) {
			array.setVal(i, (int) Math.floor((Math.random() * (maxExclusive - minInclusive)) + minInclusive));
		}
		array.resetAccessCount();
		return array;
	}

	/*
	 *	This method will create a string array from a file
	 */
	private static Array<String> createStringArray(String filename) throws FileNotFoundException {
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
