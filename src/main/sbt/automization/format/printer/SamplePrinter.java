package sbt.automization.format.printer;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ChemistryKey;
import sbt.automization.data.key.Key;
import sbt.automization.data.key.RuKKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.datatable.SampleFormatter;
import sbt.automization.format.text.*;
import sbt.automization.html.HtmlText;

import java.util.List;

public final class SamplePrinter implements TextPrinter
{
	private Outcrop outcrop;

	public SamplePrinter()
	{

	}

	public SamplePrinter(Outcrop outcrop)
	{
		this.outcrop = outcrop;
	}

	/**
	 * Method calculates the thickness of a specific outcrop from an exploration site.
	 *
	 * @param samples an ExplorationSite object
	 * @return the thickness as String
	 */
	public String printThickness(List<Sample> samples)
	{
		Double thicknessOfSamples = measureThickness(samples);

		if (thicknessOfSamples % 1 == 0)
		{
			return String.format("%.0f", thicknessOfSamples);
		} else
		{
			String height = String.valueOf(thicknessOfSamples);
			return height.replace(".", ",");
		}
	}

	public Double measureThickness(List<Sample> samples)
	{
		double heightValue = 0.0;
		for (Sample sample : samples)
		{
			heightValue += measureThickness(sample);
		}
		return heightValue;
	}

	public Double measureThickness(Sample sample)
	{
		if (sample.containsValueFor(SampleKey.THICKNESS))
		{
			String formattedValue = sample.get(SampleKey.THICKNESS).replace(",", ".");
			return Double.parseDouble(formattedValue);
		}

		return 0.0;
	}

	public String printSamplesMaterials(DataTable dataTable)
	{
		StringBuilder formattedSampleMaterial = new StringBuilder();

		SampleFormatter sampleFormatter = new SampleFormatter(dataTable, outcrop.toString());
		List<Sample> outcropLayerSamples = sampleFormatter.combineSamplesByTag(SampleKey.TYPE);

		//List<Sample> outcropLayerSamples = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop.toString());

		int size = outcropLayerSamples.size();

		for (int i = 0 ; i < size ; i++)
		{
			Sample sample = outcropLayerSamples.get(i);

			formattedSampleMaterial.append(formatKindRoundingGranulation(sample));

			String startDepth = sample.get(SampleKey.DEPTH_START);
			String endDepth = sample.get(SampleKey.DEPTH_END);
			formattedSampleMaterial.append(new DepthTextFormatter(true).format(startDepth, endDepth));

			if (i + 1 < size)
			{
				formattedSampleMaterial.append(UtilityPrinter.printCellTextDivider());
			}
		}

		return formattedSampleMaterial.toString();
	}

	private String formatKindRoundingGranulation(Sample sample)
	{
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(new LineBreakTextFormatter().format(sample.get(SampleKey.TYPE)));
		stringBuilder.append(UtilityPrinter.printLineEmptyThin());

		HtmlText attributes = new HtmlText.Builder()
				.appendAttribute("class", "Normal6")
				.appendContent(sample.get(SampleKey.ROUNDING_GRADATION))
				.appendContent(" ")
				.appendContent(sample.get(SampleKey.GRANULATION))
				.build();
		stringBuilder.append(attributes.appendTag());

		return stringBuilder.toString();
	}

	public String printAttributeOfSamplesWithDepth(final DataTable dataTable, final String outcrop, final Key tag)
	{
		SampleFormatter sampleFormatter = new SampleFormatter(dataTable, outcrop);

		List<Sample> samples = sampleFormatter.combineSamplesByTag(tag);

		StringBuilder stringBuilder = new StringBuilder();

		int amountOfSamples = samples.size();

		for (int i = 0 ; i < amountOfSamples ; i++)
		{
			Sample sample = samples.get(i);

			String formattedTag = printAttributeOfDatatable(sample, tag);

			if (i > 0)
			{
				stringBuilder.append(UtilityPrinter.printCellTextDivider());
			}

			stringBuilder.append(formattedTag);
			stringBuilder.append(UtilityPrinter.printLineEmptyThin());
			stringBuilder.append(new DepthTextFormatter(true).format(sample.get(SampleKey.DEPTH_START),
					sample.get(SampleKey.DEPTH_END)));

		}

		return stringBuilder.toString();
	}

	public String printAttributeOfSamples(final DataTable dataTable, final String outcrop, final Key tag)
	{
		SampleFormatter sampleFormatter = new SampleFormatter(dataTable, outcrop);

		List<Sample> samples = sampleFormatter.combineSamplesByTag(tag);

		StringBuilder stringBuilder = new StringBuilder();

		int amountOfSamples = samples.size();

		for (int i = 0 ; i < amountOfSamples ; i++)
		{
			Sample sample = samples.get(i);

			String formattedTag = printAttributeOfDatatable(sample, tag);

			if (i > 0)
			{
				stringBuilder.append(UtilityPrinter.printCellTextDivider());
			}

			stringBuilder.append(formattedTag);
		}

		return stringBuilder.toString();
	}

	public String printAttributeOfDatatable(final DataTable dataTable, final Key tag)
	{
			String formattedTag;

			if (tag instanceof ChemistryKey)
			{
				formattedTag = new ChemistryMarkupTextFormatter().format(dataTable.getParameterValueBy(SampleKey.CHEMISTRY_ID, tag));
			} else if (tag instanceof RuKKey)
			{
				formattedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(new StandardCellTextFormatter().format(dataTable.getParameterValueBy(SampleKey.RUK_ID, tag)))
						.build().appendTag();
			} else if (tag == SampleKey.MOISTURE)
			{
				formattedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(new StandardCellTextFormatter().format(new ProctorTextFormatter().format(dataTable.get(SampleKey.MOISTURE))))
						.build().appendTag();
			} else
			{
				formattedTag = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(new StandardCellTextFormatter().format(dataTable.get(tag)))
						.build().appendTag();
			}
		return formattedTag;
	}

	@Override
	public String print(DataTable dataTable)
	{
		return null;
	}

	@Override
	public String print()
	{
		return null;
	}
}
