package sbt.automization.core.templates;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Examination;
import sbt.automization.core.parser.CsvParser;
import sbt.automization.DatatableSerializationFactory;
import sbt.automization.core.parser.ExcelParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatatableInitializer
{
	String serializableFilePath = System.getProperty("user.dir")
			.concat(File.separator)
			.concat("tests-resources")
			.concat(File.separator)
			.concat("serializable")
			.concat(File.separator);

	public static void createDatatablesFromCsv()
	{
		File file = new File("test-resources/input/csv/csv-template-test.csv");
		CsvParser parser = new CsvParser();

		Examination examination = new Examination(parser.parse(file), file.getParent());

		int size = examination.size();
		for (int i = 0 ; i < size ; i++)
		{
			DatatableSerializationFactory.serializeDatatableToFile(examination.get(i), "tests-resources/serializable/Probe_" + i);
		}
	}
	
	public static void createDatatablesFromExcel()
	{
		File file = new File("test-resources/input/excel/excel-template-test.xlsx");
		ExcelParser parser = new ExcelParser();
		parser.setSheetName("Daten");
		Examination examination = new Examination(parser.parse(file), file.getParent());
		
		int size = examination.size();
		for (int i = 0 ; i < size ; i++)
		{
			DatatableSerializationFactory.serializeDatatableToFile(examination.get(i), "tests-resources/serializable/Probe_" + i);
		}
	}

	public List<DataTable> initializeDatatables()
	{
		List<DataTable> dataTables = new ArrayList<>();

		for (int i = 0 ; i < 24 ; i++)
		{
			dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_" + i));
		}

		return dataTables;
	}
}
