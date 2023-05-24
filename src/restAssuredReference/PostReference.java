package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.LocalDateTime;



public class PostReference {

	public static void main(String[] args) {

		//Step 1 : Declare Base Url

		String baseurl = "https://reqres.in/";
		RestAssured.baseURI = baseurl;

		//Step 2.1 : save request body in local variable
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";

		//Step 2.2 : extract request body parameters
		JsonPath jspRequest = new JsonPath(requestBody);
		String req_name = jspRequest.getString("name");
		String req_job = jspRequest.getString("job");

		//Step 2.3 : set expected date 
		String expDate = LocalDateTime.now().toString().substring(0,10);

		//Step 3.1 : configure request body
		int statusCode = given().header("Content-Type" , "application/json").body(requestBody).
				when().post("/api/users").then().extract().statusCode();

		String responseBody = given().header("Content-Type","application/json").body(requestBody).
				when().post("/api/users").then().extract().response().asString();
		System.out.println(responseBody);

		//Step 3.2 : parse the response body 
		JsonPath jsp = new JsonPath(responseBody);
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String res_createdAt = jsp.getString("createdAt");
		String actDate = res_createdAt.substring(0,10);

		//Step 3.3 : validate the response body parameters
		Assert.assertEquals(statusCode,201);
		Assert.assertEquals(res_name,req_name);
		Assert.assertEquals(res_job,req_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(actDate,expDate);

	}
}