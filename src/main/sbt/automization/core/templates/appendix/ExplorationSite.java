package sbt.automization.core.templates.appendix;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.format.printer.FootnotePrinter;
import sbt.automization.core.format.text.DateTextFormatter;
import sbt.automization.core.html.HtmlFactory;
import sbt.automization.core.html.HtmlRow;
import sbt.automization.core.html.HtmlTable;
import sbt.automization.core.templates.appendix.site.*;

import java.util.List;

public final class ExplorationSite extends Appendix
{
	private static ExplorationSite instance;
	
	private ExplorationSite()
	{
	}
	
	public static ExplorationSite getInstance()
	{
		if (instance == null)
		{
			synchronized (ExplorationSite.class)
			{
				if (instance == null)
				{
					instance = new ExplorationSite();
				}
			}
		}
		return instance;
	}
	
	@Override
	public String getExportFileName()
	{
		return "ERK-Anlage";
	}
	
	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		for (DataTable dataTable : dataTables)
		{
			Probe probe = (Probe) dataTable;
			
			createTable();
			String headOfTable = createHeadOfTable(probe);
			table.appendContent(headOfTable);
			addToTemplate(table.appendTag());
			
			createTemplatesForSamples(probe);
			
			String footer = createFooter(dataTable);
			addToTemplate(footer);
			addPageBreak();
		}
	}
	
	private String createHeadOfTable(Probe probe)
	{
		String firstRow = HtmlFactory.createRowAsString("NormalThin6", new String[]{
				HtmlFactory.createCellAsString("NormalHeader", "width:100px",
						new String[]{"Erkund.-Stelle"}),
				HtmlFactory.createCellAsString(textFormatter, "Normal", 1, 3,
						new String[]{probe.get(ProbeKey.LOCATION)}),
		});
		
		String secondRow = HtmlFactory.createRowAsString("NormalThin6", new String[]{
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Bezeichnung"}),
				HtmlFactory.createCellAsString(textFormatter, "Normal", "width:200px",
						new String[]{probe.get(ProbeKey.ID)}),
				HtmlFactory.createCellAsString("NormalHeader", "width:100px",
						new String[]{"Datum"}),
				HtmlFactory.createCellAsString(new DateTextFormatter(), "Normal", "width:200px",
						new String[]{probe.get(ProbeKey.DATE)}),
		});
		
		String thirdRow = HtmlFactory.createRowAsString("NormalThin6", new String[]{
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Koordinaten<sup>1)</sup>"}),
				HtmlFactory.createCellAsString(textFormatter, "Normal",
						new String[]{probe.get(ProbeKey.COORDINATES)}),
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Probenehmer"}),
				HtmlFactory.createCellAsString(textFormatter, "Normal",
						new String[]{probe.get(ProbeKey.INSPECTOR)}),
		});
		
		String fourthRow = HtmlFactory.createRowAsString("NormalThin6", new String[]{
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Bereich"}),
				HtmlFactory.createCellAsString(textFormatter, "Normal",
						new String[]{probe.get(ProbeKey.REGION)}),
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Ansprechpartner"}),
				HtmlFactory.createCellAsString(textFormatter, "Normal",
						new String[]{probe.get(ProbeKey.CONTACT_PERSON)}),
		});
		
		return new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow)
				.append(fourthRow)
				.toString();
	}
	
	private void createTemplatesForSamples(Probe probe)
	{
		createBanquetTemplate(probe);
		createGAPTemplate(probe);
		createOHTemplate(probe);
		createOBTemplate(probe);
		createTOBTemplate(probe);
		createUGTemplate(probe);
	}
	
	private void createBanquetTemplate(Probe probe)
	{
		if (probe.hasSampleWith(SampleKey.OUTCROP, Outcrop.BANQUET.toString()))
		{
			Banquet banquet = new Banquet();
			banquet.constructTemplate(probe);
			addToTemplate(banquet.getTemplate());
		}
	}
	
	private void createGAPTemplate(Probe probe)
	{
		if (probe.hasSampleWith(SampleKey.OUTCROP, Outcrop.GAP.toString()))
		{
			Gap table = new Gap();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}
	
	private void createOHTemplate(Probe probe)
	{
		if (probe.hasSampleWith(SampleKey.OUTCROP, Outcrop.OH.toString()))
		{
			Topsoil table = new Topsoil();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}
	
	private void createOBTemplate(Probe probe)
	{
		if (probe.hasSampleWith(SampleKey.OUTCROP, Outcrop.GOB.toString()) ||
				probe.hasSampleWith(SampleKey.OUTCROP, Outcrop.CONCRETE.toString()) ||
				probe.hasSampleWith(SampleKey.OUTCROP, Outcrop.TMHB.toString()) ||
				probe.hasSampleWith(SampleKey.OUTCROP, Outcrop.SEAL.toString()) ||
				probe.hasSampleWith(SampleKey.OUTCROP, Outcrop.COATING.toString()))
		{
			BoundSuperstructure table = new BoundSuperstructure();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}
	
	private void createTOBTemplate(Probe probe)
	{
		if (probe.hasSampleWith(SampleKey.OUTCROP, Outcrop.TOB.toString()))
		{
			BaseCourseWithoutBinder table = new BaseCourseWithoutBinder();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}
	
	private void createUGTemplate(Probe probe)
	{
		if (probe.hasSampleWith(SampleKey.OUTCROP, Outcrop.UG.toString()))
		{
			Underground table = new Underground();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}
	
	private String createFooter(DataTable dataTable)
	{
		
		String cell = HtmlFactory.createCellAsString("NormalHeaderSmallFont",
				new String[]{new FootnotePrinter().print(dataTable)});
		
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "NormalThin8")
				.appendContent(cell)
				.build();
		
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(row.appendTag())
				.build();
		
		return table.appendTag();
	}
	
	@Override
	public void constructTemplate(DataTable dataTable)
	{
	
	}
	
	@Override
	protected String constructAndGetTableHeader()
	{
		return "";
	}
	
	
}
