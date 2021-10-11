package sbt.automization.core.templates;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Examination;
import sbt.automization.core.util.csv.CsvParser;
import sbt.automization.DatatableSerializationFactory;

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

	public static void createDatatables() throws Exception
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		File csv = new File(path + "datenbank-template-test.csv");
		CsvParser csvParser = new CsvParser();

		Examination examination = new Examination(csvParser.parse(csv), csv.getParent());

		int size = examination.size();
		for (int i = 0 ; i < size ; i++)
		{
			DatatableSerializationFactory.serializeDatatableToFile(examination.get(i), "tests-resources/serializable/Probe_" + i);
		}
	}

	public List<DataTable> initializeDatatables() throws Exception
	{
		createDatatables();

		List<DataTable> dataTables = new ArrayList<>();

		for (int i = 0 ; i < 23 ; i++)
		{
			dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_" + i));
		}

		return dataTables;
	}
}
