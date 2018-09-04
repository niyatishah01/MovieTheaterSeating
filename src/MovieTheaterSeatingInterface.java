import java.io.IOException;

public interface MovieTheaterSeatingInterface {
	String parseInputFile(String s) throws CannotAllocateSeatException;
	String createFile() throws IOException;
}
