package com.qa.APITest.com.qa.APITest;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qaAPI.utility.APITestUtil;
import com.qaAPI.utility.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;


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

	
@Test(enabled=false)
	
	public void AddbookthroughJSONtest()
	{
	RestAssured.baseURI=prop.getProperty("BaseURL");
	Response response=given().
	header("Content-Type","application/json").body(Payload.AddBookPOSTJSON("API testing using rest assured", "9958948650","65123456","Justin Timbelake")).
	when().post("Library/Addbook.php").
	//post(APITestUtil.AddBookresourceURLJSON()).
	then().assertThat().statusCode(200).
	extract().response();
	JsonPath js=RawtoJSONorXML.rawtoJSON(response);
	System.out.println(response);
	String id=js.get("ID");
	System.out.println(id);
	}


@DataProvider

public Object[][] getbooksdata()
{
	return new Object[][]{{"SeleniumwithJava","ABC56789111","995889123","JaneKetherine"},{"SeleniumwithPython","EFG88999345","995996789","HellYo"}};
}


@Test(dataProvider="getbooksdata",enabled=false)
public void Addbookusingdataprovider(String bookname,String isbn,String aisle,String author)
{
RestAssured.baseURI=prop.getProperty("BaseURL");
Response response=given().
header("Content-Type","application/json").body(Payload.AddBookPOSTJSON(bookname,isbn,aisle,author)).
when().post("Library/Addbook.php").
//post(APITestUtil.AddBookresourceURLJSON()).
then().assertThat().statusCode(200).
extract().response();
JsonPath js=RawtoJSONorXML.rawtoJSON(response);
System.out.println(response);
String id=js.get("ID");
System.out.println(id);
Response responses=given().
header("Content-Type","application/json").body("{"+
		  "\"ID\": \""+id+"\""+
		"}").
				when().
				post(APITestUtil.DeleteBookresourceURLJSON()).
				then().assertThat().statusCode(200).and().and().
				body("msg",equalTo("book is successfully deleted")).extract().response();
                JsonPath js1=RawtoJSONorXML.rawtoJSON(responses);
                System.out.println(responses);
                String message=js1.get("msg");
                System.out.println(message);
}



@Test(enabled=false)
public void staticpayloadJSONtest() throws IOException
{
RestAssured.baseURI=prop.getProperty("BaseURL");
Response response=given().
header("Content-Type","application/json").body(APITestUtil.GenerateStringFromResource("C:\\Users\\priyanshua\\Desktop\\com.qa.APITest\\src\\main\\java\\Configurations\\Addbook.json")).
when().post("Library/Addbook.php").
//post(APITestUtil.AddBookresourceURLJSON()).
then().assertThat().statusCode(200).
extract().response();
JsonPath js=RawtoJSONorXML.rawtoJSON(response);
System.out.println(response);
String id=js.get("ID");
System.out.println(id);
}



@Test
public void AddbookthroughExcel()
{
	RestAssured.baseURI=prop.getProperty("BaseURL");
	Response response=given().
	header("Content-Type","application/json").body().
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