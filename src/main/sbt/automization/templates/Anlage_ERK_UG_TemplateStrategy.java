package sbt.automization.templates;

import sbt.automization.data.Chemie_DataCellStrategy;
import sbt.automization.data.WasserProctor_DataFormatStrategy;
import sbt.automization.projekt.AErkundungsstelle;
import sbt.automization.projekt.ASchicht;
import sbt.automization.templates.styles.TableStyle;
import sbt.automization.util.html.*;

import java.util.List;

class Anlage_ERK_UG_TemplateStrategy extends AHtmlTemplateStrategy
{
    private String aufschluss = "";

    @Override
    String setHtmlTableHeader()
    {
        //First Row
        HtmlTableHeader cell11 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "185")
                .appendAttribute("align", "left")
                .appendContent("Untergrund / Unterbau")
                .build();

        HtmlTableHeader cell12 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("align", "left")
                .appendAttribute("colspan", "7")        //Zelle geht über 3 Reihen
                .appendContent(aufschluss)
                .build();


        //Second Row
        HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("align", "left")
                .appendAttribute("width", "185")
                .appendContent("Bodengruppe")
                .build();

        HtmlTableHeader cell22 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Dicke")
                .build();

        HtmlTableHeader cell23 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Tiefe")
                .build();

        HtmlTableHeader cell24 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("LAGA BO")
                .build();

        HtmlTableHeader cell25 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("WG")
                .build();

        HtmlTableHeader cell26 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("WPR")
                .build();

        HtmlTableHeader cell27 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Proctor")
                .build();

        HtmlTableHeader cell28 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("width", "60")
                .appendContent("Notiz")
                .build();

        //Third Row
        HtmlTableHeader cell31 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendAttribute("align", "left")
                .appendContent("-")
                .build();

        HtmlTableHeader cell32 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("cm")
                .build();

        HtmlTableHeader cell33 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("cm")
                .build();

        HtmlTableHeader cell34 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("-")
                .build();

        HtmlTableHeader cell35 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("M%")
                .build();

        HtmlTableHeader cell36 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("M%")
                .build();

        HtmlTableHeader cell37 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("t/m3/M%")
                .build();

        HtmlTableHeader cell38 = new HtmlTableHeader.Builder()
                .appendAttribute("class", "NormalTableHeader")
                .appendContent("-")
                .build();

        HtmlRow row1 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cell11.appendTag())
                .appendContent(cell12.appendTag())
                .build();

        HtmlRow row2 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeader")
                .appendContent(cell21.appendTag())
                .appendContent(cell22.appendTag())
                .appendContent(cell23.appendTag())
                .appendContent(cell24.appendTag())
                .appendContent(cell25.appendTag())
                .appendContent(cell26.appendTag())
                .appendContent(cell27.appendTag())
                .appendContent(cell28.appendTag())
                .build();

        HtmlRow row3 = new HtmlRow.Builder()
                .appendAttribute("class", "NormalHeaderUnits")
                .appendContent(cell31.appendTag())
                .appendContent(cell32.appendTag())
                .appendContent(cell33.appendTag())
                .appendContent(cell34.appendTag())
                .appendContent(cell35.appendTag())
                .appendContent(cell36.appendTag())
                .appendContent(cell37.appendTag())
                .appendContent(cell38.appendTag())
                .build();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(row1.appendTag())
                .append(row2.appendTag())
                .append(row3.appendTag());

        return stringBuilder.toString();
    }

    @Override
    public void buildHtmlTable(final List<AErkundungsstelle> data)
    {

    }

    @Override
    public void buildHtmlTable(final AErkundungsstelle data)
    {
        aufschluss = data.getInformation("ERK_AUFSCHLUSS_UG");

        HtmlTable table = new HtmlTable.Builder()
                .appendAttribute("class", "MsoNormalTable")
                .appendAttribute("width", "605")
                .appendAttribute("border", "1")
                .appendAttribute("style", TableStyle.TABLE_STYLE1.getAttributes())
                .appendAttribute("cellspacing", "0")
                .appendAttribute("cellpadding", "0")
                .appendContent(setHtmlTableHeader())
                .build();

        for (ASchicht schicht : data.getSchichtList())
        {
            if ("UG".equals(schicht.getInformation("SCHICHT_AUFSCHLUSS"))){
                //Art der Schicht
                HtmlCell cell1 = new HtmlCell.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(schicht.getInformation("SCHICHT_ART").concat("  ").concat(schicht.getInformation("SCHICHT_KOERNUNG")))
                        .build();

                //Dicke
                HtmlCell cell2 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_DICKE"))
                        .build();

                //Tiefe
                HtmlCell cell3 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_TIEFE_ENDE"))
                        .build();

                //LAGA BO
                HtmlCell cell4 = Chemie_DataCellStrategy.getInstance().getDataCell(schicht.getInformation("CHEMIE_LAGA_BO"));

                //Wassergehalt
                HtmlCell cell5 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_WASSERGEHALT"))
                        .build();

                //WasserProctor
                HtmlCell cell6 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(WasserProctor_DataFormatStrategy.getInstance().getDataFormat(schicht))
                        .build();

                //Proctor
                HtmlCell cell7 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent("-")
                        .build();

                //Notiz
                HtmlCell cell8 = new HtmlCell.Builder()
                        .appendAttribute("class", "NormalErkundungsstelle")
                        .appendContent(schicht.getInformation("SCHICHT_BEMERKUNGEN"))
                        .build();

                HtmlRow row = new HtmlRow.Builder()
                        .appendAttribute("class", "Normal")
                        .appendContent(cell1.appendTag())
                        .appendContent(cell2.appendTag())
                        .appendContent(cell3.appendTag())
                        .appendContent(cell4.appendTag())
                        .appendContent(cell5.appendTag())
                        .appendContent(cell6.appendTag())
                        .appendContent(cell7.appendTag())
                        .appendContent(cell8.appendTag())
                        .build();

                table.appendContent(row.appendTag());
            }
        }

        setHtmlTable(table.appendTag());
    }

    @Override
    public String getExportFileName()
    {
        return null;
    }
}
