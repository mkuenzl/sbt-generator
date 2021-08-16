package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.DataTableFactory;
import sbt.automization.data.refactoring.references.Probe;
import sbt.automization.data.refactoring.references.Sample;
import sbt.automization.format.NameFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlFactory;
import sbt.automization.util.html.HtmlTable;

import java.util.ArrayList;
import java.util.List;

public final class SamplingProtocol extends Appendix
{
	private static SamplingProtocol instance;

	private SamplingProtocol()
	{
		super();
	}

	public static SamplingProtocol getInstance()
	{
		if (instance == null)
		{
			synchronized (SamplingProtocol.class)
			{
				if (instance == null)
				{
					instance = new SamplingProtocol();
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
				HtmlFactory.createHeader("NormalTableHeader", "width:40px", 2, 1,
						new String[]{"Probe", TextFormatUtil.printLineBreak(), "Nr."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:40px", 2, 1,
						new String[]{"Art"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:105px",
						new String[]{"Behältnis", TextFormatUtil.printLineBreak(), "Vol."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px",
						new String[]{"Haufwerk", TextFormatUtil.printLineBreak(), "Vol."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:140px", 2, 2,
						new String[]{"Abfallart"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:76px", 2, 1,
						new String[]{"Farbe", TextFormatUtil.printLineBreak(), "Geruch", TextFormatUtil.printLineBreak(), "Bodenart"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:35px", 2, 1,
						new String[]{"Erk. St."}),
				HtmlFactory.createHeader("NormalTableHeader", "width:70px",
						new String[]{"Tiefe"}),
				HtmlFactory.createHeader("NormalTableHeader", "width:60px", 2, 1,
						new String[]{"Notiz"})
		});

		String secondRow = HtmlFactory.createRow("NormalHeaderUnits", new String[]{
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"l"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
						new String[]{"l"}),
				HtmlFactory.createHeader("NormalTableHeaderUnits",
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
			if (dataTable instanceof sbt.automization.data.refactoring.Probe)
			{
				sbt.automization.data.refactoring.Probe probe = (sbt.automization.data.refactoring.Probe) dataTable;

				List<sbt.automization.data.refactoring.Sample> samples = formatSamples(probe.getSamples());

				for (sbt.automization.data.refactoring.Sample sample : samples)
				{
					addAndResetTableOnPageBreak();

					String row = HtmlFactory.createRow("Normal", new String[]{
							HtmlFactory.createCell("NormalCenter",
									new String[]{"P".concat(String.valueOf(++ lines))}),
							HtmlFactory.createCell("NormalCenter",
									new String[]{TextFormatUtil.formatSampleType(sample.get(Sample.CONTAINER))}),
							HtmlFactory.createCell("Normal",
									new String[]{sample.get(Sample.CONTAINER)}),
							HtmlFactory.createCell("NormalCenter",
									new String[]{"-"}),
							HtmlFactory.createCell("Normal", "width:110px",
									new String[]{NameFormatUtil.formatLayerKind(sample.get(Sample.WASTE_TYPE))}),
							HtmlFactory.createCell("NormalCenter", "width:50px",
									new String[]{sample.get(Sample.GRANULATION)}),
							HtmlFactory.createCell("Normal", "left",
									new String[]{sample.get(Sample.COLOR), TextFormatUtil.printLineBreak(),
											sample.get(Sample.SMELL), TextFormatUtil.printLineBreak(),
											sample.get(Sample.SOIL_TYPE)}),
							HtmlFactory.createCell("NormalCenter",
									new String[]{probe.get(Probe.ID)}),
							HtmlFactory.createCell("NormalCenter",
									new String[]{TextFormatUtil.formatDepth(sample.get(Sample.DEPTH_START),
											sample.get(Sample.DEPTH_END))}),
							HtmlFactory.createCell("NormalCenter",
									new String[]{probe.get(Probe.TOP_EDGE)})
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
	public List<sbt.automization.data.refactoring.Sample> formatSamples(List<sbt.automization.data.refactoring.Sample> samples)
	{
		List<sbt.automization.data.refactoring.Sample> formattedSamples = new ArrayList<>();
		for (sbt.automization.data.refactoring.Sample sample : samples)
		{
			formattedSamples.add(DataTableFactory.createSampleFrom(sample.getTable()));
		}

		int size = formattedSamples.size();

		if (size > 2)
		{
			for (int i = 0 ; i < formattedSamples.size() ; i++)
			{
				sbt.automization.data.refactoring.Sample sample = formattedSamples.get(i);

				if ("GOB".equals(sample.get(Sample.OUTCROP)))
				{
					if (formattedSamples.size() <= i + 1) break;
					if (sample.get(Sample.WASTE_TYPE).equals(formattedSamples.get(i + 1).get(Sample.WASTE_TYPE)))
					{
						formattedSamples.get(i + 1).add(Sample.DEPTH_START.getKey(),
								sample.get(Sample.DEPTH_START));
						formattedSamples.get(i + 1).add(Sample.GRANULATION.getKey(), "");
						formattedSamples.remove(sample);
						i--;
					}
				}
			}
		}
		return formattedSamples;
	}

}
