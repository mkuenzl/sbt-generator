package sbt.automization.templates.appendix;

import sbt.automization.data.DataTable;
import sbt.automization.data.DataTableFactory;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.references.RefProbe;
import sbt.automization.data.references.RefSample;
import sbt.automization.format.NameFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.html.HtmlFactory;
import sbt.automization.html.HtmlTable;

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
			if (dataTable instanceof Probe)
			{
				Probe probe = (Probe) dataTable;

				List<Sample> samples = formatSamples(probe.getSamples());

				for (Sample sample : samples)
				{
					addAndResetTableOnPageBreak();

					String row = HtmlFactory.createRow("Normal", new String[]{
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{"P".concat(String.valueOf(++ lines))}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{TextFormatUtil.formatSampleType(sample.get(RefSample.CONTAINER))}),
							HtmlFactory.createCellAsString("Normal",
									new String[]{sample.get(RefSample.CONTAINER)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{"-"}),
							HtmlFactory.createCellAsString("Normal", "width:110px",
									new String[]{NameFormatUtil.formatLayerKind(sample.get(RefSample.WASTE_TYPE))}),
							HtmlFactory.createCellAsString("NormalCenter", "width:50px",
									new String[]{sample.get(RefSample.GRANULATION)}),
							HtmlFactory.createCellAsString("Normal", "left",
									new String[]{sample.get(RefSample.COLOR), TextFormatUtil.printLineBreak(),
											sample.get(RefSample.SMELL), TextFormatUtil.printLineBreak(),
											sample.get(RefSample.SOIL_TYPE)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{probe.get(RefProbe.ID)}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{TextFormatUtil.formatDepth(sample.get(RefSample.DEPTH_START),
											sample.get(RefSample.DEPTH_END))}),
							HtmlFactory.createCellAsString("NormalCenter",
									new String[]{probe.get(RefProbe.TOP_EDGE)})
					});

					linesPerPage++;

					this.table.appendContent(row);
				}
			}
		}
		addToTemplate(this.table.appendTag());
	}

	@Override
	public void constructTemplate(DataTable dataTable)
	{

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

				if ("GOB".equals(sample.get(RefSample.OUTCROP)))
				{
					if (formattedSamples.size() <= i + 1) break;
					if (sample.get(RefSample.WASTE_TYPE).equals(formattedSamples.get(i + 1).get(RefSample.WASTE_TYPE)))
					{
						formattedSamples.get(i + 1).add(RefSample.DEPTH_START.getKey(),
								sample.get(RefSample.DEPTH_START));
						formattedSamples.get(i + 1).add(RefSample.GRANULATION.getKey(), "");
						formattedSamples.remove(sample);
						i--;
					}
				}
			}
		}
		return formattedSamples;
	}

}
