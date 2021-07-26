package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.LayerSample;
import sbt.automization.format.NameFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.data.TableFactory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;

import java.util.ArrayList;
import java.util.List;

public final class AppendixPN extends AHtmlTable
{
	private static AppendixPN instance;

	private AppendixPN() {}

	public static AppendixPN getInstance()
	{
		if (instance == null)
		{
			synchronized (AppendixPN.class)
			{
				if (instance == null)
				{
					instance = new AppendixPN();
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

		int rowCounter = 0;
		StringBuilder htmlStringBuilder = new StringBuilder();

		for (ExplorationSite explorationSite : sites)
		{
			List<LayerSample> layerSampleList = formatLayerList(explorationSite.getLayers());

			for (LayerSample layerSample : layerSampleList)
			{

				if (rowCounter >= 20)
				{
					htmlStringBuilder.append(table.appendTag())
							.append("<br>")
							.append("<br>");

					table = constructAndGetTableObject();

					rowCounter = 0;
				}


				HtmlCell sampleId = new HtmlCell.Builder()
						.appendAttribute("class", "NormalCentered")
						.appendContent("P".concat(String.valueOf(sampleIdCounter++)))
						.build();

				HtmlCell layerSampleType = new HtmlCell.Builder()
						.appendAttribute("class", "NormalCentered")
						.appendContent(TextFormatUtil.formatSampleType(layerSample.getInformation(ReferenceKey.LAYER_CONTAINER)))
						.build();

				HtmlCell layerContainer = new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(layerSample.getInformation(ReferenceKey.LAYER_CONTAINER))
						.build();

				HtmlCell heapVolume = new HtmlCell.Builder()
						.appendAttribute("class", "NormalCentered")
						.appendContent("-")
						.build();

				HtmlCell layerWasteType = new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "110")
						.appendContent(NameFormatUtil.formatLayerKind(layerSample.getInformation(ReferenceKey.LAYER_WASTE_TYPE)))
						.build();

				HtmlCell layerGrainSize = new HtmlCell.Builder()
						.appendAttribute("class", "NormalCentered")
						.appendAttribute("width", "50")
						.appendContent(layerSample.getInformation(ReferenceKey.LAYER_GRANULATION))
						.build();

				HtmlCell layerAttributes = new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(layerSample.getInformation(ReferenceKey.LAYER_COLOR))
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent(layerSample.getInformation(ReferenceKey.LAYER_SMELL))
						.appendContent(TextFormatUtil.printLineBreak())
						.appendContent(layerSample.getInformation(ReferenceKey.LAYER_SOIL_TYPE))
						.build();

				HtmlCell explorationSiteIdentifier = new HtmlCell.Builder()
						.appendAttribute("class", "NormalCentered")
						.appendAttribute("width", "30")
						.appendContent(explorationSite.getInformation(ReferenceKey.SITE_ID))
						.build();

				HtmlCell layerDepth = new HtmlCell.Builder()
						.appendAttribute("class", "NormalCentered")
						.appendContent(TextFormatUtil.formatDepth(layerSample.getInformation(ReferenceKey.LAYER_DEPTH_START),
								layerSample.getInformation(ReferenceKey.LAYER_DEPTH_END)))
						.build();

				HtmlCell explorationSiteTopEdge = new HtmlCell.Builder()
						.appendAttribute("class", "NormalCentered")
						.appendContent(explorationSite.getInformation(ReferenceKey.SITE_TOP_EDGE))
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

		htmlStringBuilder.append(table.appendTag());

		setTable(htmlStringBuilder.toString());
	}

	@Override
	public void constructTable(final ExplorationSite site)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Anlage-PN";
	}

	/**
	 * This method creates a new list of layer objects for the pn appendix. Because some layers are combined in the output table.
	 *
	 * @param layerSamples expects a not empty list of layer objects
	 * @return a smaller formatted list of layers
	 */
	public List<LayerSample> formatLayerList(List<LayerSample> layerSamples)
	{
		List<LayerSample> layerSampleList = new ArrayList<>();
		for (LayerSample layerSample : layerSamples)
		{
			layerSampleList.add(TableFactory.createLayer(layerSample.getInformationMap()));
		}

		int size = layerSampleList.size();

		if (size > 2)
		{
			for (int i = 0; i < layerSampleList.size() ; i++)
			{
				if ("GOB".equals(layerSampleList.get(i).getInformation(ReferenceKey.LAYER_OUTCROP)))
				{
					if (layerSampleList.size() <= i + 1) break;
					if (layerSampleList.get(i).getInformation(ReferenceKey.LAYER_WASTE_TYPE).equals(layerSampleList.get(i + 1).getInformation(ReferenceKey.LAYER_WASTE_TYPE)))
					{
						layerSampleList.get(i + 1).addInformation(ReferenceKey.LAYER_DEPTH_START.getIdentifier(),
								layerSampleList.get(i).getInformation(ReferenceKey.LAYER_DEPTH_START));
						layerSampleList.get(i + 1).addInformation(ReferenceKey.LAYER_GRANULATION.getIdentifier(), "");
						layerSampleList.remove(layerSampleList.get(i));
						i--;
					}
				}
			}
		}
		return layerSampleList;
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
	HtmlTable constructAndGetTableObject()
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();

		return table;
	}

}
