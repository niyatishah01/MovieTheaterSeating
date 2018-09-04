import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MovieTheaterSeating implements MovieTheaterSeatingInterface {

	private  ConcurrentHashMap<Character, ArrayList<Integer>> theaterLayout = new ConcurrentHashMap<Character, ArrayList<Integer>>();
	private  HashMap<String, Integer> reservationIdentifersList = new HashMap<String, Integer>(); 
	private  HashMap<String, ArrayList<String>> reservationDetails= new HashMap<String, ArrayList<String>>();
	private  int seatsPerRow=20;
	private  int noOfRows=10;
	private  int countAvailableSeats=seatsPerRow*noOfRows;
	
	public MovieTheaterSeating(ConcurrentHashMap<Character, ArrayList<Integer>> populateTheaterMap) {
		theaterLayout=populateTheaterMap;
	}

	@Override
	public void parseInputFile(String s) throws CannotAllocateSeatException {
		String split[]=s.split(" ");
		validateRequest(split);
	}
	
	private void validateRequest(String split[]) throws CannotAllocateSeatException {
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
	private void searchForBestSeats(int numSeats,String name){
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
	private void updateSeats(Character row, int numSeats){
		ArrayList<Integer> availableSeats=theaterLayout.get(row);
			for(int i=0;i<numSeats;i++)
				availableSeats.remove(0);
	}
	private ArrayList<String> printSeats(ArrayList<Integer> availableSeats, int numSeats, Character rowId)
	{
		ArrayList<String> result= new ArrayList<String>();
		for(int i=0;i<numSeats;i++)
		{
			result.add(Character.toString(rowId)+availableSeats.get(i));
		}
		return result;
	}
	private Character closestFit(int numSeats)
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
	@Override
	public String createFile() throws IOException {
		String content="";
		String newContent="";
		for(HashMap.Entry<String, ArrayList<String>> entry : reservationDetails.entrySet())
		{
			 content+=entry.getKey()+" "+entry.getValue()+System.lineSeparator();
			 newContent=content.replace("[", "").replace("]","");
		}
		String path=createOutputFile("output.txt",newContent);
		return path;
	}

	private String createOutputFile(String fileName, String content) throws IOException 
	{
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
        File f=new File(fileName);
        return f.getCanonicalPath();
    }

}