package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;



public class Updateaproduct {

	
	@Test
	public void updateoneproduct() {
		// https://techfios.com/api-prod/api/product/create.php
	/*{
	    "name" : "Amazing NPillow 2.0",
	    "price" : "199",
	    "description" : "The best pillow for amazing programmers.",
	    "category_id" : 2
	}*/	
		
	HashMap<String,String> payload=new HashMap<String,String>();
	payload.put("id","2333");
	payload.put("name","MD's Pillow6.0");
	payload.put("price","499");	
	payload.put("description","amazing pillow for QAS");
	payload.put("category","2");
		
	HashMap<String,String> headersMap=new HashMap<String,String>();
	payload.put("Content-Type","application/json;charset=UTF-8");
	payload.put("Authorization","BearerToken=123456789");
	
		// http method GET
		Response response=
		given()
		  .baseUri("https://techfios.com/api-prod/api/product")
		  //.header("Content-Type","application/json;charset=UTF-8")
		.headers(payload)  
		  //.header("Authorization","BearerToken=123456789")
		  .auth().preemptive().basic("username","password")
		  .body(payload).
		//  .body(new File(".\\src\\main\\java\\pauloaddata\\createpayload.json")).
	    when()
	      .put("/update.php").
	    then()
	     .extract().response();
	int statusCode=response.getStatusCode();
	   System.out.println("Status Code:" + statusCode);
	   Assert.assertEquals(statusCode, 200);
	   
	/*	long responseTime=response.getTime();
		System.out.println("ResponseTime:"+responseTime);
		if(responseTime<3000) {
			System.out.println("Response Time is within Range");
		}else {
			System.out.println("Response Time is not within Range");
			Assert.fail("Failed");
		}*/
	String responseBody=	response.getBody().asString();
	System.out.println("ResponseBody:"+responseBody);
	
	
	JsonPath jp=new JsonPath(responseBody);
	String Productmessage=jp.get("message");
	System.out.println("Product Message:"+Productmessage);
	//Assert.assertEquals(Productmessage,"Product was updated");
	
//	String ProductName=jp.get("name");
//	System.out.println("Product Name:"+ProductName);
//	Assert.assertEquals(ProductName,"SP Pillow 2.0");
	
	//String responseHeaders=response.getHeaders().toString();
	//System.out.println("ResponseHeaders:"+responseHeaders);
	
	String responseheader=response.getHeader("Content-Type");
	System.out.println("ResponseHeader:"+responseheader);
	
	}	
}
