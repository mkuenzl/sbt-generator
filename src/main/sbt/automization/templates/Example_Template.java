package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
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

		HtmlCell htmlCell = new HtmlCell.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(TextFormatUtil.presentSchichtenToB(data.get(0)))
				.build();

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
