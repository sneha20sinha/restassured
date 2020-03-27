package com.restapi.utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class RestUtilites 
{
	
	@DataProvider(name="Data")
	public Object[][] getData(Method m) throws IOException
	{
		String path = ".\\API_ TestData1.xlsx";
		String sheetName = m.getName();
		int rowcount = XLUtils.getRowCount(path, sheetName);
		int colcount = XLUtils.getCellCount(path, sheetName, 1);
		Object apidata[][] = new Object[rowcount][colcount];
		for(int i=1; i<=rowcount; i++) {
			for(int j=0; j<colcount;j++)
			{
				apidata[i-1][j]=XLUtils.getCellData(path, sheetName, i, j);
			}
		}
		return apidata;
		
	}

}
