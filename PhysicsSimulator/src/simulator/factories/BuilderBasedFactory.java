package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{

	private List<Builder<T>> _builders;
	private List<JSONObject> _factoryElements;
	
	public BuilderBasedFactory(List<Builder<T>> builders)
	{
		_builders = new ArrayList<Builder<T>>(builders);
		_factoryElements = IniElements(builders);
		
	}
	
	private List<JSONObject> IniElements(List<Builder<T>> builders)
	{
		List<JSONObject> lista_elem= new ArrayList<JSONObject>();
		
		for (Builder<T> b : _builders)
		{
			lista_elem.add(b.getBuilderInfo());
		}
		
		return lista_elem;
	}
	
	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		boolean encontrado = false;
		T resul = null;
		int i = 0;
		
		while (!encontrado && i < _builders.size())
		{
			try
			{
				if (this._builders.get(i).createInstance(info)!= null)
				{
					encontrado = true;
					resul = _builders.get(i).createInstance(info);
				}
				i++;
			}
			catch (IllegalArgumentException e)
			{
				throw e;
			}
		}
		
		if(!encontrado) throw new IllegalArgumentException();
	
		return resul;
	}

	@Override
	public List<JSONObject> getInfo() {
		List<JSONObject> aux = new ArrayList<JSONObject>(this._factoryElements);
		return aux;
	}

}
