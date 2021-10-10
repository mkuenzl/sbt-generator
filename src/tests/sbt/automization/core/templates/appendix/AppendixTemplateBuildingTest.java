package sbt.automization.core.templates.appendix;

import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.core.data.DataTable;
import sbt.automization.core.export.HtmlTemplateExport;
import sbt.automization.core.templates.DatatableInitializer;
import sbt.automization.core.templates.EnterRobot;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppendixTemplateBuildingTest
{
	static List<DataTable> dataTables = new ArrayList<>();

	String templateExportPath = System.getProperty("user.dir")
			.concat(File.separator)
			.concat("tests-resources")
			.concat(File.separator)
			.concat("template-output")
			.concat(File.separator);

	@BeforeClass
	public static void initializeDatatables() throws Exception
	{
		Thread thread = new Thread(new EnterRobot());
		thread.start();
		dataTables = new DatatableInitializer().initializeDatatables();
	}

	@Test
	public void createExplorationSiteTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ExplorationSite.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	public static void openExportFile(String path) throws IOException
	{
		File htmlFile = new File(path);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	@Test
	public void createRukTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(RingAndBall.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void createLpTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(LoadPlate.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void createPnTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(SamplingProtocol.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void createPnHeapTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(SamplingProtocolHeap.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void createPnBuildingTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(SamplingProtocolBuilding.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}
}
