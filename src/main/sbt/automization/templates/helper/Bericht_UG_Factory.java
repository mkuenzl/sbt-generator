package sbt.automization.templates.helper;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;
import sbt.automization.format.TextFormatUtil;
import sbt.automization.util.html.HtmlCell;
import sbt.automization.util.html.HtmlRow;
import sbt.automization.util.html.HtmlText;

import java.util.List;

public class Bericht_UG_Factory
{
	private static String aufschluss = "UG";

	public static String createIDRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//Erkundungsstellen ID
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "110")
						.appendContent("Erkundungsstelle")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle :
				erkundungsstellen)
		{
			HtmlCell htmlCell_ERK_ID = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(erkundungsstelle.getInformation("ERK_ID"))
					.build();

			row.appendContent(htmlCell_ERK_ID.appendTag());
		}

		return row.appendTag();
	}

	public static String createDickeRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//Erkundungsstellen Aufschlussart
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Dicke,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();


		for (Erkundungsstelle erkundungsstelle :
				erkundungsstellen)
		{
			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "50")
					.appendContent("Dicke")
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createWasserGehaltRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//WASSERGEHALT
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Wassergehalt")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("M.-%")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_WASSERGEHALT"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createFeuchteZustandRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//FEUCHTEZUSTAND
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Feuchtezustand")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_FEUCHTIGKEIT"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createKonsistenzRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//KONSISTENZ
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Konsistenz")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_KONSISTENZ"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createProctordichteRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//PROCTORDICHTE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Proctordichte")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Mg/m³")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_WASSERPROCTOR"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createProtorDifferenzRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//DIFFERENCE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Differenz W<sub>n</sub> - W<sub>opt</sub>")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("M.-%")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_DIFFERENZ_WN_WOPT"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createVerdichtungsfaehigkeitRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//VERDICHTUNGSFÄHIGKEIT
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Verdichtungsfähigkeit")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_VERDICHTUNGSFAEHIGKEIT"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createTragPlanumRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//TRAGFAEHIGKEIT_PLANUM
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Tragfähigkeit")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("Planum")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Soll: E<sub>V2</sub> >= 45 MN/m²")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Ansatz Planum: FOK -60cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(erkundungsstelle.getInformation("ERK_TRAG_PLANUM"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createTragSohleRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//TRAGFAEHIGKEIT_GRABENSOHLE
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Tragfähigkeit")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("Grabensohle")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Ansatz Sohle: FOK -200cm")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(erkundungsstelle.getInformation("ERK_TRAG_GRABENSOHLE"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createDIN18196Row(List<Erkundungsstelle> erkundungsstellen)
	{
		//DIN18196
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Bodengruppe,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DIN 18196")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_ART"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createDIN18300Row(List<Erkundungsstelle> erkundungsstellen)
	{
		//DIN18300
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Bodenklasse,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DIN 18300")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_BODENKLASSE"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createDIN19682Row(List<Erkundungsstelle> erkundungsstellen)
	{
		//DIN19682
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Bodenart-")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("Hauptgruppe,")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DIN 19682-2")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_BODENART"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createDIN18300_09Row(List<Erkundungsstelle> erkundungsstellen)
	{
		//DIN18300:2019-09
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Homogenbereich,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DIN 18300:2019-09")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_HOMOGENBEREICH"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createZTVRow(List<Erkundungsstelle> erkundungsstellen)
	{
		//ZTV-E
		HtmlRow row = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Frostempfindlichkeits-")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("klasse,")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("ZTV E")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell cell = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "SCHICHT_FROSTEMPFINDLICHKEITSKLASSE"))
					.build();

			row.appendContent(cell.appendTag());
		}

		return row.appendTag();
	}

	public static String createChemieIDRow(List<Erkundungsstelle> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_ID = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Laborprobe")
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell htmlCell_CHEMIE_ID = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_ID"))
					.build();

			rowCHEMIE_ID.appendContent(htmlCell_CHEMIE_ID.appendTag());
		}

		return rowCHEMIE_ID.appendTag();

	}

	public static String createChemieMufvRow(List<Erkundungsstelle> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_MUFV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Abgrenzung")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("Gefährlichkeit,")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Schreiben des MUFV")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell htmlCell_CHEMIE_MUFV = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_MUFV"))
					.build();

			rowCHEMIE_MUFV.appendContent(htmlCell_CHEMIE_MUFV.appendTag());
		}

		return rowCHEMIE_MUFV.appendTag();
	}

	public static String createChemieLagaBoRow(List<Erkundungsstelle> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_LAGA_BO = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("LAGA Boden,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Zuordnung")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell htmlCell_CHEMIE_LAGA_BO = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_LAGA_BO"))
					.build();

			rowCHEMIE_LAGA_BO.appendContent(htmlCell_CHEMIE_LAGA_BO.appendTag());
		}

		return rowCHEMIE_LAGA_BO.appendTag();
	}

	public static String createChemieLagaRcRow(List<Erkundungsstelle> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_LAGA_RC = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("LAGA Bauschutt,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Zuordnung")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell htmlCell_CHEMIE_LAGA_RC = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_LAGA_RC"))
					.build();

			rowCHEMIE_LAGA_RC.appendContent(htmlCell_CHEMIE_LAGA_RC.appendTag());
		}

		return rowCHEMIE_LAGA_RC.appendTag();
	}

	public static String createChemieLagaRcOrientierungRow(List<Erkundungsstelle> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_LAGA_RC_ORIENTIERUNG = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("LAGA Bauschutt,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Orientierung")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell htmlCell_CHEMIE_LAGA_RC_ORIENTIERUNG = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_LAGARC_ORIENTIERUNGSWERT"))
					.build();

			rowCHEMIE_LAGA_RC_ORIENTIERUNG.appendContent(htmlCell_CHEMIE_LAGA_RC_ORIENTIERUNG.appendTag());
		}

		return rowCHEMIE_LAGA_RC_ORIENTIERUNG.appendTag();

	}

	public static String createChemieTlGesteinRow(List<Erkundungsstelle> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_TL_GESTEIN = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("TL Gestein,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("Verwertungsklasse")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell htmlCell_CHEMIE_TL_GESTEIN = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_TLGESTEIN"))
					.build();


			rowCHEMIE_TL_GESTEIN.appendContent(htmlCell_CHEMIE_TL_GESTEIN.appendTag());
		}

		return rowCHEMIE_TL_GESTEIN.appendTag();
	}

	public static String createChemieDepvRow(List<Erkundungsstelle> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_DEPV = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Deponieverordnungs-")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("klasse,")
								.build()
								.appendTag())
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DepV")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell htmlCell_CHEMIE_DEPV = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_DEPV"))
					.build();


			rowCHEMIE_DEPV.appendContent(htmlCell_CHEMIE_DEPV.appendTag());
		}

		return rowCHEMIE_DEPV.appendTag();
	}

	public static String createChemieEntscheidungshilfeRow(List<Erkundungsstelle> erkundungsstellen)
	{
		HtmlRow rowCHEMIE_ENTSCHEIDUNGSHILFE = new HtmlRow.Builder()
				.appendAttribute("class", "Normal")
				.appendContent(new HtmlCell.Builder()
						.appendAttribute("class", "Normal")
						.appendAttribute("width", "100")
						.appendContent("Entscheidungshilfe,")
						.appendContent(new HtmlText.Builder()
								.appendAttribute("class", "Normal6")
								.appendContent("DepV")
								.build()
								.appendTag())
						.build()
						.appendTag())
				.build();

		for (Erkundungsstelle erkundungsstelle : erkundungsstellen)
		{
			List<Schicht> schichtAufschluss = erkundungsstelle.getSchichtAufschluss(aufschluss);

			HtmlCell htmlCell_CHEMIE_ENTSCHEIDUNGSILFE = new HtmlCell.Builder()
					.appendAttribute("class", "NormalErkundungsstelle")
					.appendAttribute("width", "60")
					.appendContent(TextFormatUtil.printMultipleSchichtInformation(erkundungsstelle, aufschluss, "CHEMIE_ENTSCHEIDUNGSHILFE"))
					.build();

			rowCHEMIE_ENTSCHEIDUNGSHILFE.appendContent(htmlCell_CHEMIE_ENTSCHEIDUNGSILFE.appendTag());
		}

		return rowCHEMIE_ENTSCHEIDUNGSHILFE.appendTag();
	}
}