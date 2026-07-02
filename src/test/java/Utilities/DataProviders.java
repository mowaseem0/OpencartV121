package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "LoginData", indices = {15,20})
	public String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/testdata/Login_Data.xlsx";
		
		Excelutilily_ReadData xlutils = new Excelutilily_ReadData(path);
		
		int totalrows = xlutils.getRowCount("Register_Data");
		int totalcols = 2;
		
		String loginData[][] = new String[totalrows][totalcols];
		
		for(int i=1; i<=totalrows; i++)
		{
			for(int j=0; j<totalcols; j++)
			{
				loginData[i-1][j] = xlutils.getCellData("Register_Data", i, j);
			}
		}
		return loginData;
	}
	
	@DataProvider(name = "SearchData")
	public String[][] getSearchData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/testdata/Login_Data.xlsx";
		Excelutilily_ReadData xlutils = new Excelutilily_ReadData(path);
		
		int totalrows = xlutils.getRowCount("Search");
		String searchData[][] = new String[totalrows][1];
		
		for (int i=1; i<=totalrows; i++)
		{
			searchData[i-1][0] = xlutils.getCellData("Search", i, 0);
		}
		return searchData;
	}
}
