package com.qa.APITest.com.qa.APITest;




import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import java.io.IOException;

import com.qaAPI.utility.APITestUtil;
import com.qaAPI.utility.Payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class RawtoJSONorXML{
	
	public static XmlPath rawtoXML(Response res)
	{
		String responsestring=res.asString();
		System.out.println(responsestring);
		XmlPath xs=new XmlPath(responsestring);
		return xs;
	}

	public static JsonPath rawtoJSON(Response res)
	{
	String responsestring=res.asString();
	System.out.println(responsestring);
	JsonPath js=new JsonPath(responsestring);
	return js;
   }
	
	
	public static String GetSessionID() throws IOException
	{
		RestAssured.baseURI="http://localhost:8080/";
		Response response=given().
		header("Content-Type","application/json").body(APITestUtil.GenerateStringFromResource("C:\\Users\\priyanshua\\Desktop\\com.qa.APITest\\src\\main\\java\\Configurations\\JIRAutentication.json")).
		when().post(APITestUtil.JIRAloginresourceJSON()).
		then().assertThat().statusCode(200).and().header("Content-Type","application/json;charset=UTF-8")
		.extract().response();
		JsonPath js=RawtoJSONorXML.rawtoJSON(response);
		String sessionid=js.get("session.value");
		System.out.println(sessionid);
		return sessionid;
	}
	
	public static String getissueID() throws IOException
	{
		RestAssured.baseURI="http://localhost:8080/";
		Response response=given().
		header("Content-Type","application/json").body(Payload.CreateissuePOST("DC","This is again new issue-10 for Debit Card Project","This is the description of new issue-10","Bug")).header("cookie","JSESSIONID="+GetSessionID())
		.when().post(APITestUtil.CreateissueresourceJSON()).
		then().assertThat().statusCode(201).and().header("X-AUSERNAME","Priyanshua").and().body(containsString("id"))
		.extract().response();
		JsonPath js=RawtoJSONorXML.rawtoJSON(response);
		String issueid=js.get("id");
		System.out.println(issueid);
		return issueid;
	}
}

