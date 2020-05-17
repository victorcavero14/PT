package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Builder<T extends Object> {

	protected String _type;
	protected String _desc;
	
	public T createInstance(JSONObject info) throws IllegalArgumentException
	{
		T b = null;
		if (_type != null && _type.equals(info.getString("type")))
		{
			try
			{
				b = createTheInstance(info.getJSONObject("data"));
			}
			catch(JSONException j)
			{
			throw new IllegalArgumentException();
			}
			
		}
		
		return b;
	}
	
	
	protected abstract T createTheInstance (JSONObject jsonObject) throws JSONException;
	
	public JSONObject getBuilderInfo()
	{
		JSONObject plantilla = new JSONObject();
		plantilla.put("type", _type);
		JSONObject data = createData();
		plantilla.put("data", data);
		plantilla.put("desc", _desc);
		return plantilla;
		
	}
	
	protected JSONObject createData()
	{
		return new JSONObject();
	}
	
	protected double[] jsonArrayTodoubleArray(JSONArray ja) {
		 double[] da = new double[ja.length()];
		 for (int i = 0; i < da.length; i++)
		 da[i] = ja.getDouble(i);
		 return da;
		}
}
