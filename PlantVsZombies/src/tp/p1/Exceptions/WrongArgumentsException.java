package tp.p1.Exceptions;

public class WrongArgumentsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrongArgumentsException() {
		super(); // "Wrong Arguments"
	}
	
	public  WrongArgumentsException(String msg) {
		super(msg);
	}
	

	public  WrongArgumentsException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public  WrongArgumentsException(Throwable cause) {
		super(cause);
	}
	
	public  WrongArgumentsException(String message, Throwable cause, boolean enableSuppression, 
		boolean writableStackTrace)
	{
			super(message, cause,enableSuppression, writableStackTrace);
	}

}
