package com.qa.JIRAAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.APITest.com.qa.APITest.RawtoJSONorXML;
import com.qaAPI.utility.APITestUtil;
import com.qaAPI.utility.Payload;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIssue {
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
@Test(description="Test to Create an issue in JIRA by using the cookie authentication",enabled=false)
	
	public void CreateissueinJIRA() throws IOException
	{
	RestAssured.baseURI=prop.getProperty("JIRALoginURL");
	Response response=given().
	header("Content-Type","application/json").body(Payload.CreateissuePOST()).header("cookie","JSESSIONID=B10EF49FF4635AD059771AA12500EB91")
	.when().post(APITestUtil.CreateissueresourceJSON()).
	then().assertThat().statusCode(201).and().header("X-AUSERNAME","Priyanshua").and().body(containsString("id"))
	.extract().response();
	JsonPath js=RawtoJSONorXML.rawtoJSON(response);
	System.out.println(response);
	Cookies cookies=response.getDetailedCookies();
	String issueid=js.get("id");
	String issueURl=js.get("self");
	System.out.println(issueid);
	System.out.println(issueURl);
	System.out.println(cookies);
	}


@Test(description="Test after passing the invalid session id and valid request body for Create Issue request",enabled=false)

public void InvalidSessionid() throws IOException
{
RestAssured.baseURI=prop.getProperty("JIRALoginURL");
given().
header("Content-Type","application/json").body(Payload.CreateissuePOST()).header("cookie","JSESSIONID=fdhdhfhffA12500EB91")
.when().post(APITestUtil.CreateissueresourceJSON()).
then().assertThat().statusCode(401);
}

@Test(description="Test after not passing the session id into an header and valid request body for Create Issue request")

public void BlankSessionid() throws IOException
{
RestAssured.baseURI=prop.getProperty("JIRALoginURL");
given().
header("Content-Type","application/json").body(Payload.CreateissuePOST())
.when().post(APITestUtil.CreateissueresourceJSON()).
then().assertThat().statusCode(400);
}



@Test(description="Test after passing the invalid objects into the body and valid session id")

public void Invalidbody() throws IOException
{
RestAssured.baseURI=prop.getProperty("JIRALoginURL");
given().
header("Content-Type","application/json").body(Payload.CreateissueinvalidPOST()).header("cookie","JSESSIONID=B10EF49FF4635AD059771AA12500EB91")
.when().post(APITestUtil.CreateissueresourceJSON()).
then().assertThat().statusCode(400);
}




}
