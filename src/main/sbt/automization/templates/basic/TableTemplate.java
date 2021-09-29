package sbt.automization.templates.basic;

import sbt.automization.data.DataTable;
import sbt.automization.html.HtmlTable;
import sbt.automization.styles.BasicStyle;
import sbt.automization.templates.HtmlTemplate;
import sbt.automization.util.Separator;

import java.util.Collection;
import java.util.List;

/**
 * Abstract class for all report tables inherits from AHtmlTable
 */
public abstract class TableTemplate implements HtmlTemplate
{
	protected int linesPerPage;
	protected int lines;
	protected final StringBuilder template;
	protected HtmlTable table;

	public TableTemplate()
	{
		linesPerPage = 0;
		lines = 0;
		template = new StringBuilder();
	}

	public String getTemplate()
	{
		return template.toString();
	}

	abstract void addTableHeader();

	void addPageBreak()
	{
		this.template.append("<pre><br clear=all style='mso-special-character:line-break;page-break-before:always'></pre>");
	}

	void addTable()
	{
		String tableString = table.appendTag();
		this.template.append(tableString);
	}


	/**
	 * Method used to retrieve all exploration sites containing an outcrop and dividing them into
	 * A3 paper sized portions.
	 *
	 * @param tables a List of ExplorationSites
	 * @return a Collection of Lists
	 */
	Collection<List<DataTable>> splitIntoPortionPerPage(List<DataTable> tables)
	{
		Collection<List<DataTable>> portions = Separator.separateBasedOnSize(tables, 17);

		return portions;
	}

	void createTable()
	{
		this.table = new HtmlTable.Builder()
				.appendAttribute("class", BasicStyle.TABLE.getStyleClass())
				.appendAttribute("border", "1")
				.appendAttribute("style", BasicStyle.TABLE.getStyle())
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
			addTable();
			addPageBreak();
			addPageBreak();

			linesPerPage = 0;

			createTable();
			addTableHeader();
		}
	}

}
