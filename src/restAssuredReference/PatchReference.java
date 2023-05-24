package restAssuredReference;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.testng.Assert;

public class PatchReference{

	public static void main(String[] args) {

		//Step 1 : declare base url
		RestAssured.baseURI = "https://reqres.in/";

		// Step 2: Parse the request body
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\"\r\n"
				+ "}";
		// Set the expected results
		JsonPath jsp = new JsonPath(requestBody);
		String req_name = jsp.getString("name");
		System.out.println(requestBody);

		int statusCode = given().header("Content-Type","application/json").body(requestBody)
				.when().patch("/api/users/2")
				.then().extract().statusCode();

		String responseBody = given().header("Content-Type","application/json").body(requestBody)
				.when().patch("/api/users/2")
				.then().extract().response().asString();

		// Step 3: Parse the response body
		JsonPath jsp1 = new JsonPath(responseBody);
		String res_name = jsp1.getString("name");
		System.out.println(responseBody);

		String res_date = jsp1.getString("updatedAt");
		String Date = new String(res_date);
		String result = new String(Date);
		System.out.println(res_date);
		System.out.println(Date.substring(0,10));

		// Step 4: Validate response body
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(res_name ,req_name);
		Assert.assertEquals(Date,result);
	}

}