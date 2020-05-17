package tp.p1.Exceptions;

public class CommandParseException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandParseException() {
		super();
	}
	
	public  CommandParseException(String msg) {
		super(msg);
	}
	

	public  CommandParseException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public  CommandParseException(Throwable cause) {
		super(cause);
	}
	
	public CommandParseException(String message, Throwable cause, boolean enableSuppression, 
		boolean writableStackTrace)
	{
			super(message, cause,enableSuppression, writableStackTrace);
	}
}
