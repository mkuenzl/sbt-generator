package sbt.automization.core.format.printer;

import sbt.automization.core.data.DataTable;

public interface TextPrinter
{
	String print(DataTable dataTable);
	
	String print();
}
