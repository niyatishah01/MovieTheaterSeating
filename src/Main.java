import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
///Users/niyatishah01/Desktop/test.txt

public class Main {

	private static ConcurrentHashMap<Character, ArrayList<Integer>> theaterLayout = new ConcurrentHashMap<Character, ArrayList<Integer>>();
	private static HashMap<String, Integer> reservationIdentifersList = new HashMap<String, Integer>(); 
	private static HashMap<String, ArrayList<String>> reservationDetails= new HashMap<String, ArrayList<String>>();
	private static int seatsPerRow=20;
	private static int noOfRows=10;
	private static int countAvailableSeats=200;
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
	    sc.close();
	    userInput.close();
	    System.out.println(reservationDetails);
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
		private static void searchForBestSeats(int numSeats,String name){
			String result="";
			//create a new list for all these names
			if (numSeats > countAvailableSeats) {
				result += name + "Reservation cannot be made. %n";
			}
		
			if(numSeats > seatsPerRow) {
				result += name + "think about this logic %n";
			}
			
			    	Character row=closestFit(numSeats);
			    	ArrayList<Integer> availableSeats=theaterLayout.get(row);
					reservationDetails.put(name, printSeats(availableSeats, numSeats, row));
					updateSeats(row,numSeats);
					countAvailableSeats-=numSeats;
					return;		
		}
		private static void updateSeats(Character row, int numSeats){
			ArrayList<Integer> availableSeats=theaterLayout.get(row);
				for(int i=0;i<numSeats;i++)
					availableSeats.remove(0);
		}
		private static ArrayList<String> printSeats(ArrayList<Integer> availableSeats, int numSeats, Character rowId)
		{
			ArrayList<String> result= new ArrayList<String>();
			for(int i=0;i<numSeats;i++)
			{
				result.add(Character.toString(rowId)+availableSeats.get(i));
			}
			return result;
		}
		private static Character closestFit(int numSeats)
		{
			int minSize = Integer.MAX_VALUE;
			Character row=Character.MIN_VALUE;
			for (ConcurrentHashMap.Entry<Character, ArrayList<Integer>> entry : theaterLayout.entrySet())
			{
			    if ((entry.getValue().size()-numSeats)<minSize&&(entry.getValue().size()-numSeats)>=0)
			    {
			    	minSize = entry.getValue().size()-numSeats;
			    	row=entry.getKey();
			    }
			}
			return row;
		}
		private static ArrayList<Integer> populateRows() {
			ArrayList<Integer> seats= new ArrayList<Integer>(); 
			for(int i=0;i<seatsPerRow;i++)
				seats.add(i+1);	
			return seats;
		}
		
		private static void populateTheaterMap()
		{
			char temp='A';
			for(int i=0;i<noOfRows;i++)
			{
				theaterLayout.put(temp,populateRows());
				temp++;
			}
		}
}
