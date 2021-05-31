package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.format.NameFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;

import java.util.List;

public final class AppendixPNHEAP extends AHtmlTemplate
{
	private static AppendixPNHEAP instance;

	private AppendixPNHEAP() {}

	public static AppendixPNHEAP getInstance()
	{
		if (instance == null)
		{
			synchronized (AppendixPNHEAP.class)
			{
				if (instance == null)
				{
					instance = new AppendixPNHEAP();
				}
			}
		}
		return instance;
	}

	@Override
	public void constructTable(final List<ExplorationSite> sites)
	{
		int sampleIdCounter = 1;

		HtmlTable table = constructAndGetTableObject();
		table.appendContent(constructAndGetTableHeader());

		int rowCounter = 0;

		StringBuilder htmlStringBuilder = new StringBuilder();

		for (ExplorationSite explorationSite : sites)
		{
			List<Layer> layerList = explorationSite.getLayers();

			for (Layer layer : layerList)
			{
				if ("HAUFWERK".equals(layer.getInformation("SCHICHT_AUFSCHLUSS")))
				{
					// Could be problematic
					int volume = Integer.parseInt(explorationSite.getInformation("ERK_HAUFWERK_VOLUMEN"));
					int sampleCounter = Integer.parseInt(explorationSite.getInformation("ERK_HAUFWERK_PROBEN_ANZAHL"));

					for (int i = 0 ; i < sampleCounter ; i++)
					{
						int sampleVolume = volume / sampleCounter;

						if (rowCounter >= 20)
						{
							htmlStringBuilder.append(table.appendTag())
									.append("<br>")
									.append("<br>");

							table = constructAndGetTableObject();
							table.appendContent(constructAndGetTableHeader());


							rowCounter = 0;
						}

						HtmlCell sampleId = new HtmlCell.Builder()
								.appendAttribute("class", "NormalCentered")
								.appendContent("P".concat(String.valueOf(sampleIdCounter++)))
								.build();

						HtmlCell layerSampleType = new HtmlCell.Builder()
								.appendAttribute("class", "NormalCentered")
								.appendContent(TextFormatUtil.formatLayerSampleType(layer))
								.build();

						HtmlCell layerContainer = new HtmlCell.Builder()
								.appendAttribute("class", "Normal")
								.appendContent(layer.getInformation("SCHICHT_BEHAELTNIS"))
								.build();

						HtmlCell heapVolume = new HtmlCell.Builder()
								.appendAttribute("class", "NormalCentered")
								.appendContent(String.valueOf(sampleVolume))
								.build();

						HtmlCell layerWasteType = new HtmlCell.Builder()
								.appendAttribute("class", "Normal")
								.appendAttribute("width", "110")
								.appendContent(NameFormatUtil.formatArt(layer.getInformation("SCHICHT_ABFALLART")))
								.build();

						HtmlCell layerGrainSize = new HtmlCell.Builder()
								.appendAttribute("class", "NormalCentered")
								.appendAttribute("width", "50")
								.appendContent(layer.getInformation("SCHICHT_KOERNUNG"))
								.build();

						HtmlCell layerAttributes = new HtmlCell.Builder()
								.appendAttribute("class", "Normal")
								.appendContent(layer.getInformation("SCHICHT_FARBE"))
								.appendContent(TextFormatUtil.printLineBreak())
								.appendContent(layer.getInformation("SCHICHT_GERUCH"))
								.appendContent(TextFormatUtil.printLineBreak())
								.appendContent(layer.getInformation("SCHICHT_BODENART"))
								.build();

						HtmlCell explorationSiteIdentifier = new HtmlCell.Builder()
								.appendAttribute("class", "NormalCentered")
								.appendAttribute("width", "30")
								.appendContent(explorationSite.getInformation("ERK_ID"))
								.build();

						HtmlCell layerDepth = new HtmlCell.Builder()
								.appendAttribute("class", "NormalCentered")
								.appendContent(TextFormatUtil.formatLayerDepth(layer))
								.build();

						HtmlCell explorationSiteTopEdge = new HtmlCell.Builder()
								.appendAttribute("class", "NormalCentered")
								.appendContent(explorationSite.getInformation("ERK_OBERKANTE"))
								.build();

						HtmlRow row = new HtmlRow.Builder()
								.appendAttribute("class", "Normal")
								.appendContent(sampleId.appendTag())
								.appendContent(layerSampleType.appendTag())
								.appendContent(layerContainer.appendTag())
								.appendContent(heapVolume.appendTag())
								.appendContent(layerWasteType.appendTag())
								.appendContent(layerGrainSize.appendTag())
								.appendContent(layerAttributes.appendTag())
								.appendContent(explorationSiteIdentifier.appendTag())
								.appendContent(layerDepth.appendTag())
								.appendContent(explorationSiteTopEdge.appendTag())
								.build();

						rowCounter++;

						table.appendContent(row.appendTag());
					}
				}
			}
		}

		htmlStringBuilder.append(table.appendTag());

		setTable(htmlStringBuilder.toString());
	}

	@Override
	String constructAndGetTableHeader()
	{
		HtmlTableHeader headerCellSample = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "40")
				.appendAttribute("rowspan", "2")
				.appendContent("Probe")
				.build();

		HtmlTableHeader headerCellType = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "40")
				.appendAttribute("rowspan", "2")
				.appendContent("Art")
				.build();

		HtmlTableHeader headerCellContainer = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "105")
				.appendContent("Behältnis")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("Vol.")
				.build();

		HtmlTableHeader headerCellHeap = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendContent("Haufwerk")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("Vol.")
				.build();

		HtmlTableHeader headerCellWasteType = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "140")
				.appendAttribute("colspan", "2")
				.appendAttribute("rowspan", "2")
				.appendContent("Abfallart")
				.build();

		HtmlTableHeader headerCellAttributes = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "76")
				.appendAttribute("rowspan", "2")
				.appendContent("Farbe")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("Geruch")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("Bodenart")
				.build();

		HtmlTableHeader headerCellExplorationSite = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "30")
				.appendAttribute("rowspan", "2")
				.appendContent("Erk. St.")
				.build();

		HtmlTableHeader headerCellDepth = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "70")
				.appendContent("Tiefe")
				.build();

		HtmlTableHeader headerCellComment = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "65")
				.appendAttribute("rowspan", "2")
				.appendContent("Notiz")
				.build();

		HtmlTableHeader headerCellVolumeUnit = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeaderUnits")
				.appendContent("l")
				.build();

		HtmlTableHeader headerCellDepthUnit = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeaderUnits")
				.appendContent("cm")
				.build();

		HtmlRow headerCellFirstRow = new HtmlRow.Builder()
				.appendAttribute("class", "NormalHeader")
				.appendContent(headerCellSample.appendTag())
				.appendContent(headerCellType.appendTag())
				.appendContent(headerCellContainer.appendTag())
				.appendContent(headerCellHeap.appendTag())
				.appendContent(headerCellWasteType.appendTag())
				.appendContent(headerCellAttributes.appendTag())
				.appendContent(headerCellExplorationSite.appendTag())
				.appendContent(headerCellDepth.appendTag())
				.appendContent(headerCellComment.appendTag())
				.build();

		HtmlRow headerCellSecondRow = new HtmlRow.Builder()
				.appendAttribute("class", "NormalHeaderUnits")
				.appendContent(headerCellVolumeUnit.appendTag())
				.appendContent(headerCellVolumeUnit.appendTag())
				.appendContent(headerCellDepthUnit.appendTag())
				.build();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(headerCellFirstRow.appendTag())
				.append(headerCellSecondRow.appendTag());

		return stringBuilder.toString();
	}

	@Override
	public void constructTable(ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "PN_HAUFWERK_TEMPLATE.html";
	}
}