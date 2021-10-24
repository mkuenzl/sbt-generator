package sbt.automization.core.templates.helper.strategies;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.html.HtmlCell;
import sbt.automization.core.styles.StyleParameter;
import sbt.automization.core.templates.helper.information.InformationRetrievalStrategy;

import java.util.List;

public interface CellRowStrategy
{
	void setRetrievalStrategy(InformationRetrievalStrategy strategy);
	
	void setStyle(StyleParameter style);
	
	List<HtmlCell> build(List<DataTable> dataTables);
}
