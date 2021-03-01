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
                strategyList.add(Anlage_PN_Template.getInstance());
                break;
            case "RUK_TABELLE":
                strategyList.add(Anlage_RUK_Template.getInstance());
                break;
            case "ERK_TABELLE":
                strategyList.add(Anlage_ERK_Template.getInstance());
                break;
            case "LP_TABELLE":
                strategyList.add(Anlage_LP_Template.getInstance());
                break;
            case "BERICHT_TABELLEN":
                strategyList.add(Bericht_OB_Template.getInstance());
                break;
        }
    }

    //TODO
    public void removeStrategy(String strategyName)
    {
        switch (strategyName)
        {
            case "PN_TABELLE":
                strategyList.remove(Anlage_PN_Template.getInstance());
                break;
            case "RUK_TABELLE":
                strategyList.remove(Anlage_RUK_Template.getInstance());
                break;
            case "ERK_TABELLE":
                strategyList.remove(Anlage_ERK_Template.getInstance());
                break;
            case "LP_TABELLE":
                strategyList.remove(Anlage_LP_Template.getInstance());
                break;
            case "BERICHT_TABELLEN":
                strategyList.remove(Bericht_OB_Template.getInstance());
                break;
        }
    }

    public List<IHtmlTemplate> getStrategies()
    {
        return strategyList;
    }
}
