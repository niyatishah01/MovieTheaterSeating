import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovieTheaterSeatingTests {

	private MovieTheaterSeatingInterface theaterSeating;
	private static int seatsPerRow = 20;
	private static int noOfRows = 10;

	@Before
	public void setUp() throws Exception {
		try {
			theaterSeating = new MovieTheaterSeating(populateTheaterMap());
		} catch (Exception ignore) {
		}
	}

	@Test
	public void testParseInputFile1() {
		try {
			theaterSeating.parseInputFile(" 2");
		} catch (CannotAllocateSeatException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(e.getMessage(), "Name is empty");
		}
	}

	@Test
	public void testParseInputFile2() {
		try {
			theaterSeating.parseInputFile("R001 -2");
		} catch (CannotAllocateSeatException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(e.getMessage(), "Number of seat requested is 0 or less");
		}
	}

	@Test
	public void testParseInputFile3() {
		try {
			theaterSeating.parseInputFile("R001 2 4");
		} catch (CannotAllocateSeatException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(e.getMessage(), "Parameters incorrect");
		}
	}

	@Test
	public void testParseInputFile4() {
		try {
			String result = theaterSeating.parseInputFile("R001 401");
			Assert.assertEquals(result, "R001 Reservation cannot be made, too many seats requested");
		} catch (CannotAllocateSeatException e) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	public void testParseInputFile5() {
		try {
			String result = theaterSeating.parseInputFile("R001 4").replace(" ", "");
			Assert.assertEquals(result, "R001[A1,A2,A3,A4]");
		} catch (CannotAllocateSeatException e) {
			// TODO Auto-generated catch block
		}
	}

	private static ArrayList<Integer> populateRows() {
		ArrayList<Integer> seats = new ArrayList<Integer>();
		for (int i = 0; i < seatsPerRow; i++)
			seats.add(i + 1);
		return seats;
	}

	private static ConcurrentHashMap<Character, ArrayList<Integer>> populateTheaterMap() {
		ConcurrentHashMap<Character, ArrayList<Integer>> theaterLayout = new ConcurrentHashMap<Character, ArrayList<Integer>>();
		char temp = 'A';
		for (int i = 0; i < noOfRows; i++) {
			theaterLayout.put(temp, populateRows());
			temp++;
		}
		return theaterLayout;
	}
}
