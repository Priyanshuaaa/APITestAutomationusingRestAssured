package com.qa.APITest.com.qa.APITest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qaAPI.utility.APITestUtil;
import com.qaAPI.utility.Payload;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddandDeletePlaceIDthroughJSON {
public Properties prop;

@BeforeMethod
public void getdata()
{
	prop=new Properties();
	try {
		File file = new File("C:\\Users\\priyanshua\\Desktop\\com.qa.APITest\\src\\main\\java\\Configurations\\config.properties");
		FileInputStream filein = new FileInputStream(file);
		prop.load(filein);
		filein.close();
	} 
	catch(FileNotFoundException e)
	{
		e.printStackTrace();

	}
	catch (IOException e) {
		Reporter.log(e.getLocalizedMessage());
}	
}

@Test
	
	public void addanddeleteplacethroughJSONtest()
	{
	RestAssured.baseURI=prop.getProperty("BaseURL");
	Response response=given().
	queryParam("key",prop.getProperty("KEY")).
	body(Payload.AddPlacePOST()).
	when().
	post(APITestUtil.AddplaceresourceURLJSON()).
	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	body("status",equalTo("OK")).extract().response();
	
	//Grab a Place id from create place id response and place that place id into a delete Place ID Request
	// Create a place =response (place id)
	// delete Place = (Request - Place id)
	//When you get the response,it should be in RAW format,so we need to convert the response into JSOPN format first in order to pass the place 
	//ID to another request.
	//1.Convert the RAW response into String by using asString() method.
	//2.After that you need to use JsonPath class in order to convert the response into JSON
	//3.Use get function to get the place id from a particular response and then use place id to delete the place id.
	String responsestring=response.asString();
	System.out.println(responsestring);
	
	//Task2-Grab the place ID from the response
	JsonPath js=new JsonPath(responsestring);
	String placeid=js.get("place_id");
	System.out.println(placeid);
	
	//Task3-Send the place id to the delete request
	given().
	queryParam("key",prop.getProperty("KEY")).body("{"+
			  "\"place_id\": \""+placeid+"\""+
			"}").
					when().
					post(APITestUtil.DeleteplaceresourceURL()).
					then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
					body("status",equalTo("OK"));	
   }

}
