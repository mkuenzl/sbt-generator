package sbt.automization.format;

import org.junit.Test;
import sbt.automization.data.Erkundungsstelle;
import sbt.automization.export.HtmlTemplateExportStrategy;
import sbt.automization.templates.ERKCreationTestUtil;
import sbt.automization.templates.Example_Template;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

public class HtmlCellFormatUtilText
{


	private Example_Template template = Example_Template.getInstance();

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

		Erkundungsstelle testErkundungsstelle1 = new ERKCreationTestUtil().getTestErkundungsstelle1();
		HtmlCell htmlCell1 = HtmlCellFormatUtil.presentSchichtenToB(testErkundungsstelle1);

		Erkundungsstelle testErkundungsstelle2 = new ERKCreationTestUtil().getTestErkundungsstelle2();
		HtmlCell htmlCell2 = HtmlCellFormatUtil.presentSchichtenToB(testErkundungsstelle2);

		Erkundungsstelle testErkundungsstelle3 = new ERKCreationTestUtil().getTestErkundungsstelle3();
		HtmlCell htmlCell3 = HtmlCellFormatUtil.presentSchichtenToB(testErkundungsstelle3);

		Erkundungsstelle testErkundungsstelle4 = new ERKCreationTestUtil().getTestErkundungsstelle4();
		HtmlCell htmlCell4 = HtmlCellFormatUtil.presentSchichtenToB(testErkundungsstelle4);


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
