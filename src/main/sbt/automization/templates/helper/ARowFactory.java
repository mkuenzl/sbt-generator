package sbt.automization.templates.helper;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.InformationTag;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.Util;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

/**
 * Factory Class TODO
 */
public abstract class ARowFactory
{
	protected static final String rowClass = "Normal";
	protected static final String legendCellClass = "NormalHeaderSmallFont";
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

	public String createIDRow(List<ExplorationSite> explorationSites)
	{
		//Erkundungsstellen ID
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Erkundungsstelle")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell htmlCell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(InformationTag.SITE_ID))
					.build();

			row.appendContent(htmlCell.appendTag());
		}

		return row.appendTag();
	}

	public String createSizeRow(List<ExplorationSite> explorationSites)
	{
		HtmlRow row = new HtmlRow.Builder()
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

		for (ExplorationSite explorationSite : explorationSites)
		{
			String size = TextFormatUtil.formatSiteOutcropThickness(explorationSite, outcrop);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(size)
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();

	}

	public String createLoadClassRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.SITE_LOAD_CLASS;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "", tag)) return "";

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

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(tag))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();

	}

	abstract public String createLegendRow(List<ExplorationSite> explorationSites);

	/**
	 * EXPERIMENT ROWS
	 */

	public String createEvDynRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.SITE_LP_EV;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "", tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("E<sub>Vdyn</sub>,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("MN/m²")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();


		for (ExplorationSite explorationSite :
				explorationSites)
		{

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(tag))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createEvDyn85Row(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.SITE_LP_EV85;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "", tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("E<sub>Vdyn (-15%)</sub>,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("MN/m²")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();


		for (ExplorationSite explorationSite :
				explorationSites)
		{

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(tag))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createEv2Row(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.SITE_LP_EV2;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "", tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("E<sub>V2</sub><sup>[41]</sup>,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("MN/m²")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();


		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.formatLP(explorationSite.getInformation(tag),
							explorationSite.getInformation(InformationTag.SITE_LP_EV85)))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createEvMinimumBorderRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.SITE_LP_EV2_EXPECTED;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "", tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Soll Wert,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("E<sub>V2</sub>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(tag))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createCompressiveStrengthRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_COMPRESSIVE_STRENGTH;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//Druckfestigkeit
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Druckfestigkeit,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("N/mm²")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();

	}

	public String createGrainSizeDistributionRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_GRAIN_SIZE_DISTRIBUTION;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//Korngroeßenverteilungen
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Korngrößenverteilung,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("Kornanteil < 0,063 mm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();

	}

	public String createWaterContentRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_WATER_CONTENT;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//WASSERGEHALT
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Wassergehalt,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("M.-%")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createMoistureRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_MOISTURE;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//FEUCHTEZUSTAND
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Feuchtezustand")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createConsistencyRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_CONSISTENCY;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//KONSISTENZ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Konsistenz")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createWaterProctorRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_WATER_PROCTOR;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//PROCTORDICHTE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Proctordichte")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("Mg/m³")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	@Deprecated
	public String createProctorDifferenceRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_COLOR;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "", tag)) return "";

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

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, "SCHICHT_DIFFERENZ_WN_WOPT"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createCompressibilityRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_COMPRESSIBILITY;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//VERDICHTUNGSFÄHIGKEIT
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Verdichtungsfähigkeit")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createWearPlanumRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.SITE_WEAR_PLANUM;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "", tag)) return "";

		//TRAGFAEHIGKEIT_PLANUM
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Tragfähigkeit")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("Planum")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("Soll: E<sub>V2</sub> >= 45 MN/m²")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("Ansatz Planum: FOK -60cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createWearSoleRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.SITE_WEAR_TRENCH_BOTTOM;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, "", tag)) return "";

		//TRAGFAEHIGKEIT_GRABENSOHLE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Tragfähigkeit")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("Grabensohle")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("Ansatz Sohle")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(explorationSite.getInformation(tag))
					.appendContent(TextFormatUtil.printLineEmpty())
					.appendContent(new HtmlText.Builder().appendAttribute("class", unitCellClass)
							.appendContent("[T:")
							.appendContent(explorationSite.getInformation(InformationTag.SITE_SOLE_DEPTH))
							.appendContent("]").build().appendTag())
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createZTVRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_FROST_SENSITIVITY_CLASS;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//ZTV-E
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Frostempfindlichkeits-")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("klasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("ZTV E<sup>[2]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	/**
	 * CHEMISTRY ROWS
	 */

	public String createChemieIDRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.CHEMISTRY_ID;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Laborprobe")
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();

	}

	public String createChemieMufvRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.CHEMISTRY_MUFV;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Abgrenzung")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("Gefährlichkeit,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("Schreiben des MUFV<sup>[18]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createChemieLagaBoRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.CHEMISTRY_LAGA_BO;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Zuordnungsklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("LAGA Boden<sup>[11]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createChemieLagaRcRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.CHEMISTRY_LAGA_RC;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Zuordnungsklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("LAGA Bauschutt<sup>[28]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createChemieLagaRcOrientationRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.CHEMISTRY_LAGA_RC_ORIENTATION;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Orientierungswert,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("LAGA Bauschutt<sup>[28]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createChemieTlRockRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.CHEMISTRY_TL_ROCK_STRATUM;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Verwertungsklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("TL Gestein<sup>[27]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createChemieDepvRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.CHEMISTRY_DEPV;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Deponieklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("DepV<sup>[15]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createChemieDecisionSupportRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.CHEMISTRY_DECISION_SUPPORT;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Entscheidungshilfe,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("DepV<sup>[17]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createAVVRow(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.CHEMISTRY_WASTE_KEY;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
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

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createREKUROW(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.CHEMISTRY_REKU;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Rekultivierung,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("Reku<sup>[7]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite :
				explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	/**
	 * DIN
	 */

	public String createDIN18196Row(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_TYPE;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//DIN18196
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Bodengruppe,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("DIN 18196<sup>[22]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createDIN18915Row(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_SOIL_CLASS;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//DIN18915
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Bodengruppe,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("DIN 18915<sup>[37]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createDIN18300Row(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_SOIL_CLASS;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//DIN18300
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Bodenklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("DIN 18300<sup>[23]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createDIN18320Row(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_HOMOGENEOUS_RANGE;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//DIN18320:2019-09
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Homogenbereich,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("DIN 18320:2019-09<sup>[36]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}
		return row.appendTag();
	}

	public String createDIN19682Row(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_SOIL_TYPE;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//DIN19682
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Bodenarten-")
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent("hauptgruppe,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("DIN 19682-2<sup>[24]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public String createDIN18300_09Row(List<ExplorationSite> explorationSites)
	{
		InformationTag tag = InformationTag.LAYER_HOMOGENEOUS_RANGE;

		if (!Util.thereExistsAnExplorationSiteWithData(explorationSites, outcrop, tag)) return "";

		//DIN18300:2019-09
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", rowClass)
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", headerCellClass)
						.appendAttribute("width", headerCellWidth)
						.appendContent("Homogenbereich,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", unitCellClass)
								.appendContent("DIN 18300:2019-09<sup>[34]</sup>")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (ExplorationSite explorationSite : explorationSites)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", normalCellClass)
					.appendAttribute("width", normalCellWidth)
					.appendContent(TextFormatUtil.printLayerInformationWithDepth(explorationSite, outcrop, tag))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}
}
