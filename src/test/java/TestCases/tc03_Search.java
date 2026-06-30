package TestCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import PageObjects.SearchPage;
import TestBase.BaseClass;
import Utilities.DataProviders;

public class tc03_Search extends BaseClass{
	
	SearchPage sp;
	int r=1;

	@Test(dataProvider = "SearchData", dataProviderClass = DataProviders.class)
	public void SearchOptions(String data)
	{
		sp = new SearchPage(driver);
		sp.SearchBar(data);
		sp.Search();
		sp.Img();
		String dol = sp.dollar();
		try 
		{
			rd.setCellData("Search", r, 1, dol);
			r++;
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@AfterMethod
	public void cls()
	{
		sp.clearsearch();
	}
}
