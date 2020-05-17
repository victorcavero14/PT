package tp.p1.Exceptions;

public class CommandExecuteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommandExecuteException() 
	{
		super(); // "Unable to execute"
	}
	
	public CommandExecuteException(String msg) {
		super(msg);
	}
	

	public CommandExecuteException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public CommandExecuteException(Throwable cause) {
		super(cause);
	}
	
	public CommandExecuteException(String message, Throwable cause, boolean enableSuppression, 
		boolean writableStackTrace)
	{
			super(message, cause,enableSuppression, writableStackTrace);
	}

}
