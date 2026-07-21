package Utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "TestDB")
	public static String[][] sqlData() throws SQLException
	{
		String dburl = "jdbc:mysql://localhost:3306/testdb";
		String username = "root";
		String password = "root";
		
		Connection con = DriverManager.getConnection(dburl, username, password);
		Statement smt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs = smt.executeQuery("select email,password from users");
		
		ResultSetMetaData md = rs.getMetaData();
		int colcount = md.getColumnCount();
		
		rs.last();						//Moving cursor to the last row
		int rowcount = rs.getRow();		//Counting the Rows
		rs.beforeFirst();				//Moving cursor back to the first row
		
		String[][] data = new String[rowcount][colcount];	//Creating a new Array with row (rowcount) and column [2]
		int i=0;
		while(rs.next())
		{
			for(int j=1; j<=colcount; j++)
			{
				data[i][j-1] = rs.getString(j);
			}
			i++;
		}
		rs.close();
		smt.close();
		con.close();
		return data;
	}
	
	
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
