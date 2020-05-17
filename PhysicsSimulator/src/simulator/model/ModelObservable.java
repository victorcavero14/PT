package simulator.model;

public interface ModelObservable<T> {
	
	 public void addObservador(T o);
	 public void removeObservador(T o);
	 
	}
