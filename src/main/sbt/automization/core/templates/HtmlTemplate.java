package sbt.automization.core.templates;


import sbt.automization.core.Project;
import sbt.automization.core.data.DataTable;

import java.util.List;

public interface HtmlTemplate
{
	String getExportFileName();
	
	String getTemplate();
	
	void constructTemplate(List<DataTable> dataTables);
	
	void constructTemplate(DataTable dataTable);
	
	void constructTemplate(Project project);
	
	void resetTemplate();
}
