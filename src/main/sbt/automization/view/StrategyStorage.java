package sbt.automization.view;

import sbt.automization.core.templates.HtmlTemplate;
import sbt.automization.core.templates.appendix.*;
import sbt.automization.core.templates.basic.Coordinates;
import sbt.automization.core.templates.report.*;

import java.util.ArrayList;
import java.util.List;

public final class StrategyStorage
{
	private static StrategyStorage instance;

	private final List<HtmlTemplate> strategyList;

	private StrategyStorage()
	{
		strategyList = new ArrayList<>();
	}

	public static StrategyStorage getInstance()
	{
		if (instance == null)
		{
			synchronized (StrategyStorage.class)
			{
				if (instance == null)
				{
					instance = new StrategyStorage();
				}
			}
		}
		return instance;
	}

	public void addStrategy(String strategyName)
	{
		switch (strategyName)
		{
			case "ANLAGE_PN":
				strategyList.add(SamplingProtocol.getInstance());
				break;
			case "ANLAGE_RUK":
				strategyList.add(RingAndBall.getInstance());
				break;
			case "ANLAGE_ERK":
				strategyList.add(ExplorationSite.getInstance());
				break;
			case "ANLAGE_LP":
				strategyList.add(LoadPlate.getInstance());
				break;
			case "ANLAGE_PN_HAUFWERK":
				strategyList.add(SamplingProtocolHeap.getInstance());
				break;
			case "ANLAGE_PN_GEBÄUDE":
				strategyList.add(SamplingProtocolBuilding.getInstance());
				break;
			case "BERICHT_GOB":
				strategyList.add(BoundSuperstructure.getInstance());
				break;
			case "BERICHT_TOB":
				strategyList.add(BaseCourseWithoutBinder.getInstance());
				break;
			case "BERICHT_UG":
				strategyList.add(Underground.getInstance());
				break;
			case "BERICHT_OH":
				strategyList.add(Topsoil.getInstance());
				break;
			case "BERICHT_TMHB":
				strategyList.add(BaseCourseWithHydraulicBinder.getInstance());
				break;
			case "BERICHT_BETON":
				strategyList.add(Concrete.getInstance());
				break;
			case "BERICHT_FUGE":
				strategyList.add(Gap.getInstance());
				break;
			case "BERICHT_HAUFWERK":
				strategyList.add(Heap.getInstance());
				break;
			case "BERICHT_KOORDINATEN":
				strategyList.add(Coordinates.getInstance());
				break;
			case "BERICHT_BANKETT":
				strategyList.add(Banquet.getInstance());
				break;
			case "BERICHT_GEBÄUDE":
				strategyList.add(Building.getInstance());
				break;
		}
	}

	public void removeStrategy(String strategyName)
	{
		switch (strategyName)
		{
			case "ANLAGE_PN":
				strategyList.remove(SamplingProtocol.getInstance());
				break;
			case "ANLAGE_RUK":
				strategyList.remove(RingAndBall.getInstance());
				break;
			case "ANLAGE_ERK":
				strategyList.remove(ExplorationSite.getInstance());
				break;
			case "ANLAGE_LP":
				strategyList.remove(LoadPlate.getInstance());
				break;
			case "ANLAGE_PN_HAUFWERK":
				strategyList.remove(SamplingProtocolHeap.getInstance());
				break;
			case "ANLAGE_PN_GEBÄUDE":
				strategyList.remove(SamplingProtocolBuilding.getInstance());
				break;
			case "BERICHT_GOB":
				strategyList.remove(BoundSuperstructure.getInstance());
				break;
			case "BERICHT_TOB":
				strategyList.remove(BaseCourseWithoutBinder.getInstance());
				break;
			case "BERICHT_UG":
				strategyList.remove(Underground.getInstance());
				break;
			case "BERICHT_OH":
				strategyList.remove(Topsoil.getInstance());
				break;
			case "BERICHT_TMHB":
				strategyList.remove(BaseCourseWithHydraulicBinder.getInstance());
				break;
			case "BERICHT_BETON":
				strategyList.remove(Concrete.getInstance());
				break;
			case "BERICHT_FUGE":
				strategyList.remove(Gap.getInstance());
				break;
			case "BERICHT_HAUFWERK":
				strategyList.remove(Heap.getInstance());
				break;
			case "BERICHT_KOORDINATEN":
				strategyList.remove(Coordinates.getInstance());
				break;
			case "BERICHT_BANKETT":
				strategyList.remove(Banquet.getInstance());
				break;
			case "BERICHT_GEBÄUDE":
				strategyList.remove(Building.getInstance());
				break;
		}
	}

	public List<HtmlTemplate> getStrategies()
	{
		return strategyList;
	}
}
