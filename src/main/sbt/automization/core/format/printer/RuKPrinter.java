package sbt.automization.core.format.printer;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.RuKKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.html.HtmlText;
import sbt.automization.core.util.CheckDataAvailability;

import java.util.List;

public final class RuKPrinter implements TextPrinter
{
	public String printRukLayers(final DataTable dataTable, final String outcrop)
	{
		List<Sample> samples = dataTable.getSamplesBy(SampleKey.OUTCROP, outcrop);
		
		if (!CheckDataAvailability.thereExistsAnSampleWithData(dataTable, outcrop, RuKKey.VALUE))
		{
			return "-";
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Sample sample : samples)
		{
			String rukValue = sample.getParameterValueBy(SampleKey.RUK_ID, RuKKey.VALUE);
			
			if (!"-".equals(rukValue) && !"".equals(rukValue))
			{
				if (0 != stringBuilder.length())
				{
					stringBuilder.append(UtilityPrinter.printCellTextDivider());
				}
				
				HtmlText layerKind = new HtmlText.Builder()
						.appendAttribute("class", "Normal6")
						.appendContent(sample.get(SampleKey.TYPE))
						.build();
				
				HtmlText rukText = new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(rukValue)
						.build();
				
				stringBuilder.append(layerKind.appendTag())
						.append(rukText.appendTag());
				
			}
		}
		
		return stringBuilder.toString();
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
