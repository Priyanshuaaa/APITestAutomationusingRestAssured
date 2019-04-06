package com.qaAPI.utility;


public class Payload{
	
	public static String AddPlacePOST()
	{
		String addplace="{"+
			    "\"location\": {"+
			    "\"lat\": -33.8669710,"+
			    "\"lng\": 151.1958750"+
			    "},"+
			    "\"accuracy\": 50,"+
			    "\"name\": \"Google Shoes!\","+
			    "\"phone_number\": \"(02) 9374 4000\","+
			    "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
			    "\"types\": [\"shoe_store\"],"+
			    "\"website\": \"http://www.google.com.au/\","+
			    "\"language\": \"en-AU\""+
			    "}";
		return addplace;
	}
	
	public static String AddBookPOST()
	{
		String addbook="{\r\n\r\n\"name\":\"Learn Appium Automation with Javadfddfddfdfdfdfdfdfd\",\r\n\"isbn\":\"bcdgfggg\",\r\n\"aisle\":\"22743434\",\r\n\"author\":\"Johneeefdfeffdfffdfdfdfdfoe\"\r\n}\r\n\r\n";
		return addbook;
	}
}




