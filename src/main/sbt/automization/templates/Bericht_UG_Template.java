package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.templates.helper.Bericht_UG_Factory;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlTable;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public final class Bericht_UG_Template extends AHtmlTemplate
{
	private static Bericht_UG_Template instance;

	private Bericht_UG_Template() {}

	public static Bericht_UG_Template getInstance()
	{
		if (instance == null)
		{
			synchronized (Bericht_UG_Template.class)
			{
				if (instance == null)
				{
					instance = new Bericht_UG_Template();
				}
			}
		}
		return instance;
	}

	@Override
	String setHtmlTableHeader()
	{
		return null;
	}

	@Override
	public void buildHtmlTable(List<Erkundungsstelle> erkundungsstellen)
	{
		//Sort Data nach TOB
		HtmlTable tableBericht = new HtmlTable.Builder()
				.appendAttribute("class", "MsoNormalTable")
				.appendAttribute("border", "1")
				.appendAttribute("style", HTML_BASIC_TABLE_STYLE)
				.appendAttribute("cellspacing", "0")
				.appendAttribute("cellpadding", "0")
				.build();

		tableBericht.appendContent(Bericht_UG_Factory.createIDRow(erkundungsstellen));
		tableBericht.appendContent(Bericht_UG_Factory.createDickeRow(erkundungsstellen));
		tableBericht.appendContent(Bericht_UG_Factory.createZielTiefeRow(erkundungsstellen));
		tableBericht.appendContent(buildTechnischeMerkmale(erkundungsstellen));
		tableBericht.appendContent(buildUmweltTechnischeMerkmale(erkundungsstellen));

		setHtmlTable(tableBericht.appendTag());
	}

	@Override
	public void buildHtmlTable(Erkundungsstelle data)
	{

	}

	@Override
	public String getExportFileName()
	{
		return "Bericht_UG_Table.html";
	}


	private String buildTechnischeMerkmale(List<Erkundungsstelle> erkundungsstellen)
	{
		StringBuilder techBuilder = new StringBuilder();

		//Technische Merkmale Trennzeile
		HtmlRow rowTECHMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent("Technische Merkmale")
						.build()
						.appendTag())
				.build();

		techBuilder.append(rowTECHMERKMALE.appendTag())
				.append(Bericht_UG_Factory.createDIN18196Row(erkundungsstellen))
				.append(Bericht_UG_Factory.createDIN18300Row(erkundungsstellen))
				.append(Bericht_UG_Factory.createDIN19682Row(erkundungsstellen))
				.append(Bericht_UG_Factory.createDIN18300_09Row(erkundungsstellen))
				.append(Bericht_UG_Factory.createZTVRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createWasserGehaltRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createFeuchteZustandRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createKonsistenzRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createVerdichtungsfaehigkeitRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createTragPlanumRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createTragSohleRow(erkundungsstellen));

		return techBuilder.toString();
	}

	private String buildUmweltTechnischeMerkmale(List<Erkundungsstelle> erkundungsstellen)
	{
		StringBuilder umweltTechBuilder = new StringBuilder();

		//Umwelttechnische Merkmale Trennzeile
		HtmlRow rowUMWELTMERKMALE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "NormalHeader")
						.appendAttribute("colspan", String.valueOf(1 + erkundungsstellen.size()))
						.appendContent("Umwelttechnische Merkmale")
						.build()
						.appendTag())
				.build();

		umweltTechBuilder.append(rowUMWELTMERKMALE.appendTag())
				.append(Bericht_UG_Factory.createChemieIDRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createChemieMufvRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createChemieLagaBoRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createChemieLagaRcRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createChemieLagaRcOrientierungRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createChemieTlGesteinRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createChemieDepvRow(erkundungsstellen))
				.append(Bericht_UG_Factory.createChemieEntscheidungshilfeRow(erkundungsstellen));

		return umweltTechBuilder.toString();
	}

}
