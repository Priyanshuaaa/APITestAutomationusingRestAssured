package com.qa.APITest.com.qa.APITest;




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
	
}

