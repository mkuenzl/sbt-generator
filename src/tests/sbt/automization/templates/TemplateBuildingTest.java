package sbt.automization.templates;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.TableInformation;
import sbt.automization.data.ExplorationSite;
import sbt.automization.export.HtmlTemplateExport;
import sbt.automization.util.CsvParser;
import sbt.automization.util.Util;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplateBuildingTest
{
	List<ExplorationSite> explorationSites = new ArrayList<>();

	public static void openExportFile(String path) throws IOException
	{
		File htmlFile = new File(path);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	@BeforeClass
	public static void createErk() throws Exception
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		File csv = new File(path + "excel-template-test.csv");
		CsvParser csvParser = new CsvParser(csv);

		TableInformation database = new TableInformation(csvParser.parse(), csv.getParent());

		List<ExplorationSite> databaseExplorationSites = database.getExplorationSites();
		int size = databaseExplorationSites.size();
		for (int i = 0 ; i < size ; i++)
		{
			Util.serializeExplorationSiteToFile(databaseExplorationSites.get(i), "tests-resources/ExplorationSite_" + i);
		}
	}

	@Before
	public void initializeErk()
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_0"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_1"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_2"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_3"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_4"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_5"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_6"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_7"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_8"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_9"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_10"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "ExplorationSite_11"));

	}

	@Test
	public void createErkTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixExplorationSite.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createRukTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixRUK.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createLpTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixLP.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createPnTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixPN.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createPnHeapTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixPNHEAP.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportOBTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportGOB.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportTOBTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportTOB.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportUGTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportUG.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportOHTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportOH.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createExampleTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AttemptTemplate.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportTMHBTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportTMHB.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportBETONTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportCONCRETE.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportFUGETemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportFUGE.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportHeapTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportHEAP.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportCoordinateTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportCOORDINATES.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportBankettTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportBANKETT.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}
}
