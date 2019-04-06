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
import com.qaAPI.utility.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddBookTest {
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
	header("Content-Type","application/json").body(Payload.AddBookPOST()).
	when().post("Library/Addbook.php").
	//post(APITestUtil.AddBookresourceURLJSON()).
	then().assertThat().statusCode(200).
	extract().response();
	JsonPath js=RawtoJSONorXML.rawtoJSON(response);
	System.out.println(response);
	String id=js.get("ID");
	System.out.println(id);
	}
}
