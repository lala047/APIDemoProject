package learning;

//DYNAMIC JSON WITH FEW PARAMETERS BY SENDING THESE PARAMETERS FROM THIS TEST TO THE PAYLOAD 
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class DynamicJson {

	
	@Test
	public void addBook(){

	
	RestAssured.baseURI="http://216.10.245.166";

	String resp=given().header("Content-Type","application/json").body(Payload.Addbook("bcd","227")).  //"bcd","227" These 2 parameters(or any put here)  are sent to the payload in Payload in 'Addbook' method(Payload Class) to take the place of ""+isbn+" and ""+aisle+" and the test will now run preventing hardcoding	

	when().post("/Library/Addbook.php").

	then().assertThat().statusCode(200).extract().response().asString();    //converting to string

	JsonPath js= ReusableMethods.rawToJson(resp); //calling the rawToJson method from the ReusableMethods Class so as to convert from string to Json format

	   String id=js.get("ID");   //extracting the ID which is part of the output

	   System.out.println(id);

}
}