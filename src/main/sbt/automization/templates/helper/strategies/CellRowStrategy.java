package sbt.automization.templates.helper.strategies;

import sbt.automization.data.DataTable;
import sbt.automization.html.HtmlCell;
import sbt.automization.styles.StyleParameter;
import sbt.automization.templates.helper.information.InformationRetrievalStrategy;

import java.util.List;

public interface CellRowStrategy
{
	void setRetrievalStrategy(InformationRetrievalStrategy strategy);

	void setStyle(StyleParameter style);

	List<HtmlCell> build(List<DataTable> dataTables);
}
