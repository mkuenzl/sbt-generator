package sbt.automization.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ErkCompareToTest
{
	Erkundungsstelle erkundungsstelleFB1;
	Erkundungsstelle erkundungsstelleFB2;
	Erkundungsstelle erkundungsstelleFB3;
	Erkundungsstelle erkundungsstelleFB4;
	Erkundungsstelle erkundungsstelleFB17;
	Erkundungsstelle erkundungsstelleFB22;

	Erkundungsstelle erkundungsstelle1;
	Erkundungsstelle erkundungsstelle2;
	Erkundungsstelle erkundungsstelle3;


	@Before
	public void createErkundungsstellen()
	{
		Map<String, String> erk1Daten = new HashMap<>(){{
			put("ERK_ID", "FB1");
		}};

		erkundungsstelleFB1 = new Erkundungsstelle(erk1Daten);

		Map<String, String> erk2Daten = new HashMap<>(){{
			put("ERK_ID", "FB2");
		}};

		erkundungsstelleFB2 = new Erkundungsstelle(erk2Daten);

		Map<String, String> erk3Daten = new HashMap<>(){{
			put("ERK_ID", "FB3");
		}};

		erkundungsstelleFB3 = new Erkundungsstelle(erk3Daten);

		Map<String, String> erk4Daten = new HashMap<>(){{
			put("ERK_ID", "FB4");
		}};

		erkundungsstelleFB4 = new Erkundungsstelle(erk4Daten);

		Map<String, String> erk17Daten = new HashMap<>(){{
			put("ERK_ID", "FB17");
		}};

		erkundungsstelleFB17 = new Erkundungsstelle(erk17Daten);

		Map<String, String> erk22Daten = new HashMap<>(){{
			put("ERK_ID", "FB22");
		}};

		erkundungsstelleFB22 = new Erkundungsstelle(erk22Daten);

		Map<String, String> erk01Daten = new HashMap<>(){{
			put("ERK_ID", "FB22");
		}};

		erkundungsstelle1 = new Erkundungsstelle(erk01Daten);

		Map<String, String> erk02Daten = new HashMap<>(){{
			put("ERK_ID", "FB22");
		}};

		erkundungsstelle2 = new Erkundungsstelle(erk02Daten);

		Map<String, String> erk03Daten = new HashMap<>(){{
			put("ERK_ID", "FB22");
		}};

		erkundungsstelle3 = new Erkundungsstelle(erk03Daten);


	}

	@Test
	public void sortWithText()
	{
		List<Erkundungsstelle> erkundungsstellen = new ArrayList<>();

		erkundungsstellen.add(erkundungsstelleFB1);
		erkundungsstellen.add(erkundungsstelleFB2);
		erkundungsstellen.add(erkundungsstelleFB17);
		erkundungsstellen.add(erkundungsstelleFB22);
		erkundungsstellen.add(erkundungsstelleFB3);
		erkundungsstellen.add(erkundungsstelleFB4);


		List<Erkundungsstelle> erkundungsstellenCompareTo = new ArrayList<>();

		erkundungsstellenCompareTo.add(erkundungsstelleFB1);
		erkundungsstellenCompareTo.add(erkundungsstelleFB2);
		erkundungsstellenCompareTo.add(erkundungsstelleFB3);
		erkundungsstellenCompareTo.add(erkundungsstelleFB4);
		erkundungsstellenCompareTo.add(erkundungsstelleFB17);
		erkundungsstellenCompareTo.add(erkundungsstelleFB22);

		Collections.sort(erkundungsstellen);

		Assert.assertEquals(erkundungsstellenCompareTo, erkundungsstellen);
	}

	@Test
	public void sortWithOutText()
	{
		List<Erkundungsstelle> erkundungsstellen = new ArrayList<>();

		erkundungsstellen.add(erkundungsstelle3);
		erkundungsstellen.add(erkundungsstelle2);
		erkundungsstellen.add(erkundungsstelle1);



		List<Erkundungsstelle> erkundungsstellenCompareTo = new ArrayList<>();

		erkundungsstellenCompareTo.add(erkundungsstelle1);
		erkundungsstellenCompareTo.add(erkundungsstelle2);
		erkundungsstellenCompareTo.add(erkundungsstelle3);


		Collections.sort(erkundungsstellen);

		Assert.assertEquals(erkundungsstellenCompareTo, erkundungsstellen);
	}
}
