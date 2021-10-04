package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.format.text.StandardCellTextFormatter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlTable;
import sbt.automization.styles.ReportStyle;
import sbt.automization.styles.StyleParameter;
import sbt.automization.styles.StyleParameterBuilder;
import sbt.automization.templates.HtmlTemplate;
import sbt.automization.templates.helper.HeaderProvider;
import sbt.automization.util.DatatableFilter;
import sbt.automization.util.Separator;

import java.util.Collection;
import java.util.List;

/**
 * Abstract class for all report tables inherits from AHtmlTable
 */
public abstract class Report implements HtmlTemplate
{
	protected final Outcrop outcrop;
	protected final StringBuilder template;
	protected HtmlTable table;
	protected final HeaderProvider header;


	public Report()
	{
		this.template = new StringBuilder();
		this.outcrop = null;
		this.header = new HeaderProvider(getStyleParameterHeader());
	}

	public Report(Outcrop outcrop)
	{
		this.template = new StringBuilder();
		this.outcrop = outcrop;
		this.header = new HeaderProvider(getStyleParameterHeader());
	}

	public String getTemplate()
	{
		return template.toString();
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

		Collection<List<DataTable>> portions = Separator.separateBasedOnSize(probesWhichIncludeOutcrop, 17);

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

	protected void addToTable(String content)
	{
		table.appendContent(content);
	}

	protected StyleParameter getStyleParameterHeader()
	{
		return new StyleParameterBuilder()
				.setRowClass("NormalThin8")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("5")
				.setNormalCellClass("NormalBoldHeader")
				.setNormalCellWidth("2.5")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}

	protected StyleParameter getStyleParameter()
	{
		return new StyleParameterBuilder()
				.setRowClass("NormalThin8")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("5")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("2.5")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}
}
