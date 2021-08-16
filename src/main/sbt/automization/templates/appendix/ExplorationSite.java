package sbt.automization.templates.appendix;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.references.RefProbe;
import sbt.automization.data.refactoring.references.RefSample;
import sbt.automization.format.FootnoteFormatUtil;
import sbt.automization.templates.Outcrop;
import sbt.automization.templates.appendix.site.*;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;

import java.util.List;

public final class ExplorationSite extends Appendix
{
	private static ExplorationSite instance;

	private ExplorationSite() {}

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

	private String createHeadOfTable(sbt.automization.data.refactoring.Probe probe)
	{
		String firstRow = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeader", "width:100px",
						new String[]{"Erkund.-Stelle"}),
				HtmlFactory.createCellAsString("Normal",1, 3,
						new String[]{probe.get(RefProbe.LOCATION)}),
		});

		String secondRow = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeader",
						new String[]{"Bezeichnung"}),
				HtmlFactory.createCellAsString("Normal", "width:200px",
						new String[]{probe.get(RefProbe.ID)}),
				HtmlFactory.createCellAsString("NormalHeader", "width:100px",
						new String[]{"Datum"}),
				HtmlFactory.createCellAsString("Normal", "width:200px",
						new String[]{probe.get(RefProbe.DATE)}),
		});

		String thirdRow = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeader", "width:100px",
						new String[]{"Koordinaten<sup>1)</sup>"}),
				HtmlFactory.createCellAsString("Normal", "width:200px",
						new String[]{probe.get(RefProbe.COORDINATES)}),
				HtmlFactory.createCellAsString("NormalHeader", "width:100px",
						new String[]{"Probenehmer"}),
				HtmlFactory.createCellAsString("Normal", "width:200px",
						new String[]{probe.get(RefProbe.INSPECTOR)}),
		});

		String fourthRow = HtmlFactory.createRow("Normal", new String[]{
				HtmlFactory.createCellAsString("NormalHeader", "width:100px",
						new String[]{"Bereich"}),
				HtmlFactory.createCellAsString("Normal", "width:200px",
						new String[]{probe.get(RefProbe.REGION)}),
				HtmlFactory.createCellAsString("NormalHeader", "width:100px",
						new String[]{"Ansprechpartner"}),
				HtmlFactory.createCellAsString("Normal", "width:200px",
						new String[]{probe.get(RefProbe.CONTACT_PERSON)}),
		});

		return new StringBuilder()
				.append(firstRow)
				.append(secondRow)
				.append(thirdRow)
				.append(fourthRow)
				.toString();
	}

	@Override
	public String getExportFileName()
	{
		return "Anlage-ERK";
	}


	@Override
	public void constructTemplate(DataTable dataTable)
	{

	}

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		for (DataTable dataTable : dataTables)
		{
			sbt.automization.data.refactoring.Probe probe = (sbt.automization.data.refactoring.Probe) dataTable;

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

	private void createTemplatesForSamples(sbt.automization.data.refactoring.Probe probe)
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
	protected String constructAndGetTableHeader()
	{
		return "";
	}

	@Override
	public HtmlTable constructAndGetTableObject()
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

	private void createBanquetTemplate(sbt.automization.data.refactoring.Probe probe)
	{
		if (probe.hasSampleWith(RefSample.OUTCROP, Outcrop.BANQUET.toString()))
		{
			Banquet banquet = new Banquet();
			banquet.constructTemplate(probe);
			addToTemplate(banquet.getTemplate());
		}
	}

	private void createGAPTemplate(sbt.automization.data.refactoring.Probe probe)
	{
		if (probe.hasSampleWith(RefSample.OUTCROP, Outcrop.GAP.toString()))
		{
			Gap table = new Gap();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}

	private void createOHTemplate(sbt.automization.data.refactoring.Probe probe)
	{
		if (probe.hasSampleWith(RefSample.OUTCROP, Outcrop.OH.toString()))
		{
			Topsoil table = new Topsoil();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}

	private void createOBTemplate(sbt.automization.data.refactoring.Probe probe)
	{
		if (probe.hasSampleWith(RefSample.OUTCROP, Outcrop.GOB.toString()) ||
				probe.hasSampleWith(RefSample.OUTCROP, Outcrop.CONCRETE.toString()) ||
				probe.hasSampleWith(RefSample.OUTCROP, Outcrop.TMHB.toString()) ||
				probe.hasSampleWith(RefSample.OUTCROP, Outcrop.SEAL.toString()) ||
				probe.hasSampleWith(RefSample.OUTCROP, Outcrop.COATING.toString()))
		{
			BoundSuperstructure table = new BoundSuperstructure();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}

	private void createTOBTemplate(sbt.automization.data.refactoring.Probe probe)
	{
		if (probe.hasSampleWith(RefSample.OUTCROP, Outcrop.TOB.toString()))
		{
			BaseCourseWithoutBinder table = new BaseCourseWithoutBinder();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}

	private void createUGTemplate(sbt.automization.data.refactoring.Probe probe)
	{
		if (probe.hasSampleWith(RefSample.OUTCROP, Outcrop.UG.toString()))
		{
			Underground table = new Underground();
			table.constructTemplate(probe);
			addToTemplate(table.getTemplate());
		}
	}


}
