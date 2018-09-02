import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
///Users/niyatishah01/Desktop/test.txt

public class Main {
	private static ArrayList<Integer> seats=new ArrayList<Integer>(); 
	private static HashMap<Character, ArrayList<Integer>> theaterLayout = new HashMap<Character, ArrayList<Integer>>();
	private static HashMap<String, Integer> reservationIdentifersList = new HashMap<String, Integer>(); 
	private static AtomicInteger countAvailableSeats = new AtomicInteger(0);
	private static int seatsPerRow=20;
	private static int noOfRows=10;
	
	public static void main(String[] args) throws IOException, CannotAllocateSeatException  {
		
		System.out.println("Enter input file path");
		Scanner userInput = new Scanner(System. in);
		String path = userInput. next();
		File file = new File(path); 
		Scanner sc = new Scanner(file);
		populateTheaterMap();
	    while (sc.hasNextLine())
	    {
	    	splitLine(sc.nextLine());
	    }
	    userInput.close();
	    sc.close();
		
	}
		private static void splitLine(String s) throws CannotAllocateSeatException {
			String split[]=s.split(" ");
			validateRequest(split);
		}
		private static void validateRequest(String split[]) throws CannotAllocateSeatException {
			String name="";
			int numSeats=0;
			if(split.length==2) {
				 name=split[0];
				 numSeats=Integer.parseInt(split[1]);
				 reservationIdentifersList.put(name,numSeats);
				 searchForBestSeats(numSeats,name);
			}
			if(split.length>2||split.length<2)
			{
				throw new CannotAllocateSeatException("Parameters incorrect");
			}
			if (numSeats <= 0) {
				throw new CannotAllocateSeatException("Number of seat requested is 0 or less");
			}

			if (name == null || name.isEmpty()) {
				throw new CannotAllocateSeatException("Name is empty");
			}
					
		}
		private static String searchForBestSeats(int numSeats,String name){
			String result="";
			
			/*if (numSeats > countAvailableSeats.get()) {
				result += name + "Reservation cannot be made. %n";
				return result;
			}
		*/
			if(numSeats > seatsPerRow) {
				result += name + "think about this logic %n";
				return result;
			}
			System.out.println("out here");
			for (int i = 0; i < noOfRows; i++) {
				System.out.println("in here");
				ArrayList<Integer> availableSeats = theaterLayout.get('A');
					int seatCnt = availableSeats.size();
					if (seatCnt > 0 && seatCnt >= numSeats&&bestFit(seatCnt, numSeats)) {
						
						result+= name + printSeats(availableSeats, numSeats, i);

					}
				}
			System.out.println(result);
			return result;	
		}

		private static String printSeats(ArrayList<Integer> availableSeats, int numSeats, int rowId)
		{
			String result="";
			for(int i=0;i<numSeats;i++)
			{
				result+=Character.toString((char)(rowId+64))+availableSeats.get(i);
			}
			return result;
		}
		private static boolean bestFit(int seatCnt, int numSeats) {
				return (seatCnt - numSeats == 0);
		}
		private static void populateRows() {
			for(int i=0;i<seatsPerRow;i++)
				seats.add(i+1);	
		}
		
		private static void populateTheaterMap()
		{
			populateRows();
			char temp='A';
			for(int i=0;i<noOfRows;i++)
			{
				theaterLayout.put(temp,seats);
				temp++;
			}
			//System.out.println(theaterLayout);
		}

}
