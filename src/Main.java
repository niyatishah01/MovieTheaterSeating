import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
	/*
	 * Assuming a fixed theater layout, having 10 rows and 20 seats per row.
	 */
	private static int seatsPerRow = 20;
	private static int noOfRows = 10;

	public static void main(String[] args) throws IOException, CannotAllocateSeatException {

		System.out.println("Enter input file path");
		Scanner userInput = new Scanner(System.in);
		String path = userInput.next();
		File file = new File(path);
		Scanner sc = new Scanner(file);
		MovieTheaterSeatingInterface theaterSeating = new MovieTheaterSeating(populateTheaterMap());
		while (sc.hasNextLine()) {
			theaterSeating.parseInputFile(sc.nextLine());
		}
		sc.close();
		userInput.close();
		System.out.println(theaterSeating.createFile());

	}

	/*
	 * Generating the Theater Seat Layout
	 */
	private static ConcurrentHashMap<Character, ArrayList<Integer>> populateTheaterMap() {
		ConcurrentHashMap<Character, ArrayList<Integer>> theaterLayout = new ConcurrentHashMap<Character, ArrayList<Integer>>();
		char temp = 'A';
		for (int i = 0; i < noOfRows; i++) {
			theaterLayout.put(temp, populateRows());
			temp++;
		}
		return theaterLayout;
	}

	private static ArrayList<Integer> populateRows() {
		ArrayList<Integer> seats = new ArrayList<Integer>();
		for (int i = 0; i < seatsPerRow; i++)
			seats.add(i + 1);
		return seats;
	}
}
