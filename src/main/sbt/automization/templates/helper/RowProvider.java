package sbt.automization.templates.helper;

import org.w3c.dom.Text;
import sbt.automization.data.DataTable;
import sbt.automization.data.Sample;
import sbt.automization.data.key.*;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.format.text.LoadPlateTextFormatter;
import sbt.automization.format.text.StandardCellTextFormatter;
import sbt.automization.format.text.TextFormatter;
import sbt.automization.util.CheckDataAvailability;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlText;

import java.util.List;

/**
 * Factory Class TODO
 */
public abstract class RowProvider
{
	protected static final String rowClass = "Normal";
	protected static final String legendCellClass = "NormalHeaderSmallFont";
	protected static final String headerCellClass = "NormalHeader";
	protected static final String normalCellClass = "NormalBold";
	protected static final String unitCellClass = "Normal6";
	protected static final String normalCellWidth = "60";
	protected static final String headerCellWidth = "110";
	protected static final TextFormatter textFormatter = new StandardCellTextFormatter();
	protected String outcrop;

	public RowProvider(String outcrop)
	{
		this.outcrop = outcrop;
	}

	public String createIDRow(List<DataTable> dataTables)
	{
		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Erkundungsstelle"})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{table.get(ProbeKey.ID)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createGroundExposureRow(List<DataTable> dataTables)
	{
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Aufschlussart"})
		});

		for (DataTable dataTable :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{dataTable.get(ProbeKey.OUTCROP_UG_OH_BA)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createSuperstructureExposureRow(List<DataTable> dataTables)
	{
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Aufschlussart"})
		});

		for (DataTable dataTable :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{dataTable.get(ProbeKey.OUTCROP_GOB)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createBaseCourseExposureRow(List<DataTable> dataTables)
	{
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Aufschlussart"})
		});

		for (DataTable dataTable :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{dataTable.get(ProbeKey.OUTCROP_TOB)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createSizeRow(List<DataTable> dataTables)
	{
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("cm")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Dicke,", formattedUnitText})
		});

		for (DataTable dataTable :
				dataTables)
		{
			List<Sample> samples = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop);
			String size = new SamplePrinter().printThickness(samples);

			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{size});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();

	}

	public String createLoadClassRow(List<DataTable> dataTables)
	{
		Key tag = ProbeKey.LOAD_CLASS;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", tag)) return "";

		//Belastungklasse
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("RStO<sup>[5]</sup>")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Belastungklasse,", formattedUnitText})
		});

		for (DataTable dataTable : dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{dataTable.get(tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();

	}

	abstract public String createLegendRow(List<DataTable> dataTables);

	/**
	 * EXPERIMENT ROWS
	 */

	public String createEvDynRow(List<DataTable> dataTables)
	{
		Key tag = LpKey.EV;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("MN/m²")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"E<sub>Vdyn</sub>,", formattedUnitText})
		});

		for (DataTable dataTable :
				dataTables)
		{
			String parameterValue = dataTable.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV85);

			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{parameterValue});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createEvDyn85Row(List<DataTable> dataTables)
	{
		Key tag = LpKey.EV85;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("MN/m²")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"E<sub>Vdyn (-15%)</sub>,", formattedUnitText})
		});

		for (DataTable dataTable :
				dataTables)
		{
			String parameterValue = dataTable.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV85);

			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{parameterValue});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createEv2Row(List<DataTable> dataTables)
	{
		Key tag = LpKey.EV2;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("MN/m²")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"E<sub>V2</sub><sup>[41]</sup>,", formattedUnitText})
		});

		for (DataTable dataTable :
				dataTables)
		{
			String parameterEV2 = dataTable.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV2);
			String parameterEV85 = dataTable.getParameterValueBy(ProbeKey.LP_ID, LpKey.EV85);

			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new LoadPlateTextFormatter().format(parameterEV2, parameterEV85)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createEvMinimumBorderRow(List<DataTable> dataTables)
	{
		Key tag = LpKey.EV2_TARGET;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("E<sub>V2</sub>")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Soll Wert,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{table.getParameterValueBy(ProbeKey.LP_ID, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createCompressiveStrengthRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.COMPRESSIVE_STRENGTH;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("N/mm²")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Druckfestigkeit,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createGrainSizeDistributionRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.GRAIN_SIZE_DISTRIBUTION;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Kornanteil < 0,063 mm")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Korngrößenverteilung,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createWaterContentRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.WATER_CONTENT;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		//WASSERGEHALT
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("M.-%")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Wassergehalt,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createMoistureRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.MOISTURE;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Feuchtezustand"})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createConsistencyRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.CONSISTENCY;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Konsistenz"})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createWaterProctorRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.WATER_PROCTOR;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		//PROCTORDICHTE
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Mg/m³")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Proctordichte", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	@Deprecated
	public String createProctorDifferenceRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.COLOR;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", tag)) return "";

		//DIFFERENCE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Differenz W<sub>n</sub> - W<sub>opt</sub>")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("M.-%")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (DataTable dataTable : dataTables)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(new SamplePrinter().printAttributeOfSamplesWithDepth(dataTable, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createCompressibilityRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.COMPRESSIBILITY;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Verdichtungsfähigkeit"})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createWearPlanumRow(List<DataTable> dataTables)
	{
		Key tag = ProbeKey.WEAR_PLANUM;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Soll: E<sub>V2</sub> >= 45 MN/m²")
				.appendContent(UtilityPrinter.printLineBreak())
				.appendContent("Ansatz Planum: FOK -60cm")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Tragfähigkeit", UtilityPrinter.printLineBreak(), "Planum", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{table.get(tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();


	}

	public String createWearSoleRow(List<DataTable> dataTables)
	{
		Key tag = ProbeKey.WEAR_TRENCH_BOTTOM;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedHeaderText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Ansatz Sohle")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Tragfähigkeit", UtilityPrinter.printLineBreak(), "Grabensohle", formattedHeaderText})
		});

		for (DataTable table :
				dataTables)
		{
			String formattedCellText = new HtmlText.Builder().appendAttribute("class", unitCellClass)
					.appendContent("[T:")
					.appendContent(table.get(ProbeKey.SOLE_DEPTH))
					.appendContent("]").build().appendTag();

			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{table.get(tag), UtilityPrinter.printLineEmpty(), formattedCellText});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createZTVRow(List<DataTable> dataTables)
	{
		Key tag = SampleKey.FROST_SENSITIVITY_CLASS;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("ZTV E<sup>[2]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Frostempfindlichkeits-", UtilityPrinter.printLineBreak(), "klasse,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	/**
	 * CHEMISTRY ROWS
	 */

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
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

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
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	/**
	 * DIN
	 */

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
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createDIN18915Row(List<DataTable> dataTables)
	{
		Key tag = SampleKey.SOIL_CLASS;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		//DIN18320:2019-09
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("DIN 18915<sup>[37]</sup>")
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
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

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
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createDIN18320Row(List<DataTable> dataTables)
	{
		Key tag = SampleKey.HOMOGENEOUS_RANGE;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		//DIN18320:2019-09
		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("DIN 18320:2019-09<sup>[36]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Homogenbereich,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createDIN19682Row(List<DataTable> dataTables)
	{
		Key tag = SampleKey.SOIL_TYPE;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("DIN 19682-2<sup>[24]</sup>")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Bodenarten-", UtilityPrinter.printLineBreak(), "hauptgruppe,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createDIN18300_09Row(List<DataTable> dataTables)
	{
		Key tag = SampleKey.HOMOGENEOUS_RANGE;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("DIN 18320:2019-09<sup>[34]</sup>")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Homogenbereich,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createChemistryRuva(List<DataTable> dataTables)
	{
		Key tag = ChemistryKey.RUVA;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("RuVa-StB")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Verwertungsklasse,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

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
			HtmlCell cell = HtmlFactory.createCell(textFormatter, normalCellClass, "width:" + normalCellWidth,
					new String[]{new SamplePrinter().printAttributeOfSamplesWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}
}
