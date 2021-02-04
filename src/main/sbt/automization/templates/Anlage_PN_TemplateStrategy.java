package sbt.automization.templates;

import sbt.automization.data.PNProbenart_DataFormatStrategy;
import sbt.automization.data.TiefeVonBis_DataFormatStrategy;
import sbt.automization.engine.AErkundungsstelle;
import sbt.automization.engine.ASchicht;
import sbt.automization.templates.styles.TableStyle;
import sbt.automization.util.html.*;

import java.util.ArrayList;
import java.util.List;

public final class Anlage_PN_TemplateStrategy extends AHtmlTemplateStrategy
{
    private static Anlage_PN_TemplateStrategy instance;
    private int counter = 1;

    private Anlage_PN_TemplateStrategy(){}

    public static Anlage_PN_TemplateStrategy getInstance(){
        if (instance == null)
        {
            synchronized (Anlage_PN_TemplateStrategy.class)
            {
                if (instance == null)
                {
                    instance = new Anlage_PN_TemplateStrategy();
                }
            }
        }
        return instance;
    }

    public List<ASchicht> formatSchichtList(List<ASchicht> schichtListe){

        List<ASchicht> schichtList = new ArrayList<>();
        for (ASchicht schicht : schichtListe)
        {
            try
            {
                schichtList.add((ASchicht) schicht.clone());
            } catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < schichtList.size(); i++)
        {
            if ("GOB".equals(schichtList.get(i).getInformation("SCHICHT_AUFSCHLUSS"))){
                if (schichtList.get(i).getInformation("SCHICHT_ABFALLART").equals(schichtList.get(i+1).getInformation("SCHICHT_ABFALLART")))
                {
                    schichtList.get(i+1).setInformation("SCHICHT_TIEFE_START", schichtList.get(i).getInformation("SCHICHT_TIEFE_START"));
                    schichtList.get(i+1).setInformation("SCHICHT_KOERNUNG", "");
                    schichtList.remove(schichtList.get(i));
                    i--;
                }
            }
        }
        return schichtList;
    }

    @Override
    public void buildHtmlTable(final List<AErkundungsstelle> data)
    {
        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", TableStyle.TABLE_STYLE1.getAttributes())
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(setHtmlTableHeader())
                .build();

        int rowCounter = 0;
        StringBuilder stringBuilder = new StringBuilder();

        for (AErkundungsstelle erkundungsstelle : data)
        {
            List<ASchicht> schichtList = formatSchichtList(erkundungsstelle.getSchichtList());

            for (ASchicht schicht : schichtList)
            {

                if (rowCounter >= 20){
                    stringBuilder.append(table.appendTag())
                            .append("<br>")
                            .append("<br>");

                    table = new HtmlTable.Builder()
                            .appendAttribute("class", "MsoNormalTable")
                            .appendAttribute("width", "605")
                            .appendAttribute("border", "1")
                            .appendAttribute("style", TableStyle.TABLE_STYLE1.getAttributes())
                            .appendAttribute("cellspacing", "0")
                            .appendAttribute("cellpadding", "0")
                            .appendContent(setHtmlTableHeader())
                            .build();

                    rowCounter = 0;
                }


                HtmlCell cell1 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent("P".concat(String.valueOf(counter++)))
                        .build();

                HtmlCell cell2 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent(PNProbenart_DataFormatStrategy.getInstance().getDataFormat(schicht))
                        .build();

                HtmlCell cell3 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(schicht.getInformation("SCHICHT_BEHAELTNIS"))
                        .build();

                HtmlCell cell5 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent("")
                        .build();

                //HIER MUSS MEHR CODE STRATEGY?? TODO
                HtmlCell cell6 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendAttribute("width", "110")
                        .appendContent(schicht.getInformation("SCHICHT_ABFALLART"))
                        .build();

                HtmlCell cell7 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendAttribute("width", "50")
                        .appendContent(schicht.getInformation("SCHICHT_KOERNUNG"))
                        .build();

                HtmlCell cell8 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(schicht.getInformation("SCHICHT_FARBE"))
                        .appendContent(new HtmlText.Builder().appendAttribute("class","Normal").appendContent(schicht.getInformation("SCHICHT_GERUCH")).build().appendTag())
                        .build();

                HtmlCell cell9 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendAttribute("width", "30")
                        .appendContent(erkundungsstelle.getInformation("ERK_ID"))
                        .build();

                HtmlCell cell10 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent(TiefeVonBis_DataFormatStrategy.getInstance().getDataFormat(schicht))
                        .build();

                HtmlCell cell11 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalCentered")
                        .appendContent(erkundungsstelle.getInformation("ERK_OBERKANTE"))
                        .build();

                HtmlRow row = new HtmlRow.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(cell1.appendTag())
                        .appendContent(cell2.appendTag())
                        .appendContent(cell3.appendTag())
 //                       .appendContent(cell4.appendTag())
                        .appendContent(cell5.appendTag())
                        .appendContent(cell6.appendTag())
                        .appendContent(cell7.appendTag())
                        .appendContent(cell8.appendTag())
                        .appendContent(cell9.appendTag())
                        .appendContent(cell10.appendTag())
                        .appendContent(cell11.appendTag())
                        .build();

                rowCounter++;

                table.appendContent(row.appendTag());
            }
        }

        stringBuilder.append(table.appendTag());

        setHtmlTable(stringBuilder.toString());
    }



    @Override
    public void buildHtmlTable(final AErkundungsstelle data)
    {

    }

    @Override
    String setHtmlTableHeader()
    {
        HtmlTableHeader cell1 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "40")
                .appendAttribute("rowspan", "2")
                .appendContent("Probe")
                .build();

        HtmlTableHeader cell2 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "40")
                .appendAttribute("rowspan", "2")
                .appendContent("Art")
                .build();

        HtmlTableHeader cell3 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "105")
                .appendContent("Behältnis")
                .appendContent(new HtmlText.Builder().appendAttribute("class","Normal").appendContent("Vol.").build().appendTag())
                .build();

//        HtmlTableHeader cell4 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("width", "40")
//                .appendContent("Vol.")
//                .build();

        HtmlTableHeader cell5 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Haufwerk")
                .appendContent(new HtmlText.Builder().appendAttribute("class","Normal").appendContent("Vol.").build().appendTag())
                .build();

        HtmlTableHeader cell6 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "140")
                .appendAttribute("colspan", "2")
                .appendAttribute("rowspan", "2")
                .appendContent("Abfallart")
                .build();

        HtmlTableHeader cell7 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "76")
                .appendAttribute("rowspan", "2")
                .appendContent("Farbe")
                .appendContent(new HtmlText.Builder().appendAttribute("class","Normal").appendContent("Geruch").build().appendTag())
                .build();

        HtmlTableHeader cell8 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "30")
                .appendAttribute("rowspan", "2")
                .appendContent("Erk. St.")
                .build();

        HtmlTableHeader cell9 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "70")
                .appendContent("Tiefe")
                .build();

        HtmlTableHeader cell10 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "65")
                .appendAttribute("rowspan", "2")
                .appendContent("Notiz")
                .build();

//        HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("colspan", "2")
//                .appendContent("")
//                .build();

        HtmlTableHeader cell22 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("L")
                .build();

        HtmlTableHeader cell23 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("L")
                .build();

//        HtmlTableHeader cell24 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("colspan", "4")
//                .appendContent("")
//                .build();

        HtmlTableHeader cell25 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("cm")
                .build();

//        HtmlTableHeader cell26 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendContent("")
//                .build();


        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cell1.appendTag())
                .appendContent(cell2.appendTag())
                .appendContent(cell3.appendTag())
       //         .appendContent(cell4.appendTag())
                .appendContent(cell5.appendTag())
                .appendContent(cell6.appendTag())
                .appendContent(cell7.appendTag())
                .appendContent(cell8.appendTag())
                .appendContent(cell9.appendTag())
                .appendContent(cell10.appendTag())
                .build();

        HtmlRow row2 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeaderUnits")
             //   .appendContent(cell21.appendTag())
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
              //  .appendContent(cell24.appendTag())
                .appendContent(cell25.appendTag())
            //    .appendContent(cell26.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(row1.appendTag())
                .append(row2.appendTag());

        return stringBuilder.toString();
    }

    @Override
    public String getExportFileName()
    {
        return "PN_Tabelle.html";
    }
}
