package com.qa.TwitterAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.APITest.com.qa.APITest.RawtoJSONorXML;
import com.qaAPI.utility.APITestUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetTweets {
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
	
@Test(description="Test to Get the Tweets from the Timeline using Twitter API",enabled=false)
	
	public void Getweets()
	{
	RestAssured.baseURI=prop.getProperty("TwitterGETweetsURL");
	Response response=given().auth().oauth(prop.getProperty("APIkey"),prop.getProperty("APIsecretkey"),prop.getProperty("Accesstoken"),prop.getProperty("Accesstokensecret"))
	.when().get(APITestUtil.GetTweets()).
	then().assertThat().statusCode(200).and().header("content-type","application/json;charset=utf-8").and().header("server","tsa_a")
	.and().header("status","200 OK").and()//.body("[0].id_str",equalTo("1117848743364063200"))
	.extract().response();
	JsonPath js=RawtoJSONorXML.rawtoJSON(response);
	System.out.println(response);
//	//int jsonsize=js.get("array.size()");
//	List<String> lst=js.get("[0].id_str");
//	System.out.println(lst);
//use .tostring() method to convert the long into string and then print it out to a console.
    	
    }       



@Test(description="Test to Get the Tweets from the Timeline using Twitter API using the count query parameter")

public void GetweetsbyCount()
{
RestAssured.baseURI=prop.getProperty("TwitterGETweetsURL");
Response response=given().auth().oauth(prop.getProperty("APIkey"),prop.getProperty("APIsecretkey"),prop.getProperty("Accesstoken"),prop.getProperty("Accesstokensecret")).queryParam("count","5")
.when().get(APITestUtil.GetTweets()).
then().assertThat().statusCode(200).and().header("content-type","application/json;charset=utf-8").and().header("server","tsa_a")
.and().header("status","200 OK").and()//.body("[0].id_str",equalTo("1117848743364063200"))
.extract().response();
JsonPath js=RawtoJSONorXML.rawtoJSON(response);
int arraysize=js.get("array.size()");
if(arraysize==5)
{
	System.out.println("5 Tweets are getting displayed-Test Case Passed");
}
else
{
	System.out.println("5 Tweets are not getting displayed-Test Case Failed");
}
}       
}
