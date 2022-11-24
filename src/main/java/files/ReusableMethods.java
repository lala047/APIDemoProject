package files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {

	// REUSABLE METHODS WHICH HELPS YOU OPTMISE YOUR TEST (By firstly converting the string to Json format
	public static JsonPath rawToJson(String response){
		JsonPath js1 =new JsonPath(response);
		return js1;
	}

}
