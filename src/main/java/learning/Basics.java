package learning;

import io.restassured.RestAssured;        // copy this and repaste it below it
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;   // To remove errors from the code, add 'static' and '.*'    import STATIC io.restassured.RestAssured.*; 
import static org.hamcrest.Matchers.*;     //This removes errors as result of using "equalTo".

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;


//REQUIREMENT:
//(1)Add place->  (2)Update Place with New Address -> (3)Get Place to validate if New address is present in response (END TO END AUTOMATION)
		
//   (1)Add place   .  validate if Add Place API is working as expected (i.e Post 

	//  CONTRACT
	//	This API Will add new place into Server
		 
	//	Complete URL : https://rahulshettyacademy.com/maps/api/place/add/json?key= qaclick123
		//				<-BASE URL-				  -><-- RESOURCE--  	----><-QUERY PARAM-->
		 
	//	Base URL:  https://rahulshettyacademy.com  
		//Resource: /maps/api/place/add/json
	//	Query Parameters: key =qaclick123
	// Http Method: POST
		
		
	//SAMPLE BODY        // When you paste this body inside  .body""  in .given(), it changes to {\r\n.....
	//	{
	//	  "location": {
	//		    "lat": -38.383494,
	//		    "lng": 33.427362
	//		  },
	//		  "accuracy": 50,
	//		  "name": "Frontline house",
	//	  "phone_number": "(+91) 983 893 3937",
	//	  "address": "29, side layout, cohen 09",
	//		  "types": [
	//    "shoe park",
	//		    "shop"
	//		  ],
	//	  "website": "http://google.com",
	//	  "language": "French-IN"
	//		}
		 
	//	SAMPLE RESPONSE
		 
	//		{
	//	    "status": "OK",
	//		    "place_id": "928b51f64aed18713b0d164d9be8d67f",
	//		    "scope": "APP",
	//		    "reference": "736f3c9bec384af62a184a1936d42bb0736f3c9bec384af62a184a1936d42bb0",
	//		    "id": "736f3c9bec384af62a184a1936d42bb0"
	//	 	}
		 
		 
	
		
		
public class Basics {
	
	public static void main (String [] args) {
		
		
		//REST ASSSURED WORKS ON GIVEN, WHEN, THEN
		//given - all input details should be part of given().e.g (Query Parameter, Authorization, Header, Body )    pls refer to postman to see these inputs
		//when - Submit the API -resource  ( /maps/api/place/add/json), http method(post)     i.e .when(). will have value of post("resource")
		//Then - To validate the test, you can use output like  sample response  like Body, Cookies, Headers 
		
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";     
	String response= 	given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json") //pls refer to postman to see the header named "Content-Type" 
		.body(Payload.AddPlace())         //.body(Payload.AddPlace())  means that this class Addplace is getting the sample body from the class payload. Addplace is a method in the class 'payload
		.when().post("/maps/api/place/add/json")          //select .post (URI)   
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))       //select .status code(response), select .body (list argument)..then() is used to validate the sample response  like status, body. select string matcher
				.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();   // .extract().response().asString(); converts and extract the response body to a string format
		 
		//import static org.hamcrest.Matchers.*;  is used to remove errors caused by "equalTo"      
		//IF YOU LOOK AT YOUR POSTMAN, YOU WILL SEE THAT INPUTS ARE :         //PLS REFER TO THE POSTAN IMAGE. POSTMAN IS USED ALONG WITH RESTASSURED
		//Params 	Authorization	Headers  Body Pre-request Script Tests Settings          <--  all or some of these inputs can be used in given().
		//TO VALIDATE THE TESTS, YOU WILL USE OUTPUTS LIKE:
		//	Body 	Cookies	    Headers		Test Results         (You can use more than 1 variable in each output to do assertions e.g Body has status": "OK", "scope": "APP", which were both used.
	//'String response'= This ensures that response body is stored in a string response variable
		
	System.out.println(response);  // This prints out the response body (result) in a string straight line

	
	
	//(2)Update Place with New Address    (The "place_id" (that you were provided which is unique to your place) from the 'Add' response is needed to update the address
	  
	//(a)Firstly extract the 'placeId' which you need to update the address ( the reason for this is bcos u need the place_id to be stored as string and replace the place_id variable"6f384e6b416b582a715fc3f711707e5f\" in the body (so as not to hardcode the value) to be used in 'PUT'
	
		JsonPath js=new JsonPath(response);   // for parsing Json
		String placeId= js.getString("place_id");  // it means get the string from the path 'placeId' and print it out in the output
		//js.getString(response)
		System.out.println(placeId);
		
		
//(b) 2ndly,you can now update place with new address after extracting the placeId
		
		//CONTRACT
		//Base URL :  https://rahulshettyacademy.com	 	
		//	Resource: /maps/api/place/update/json
		//	Query Parameters: key, place_id //(place_id  value comes from Add place response)
		//	Http Method: PUT
		//	Note: Key value is hardcoded and it is always qaclick123
			 
			 
		//	Sample Request:
			{
				//	"place_id":"6f384e6b416b582a715fc3f711707e5f",
				// 	"address":"70 summer walk, Africa",
				// "key":"qaclick123"
				//	}
			 
			 
				//Sample Response for the Provided Place_Id
				//{
			 
				//    	"location":{
				// 
				//   	"lat" : -38.383494,
			 
				//   	"lng" : 33.427362
			 
				//  	},
			 
				// 	"accuracy":50,
				
				//	"name":"Frontline house",
			    	
				//	"phone_number":"(+91) 983 893 3937",
			 
				//	"address" : "29, side layout, cohen 09",
			 
				//	"types": ["shoe park","shop"],
			 
				//	"website" : "http://google.com",
			 
				//	"language" : "French-IN"
			 
			}
			 
			 String newAddress= "70 summer walk, Africa";
			                    
			 
			RestAssured.baseURI= "https://rahulshettyacademy.com";     
			given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json").body("{\r\n"
					+ "\"place_id\":\""+placeId+"\",\r\n"    //replace the place_id variable"6f384e6b416b582a715fc3f711707e5f\" with the variable name this way ""+placeId+"
					+ "\"address\":\""+newAddress+"\",\r\n"    //replace the address"70 summer walk, Africa"  with newAddress  so as not to hardcode the value
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"
					+ "")
			.when().put("/maps/api/place/update/json")        //select 'Response' option
			.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated")) ;    //  pls refer to postman to see these outputs (updatePlace)
			 
			 
	
//(3)Get Place to validate if New address is present in response.
		//Note: 'Get' does not require any 'body' hence there is no point adding any 'header' (which is a queryPam in 'given' ) to describe a non existent body 
			
			
			//CONTRACT
			//Base URL:  https://rahulshettyacademy.com	  	
			//	Resource: /maps/api/place/get/json
			//	Parameters: key,  place_id  //( place_id  value comes from Add place response)
			//	Http request: GET
			//	Note: Key value is hardcoded and it is always qaclick123

		
			String getPlaceResponse= given().log().all().queryParam("key", "qaclick123")    //String getPlaceResponse- This is to store the response as a string format
					.queryParam("place_id",placeId)    //2 diff query param is used as a result of requirement
					.when().get("maps/api/place/get/json")
					.then().assertThat().log().all().statusCode(200).extract().response().asString();   //cucumber assertion
			//Apart from the status code verification, you also need to verify that the  actual address matches with the expected address. 
			//JAVA LOGIC
				//JsonPath js1= new JsonPath(getPlaceResponse);
				//String actualAddress =js1.getString("address");  
				//System.out.println(actualAddress);
				//Assert.assertEquals(actualAddress, newAddress);   //select Boolean Actual, expected)   TestNG Assertion

//	OR   IF YOU PREFER TO RESTRICT EVERYTHING TO REST ASSURED CODE LOGIC WITHOUT JAVA LOGIC THEN  USE THIS INSTEAD OF THE PATH STARTING WITH JsonPath js1= new JsonPath(getPlaceResponse);
				//CREATE A CLASS   - REUSABLE METHODS WHICH HELPS YOU OPTMISE YOUR TEST (By firstly converting the string to Json format)
				
				JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse); //calling the rawToJson method from the ReusableMethods Class so as to convert from string to Json format
					String actualAddress =js1.getString("address");  
					System.out.println(actualAddress);
					Assert.assertEquals(actualAddress, newAddress);   //select Boolean Actual, expected) TestNG Assertion
		
				
		
		}
}