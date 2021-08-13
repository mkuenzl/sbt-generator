package sbt.automization.templates.appendix;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.ReferenceKey;
import sbt.automization.data.LayerSample;
import sbt.automization.format.HtmlCellFormatUtil;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.*;

import java.util.List;

final class AppendixSiteTOB extends AppendixTemplate
{
	private String outcrop = "";

	@Override
	public void constructTable(final List<ExplorationSite> sites)
	{

	}

	@Override
	public void constructTable(final ExplorationSite site)
	{
		outcrop = site.getInformation(ReferenceKey.SITE_OUTCROP_TOB);

		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();

		boolean lpPrinted = false;

		for (LayerSample layerSample : site.getLayers())
		{
			if ("TOB".equals(layerSample.getInformation(ReferenceKey.LAYER_OUTCROP).toUpperCase()))
			{
				//Art der Schicht
				HtmlCell cell1 = new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(layerSample.getInformation(ReferenceKey.LAYER_TYPE))
						.appendContent(new HtmlText.Builder().appendAttribute("class", "Normal")
								.appendContent(layerSample.getInformation(ReferenceKey.LAYER_GRANULATION))
								.appendContent(" ")
								.appendContent(layerSample.getInformation(ReferenceKey.LAYER_ROUNDING_GRADATION))
								.build()
								.appendTag())
						.build();

				//Dicke
				HtmlCell cell2 = new HtmlCell.Builder()
						.appendAttribute("class", "NormalErkundungsstelle")
						.appendContent(layerSample.getInformation(ReferenceKey.LAYER_THICKNESS))
						.build();

				//Tiefe
				HtmlCell cell3 = new HtmlCell.Builder()
						.appendAttribute("class", "NormalErkundungsstelle")
						.appendContent(layerSample.getInformation(ReferenceKey.LAYER_DEPTH_END))
						.build();

				//MUFV
				String chemie_mufv = layerSample.getInformation(ReferenceKey.CHEMISTRY_MUFV);
				HtmlCell cell4 = HtmlCellFormatUtil.formatChemistry(chemie_mufv);

				//LAGA_BO
				String chemie_laga_bo = layerSample.getInformation(ReferenceKey.CHEMISTRY_LAGA_BO);
				HtmlCell cell5 = HtmlCellFormatUtil.formatChemistry(chemie_laga_bo);

				//LAGA_RC
				String chemie_laga_rc = layerSample.getInformation(ReferenceKey.CHEMISTRY_LAGA_RC);
				HtmlCell cell6 = HtmlCellFormatUtil.formatChemistry(chemie_laga_rc);

				//TL_GESTEIN
				String chemie_tlgestein = layerSample.getInformation(ReferenceKey.CHEMISTRY_TL_ROCK_STRATUM);
				HtmlCell cell7 = HtmlCellFormatUtil.formatChemistry(chemie_tlgestein);

				//LP_DYN
				String erk_lp_ev2 = site.getInformation(ReferenceKey.SITE_LP_EV2);
				HtmlCell cellLP_DYN;
				if (erk_lp_ev2.equals("-") || lpPrinted)
				{
					cellLP_DYN = new HtmlCell.Builder()
							.appendAttribute("class", "NormalErkundungsstelle")
							.appendContent("-")
							.build();
				} else
				{
					cellLP_DYN = new HtmlCell.Builder()
							.appendAttribute("class", "NormalErkundungsstelle")
							.appendContent(erk_lp_ev2)
							.appendContent(TextFormatUtil.printLineBreak())
							.appendContent(site.getInformation(ReferenceKey.SITE_LP_EV85))
							.build();

					lpPrinted = true;
				}


				HtmlCell cell9 = new HtmlCell.Builder()
						.appendAttribute("class", "NormalErkundungsstelle")
						.appendContent(layerSample.getInformation(ReferenceKey.LAYER_GRAIN_SIZE_DISTRIBUTION))
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
						.appendContent(cellLP_DYN.appendTag())
						.appendContent(cell9.appendTag())
						.build();

				table.appendContent(row.appendTag());
			}
		}
		addToTemplate(table.appendTag());
	}

	@Override
	String constructAndGetTableHeader()
	{
		//First Row
		HtmlTableHeader cell11 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "125")
				.appendAttribute("align", "left")
				.appendContent("Tragschicht ohne")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("Bindemittel")
				.build();


		HtmlTableHeader cell12 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("colspan", "8")        //Zelle geht über 3 Reihen
				.appendAttribute("align", "left")
				.appendContent("Aufschlussverfahren:".concat(" ").concat(outcrop))
				.build();


		//Second Row
		HtmlTableHeader cell21 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "125")
				.appendAttribute("align", "left")
				.appendAttribute("rowspan", "2")
				.appendContent("Art der Schicht")
				.build();

		HtmlTableHeader cell22 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendContent("Dicke")
				.appendContent("<div>[7]</div>")
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
				.appendContent("<div>[18]</div>")
				.build();

		HtmlTableHeader lagaBoHeader = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendAttribute("rowspan", "2")
				.appendContent("LAGA BO")
				.appendContent("<div>[11]</div>")
				.build();

		HtmlTableHeader cell26 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendAttribute("rowspan", "2")
				.appendContent("LAGA RC")
				.appendContent("<div>[28]</div>")
				.build();

		HtmlTableHeader cell27 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendAttribute("rowspan", "2")
				.appendContent("TL Ge.")
				.appendContent("<div>[27]</div>")
				.build();

		HtmlTableHeader cell28 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendContent("E<sub>V2</sub>")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("E<sub>Vdyn</sub>")
				.appendContent(TextFormatUtil.printLineBreak())
				.appendContent("<sub>(-15%)</sub>")
				.appendContent("<div>[41]</div>")
				.build();


		HtmlTableHeader cell29 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeader")
				.appendAttribute("width", "60")
				.appendContent("KGV")
				.appendContent("<div>[25]</div>")
				.build();

		HtmlTableHeader cell32 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeaderUnits")
				.appendAttribute("width", "60")
				.appendContent("cm")
				.build();

		HtmlTableHeader cell33 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeaderUnits")
				.appendAttribute("width", "60")
				.appendContent("cm")
				.build();

		HtmlTableHeader cell37 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeaderUnits")
				.appendAttribute("width", "60")
				.appendContent("MN/m²")
				.build();

		HtmlTableHeader cell38 = new HtmlTableHeader.Builder()
				.appendAttribute("class", "NormalTableHeaderUnits")
				.appendAttribute("width", "60")
				.appendContent("M.-%")
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
				.appendContent(lagaBoHeader.appendTag())
				.appendContent(cell26.appendTag())
				.appendContent(cell27.appendTag())
				.appendContent(cell28.appendTag())
				.appendContent(cell29.appendTag())
				.build();

		HtmlRow row3 = new HtmlRow.Builder()
				.appendAttribute("class", "NormalHeaderUnits")
				.appendContent(cell32.appendTag())
				.appendContent(cell33.appendTag())
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
	HtmlTable constructAndGetTableObject()
	{
		HtmlTable table = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("width", "605")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.appendContent(constructAndGetTableHeader())
				.build();

		return table;
	}

	@Override
	public String getExportFileName()
	{
		return null;
	}
}
