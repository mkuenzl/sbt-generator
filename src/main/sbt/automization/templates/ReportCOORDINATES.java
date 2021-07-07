package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;

import java.util.Arrays;
import java.util.List;

public class ReportCOORDINATES extends AHtmlTable
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
	String constructAndGetTableHeader()
	{
		HtmlTableHeader erkId = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "75")
				.appendAttribute("rowspan", "2")
				.appendContent("Erk. St.")
				.build();

		HtmlTableHeader utm = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "530")
				.appendAttribute("align", "left")
				.appendAttribute("rowspan", "1")
				.appendAttribute("colspan", "4")
				.appendContent("UTM")
				.build();

		HtmlTableHeader zone = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "75")
				.appendAttribute("align", "left")
				.appendAttribute("rowspan", "1")
				.appendContent("Zone")
				.build();

		HtmlTableHeader eastValue = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "75")
				.appendAttribute("align", "left")
				.appendAttribute("rowspan", "1")
				.appendContent("Ostwert")
				.build();

		HtmlTableHeader northValue = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "75")
				.appendAttribute("align", "left")
				.appendAttribute("rowspan", "1")
				.appendContent("Nordwert")
				.build();

		HtmlTableHeader emptyCell = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "305")
				.appendAttribute("rowspan", "1")
				.appendContent("")
				.build();

		HtmlRow firstHeaderRow = new HtmlRow.Builder()
				.appendAttribute("class", "NormalHeader")
				.appendContent(erkId.appendTag())
				.appendContent(utm.appendTag())
				.build();

		HtmlRow secondHeaderRow = new HtmlRow.Builder()
				.appendAttribute("class", "NormalHeader")
				.appendContent(zone.appendTag())
				.appendContent(eastValue.appendTag())
				.appendContent(northValue.appendTag())
				.appendContent(emptyCell.appendTag())
				.build();

		StringBuilder strb = new StringBuilder();
		strb.append(firstHeaderRow.appendTag())
				.append(secondHeaderRow.appendTag());

		return strb.toString();
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

			List<String> coordinateSplit = splitCoordinate(site);

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

	private List<String> splitCoordinate(ExplorationSite site)
	{
		List<String> coordinateList;

		String coordinates = site.getInformation("ERK_KOORDINATEN");
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
