package learning;

import org.testng.annotations.DataProvider;
//DYNAMIC JSON PAYLOADS WITH WITH MULTIPLE DATA USING TESTNG DATA PROVIDER FOR PARAMETERIZATION
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class DynamicJson2 {

	
	@Test(dataProvider="BooksData")   // The name here should be same as that of Dataprovider

	public void addBook(String isbn,String aisle)



	{
	
	RestAssured.baseURI="http://216.10.245.166";

	String resp=given().header("Content-Type","application/json").body(Payload.Addbook(isbn,aisle)).  //The data is gotten from the dataprovider
	when().post("/Library/Addbook.php").

	then().assertThat().statusCode(200).extract().response().asString();    //converting to string

	JsonPath js= ReusableMethods.rawToJson(resp); //calling the rawToJson method from the ReusableMethods Class so as to convert from string to Json format

	   String id=js.get("ID");   //extracting the ID which is part of the output

	   System.out.println(id);
	   
	}
	  // @DataProvider(name="BooksData")

	 /*  public Object[][]  getData(){

	   //array=collection of elements

	   //multidimensional array= collection of arrays

	   return new Object[][] {‌{"ojfwty","9363"},{"cwetee","4253"}, {"okmfet","533"} };   // The Dataprovider supplies the test (.body(Payload.Addbook(isbn,aisle))with these parameters
*/
}
//}