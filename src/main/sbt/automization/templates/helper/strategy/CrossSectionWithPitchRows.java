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

public class CrossSectionWithPitchRows extends RowConstruction
{
	public CrossSectionWithPitchRows()
	{
		super(ProbeKey.ID);
	}

	@Override
	public String buildWithProbes()
	{
		StringBuilder crossSectionBuilder = new StringBuilder();

		boolean isThereDataToBuild = false;

		String crossSection = "Pechhaltiger Querschnitt";
		String size = "-";
		String mufv = "gefährlich";
		String ruva = "B";
		String avv = "17 03 01*";

		//Pechfreier Querschnitt Trennzeile
		HtmlRow rowHeader = new HtmlRow.Builder()
				.appendAttribute("class", styleParameter.getRowClass())
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", styleParameter.getHeaderCellClass())
						.appendAttribute("colspan", String.valueOf(1 + probes.size()))
						.appendContent(crossSection)
						.build()
						.appendTag())
				.build();

		HtmlRow rowPitchSize = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Dicke,", formatUnit("cm")})});

		HtmlRow rowPitchMufv = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Abgrenzung", UtilityPrinter.printLineBreak(), "Gefährlichkeit,", formatUnit("Schreiben des MUFV<sup>[11]</sup>")})});

		HtmlRow rowPitchRuva = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Verwertungsklasse,", formatUnit("RuVA<sup>[6]</sup>")})});

		HtmlRow rowPitchAvv = HtmlFactory.createRow(styleParameter.getRowClass(), new HtmlCell[]{
				HtmlFactory.createCell(styleParameter.getHeaderCellClass(), styleParameter.getHeaderCellWidth(),
						new String[]{"Abfallschlüssel,", formatUnit("AVV<sup>[14]</sup>")})});

		for (DataTable dataTable :
				probes)
		{
			boolean empty = true;

			List<Sample> samples = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop);

			if (samples != null)
			{
				double depth = 0;

				//TODO
				//Wie viele Schichten haben Pech und wie viele haben kein Pech
				//Dicke anpassen
				//Wenn eine Erkundungsstelle nicht keine pitch freien / haltigen Schichten hat, dann "-"

				for (Sample sample : samples)
				{
					Double thicknessOfSample = sample.getAsDouble(SampleKey.THICKNESS);

					String isPitch = sample.get(SampleKey.PITCH);

					if ("JA".equalsIgnoreCase(isPitch))
					{
						//Zähle Dicke der pechhaltigen Schichten
						depth += thicknessOfSample;
					}
				}

				if (depth > 0)
				{
					size = String.valueOf(depth).replace(".", ",");
					empty = false;
					isThereDataToBuild = true;
				}
			}

			if (empty)
			{
				size = "-";
				mufv = "-";
				ruva = "-";
				avv = "-";
			}

			//DICKE
			HtmlCell cellPitchSize = HtmlFactory.createCell(styleParameter.getNormalCellClass(), styleParameter.getNormalCellWidth(),
					new String[]{size});
			rowPitchSize.appendContent(cellPitchSize.appendTag());

			rowPitchMufv.appendContent(HtmlFactory.createChemistryCell(mufv));

			//RUVA
			HtmlCell cellPitchRuva = HtmlFactory.createCell(styleParameter.getNormalCellClass(), styleParameter.getNormalCellWidth(),
					new String[]{ruva});
			rowPitchRuva.appendContent(cellPitchRuva.appendTag());

			//AVV
			HtmlCell cellPitchAvv = HtmlFactory.createCell(styleParameter.getNormalCellClass(), styleParameter.getNormalCellWidth(),
					new String[]{avv});
			rowPitchAvv.appendContent(cellPitchAvv.appendTag());
		}


		if (isThereDataToBuild)
		{
			crossSectionBuilder.append(rowHeader.appendTag())
					.append(rowPitchSize.appendTag())
					.append(rowPitchMufv.appendTag())
					.append(rowPitchRuva.appendTag())
					.append(rowPitchAvv.appendTag());
		}

		return crossSectionBuilder.toString();
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
