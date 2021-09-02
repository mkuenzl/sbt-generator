package sbt.automization.templates.appendix;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.DataTable;
import sbt.automization.data.Examination;
import sbt.automization.export.HtmlTemplateExport;
import sbt.automization.export.WordTemplateExport;
import sbt.automization.util.CsvParser;
import sbt.automization.util.DatatableSerializationFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppendixTemplateBuildingTest
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

	@Test
	public void createExplorationSiteTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ExplorationSite.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	public static void openExportFile(String path) throws IOException
	{
		File htmlFile = new File(path);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	@Test
	public void createRukTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(RingAndBall.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void createLpTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(LoadPlate.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void createPnTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(SamplingProtocol.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void createPnHeapTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(SamplingProtocolHeap.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void createPnBuildingTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(SamplingProtocolBuilding.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}
}
