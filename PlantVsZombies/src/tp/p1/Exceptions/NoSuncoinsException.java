package tp.p1.Exceptions;

public class NoSuncoinsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoSuncoinsException() {
		super(); // "No suncoins"
	}
	
	public  NoSuncoinsException(String msg) {
		super(msg);
	}
	

	public  NoSuncoinsException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public  NoSuncoinsException(Throwable cause) {
		super(cause);
	}
	
	public  NoSuncoinsException(String message, Throwable cause, boolean enableSuppression, 
		boolean writableStackTrace)
	{
			super(message, cause,enableSuppression, writableStackTrace);
	}

}
