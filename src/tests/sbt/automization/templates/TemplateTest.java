package sbt.automization.templates;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.TableEngine;
import sbt.automization.data.Erkundungsstelle;
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
	List<Erkundungsstelle> erkundungsstellen = new ArrayList<>();

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

		List<Erkundungsstelle> databaseErkundungsstellen = database.getErkundungsstellen();
		int size = databaseErkundungsstellen.size();
		for (int i = 0 ; i < size ; i++)
		{
			Util.serializeErkundungsstelleToFile(databaseErkundungsstellen.get(i), "tests-resources/TestErkundungsstelle_" + i);
		}
	}

	@Before
	public void initializeErk()
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		erkundungsstellen.add(Util.readSerializedErkundungsstelle(path + "TestErkundungsstelle_0"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle(path + "TestErkundungsstelle_1"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle(path + "TestErkundungsstelle_2"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle(path + "TestErkundungsstelle_3"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle(path + "TestErkundungsstelle_4"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle(path + "TestErkundungsstelle_5"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle(path + "TestErkundungsstelle_6"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle(path + "TestErkundungsstelle_7"));
	}

	@Test
	public void createErkTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Anlage_ERK_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createRukTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Anlage_RUK_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createLpTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Anlage_LP_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createPnTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Anlage_PN_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtOBTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Bericht_OB_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtTOBTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Bericht_TOB_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtUGTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Bericht_UG_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createBerichtOHTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Bericht_OH_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);

		openExportFile(htmlTemplateExportStrategy);
	}

	@Test
	public void createExampleTemplate() throws IOException
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Example_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);

		openExportFile(htmlTemplateExportStrategy);
	}
}
