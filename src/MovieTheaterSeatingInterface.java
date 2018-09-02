
public interface MovieTheaterSeatingInterface {
	int countAvailableSeats();
	String allocateSeats(int numSeats, String name) throws CannotAllocateSeatException;
	
}
