package sbt.automization.templates;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Examination;
import sbt.automization.export.HtmlTemplateExport;
import sbt.automization.templates.appendix.AppendixLP;
import sbt.automization.templates.appendix.AppendixPN;
import sbt.automization.templates.appendix.AppendixRUK;
import sbt.automization.util.CsvParser;
import sbt.automization.util.Util;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplateBuildingTest
{
	List<DataTable> dataTables = new ArrayList<>();

	public static void openExportFile(String path) throws IOException
	{
		File htmlFile = new File(path);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

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
			Util.serializeDatatableToFile(examination.get(i), "tests-resources/Probe_" + i);
		}
	}

	@Before
	public void initializeDatatables()
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		dataTables.add(Util.readSerializedDatatable(path + "Probe_0"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_1"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_2"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_3"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_4"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_5"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_6"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_7"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_8"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_9"));
//		dataTables.add(Util.readSerializedDatatable(path + "Probe_10"));
//		dataTables.add(Util.readSerializedDatatable(path + "Probe_11"));

	}

//	@Test
//	public void createErkTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixExplorationSite.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}

	@Test
	public void createRukTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixRUK.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createLpTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixLP.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createPnTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixPN.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}
//
//	@Test
//	public void createPnHeapTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixPNHEAP.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createReportOBTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportGOB.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createReportTOBTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportTOB.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createReportUGTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportUG.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createReportOHTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportOH.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createExampleTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AttemptTemplate.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createReportTMHBTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportTMHB.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createReportBETONTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportCONCRETE.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createReportFUGETemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportFUGE.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createReportHeapTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportHEAP.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createReportCoordinateTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportCOORDINATES.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
//
//	@Test
//	public void createReportBankettTemplate() throws IOException
//	{
//		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportBANKETT.getInstance());
//		htmlTemplateExportStrategy.export(explorationSites);
//
//		openExportFile(htmlTemplateExportStrategy.getPath());
//	}
}
