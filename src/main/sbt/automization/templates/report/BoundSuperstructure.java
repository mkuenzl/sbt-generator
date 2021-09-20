package sbt.automization.templates.report;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.templates.helper.RowProvider;
import sbt.automization.templates.helper.strategy.*;

import java.util.Collection;
import java.util.List;

public final class BoundSuperstructure extends Report
{
	private static BoundSuperstructure instance;
	private final RowProvider rowProvider;

	private BoundSuperstructure()
	{
		super(Outcrop.GOB);
		rowProvider = new RowProvider(Outcrop.GOB);
	}

	public static BoundSuperstructure getInstance()
	{
		if (instance == null)
		{
			synchronized (BoundSuperstructure.class)
			{
				if (instance == null)
				{
					instance = new BoundSuperstructure();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "GOB-Report";
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
		rowProvider.setDataTables(dataTables);

		addToTable(rowProvider.getRowWithProbes(new IdRow()));
		addToTable(rowProvider.getRowWithProbes(new SuperstructureExposureRow()));

		constructTechnicalFeatures(dataTables);
		constructEnvironmentTechnicalFeatures(dataTables);

		addToTable(rowProvider.getRowWithProbes(new CrossSectionWithPitchRows()));
		addToTable(rowProvider.getRowWithProbes(new CrossSectionWithoutPitchRows()));
	}

	@Override
	void constructTechnicalFeatures(List<DataTable> dataTables)
	{
		addTechnicalHeader(dataTables);

		addToTable(rowProvider.getRowWithProbes(new SizeTotalObRow()));
		addToTable(rowProvider.getRowWithProbes(new LoadClassRow()));
		addToTable(rowProvider.getRowWithProbes(new RuKCombinedRow()));
		addToTable(rowProvider.getRowWithProbes(new RuKSingleValueRow()));
	}

	@Override
	void constructEnvironmentTechnicalFeatures(List<DataTable> dataTables)
	{
		addEnvironmentTechnicalHeader(dataTables);

		addToTable(rowProvider.getRowWithProbes(new PitchQualitativeRow()));
		addToTable(rowProvider.getRowWithProbes(new PitchHalfQuantitativeRow()));
		addToTable(rowProvider.getRowWithProbes(new PitchQuantitativeRow()));
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}
}
