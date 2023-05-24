package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class SoapReferenceNumberToDollars {

	public static void main(String[] args) {

		//Step 1 : declare base uri and request body variables 
		String BaseURI = "https://www.dataaccess.com/";
		String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <dNum>13</dNum>\r\n"
				+ "    </NumberToDollars>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";

		//Step 2 : Fetch request params
		XmlPath xmlrequest = new XmlPath(requestBody);
		String req_param = xmlrequest.getString("dNum");
		System.out.println(req_param);

		//Step 3 : Configure the API and fetch the response body
		RestAssured.baseURI = BaseURI;
		String responseBody = given().header("Content-Type" , "text/xml; charset=utf-8").body(requestBody).
				when().post("/webservicesserver/NumberConversion.wso").
				then().extract().response().getBody().asString();
		System.out.println(responseBody);

		//Step 4 : parse the responsebody and fetch the response params
		XmlPath xml_res = new XmlPath(responseBody);
		String Result = xml_res.getString("NumberToDollarsResult");
		System.out.println(Result);

		//Step 5 : validate response body params 
		Assert.assertEquals(Result, "thirteen dollars");

	}

}
