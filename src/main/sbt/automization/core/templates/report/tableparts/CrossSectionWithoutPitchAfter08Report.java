package sbt.automization.core.templates.report.tableparts;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.text.DepthTextFormatter;
import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.html.HtmlRow;
import sbt.automization.core.html.HtmlText;
import sbt.automization.core.styles.StyleParameter;
import sbt.automization.core.templates.report.AbstractReport;

import java.util.List;

/**
 * Repräsentiert die Zeile innerhalb eines Reports, die den <b>Pechfreien Querschnitt</b> abbildet.
 */
public final class CrossSectionWithoutPitchAfter08Report
		extends AbstractReport
{
	private String size;
	private HtmlRow rowSize;
	private String mkuem;
	private HtmlRow rowMkuem;
	private String ruva;
	private HtmlRow rowRuva;
	private String avv;
	private HtmlRow rowAvv;
	
	public CrossSectionWithoutPitchAfter08Report()
	{
		super(Outcrop.GOB);
	}
	
	@Override
	protected void constructTechnicalFeatures(List<DataTable> dataTables)
	{
	
	}
	
	@Override
	protected void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
	
	}
	
	@Override
	protected void addLegendRow(List<DataTable> dataTables)
	{
	
	}
	
	@Override
	public String getExportFileName()
	{
		return null;
	}
	
	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		template.append(buildTable(dataTables));
	}
	
	private String buildTable(List<DataTable> dataTables)
	{
		StringBuilder crossSectionBuilder = new StringBuilder();
		
		createVariableRows();
		
		for (DataTable dataTable :
				dataTables)
		{
			double depth = measureDepthOfPitchContent(dataTable);
			
			setCellStrings(depth);
			
			addCellsToRows();
		}
		
		crossSectionBuilder.append(createHeaderRow(dataTables))
				.append(rowSize.appendTag())
				.append(rowMkuem.appendTag())
				.append(rowRuva.appendTag())
				.append(rowAvv.appendTag());
		
		
		return crossSectionBuilder.toString();
	}
	
	private void createVariableRows()
	{
		StyleParameter styleParameter = getStyleParameter();
		rowSize = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Dicke,", formatUnit("cm")})});
		
		rowMkuem = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Abgrenzung Gefährlichkeit,", formatUnit("Schreiben des MKUEM<sup>[11]</sup>")})});
		
		rowRuva = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Verwertungsklasse,", formatUnit("RuVA<sup>[6]</sup>")})});
		
		rowAvv = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Abfallschlüssel,", formatUnit("AVV<sup>[14]</sup>")})});
	}
	
	private String formatUnit(String text)
	{
		StyleParameter styleParameter = getStyleParameter();
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", styleParameter.getUnitCellClass())
				.appendContent(text)
				.build()
				.appendTag();
		
		return formattedUnitText;
	}
	
	private double measureDepthOfPitchContent(DataTable dataTable)
	{
		double depth = 0;
		
		List<Sample> samples = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop.toString());
		
		if (samples != null)
		{
			for (Sample sample : samples)
			{
				Double thicknessOfSample = sample.getAsDouble(SampleKey.THICKNESS);
				
				String isPitch = sample.get(SampleKey.PITCH);
				
				if ("NEIN".equalsIgnoreCase(isPitch) && thicknessOfSample != null)
				{
					depth += thicknessOfSample;
				}
			}
		}
		
		return depth;
	}

	private void setCellStrings(double depth)
	{
		// Pechfreier Querschnitt, wenn gemessen gilt generell als nicht gefährlich.
		if (depth > 0)
		{
			size = new DepthTextFormatter().printThicknessInHalves(depth);
			mkuem = "nicht gefährlich";
			ruva = "A";
			avv = "17 03 02";
		} else
		{
			size = "-";
			mkuem = "-";
			ruva = "-";
			avv = "-";
		}
	}
	
	private void addCellsToRows()
	{
		StyleParameter styleParameter = getStyleParameter();
		
		HtmlCell cellPitchSize = HtmlFactory.createCell(styleParameter.getNormalCellClass(), styleParameter.getNormalCellWidth(),
				new String[]{size});
		rowSize.appendContent(cellPitchSize.appendTag());
		
		rowMkuem.appendContent(HtmlFactory.createChemistryCellAsString(mkuem));
		
		HtmlCell cellPitchRuva = HtmlFactory.createCell(styleParameter.getNormalCellClass(), styleParameter.getNormalCellWidth(),
				new String[]{ruva});
		rowRuva.appendContent(cellPitchRuva.appendTag());
		
		HtmlCell cellPitchAvv = HtmlFactory.createCell(styleParameter.getNormalCellClass(), styleParameter.getNormalCellWidth(),
				new String[]{avv});
		rowAvv.appendContent(cellPitchAvv.appendTag());
	}
	
	private String createHeaderRow(List<DataTable> dataTables)
	{
		StyleParameter styleParameter = getStyleParameter();
		HtmlRow rowHeader = new HtmlRow.Builder()
				.appendAttribute("class", styleParameter.getRowClass())
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", styleParameter.getHeaderCellClass())
						.appendAttribute("colspan", String.valueOf(1 + dataTables.size()))
						.appendContent("Pechfreier Querschnitt")
						.build()
						.appendTag())
				.build();
		
		return rowHeader.appendTag();
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
}
