package sbt.automization.core.templates.appendix;

import sbt.automization.core.format.text.StandardCellTextFormatter;
import sbt.automization.core.format.text.TextFormatter;
import sbt.automization.core.html.HtmlTable;
import sbt.automization.core.templates.HtmlTemplate;

/**
 * Abstract class for all html tables
 */
public abstract class Appendix implements HtmlTemplate
{
	protected TextFormatter textFormatter = new StandardCellTextFormatter();

	public static final String HTML_BASIC_TABLE_STYLE = new StringBuilder()
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

	protected int linesPerPage;
	protected int lines;
	protected final StringBuilder template;
	protected HtmlTable table;

	public Appendix()
	{
		linesPerPage = 0;
		lines = 0;
		template = new StringBuilder();
	}

	public String getTemplate()
	{
		return template.toString();
	}

	public void addToTemplate(final String table)
	{
		this.template.append(table);
	}

	void addPageBreak()
	{
		this.template.append("<pre><br clear=all style='mso-special-character:line-break;page-break-before:always'></pre>");
	}

	void addTable()
	{
		String tableString = table.appendTag();
		this.template.append(tableString);
	}

	protected abstract String constructAndGetTableHeader();

	public void createTableWithHeader()
	{

		createTable();
		table.appendContent(constructAndGetTableHeader());
	}

	public void createTable()
	{
		table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();
	}

	void addToTable(String content)
	{
		table.appendContent(content);
	}

	protected void addAndResetTableOnPageBreak()
	{
		if (linesPerPage >= 19)
		{
			template.append(table.appendTag());
			addPageBreak();
			linesPerPage = 0;

			createTableWithHeader();
		}
	}

	@Override
	public void resetTemplate()
	{
		template.setLength(0);
	}
}
