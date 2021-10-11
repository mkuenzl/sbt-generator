package sbt.automization.core.util.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import sbt.automization.view.popup.ErrorPopup;
import sbt.automization.view.popup.InfoPopup;

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
public final class CsvParser
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
	public static List<String> parseHeader(File csv)
	{
		List<String> headers = null;

		try (FileInputStream fileInputStream = new FileInputStream(csv) ;
		     BOMInputStream bomInputStream = new BOMInputStream(fileInputStream) ;
		     InputStreamReader inputStreamReader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8))
		{
			CSVParser csvParser = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().withIgnoreEmptyLines(true).parse(inputStreamReader);

			headers = csvParser.getHeaderNames();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return headers;
	}

	public List<Map<String, String>> parse(File file) throws Exception {
		return parse(file, ',');
	}

	/**
	 * Method uses the Apache CSV parser to read the csv and provide each line as record.
	 * The excel export of .csv files in UTF-8 is always with BOM information (first bytes of each line)
	 * therefore the BOMInputStream from Apache IO is used.
	 *
	 * @return a list of maps, each map contains the information of one csv row
	 * @throws Exception if anything happens while reading or exporting the csv
	 */
	public List<Map<String, String>> parse(File file, Character delimiter) throws Exception {
		List<Map<String, String>> csvData = new ArrayList<>();

		try (FileInputStream fileInputStream = new FileInputStream(file) ;
		     BOMInputStream bomInputStream = new BOMInputStream(fileInputStream) ;
		     InputStreamReader inputStreamReader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8))
		{
			CSVParser csvParser = CSVFormat.EXCEL.withDelimiter(delimiter)
					.withFirstRecordAsHeader()
					.withIgnoreEmptyLines(true)
					.parse(inputStreamReader);

			List<String> providedHeader = csvParser.getHeaderNames();
			showCSVInfoMessage(providedHeader);

			for (CSVRecord record : csvParser)
			{    // each record represents a line from the csv
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

	public boolean checkValidityOfHeader(List<String> input)
	{
		String probeID = "PROBE.ID";
		String sampleID = "SAMPLE.ID";

		return input.contains(probeID) && input.contains(sampleID);
	}

	public void showCSVInfoMessage(List<String> input) throws Exception
	{
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Die CSV enthält:");

		if (checkValidityOfHeader(input))
		{
			messageBuilder.append("\n- Erkundungsstellen");
			messageBuilder.append("\n- Proben");
		} else
		{
			//Implement Exception
			ErrorPopup.showMessage("Der Datensatz enthält keine Erkundungsstellen oder Proben! \nÜberprüfen sie den Datensatz.");
			throw new Exception("No valid database, either probes or samples are missing.");
		}

		String chemistryID = "PARAMETER.CHEMISTRY.ID";
		if (input.contains(chemistryID))
		{
			messageBuilder.append("\n- Chemie Parameter");
		}
		String rukID = "PARAMETER.RUK.ID";
		if (input.contains(rukID))
		{
			messageBuilder.append("\n- RuK Parameter");
		}
		String lpID = "PARAMETER.LP.ID";
		if (input.contains(lpID))
		{
			messageBuilder.append("\n- Lp Parameter");
		}

		InfoPopup.showMessage(messageBuilder.toString());
	}
}
