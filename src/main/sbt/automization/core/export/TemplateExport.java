package sbt.automization.core.export;

import sbt.automization.core.Project;
import sbt.automization.core.data.DataTable;
import sbt.automization.core.templates.HtmlTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Abstract class for an export strategy pattern
 */
public abstract class TemplateExport
{
	HtmlTemplate tableExportStrategy;
	
	TemplateExport(final HtmlTemplate tableExportStrategy)
	{
		this.tableExportStrategy = tableExportStrategy;
	}
	
	public TemplateExport()
	{
	
	}
	
	public void export(Project project, String path) throws Exception
	{
		export(getPath(path), format(project));
	}
	
	private void export(String path, String content) throws Exception
	{
		tableExportStrategy.resetTemplate();
		
		try (FileOutputStream fileOutputStream = new FileOutputStream(path);
		     OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
		     BufferedWriter bw = new BufferedWriter(outputStreamWriter))
		{
			bw.write(content);
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new Exception("Export failed.");
		}
	}
	
	public abstract String getPath();
	
	abstract String format(Project project);
	
	public void export(List<DataTable> tables)
	{
		try
		{
			export(getPath(), format(tables));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	abstract String format(List<DataTable> tables);
	
	public void export(String path, List<DataTable> tables)
	{
		try
		{
			export(getPath(path), format(tables));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public abstract String getPath(String path);
	
	public void exportAsShowcase(String htmlCode)
	{
		try
		{
			export(System.getProperty("user.dir").concat(File.separator).concat("testShowcase.html"), format(htmlCode));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	abstract String format(String text);
	
	public String getPathToShowcase()
	{
		return System.getProperty("user.dir").concat(File.separator).concat("testShowcase.html");
		
	}
}
