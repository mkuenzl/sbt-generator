package sbt.automization.templates;

import sbt.automization.util.html.HtmlTable;

/**
 * Abstract class for all html tables
 */
public abstract class AHtmlTable implements IHtmlTable
{
	static final String HTML_BASIC_TABLE_STYLE = new StringBuilder()
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
			.toString();

	private String table;

	public String getTable()
	{
		return table;
	}

	public void setTable(final String table)
	{
		this.table = table;
	}

	abstract String constructAndGetTableHeader();

	HtmlTable constructAndGetTableObject()
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();

		return table;
	}
}
