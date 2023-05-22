package learning;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	
		
//YOU CAN COPY THE PAYLOAD AND PASTE IN 'www.jsoneditoronline. org'. THIS WILL HELP YOU TO ANALYZE THE PAYLOAD AND SEE THE NESTED JSONS PROPERLY  

		//(1) Print No of courses returned by API
	

	public static void main(String[] args) {
	JsonPath js= new JsonPath(Payload.CoursePrice());    // This directly focus on the method (courseprice) in the class(payload)
		
int count=js.getInt("courses.size()");	    //use obj.getInt bcos the course size is an integer
System.out.println(count);


// (2) Print Purchase Amount

//Object		{2}
//-  Courses		[3]    <- array 	
//dashboard		{2}	       (both purchaseAmount & website are under this dashboard in the nested json, hence the {2}
//purchaseAmount	:	910	    
//website	:	rahulshettyacademy.com


int totalAmount= js.getInt("dashboard.purchaseAmount");  //use obj.getInt. (purchaseAmount is under this dashboard in the nested json
System.out.println(totalAmount);

	
	
//	(3) Print Title of the first course


String titleFirstCourse=js.get("courses[0].title");    //use obj.get which will get string by default
System.out.println(titleFirstCourse);




//(4) Print All course titles and their respective Prices

	for(int i=0;i<count;i++){
	String courseTitles=js.get("courses["+i+"].title");   //same as js.get("courses[0].title" but [0] is replaced by the variable ["+i+"] because we are getting titles, not a single r particular title
	System.out.println(js.get("courses["+i+"].price").toString());  //price is an integer hence needed to be converted to string
	System.out.println(courseTitles);
	}
	// (5) Print no of copies sold by RPA Course
	//System.out.println("Print no of copies sold by RPA Course");
	 
	 for(int i=0;i<count;i++) {
	 
		  String curseTitles=js.get("courses["+i+"].title");    //use obj.get which will get string by default
		  if(curseTitles.equalsIgnoreCase("RPA")) {
			  int copies=js.get("courses["+i+"].copies");
			  System.out.println(copies);
			  break;    //If the For Loop achieves or matches the condition it is looking for, it should break.
			  
			  
			  
			//(6) VERIFY IF SUM OF ALL COURSE PRICE MATCHES WITH PURCHASE AMOUNT
			  //Pls refer to the sumvalidation class.
	}
	}
	}
	
	
}