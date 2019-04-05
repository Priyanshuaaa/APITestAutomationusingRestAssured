package com.qa.APITest.com.qa.APITest;
import org.testng.annotations.Test;

import com.qaAPI.utility.APITestUtil;
import com.qaAPI.utility.Payload;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class AddplacethroughJSON {
@Test
	
	public void Addplace()
	{
	RestAssured.baseURI="http://216.10.245.166";
	given().
	
	queryParam("key","qaclick123").
	body(Payload.AddPlacePOST()).
	when().
	post(APITestUtil.AddplaceresourceURLJSON()).
	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	body("status",equalTo("OK"));
	
	//Grab a Place id from create place id response and place that place id into a delete Place ID Request
	// Create a place =response (place id)
	// delete Place = (Request - Place id)
   }
}
