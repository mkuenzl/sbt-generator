package sbt.automization.gui;

import sbt.automization.templates.*;

import java.util.ArrayList;
import java.util.List;

public class StrategyStorage
{
    private static StrategyStorage instance;

    private final List<IHtmlTemplate> strategyList;

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
            case "PN_TABELLE":
                strategyList.add(AppendixPN.getInstance());
                break;
            case "RUK_TABELLE":
                strategyList.add(AppendixRuk.getInstance());
                break;
            case "ERK_TABELLE":
                strategyList.add(AppendixExplorationSite.getInstance());
                break;
            case "LP_TABELLE":
                strategyList.add(AppendixLP.getInstance());
                break;
            case "BERICHT_GOB":
                strategyList.add(GOBReport.getInstance());
                break;
            case "BERICHT_TOB":
                strategyList.add(TOBReport.getInstance());
                break;
            case "BERICHT_UG":
                strategyList.add(UGReport.getInstance());
                break;
            case "BERICHT_OH":
                strategyList.add(OHReport.getInstance());
                break;
            case "BERICHT_TMBH_TEST":
                strategyList.add(TMHBReport.getInstance());
                break;
            case "BERICHT_BETON_TEST":
                strategyList.add(CONCRETEReport.getInstance());
                break;
            case "BERICHT_FUGE_TEST":
                strategyList.add(FUGEReport.getInstance());
                break;
        }
    }

    //TODO
    public void removeStrategy(String strategyName)
    {
        switch (strategyName)
        {
            case "PN_TABELLE":
                strategyList.remove(AppendixPN.getInstance());
                break;
            case "RUK_TABELLE":
                strategyList.remove(AppendixRuk.getInstance());
                break;
            case "ERK_TABELLE":
                strategyList.remove(AppendixExplorationSite.getInstance());
                break;
            case "LP_TABELLE":
                strategyList.remove(AppendixLP.getInstance());
                break;
            case "BERICHT_GOB":
                strategyList.remove(GOBReport.getInstance());
                break;
            case "BERICHT_TOB":
                strategyList.remove(TOBReport.getInstance());
                break;
            case "BERICHT_UG":
                strategyList.remove(UGReport.getInstance());
                break;
            case "BERICHT_OH":
                strategyList.remove(OHReport.getInstance());
                break;
            case "BERICHT_TMBH_TEST":
                strategyList.remove(TMHBReport.getInstance());
                break;
            case "BERICHT_BETON_TEST":
                strategyList.remove(CONCRETEReport.getInstance());
                break;
            case "BERICHT_FUGE_TEST":
                strategyList.remove(FUGEReport.getInstance());
                break;
        }
    }

    public List<IHtmlTemplate> getStrategies()
    {
        return strategyList;
    }
}
