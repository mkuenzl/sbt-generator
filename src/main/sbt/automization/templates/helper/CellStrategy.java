package sbt.automization.templates.helper;

import sbt.automization.data.DataTable;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.html.HtmlCell;
import sbt.automization.html.HtmlFactory;
import sbt.automization.styles.StyleParameter;
import sbt.automization.templates.helper.information.InformationRetrievalStrategy;

import java.util.ArrayList;
import java.util.List;

public abstract class CellStrategy
{
	protected StyleParameter styleParameter;
	protected List<HtmlCell> cells = new ArrayList<>();

	private InformationRetrievalStrategy retrievalStrategy;

	public CellStrategy(StyleParameter styleParameter)
	{
		this.styleParameter = styleParameter;
	}

	public CellStrategy()
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

	public void setStyle(StyleParameter styleParameter)
	{
		this.styleParameter = styleParameter;
	}

	public InformationRetrievalStrategy getRetrievalStrategy()
	{
		return this.retrievalStrategy;
	}

	public void setRetrievalStrategy(InformationRetrievalStrategy retrievalStrategy)
	{
		this.retrievalStrategy = retrievalStrategy;
	}
}
