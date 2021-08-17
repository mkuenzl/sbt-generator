package sbt.automization.templates;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Examination;
import sbt.automization.export.HtmlTemplateExport;
import sbt.automization.templates.appendix.*;
import sbt.automization.templates.basic.Coordinates;
import sbt.automization.templates.report.*;
import sbt.automization.util.CsvParser;
import sbt.automization.util.Util;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplateBuildingTest
{
	List<DataTable> dataTables = new ArrayList<>();

	public static void openExportFile(String path) throws IOException
	{
		File htmlFile = new File(path);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	@BeforeClass
	public static void createDatatables() throws Exception
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		File csv = new File(path + "datenbank-template-test.csv");
		CsvParser csvParser = new CsvParser(csv);

		Examination examination = new Examination(csvParser.parse(), csv.getParent());

		int size = examination.size();
		for (int i = 0 ; i < size ; i++)
		{
			Util.serializeDatatableToFile(examination.get(i), "tests-resources/Probe_" + i);
		}
	}

	@Before
	public void initializeDatatables()
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);

		dataTables.add(Util.readSerializedDatatable(path + "Probe_0"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_1"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_2"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_3"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_4"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_5"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_6"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_7"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_8"));
		dataTables.add(Util.readSerializedDatatable(path + "Probe_9"));
//		dataTables.add(Util.readSerializedDatatable(path + "Probe_10"));
//		dataTables.add(Util.readSerializedDatatable(path + "Probe_11"));

	}

	@Test
	public void createExplorationSiteTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(ExplorationSite.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createRukTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(RingAndBall.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createLpTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(LoadPlate.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createPnTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(SamplingProtocol.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createPnHeapTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(SamplingProtocolHeap.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportOBTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(BoundSuperstructure.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportTOBTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(BaseCourseWithoutBinder.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportUGTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Underground.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportOHTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Topsoil.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createExampleTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(AttemptTemplate.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportTMHBTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(BaseCourseWithHydraulicBinder.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportBETONTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Concrete.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportFUGETemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Gap.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportHeapTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Heap.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportCoordinateTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Coordinates.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}

	@Test
	public void createReportBankettTemplate() throws IOException
	{
		HtmlTemplateExport htmlTemplateExportStrategy = new HtmlTemplateExport(Banquet.getInstance());
		htmlTemplateExportStrategy.export(dataTables);

		openExportFile(htmlTemplateExportStrategy.getPath());
	}
}
