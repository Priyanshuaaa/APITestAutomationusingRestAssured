package com.qaAPI.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.qaAPI.utility.APITestUtil;


import com.qaAPI.utility.APITestUtil;


public class APITestUtil {
	
	static Xls_reader reader;

	
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
	
//	public static HashMap<String,Object> AddBook()
//	{
////		HashMap<String, Object>  map = new HashMap<>();
////		map.put("name",);
////		map.put("isbn",);
////		map.put("aisle",);
////		map.put("author",);
////		return 
//		
//	}
	
	
	
	public static ArrayList<Object[]> getdatafromexcel()
	{
		ArrayList<Object[]> mydata=new ArrayList<Object[]>();
		try
		{
			reader=new Xls_reader("C:\\Users\\priyanshua\\Desktop\\com.qa.APITest\\src\\main\\java\\Configurations\\AddBookData.xlsx");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	for(int rowNum=2;rowNum<=reader.getRowCount("AddBookData");rowNum++)
	{	  
		  String name=reader.getCellData("AddBookData","Name",rowNum);
		  System.out.println(name);
		  String isbn=reader.getCellData("AddBookData","ISBN",rowNum);
		  System.out.println(isbn);
		  String aisle_num=reader.getCellData("AddBookData","Aisle",rowNum);
		  System.out.println(aisle_num);
		  String authorname=reader.getCellData("AddBookData","Author",rowNum);
		  System.out.println(authorname);
		  //Object array to store cell values
		  Object ob[]= {name,isbn,aisle_num,authorname};
		  mydata.add(ob);
    }
	return mydata;
	}
	
	
	
	
}
