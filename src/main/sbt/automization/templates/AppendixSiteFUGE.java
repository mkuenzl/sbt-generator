package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlTableHeader;

import java.util.List;

final class AppendixSiteFUGE extends AHtmlTemplate
{
	private String outcrop = "";

	@Override
	public void buildHtmlTable(final List<ExplorationSite> sites)
	{

	}

	@Override
	public void buildHtmlTable(final ExplorationSite site)
	{
		outcrop = site.getInformation("ERK_AUFSCHLUSS_UG_OH_BA");

		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(setHtmlTableHeader())
				.build();

		for (Layer layer : site.getSchichtList())
		{
			if ("FUGE".equals(layer.getInformation("SCHICHT_AUFSCHLUSS")))
			{
				//Art der Schicht
				HtmlCell schichtArt = new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(layer.getInformation("SCHICHT_ART"))
						.build();

				//Dicke
				HtmlCell schichtDicke = new HtmlCell.Builder()
						.appendAttribute("class", "NormalErkundungsstelle")
						.appendContent(layer.getInformation("SCHICHT_DICKE"))
						.build();

				//Tiefe
				HtmlCell schichtTiefe = new HtmlCell.Builder()
						.appendAttribute("class", "NormalErkundungsstelle")
						.appendContent(layer.getInformation("SCHICHT_TIEFE_ENDE"))
						.build();

				//MUFV
				String chemie_mufv = layer.getInformation("CHEMIE_MUFV");
				HtmlCell schichtMufv = HtmlCellFormatUtil.formatChemistry(chemie_mufv);

				//PAK
				HtmlCell schichtPak = new HtmlCell.Builder()
						.appendAttribute("class", "NormalErkundungsstelle")
						.appendContent(layer.getInformation("SCHICHT_PAK"))
						.build();

				//Asbest
				String chemie_asbest = layer.getInformation("CHEMIE_ASBEST");
				HtmlCell schichtAsbest = HtmlCellFormatUtil.formatChemistry(chemie_asbest);

				//empty
				HtmlCell cell7 = new HtmlCell.Builder()
						.appendAttribute("class", "NormalErkundungsstelle")
						.appendContent("")
						.build();

				//empty
				HtmlCell cell8 = new HtmlCell.Builder()
						.appendAttribute("class", "NormalErkundungsstelle")
						.appendContent("")
						.build();

				//Bemerkung
				HtmlCell schichtBemerkungen = new HtmlCell.Builder()
						.appendAttribute("class", "NormalErkundungsstelle")
						.appendContent("-")
						.build();

				HtmlRow row = new HtmlRow.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(schichtArt.appendTag())
						.appendContent(schichtDicke.appendTag())
						.appendContent(schichtTiefe.appendTag())
						.appendContent(schichtMufv.appendTag())
						.appendContent(schichtPak.appendTag())
						.appendContent(schichtAsbest.appendTag())
						.appendContent(cell7.appendTag())
						.appendContent(cell8.appendTag())
						.appendContent(schichtBemerkungen.appendTag())
						.build();

				table.appendContent(row.appendTag());
			}
		}

		setHtmlTable(table.appendTag());
	}

	@Override
	String setHtmlTableHeader()
	{
		//First Row
		HtmlTableHeader cell11 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "125")
				.appendAttribute("align", "left")
				.appendContent("Fuge")
				.build();

		HtmlTableHeader cell12 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("align", "left")
				.appendAttribute("colspan", "8")        //Zelle geht über 3 Reihen
				.appendContent("Aufschlussverfahren:".concat(" ").concat(outcrop))
				.build();


		//Second Row
		HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("align", "left")
				.appendAttribute("width", "125")
				.appendAttribute("rowspan", "2")
				.appendContent("Art der Schicht")
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
				.appendAttribute("rowspan", "2")
				.appendContent("MUFV")
				.build();

		HtmlTableHeader cell25 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendContent("PAK")
				.build();

		HtmlTableHeader cell26 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendAttribute("rowspan", "2")
				.appendContent("Asbest")
				.build();

		HtmlTableHeader cell27 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendAttribute("rowspan", "2")
				.appendContent("")
				.build();

		HtmlTableHeader cell28 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendAttribute("rowspan", "2")
				.appendContent("")
				.build();

		HtmlTableHeader cell29 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendAttribute("rowspan", "2")
				.appendContent("Notiz")
				.build();


		//Third Row
//        HtmlTableHeader cell31 = new HtmlTableHeader.Builder()
//                .appendAttribute("class", "NormalTableHeader")
//                .appendAttribute("align", "left")
//                .appendContent("-")
//                .build();

		HtmlTableHeader cell32 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeaderUnits")
				.appendContent("cm")
				.build();

		HtmlTableHeader cell33 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeaderUnits")
				.appendContent("cm")
				.build();

		HtmlTableHeader cell35 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeaderUnits")
				.appendContent("mg/kg")
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
				.appendContent(cell29.appendTag())
				.build();

		HtmlRow row3 = new HtmlRow.Builder()
				.appendAttribute("class", "NormalHeaderUnits")
				.appendContent(cell32.appendTag())
				.appendContent(cell33.appendTag())
				.appendContent(cell35.appendTag())
				.build();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(row1.appendTag())
				.append(row2.appendTag())
				.append(row3.appendTag());

		return stringBuilder.toString();
	}

	@Override
	public String getExportFileName()
	{
		return null;
	}
}