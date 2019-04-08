package com.qaAPI.utility;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	
	
}
