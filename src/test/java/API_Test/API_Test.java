package API_Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Test {
	
	
	 @Test(priority=1)
	 void getPetDetails()
	 {
	  //Specify base URI
	  RestAssured.baseURI="https://petstore.swagger.io/v2/pet/findByStatus?status=available";
	  
	  //Request object
	  RequestSpecification httpRequest=RestAssured.given();
	  
	  //Response object
	  Response response=httpRequest.request(Method.GET);
	  
	  //print response in console window
	  
	  String responseBody=response.getBody().asString();
	  System.out.println("Response Body is:" +responseBody);
	  
	  //status code validation
	  int statusCode=response.getStatusCode();
	  System.out.println("Status code is: "+statusCode);
	  Assert.assertEquals(statusCode, 200);
	  
	  //status line verification
	  String statusLine=response.getStatusLine();
	  System.out.println("Status line is:"+statusLine);
	  Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	  
	 }
	 
	 @Test(priority=2)
	 void postNewPet()
	 {
	  RestAssured.baseURI="https://petstore.swagger.io/v2";
	  
	  //Request object
	  RequestSpecification httpRequest=RestAssured.given();
	  
	   //Array
	  
	  	int id=981;
	    JSONArray tags_array = new JSONArray();
	    JSONObject tags = new JSONObject();
	    tags.put("id", id);
	    tags.put("name", "string");
	    tags_array.add(tags);
	    JSONArray photo_urls = new JSONArray();
		photo_urls.add("string");
		
		//Request paylaod sending along with post request
	  	JSONObject category = new JSONObject();
	  	category.put("id", id);
	  	category.put("name", "new");
  	
	  	JSONObject json = new JSONObject();
	  	json.put("id", id);
	  	json.put("name", "newwdog"+id);
	  	json.put("status", "available");
	  	
	  	json.put("category", category);
	  	json.put("tags", tags_array);
	  	json.put("photoUrls", photo_urls);
	  	
	  
	  httpRequest.header("Content-Type","application/json");
	  
	  httpRequest.body(json.toJSONString()); // attach above data to the request
	  
	  //Response object
	  Response response=httpRequest.request(Method.POST,"/pet");
	    
	  
	  //print response in console window
	  
	  String responseBody=response.getBody().asString();
	  System.out.println("Response Body is:" +responseBody);
	  
	  //status code validation
	  int statusCode=response.getStatusCode();
	  System.out.println("Status code is: "+statusCode);
	  Assert.assertEquals(statusCode, 200);
	  
	  
	 }
	 
	 
	 @Test(priority=3)
	 void UpdatePet()
	 {
	  RestAssured.baseURI="https://petstore.swagger.io/v2";
	  
	  //Request object
	  RequestSpecification httpRequest=RestAssured.given();
	  
	   //Array
	  
	  
	  	int id=981;
	    JSONArray tags_array = new JSONArray();
	    JSONObject tags = new JSONObject();
	    tags.put("id", id);
	    tags.put("name", "string");
	    tags_array.add(tags);
	    JSONArray photo_urls = new JSONArray();
		photo_urls.add("string");
		
		//Request paylaod sending along with post request
	  	JSONObject category = new JSONObject();
	  	category.put("id", id);
	  	category.put("name", "new");
	
	  	JSONObject json = new JSONObject();
	  	json.put("id", id);
	  	json.put("name", "newwdog"+id);
	  	json.put("status", "sold");
	  	
	  	json.put("category", category);
	  	json.put("tags", tags_array);
	  	json.put("photoUrls", photo_urls);
	  	
	  
	  httpRequest.header("Content-Type","application/json");
	  
	  httpRequest.body(json.toJSONString()); // attach above data to the request
	  
	  //Response object
	  Response response=httpRequest.request(Method.PUT,"/pet");
	    
	  
	  //print response in console window
	  
	  String responseBody=response.getBody().asString();
	  System.out.println("Response Body is:" +responseBody);
	  
	  //status code validation
	  int statusCode=response.getStatusCode();
	  System.out.println("Status code is: "+statusCode);
	  Assert.assertEquals(statusCode, 200);
	  
	  
	 }
	 
	 @Test(priority=4)
	 void DeletePet()
	 {
	  int num =981;
	  RestAssured.baseURI="https://petstore.swagger.io/v2/pet";
	  
	  //Request object
	  RequestSpecification httpRequest=RestAssured.given();
	  
	  
	  Response response=httpRequest.request(Method.DELETE,"/"+num);
	    
	  
	  //print response in console window
	  
	  String responseBody=response.getBody().asString();
	  System.out.println("Response Body is:" +responseBody);
	  
	  //status code validation
	  int statusCode=response.getStatusCode();
	  System.out.println("Status code is: "+statusCode);
	  Assert.assertEquals(statusCode, 200);
	  
	  
	 }
	  
	
}
