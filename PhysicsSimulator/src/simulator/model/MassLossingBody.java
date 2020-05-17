package simulator.model;

import simulator.misc.Vector;

public class MassLossingBody extends Body{

	protected double _lossFactor;
	protected double _lossFrequency;
	protected double _c;
	
	//Metodos de las diapositivas
	
	public MassLossingBody(String id, Vector v, Vector a, Vector p, Double m,
			double _lossFactor, double _lossFrequency) 
	{
		super(id, v, a, p, m);
		this._lossFactor = _lossFactor;
		this._lossFrequency = _lossFrequency;
		_c = 0.0;
	}
	
	protected void move(double t)  //Sobrescribe el move del super
	{
		super.move(t);
		
		_c = _c + t;
		
		if (_c >= _lossFrequency) 
		{
			_m = _m*(1 - _lossFactor);
			_c = 0.0;
		}	
	}
}
