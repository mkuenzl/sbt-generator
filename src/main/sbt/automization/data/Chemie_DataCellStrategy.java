package sbt.automization.data;

import sbt.automization.engine.AErkundungsstelle;
import sbt.automization.engine.ASchicht;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlText;

public class Chemie_DataCellStrategy extends ADataCellStrategy
{
    private static Chemie_DataCellStrategy instance;

    private Chemie_DataCellStrategy(){}

    public static Chemie_DataCellStrategy getInstance(){
        if (instance == null)
        {
            synchronized (Chemie_DataCellStrategy.class)
            {
                if (instance == null)
                {
                    instance = new Chemie_DataCellStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public HtmlCell getDataCell(final AErkundungsstelle erkundungsstelle)
    {
        return null;
    }

    @Override
    public HtmlCell getDataCell(final ASchicht schicht)
    {
        return null;
    }

    @Override
    public HtmlCell getDataCell(final String data)
    {
        HtmlCell htmlCell;

        switch (data){
            case "Z0":
            case "DK0":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieWhite")
                        .appendContent(data)
                        .build();
                break;
            case "Z0*":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieBlue")
                        .appendContent(data)
                        .build();
                break;
            case "Z1":
            case "Z1.1":
            case "RC1":
            case "DK1":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieGreen")
                        .appendContent(data)
                        .build();
                break;
            case "Z1.2":
            case "RC2":
            case "DK2":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieYellow")
                        .appendContent(data)
                        .build();
                break;
            case "Z2":
            case "RC3":
            case "DK3":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieRed")
                        .appendContent(data)
                        .build();
                break;
            case ">Z2":
            case "gefährlich":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieBlack")
                        .appendContent(data)
                        .build();
                break;
            case "nicht gefährlich":
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "ChemieWhite")
                        .appendContent("nicht")
                        .appendContent(new HtmlText.Builder().appendAttribute("class","Normal").appendContent("gefährlich").build().appendTag())
                        .build();
                break;
            default:
                htmlCell = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(data)
                        .build();
                break;
        }

        return htmlCell;
    }
}
