package com.restapi.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restapi.utilities.RestUtilites;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;


public class RestAssuredHTTPMethods 
{
	
	@Test(priority=1)
	public void test_GetAll()
	{
		given()
		
		.when()
		        .get("http://localhost:8080/app/videogames")
		      
		.then()
		        .statusCode(200)
		        .header("content-type","application/xml");
		
	}
	
	//@Test(priority=2,dataProviderClass=RestUtilites.class,dataProvider="Data")
	public void test_Add(String id, String name, String releaseDate, String reviewScore)
	{
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("name", name);
		map.put("releaseDate", releaseDate);
		map.put("reviewScore", reviewScore);
		
		Response res=
			
			given()
			         .contentType("application/json")
			         .body(map)
			         
			.when()
			          .post("http://localhost:8080/app/videogames")
			          
			.then()
			
			          .statusCode(200)
			          .log().body()
			          .extract().response();
   String jsonstring = res.asString();
   Assert.assertEquals(jsonstring.contains("Record Added Successfully"), true);			
	}
	
	
	//@Test(priority=3,dataProviderClass=RestUtilites.class,dataProvider="Data")
	public void test_Get(String id, String name, String releaseDate, String reviewScore)
	{
		given()
		        .pathParam("id", id)
		.when()
		         .get("http://localhost:8080/app/videogames/{id}")
		         
		 .then()
		          .statusCode(200)
		         
		          .log().body()
	              .body("videoGame.id", equalTo(id))
		          .body("videoGame.name", equalTo(name));
			          
	}
	
	//@Test(priority=4,dataProviderClass=RestUtilites.class,dataProvider="Data")
	public void test_Update(String id, String name, String releaseDate, String reviewScore)
	{
      HashMap map = new HashMap();
        map.put("id", id);
		map.put("name", name);
		map.put("releaseDate", releaseDate);
		map.put("reviewScore", reviewScore);
		
      
      given()
               .contentType("application/json")
               .body(map)
      
      .when()
               .put("http://localhost:8080/app/videogames")
               
      .then()
               .statusCode(200)
               .log().body()
               .header("content-type","application/xml")
               .body("videoGame.id", equalTo("11"))
		       .body("videoGame.name", equalTo("Hide-seek"));
               
	}
       
	//@Test(priority=5,dataProviderClass=RestUtilites.class,dataProvider="Data")
	public void test_Delete(String id, String name, String releaseDate, String reviewScore)
	{
		Response res=
		given()
		        .pathParam("id", id)
		.when()
		        .delete("http://localhost:8080/app/videogames/{id}")
		      
		.then()
		        .statusCode(200)
		        .log().body()
		        .extract().response();
		String jsonString = res.asString();
		//String contentlength = res.header("content-length");
		Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
		//Assert.assertTrue(Integer.parseInt(contentlength)<=1000);
		
	}
			
}
