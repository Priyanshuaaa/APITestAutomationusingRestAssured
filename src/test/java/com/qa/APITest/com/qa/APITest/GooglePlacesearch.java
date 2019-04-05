package com.qa.APITest.com.qa.APITest;
import org.testng.annotations.Test;

import com.qaAPI.utility.APITestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GooglePlacesearch{

	

	
	@Test
	
	public void googleplacesearchAPIscenario1test()
	{
		//BaseURl or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		given().param("key","AIzaSyCtkrL1SYlW4Ld7HGyVXfijVAb5Hy5kwkY").param("location","-33.8670522,151.1957362").param("radius","500")
		.when().get(APITestUtil.PlacesearchresourceURL())
		.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		.body("results[0].name",equalTo("Sydney")).and().
	     body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
	     header("Server","scaffolding on HTTPServer2");
		//Status code of the response
		//Content type 
		//Body
		//Header responses		
}
	
	@Test
	
	public void googleplacesearchAPIscenario2test()
	{
		//BaseURl or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		given().param("key","AIzaSyCtkrL1SYlW4Ld7HGyVXfijVAb5Hy5kwkY").param("location","-33.8670522,151.1957362").param("radius","500")
		.when().get(APITestUtil.PlacesearchresourceURL())
		.then().assertThat().body("next_page_token",equalTo("CqQCFQEAAMjCLDRVBkbhlwXnHnbEOqne379BFHifQBlv3ORSY9xpT6_vEUUYPaHh-XMCf9PRS60_hOQeUbtXwvYsvbBoL3t5ZPYg9zGm24g8nuAY2BMahrVVNmuHW6A_WqToGeIUbOBQcGzBmVroHT_fObDhUrVMU9U1O9ium1oFp-OcvBMiMsnkaVkFI1FmCT5xM23nXC5jn2bMyZFQ0lEv5gE52wZlqVG-8EujjB1WD2A-RwhebkyMR1WiQzAZr96JZwl0Y-mS4pNtUJdNu11BGfepFeytC221HwPwaxwkKTRClgbuKMcJcQs4RZJWKfjjyA-FqOvmhTihXWY5jfupUfT-qKY1DKrRQ3t1lvaLqN9sxWtK1qxjttt0tVEITSiqScKG0hIQq5-MAGCpGfOFXEDKjc4uPhoU0qr271PD4PIyvXxS7dT0Moms2RI"));			
}
	
	@Test
	
	public void googleplacesearchAPIscenario3test()
	{
		//BaseURl or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		given().param("key","AIzaSyCtkrL1SYlW4Ld7HGyVXfijVAb5Hy5kwkY").param("location","-33.8670522,151.1957362").param("radius","500")
		.when().get(APITestUtil.PlacesearchresourceURL())
		.then().assertThat().body("results[0].geometry.location.lng",equalTo("151.2092955")).and().body("results[0].geometry.location.lat",equalTo("-33.8688197"));		
}

}