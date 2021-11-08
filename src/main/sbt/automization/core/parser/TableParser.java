package sbt.automization.core.parser;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface TableParser
{
	List<Map<String, String>> parse(File file)  throws Exception;
	
	List<String> retrieveHeader(File file);
}
