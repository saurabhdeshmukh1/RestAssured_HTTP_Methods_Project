package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class DeleteReference {

	public static void main(String[] args) {
		//step 1 : Declare Base Url
		   RestAssured.baseURI = "https://reqres.in/";
	        
	        int statusCode = given().header("Content-Type","application/json")
	        				.when().delete("/api/users/2")
	        				.then().extract().statusCode();
	        
	        String responseBody = given().header("Content-Type","application/json")
	        				.when().delete("/api/users/2")
	        				.then().extract().response().asString();
	        System.out.println(responseBody);
	        System.out.println("Status code is: "+ statusCode + " No Content");
	        
	        Assert.assertEquals(statusCode, 204);
	}
}