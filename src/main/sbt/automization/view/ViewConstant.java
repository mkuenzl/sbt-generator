package sbt.automization.view;

import sbt.automization.core.templates.HtmlTemplate;
import sbt.automization.view.element.TextInputArea;

import java.util.ArrayList;
import java.util.List;

public class ViewConstant
{
    public static TextInputArea pathComponent;

    public static String dataSet = "";

    public static List<HtmlTemplate> strategyList = new ArrayList<>();

    private ViewConstant(){}
}
