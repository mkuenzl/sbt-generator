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
		String headers = "PROBE.ID;PROBE.NUMMER;PROBE.LP.ID;PROBE.DATUM;PROBE.PRUEFER;PROBE.BEREICH;PROBE.ANSPRECHPARTNER;" +
				"PROBE.KOORDINATEN;PROBE.ORT;PROBE.AUFSCHLUSS_OB;PROBE.AUFSCHLUSS_TOB;PROBE.AUFSCHLUSS_UG_OH_BA;" +
				"PROBE.OBERKANTE;PROBE.BELASTUNGSKLASSE;PROBE.BELASTUNGSKLASSE_TAFEL;PROBE.PECH_QUALITATIV;" +
				"PROBE.PECH_HALBQUANTITATIV;PROBE.PECH_QUANTITATIV;PROBE.TRAG_PLANUM;PROBE.TRAG_GRABENSOHLE;" +
				"PROBE.SOHLE_TIEFE;PROBE.VERBUND_UNTERLAGE;PROBE.ZIELTIEFE;PROBE.FOOTNOTE_1;PROBE.FOOTNOTE_2;" +
				"PROBE.FOOTNOTE_3;PROBE.FOOTNOTE_4;PROBE.FOOTNOTE_5;PROBE.FOOTNOTE_6;PROBE.FOOTNOTE_7;PROBE.FOOTNOTE_8;" +
				"PROBE.FOOTNOTE_9;PROBE.FOOTNOTE_10;PROBE.BAUTEIL;PROBE.PROBENCHARAKTER;PROBE.GERUCH;PROBE.PROBENNUMMER;" +
				"PROBE.GEBAEUDE;PROBE.ETAGE;PROBE.RAUM;PROBE.ENTNAHME;SAMPLE.ID;SAMPLE.PROBE.ID;SAMPLE.CHEMISTRY.ID;" +
				"SAMPLE.RUK.ID;SAMPLE.NUMMER;SAMPLE.AUFSCHLUSS;SAMPLE.ART;SAMPLE.KOERNUNG;SAMPLE.BODENKLASSE;" +
				"SAMPLE.DICKE;SAMPLE.TIEFE_START;SAMPLE.TIEFE_ENDE;SAMPLE.PECH;SAMPLE.RUNDUNGSGRAD_GESTUFTHEIT;" +
				"SAMPLE.FARBE;SAMPLE.BODENART;SAMPLE.KONSISTENZ;SAMPLE.FEUCHTIGKEIT;SAMPLE.WASSERGEHALT;" +
				"SAMPLE.WASSERPROCTOR;SAMPLE.NOTIZ;SAMPLE.BEHAELTNIS;SAMPLE.ABFALLART;SAMPLE.GERUCH;" +
				"SAMPLE.KORNGROESSENVERTEILUNG;SAMPLE.DRUCKFESTIGKEIT;SAMPLE.FROSTEMPFINDLICHKEITSKLASSE;" +
				"SAMPLE.VERDICHTUNGSFAEHIGKEIT;SAMPLE.HOMOGENBEREICH;SAMPLE.MATERIAL;SAMPLE.VOLUMEN;" +
				"SAMPLE.PROBEN;SAMPLE.ENTNAHME;SAMPLE.SCHADSTOFFVERDACHT;SAMPLE.ABFALLSCHLUESSEL_MATERIAL;" +
				"SAMPLE.ABFALLSCHLUESSEL_GEMISCH;PARAMETER.CHEMISTRY.ID;PARAMETER.CHEMISTRY.MUFV;" +
				"PARAMETER.CHEMISTRY.MUFV_PARAMETER;PARAMETER.CHEMISTRY.LFS;PARAMETER.CHEMISTRY.LAGA_BO;" +
				"PARAMETER.CHEMISTRY.LAGA_RC;PARAMETER.CHEMISTRY.LAGA_RC_ORIENTIERUNGSWERT;" +
				"PARAMETER.CHEMISTRY.TL_GESTEIN;PARAMETER.CHEMISTRY.DEPV;PARAMETER.CHEMISTRY.ENTSCHEIDUNGSHILFE;" +
				"PARAMETER.CHEMISTRY.REKU;PARAMETER.CHEMISTRY.ABFALLSCHLUESSEL;PARAMETER.CHEMISTRY.ASBEST;PARAMETER.CHEMISTRY.RUVA;" +
				"PARAMETER.CHEMISTRY.PCB;PARAMETER.CHEMISTRY.BTEX;PARAMETER.CHEMISTRY.EOX;" +
				"PARAMETER.CHEMISTRY.PHENOLE;PARAMETER.CHEMISTRY.ICP_SCREENING;PARAMETER.CHEMISTRY.SULFAT;" +
				"PARAMETER.CHEMISTRY.KMF;PARAMETER.CHEMISTRY.PAK;PARAMETER.CHEMISTRY.ALTHOLZ_VERORDNUNG;" +
				"PARAMETER.LP.ID;PARAMETER.LP.NUMMER;PARAMETER.LP.WERT_1;PARAMETER.LP.WERT_2;PARAMETER.LP.WERT_3;" +
				"PARAMETER.LP.MITTELWERT;PARAMETER.LP.EV;PARAMETER.LP.EV85;PARAMETER.LP.EV2;PARAMETER.LP.EV2_SOLL;" +
				"PARAMETER.RUK.ID;PARAMETER.RUK.NUMMER;PARAMETER.RUK.PROBENART;PARAMETER.RUK.WERT";

		String[] splitHeaders = headers.split(";");
		List<String> actualHeaders = Arrays.asList(splitHeaders);

		boolean containsAll = input.containsAll(actualHeaders);

		return containsAll;
	}
}
