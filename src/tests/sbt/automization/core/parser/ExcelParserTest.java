
package sbt.automization.core.parser;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelParserTest
{
	private final File input = new File("tests-resources/input/excel/excel-parse-test.xlsx");
	private final ExcelParser excelParser = new ExcelParser();
	
	@Test
	public void numberTableTest()
	{
		excelParser.setSheetName("Sheet1");
		List<Map<String, String>> parsedTable = excelParser.parse(input);
		
		Map<String, String> line = new HashMap<>(){{
			put("H1", "1");
			put("H2", "2");
			put("H3", "3");
			put("H4", "4");
			put("H5", "5");
		}};
		
		List<Map<String, String>> expectedTable = new ArrayList<>(){{
			add(line);
			add(line);
			add(line);
			add(line);
		}};
		
		Assert.assertArrayEquals(expectedTable.toArray(), parsedTable.toArray());
	}
	
	@Test
	public void multipleNumberTableTest()
	{
		excelParser.setSheetName("Sheet6");
		List<Map<String, String>> parsedTable = excelParser.parse(input);
		
		Map<String, String> lineFirst = new HashMap<>(){{
			put("H1", "1");
			put("H2", "2");
			put("H3", "3");
			put("H4", "4");
			put("H5", "5");
		}};
		
		Map<String, String> lineSecond = new HashMap<>(){{
			put("H1", "5");
			put("H2", "1");
			put("H3", "2");
			put("H4", "3");
			put("H5", "4");
		}};
		
		Map<String, String> lineThird = new HashMap<>(){{
			put("H1", "4");
			put("H2", "5");
			put("H3", "1");
			put("H4", "2");
			put("H5", "3");
		}};
		
		Map<String, String> lineFourth = new HashMap<>(){{
			put("H1", "3");
			put("H2", "4");
			put("H3", "5");
			put("H4", "1");
			put("H5", "2");
		}};
		
		List<Map<String, String>> expectedTable = new ArrayList<>(){{
			add(lineFirst);
			add(lineSecond);
			add(lineThird);
			add(lineFourth);
		}};
		
		Assert.assertArrayEquals(expectedTable.toArray(), parsedTable.toArray());
	}
	
	@Test
	public void emptyCellTableTest()
	{
		excelParser.setSheetName("Sheet5");
		List<Map<String, String>> parsedTable = excelParser.parse(input);
		
		Map<String, String> line = new HashMap<>(){{
			put("H1", "1");
			put("H2", "");
			put("H3", "3");
			put("H4", "4");
			put("H5", "5");
		}};
		
		List<Map<String, String>> expectedTable = new ArrayList<>(){{
			add(line);
			add(line);
			add(line);
			add(line);
		}};
		
		Assert.assertArrayEquals(expectedTable.toArray(), parsedTable.toArray());
	}
	
	@Test
	public void emptyTableTest()
	{
		excelParser.setSheetName("Sheet2");
		List<Map<String, String>> parsedTable = excelParser.parse(input);

		List<Map<String, String>> expectedTable = new ArrayList<>();
		
		Assert.assertArrayEquals(expectedTable.toArray(), parsedTable.toArray());
	}
	
	@Test
	public void noTableTest()
	{
		excelParser.setSheetName("Sheet4");
		List<Map<String, String>> parsedTable = excelParser.parse(input);
		
		List<Map<String, String>> expectedTable = new ArrayList<>();
		
		Assert.assertArrayEquals(expectedTable.toArray(), parsedTable.toArray());
	}
	
	@Test
	public void noSheetTest()
	{
		excelParser.setSheetName("Shit1");
		List<Map<String, String>> parsedTable = excelParser.parse(input);
		
		List<Map<String, String>> expectedTable = new ArrayList<>();
		
		Assert.assertArrayEquals(expectedTable.toArray(), parsedTable.toArray());
	}
	
	@Test
	public void characterTableTest()
	{
		excelParser.setSheetName("Sheet3");
		List<Map<String, String>> parsedTable = excelParser.parse(input);
		
		Map<String, String> line = new HashMap<>(){{
			put("H1", "a");
			put("H2", "b");
			put("H3", "c");
			put("H4", "d");
			put("H5", "e");
		}};
		
		List<Map<String, String>> expectedTable = new ArrayList<>(){{
			add(line);
			add(line);
			add(line);
			add(line);
		}};
		
		Assert.assertArrayEquals(expectedTable.toArray(), parsedTable.toArray());
	}
}
