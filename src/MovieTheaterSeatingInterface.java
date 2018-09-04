
import java.io.IOException;


public interface MovieTheaterSeatingInterface {
	void parseInputFile(String s) throws CannotAllocateSeatException;
	String createFile() throws IOException;
}
