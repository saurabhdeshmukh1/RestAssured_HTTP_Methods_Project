package restAssuredReference;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.testng.Assert;

public class PutReference {

	public static void main(String[] args) {

		//declare base url 
		String baseurl = "https://reqres.in/";
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";    
		// Step 2: Fetch request body parameter values

		JsonPath jsp = new JsonPath(requestBody);
		String req_name = jsp.getString("name");
		String req_job = jsp.getString("job");

		// Step 3:Declare BaseURL
		RestAssured.baseURI = baseurl; 

		// Step 4:Configure Request Body

		System.out.println(requestBody);
		int statusCode = given().header("Content-Type","application/json").body(requestBody)
				.when().put("/api/users/2")
				.then().extract().statusCode();

		String responseBody = given().header("Content-Type","application/json").body(requestBody)
				.when().put("/api/users/2")
				.then().extract().response().asString();
		System.out.println(responseBody);

		JsonPath jsp1 = new JsonPath(responseBody);
		String res_name = jsp1.getString("name");
		String res_job = jsp1.getString("job");
		String res_updatedAt = jsp1.getString("updatedAt");

		String actualdate = res_updatedAt.substring(0,10);
		String currentdate = LocalDate.now().toString(); 

		System.out.println("Status code is: "+ statusCode + " OK");  
		System.out.println("name : "+res_name);
		System.out.println("job : "+res_job);
		System.out.println("updatedAt : "+res_updatedAt);

		//Validate response body
		Assert.assertEquals(res_name , req_name);
		Assert.assertEquals(res_job , req_job);
		Assert.assertEquals(actualdate , currentdate);

	}

}