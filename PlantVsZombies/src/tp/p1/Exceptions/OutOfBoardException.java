package tp.p1.Exceptions;

public class OutOfBoardException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OutOfBoardException() {
		super();
	}
	
	public  OutOfBoardException(String msg) {
		super(msg);
	}
	

	public  OutOfBoardException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public  OutOfBoardException(Throwable cause) {
		super(cause);
	}
	
	public  OutOfBoardException(String message, Throwable cause, boolean enableSuppression, 
		boolean writableStackTrace)
	{
			super(message, cause,enableSuppression, writableStackTrace);
	}

}
