package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;



public class ReadAProduct {
Response response;
SoftAssert softassert=new SoftAssert();

	public void Readaproduct(String productId) {
		 response=
				given()
				  .baseUri("https://techfios.com/api-prod/api/product")
				  .header("Content-Type","application/json;")
				 // .header("Authorization","BearerToken=123456789")
				  .auth().preemptive().basic("username","password")
				  .queryParam("id", productId).
			    when()
			      .get("/read_one.php").
			    then()
			     .extract().response();
	}
	
	@Test
	public void testAPI() {
		
	Readaproduct("2333");
		String responseBody=response.getBody().asString();
		
		JsonPath jp=new JsonPath(responseBody);
		String productId=jp.getString("id");
		System.out.println("productId:"+ productId);
		//Assert.assertEquals(productId, "2333");
		softassert.assertEquals(productId,"2334","Actual and expected are not matching");
		
		String productname=jp.getString("name");
		System.out.println("productname:"+productname);
		//Assert.assertEquals(productname,"MD's Pillow5.1");
		softassert.assertEquals(productname,"MD's Pillow5.1");
		
	//softassert available only in testng
	//	jnuit in cucumber
		
		softassert.assertAll();
	}
}
	
	
/*	@Test
	public void Readaproduct() {
		
		// https://techfios.com/api-prod/api/product/read_one.php?id=2291
		// https://techfios.com/api-prod/api/product/read.php
		// http method GET
		Response response=
		given()
		  .baseUri("https://techfios.com/api-prod/api/product")
		  .header("Content-Type","application/json;")
		  .header("Authorization","BearerToken=123456789")
		  .auth().preemptive().basic("username","password")
		  .queryParam("id", "2291").
	    when()
	      .get("/read_one.php").
	    then()
	     .extract().response();
	int statusCode=response.getStatusCode();
	   System.out.println("Status Code:" + statusCode);
	   Assert.assertEquals(statusCode, 200);
	   
		long responseTime=response.getTime();
		System.out.println("ResponseTime:"+responseTime);
		if(responseTime<3000) {
			System.out.println("Response Time is within Range");
		}else {
			System.out.println("Response Time is not within Range");
			Assert.fail("Failed");
		}
	String responseBody=	response.getBody().asString();
	System.out.println("ResponseBody:"+responseBody);
	
	
	JsonPath jp=new JsonPath(responseBody);
	String ProductId=jp.get("id");
	System.out.println("Product Id:"+ProductId);
	Assert.assertEquals(ProductId,"2291");
	
	String ProductName=jp.get("name");
	System.out.println("Product Name:"+ProductName);
	Assert.assertEquals(ProductName,"SP Pillow 2.0");
	
	String responseHeaders=response.getHeaders().toString();
	System.out.println("ResponseHeaders:"+responseHeaders);
	
	String responseheader=response.getHeader("Content-Type");
	System.out.println("ResponseHeader:"+responseheader);
	
	}	
}*/
