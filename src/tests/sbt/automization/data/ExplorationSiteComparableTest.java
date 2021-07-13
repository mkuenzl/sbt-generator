package sbt.automization.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ExplorationSiteComparableTest
{
	ExplorationSite explorationSiteFB1;
	ExplorationSite explorationSiteFB2;
	ExplorationSite explorationSiteFB3;
	ExplorationSite explorationSiteFB4;
	ExplorationSite explorationSiteFB17;
	ExplorationSite explorationSiteFB22;

	ExplorationSite explorationSite1;
	ExplorationSite explorationSite2;
	ExplorationSite explorationSite3;

	@Before
	public void createExplorationSites()
	{
		Map<String, String> site1 = new HashMap<>(){{
			put("ERK_ID", "FB1");
			put("ERK_NUMMER", "1");
		}};

		explorationSiteFB1 = new ExplorationSite(site1);

		Map<String, String> site2 = new HashMap<>(){{
			put("ERK_ID", "FB2");
			put("ERK_NUMMER", "2");
		}};

		explorationSiteFB2 = new ExplorationSite(site2);

		Map<String, String> site3 = new HashMap<>(){{
			put("ERK_ID", "FB3");
			put("ERK_NUMMER", "3");
		}};

		explorationSiteFB3 = new ExplorationSite(site3);

		Map<String, String> site4 = new HashMap<>(){{
			put("ERK_ID", "FB4");
			put("ERK_NUMMER", "4");
		}};

		explorationSiteFB4 = new ExplorationSite(site4);

		Map<String, String> site17 = new HashMap<>(){{
			put("ERK_ID", "FB17");
			put("ERK_NUMMER", "17");
		}};

		explorationSiteFB17 = new ExplorationSite(site17);

		Map<String, String> site22 = new HashMap<>(){{
			put("ERK_ID", "FB22");
			put("ERK_NUMMER", "22");
		}};

		explorationSiteFB22 = new ExplorationSite(site22);

		Map<String, String> site5 = new HashMap<>(){{
			put("ERK_ID", "5");
			put("ERK_NUMMER", "5");
		}};

		explorationSite1 = new ExplorationSite(site5);

		Map<String, String> site6 = new HashMap<>(){{
			put("ERK_ID", "6");
			put("ERK_NUMMER", "6");
		}};

		explorationSite2 = new ExplorationSite(site6);

		Map<String, String> site7 = new HashMap<>(){{
			put("ERK_ID", "7");
			put("ERK_NUMMER", "7");
		}};

		explorationSite3 = new ExplorationSite(site7);


	}

	@Test
	public void sortWithText()
	{
		List<ExplorationSite> explorationSites = new ArrayList<>();

		explorationSites.add(explorationSiteFB1);
		explorationSites.add(explorationSiteFB2);
		explorationSites.add(explorationSiteFB17);
		explorationSites.add(explorationSiteFB22);
		explorationSites.add(explorationSiteFB3);
		explorationSites.add(explorationSiteFB4);


		List<ExplorationSite> explorationSitesToCompare = new ArrayList<>();

		explorationSitesToCompare.add(explorationSiteFB1);
		explorationSitesToCompare.add(explorationSiteFB2);
		explorationSitesToCompare.add(explorationSiteFB3);
		explorationSitesToCompare.add(explorationSiteFB4);
		explorationSitesToCompare.add(explorationSiteFB17);
		explorationSitesToCompare.add(explorationSiteFB22);

		Collections.sort(explorationSites);

		Assert.assertEquals(explorationSitesToCompare, explorationSites);
	}

	@Deprecated
	@Test
	public void sortWithOutText()
	{
		List<ExplorationSite> explorationSites = new ArrayList<>();

		explorationSites.add(explorationSite3);
		explorationSites.add(explorationSite2);
		explorationSites.add(explorationSite1);



		List<ExplorationSite> explorationSitesToCompare = new ArrayList<>();

		explorationSitesToCompare.add(explorationSite1);
		explorationSitesToCompare.add(explorationSite2);
		explorationSitesToCompare.add(explorationSite3);


		Collections.sort(explorationSites);

		Assert.assertEquals(explorationSitesToCompare, explorationSites);
	}
}
