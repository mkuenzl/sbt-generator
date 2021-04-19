package sbt.automization.format;

import org.junit.Test;
import sbt.automization.data.ExplorationSite;
import sbt.automization.export.HtmlTemplateExportStrategy;
import sbt.automization.templates.ErkundungsstellenTestFactory;
import sbt.automization.templates.ExampleTemplate;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

public class HtmlCellFormatUtilTest
{


	private ExampleTemplate template = ExampleTemplate.getInstance();

	private HtmlTable table = new HtmlTable.Builder()
			.appendAttribute("class", "MsoNormalTable")
			.appendAttribute("width", "605")
			.appendAttribute("border", "1")
			.appendAttribute("style", new StringBuilder()
					.append("'")
					.append("border-collapse:collapse")
					.append(";")
					.append("mso-table-layout-alt:fixed")
					.append(";")
					.append("border:none")
					.append(";")
					.append("mso-border-alt:solid windowtext .5pt")
					.append(";")
					.append("mso-padding-alt:0cm 5.4pt 0cm 5.4pt")
					.append("'")
					.toString())
			.appendAttribute("cellspacing", "0")
			.appendAttribute("cellpadding", "0")
			.build();

	@Test
	public void presentSchichtenToBTest()
	{

		ExplorationSite testExplorationSite1 = new ErkundungsstellenTestFactory().getTestErkundungsstelle1();
		HtmlCell htmlCell1 = new HtmlCell.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(TextFormatUtil.presentTobLayers(testExplorationSite1))
				.build();

		ExplorationSite testExplorationSite2 = new ErkundungsstellenTestFactory().getTestErkundungsstelle2();
		HtmlCell htmlCell2 = new HtmlCell.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(TextFormatUtil.presentTobLayers(testExplorationSite2))
				.build();

		ExplorationSite testExplorationSite3 = new ErkundungsstellenTestFactory().getTestErkundungsstelle3();
		HtmlCell htmlCell3 = new HtmlCell.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(TextFormatUtil.presentTobLayers(testExplorationSite3))
				.build();

		ExplorationSite testExplorationSite4 = new ErkundungsstellenTestFactory().getTestErkundungsstelle4();
		HtmlCell htmlCell4 = new HtmlCell.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(TextFormatUtil.presentTobLayers(testExplorationSite4))
				.build();


		HtmlRow htmlRow = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(htmlCell1.appendTag())
				.appendContent(htmlCell2.appendTag())
				.appendContent(htmlCell3.appendTag())
				.appendContent(htmlCell4.appendTag())
				.build();

		table.appendContent(htmlRow.appendTag());

		template.setHtmlTable(table.appendTag());

		HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(template);
		htmlTemplateExportStrategy.export();
	}
}
