package sbt.automization.format.printer;

import sbt.automization.data.DataTable;

public interface TextPrinter
{
	String print(DataTable dataTable);

	String print();
}
