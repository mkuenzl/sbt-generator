package sbt.automization.core;

import org.apache.commons.io.FilenameUtils;
import sbt.automization.core.data.DataTableFactory;
import sbt.automization.core.export.TemplateExport;
import sbt.automization.core.parser.CsvParser;
import sbt.automization.core.parser.ExcelParser;
import sbt.automization.core.parser.TableParser;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ProjectEngine
{
	private final Project project;
	
	public ProjectEngine()
	{
		this.project = new Project();
	}
	
	//Create Project
	public void createDatabase(List<Map<String, String>> tableRows)
	{
		DataTableFactory.initialize(tableRows);
		project.setProbes(DataTableFactory.getProbes());
		project.setSamples(DataTableFactory.getSamples());
		project.setParameters(DataTableFactory.getParameters());
	}
	
	//TODO: Turn function into factory
	private TableParser createParser(File file, String sheetName)
	{
		String extension = FilenameUtils.getExtension(file.getPath());
		
		switch (extension)
		{
			case "csv":
				return new CsvParser();
			case "xlsx":
			case "xlsm":
				ExcelParser excelParser = new ExcelParser();
				excelParser.setSheetName(sheetName);
				return excelParser;
			default:
				return null;
		}
	}
	
	public void retrieveDataFrom(File file, String sheetName)
	{
		TableParser parser = createParser(file, sheetName);
		try
		{
			assert parser != null;
			List<Map<String, String>> tableRows = parser.parse(file);
			createDatabase(tableRows);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Export ProjectTemplate
	public void export(TemplateExport templateExport, String exportPath) throws Exception
	{
		templateExport.export(project, exportPath);
	}
}
