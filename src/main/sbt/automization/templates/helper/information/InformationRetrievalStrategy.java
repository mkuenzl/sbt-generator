package sbt.automization.templates.helper.information;

import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.key.Key;

public interface InformationRetrievalStrategy
{
	String retrieve(DataTable dataTable);

	Key getInformationKey();

	void setOutcrop(Outcrop outcrop);
}
