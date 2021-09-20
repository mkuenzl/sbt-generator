package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;

import java.util.Collection;
import java.util.List;

public final class Building extends Report
{
	private static Building instance;
	private final RowProvider provider;

	private Building()
	{
		super(Outcrop.BUILDING);
		provider = new RowProvider(Outcrop.BUILDING);
	}

	public static Building getInstance()
	{
		if (instance == null)
		{
			synchronized (Building.class)
			{
				if (instance == null)
				{
					instance = new Building();
				}
			}
		}
		return instance;
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		Collection<List<DataTable>> tablesSplitIntoPortions = splitIntoPortionPerPage(dataTables);
		for (List<DataTable> portion : tablesSplitIntoPortions)
		{
			buildTable(portion);
			addTable();
			addPageBreak();
		}
	}

	private void buildTable(List<DataTable> dataTables)
	{
		createTable();
		provider.setDataTables(dataTables);

		addToTable(provider.getRowWithSamples(new IdRow()));
		addToTable(provider.getRowWithSamples(new ComponentRow()));
		addToTable(provider.getRowWithSamples(new MaterialBuildingRow()));

		constructEnvironmentTechnicalFeatures(dataTables);
		addToTable(provider.getRowWithSamples(new LegendWithDepthRow()));
	}

	@Override
	public String getExportFileName()
	{
		return "GEBÄUDE-Report";
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{

	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addToTable(provider.getRowWithSamples(new ChemistryIdRow()));
		addToTable(provider.getRowWithSamples(new SuspectedPollutantRow()));
		addToTable(provider.getRowWithSamples(new ChemistryPak()));
		addToTable(provider.getRowWithSamples(new ChemistryPcbRow())); // PCB
		addToTable(provider.getRowWithSamples(new ChemistryAsbestosRow())); // ASBEST

		addToTable(provider.getRowWithSamples(new ChemistryBtexRow()));
		addToTable(provider.getRowWithSamples(new ChemistryPhenolRow())); // KMF
		addToTable(provider.getRowWithSamples(new ChemistryKmfRow())); // KMF
		addToTable(provider.getRowWithSamples(new ChemistrySulfateRow()));
		addToTable(provider.getRowWithSamples(new ChemistryIcpScreeningRow()));
		addToTable(provider.getRowWithSamples(new ChemistryEoxRow()));

		addToTable(provider.getRowWithSamples(new ChemistryLagaBoRow()));
		addToTable(provider.getRowWithSamples(new ChemistryLagaRc()));
		addToTable(provider.getRowWithSamples(new ChemistryLagaRcOrientation()));
		addToTable(provider.getRowWithSamples(new ChemistryTlRockRow()));
		addToTable(provider.getRowWithSamples(new ChemistryMufvClassificationRow())); // Einstufung
		addToTable(provider.getRowWithSamples(new ChemistryMufvParameterRow())); // Parameter
		addToTable(provider.getRowWithSamples(new WasteKeyMaterialRow()));  // Material
		addToTable(provider.getRowWithSamples(new WasteKeyMixRow()));  // gemischt
	}
}
