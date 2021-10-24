package sbt.automization.core.templates.basic;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.html.HtmlTable;
import sbt.automization.core.styles.BasicStyle;
import sbt.automization.core.templates.HtmlTemplate;
import sbt.automization.core.util.ListSeparator;

import java.util.Collection;
import java.util.List;

/**
 * Abstract class for all report tables inherits from AHtmlTable
 */
public abstract class TableTemplate implements HtmlTemplate
{
	protected final StringBuilder template;
	protected int linesPerPage;
	protected int lines;
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
	
	@Override
	public void resetTemplate()
	{
		template.setLength(0);
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
		Collection<List<DataTable>> portions = ListSeparator.separateBasedOnSize(tables, 17);
		
		return portions;
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
	
}
