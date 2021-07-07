package sbt.automization.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import sbt.automization.gui.ErrorPopup;

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
public class Parser
{
	final File csv;

	public Parser(File csv)
	{
		this.csv = csv;
	}

	/**
	 * Method uses the Apache CSV parser to read the csv and provide each line as record.
	 * The excel export of .csv files in UTF-8 is always with BOM information (first bytes of each line)
	 * therefore the BOMInputStream from Apache IO is used.
	 * @return a list of maps, each map contains the information of one csv row
	 * @throws Exception if anything happens while reading or exporting the csv
	 */
	public List<Map<String, String>> parse() throws Exception
	{
		List<Map<String, String>> csvData = new ArrayList<>();

		try (FileInputStream fileInputStream = new FileInputStream(csv);
			 BOMInputStream bomInputStream = new BOMInputStream(fileInputStream);
			 InputStreamReader inputStreamReader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8))
		{
			CSVParser csvParser = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().withIgnoreEmptyLines(true).parse(inputStreamReader);

			List<String> providedCsv = csvParser.getHeaderNames();
			if (! CsvHeader.compare(providedCsv))
			{
				ErrorPopup.showErrorMessage("Es wurde eine veraltete Version des Excel Templates verwendet.");
			}

			for (CSVRecord record : csvParser)
			{	// each record represents a line from the csv
				if (! "".equals(record.get(0)))
				{ // prevent wrong excel formatting of .csv files to crash the program
					Map<String, String> map = record.toMap();
					csvData.add(map);
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return csvData;
	}
}
