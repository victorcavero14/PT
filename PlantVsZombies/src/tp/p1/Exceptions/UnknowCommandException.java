package tp.p1.Exceptions;

public class UnknowCommandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnknowCommandException() {
		super(); // "Unknown Command. Use 'Help' to see available commands."
	}
	
	public  UnknowCommandException(String msg) {
		super(msg);
	}
	

	public  UnknowCommandException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public  UnknowCommandException(Throwable cause) {
		super(cause);
	}
	
	public  UnknowCommandException(String message, Throwable cause, boolean enableSuppression, 
		boolean writableStackTrace)
	{
			super(message, cause,enableSuppression, writableStackTrace);
	}

}
