package sbt.automization.templates.helper;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.RefProbe;
import sbt.automization.data.refactoring.references.RefSample;
import sbt.automization.data.refactoring.references.Reference;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.Util;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

/**
 * Factory Class TODO
 */
public abstract class ARowFactory
{
	protected static final String rowClass = "Normal";
	protected static final String headerCellClass = "NormalHeader";
	protected static final String normalCellClass = "NormalBold";
	protected static final String unitCellClass = "Normal6";
	protected static final String normalCellWidth = "60";
	protected static final String headerCellWidth = "110";
	protected String outcrop;

	public ARowFactory(String outcrop)
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

		for (DataTable dataTable :
				dataTables)
		{
			HtmlCell cell = HtmlFactory.createCell(normalCellClass, "width:" + normalCellWidth,
					new String[]{dataTable.get(RefProbe.ID)});

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
			Probe probe = (Probe) dataTable;
			List<Sample> samples = probe.getSamplesBy(RefSample.OUTCROP, outcrop);
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

		if (!Util.thereExistsAnTableWithData(dataTables, "", tag)) return "";

		//Belastungklasse
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Belastungklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("RStO<sup>[5]</sup>")
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
					.appendContent(dataTable.get(tag))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();

	}

	abstract public String createLegendRow(List<DataTable> dataTables);

	/**
	 * EXPERIMENT ROWS
	 */

//	public String createEvDynRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.SITE_LP_EV;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, "", tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("E<sub>Vdyn</sub>,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("MN/m²")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//
//		for (DataTable dataTable :
//				dataTables)
//		{
//
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(dataTable.getInformation(tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//		return row.appendTag();
//	}
//
//	public String createEvDyn85Row(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.SITE_LP_EV85;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, "", tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("E<sub>Vdyn (-15%)</sub>,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("MN/m²")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//
//		for (DataTable dataTable :
//				dataTables)
//		{
//
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(dataTable.getInformation(tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//		return row.appendTag();
//	}
//
//	public String createEv2Row(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.SITE_LP_EV2;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, "", tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("E<sub>V2</sub><sup>[41]</sup>,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("MN/m²")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//
//		for (DataTable dataTable :
//				dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.formatLP(dataTable.getInformation(tag),
//							dataTable.getInformation(ReferenceKey.SITE_LP_EV85)))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//		return row.appendTag();
//	}
//
//	public String createEvMinimumBorderRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.SITE_LP_EV2_EXPECTED;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, "", tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Soll Wert,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("E<sub>V2</sub>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable :
//				dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(dataTable.getInformation(tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//		return row.appendTag();
//	}
//
//	public String createCompressiveStrengthRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_COMPRESSIVE_STRENGTH;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//Druckfestigkeit
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Druckfestigkeit,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("N/mm²")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//		return row.appendTag();
//
//	}
//
//	public String createGrainSizeDistributionRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_GRAIN_SIZE_DISTRIBUTION;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//Korngroeßenverteilungen
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Korngrößenverteilung,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("Kornanteil < 0,063 mm")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//		return row.appendTag();
//
//	}
//
//	public String createWaterContentRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_WATER_CONTENT;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//WASSERGEHALT
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Wassergehalt,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("M.-%")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createMoistureRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_MOISTURE;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//FEUCHTEZUSTAND
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Feuchtezustand")
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createConsistencyRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_CONSISTENCY;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//KONSISTENZ
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Konsistenz")
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createWaterProctorRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_WATER_PROCTOR;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//PROCTORDICHTE
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Proctordichte")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("Mg/m³")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	@Deprecated
//	public String createProctorDifferenceRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_COLOR;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, "", tag)) return "";
//
//		//DIFFERENCE
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Differenz W<sub>n</sub> - W<sub>opt</sub>")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("M.-%")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, "SCHICHT_DIFFERENZ_WN_WOPT"))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createCompressibilityRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_COMPRESSIBILITY;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//VERDICHTUNGSFÄHIGKEIT
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Verdichtungsfähigkeit")
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createWearPlanumRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.SITE_WEAR_PLANUM;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, "", tag)) return "";
//
//		//TRAGFAEHIGKEIT_PLANUM
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Tragfähigkeit")
//						.appendContent(TextFormatUtil.printLineBreak())
//						.appendContent("Planum")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("Soll: E<sub>V2</sub> >= 45 MN/m²")
//								.build()
//								.appendTag())
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("Ansatz Planum: FOK -60cm")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(dataTable.getInformation(tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createWearSoleRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.SITE_WEAR_TRENCH_BOTTOM;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, "", tag)) return "";
//
//		//TRAGFAEHIGKEIT_GRABENSOHLE
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Tragfähigkeit")
//						.appendContent(TextFormatUtil.printLineBreak())
//						.appendContent("Grabensohle")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("Ansatz Sohle")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(dataTable.getInformation(tag))
//					.appendContent(TextFormatUtil.printLineEmpty())
//					.appendContent(new HtmlText.Builder().appendAttribute("class", unitCellClass)
//							.appendContent("[T:")
//							.appendContent(dataTable.getInformation(ReferenceKey.SITE_SOLE_DEPTH))
//							.appendContent("]").build().appendTag())
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createZTVRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_FROST_SENSITIVITY_CLASS;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//ZTV-E
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Frostempfindlichkeits-")
//						.appendContent(TextFormatUtil.printLineBreak())
//						.appendContent("klasse,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("ZTV E<sup>[2]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	/**
//	 * CHEMISTRY ROWS
//	 */
//
//	public String createChemieIDRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.CHEMISTRY_ID;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Laborprobe")
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//
//	}
//
//	public String createChemieMufvRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.CHEMISTRY_MUFV;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Abgrenzung")
//						.appendContent(TextFormatUtil.printLineBreak())
//						.appendContent("Gefährlichkeit,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("Schreiben des MUFV<sup>[18]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createChemieLagaBoRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.CHEMISTRY_LAGA_BO;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Zuordnungsklasse,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("LAGA Boden<sup>[11]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createChemieLagaRcRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.CHEMISTRY_LAGA_RC;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Zuordnungsklasse,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("LAGA Bauschutt<sup>[28]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createChemieLagaRcOrientationRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.CHEMISTRY_LAGA_RC_ORIENTATION;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Orientierungswert,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("LAGA Bauschutt<sup>[28]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createChemieTlRockRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.CHEMISTRY_TL_ROCK_STRATUM;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Verwertungsklasse,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("TL Gestein<sup>[27]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createChemieDepvRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.CHEMISTRY_DEPV;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Deponieklasse,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("DepV<sup>[15]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createChemieDecisionSupportRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.CHEMISTRY_DECISION_SUPPORT;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Entscheidungshilfe,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("DepV<sup>[17]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createAVVRow(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.CHEMISTRY_WASTE_KEY;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Abfallschlüssel,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("AVV<sup>[14]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createREKUROW(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.CHEMISTRY_REKU;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Rekultivierung,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("Reku<sup>[7]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable :
//				dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	/**
//	 * DIN
//	 */
//
//	public String createDIN18196Row(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_TYPE;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//DIN18196
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Bodengruppe,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("DIN 18196<sup>[22]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//		return row.appendTag();
//	}
//
//	public String createDIN18915Row(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_SOIL_CLASS;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//DIN18915
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Bodengruppe,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("DIN 18915<sup>[37]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//		return row.appendTag();
//	}
//
//	public String createDIN18300Row(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_SOIL_CLASS;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//DIN18300
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Bodenklasse,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("DIN 18300<sup>[23]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createDIN18320Row(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_HOMOGENEOUS_RANGE;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//DIN18320:2019-09
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Homogenbereich,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("DIN 18320:2019-09<sup>[36]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//		return row.appendTag();
//	}
//
//	public String createDIN19682Row(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_SOIL_TYPE;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//DIN19682
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Bodenarten-")
//						.appendContent(TextFormatUtil.printLineBreak())
//						.appendContent("hauptgruppe,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("DIN 19682-2<sup>[24]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
//
//	public String createDIN18300_09Row(List<DataTable> dataTables)
//	{
//		ReferenceKey tag = ReferenceKey.LAYER_HOMOGENEOUS_RANGE;
//
//		if (!Util.thereExistsAnTableWithData(dataTables, outcrop, tag)) return "";
//
//		//DIN18300:2019-09
//		HtmlRow row = new HtmlRow.Builder()
//				.appendAttribute("class", rowClass)
//				.appendContent(new HtmlCell.Builder()
//						.appendAttribute("class", headerCellClass)
//						.appendAttribute("width", headerCellWidth)
//						.appendContent("Homogenbereich,")
//						.appendContent(new HtmlText.Builder()
//								.appendAttribute("class", unitCellClass)
//								.appendContent("DIN 18300:2019-09<sup>[34]</sup>")
//								.build()
//								.appendTag())
//						.build()
//						.appendTag())
//				.build();
//
//		for (DataTable dataTable : dataTables)
//		{
//			HtmlCell cell = new HtmlCell.Builder()
//					.appendAttribute("class", normalCellClass)
//					.appendAttribute("width", normalCellWidth)
//					.appendContent(TextFormatUtil.printLayerInformationWithDepth(dataTable, outcrop, tag))
//					.build();
//
//			row.appendContent(cell.appendTag());
//		}
//
//		return row.appendTag();
//	}
}
