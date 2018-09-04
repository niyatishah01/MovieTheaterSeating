public class CannotAllocateSeatException extends Exception {
	/**
	 * Exception to handle invalid input 
	 */
	private static final long serialVersionUID = 1L;

	public CannotAllocateSeatException(String message) {
		super(message);
	}

	public CannotAllocateSeatException() {
		super();
	}
}
