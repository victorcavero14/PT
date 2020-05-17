package tp.p1.Exceptions;


public class UnknowLevelException extends Exception
{
private static final long serialVersionUID = 1L;
	
	public UnknowLevelException() {
		super(); // "Usage: plantsVsZombies <" + Level.all("|") + "> [seed]. Level must be one of: " + Level.all(",")
	}
	
	public  UnknowLevelException(String msg) {
		super(msg);
	}
	

	public  UnknowLevelException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public  UnknowLevelException(Throwable cause) {
		super(cause);
	}
	
	public  UnknowLevelException(String message, Throwable cause, boolean enableSuppression, 
		boolean writableStackTrace)
	{
			super(message, cause,enableSuppression, writableStackTrace);
	}
}
