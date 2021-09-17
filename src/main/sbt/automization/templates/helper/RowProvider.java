package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Sample;
import sbt.automization.data.key.*;
import sbt.automization.format.printer.SamplePrinter;
import sbt.automization.format.printer.UtilityPrinter;
import sbt.automization.format.text.LoadPlateTextFormatter;
import sbt.automization.format.text.StandardCellTextFormatter;
import sbt.automization.format.text.TextFormatter;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlRow;
import sbt.automization.html.HtmlText;
import sbt.automization.styles.StyleParameter;
import sbt.automization.styles.StyleParameterBuilder;
import sbt.automization.templates.helper.strategy.*;

import java.util.List;

/**
 * Factory Class TODO
 */
public abstract class RowProvider
{
	protected String rowClass;
	protected String legendCellClass;
	protected String headerCellClass;
	protected String normalCellClass;
	protected String unitCellClass;
	protected String normalCellWidth;
	protected String headerCellWidth;
	protected TextFormatter textFormatter;
	protected String outcrop;
	protected StyleParameter styleParameter;

	public RowProvider(String outcrop)
	{
		this.outcrop = outcrop;
		this.rowClass = "Normal";
		this.legendCellClass = "NormalHeaderSmallFont";
		this.headerCellClass = "NormalHeader";
		this.normalCellClass = "NormalBold";
		this.unitCellClass = "Normal6";
		this.normalCellWidth = "60";
		this.headerCellWidth = "110";
		this.textFormatter = new StandardCellTextFormatter();
		this.styleParameter = new StyleParameterBuilder()
				.setRowClass("Normal")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("width:110")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("width:60")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build();
	}

	public String getRowWithProbes(RowConstructionStrategy rowConstructionStrategy)
	{
		rowConstructionStrategy.setStyle(styleParameter);

		return rowConstructionStrategy.buildWithProbes();
	}

	public String getRowWithSamples(RowConstructionStrategy rowConstructionStrategy)
	{
		rowConstructionStrategy.setStyle(styleParameter);

		return rowConstructionStrategy.buildWithSamples();
	}

	public String createIDRow(List<DataTable> dataTables)
	{
		IdRow idRow = new IdRow(dataTables, outcrop, ProbeKey.ID, styleParameter);

		return idRow.buildWithProbes();
	}

	public String createGroundExposureRow(List<DataTable> dataTables)
	{
		GroundExposureRow groundExposureRow = new GroundExposureRow(dataTables, outcrop, ProbeKey.OUTCROP_UG_OH_BA, styleParameter);

		return groundExposureRow.buildWithProbes();
	}

	public String createSuperstructureExposureRow(List<DataTable> dataTables)
	{
		SuperstructureExposureRow superstructureExposureRow = new SuperstructureExposureRow(dataTables, outcrop, ProbeKey.OUTCROP_GOB, styleParameter);

		return superstructureExposureRow.buildWithProbes();
	}

	public String createBaseCourseExposureRow(List<DataTable> dataTables)
	{
		BaseCourseExposureRow baseCourseExposureRow = new BaseCourseExposureRow(dataTables, outcrop, ProbeKey.OUTCROP_TOB, styleParameter);

		return baseCourseExposureRow.buildWithProbes();
	}

	public String createSizeRow(List<DataTable> dataTables)
	{
		SizeRow sizeRow = new SizeRow(dataTables, outcrop, SampleKey.THICKNESS, styleParameter);

		return sizeRow.buildWithProbes();
	}

	public String createLoadClassRow(List<DataTable> dataTables)
	{
		LoadClassRow loadClassRow = new LoadClassRow(dataTables, outcrop, ProbeKey.LOAD_CLASS, styleParameter);

		return loadClassRow.buildWithProbes();
	}

	abstract public String createLegendRow(List<DataTable> dataTables);

	/**
	 * EXPERIMENT ROWS
	 */

	public String createEvDynRow(List<DataTable> dataTables)
	{
		EvDynRow evDynRow = new EvDynRow(dataTables, outcrop, LpKey.EV, styleParameter);

		return evDynRow.buildWithProbes();
	}

	public String createEvDyn85Row(List<DataTable> dataTables)
	{
		EvDyn85Row evDyn85Row = new EvDyn85Row(dataTables, outcrop, LpKey.EV85, styleParameter);

		return evDyn85Row.buildWithProbes();
	}

	public String createEv2With85Row(List<DataTable> dataTables)
	{
		Ev2WithEv85Row ev2WithEv85Row = new Ev2WithEv85Row(dataTables, outcrop, LpKey.EV2, styleParameter);

		return ev2WithEv85Row.buildWithProbes();
	}

	public String createEvMinimumBorderRow(List<DataTable> dataTables)
	{
		EvMinimumBorderRow evMinimumBorderRow = new EvMinimumBorderRow(dataTables, outcrop, LpKey.EV2_TARGET, styleParameter);

		return evMinimumBorderRow.buildWithProbes();
	}

	public String createCompressiveStrengthRow(List<DataTable> dataTables)
	{
		CompressiveStrengthRow compressiveStrengthRow = new CompressiveStrengthRow(dataTables, outcrop, SampleKey.COMPRESSIVE_STRENGTH, styleParameter);

		return compressiveStrengthRow.buildWithProbes();
	}

	public String createGrainSizeDistributionRow(List<DataTable> dataTables)
	{
		GrainSizeDistributionRow grainSizeDistributionRow = new GrainSizeDistributionRow(dataTables, outcrop, SampleKey.GRAIN_SIZE_DISTRIBUTION, styleParameter);

		return grainSizeDistributionRow.buildWithProbes();
	}

	public String createWaterContentRow(List<DataTable> dataTables)
	{
		WaterContentRow waterContentRow = new WaterContentRow(dataTables, outcrop, SampleKey.GRAIN_SIZE_DISTRIBUTION, styleParameter);

		return waterContentRow.buildWithProbes();
	}

	public String createMoistureRow(List<DataTable> dataTables)
	{
		MoistureRow moistureRow = new MoistureRow(dataTables, outcrop, SampleKey.GRAIN_SIZE_DISTRIBUTION, styleParameter);

		return moistureRow.buildWithProbes();
	}

	public String createConsistencyRow(List<DataTable> dataTables)
	{
		ConsistencyRow consistencyRow = new ConsistencyRow(dataTables, outcrop, SampleKey.GRAIN_SIZE_DISTRIBUTION, styleParameter);

		return consistencyRow.buildWithProbes();
	}

	public String createWaterProctorRow(List<DataTable> dataTables)
	{
		WaterProctorRow waterProctorRow = new WaterProctorRow(dataTables, outcrop, SampleKey.GRAIN_SIZE_DISTRIBUTION, styleParameter);

		return waterProctorRow.buildWithProbes();
	}

	public String createCompressibilityRow(List<DataTable> dataTables)
	{
		CompressibilityRow compressibilityRow = new CompressibilityRow(dataTables, outcrop, SampleKey.GRAIN_SIZE_DISTRIBUTION, styleParameter);

		return compressibilityRow.buildWithProbes();
	}

	public String createWearPlanumRow(List<DataTable> dataTables)
	{
		WearPlanumRow wearPlanumRow = new WearPlanumRow(dataTables, outcrop, SampleKey.GRAIN_SIZE_DISTRIBUTION, styleParameter);

		return wearPlanumRow.buildWithProbes();
	}

	public String createWearSoleRow(List<DataTable> dataTables)
	{
		WearSoleRow wearSoleRow = new WearSoleRow(dataTables, outcrop, SampleKey.GRAIN_SIZE_DISTRIBUTION, styleParameter);

		return wearSoleRow.buildWithProbes();
	}

	public String createZTVRow(List<DataTable> dataTables)
	{
		ZTVRow ztvRow = new ZTVRow(dataTables, outcrop, SampleKey.GRAIN_SIZE_DISTRIBUTION, styleParameter);

		return ztvRow.buildWithProbes();
	}

	/**
	 * CHEMISTRY ROWS
	 */

	public String createChemistryIDRow(List<DataTable> dataTables)
	{
		ChemistryIdRow chemistryIdRow = new ChemistryIdRow(dataTables, outcrop, SampleKey.CHEMISTRY_ID, styleParameter);

		return chemistryIdRow.buildWithProbes();
	}

	public String createChemieMufvRow(List<DataTable> dataTables)
	{
		ChemistryMufvRow chemistryMufvRow = new ChemistryMufvRow(dataTables, outcrop, ChemistryKey.MUFV, styleParameter);

		return chemistryMufvRow.buildWithProbes();
	}

	public String createChemieLagaBoRow(List<DataTable> dataTables)
	{
		ChemistryLagaBoRow chemistryLagaBoRow = new ChemistryLagaBoRow(dataTables, outcrop, ChemistryKey.LAGA_BO, styleParameter);

		return chemistryLagaBoRow.buildWithProbes();
	}

	public String createChemieLagaRcRow(List<DataTable> dataTables)
	{
		ChemistryLagaRc chemistryLagaRc = new ChemistryLagaRc(dataTables, outcrop, ChemistryKey.LAGA_RC, styleParameter);

		return chemistryLagaRc.buildWithProbes();
	}

	public String createChemieLagaRcOrientationRow(List<DataTable> dataTables)
	{
		ChemistryLagaRcOrientation chemistryLagaRcOrientation = new ChemistryLagaRcOrientation(dataTables, outcrop, ChemistryKey.LAGA_RC_ORIENTATION, styleParameter);

		return chemistryLagaRcOrientation.buildWithProbes();
	}

	public String createChemieTlRockRow(List<DataTable> dataTables)
	{
		ChemistryTlRockRow chemistryTlRockRow = new ChemistryTlRockRow(dataTables, outcrop, ChemistryKey.TL_ROCK_STRATUM, styleParameter);

		return chemistryTlRockRow.buildWithProbes();
	}

	public String createChemieDepvRow(List<DataTable> dataTables)
	{
		ChemistryDepvRow chemistryDepvRow = new ChemistryDepvRow(dataTables, outcrop, ChemistryKey.DEPV, styleParameter);

		return chemistryDepvRow.buildWithProbes();
	}

	public String createChemieDecisionSupportRow(List<DataTable> dataTables)
	{
		ChemistryDecisionSupport chemistryDecisionSupport = new ChemistryDecisionSupport(dataTables, outcrop, ChemistryKey.DECISION_SUPPORT, styleParameter);

		return chemistryDecisionSupport.buildWithProbes();
	}

	public String createChemieAVVRow(List<DataTable> dataTables)
	{
		ChemistryAvvRow chemistryAvvRow = new ChemistryAvvRow(dataTables, outcrop, ChemistryKey.WASTE_KEY, styleParameter);

		return chemistryAvvRow.buildWithProbes();
	}

	public String createChemistryReku(List<DataTable> dataTables)
	{
		ChemistryRekuRow chemistryRekuRow = new ChemistryRekuRow(dataTables, outcrop, ChemistryKey.REKU, styleParameter);

		return chemistryRekuRow.buildWithProbes();
	}

	public String createChemistryRuva(List<DataTable> dataTables)
	{
		ChemistryRuvaRow row = new ChemistryRuvaRow(dataTables, outcrop, ChemistryKey.RUVA, styleParameter);

		return row.buildWithProbes();
	}

	public String createChemistryPak(List<DataTable> dataTables)
	{
		ChemistryPak row = new ChemistryPak(dataTables, outcrop, ChemistryKey.PAK, styleParameter);

		return row.buildWithProbes();
	}

	/**
	 * DIN
	 */

	public String createDIN18196Row(List<DataTable> dataTables)
	{
		DIN18196Row row = new DIN18196Row(dataTables, outcrop, SampleKey.TYPE, styleParameter);

		return row.buildWithProbes();
	}

	public String createDIN18915Row(List<DataTable> dataTables)
	{
		DIN18915Row row = new DIN18915Row(dataTables, outcrop, SampleKey.SOIL_CLASS, styleParameter);

		return row.buildWithProbes();
	}

	public String createDIN18300Row(List<DataTable> dataTables)
	{
		DIN18300Row row = new DIN18300Row(dataTables, outcrop, SampleKey.SOIL_CLASS, styleParameter);

		return row.buildWithProbes();
	}

	public String createDIN18320Row(List<DataTable> dataTables)
	{
		DIN18320Row row = new DIN18320Row(dataTables, outcrop, SampleKey.HOMOGENEOUS_RANGE, styleParameter);

		return row.buildWithProbes();
	}

	public String createDIN19682Row(List<DataTable> dataTables)
	{
		DIN19682Row row = new DIN19682Row(dataTables, outcrop, SampleKey.SOIL_TYPE, styleParameter);

		return row.buildWithProbes();
	}

	public String createDIN18300_09Row(List<DataTable> dataTables)
	{
		DIN18300_09Row row = new DIN18300_09Row(dataTables, outcrop, SampleKey.HOMOGENEOUS_RANGE, styleParameter);

		return row.buildWithProbes();
	}
}
