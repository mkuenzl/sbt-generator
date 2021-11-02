
package sbt.automization.core.templates;

import sbt.automization.core.data.*;
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

	public static void serializeDatatablesFromCsv()
	{
		File file = new File("test-resources/input/csv/csv-template-test.csv");
		CsvParser parser = new CsvParser();
		
		DataTableFactory.initialize(parser.parse(file));
		
		List<DataTable> tables = DataTableFactory.getTables();
		
		serializeDataTables(tables);
	}
	
	public static void serializeDatatablesFromExcel()
	{
		File file = new File("tests-resources/input/excel/excel-template-test.xlsx");
		ExcelParser parser = new ExcelParser();
		parser.setSheetName("Daten");
		
		DataTableFactory.initialize(parser.parse(file));
		
		List<DataTable> tables = DataTableFactory.getTables();
		
		serializeDataTables(tables);
	}
	
	public static void serializeDataTables(List<DataTable> tables)
	{
		int size = tables.size();
		int counterProbes = 0;
		int counterSamples = 0;
		int counterParameters = 0;
		
		for (int i = 0 ; i < size ; i++)
		{
			DataTable dataTable = tables.get(i);
			
			String fileName = "";
			if (dataTable instanceof Probe){
				fileName = "Probe_" + counterProbes++;
			}
			if (dataTable instanceof Sample){
				fileName = "Sample_" + counterSamples++;
			}
			if (dataTable instanceof Parameter){
				fileName = "Parameter_" + counterParameters++;
			}
			
			DatatableSerializationFactory.serializeDatatableToFile(dataTable, "tests-resources/serializable/" +
					fileName);
		}
	}
	
	public List<DataTable> initializeProbes()
	{
		List<DataTable> dataTables = new ArrayList<>();

		for (int i = 0 ; i < 24 ; i++)
		{
			dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_" + i));
		}

		return dataTables;
	}
	
	public List<DataTable> initializeSamples()
	{
		List<DataTable> dataTables = new ArrayList<>();
		
		for (int i = 0 ; i < 84 ; i++)
		{
			dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Sample_" + i));
		}
		
		return dataTables;
	}
	
	public List<DataTable> initializeParameters()
	{
		List<DataTable> dataTables = new ArrayList<>();
		
		for (int i = 0 ; i < 20 ; i++)
		{
			dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Parameter_" + i));
		}
		
		return dataTables;
	}
}
