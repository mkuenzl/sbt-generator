package sbt.automization.templates.report;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.templates.appendix.AppendixTemplate;
import sbt.automization.util.html.*;

import java.util.Arrays;
import java.util.List;

public final class ReportCOORDINATES extends ReportTemplate
{
	private static ReportCOORDINATES instance;

	private ReportCOORDINATES() {}

	public static ReportCOORDINATES getInstance()
	{
		if (instance == null)
		{
			synchronized (ReportCOORDINATES.class)
			{
				if (instance == null)
				{
					instance = new ReportCOORDINATES();
				}
			}
		}
		return instance;
	}

	@Override
	public void constructTable(List<ExplorationSite> sites)
	{
		HtmlTable coordinateTable = constructAndGetTableObject();
		coordinateTable.appendContent(constructAndGetTableHeader());
		coordinateTable.appendContent(addDataToTable(sites));
		setTable(coordinateTable.appendTag());
	}

	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow("NormalHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", 75, 2, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeader("NormalTableHeader", 530, 1, 4,
						new String[]{"UTM"})
		});

		String secondRow = HtmlFactory.createRow("NormalHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", 75, 1, 1,
						new String[]{"Zone"}),
				HtmlFactory.createHeader("NormalTableHeader", 75, 1, 1,
						new String[]{"Ostwert"}),
				HtmlFactory.createHeader("NormalTableHeader", 75, 1, 1,
						new String[]{"Nordwert"}),
				HtmlFactory.createHeader("NormalTableHeader", 305, 1, 1,
						new String[]{""}),
		});

		StringBuilder strb = new StringBuilder()
				.append(firstRow)
				.append(secondRow);

		return strb.toString();
	}

	@Override
	String buildTechnicalFeatures(List<ExplorationSite> explorationSites)
	{
		return null;
	}

	@Override
	String buildEnvironmentTechnicalFeatures(List<ExplorationSite> explorationSites)
	{
		return null;
	}

	String addDataToTable(List<ExplorationSite> sites)
	{
		StringBuilder strb = new StringBuilder();

		for (ExplorationSite site : sites)
		{
			HtmlCell erkId = new HtmlCell.Builder()
					.appendAttribute("class", "Normal")
					.appendAttribute("width", "75")
					.appendAttribute("align", "center")
					.appendAttribute("rowspan", "2")
					.appendContent(site.getInformation("ERK_ID"))
					.build();

			HtmlCell utm = new HtmlCell.Builder()
					.appendAttribute("class", "Normal")
					.appendAttribute("width", "530")
					.appendAttribute("rowspan", "1")
					.appendAttribute("colspan", "4")
					.appendContent(site.getInformation("ERK_ORT"))
					.build();

			List<String> coordinateSplit = splitCoordinate(site.getInformation(ReferenceKey.SITE_COORDINATES));

			HtmlCell zone = new HtmlCell.Builder()
					.appendAttribute("class", "Normal")
					.appendAttribute("width", "75")
					.appendAttribute("rowspan", "1")
					.appendContent(coordinateSplit.get(0))
					.build();

			HtmlCell eastValue = new HtmlCell.Builder()
					.appendAttribute("class", "Normal")
					.appendAttribute("width", "75")
					.appendAttribute("rowspan", "1")
					.appendContent(coordinateSplit.get(1))
					.build();

			HtmlCell northValue = new HtmlCell.Builder()
					.appendAttribute("class", "Normal")
					.appendAttribute("width", "75")
					.appendAttribute("rowspan", "1")
					.appendContent(coordinateSplit.get(2))
					.build();

			HtmlCell emptyCell = new HtmlCell.Builder()
					.appendAttribute("class", "Normal")
					.appendAttribute("width", "305")
					.appendAttribute("rowspan", "1")
					.appendContent("")
					.build();

			HtmlRow firstDataRow = new HtmlRow.Builder()
					.appendAttribute("class", "NormalThin")
					.appendContent(erkId.appendTag())
					.appendContent(utm.appendTag())
					.build();

			HtmlRow secondDataRow = new HtmlRow.Builder()
					.appendAttribute("class", "NormalThin")
					.appendContent(zone.appendTag())
					.appendContent(eastValue.appendTag())
					.appendContent(northValue.appendTag())
					.appendContent(emptyCell.appendTag())
					.build();

			strb.append(firstDataRow.appendTag())
					.append(secondDataRow.appendTag());
		}

		return strb.toString();
	}

	@Override
	public void constructTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht-KOORDINATEN";
	}

	private List<String> splitCoordinate(String coordinates)
	{
		List<String> coordinateList;

		String[] split = coordinates.split("(\\s)+");

		if (split.length == 3)
		{
			coordinateList = Arrays.asList(split);
		} else
		{
			coordinateList = Arrays.asList("-", "-", "-");
		}

		return coordinateList;
	}
}
