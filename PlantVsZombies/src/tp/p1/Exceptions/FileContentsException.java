package tp.p1.Exceptions;

public class FileContentsException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FileContentsException() {
		super(); //"Load failed: invalid file contents"
	}
	
	public FileContentsException(String msg) {
		super(msg);
	}
	

	public FileContentsException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public FileContentsException(Throwable cause) {
		super(cause);
	}
	
	public FileContentsException(String message, Throwable cause, boolean enableSuppression, 
		boolean writableStackTrace)
	{
			super(message, cause,enableSuppression, writableStackTrace);
	}

}
