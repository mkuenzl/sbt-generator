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
		Map<String, String> erk1Daten = new HashMap<>(){{
			put("ERK_ID", "FB1");
			put("ERK_NUMMER", "1");
		}};

		explorationSiteFB1 = new ExplorationSite(erk1Daten);

		Map<String, String> erk2Daten = new HashMap<>(){{
			put("ERK_ID", "FB2");
			put("ERK_NUMMER", "2");
		}};

		explorationSiteFB2 = new ExplorationSite(erk2Daten);

		Map<String, String> erk3Daten = new HashMap<>(){{
			put("ERK_ID", "FB3");
			put("ERK_NUMMER", "3");
		}};

		explorationSiteFB3 = new ExplorationSite(erk3Daten);

		Map<String, String> erk4Daten = new HashMap<>(){{
			put("ERK_ID", "FB4");
			put("ERK_NUMMER", "4");
		}};

		explorationSiteFB4 = new ExplorationSite(erk4Daten);

		Map<String, String> erk17Daten = new HashMap<>(){{
			put("ERK_ID", "FB17");
			put("ERK_NUMMER", "17");
		}};

		explorationSiteFB17 = new ExplorationSite(erk17Daten);

		Map<String, String> erk22Daten = new HashMap<>(){{
			put("ERK_ID", "FB22");
			put("ERK_NUMMER", "22");
		}};

		explorationSiteFB22 = new ExplorationSite(erk22Daten);

		Map<String, String> erk05Data = new HashMap<>(){{
			put("ERK_ID", "5");
			put("ERK_NUMMER", "5");
		}};

		explorationSite1 = new ExplorationSite(erk05Data);

		Map<String, String> erk06Data = new HashMap<>(){{
			put("ERK_ID", "6");
			put("ERK_NUMMER", "6");
		}};

		explorationSite2 = new ExplorationSite(erk06Data);

		Map<String, String> erk07Data = new HashMap<>(){{
			put("ERK_ID", "7");
			put("ERK_NUMMER", "7");
		}};

		explorationSite3 = new ExplorationSite(erk07Data);


	}

	@Test
	public void sortWithText()
	{
		List<ExplorationSite> erkundungsstellen = new ArrayList<>();

		erkundungsstellen.add(explorationSiteFB1);
		erkundungsstellen.add(explorationSiteFB2);
		erkundungsstellen.add(explorationSiteFB17);
		erkundungsstellen.add(explorationSiteFB22);
		erkundungsstellen.add(explorationSiteFB3);
		erkundungsstellen.add(explorationSiteFB4);


		List<ExplorationSite> erkundungsstellenCompareTo = new ArrayList<>();

		erkundungsstellenCompareTo.add(explorationSiteFB1);
		erkundungsstellenCompareTo.add(explorationSiteFB2);
		erkundungsstellenCompareTo.add(explorationSiteFB3);
		erkundungsstellenCompareTo.add(explorationSiteFB4);
		erkundungsstellenCompareTo.add(explorationSiteFB17);
		erkundungsstellenCompareTo.add(explorationSiteFB22);

		Collections.sort(erkundungsstellen);

		Assert.assertEquals(erkundungsstellenCompareTo, erkundungsstellen);
	}

	@Deprecated
	@Test
	public void sortWithOutText()
	{
		List<ExplorationSite> erkundungsstellen = new ArrayList<>();

		erkundungsstellen.add(explorationSite3);
		erkundungsstellen.add(explorationSite2);
		erkundungsstellen.add(explorationSite1);



		List<ExplorationSite> erkundungsstellenCompareTo = new ArrayList<>();

		erkundungsstellenCompareTo.add(explorationSite1);
		erkundungsstellenCompareTo.add(explorationSite2);
		erkundungsstellenCompareTo.add(explorationSite3);


		Collections.sort(erkundungsstellen);

		Assert.assertEquals(erkundungsstellenCompareTo, erkundungsstellen);
	}
}
