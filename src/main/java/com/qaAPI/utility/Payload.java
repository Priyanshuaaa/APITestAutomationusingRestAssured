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
	
	public static String AddBookPOSTJSON(String book,String isbn,String aisle,String author)
	{
		String addbook="{\r\n\r\n\"name\":\""+book+"\",\r\n\"isbn\":\""+isbn+"\",\r\n\"aisle\":\""+aisle+"\",\r\n\"author\":\""+author+"\"\r\n}\r\n\r\n";
		return addbook;
	}
	
	public static String AddBookPOST(String isbn,String aisle)
	{
		String addbook="{\r\n\r\n\"name\":\"Automation testing using TestNG\",\r\n\"isbn\":\""+isbn+"\",\r\n\"aisle\":\""+aisle+"\",\r\n\"author\":\"Opera King\"\r\n}\r\n\r\n";
		return addbook;
	}
	
	public static String loginJIRAPOST(String username,String password)
	{
		String login="{\r\n    \"username\": \""+username+"\",\r\n    \"password\": \""+password+"\"\r\n}";
		return login;
	}
	
	public static String CreateissuePOST()
	{
		String createissue="{\r\n    \"fields\": {\r\n       \"project\":\r\n       {\r\n          \"key\": \"DC\"\r\n       },\r\n       \"summary\": \"Debit Card information is not getting saved properly into database\",\r\n       \"description\": \"After submitting the POST request from API,the debit card information is not getting saved into the database \",\r\n       \"issuetype\": {\r\n          \"name\": \"Bug\"\r\n       }\r\n   }\r\n}";
		return createissue;
	}

	public static String CreateissueinvalidPOST()
	{
		String invalibody="{\r\n    \"fields\": {\r\n       \"projects\":\r\n       {\r\n          \"keys\": \"DC\"\r\n       },\r\n       \"summaryss\": \"Debit Card information is not getting saved properly into database\",\r\n       \"description\": \"After submitting the POST request from API,the debit card information is not getting saved into the database \",\r\n       \"issuetype\": {\r\n          \"name\": \"Bug\"\r\n       }\r\n   }\r\n}";
		return invalibody;
	}
	
}



