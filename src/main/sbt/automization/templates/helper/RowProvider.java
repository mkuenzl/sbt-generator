package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Sample;
import sbt.automization.data.references.*;
import sbt.automization.format.TextFormatUtil;
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
	protected static final String headerCellClass = "NormalHeader";
	protected static final String normalCellClass = "NormalBold";
	protected static final String unitCellClass = "Normal6";
	protected static final String normalCellWidth = "60";
	protected static final String headerCellWidth = "110";
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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{table.get(RefProbe.ID)});

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{dataTable.get(RefProbe.OUTCROP_UG_OH_BA)});

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{dataTable.get(RefProbe.OUTCROP_GOB)});

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{dataTable.get(RefProbe.OUTCROP_TOB)});

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
			List<Sample> samples = dataTable.getSamplesBy(RefSample.OUTCROP, outcrop);
			String size = TextFormatUtil.printThicknessOfSamples(samples);

			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{size});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();

	}

	public String createLoadClassRow(List<DataTable> dataTables)
	{
		Reference tag = RefProbe.LOAD_CLASS;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
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
		Reference tag = RefLP.EV;

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
			String parameterValue = dataTable.getParameterValueBy(RefProbe.LP_ID, RefLP.EV85);

			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{parameterValue});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createEvDyn85Row(List<DataTable> dataTables)
	{
		Reference tag = RefLP.EV85;

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
			String parameterValue = dataTable.getParameterValueBy(RefProbe.LP_ID, RefLP.EV85);

			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{parameterValue});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createEv2Row(List<DataTable> dataTables)
	{
		Reference tag = RefLP.EV2;

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
			String parameterEV2 = dataTable.getParameterValueBy(RefProbe.LP_ID, RefLP.EV2);
			String parameterEV85 = dataTable.getParameterValueBy(RefProbe.LP_ID, RefLP.EV85);

			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.formatLP(parameterEV2, parameterEV85)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createEvMinimumBorderRow(List<DataTable> dataTables)
	{
		Reference tag = RefLP.EV2_TARGET;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{table.getParameterValueBy(RefProbe.LP_ID, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createCompressiveStrengthRow(List<DataTable> dataTables)
	{
		Reference tag = RefSample.COMPRESSIVE_STRENGTH;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createGrainSizeDistributionRow(List<DataTable> dataTables)
	{
		Reference tag = RefSample.GRAIN_SIZE_DISTRIBUTION;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createWaterContentRow(List<DataTable> dataTables)
	{
		Reference tag = RefSample.WATER_CONTENT;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createMoistureRow(List<DataTable> dataTables)
	{
		Reference tag = RefSample.MOISTURE;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Feuchtezustand"})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createConsistencyRow(List<DataTable> dataTables)
	{
		Reference tag = RefSample.CONSISTENCY;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Konsistenz"})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createWaterProctorRow(List<DataTable> dataTables)
	{
		Reference tag = RefSample.WATER_PROCTOR;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	@Deprecated
	public String createProctorDifferenceRow(List<DataTable> dataTables)
	{
		Reference tag = RefSample.COLOR;

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
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createCompressibilityRow(List<DataTable> dataTables)
	{
		Reference tag = RefSample.COMPRESSIBILITY;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Verdichtungsfähigkeit"})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createWearPlanumRow(List<DataTable> dataTables)
	{
		Reference tag = RefProbe.WEAR_PLANUM;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Soll: E<sub>V2</sub> >= 45 MN/m²")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("Ansatz Planum: FOK -60cm")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Tragfähigkeit", TextFormatUtil.printLineBreak(), "Planum", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{table.get(tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();


	}

	public String createWearSoleRow(List<DataTable> dataTables)
	{
		Reference tag = RefProbe.WEAR_TRENCH_BOTTOM;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedHeaderText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Ansatz Sohle")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Tragfähigkeit", TextFormatUtil.printLineBreak(), "Grabensohle", formattedHeaderText})
		});

		for (DataTable table :
				dataTables)
		{
			String formattedCellText = new HtmlText.Builder().appendAttribute("class", unitCellClass)
					.appendContent("[T:")
					.appendContent(table.get(RefProbe.SOLE_DEPTH))
					.appendContent("]").build().appendTag();

			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{table.get(tag), TextFormatUtil.printLineEmpty(), formattedCellText});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createZTVRow(List<DataTable> dataTables)
	{
		Reference tag = RefSample.FROST_SENSITIVITY_CLASS;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("ZTV E<sup>[2]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Frostempfindlichkeits-", TextFormatUtil.printLineBreak(), "klasse,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	/**
	 * CHEMISTRY ROWS
	 */

	public String createChemieIDRow(List<DataTable> dataTables)
	{
		Reference tag = RefSample.CHEMISTRY_ID;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Laborprobe"})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createChemieMufvRow(List<DataTable> dataTables)
	{
		Reference tag = RefChemistry.MUFV;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("Schreiben des MUFV<sup>[18]</sup>")
				.build()
				.appendTag();

		//Erkundungsstellen ID
		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Abgrenzung", TextFormatUtil.printLineBreak(), "Gefährlichkeit,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createChemieLagaBoRow(List<DataTable> dataTables)
	{
		Reference tag = RefChemistry.LAGA_BO;

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
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createChemieLagaRcRow(List<DataTable> dataTables)
	{
		Reference tag = RefChemistry.LAGA_RC;

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
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createChemieLagaRcOrientationRow(List<DataTable> dataTables)
	{
		Reference tag = RefChemistry.LAGA_RC_ORIENTATION;

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
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createChemieTlRockRow(List<DataTable> dataTables)
	{
		Reference tag = RefChemistry.TL_ROCK_STRATUM;

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
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createChemieDepvRow(List<DataTable> dataTables)
	{
		Reference tag = RefChemistry.DEPV;

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
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createChemieDecisionSupportRow(List<DataTable> dataTables)
	{
		Reference tag = RefChemistry.DECISION_SUPPORT;

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
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createChemieAVVRow(List<DataTable> dataTables)
	{
		Reference tag = RefChemistry.WASTE_KEY;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createREKUROW(List<DataTable> dataTables)
	{
		Reference tag = RefChemistry.REKU;

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
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	/**
	 * DIN
	 */

	public String createDIN18196Row(List<DataTable> dataTables)
	{
		Reference tag = RefSample.TYPE;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createDIN18915Row(List<DataTable> dataTables)
	{
		Reference tag = RefSample.SOIL_CLASS;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createDIN18300Row(List<DataTable> dataTables)
	{
		Reference tag = RefSample.SOIL_CLASS;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createDIN18320Row(List<DataTable> dataTables)
	{
		Reference tag = RefSample.HOMOGENEOUS_RANGE;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createDIN19682Row(List<DataTable> dataTables)
	{
		Reference tag = RefSample.SOIL_TYPE;

		if (! CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";

		String formattedUnitText = new HtmlText.Builder()
				.appendAttribute("class", unitCellClass)
				.appendContent("DIN 19682-2<sup>[24]</sup>")
				.build()
				.appendTag();

		HtmlRow row = HtmlFactory.createRow(rowClass, new HtmlCell[]{
				HtmlFactory.createCell(headerCellClass, "width:" + headerCellWidth,
						new String[]{"Bodenarten-", TextFormatUtil.printLineBreak(), "hauptgruppe,", formattedUnitText})
		});

		for (DataTable table :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createDIN18300_09Row(List<DataTable> dataTables)
	{
		Reference tag = RefSample.HOMOGENEOUS_RANGE;

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
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{TextFormatUtil.printLayerInformationWithDepth(table, outcrop, tag)});

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}
}
