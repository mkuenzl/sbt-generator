package sbt.automization.gui;

import sbt.automization.templates.*;

import java.util.ArrayList;
import java.util.List;

public class StrategyStorage
{
    private static StrategyStorage instance;

    private List<IHtmlTemplateStrategy> strategyList;

    private StrategyStorage(){
        strategyList = new ArrayList<>();
    }

    public static StrategyStorage getInstance(){
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

    public void addStrategy(String strategyName){
        switch (strategyName){
            case "PN_TABELLE":
                strategyList.add(Anlage_PN_TemplateStrategy.getInstance());
                break;
            case "RUK_TABELLE":
                strategyList.add(Anlage_RUK_TemplateStrategy.getInstance());
                break;
            case "ERK_TABELLE":
                strategyList.add(Anlage_ERK_TemplateStrategy.getInstance());
                break;
            case "LP_TABELLE":
                strategyList.add(Anlage_LP_TemplateStrategy.getInstance());
                break;
        }
    }

    //TODO
    public void removeStrategy(String strategyName){
        switch (strategyName){
            case "PN_TABELLE":
                strategyList.remove(Anlage_PN_TemplateStrategy.getInstance());
                break;
            case "RUK_TABELLE":
                strategyList.remove(Anlage_RUK_TemplateStrategy.getInstance());
                break;
            case "ERK_TABELLE":
                strategyList.remove(Anlage_ERK_TemplateStrategy.getInstance());
                break;
            case "LP_TABELLE":
                strategyList.remove(Anlage_LP_TemplateStrategy.getInstance());
                break;
        }
    }

    public List<IHtmlTemplateStrategy> getStrategies(){
        return strategyList;
    }
}
