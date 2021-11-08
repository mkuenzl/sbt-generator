package sbt.automization.core.parser;

import sbt.automization.view.popup.ErrorPopup;
import sbt.automization.view.popup.InfoPopup;

import java.util.List;

public abstract class ATableParser implements TableParser
{
	public void showCSVInfoMessage(List<String> input) throws Exception
	{
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Die CSV enthält:");
		
		if (checkValidityOfHeader(input))
		{
			messageBuilder.append("\n- Erkundungsstellen");
			messageBuilder.append("\n- Proben");
		} else
		{
			//Implement Exception
			ErrorPopup.showMessage("Der Datensatz enthält keine Erkundungsstellen oder Proben! \nÜberprüfen sie den Datensatz.");
			throw new Exception("No valid database, either probes or samples are missing.");
		}
		
		String chemistryID = "PARAMETER.CHEMISTRY.ID";
		if (input.contains(chemistryID))
		{
			messageBuilder.append("\n- Chemie Parameter");
		}
		String rukID = "PARAMETER.RUK.ID";
		if (input.contains(rukID))
		{
			messageBuilder.append("\n- RuK Parameter");
		}
		String lpID = "PARAMETER.LP.ID";
		if (input.contains(lpID))
		{
			messageBuilder.append("\n- Lp Parameter");
		}
		
		InfoPopup.showMessage(messageBuilder.toString());
	}
	
	public boolean checkValidityOfHeader(List<String> input)
	{
		String probeID = "PROBE.ID";
		String sampleID = "SAMPLE.ID";
		
		return input.contains(probeID) && input.contains(sampleID);
	}
}
