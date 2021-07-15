package sbt.automization.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sbt.automization.util.CsvParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InformationTagTest
{
	private List<String> headers = new ArrayList<>();

	@Before
	public void importCSVHeader()
	{
		String path = System.getProperty("user.dir").concat(File.separator).concat("resources").concat(File.separator);

		File csv = new File(path + "sbt-excel-template.csv");
		CsvParser csvParser = new CsvParser(csv);

		headers = csvParser.parseHeader(csv);
	}

	/**
	 * Are all tags available as headers?
	 */
	@Test
	public void headersContainTagsTest(){

		List<String> missingTags = new ArrayList<>();

		for (InformationTag informationTag : InformationTag.values())
		{
			boolean contains = headers.contains(informationTag.getIdentifier());
			if (!contains) missingTags.add(informationTag.name());
		}

		Assert.assertEquals(missingTags.toString(), 0, missingTags.size());
	}

	/**
	 * Are all possible information covered by tags? TODO implement 22 more tags
	 */
	@Test
	public void tagsContainHeadersTest(){

		List<String> missingHeaders = new ArrayList<>();

		List<String> informationTags = Arrays.stream(InformationTag.values())
				.map(InformationTag::getIdentifier)
				.collect(Collectors.toList());

		for (String header : headers)
		{
			boolean contains = informationTags.contains(header);
			if (!contains) missingHeaders.add(header);
		}

		Assert.assertEquals(missingHeaders.toString(), 22, missingHeaders.size());
	}
}
