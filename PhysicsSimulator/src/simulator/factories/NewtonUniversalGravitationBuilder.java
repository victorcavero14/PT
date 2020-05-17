package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws>{

	
	public NewtonUniversalGravitationBuilder()
	{
		_type = "nlug";
		_desc="Newton's law of universal gravitation";
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) throws JSONException{
		
		return new NewtonUniversalGravitation();
	}
	
}
