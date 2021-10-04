package sbt.automization.templates;


import sbt.automization.data.DataTable;

import java.util.List;

public interface HtmlTemplate
{
	String getExportFileName();

	String getTemplate();

	void constructTemplate(List<DataTable> dataTables);

	void constructTemplate(DataTable dataTable);

	void resetTemplate();
}
