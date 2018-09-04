 public class CannotAllocateSeatException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CannotAllocateSeatException(String message){
		super(message);
	}
	
	public CannotAllocateSeatException(){
		super();
	}
}
