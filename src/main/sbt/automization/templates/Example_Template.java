package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class Example_Template extends AHtmlTemplate
{

	private static Example_Template instance;

	private Example_Template() {}

	public static Example_Template getInstance()
	{
		if (instance == null)
		{
			synchronized (Example_Template.class)
			{
				if (instance == null)
				{
					instance = new Example_Template();
				}
			}
		}
		return instance;
	}


	@Override
	String setHtmlTableHeader()
	{
		return null;
	}

/*	@Override
	public void buildHtmlTable(List<Erkundungsstelle> data)
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();


		HtmlCell cell1 = new HtmlCell.Builder()
				.appendAttribute("class", "NormalInner")
				.appendAttribute("width", "35")
				.appendAttribute("align", "center")
				.appendContent("1")
				.build();

		HtmlTable table4 = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "100.0%")
				.appendAttribute("height", "100.0%")
				.appendAttribute("border", "0")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();

		HtmlRow htmlRow41 = new HtmlRow.Builder()
				.appendAttribute("class", "NormalInner")
				.appendContent(cell1.appendTag())
				.build();

		HtmlRow htmlRow42 = new HtmlRow.Builder()
				.appendAttribute("class", "NormalInner")
				.appendContent(cell1.appendTag())
				.build();

		HtmlRow htmlRow43 = new HtmlRow.Builder()
				.appendAttribute("class", "NormalInner")
				.appendContent(cell1.appendTag())
				.build();

		table4.appendContent(htmlRow41.appendTag());
		table4.appendContent(htmlRow42.appendTag());
		table4.appendContent(htmlRow43.appendTag());

		HtmlCell cell4 = new HtmlCell.Builder()
				.appendAttribute("class", "Normal")
				.appendAttribute("align", "center")
				.appendContent(table4.appendTag())
				.build();


//		HtmlTable table2 = new HtmlTable.Builder()
//				.appendAttribute("class", "MsoTableGrid")
//				.appendAttribute("width", "605")
//				.appendAttribute("border", "1")
//				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
//				.appendAttribute("cellspacing", "0")
//				.appendAttribute("cellpadding", "0")
//				.build();

		HtmlTable table2 = HtmlCellFormatUtil.buildInnerTable();

		HtmlRow htmlRow11 = new HtmlRow.Builder()
				.appendAttribute("class", "NormalInner")
				.appendContent(cell1.appendTag())
				.build();

		HtmlRow htmlRow12 = new HtmlRow.Builder()
				.appendAttribute("class", "NormalInner")
				.appendContent(cell1.appendTag())
				.build();

		table2.appendContent(htmlRow11.appendTag());
		table2.appendContent(htmlRow12.appendTag());

		HtmlCell cell2 = new HtmlCell.Builder()
				.appendAttribute("class", "Normal")
				.appendAttribute("align", "center")
				.appendContent(table2.appendTag())
				.build();


		HtmlTable table3 = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "100.0%")
				.appendAttribute("border", "0")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();

		HtmlRow htmlRow31 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(cell1.appendTag())
				.build();

		HtmlRow htmlRow32 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(cell1.appendTag())
				.build();

		HtmlRow htmlRow33 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(cell1.appendTag())
				.build();

		HtmlRow htmlRow34 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(cell1.appendTag())
				.build();

		table3.appendContent(htmlRow31.appendTag());
		table3.appendContent(htmlRow32.appendTag());
		table3.appendContent(htmlRow33.appendTag());
		table3.appendContent(htmlRow34.appendTag());

		HtmlCell cell3 = new HtmlCell.Builder()
				.appendAttribute("class", "Normal")
				.appendAttribute("align", "center")
				.appendContent(table3.appendTag())
				.build();

		HtmlRow htmlRow1 = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(cell1.appendTag())
				.appendContent(cell4.appendTag())
				.appendContent(cell2.appendTag())
				.appendContent(cell3.appendTag())
				.build();

		table.appendContent(htmlRow1.appendTag());

		setHtmlTable(table.appendTag());

	}*/

	@Override
	public void buildHtmlTable(List<Erkundungsstelle> data)
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();

		HtmlCell htmlCell = HtmlCellFormatUtil.presentSchichtenToB(data.get(0));

		HtmlRow htmlRow = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(htmlCell.appendTag())
				.build();

		table.appendContent(htmlRow.appendTag());

		setHtmlTable(table.appendTag());
	}

	@Override
	public void buildHtmlTable(Erkundungsstelle data)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "example.html";
	}
}
