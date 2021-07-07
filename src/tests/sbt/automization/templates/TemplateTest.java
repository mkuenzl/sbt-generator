package sbt.automization.templates;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.TableInformation;
import sbt.automization.data.ExplorationSite;
import sbt.automization.export.HtmlTemplateExport;
import sbt.automization.util.Parser;
import sbt.automization.util.Util;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplateTest
{
	List<ExplorationSite> explorationSites = new ArrayList<>();

	private static void openExportFile(HtmlTemplateExport htmlTemplateExportStrategy) throws IOException
	{
		String path = htmlTemplateExportStrategy.getPath();
		File htmlFile = new File(path);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	@BeforeClass
	public static void createErk() throws Exception
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		File csv = new File(path + "excel-template-test.csv");
		Parser parser = new Parser(csv);

		TableInformation database = new TableInformation(parser.parse(), csv.getParent());

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
	}

	@Test
	public void createErkTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixExplorationSite.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createRukTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixRUK.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createLpTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixLP.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createPnTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixPN.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createPnHeapTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AppendixPNHEAP.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createReportOBTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportGOB.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createReportTOBTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportTOB.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createReportUGTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportUG.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createReportOHTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportOH.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createExampleTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ExampleTable.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createReportTMHBTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportTMHB.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createReportBETONTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportCONCRETE.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createReportFUGETemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportFUGE.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createReportHeapTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportHEAP.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createReportCoordinateTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ReportCOORDINATES.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}
}
