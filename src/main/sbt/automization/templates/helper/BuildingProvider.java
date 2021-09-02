package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlText;
import sbt.automization.util.CheckDataAvailability;

import java.util.List;

public final class BuildingProvider extends RowProvider
{
	public BuildingProvider()
	{
		super(Outcrop.BUILDING.toString());
		normalCellWidth = "2.4cm";
	}

	@Override
	public String createLegendRow(List<DataTable> dataTables)
	{
		return null;
	}

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
						new String[]{sample.get(SampleKey.PROBE_ID)});

				row.appendContent(cell.appendTag());
			}
		}

		return row.appendTag();
	}

	public String createComponentRow(List<DataTable> dataTables)
	{
		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Bauteil"})
		});

		for (DataTable table : dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{table.get(ProbeKey.COMPONENT)});

				row.appendContent(cell.appendTag());
			}
		}

		return row.appendTag();
	}

	public String createMaterialRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.MATERIAL;

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
						new String[]{textFormatter.format(sample.get(tag))});

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

	public String createSuspectedPollutantRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.SUSPECTED_POLLUTANT;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Schadstoffverdacht"})
		});

		for (DataTable table :
				dataTables)
		{
			for (Sample sample : table.getSamples())
			{
				HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
						new String[]{textFormatter.format(sample.get(tag))});

				row.appendContent(cell.appendTag());
			}
		}
		return row.appendTag();
	}

	public String createChemistryPCB(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.PCB;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("mg/kg")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"PCB,", formattedUnitText})
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

	public String createChemistryBTEX(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.BTEX;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("-")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"BTEX,", formattedUnitText})
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

	public String createChemistryEOX(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.EOX;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("-")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"EOX,", formattedUnitText})
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

	public String createChemistryPhenol(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.PHENOL;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("-")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Phenole,", formattedUnitText})
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

	public String createChemistryICP(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.ICP_SCREENING;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("-")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"ICP Screening,", formattedUnitText})
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

	public String createChemistrySulfate(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.SULFATE;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("-")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Sulfat,", formattedUnitText})
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

	public String createChemistryKMF(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.KMF;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Nachweisgrenze")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"KMF,", formattedUnitText})
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
	public String createChemistryPak(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.PAK;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("mg/kg")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"PAK,", formattedUnitText})
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

	public String createChemistryAsbestos(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.ASBESTOS;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Nachweisgrenze")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Asbest,", formattedUnitText})
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
				.appendContent("Einstufung")
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

	public String createChemieMufvParameterRow(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.MUFV_PARAMETER;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Parameter")
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
				.appendContent("LAGA Boden<sup>[4]</sup>")
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
				.appendContent("LAGA Bauschutt<sup>[15]</sup>")
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
				.appendContent("LAGA Bauschutt<sup>[15]</sup>")
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
				.appendContent("TL Gestein<sup>[14]</sup>")
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

	public String createChemieMaterialAVVRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.WASTE_KEY_MATERIAL;

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

	public String createChemieMixedAVVRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.WASTE_KEY_MIX;

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
	public String createChemistryReku(List<DataTable> dataTables)
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
