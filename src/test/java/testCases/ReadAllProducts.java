package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;



public class ReadAllProducts {

	@Test
	public void Readallaproduct() {
		// https://techfios.com/api-prod/api/product/read.php
		// http method GET
		Response response=
		given()
		  .baseUri("https://techfios.com/api-prod/api/product")
		  .header("Content-Type","application/json;charset=UTF-8").
	    when()
	      .get("/read.php").
	    then()
	     .extract().response();
	int statusCode=response.getStatusCode();
	   System.out.println("Status Code:" + statusCode);
	   Assert.assertEquals(statusCode, 200);
	   
		
	}
}
