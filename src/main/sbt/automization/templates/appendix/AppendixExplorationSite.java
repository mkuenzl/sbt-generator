package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.references.ReferenceProbe;
import sbt.automization.data.refactoring.references.ReferenceSample;
import sbt.automization.format.FootnoteFormatUtil;
import sbt.automization.templates.Outcrop;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class AppendixExplorationSite extends AppendixTemplate
{
	private static AppendixExplorationSite instance;

	private AppendixExplorationSite() {}

	public static AppendixExplorationSite getInstance()
	{
		if (instance == null)
		{
			synchronized (AppendixExplorationSite.class)
			{
				if (instance == null)
				{
					instance = new AppendixExplorationSite();
				}
			}
		}
		return instance;
	}

	@Override
	public void constructTable(final List<ExplorationSite> sites)
	{
	}

	private String createHeadOfTable(Probe probe)
	{
		String firstRow = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCell("NormalHeader", "width:100px",
						new String[]{"Erkund.-Stelle"}),
				HtmlFactory.createCell("Normal",1, 3,
						new String[]{probe.get(ReferenceProbe.LOCATION)}),
		});

		String secondRow = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCell("NormalHeader",
						new String[]{"Bezeichnung"}),
				HtmlFactory.createCell("Normal", "width:200px",
						new String[]{probe.get(ReferenceProbe.ID)}),
				HtmlFactory.createCell("NormalHeader", "width:100px",
						new String[]{"Datum"}),
				HtmlFactory.createCell("Normal", "width:200px",
						new String[]{probe.get(ReferenceProbe.DATE)}),
		});

		String thirdRow = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCell("NormalHeader", "width:100px",
						new String[]{"Koordinaten<sup>1)</sup>"}),
				HtmlFactory.createCell("Normal", "width:200px",
						new String[]{probe.get(ReferenceProbe.COORDINATES)}),
				HtmlFactory.createCell("NormalHeader", "width:100px",
						new String[]{"Probenehmer"}),
				HtmlFactory.createCell("Normal", "width:200px",
						new String[]{probe.get(ReferenceProbe.INSPECTOR)}),
		});

		String fourthRow = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCell("NormalHeader", "width:100px",
						new String[]{"Bereich"}),
				HtmlFactory.createCell("Normal", "width:200px",
						new String[]{probe.get(ReferenceProbe.REGION)}),
				HtmlFactory.createCell("NormalHeader", "width:100px",
						new String[]{"Ansprechpartner"}),
				HtmlFactory.createCell("Normal", "width:200px",
						new String[]{probe.get(ReferenceProbe.CONTACT_PERSON)}),
		});

		return new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow)
				.append(fourthRow)
				.toString();
	}

	@Override
	public void constructTable(final ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Anlage-ERK";
	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		for (DataTable dataTable : dataTables)
		{
			Probe probe = (Probe) dataTable;

			HtmlTable table = constructAndGetTableObject();
			String headOfTable = createHeadOfTable(probe);
			table.appendContent(headOfTable);
			addToTemplate(table.appendTag());

			createTemplatesForSamples(probe);

			String footer = createFooter(dataTable);
			addToTemplate(footer);
			addToTemplate("<br></br>");
		}
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

	private String createFooter(DataTable dataTable)
	{
		HtmlCell cell = new HtmlCell.Builder()
				.appendAttribute("class", "NormalHeader")
				.appendContent(FootnoteFormatUtil.printFootnotes(dataTable))
				.build();

		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(cell.appendTag())
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
	String constructAndGetTableHeader()
	{
		return "";
	}

	@Override
	HtmlTable constructAndGetTableObject()
	{
		return new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();
	}

	private void createBanquetTemplate(Probe probe)
	{
		if (probe.hasSampleWith(ReferenceSample.OUTCROP, Outcrop.BANQUET.toString()))
		{
			AppendixSiteBANKETT banquet = new AppendixSiteBANKETT();
			banquet.constructTemplate(probe);
			addToTemplate(banquet.getTemplate());
		}
	}

	private void createGAPTemplate(Probe probe)
	{
		if (probe.hasSampleWith(ReferenceSample.OUTCROP, Outcrop.GAP.toString()))
		{
			AppendixSiteFUGE table = new AppendixSiteFUGE();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}

	private void createOHTemplate(Probe probe)
	{
		if (probe.hasSampleWith(ReferenceSample.OUTCROP, Outcrop.OH.toString()))
		{
			AppendixSiteOH table = new AppendixSiteOH();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}

	private void createOBTemplate(Probe probe)
	{
		if (probe.hasSampleWith(ReferenceSample.OUTCROP, Outcrop.GOB.toString()) ||
				probe.hasSampleWith(ReferenceSample.OUTCROP, Outcrop.CONCRETE.toString()) ||
				probe.hasSampleWith(ReferenceSample.OUTCROP, Outcrop.TMHB.toString()) ||
				probe.hasSampleWith(ReferenceSample.OUTCROP, Outcrop.SEAL.toString()) ||
				probe.hasSampleWith(ReferenceSample.OUTCROP, Outcrop.COATING.toString()))
		{
			AppendixSiteGOB table = new AppendixSiteGOB();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}

	private void createTOBTemplate(Probe probe)
	{
		if (probe.hasSampleWith(ReferenceSample.OUTCROP, Outcrop.TOB.toString()))
		{
			AppendixSiteTOB table = new AppendixSiteTOB();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}

	private void createUGTemplate(Probe probe)
	{
		if (probe.hasSampleWith(ReferenceSample.OUTCROP, Outcrop.UG.toString()))
		{
			AppendixSiteUG table = new AppendixSiteUG();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}


}
