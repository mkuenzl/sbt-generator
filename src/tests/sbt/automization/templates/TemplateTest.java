package sbt.automization.templates;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.TableEngine;
import sbt.automization.data.ExplorationSite;
import sbt.automization.export.HtmlTemplateExportStrategy;
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

	private static void openExportFile(HtmlTemplateExportStrategy htmlTemplateExportStrategy) throws IOException
	{
		String path = htmlTemplateExportStrategy.getPath();
		File htmlFile = new File(path);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	@BeforeClass
	public static void createErk()
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		File csv = new File(path + "Datenbank-Test.csv");
		Parser parser = new Parser(csv);

		TableEngine database = new TableEngine(parser.parse(), csv.getParent());

		List<ExplorationSite> databaseErkundungsstellen = database.getExplorationSites();
		int size = databaseErkundungsstellen.size();
		for (int i = 0 ; i < size ; i++)
		{
			Util.serializeExplorationSiteToFile(databaseErkundungsstellen.get(i), "tests-resources/TestErkundungsstelle_" + i);
		}
	}

	@Before
	public void initializeErk()
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		explorationSites.add(Util.readSerializedExplorationSite(path + "TestErkundungsstelle_0"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "TestErkundungsstelle_1"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "TestErkundungsstelle_2"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "TestErkundungsstelle_3"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "TestErkundungsstelle_4"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "TestErkundungsstelle_5"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "TestErkundungsstelle_6"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "TestErkundungsstelle_7"));
		explorationSites.add(Util.readSerializedExplorationSite(path + "TestErkundungsstelle_8"));
	}

	@Test
	public void createErkTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(AppendixExplorationSite.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createRukTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(AppendixRUK.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createLpTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(AppendixLP.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createPnTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy2 = new HtmlTemplateExportStrategy(AppendixPN.getInstance());
		htmlTemplateExportStrategy2.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy2);

		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(AppendixRUK.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtOBTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(ReportGOB.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtTOBTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(ReportTOB.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtUGTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(ReportUG.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtOHTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(ReportOH.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createExampleTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(ExampleTemplate.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtTMHBTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(ReportTMHB.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtBETONTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(ReportCONCRETE.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtFUGETemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(ReportFUGE.getInstance());
		htmlTemplateExportStrategy.export(explorationSites);

		openExportFile(htmlTemplateExportStrategy);
	}
}
