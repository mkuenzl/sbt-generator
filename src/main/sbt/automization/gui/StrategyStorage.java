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
                strategyList.add(ReportGOB.getInstance());
                break;
            case "BERICHT_TOB":
                strategyList.add(ReportTOB.getInstance());
                break;
            case "BERICHT_UG":
                strategyList.add(ReportUG.getInstance());
                break;
            case "BERICHT_OH":
                strategyList.add(ReportOH.getInstance());
                break;
            case "BERICHT_TMHB_TEST":
                strategyList.add(ReportTMHB.getInstance());
                break;
            case "BERICHT_BETON_TEST":
                strategyList.add(ReportCONCRETE.getInstance());
                break;
            case "BERICHT_FUGE_TEST":
                strategyList.add(ReportFUGE.getInstance());
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
                strategyList.remove(ReportGOB.getInstance());
                break;
            case "BERICHT_TOB":
                strategyList.remove(ReportTOB.getInstance());
                break;
            case "BERICHT_UG":
                strategyList.remove(ReportUG.getInstance());
                break;
            case "BERICHT_OH":
                strategyList.remove(ReportOH.getInstance());
                break;
            case "BERICHT_TMHB_TEST":
                strategyList.remove(ReportTMHB.getInstance());
                break;
            case "BERICHT_BETON_TEST":
                strategyList.remove(ReportCONCRETE.getInstance());
                break;
            case "BERICHT_FUGE_TEST":
                strategyList.remove(ReportFUGE.getInstance());
                break;
        }
    }

    public List<IHtmlTemplate> getStrategies()
    {
        return strategyList;
    }
}
