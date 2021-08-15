package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.DataTableFactory;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.ReferenceProbe;
import sbt.automization.data.refactoring.references.ReferenceSample;
import sbt.automization.format.NameFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlTable;

import java.util.ArrayList;
import java.util.List;

public final class AppendixPN extends AppendixTemplate
{
	private static AppendixPN instance;

	private AppendixPN()
	{
		super();
	}

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
				HtmlFactory.createHeader("NormalTableHeader", 35, 2, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeader("NormalTableHeader", 70, 1, 1,
						new String[]{"Tiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", 60, 2, 1,
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

	@Override
	public void constructTemplate(List<DataTable> dataTables)
	{
		this.table = constructAndGetTableObject();

		for (DataTable dataTable : dataTables)
		{
			if (dataTable instanceof Probe)
			{
				Probe probe = (Probe) dataTable;

				List<Sample> samples = formatSamples(probe.getSamples());

				for (Sample sample : samples)
				{
					addAndResetTableOnPageBreak();

					String row = HtmlFactory.createRow("Normal", new String[]{
							HtmlFactory.createCell("Normal", "center",
									new String[]{"P".concat(String.valueOf(++ lines))}),
							HtmlFactory.createCell("Normal", "center",
									new String[]{TextFormatUtil.formatSampleType(sample.get(ReferenceSample.CONTAINER))}),
							HtmlFactory.createCell("Normal", "left",
									new String[]{sample.get(ReferenceSample.CONTAINER)}),
							HtmlFactory.createCell("Normal", "center",
									new String[]{"-"}),
							HtmlFactory.createCell("Normal", 110, "left",
									new String[]{NameFormatUtil.formatLayerKind(sample.get(ReferenceSample.WASTE_TYPE))}),
							HtmlFactory.createCell("Normal", 50, "center",
									new String[]{sample.get(ReferenceSample.GRANULATION)}),
							HtmlFactory.createCell("Normal", "left",
									new String[]{sample.get(ReferenceSample.COLOR), TextFormatUtil.printLineBreak(),
											sample.get(ReferenceSample.SMELL), TextFormatUtil.printLineBreak(),
											sample.get(ReferenceSample.SOIL_TYPE)}),
							HtmlFactory.createCell("Normal", 35, "center",
									new String[]{probe.get(ReferenceProbe.ID)}),
							HtmlFactory.createCell("Normal", "center",
									new String[]{TextFormatUtil.formatDepth(sample.get(ReferenceSample.DEPTH_START),
											sample.get(ReferenceSample.DEPTH_END))}),
							HtmlFactory.createCell("Normal", "center",
									new String[]{probe.get(ReferenceProbe.TOP_EDGE)})
					});

					linesPerPage++;

					this.table.appendContent(row);
				}
			}
		}
		addToTemplate(this.table.appendTag());
	}

	/**
	 * This method creates a new list of layer objects for the pn appendix. Because some layers are combined in the output table.
	 * TODO TEST
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
			for (int i = 0 ; i < formattedSamples.size() ; i++)
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

}
