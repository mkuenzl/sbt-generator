package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.LayerSample;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.DataTableFactory;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.ReferenceSample;
import sbt.automization.format.NameFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.*;

import java.util.ArrayList;
import java.util.List;

public final class AppendixPN extends AppendixTemplate
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
			//List<LayerSample> layerSampleList = formatSamples(explorationSite.getLayers());

			for (LayerSample layerSample : explorationSite.getLayers())
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
	public void constructTemplate(List<DataTable> tables)
	{
		HtmlTable table = constructAndGetTableObject();

		//add row counter
		//TODO ADD PAGE BREAK

		for (DataTable dataTable : tables)
		{
			if (dataTable instanceof Probe)
			{
				Probe probe = (Probe) dataTable;

				List<Sample> samples = formatSamples(probe.getSamples());

				for (Sample sample : samples)
				{

				}
			}
		}

		setTable(table.appendTag());
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
	 * @param samples expects a not empty list of layer objects
	 * @return a smaller formatted list of layers
	 */
	public List<Sample> formatSamples(List<Sample> samples)
	{
		List<Sample> formattedSamples = new ArrayList<>();
		for (Sample sample : samples)
		{
			formattedSamples.add(DataTableFactory.createSampleFrom(sample.getTable()));
		}

		int size = formattedSamples.size();

		if (size > 2)
		{
			for (int i = 0; i < formattedSamples.size() ; i++)
			{
				Sample sample = formattedSamples.get(i);

				if ("GOB".equals(sample.get(ReferenceSample.OUTCROP)))
				{
					if (formattedSamples.size() <= i + 1) break;
					if (sample.get(ReferenceSample.WASTE_TYPE).equals(formattedSamples.get(i + 1).get(ReferenceSample.WASTE_TYPE)))
					{
						formattedSamples.get(i + 1).add(ReferenceSample.DEPTH_START.getKey(),
								sample.get(ReferenceSample.DEPTH_START));
						formattedSamples.get(i + 1).add(ReferenceSample.GRANULATION.getKey(), "");
						formattedSamples.remove(sample);
						i--;
					}
				}
			}
		}
		return formattedSamples;
	}

	@Override
	public String constructAndGetTableHeader()
	{
		String firstRow = HtmlFactory.createRow("NormalHeader", new String[]{
				HtmlFactory.createHeader("NormalTableHeader", 40, 2, 1,
						new String[]{"Probe", TextFormatUtil.printLineBreak(), "Nr."}),
				HtmlFactory.createHeader("NormalTableHeader", 40, 2, 1,
						new String[]{"Art"}),
				HtmlFactory.createHeader("NormalTableHeader", 105, 1, 1,
						new String[]{"Behältnis", TextFormatUtil.printLineBreak(), "Vol."}),
				HtmlFactory.createHeader("NormalTableHeader", 60, 1, 1,
						new String[]{"Haufwerk", TextFormatUtil.printLineBreak(), "Vol."}),
				HtmlFactory.createHeader("NormalTableHeader", 140, 2, 2,
						new String[]{"Abfallart"}),
				HtmlFactory.createHeader("NormalTableHeader", 76, 2, 1,
						new String[]{"Farbe", TextFormatUtil.printLineBreak(), "Geruch", TextFormatUtil.printLineBreak(), "Bodenart"}),
				HtmlFactory.createHeader("NormalTableHeader", 30, 2, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeader("NormalTableHeader", 70, 1, 1,
						new String[]{"Tiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", 65, 2, 1,
						new String[]{"Notiz"})
		});

		String secondRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits", 105, 1, 1,
						new String[]{"l"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 60, 1, 1,
						new String[]{"l"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits", 70, 1, 1,
						new String[]{"cm"})
		});

		StringBuilder stringBuilder = new StringBuilder()
				.append(firstRow)
				.append(secondRow);

		return stringBuilder.toString();
	}

	@Override
	public HtmlTable constructAndGetTableObject()
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
