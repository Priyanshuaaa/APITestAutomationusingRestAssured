package com.qa.JIRAAPITests;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.APITest.com.qa.APITest.RawtoJSONorXML;
import com.qaAPI.utility.APITestUtil;
import com.qaAPI.utility.Payload;
import static org.hamcrest.Matchers.containsString;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Authenticationtests {
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
	
@Test(description="Test to Authenticate to JIRA API with valid login credentials")
	
	public void AuthenticationJIRA() throws IOException
	{
	RestAssured.baseURI=prop.getProperty("JIRALoginURL");
	Response response=given().
	header("Content-Type","application/json").body(APITestUtil.GenerateStringFromResource("C:\\Users\\priyanshua\\Desktop\\com.qa.APITest\\src\\main\\java\\Configurations\\JIRAutentication.json")).
	when().post(APITestUtil.JIRAloginresourceJSON()).
	then().assertThat().statusCode(200).and().header("Content-Type","application/json;charset=UTF-8")
	.extract().response();
	JsonPath js=RawtoJSONorXML.rawtoJSON(response);
	System.out.println(response);
	Cookies cookies=response.getDetailedCookies();
	String sessionid=js.get("session.value");
	System.out.println(sessionid);
	System.out.println(cookies);
	}


@DataProvider
public Object[][] getJIRAlogindata()
{
	return new Object[][]{{"ffvfvv","vjfvfjvnfvn"},{"frfhrfvfvfvf","Chetu@123"},{"vfvhfuvhfuvfuvfuvfuvfvhfuvhfuvufvhufvufiviuvv112121222212","fhdvfvnfv8434343355353gfgbfhnfhbbbfg4855554"},{"<HTML>","<div>"},{"",""}};
}



@Test(description="Test to Authenticate to JIRA API with invalid login credentials",dataProvider="getJIRAlogindata")

public void JIRAuthenticationinvalidata(String username,String password) throws IOException
{
RestAssured.baseURI=prop.getProperty("JIRALoginURL");
Response response=given().
header("Content-Type","application/json").body(Payload.loginJIRAPOST(username,password)).
when().post(APITestUtil.JIRAloginresourceJSON()).
then().assertThat().statusCode(401).and().header("Content-Type","application/json;charset=UTF-8").and().header("x-seraph-loginreason","AUTHENTICATED_FAILED").and().body(containsString("errorMessages"))
.extract().response();
//JsonPath js=RawtoJSONorXML.rawtoJSON(response);
System.out.println(response);
Map<String, String> sessionid=response.getCookies();
//String token=response.getCookie("atlassian.xsrf.token");
System.out.println(sessionid);
//System.out.println(token);
//JSESSIONID
}


}
