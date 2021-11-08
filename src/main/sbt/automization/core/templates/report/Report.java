package sbt.automization.core.templates.report;

import sbt.automization.core.Project;
import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.format.text.StandardCellTextFormatter;
import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.html.HtmlRow;
import sbt.automization.core.html.HtmlTable;
import sbt.automization.core.styles.ReportStyle;
import sbt.automization.core.styles.StyleParameter;
import sbt.automization.core.styles.StyleParameterBuilder;
import sbt.automization.core.templates.HtmlTemplate;
import sbt.automization.core.templates.construction.HeaderFactory;
import sbt.automization.core.util.DatatableFilter;
import sbt.automization.core.util.ListSeparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract class for all report tables inherits from AHtmlTable
 */
public abstract class Report implements HtmlTemplate
{
	protected final Outcrop outcrop;
	protected final StringBuilder template;
	protected final HeaderFactory header;
	protected HtmlTable table;
	
	
	public Report()
	{
		this.template = new StringBuilder();
		this.outcrop = null;
		this.header = new HeaderFactory(getStyleParameterHeader());
	}
	
	protected StyleParameter getStyleParameterHeader()
	{
		return new StyleParameterBuilder()
				.setRowClass("NormalThin8")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("4")
				.setNormalCellClass("NormalBoldHeader")
				.setNormalCellWidth("2.5")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}
	
	public Report(Outcrop outcrop)
	{
		this.template = new StringBuilder();
		this.outcrop = outcrop;
		this.header = new HeaderFactory(getStyleParameterHeader());
	}
	
	@Override
	public void constructTemplate(Project project)
	{
		List<DataTable> dataTables = new ArrayList<>(project.getProbes());
		constructTemplate(dataTables);
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
	
	protected void addPageBreak()
	{
		this.template.append("<pre><br clear=all style='mso-special-character:line-break;page-break-before:always'></pre>");
	}
	
	protected void addTable()
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
	protected Collection<List<DataTable>> splitIntoPortionPerPage(List<DataTable> tables)
	{
		List<DataTable> probesWhichIncludeOutcrop = DatatableFilter.getProbesWhichIncludeOutcrop(tables, outcrop);
		
		Collection<List<DataTable>> portions = ListSeparator.separateBasedOnSize(probesWhichIncludeOutcrop, 17);
		
		return portions;
	}
	
	protected abstract void constructTechnicalFeatures(List<DataTable> dataTables);
	
	void addTechnicalHeader(List<DataTable> dataTables)
	{
		int colspan = dataTables.size() + 1;
		
		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Technische Merkmale"})
		});
		
		addToTable(row.appendTag());
	}
	
	protected void addToTable(String content)
	{
		table.appendContent(content);
	}
	
	protected abstract void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables);
	
	protected abstract void addLegendRow(List<DataTable> dataTables);
	
	void addEnvironmentTechnicalHeader(List<DataTable> dataTables)
	{
		int colspan = dataTables.size() + 1;
		
		HtmlRow row = HtmlFactory.createRow(ReportStyle.ROW.getStyleClass(), new HtmlCell[]{
				HtmlFactory.createCell(ReportStyle.HEADER.getStyleClass(), 1, colspan,
						new String[]{"Umwelttechnische Merkmale"})
		});
		
		addToTable(row.appendTag());
	}
	
	protected void createTable()
	{
		this.table = new HtmlTable.Builder()
				.appendAttribute("class", ReportStyle.TABLE.getStyleClass())
				.appendAttribute("border", "1")
				.appendAttribute("style", ReportStyle.TABLE.getStyle())
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();
	}
	
	protected StyleParameter getStyleParameter()
	{
		return new StyleParameterBuilder()
				.setRowClass("NormalThin8")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("4")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("1.8")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}
}
