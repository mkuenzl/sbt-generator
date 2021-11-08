package sbt.automization.core.parser;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExcelParser extends ATableParser
{
	private String sheetName;
	
	public ExcelParser()
	{
		this.sheetName = "";
	}
	
	public void setSheetName(String sheetName)
	{
		this.sheetName = sheetName;
	}
	
	@Override
	public List<Map<String, String>> parse(File file)
	{
		List<Map<String, String>> parsedTable = new ArrayList<>();
		
		try (FileInputStream fis = new FileInputStream(file))
		{
			//obtaining bytes from the file
			XSSFWorkbook wb = new XSSFWorkbook(fis);    //creating Workbook instance that refers to .xlsx file
			XSSFSheet sheet = wb.getSheet(sheetName);   //creating a Sheet object to retrieve object
			
			if (sheet == null) return parsedTable;
			
			List<XSSFTable> sheetTables = sheet.getTables();
			for (XSSFTable table : sheetTables)
			{
				int headerRow = table.getStartCellReference().getRow();
				int startRow = headerRow + 1;
				int endRow = table.getEndCellReference().getRow();
				int startColumn = table.getStartCellReference().getCol();
				int endColumn = table.getEndCellReference().getCol();
				
				//System.out.println("headerRow = " + headerRow);
				//System.out.println("startRow = " + startRow);
				//System.out.println("endRow = " + endRow);
				//System.out.println("startColumn = " + startColumn);
				//System.out.println("endColumn = " + endColumn);
				
				ArrayList<String> header = new ArrayList<>();
				for (int i = startColumn; i <= endColumn; i++)
				{
					XSSFCell cell = sheet.getRow(headerRow).getCell(i);
					if (cell != null)
					{
						DataFormatter formatter = new DataFormatter();
						String cellValue = formatter.formatCellValue(cell);
						header.add(cellValue);
					}
				}
				
				for (int i = startRow; i <= endRow; i++)
				{
					Map<String, String> line = new HashMap<>();
					XSSFRow row = sheet.getRow(i);
					if (row != null)
					{
						for (int j = startColumn; j <= endColumn; j++)
						{
							String cellValue = "";
							XSSFCell cell = row.getCell(j);
							if (cell != null)
							{
								DataFormatter formatter = new DataFormatter();
								cellValue = formatter.formatCellValue(cell);
							}
							line.put(header.get(j), cellValue);
							
							//System.out.print(header.get(j) + " " + cellValue + "\t");
						}
						parsedTable.add(line);
						//System.out.println();
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return parsedTable;
	}
	
	@Override
	public List<String> retrieveHeader(File file)
	{
		List<String> parsedHeader = new ArrayList<>();
		
		try (FileInputStream fis = new FileInputStream(file))
		{
			//obtaining bytes from the file
			XSSFWorkbook wb = new XSSFWorkbook(fis);    //creating Workbook instance that refers to .xlsx file
			XSSFSheet sheet = wb.getSheet(sheetName);   //creating a Sheet object to retrieve object
			
			if (sheet == null) return parsedHeader;
			
			List<XSSFTable> sheetTables = sheet.getTables();
			for (XSSFTable table : sheetTables)
			{
				int headerRow = table.getStartCellReference().getRow();
				int startColumn = table.getStartCellReference().getCol();
				int endColumn = table.getEndCellReference().getCol();
				
				for (int i = startColumn; i <= endColumn; i++)
				{
					XSSFCell cell = sheet.getRow(headerRow).getCell(i);
					if (cell != null)
					{
						DataFormatter formatter = new DataFormatter();
						String cellValue = formatter.formatCellValue(cell);
						parsedHeader.add(cellValue);
					}
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return parsedHeader;
	}
}
