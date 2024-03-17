package sbt.automization.core.templates.report;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import sbt.automization.core.ProjectEngine;
import sbt.automization.core.export.HtmlExport;
import sbt.automization.core.export.TemplateExport;
import sbt.automization.core.templates.appendix.AttemptTemplate;
import sbt.automization.core.templates.basic.Coordinates;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReportTemplateBuildingTestReport
{
	static ProjectEngine projectEngine;
	
	static String templateExportPath = System.getProperty("user.dir")
			.concat(File.separator)
			.concat("tests-resources")
			.concat(File.separator)
			.concat("template-output")
			.concat(File.separator);
	
	public static void initializeDatatables() throws Exception
	{
		projectEngine = new ProjectEngine();
		projectEngine.retrieveDataFrom(new File("tests-resources/input/excel/excel-template-test.xlsx"), "Daten");
	}
	
	@BeforeClass
	public static void initialize() throws Exception
	{
		initializeDatatables();
		
		if (!new File(templateExportPath).exists())
		{
			Files.createDirectory(Path.of(templateExportPath));
		}
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
	public void GOBTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(BoundSuperstructureReport.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	@Test
	public void GOB08TemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(BoundSuperstructure08Report.getInstance());
		createAndOpenTemplate(exportStrategy);

		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void TOBTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(BaseCourseWithoutBinderReport.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void UGTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(UndergroundReport.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void OHTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(TopsoilReport.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void TMHBTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(BaseCourseWithHydraulicBinderReport.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void CONCRETETemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(ConcreteReport.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void GAPTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(GapReport.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void HEAPTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(HeapReport.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void COORDINATETemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(Coordinates.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void BANQUETTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(BanquetReport.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void BUILDINGTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(BuildingReport.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Ignore
	@Test
	public void ExampleTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(AttemptTemplate.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
}
