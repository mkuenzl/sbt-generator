package sbt.automization.templates.report;

import org.junit.Before;
import org.junit.Test;
import sbt.automization.data.DataTable;
import sbt.automization.export.HtmlTemplateExport;
import sbt.automization.templates.DatatableInitializer;
import sbt.automization.templates.appendix.AttemptTemplate;
import sbt.automization.templates.basic.Coordinates;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportTemplateBuildingTest
{
	List<DataTable> dataTables = new ArrayList<>();

	String templateExportPath = System.getProperty("user.dir")
			.concat(File.separator)
			.concat("tests-resources")
			.concat(File.separator)
			.concat("template-output")
			.concat(File.separator);

	@Before
	public void initializeDatatables() throws Exception
	{
		dataTables = new DatatableInitializer().initializeDatatables();
	}

	@Test
	public void GOBTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(BoundSuperstructure.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	public static void openExportFile(String path) throws IOException
	{
		File htmlFile = new File(path);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	@Test
	public void TOBTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(BaseCourseWithoutBinder.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void UGTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Underground.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void OHTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Topsoil.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void ExampleTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AttemptTemplate.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void TMHBTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(BaseCourseWithHydraulicBinder.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void CONCRETETemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Concrete.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void GAPTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Gap.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void HEAPTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Heap.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void COORDINATETemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Coordinates.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void BANQUETTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Banquet.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}

	@Test
	public void BUILDINGTemplateTest() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Building.getInstance());
		htmlTemplateExportStrategy.export(templateExportPath, dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath(templateExportPath));
	}
}