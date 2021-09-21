package sbt.automization.templates.helper.strategy;

import sbt.automization.data.DataTable;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;

import java.util.List;

public class CrossSectionWithoutPitchRows extends RowConstruction
{
	private String size;
	private HtmlRow rowSize;
	private String mufv;
	private HtmlRow rowMufv;
	private String ruva;
	private HtmlRow rowRuva;
	private String avv;
	private HtmlRow rowAvv;

	public CrossSectionWithoutPitchRows()
	{
		super(ProbeKey.ID);
	}

	@Override
	public String buildWithProbes()
	{
		StringBuilder crossSectionBuilder = new StringBuilder();

		createVariableRows();

		for (DataTable dataTable :
				probes)
		{
			double depth = measureDepthOfPitchContent(dataTable);

			setCellStrings(depth);

			addCellsToRows();
		}

		crossSectionBuilder.append(createHeaderRow())
				.append(rowSize.appendTag())
				.append(rowMufv.appendTag())
				.append(rowRuva.appendTag())
				.append(rowAvv.appendTag());


		return crossSectionBuilder.toString();
	}

	private void createVariableRows()
	{
		rowSize = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Dicke,", formatUnit("cm")})});

		rowMufv = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Abgrenzung", UtilityPrinter.printLineBreak(), "Gefährlichkeit,", formatUnit("Schreiben des MUFV<sup>[11]</sup>")})});

		rowRuva = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Verwertungsklasse,", formatUnit("RuVA<sup>[6]</sup>")})});

		rowAvv = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Abfallschlüssel,", formatUnit("AVV<sup>[14]</sup>")})});
	}

	private double measureDepthOfPitchContent(DataTable dataTable)
	{
		double depth = 0;

		List<Sample> samples = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop);

		if (samples != null)
		{
			for (Sample sample : samples)
			{
				Double thicknessOfSample = sample.getAsDouble(SampleKey.THICKNESS);

				String isPitch = sample.get(SampleKey.PITCH);

				if ("NEIN".equalsIgnoreCase(isPitch))
				{
					depth += thicknessOfSample;
				}
			}
		}

		return depth;
	}

	private void setCellStrings(double depth)
	{
		if (depth > 0)
		{
			size = String.valueOf(depth).replace(".", ",");
			mufv = "nicht gefährlich";
			ruva = "A";
			avv = "17 03 02";
		} else
		{
			size = "-";
			mufv = "-";
			ruva = "-";
			avv = "-";
		}
	}

	private void addCellsToRows()
	{
		HtmlCell cellPitchSize = HtmlFactory.createCell(styleParameter.getNormalCellClass(), styleParameter.getNormalCellWidth(),
				new String[]{size});
		rowSize.appendContent(cellPitchSize.appendTag());

		rowMufv.appendContent(HtmlFactory.createChemistryCell(mufv));

		HtmlCell cellPitchRuva = HtmlFactory.createCell(styleParameter.getNormalCellClass(), styleParameter.getNormalCellWidth(),
				new String[]{ruva});
		rowRuva.appendContent(cellPitchRuva.appendTag());

		HtmlCell cellPitchAvv = HtmlFactory.createCell(styleParameter.getNormalCellClass(), styleParameter.getNormalCellWidth(),
				new String[]{avv});
		rowAvv.appendContent(cellPitchAvv.appendTag());
	}

	private String createHeaderRow()
	{
		HtmlRow rowHeader = new HtmlRow.Builder()
				.appendAttribute("class", styleParameter.getRowClass())
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", styleParameter.getHeaderCellClass())
						.appendAttribute("colspan", String.valueOf(1 + probes.size()))
						.appendContent("Pechfreier Querschnitt")
						.build()
						.appendTag())
				.build();

		return rowHeader.appendTag();
	}

	@Override
	HtmlRow createRow()
	{
		return null;
	}

	@Override
	String createCellFrom(Probe probe)
	{
		return null;
	}

	@Override
	public String buildWithSamples()
	{
		return "";
	}

	@Override
	String createCellFrom(Sample sample)
	{
		return null;
	}
}
