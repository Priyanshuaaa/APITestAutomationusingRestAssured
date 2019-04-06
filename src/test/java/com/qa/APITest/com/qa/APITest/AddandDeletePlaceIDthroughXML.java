package com.qa.APITest.com.qa.APITest;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qaAPI.utility.APITestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class AddandDeletePlaceIDthroughXML {
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
		
		public void addanddeleteplacethroughXMLtest() throws IOException
		{
		RestAssured.baseURI=prop.getProperty("BaseURL");
		Response response=given().
	    queryParam("key",prop.getProperty("KEY")).
		body(APITestUtil.GenerateStringFromResource("C:\\Users\\priyanshua\\Desktop\\com.qa.APITest\\src\\main\\java\\Configurations\\AddPlace.xml")).
		when().
		post(APITestUtil.AddplaceresourceURLXML()).
		then().assertThat().statusCode(200).and().contentType(ContentType.XML)
		.extract().response();
		XmlPath resxml=RawtoJSONorXML.rawtoXML(response);
		String placeid=resxml.get("response.place_id");
		System.out.println(placeid);
		//Task2-Deleting the created Place ID.
//		given().queryParam("key",prop.getProperty("KEY")).body("root.").when().post(APITestUtil.DeleteplaceresourceURLXML()).
//		then().assertThat().statusCode(200).and().
//		body("status",equalTo("OK"));	
		
		}	
}



