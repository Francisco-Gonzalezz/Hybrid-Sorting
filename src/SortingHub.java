import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SortingHub {

	public static void main( String[] args ) throws FileNotFoundException {
		System.out.println( "Hello World!" );
	}
	
	static Array<Integer> createNumberArray(int length, int minInclusive, int maxExclusive) {
		Array<Integer> array = new Array<Integer>(length);
		for(int i = 0; i < array.length(); i++) {
			array.setVal(i, (int)Math.floor((Math.random()*(maxExclusive-minInclusive)) + minInclusive));
		}
		array.resetAccessCount();
		return array;
	}
	
	static Array<String> createStringArray(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));
		
		ArrayList<String> temp = new ArrayList<String>();
		while(scanner.hasNext()) {
			temp.add(scanner.nextLine());
		}
		
		Array<String> array = new Array<String>(temp.size());
		for(int i = 0; i < temp.size(); i++) {
			array.setVal(i, temp.get(i));
		}
		
		scanner.close();
		return array;
	}
	
	public static void sortTimSort(Array array) {}

	public static void sortBlockSort(Array array) {}
}
