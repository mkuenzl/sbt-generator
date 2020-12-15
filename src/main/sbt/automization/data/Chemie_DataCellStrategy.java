package sbt.automization.data;

import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;
import sbt.automization.util.html.HtmlCell;

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
        String chemie = data;
        HtmlCell htmlCell = new HtmlCell.Builder()
                .appendContent(chemie)
                .build();

        switch (chemie){
            case "Z0":
            case "DK0":
                htmlCell.appendAttribute("class", "ChemieWhite");
                break;
            case "Z0*":
                htmlCell.appendAttribute("class", "ChemieBlue");
                break;
            case "Z1":
            case "Z1.1":
            case "RC1":
            case "DK1":
                htmlCell.appendAttribute("class", "ChemieGreen");
                break;
            case "Z1.2":
            case "RC2":
            case "DK2":
                htmlCell.appendAttribute("class", "ChemieYellow");
                break;
            case "Z2":
            case "RC3":
            case "DK3":
                htmlCell.appendAttribute("class", "ChemieRed");
                break;
            case ">Z2":
                htmlCell.appendAttribute("class", "ChemieBlack");
                break;
            default:
                htmlCell.appendAttribute("class", "Normal");
                break;
        }

        return htmlCell;
    }
}
