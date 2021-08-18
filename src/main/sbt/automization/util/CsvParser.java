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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class for reading and transforming a csv into a map of data
 */
public final class CsvParser
{
	private final File csv;

	public CsvParser(File csv)
	{
		this.csv = csv;
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

	/**
	 * Method uses the Apache CSV parser to read the csv and provide each line as record.
	 * The excel export of .csv files in UTF-8 is always with BOM information (first bytes of each line)
	 * therefore the BOMInputStream from Apache IO is used.
	 *
	 * @return a list of maps, each map contains the information of one csv row
	 * @throws Exception if anything happens while reading or exporting the csv
	 */
	public List<Map<String, String>> parse() throws Exception
	{
		List<Map<String, String>> csvData = new ArrayList<>();

		try (FileInputStream fileInputStream = new FileInputStream(csv) ;
		     BOMInputStream bomInputStream = new BOMInputStream(fileInputStream) ;
		     InputStreamReader inputStreamReader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8))
		{
			CSVParser csvParser = CSVFormat.EXCEL.withDelimiter(';')
					.withFirstRecordAsHeader()
					.withIgnoreEmptyLines(true)
					.parse(inputStreamReader);

			List<String> providedCsv = csvParser.getHeaderNames();
			if (! compareHeader(providedCsv))
			{
				ErrorPopup.showErrorMessage("Es wird eine veraltete Version des Excel Templates verwendet.");
			}

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

	/**
	 * This function compares a list of necessary csv headers with the input csv headers, if the input contains all of
	 * them it returns true, else false.
	 * This function has to be up to date with the excel template! TODO
	 *
	 * @param input a List of Strings containing the headers of a csv file
	 * @return boolean true if input contains all, false if not.
	 */
	public static boolean compareHeader(List<String> input)
	{
		String headers = "ERK_ID;ERK_NUMMER;ERK_DATUM;ERK_PRUEFER;ERK_BEREICH;ERK_ANSPRECHPARTNER;ERK_KOORDINATEN;" +
				"ERK_ORT;ERK_AUFSCHLUSS_OB;ERK_AUFSCHLUSS_TOB;ERK_AUFSCHLUSS_UG_OH_BA;ERK_OBERKANTE;ERK_BELASTUNGSKLASSE;" +
				"ERK_BELASTUNGSKLASSE_TAFEL;ERK_PECH_QUALITATIV;ERK_PECH_HALBQUANTITATIV;ERK_PECH_QUANTITATIV;" +
				"ERK_TRAG_PLANUM;ERK_TRAG_GRABENSOHLE;ERK_SOHLE_TIEFE;ERK_VERBUND_UNTERLAGE;ERK_LP;ERK_LP1;ERK_LP2;" +
				"ERK_LP3;ERK_LP_MEAN;ERK_LP_EV;ERK_LP_EV15;ERK_LP_EV2;ERK_LP_EV2_SOLL;ERK_ZIELTIEFE;" +
				"ERK_HAUFWERK_MATERIAL;ERK_HAUFWERK_VOLUMEN;ERK_HAUFWERK_PROBEN_ANZAHL;ERK_LEITFADEN_AUSBAUASPHALT;" +
				"ERK_TEILWEISE_VERFESTIGT;ERK_UEBERSCHREITUNG_ORIENT;ERK_RAMMHINDERNIS;ERK_KABELTRASSE;" +
				"ERK_FREMDBESTANDTEILE;ERK_GUENSTIGE_EINSTUFUNG;ERK_VERNACHLAESSIGUNG_LEITFAEHIGKEIT;" +
				"ERK_VARIABLE_FOOTNOTE1;ERK_VARIABLE_FOOTNOTE2;ERK_VARIABLE_FOOTNOTE3;SCHICHT_ID;ERK_ID.1;" +
				"CHEMIE_ID;SCHICHT_NR;SCHICHT_AUFSCHLUSS;SCHICHT_ART;SCHICHT_KOERNUNG;SCHICHT_BODENKLASSE;" +
				"SCHICHT_DICKE;SCHICHT_TIEFE_START;SCHICHT_TIEFE_ENDE;SCHICHT_PECH;SCHICHT_PAK;" +
				"SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT;SCHICHT_FARBE;SCHICHT_BODENART;SCHICHT_KONSISTENZ;" +
				"SCHICHT_FEUCHTIGKEIT;SCHICHT_WASSERGEHALT;SCHICHT_WASSERPROCTOR;SCHICHT_NOTIZ;SCHICHT_BEHAELTNIS;" +
				"SCHICHT_ABFALLART;SCHICHT_GERUCH;SCHICHT_KORNGROESSENVERTEILUNG;SCHICHT_RUK_NR;SCHICHT_RUK_PROBE;" +
				"SCHICHT_RUK;SCHICHT_DRUCKFESTIGKEIT;SCHICHT_FROSTEMPFINDLICHKEITSKLASSE;SCHICHT_DIFFERENZ_WN_WOPT;" +
				"SCHICHT_VERDICHTUNGSFAEHIGKEIT;SCHICHT_HOMOGENBEREICH;CHEMIE_ID.1;CHEMIE_MUFV;CHEMIE_LFS;" +
				"CHEMIE_LAGA_BO;CHEMIE_LAGA_RC;CHEMIE_LAGARC_ORIENTIERUNGSWERT;CHEMIE_TLGESTEIN;CHEMIE_DEPV;" +
				"CHEMIE_ENTSCHEIDUNGSHILFE;CHEMIE_DEPV_ODER_ENTSCHEIDUNGSHILFE;CHEMIE_REKU;CHEMIE_ABFALLSCHLUESSEL;" +
				"CHEMIE_ASBEST;CHEMIE_PCB;CHEMIE_BTEX";

		String[] splitHeaders = headers.split(";");
		List<String> actualHeaders = Arrays.asList(splitHeaders);

		boolean containsAll = input.containsAll(actualHeaders);

		return containsAll;
	}
}
