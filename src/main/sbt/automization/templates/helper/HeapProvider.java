package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlText;
import sbt.automization.util.CheckDataAvailability;

import java.util.List;

public final class HeapProvider extends RowProvider
{
	public HeapProvider() {super("HAUFWERK");}

	@Override
	public String createIDRow(List<DataTable> dataTables)
	{
		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Erkundungsstelle"})
		});

		for (DataTable table : dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{sample.get(SampleKey.PROBE_ID), UtilityPrinter.printLineBreak(), "(", sample.get(SampleKey.ID), ")"});

				row.appendContent(cell.appendTag());
			}
		}

		return row.appendTag();
	}

	public String createOutcropRow(List<DataTable> dataTables)
	{
		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Aufschlussart"})
		});

		for (DataTable table : dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{"Haufwerks-", UtilityPrinter.printLineBreak(), "beprobung"});

				row.appendContent(cell.appendTag());
			}
		}

		return row.appendTag();
	}

	public String createMaterialRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.WASTE_TYPE;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Material"})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{sample.get(tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createLegendRow(List<DataTable> dataTables)
	{
		int size = Integer.valueOf(headerCellWidth) + dataTables.size() * Integer.valueOf(normalCellWidth);

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("colspan", String.valueOf(1 + dataTables.size()))
						.appendAttribute("width", String.valueOf(size))
						.appendContent("Für die angegebenen Tiefen (T[]) gilt die Einheit cm.")
						.build()
						.appendTag())
				.build();

		return row.appendTag();
	}

	public String createDIN18300Row(List<DataTable> dataTables)
	{
		Key tag = SampleKey.SOIL_CLASS;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("DIN 18300<sup>[23]</sup>")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Bodenklasse,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{sample.get(tag)});

				row.appendContent(cell.appendTag());
			}
		}

		return row.appendTag();
	}

	public String createDIN18196Row(List<DataTable> dataTables)
	{
		Key tag = SampleKey.TYPE;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		//DIN18196
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("DIN 18196<sup>[22]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Bodengruppe,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{sample.get(tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createChemieIDRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.CHEMISTRY_ID;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Laborprobe"})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{new SamplePrinter().printAttributeOfDatatable(sample, tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createChemieMufvRow(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.MUFV;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Schreiben des MUFV<sup>[18]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Abgrenzung", UtilityPrinter.printLineBreak(), "Gefährlichkeit,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{new SamplePrinter().printAttributeOfDatatable(sample, tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createChemieLagaBoRow(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.LAGA_BO;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("LAGA Boden<sup>[11]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Zuordnungsklasse,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{new SamplePrinter().printAttributeOfDatatable(sample, tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createChemieLagaRcRow(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.LAGA_RC;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("LAGA Bauschutt<sup>[28]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Zuordnungsklasse,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{new SamplePrinter().printAttributeOfDatatable(sample, tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createChemieLagaRcOrientationRow(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.LAGA_RC_ORIENTATION;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("LAGA Bauschutt<sup>[28]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Orientierungswert,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{new SamplePrinter().printAttributeOfDatatable(sample, tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createChemieTlRockRow(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.TL_ROCK_STRATUM;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("TL Gestein<sup>[27]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Verwertungsklasse,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{new SamplePrinter().printAttributeOfDatatable(sample, tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createChemieDepvRow(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.DEPV;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("DepV<sup>[15]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Deponieklasse,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{new SamplePrinter().printAttributeOfDatatable(sample, tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createChemieDecisionSupportRow(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.DECISION_SUPPORT;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("DepV<sup>[17]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Entscheidungshilfe,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{new SamplePrinter().printAttributeOfDatatable(sample, tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createChemieAVVRow(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.WASTE_KEY;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("AVV<sup>[14]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Abfallschlüssel,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{new SamplePrinter().printAttributeOfDatatable(sample, tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	@Override
	public String createREKUROW(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.REKU;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Reku<sup>[7]</sup>")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Rekultivierung,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{new SamplePrinter().printAttributeOfDatatable(sample, tag)});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}
}
