package sbt.automization.core.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class for reading and transforming a csv into a map of data
 */
public final class CsvParser extends ATableParser
{
	public CsvParser()
	{
	}
	
	/**
	 * Method for testing purposes
	 *
	 * @param csv a file of a csv
	 * @return a list of headers as Strings
	 */
	public List<String> retrieveHeader(File csv)
	{
		List<String> headers = null;
		
		try (FileInputStream fileInputStream = new FileInputStream(csv);
		     BOMInputStream bomInputStream = new BOMInputStream(fileInputStream);
		     InputStreamReader inputStreamReader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8))
		{
			CSVParser csvParser =
					CSVFormat.EXCEL.withDelimiter(',').withFirstRecordAsHeader().withIgnoreEmptyLines(true).parse(inputStreamReader);
			
			headers = csvParser.getHeaderNames();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return headers;
	}
	
	@Override
	public List<Map<String, String>> parse(File file)
	{
		return parse(file, ';');
	}
	
	/**
	 * Method uses the Apache CSV parser to read the csv and provide each line as record.
	 * The excel export of .csv files in UTF-8 is always with BOM information (first bytes of each line)
	 * therefore the BOMInputStream from Apache IO is used.
	 *
	 * @return a list of maps, each map contains the information of one csv row
	 * @throws Exception if anything happens while reading or exporting the csv
	 */
	public List<Map<String, String>> parse(File file, Character delimiter)
	{
		List<Map<String, String>> parsedTable = new ArrayList<>();
		
		if (!file.exists())
		{
			return parsedTable;
		}
		
		try (FileInputStream fileInputStream = new FileInputStream(file);
		     BOMInputStream bomInputStream = new BOMInputStream(fileInputStream);
		     InputStreamReader inputStreamReader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8))
		{
			CSVParser csvParser = CSVFormat.EXCEL.withDelimiter(delimiter)
					.withFirstRecordAsHeader()
					.withIgnoreEmptyLines(true)
					.parse(inputStreamReader);
			
			//List<String> providedHeader = csvParser.getHeaderNames();
			//showCSVInfoMessage(providedHeader);
			
			for (CSVRecord record : csvParser)
			{    // each record represents a line from the csv
				if (!"".equals(record.get(0)))
				{ // prevent wrong excel formatting of .csv files to crash the program
					Map<String, String> map = record.toMap();
					parsedTable.add(map);
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return parsedTable;
	}
}
