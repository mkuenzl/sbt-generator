package sbt.automization.templates.report;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.DataTable;
import sbt.automization.data.Examination;
import sbt.automization.export.HtmlTemplateExport;
import sbt.automization.templates.appendix.*;
import sbt.automization.templates.basic.Coordinates;
import sbt.automization.templates.report.*;
import sbt.automization.util.CsvParser;
import sbt.automization.util.DatatableSerializationFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportTemplateBuildingTest
{
	List<DataTable> dataTables = new ArrayList<>();

	String serializableFilePath = System.getProperty("user.dir")
			.concat(File.separator)
			.concat("tests-resources")
			.concat(File.separator)
			.concat("serializable")
			.concat(File.separator);

	String templateExportPath = System.getProperty("user.dir")
			.concat(File.separator)
			.concat("tests-resources")
			.concat(File.separator)
			.concat("template-output")
			.concat(File.separator);

	@BeforeClass
	public static void createDatatables() throws Exception
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		File csv = new File(path + "datenbank-template-test.csv");
		CsvParser csvParser = new CsvParser(csv);

		Examination examination = new Examination(csvParser.parse(), csv.getParent());

		int size = examination.size();
		for (int i = 0 ; i < size ; i++)
		{
			DatatableSerializationFactory.serializeDatatableToFile(examination.get(i), "tests-resources/serializable/Probe_" + i);
		}
	}

	@Before
	public void initializeDatatables()
	{
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_0"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_1"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_2"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_3"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_4"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_5"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_6"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_7"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_8"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_9"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_10"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_11"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_12"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_13"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_14"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_15"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_16"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_17"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_18"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_19"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_20"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_21"));
		dataTables.add(DatatableSerializationFactory.readSerializedDatatable(serializableFilePath + "Probe_22"));
	}

	public static void openExportFile(String path) throws IOException
	{
		File htmlFile = new File(path);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	@Test
	public void GOBTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(BoundSuperstructure.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void TOBTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(BaseCourseWithoutBinder.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void UGTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Underground.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void OHTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Topsoil.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void ExampleTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AttemptTemplate.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void TMHBTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(BaseCourseWithHydraulicBinder.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void CONCRETETemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Concrete.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void GAPTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Gap.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void HEAPTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Heap.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void COORDINATETemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Coordinates.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void BANQUETTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Banquet.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void BUILDINGTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Building.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}
}
