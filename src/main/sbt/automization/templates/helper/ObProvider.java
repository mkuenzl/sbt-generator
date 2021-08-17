package sbt.automization.templates.helper;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.RefProbe;
import sbt.automization.data.refactoring.references.RefSample;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.templates.Outcrop;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

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
						.appendContent(TextFormatUtil.printLineBreak())
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
			List<Sample> samples = dataTable.getSamplesBy(RefSample.OUTCROP, new String[]{outcrop,
					Outcrop.CONCRETE.toString(),
					Outcrop.COATING.toString(),
					Outcrop.TMHB.toString(),
					Outcrop.SEAL.toString()});

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printThicknessOfSamples(samples))
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
						.appendContent(TextFormatUtil.printLineBreak())
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
					.appendContent(TextFormatUtil.printRukLayers(dataTable, outcrop))
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
						.appendContent(TextFormatUtil.printLineBreak())
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
					.appendContent(dataTable.get(RefProbe.PITCH_QUALITATIVE))
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
						.appendContent(TextFormatUtil.printLineBreak())
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
					.appendContent(dataTable.get(RefProbe.PITCH_HALF_QUANTITATIVE))
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
						.appendContent(TextFormatUtil.printLineBreak())
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
					.appendContent(dataTable.get(RefProbe.PITCH_QUANTITATIVE))
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
						.appendContent(TextFormatUtil.printLineBreak())
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

			List<Sample> samples = dataTable.getSamplesBy(RefSample.OUTCROP, outcrop);

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
					String layerSize = sample.get(RefSample.THICKNESS);
					layerSize = layerSize.replace(",", ".");

					String layerPitch = sample.get(RefSample.PITCH);

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

			//MUFV
			HtmlCell cellPitchMufv = HtmlCellFormatUtil.formatChemistry(mufv);

			rowPitchMufv.appendContent(cellPitchMufv.appendTag());

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
