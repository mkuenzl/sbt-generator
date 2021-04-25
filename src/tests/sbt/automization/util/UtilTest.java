package sbt.automization.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.data.ExplorationSite;
import sbt.automization.templates.ErkundungsstellenTestFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UtilTest
{
	@Test
	public void exportCSVTemplate() throws IOException
	{
		Util.exportExcelTemplate();
	}

	@Test
	public void partioningTest()
	{
		List<ExplorationSite> erkundungsstellen = new ArrayList<>();
		ErkundungsstellenTestFactory erkundungsstellenTestFactory = new ErkundungsstellenTestFactory();
		for (int i = 0 ; i < 100 ; i++)
		{
			erkundungsstellen.add(erkundungsstellenTestFactory.getTestErkundungsstelle1());
		}

		Collection<List<ExplorationSite>> partionBasedOnSize = Util.separateBasedOnSize(erkundungsstellen, 5);

		Assert.assertTrue(partionBasedOnSize.size() == 20);
	}

	@Test
	public void partioningLessThenExpectedTest()
	{
		List<ExplorationSite> erkundungsstellen = new ArrayList<>();
		ErkundungsstellenTestFactory erkundungsstellenTestFactory = new ErkundungsstellenTestFactory();
		for (int i = 0 ; i < 3 ; i++)
		{
			erkundungsstellen.add(erkundungsstellenTestFactory.getTestErkundungsstelle1());
		}

		Collection<List<ExplorationSite>> partionBasedOnSize = Util.separateBasedOnSize(erkundungsstellen, 5);

		Assert.assertTrue(partionBasedOnSize.size() == 1);
	}

	@Test
	public void partioningUnregularAmountTest()
	{
		List<ExplorationSite> erkundungsstellen = new ArrayList<>();
		ErkundungsstellenTestFactory erkundungsstellenTestFactory = new ErkundungsstellenTestFactory();
		for (int i = 0 ; i < 17 ; i++)
		{
			erkundungsstellen.add(erkundungsstellenTestFactory.getTestErkundungsstelle1());
		}

		Collection<List<ExplorationSite>> partionBasedOnSize = Util.separateBasedOnSize(erkundungsstellen, 5);

		Assert.assertTrue(partionBasedOnSize.size() == 4);
	}

	@Test
	public void partioningNullTest()
	{
		List<ExplorationSite> erkundungsstellen = new ArrayList<>();

		Collection<List<ExplorationSite>> partionBasedOnSize = Util.separateBasedOnSize(erkundungsstellen, 5);

		Assert.assertTrue(partionBasedOnSize.size() == 0);
	}
}
