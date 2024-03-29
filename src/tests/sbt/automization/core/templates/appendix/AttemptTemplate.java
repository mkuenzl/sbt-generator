package sbt.automization.core.templates.appendix;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.html.HtmlTable;

import java.util.List;

public final class AttemptTemplate extends Appendix
{
	private static AttemptTemplate instance;

	private AttemptTemplate() {}

	public static AttemptTemplate getInstance()
	{
		if (instance == null)
		{
			synchronized (AttemptTemplate.class)
			{
				if (instance == null)
				{
					instance = new AttemptTemplate();
				}
			}
		}
		return instance;
	}

	@Override
	public String getExportFileName()
	{
		return "example";
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{

	}

	@Override
	public void createTableWithHeader()
	{
		table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}

	@Override
	public String constructAndGetTableHeader()
	{
		return null;
	}




}
