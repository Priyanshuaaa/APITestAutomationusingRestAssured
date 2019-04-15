package com.qaAPI.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.qaAPI.utility.APITestUtil;


import com.qaAPI.utility.APITestUtil;


public class APITestUtil {
	
	public static String AddplaceresourceURLJSON()
	{
		String addplaceresource="/maps/api/place/add/json";
		return addplaceresource;
	}
	
	public static String DeleteplaceresourceURL()
	{
		String deleteplaceresource="/maps/api/place/delete/json";
		return deleteplaceresource;
	}
	
	public static String PlacesearchresourceURL()
	{
		String placesearch="/maps/api/place/nearbysearch/json";
		return placesearch;
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		
		  return new String(Files.readAllBytes(Paths.get(path)));
		  }
	
	public static String AddplaceresourceURLXML()
	{
		String addplace="/maps/api/place/add/xml";
		return addplace;
	}	
	
	public static String DeleteplaceresourceURLXML()
	{
		String deleteplace="/maps/api/place/add/xml";
		return deleteplace;
	}	
	
	public static String AddBookresourceURLJSON()
	{
		String addbook="/Library/Addbook.php";
		return addbook;
	}
	
	public static String DeleteBookresourceURLJSON()
	{
		String deletebook="/Library/DeleteBook.php";
		return deletebook;
	}
	
	public static String JIRAloginresourceJSON()
	{
		String loginURL="/rest/auth/1/session";
		return loginURL;
	}
	
	public static String CreateissueresourceJSON()
	{
		String loginURL="/rest/api/2/issue";
		return loginURL;
	}
	
	public static String AddCommentsJSON(String issueid)
	{
		String comments="rest/api/2/issue/"+issueid+"/comment";
		return comments;
	}

	public static String GetTweets()
	{
		String getweet="/1.1/statuses/home_timeline.json";
		return getweet;
	}
	
	
}
