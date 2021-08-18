package sbt.automization.templates.basic;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.templates.HtmlTemplate;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.styles.BasicStyle;
import sbt.automization.util.Util;
import sbt.automization.util.html.HtmlTable;

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

	void addPageBreak(){
		this.template.append("<br>");
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
		Collection<List<DataTable>> portions = Util.separateBasedOnSize(tables, 17);

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

}
