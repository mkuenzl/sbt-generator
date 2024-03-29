package sbt.automization.core.retrieval;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.key.Key;

public interface InformationRetrievalStrategy
{
	String retrieve(DataTable dataTable);
	
	Key getInformationKey();
	
	void setOutcrop(Outcrop outcrop);
}
