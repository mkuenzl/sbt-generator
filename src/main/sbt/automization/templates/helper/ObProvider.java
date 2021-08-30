package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.RuKPrinter;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlText;

import java.util.List;

public final class ObProvider extends RowProvider
{
	public ObProvider()
	{
		super("GOB");
	}

	@Override
	public String createLegendRow(List<DataTable> dataTables)
	{
		int size = Integer.valueOf(headerCellWidth) + dataTables.size() * Integer.valueOf(normalCellWidth);

		return null;
	}

	public String createSizeOBRow(List<DataTable> dataTables)
	{
		//Gesamtdicke Oberbau
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Gesamtdicke geb.")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("Oberbau,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (DataTable dataTable :
				dataTables)
		{
			List<Sample> samples = dataTable.getSamplesBy(SampleKey.OUTCROP, new String[]{outcrop,
					Outcrop.CONCRETE.toString(),
					Outcrop.COATING.toString(),
					Outcrop.TMHB.toString(),
					Outcrop.SEAL.toString()});

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(new SamplePrinter().printThickness(samples))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createRukRow(List<DataTable> dataTables)
	{
		//RUK
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Erweichungspunkt")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("RuK<sup>[31]</sup>,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("°C")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (DataTable dataTable :
				dataTables)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(new RuKPrinter().printRukLayers(dataTable, outcrop))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createRukSingleValueRow(List<DataTable> dataTables)
	{
		//RUK EinzelWert
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Soll Einzelwert,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("RuK")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (DataTable dataTable :
				dataTables)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent("77")
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createPitchQualitativeRow(List<DataTable> dataTables)
	{
		//Pechnachweis qualitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Pechnachweis")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("qualitativ")
						.build()
						.appendTag())
				.build();

		for (DataTable dataTable :
				dataTables)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(textFormatter.format(dataTable.get(ProbeKey.PITCH_QUALITATIVE)))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createPitchHalfQuantitativeRow(List<DataTable> dataTables)
	{
		//Pechnachweis quantitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Pechnachweis")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("halbquantitativ")
						.build()
						.appendTag())
				.build();

		for (DataTable dataTable :
				dataTables)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(textFormatter.format(dataTable.get(ProbeKey.PITCH_HALF_QUANTITATIVE)))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createPitchQuantitativeRow(List<DataTable> dataTables)
	{
		//Pechnachweis quantitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Pechnachweis")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("quantitativ")
						.build()
						.appendTag())
				.build();

		for (DataTable dataTable :
				dataTables)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(textFormatter.format(dataTable.get(ProbeKey.PITCH_QUANTITATIVE)))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createPitchCrossSectionRows(List<DataTable> dataTables, boolean pitch)
	{
		StringBuilder crossSectionBuilder = new StringBuilder();

		boolean isThereDataToBuild = false;

		String crossSection = pitch ? "Pechhaltiger Querschnitt" : "Pechfreier Querschnitt";
		String size = "-";
		String mufv = pitch ? "gefährlich" : "nicht gefährlich";
		String ruva = pitch ? "B" : "A";
		String avv = pitch ? "17 03 01*" : "17 03 02";

		//Pechfreier Querschnitt Trennzeile
		HtmlRow rowHeader = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("colspan", String.valueOf(1 + dataTables.size()))
						.appendContent(crossSection)
						.build()
						.appendTag())
				.build();

		//Dicke pechfreier Querschnitt
		HtmlRow rowPitchSize = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Dicke,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();


		HtmlRow rowPitchPaK = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"PAK,", new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("mg/kg")
								.build()
								.appendTag()})
		});

		//MUFV Querschnitt
		HtmlRow rowPitchMufv = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Abgrenzung")
						.appendContent(UtilityPrinter.printLineBreak())
						.appendContent("Gefährlichkeit,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("Schreiben des MUFV<sup>[11]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		//RUVA Querschnitt
		HtmlRow rowPitchRuva = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Verwertungsklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("RuVA<sup>[6]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		//AVV Querschnitt
		HtmlRow rowPitchAvv = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Abfallschlüssel,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("AVV<sup>[14]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (DataTable dataTable :
				dataTables)
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
					//TODO CHANGE TO TRUE & FALSE PECH
					Double thicknessOfSample = sample.getAsDouble(SampleKey.THICKNESS);

					String isPitch = sample.get(SampleKey.PITCH);

					if (pitch && "JA".equalsIgnoreCase(isPitch))
					{
						//Zähle Dicke der pechhaltigen Schichten
						depth += thicknessOfSample;
					}
					if (! pitch && "NEIN".equalsIgnoreCase(isPitch))
					{
						//Zähle Dicke der pechfreien Schichten
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
			HtmlCell cellPitchSize = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(size)
					.build();

			rowPitchSize.appendContent(cellPitchSize.appendTag());

			//TODO
			rowPitchPaK.appendContent(HtmlFactory.createCellAsString(normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(dataTable, outcrop, ChemistryKey.PAK)}));

			rowPitchMufv.appendContent(HtmlFactory.createChemistryCell(mufv));

			//RUVA
			HtmlCell cellPitchRuva = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(ruva)
					.build();

			rowPitchRuva.appendContent(cellPitchRuva.appendTag());

			//AVV
			HtmlCell cellPitchAvv = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(avv)
					.build();

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
}
