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

public class ReportTemplateBuildingTest
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
		TemplateExport exportStrategy = new HtmlExport(BoundSuperstructure.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	@Test
	public void GOB08TemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(BoundSuperstructure08.getInstance());
		createAndOpenTemplate(exportStrategy);

		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void TOBTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(BaseCourseWithoutBinder.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void UGTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(Underground.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void OHTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(Topsoil.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void TMHBTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(BaseCourseWithHydraulicBinder.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void CONCRETETemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(Concrete.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void GAPTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(Gap.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void HEAPTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(Heap.getInstance());
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
		TemplateExport exportStrategy = new HtmlExport(Banquet.getInstance());
		createAndOpenTemplate(exportStrategy);
		
		Assert.assertTrue(new File(exportStrategy.getPath(templateExportPath)).exists());
	}
	
	@Test
	public void BUILDINGTemplateTest() throws Exception
	{
		TemplateExport exportStrategy = new HtmlExport(Building.getInstance());
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
