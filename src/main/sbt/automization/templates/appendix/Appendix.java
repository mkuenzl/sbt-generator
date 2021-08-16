package sbt.automization.templates.appendix;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.HtmlTemplate;
import sbt.automization.util.html.HtmlTable;

/**
 * Abstract class for all html tables
 */
public abstract class Appendix implements HtmlTemplate
{
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

	protected abstract String constructAndGetTableHeader();

	public HtmlTable constructAndGetTableObject()
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

	protected void addAndResetTableOnPageBreak()
	{
		if (linesPerPage >= 20)
		{
			template.append(table.appendTag())
					.append("<br>")
					.append("<br>");

			linesPerPage = 0;

			table = constructAndGetTableObject();
		}
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
