package tp.p1.Exceptions;

public class CasillaOcupadaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CasillaOcupadaException() {
		super();
	}
	
	public CasillaOcupadaException(String msg) {
		super(msg);
	}
	

	public CasillaOcupadaException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public CasillaOcupadaException(Throwable cause) {
		super(cause);
	}
	
	public CasillaOcupadaException(String message, Throwable cause, boolean enableSuppression, 
		boolean writableStackTrace)
	{
			super(message, cause,enableSuppression, writableStackTrace);
	}
}
