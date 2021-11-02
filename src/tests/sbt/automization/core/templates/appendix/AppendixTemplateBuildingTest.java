package sbt.automization.core.templates.appendix;

import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.core.ProjectEngine;
import sbt.automization.core.export.HtmlExport;
import sbt.automization.core.export.TemplateExport;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AppendixTemplateBuildingTest
{
	static ProjectEngine projectEngine;
	
	String templateExportPath = System.getProperty("user.dir")
			.concat(File.separator)
			.concat("tests-resources")
			.concat(File.separator)
			.concat("template-output")
			.concat(File.separator);
	
	@BeforeClass
	public static void initializeDatatables() throws Exception
	{
		projectEngine = new ProjectEngine();
		projectEngine.retrieveDataFrom(new File("tests-resources/input/excel/excel-template-test.xlsx"), "Daten");
	}
	
	private void createAndOpenTemplate(TemplateExport exportStrategy) throws Exception
	{
		projectEngine.export(exportStrategy, templateExportPath);
		openTemplateFile(exportStrategy.getPath(templateExportPath));
	}
	
	public static void openTemplateFile(String path) throws IOException
	{
		File htmlFile = new File(path);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}
	
	@Test
	public void createExplorationSiteTemplate() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(ExplorationSite.getInstance());
		createAndOpenTemplate(exportStrategy);
	}
	
	@Test
	public void createRukTemplate() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(RingAndBall.getInstance());
		createAndOpenTemplate(exportStrategy);
	}
	
	@Test
	public void createLpTemplate() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(LoadPlate.getInstance());
		createAndOpenTemplate(exportStrategy);
	}
	
	@Test
	public void createPnTemplate() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(SamplingProtocol.getInstance());
		createAndOpenTemplate(exportStrategy);
	}
	
	@Test
	public void createPnHeapTemplate() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(SamplingProtocolHeap.getInstance());
		createAndOpenTemplate(exportStrategy);
	}
	
	@Test
	public void createPnBuildingTemplate() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(SamplingProtocolBuilding.getInstance());
		createAndOpenTemplate(exportStrategy);
	}
}
