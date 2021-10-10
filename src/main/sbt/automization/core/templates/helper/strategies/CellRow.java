package sbt.automization.core.templates.helper.strategies;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.key.ChemistryKey;
import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.styles.StyleParameter;
import sbt.automization.core.templates.helper.information.InformationRetrievalStrategy;

import java.util.ArrayList;
import java.util.List;

public abstract class CellRow implements CellRowStrategy
{
	protected StyleParameter styleParameter;
	protected List<HtmlCell> cells = new ArrayList<>();

	private InformationRetrievalStrategy retrievalStrategy;

	public CellRow(StyleParameter styleParameter)
	{
		this.styleParameter = styleParameter;
	}

	public CellRow()
	{
	}


	public List<HtmlCell> build(List<DataTable> probes)
	{
		this.cells = new ArrayList<>();
		createCells(probes);
		return cells;
	}

	abstract void createCells(List<DataTable> probes);

	protected HtmlCell createCell(DataTable dataTable)
	{
		String retrievedInformation = retrievalStrategy.retrieve(dataTable);

		if (retrievalStrategy.getInformationKey() instanceof ChemistryKey)
			return HtmlFactory.createChemistryCell(retrievedInformation);

		HtmlCell cell = HtmlFactory.createCell(styleParameter.getTextFormatter(),
				styleParameter.getNormalCellClass(),
				styleParameter.getNormalCellWidth(),
				new String[]{retrievedInformation});

		return cell;
	}

	@Override
	public void setStyle(StyleParameter styleParameter)
	{
		this.styleParameter = styleParameter;
	}

	public InformationRetrievalStrategy getRetrievalStrategy()
	{
		return this.retrievalStrategy;
	}

	@Override
	public void setRetrievalStrategy(InformationRetrievalStrategy retrievalStrategy)
	{
		this.retrievalStrategy = retrievalStrategy;
	}
}
