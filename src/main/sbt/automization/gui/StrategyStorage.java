package sbt.automization.gui;

import sbt.automization.templates.*;

import java.util.ArrayList;
import java.util.List;

public class StrategyStorage
{
    private static StrategyStorage instance;

    private final List<IHtmlTable> strategyList;

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
                strategyList.add(AppendixPN.getInstance());
                break;
            case "ANLAGE_RUK":
                strategyList.add(AppendixRUK.getInstance());
                break;
            case "ANLAGE_ERK":
                strategyList.add(AppendixExplorationSite.getInstance());
                break;
            case "ANLAGE_LP":
                strategyList.add(AppendixLP.getInstance());
                break;
            case "ANLAGE_PN_HEAP":
                strategyList.add(AppendixPNHEAP.getInstance());
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
            case "BERICHT_TMHB":
                strategyList.add(ReportTMHB.getInstance());
                break;
            case "BERICHT_BETON":
                strategyList.add(ReportCONCRETE.getInstance());
                break;
            case "BERICHT_FUGE":
                strategyList.add(ReportFUGE.getInstance());
                break;
            case "BERICHT_HAUFWERK":
                strategyList.add(ReportHEAP.getInstance());
                break;
            case "BERICHT_KOORDINATEN":
                strategyList.add(ReportCOORDINATES.getInstance());
                break;
        }
    }

    public void removeStrategy(String strategyName)
    {
        switch (strategyName)
        {
            case "ANLAGE_PN":
                strategyList.remove(AppendixPN.getInstance());
                break;
            case "ANLAGE_RUK":
                strategyList.remove(AppendixRUK.getInstance());
                break;
            case "ANLAGE_ERK":
                strategyList.remove(AppendixExplorationSite.getInstance());
                break;
            case "ANLAGE_LP":
                strategyList.remove(AppendixLP.getInstance());
                break;
            case "ANLAGE_PN_HEAP":
                strategyList.remove(AppendixPNHEAP.getInstance());
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
            case "BERICHT_TMHB":
                strategyList.remove(ReportTMHB.getInstance());
                break;
            case "BERICHT_BETON":
                strategyList.remove(ReportCONCRETE.getInstance());
                break;
            case "BERICHT_FUGE":
                strategyList.remove(ReportFUGE.getInstance());
                break;
            case "BERICHT_HAUFWERK":
                strategyList.remove(ReportHEAP.getInstance());
                break;
            case "BERICHT_KOORDINATEN":
                strategyList.remove(ReportCOORDINATES.getInstance());
                break;
        }
    }

    public List<IHtmlTable> getStrategies()
    {
        return strategyList;
    }
}
