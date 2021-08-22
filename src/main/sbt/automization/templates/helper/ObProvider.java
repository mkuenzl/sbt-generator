package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.TextFormatterMulti;
import sbt.automization.data.Outcrop;
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
		int size = Integer.valueOf(headerCellWidth) + dataTables.size()* Integer.valueOf(normalCellWidth);

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
						.appendContent(TextFormatterMulti.printLineBreak())
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
					.appendContent(TextFormatterMulti.printThicknessOfSamples(samples))
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
						.appendContent(TextFormatterMulti.printLineBreak())
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
					.appendContent(TextFormatterMulti.printRukLayers(dataTable, outcrop))
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

	public String createPechQualitativRow(List<DataTable> dataTables)
	{
		//Pechnachweis qualitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Pechnachweis")
						.appendContent(TextFormatterMulti.printLineBreak())
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
					.appendContent(dataTable.get(ProbeKey.PITCH_QUALITATIVE))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createPechHalbQuantitativRow(List<DataTable> dataTables)
	{
		//Pechnachweis quantitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", "100")
						.appendContent("Pechnachweis")
						.appendContent(TextFormatterMulti.printLineBreak())
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
					.appendContent(dataTable.get(ProbeKey.PITCH_HALF_QUANTITATIVE))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createPechQuantitativRow(List<DataTable> dataTables)
	{
		//Pechnachweis quantitativ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Pechnachweis")
						.appendContent(TextFormatterMulti.printLineBreak())
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
					.appendContent(dataTable.get(ProbeKey.PITCH_QUANTITATIVE))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createPechQuerschnittRows(List<DataTable> dataTables, boolean pitch)
	{
		StringBuilder querschnittBuilder = new StringBuilder();

		boolean isThereDataToBuild = false;

		String querschnitt = pitch ? "Pechhaltiger Querschnitt" : "Pechfreier Querschnitt";
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
						.appendContent(querschnitt)
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

		//MUFV Querschnitt
		HtmlRow rowPitchMufv = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Abgrenzung")
						.appendContent(TextFormatterMulti.printLineBreak())
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
				double d = 0;

				//TODO
				//Wie viele Schichten haben Pech und wie viele haben kein Pech
				//Dicke anpassen
				//Wenn eine Erkundungsstelle nicht keine pitch freien / haltigen Schichten hat, dann "-"

				for (Sample sample : samples)
				{
					//TODO CHANGE TO TRUE & FALSE PECH
					String layerSize = sample.get(SampleKey.THICKNESS);
					layerSize = layerSize.replace(",", ".");

					String layerPitch = sample.get(SampleKey.PITCH);

					if (pitch && "JA".equalsIgnoreCase(layerPitch))
					{
						//Zähle Dicke der pechhaltigen Schichten
						d += Double.parseDouble(layerSize);
					}
					if (! pitch && "NEIN".equalsIgnoreCase(layerPitch))
					{
						//Zähle Dicke der pechfreien Schichten
						d += Double.parseDouble(layerSize);
					}
				}

				if (d > 0)
				{
					size = String.valueOf(d).replace(".", ",");
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
			querschnittBuilder.append(rowHeader.appendTag())
					.append(rowPitchSize.appendTag())
					.append(rowPitchMufv.appendTag())
					.append(rowPitchRuva.appendTag())
					.append(rowPitchAvv.appendTag());
		}

		return querschnittBuilder.toString();
	}
}
