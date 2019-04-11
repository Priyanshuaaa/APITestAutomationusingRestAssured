package com.qa.JIRAAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddingComments {
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
	
@Test(description="Test to create a new issue and adding comments to a newly added issue in JIRA",enabled=false)
	
	public void AddComments() throws IOException
	{
	RestAssured.baseURI=prop.getProperty("JIRALoginURL");
	Response response=given().
	header("Content-Type","application/json").body(Payload.AddComment("Adding comments through RESTAPI Automation")).header("cookie","JSESSIONID="+RawtoJSONorXML.GetSessionID())
	.when().post(APITestUtil.AddCommentsJSON(RawtoJSONorXML.getissueID())).
	then().assertThat().statusCode(201).and().header("X-AUSERNAME","Priyanshua").and().header("Content-Type","application/json;charset=UTF-8").and().body(containsString("id")).and().body(containsString("self"))
	.body("body",equalTo("Adding comments through RESTAPI Automation")).and().body("author.name",equalTo("Priyanshua")).and().body("author.emailAddress",equalTo("Priyanshua@chetu.com"))
    .extract().response();
	JsonPath js=RawtoJSONorXML.rawtoJSON(response);
	System.out.println(response);
	String issueid=js.get("id");
	System.out.println(issueid);
	}



@Test(description="Test to get a comment of ")

public void AddComment() throws IOException
{
RestAssured.baseURI=prop.getProperty("JIRALoginURL");
Response response=given().
header("Content-Type","application/json").body(Payload.AddComment("Adding comments through RESTAPI Automation for Issue#10010")).header("cookie","JSESSIONID="+RawtoJSONorXML.GetSessionID()).pathParams("issueId","10010")
.when().post("/rest/api/2/issue/{issueId}/comment").
then().assertThat().statusCode(201).and().header("X-AUSERNAME","Priyanshua").and().header("Content-Type","application/json;charset=UTF-8").and().body(containsString("id")).and().body(containsString("self"))
.body("body",equalTo("Adding comments through RESTAPI Automation for Issue#10010")).and().body("author.name",equalTo("Priyanshua")).and().body("author.emailAddress",equalTo("Priyanshua@chetu.com"))
.extract().response();
JsonPath js=RawtoJSONorXML.rawtoJSON(response);
System.out.println(response);
String commentid=js.get("id");
System.out.println("Comment id for issue#10010 is"+commentid);
}

@Test(description="Test to get the parameters of an issue after passingf the valid issue id")

public void GetIssueDetails() throws IOException
{
RestAssured.baseURI=prop.getProperty("JIRALoginURL");
Response response=given().
header("Content-Type","application/json").body(Payload.AddComment("Adding comments through RESTAPI Automation for Issue#10010")).header("cookie","JSESSIONID="+RawtoJSONorXML.GetSessionID()).pathParams("issueId","10010")
.when().post("/rest/api/2/issue/{issueId}/comment").
then().assertThat().statusCode(201).and().header("X-AUSERNAME","Priyanshua").and().header("Content-Type","application/json;charset=UTF-8").and().body(containsString("id")).and().body(containsString("self"))
.body("body",equalTo("Adding comments through RESTAPI Automation for Issue#10010")).and().body("author.name",equalTo("Priyanshua")).and().body("author.emailAddress",equalTo("Priyanshua@chetu.com"))
.extract().response();
JsonPath js=RawtoJSONorXML.rawtoJSON(response);
System.out.println(response);
String commentid=js.get("id");
System.out.println("Comment id for issue#10010 is"+commentid);
}




}
