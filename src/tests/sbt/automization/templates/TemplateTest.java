package sbt.automization.templates;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.TableEngine;
import sbt.automization.data.Erkundungsstelle;
import sbt.automization.export.HtmlTemplateExportStrategy;
import sbt.automization.util.Parser;
import sbt.automization.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TemplateTest
{
	List<Erkundungsstelle> erkundungsstellen = new ArrayList<>();

	@BeforeClass
	public static void createErk()
	{
//		ERKCreationTestUtil erkCreationTestUtil = new ERKCreationTestUtil();
//		erkundungsstellen.add(erkCreationTestUtil.getTestErkundungsstelle1());
//		erkundungsstellen.add(erkCreationTestUtil.getTestErkundungsstelle2());
//		erkundungsstellen.add(erkCreationTestUtil.getTestErkundungsstelle3());
//		erkundungsstellen.add(erkCreationTestUtil.getTestErkundungsstelle4());

		File csv = new File("C:/Users/Kuenzl/Documents/GitHub/sbt-generator/tests-resources/Datenbank-Test.csv");
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
		erkundungsstellen.add(Util.readSerializedErkundungsstelle("C:/Users/Kuenzl/Documents/GitHub/sbt-generator/tests-resources/TestErkundungsstelle_0"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle("C:/Users/Kuenzl/Documents/GitHub/sbt-generator/tests-resources/TestErkundungsstelle_1"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle("C:/Users/Kuenzl/Documents/GitHub/sbt-generator/tests-resources/TestErkundungsstelle_2"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle("C:/Users/Kuenzl/Documents/GitHub/sbt-generator/tests-resources/TestErkundungsstelle_3"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle("C:/Users/Kuenzl/Documents/GitHub/sbt-generator/tests-resources/TestErkundungsstelle_4"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle("C:/Users/Kuenzl/Documents/GitHub/sbt-generator/tests-resources/TestErkundungsstelle_5"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle("C:/Users/Kuenzl/Documents/GitHub/sbt-generator/tests-resources/TestErkundungsstelle_6"));
		erkundungsstellen.add(Util.readSerializedErkundungsstelle("C:/Users/Kuenzl/Documents/GitHub/sbt-generator/tests-resources/TestErkundungsstelle_7"));
	}

	@Test
	public void createErkTemplate()
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Anlage_ERK_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);
	}

	@Test
	public void createRukTemplate()
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Anlage_RUK_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);
	}

	@Test
	public void createLpTemplate()
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Anlage_LP_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);
	}

	@Test
	public void createPnTemplate()
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Anlage_PN_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);
	}

	@Test
	public void createBerichtOBTemplate()
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Bericht_OB_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);
	}

	@Test
	public void createBerichtTOBTemplate()
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Bericht_TOB_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);
	}

	@Test
	public void createBerichtUGTemplate()
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Bericht_UG_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);
	}

	@Test
	public void createBerichtOHTemplate()
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Bericht_OH_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);
	}

	@Test
	public void createExampleTemplate()
	{
		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Example_Template.getInstance());
		htmlTemplateExportStrategy.export(erkundungsstellen);
	}
}
