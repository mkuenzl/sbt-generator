package sbt.automization.templates;

import sbt.automization.data.Erkundungsstelle;
import sbt.automization.data.Schicht;

import java.util.HashMap;
import java.util.Map;

public class ERKCreationTestUtil
{

/*	Map<String, String> schichtDaten = new HashMap<>();

		schichtDaten.put("SCHICHT_ID", "");
		schichtDaten.put("ERK_ID", "");
		schichtDaten.put("CHEMIE_ID", "");
		schichtDaten.put("SCHICHT_NR", "");
		schichtDaten.put("SCHICHT_AUFSCHLUSS", "");
		schichtDaten.put("SCHICHT_ART", "");
		schichtDaten.put("SCHICHT_KOERNUNG", "");
		schichtDaten.put("SCHICHT_DICKE", "");
		schichtDaten.put("SCHICHT_TIEFE_START", "");
		schichtDaten.put("SCHICHT_TIEFE_ENDE", "");
		schichtDaten.put("SCHICHT_PECH", "");
		schichtDaten.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "");
		schichtDaten.put("SCHICHT_FARBE", "");
		schichtDaten.put("SCHICHT_FEUCHTIGKEIT", "");
		schichtDaten.put("SCHICHT_WASSERGEHALT", "");
		schichtDaten.put("SCHICHT_BEMERKUNGEN", "");
		schichtDaten.put("SCHICHT_BEHAELTNIS", "");
		schichtDaten.put("SCHICHT_ABFALLART", "");
		schichtDaten.put("SCHICHT_GERUCH", "");
		schichtDaten.put("SCHICHT_RUK_NR", "");
		schichtDaten.put("SCHICHT_RUK_PROBE", "");
		schichtDaten.put("SCHICHT_RUK", "");
		schichtDaten.put("CHEMIE_MUFV", "");
		schichtDaten.put("CHEMIE_LAGA_BO", "");
		schichtDaten.put("CHEMIE_LAGA_RC", "");
		schichtDaten.put("CHEMIE_LFS", "");
		schichtDaten.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "");
		schichtDaten.put("CHEMIE_TLGESTEIN", "");
		schichtDaten.put("CHEMIE_DEPV", "");
		schichtDaten.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		schichtDaten.put("CHEMIE_REKU", "");
		schichtDaten.put("CHEMIE_ASBEST", "");
		schichtDaten.put("CHEMIE_PCB", "");
		schichtDaten.put("CHEMIE_BTEX", "");
		*/

	Erkundungsstelle erkundungsstelle1 = new Erkundungsstelle();
	Schicht schicht1;
	Schicht schicht2;
	Schicht schicht3;
	Schicht schicht4;
	Schicht schicht5;
	Schicht schicht6;
	Schicht schicht7;
	Schicht schicht8;


	public Erkundungsstelle getTestErkundungsstelle1()
	{
		buildErkDaten();
		buildSchichtDaten();
		erkundungsstelle1.addSchicht(schicht1);
		erkundungsstelle1.addSchicht(schicht2);
		erkundungsstelle1.addSchicht(schicht3);
		erkundungsstelle1.addSchicht(schicht4);
		erkundungsstelle1.addSchicht(schicht5);

		return erkundungsstelle1;
	}

	private void buildErkDaten()
	{
		Map<String, String> erkDaten = new HashMap<>();

		erkDaten.put("ERK_ID", "FB8");
		erkDaten.put("ERK_DATUM", "01.01.2021");
		erkDaten.put("ERK_PRUEFER", "Mensur;Polaki");
		erkDaten.put("ERK_BEREICH", "LBM");
		erkDaten.put("ERK_ANSPRECHPARTNER", "Herr WasWeißIch");
		erkDaten.put("ERK_KOORDINATEN", "32U 123141 123123");
		erkDaten.put("ERK_ORT", "K 35, NK 5809 018 – NK 5809 016, Stat. 4+200, FS FR Treis");
		erkDaten.put("ERK_AUFSCHLUSS_OB", "Bk400");
		erkDaten.put("ERK_AUFSCHLUSS_TOB", "Handschurf");
		erkDaten.put("ERK_AUFSCHLUSS_UG", "Kleinrammbohrung");
		erkDaten.put("ERK_OBERKANTE", "FOK");
		erkDaten.put("ERK_BELASTUNGSKLASSE", "1.2");
		erkDaten.put("ERK_BELASTUNGSKLASSE_TAFEL", "Tafel 1, Zeile 1");
		erkDaten.put("ERK_LP", "");

		erkundungsstelle1.setDataMap(erkDaten);
	}

	private void buildSchichtDaten()
	{
		Map<String, String> schicht1Daten = new HashMap<>();

		schicht1Daten.put("SCHICHT_ID", "1");
		schicht1Daten.put("ERK_ID", "FB8");
		schicht1Daten.put("CHEMIE_ID", "");
		schicht1Daten.put("SCHICHT_NR", "1");
		schicht1Daten.put("SCHICHT_AUFSCHLUSS", "GOB");
		schicht1Daten.put("SCHICHT_ART", "Deckschicht");
		schicht1Daten.put("SCHICHT_KOERNUNG", "0/8");
		schicht1Daten.put("SCHICHT_DICKE", "3");
		schicht1Daten.put("SCHICHT_TIEFE_START", "0");
		schicht1Daten.put("SCHICHT_TIEFE_ENDE", "3");
		schicht1Daten.put("SCHICHT_PECH", "nein");
		schicht1Daten.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "");
		schicht1Daten.put("SCHICHT_FARBE", "schwarz-grau");
		schicht1Daten.put("SCHICHT_FEUCHTIGKEIT", "");
		schicht1Daten.put("SCHICHT_WASSERGEHALT", "");
		schicht1Daten.put("SCHICHT_BEMERKUNGEN", "");
		schicht1Daten.put("SCHICHT_BEHAELTNIS", "");
		schicht1Daten.put("SCHICHT_ABFALLART", "Asphalt");
		schicht1Daten.put("SCHICHT_GERUCH", "neutral");
		schicht1Daten.put("SCHICHT_RUK_NR", "8");
		schicht1Daten.put("SCHICHT_RUK_PROBE", "Einzelprobe");
		schicht1Daten.put("SCHICHT_RUK", "62,0");
		schicht1Daten.put("CHEMIE_MUFV", "");
		schicht1Daten.put("CHEMIE_LAGA_BO", "");
		schicht1Daten.put("CHEMIE_LAGA_RC", "");
		schicht1Daten.put("CHEMIE_LFS", "");
		schicht1Daten.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "");
		schicht1Daten.put("CHEMIE_TLGESTEIN", "");
		schicht1Daten.put("CHEMIE_DEPV", "");
		schicht1Daten.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		schicht1Daten.put("CHEMIE_REKU", "");
		schicht1Daten.put("CHEMIE_ASBEST", "");
		schicht1Daten.put("CHEMIE_PCB", "");
		schicht1Daten.put("CHEMIE_BTEX", "");

		schicht1 = new Schicht(schicht1Daten);

		Map<String, String> schicht2Daten = new HashMap<>();

		schicht2Daten.put("SCHICHT_ID", "2");
		schicht2Daten.put("ERK_ID", "FB8");
		schicht2Daten.put("CHEMIE_ID", "");
		schicht2Daten.put("SCHICHT_NR", "2");
		schicht2Daten.put("SCHICHT_AUFSCHLUSS", "GOB");
		schicht2Daten.put("SCHICHT_ART", "Tragschicht");
		schicht2Daten.put("SCHICHT_KOERNUNG", "0/16");
		schicht2Daten.put("SCHICHT_DICKE", "3,5");
		schicht2Daten.put("SCHICHT_TIEFE_START", "3");
		schicht2Daten.put("SCHICHT_TIEFE_ENDE", "6,5");
		schicht2Daten.put("SCHICHT_PECH", "nein");
		schicht2Daten.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "");
		schicht2Daten.put("SCHICHT_FARBE", "schwarz-grau");
		schicht2Daten.put("SCHICHT_FEUCHTIGKEIT", "");
		schicht2Daten.put("SCHICHT_WASSERGEHALT", "");
		schicht2Daten.put("SCHICHT_BEMERKUNGEN", "");
		schicht2Daten.put("SCHICHT_BEHAELTNIS", "");
		schicht2Daten.put("SCHICHT_ABFALLART", "Asphalt");
		schicht2Daten.put("SCHICHT_GERUCH", "neutral");
		schicht2Daten.put("SCHICHT_RUK_NR", "9");
		schicht2Daten.put("SCHICHT_RUK_PROBE", "Einzelprobe");
		schicht2Daten.put("SCHICHT_RUK", "59,2");
		schicht2Daten.put("CHEMIE_MUFV", "");
		schicht2Daten.put("CHEMIE_LAGA_BO", "");
		schicht2Daten.put("CHEMIE_LAGA_RC", "");
		schicht2Daten.put("CHEMIE_LFS", "");
		schicht2Daten.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "");
		schicht2Daten.put("CHEMIE_TLGESTEIN", "");
		schicht2Daten.put("CHEMIE_DEPV", "");
		schicht2Daten.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		schicht2Daten.put("CHEMIE_REKU", "");
		schicht2Daten.put("CHEMIE_ASBEST", "");
		schicht2Daten.put("CHEMIE_PCB", "");
		schicht2Daten.put("CHEMIE_BTEX", "");

		schicht2 = new Schicht(schicht2Daten);

		Map<String, String> schicht3Daten = new HashMap<>();

		schicht3Daten.put("SCHICHT_ID", "3");
		schicht3Daten.put("ERK_ID", "FB8");
		schicht3Daten.put("CHEMIE_ID", "");
		schicht3Daten.put("SCHICHT_NR", "3");
		schicht3Daten.put("SCHICHT_AUFSCHLUSS", "GOB");
		schicht3Daten.put("SCHICHT_ART", "Tragschicht");
		schicht3Daten.put("SCHICHT_KOERNUNG", "0/22");
		schicht3Daten.put("SCHICHT_DICKE", "6");
		schicht3Daten.put("SCHICHT_TIEFE_START", "6,5");
		schicht3Daten.put("SCHICHT_TIEFE_ENDE", "12,5");
		schicht3Daten.put("SCHICHT_PECH", "nein");
		schicht3Daten.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "");
		schicht3Daten.put("SCHICHT_FARBE", "schwarz-grau");
		schicht3Daten.put("SCHICHT_FEUCHTIGKEIT", "");
		schicht3Daten.put("SCHICHT_WASSERGEHALT", "");
		schicht3Daten.put("SCHICHT_BEMERKUNGEN", "");
		schicht3Daten.put("SCHICHT_BEHAELTNIS", "");
		schicht3Daten.put("SCHICHT_ABFALLART", "Asphalt");
		schicht3Daten.put("SCHICHT_GERUCH", "neutral");
		schicht3Daten.put("SCHICHT_RUK_NR", "10");
		schicht3Daten.put("SCHICHT_RUK_PROBE", "Einzelprobe");
		schicht3Daten.put("SCHICHT_RUK", "60,6");
		schicht3Daten.put("CHEMIE_MUFV", "");
		schicht3Daten.put("CHEMIE_LAGA_BO", "");
		schicht3Daten.put("CHEMIE_LAGA_RC", "");
		schicht3Daten.put("CHEMIE_LFS", "");
		schicht3Daten.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "");
		schicht3Daten.put("CHEMIE_TLGESTEIN", "");
		schicht3Daten.put("CHEMIE_DEPV", "");
		schicht3Daten.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		schicht3Daten.put("CHEMIE_REKU", "");
		schicht3Daten.put("CHEMIE_ASBEST", "");
		schicht3Daten.put("CHEMIE_PCB", "");
		schicht3Daten.put("CHEMIE_BTEX", "");

		schicht3 = new Schicht(schicht3Daten);

		Map<String, String> schicht4Daten = new HashMap<>();

		schicht4Daten.put("SCHICHT_ID", "4");
		schicht4Daten.put("ERK_ID", "FB8");
		schicht4Daten.put("CHEMIE_ID", "");
		schicht4Daten.put("SCHICHT_NR", "4");
		schicht4Daten.put("SCHICHT_AUFSCHLUSS", "GOB");
		schicht4Daten.put("SCHICHT_ART", "Tragschicht");
		schicht4Daten.put("SCHICHT_KOERNUNG", "0/22");
		schicht4Daten.put("SCHICHT_DICKE", "5,5");
		schicht4Daten.put("SCHICHT_TIEFE_START", "12,5");
		schicht4Daten.put("SCHICHT_TIEFE_ENDE", "18");
		schicht4Daten.put("SCHICHT_PECH", "nein");
		schicht4Daten.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "");
		schicht4Daten.put("SCHICHT_FARBE", "schwarz-grau");
		schicht4Daten.put("SCHICHT_FEUCHTIGKEIT", "");
		schicht4Daten.put("SCHICHT_WASSERGEHALT", "");
		schicht4Daten.put("SCHICHT_BEMERKUNGEN", "");
		schicht4Daten.put("SCHICHT_BEHAELTNIS", "");
		schicht4Daten.put("SCHICHT_ABFALLART", "Asphalt");
		schicht4Daten.put("SCHICHT_GERUCH", "neutral");
		schicht4Daten.put("SCHICHT_RUK_NR", "11");
		schicht4Daten.put("SCHICHT_RUK_PROBE", "Einzelprobe");
		schicht4Daten.put("SCHICHT_RUK", "63,0");
		schicht4Daten.put("CHEMIE_MUFV", "");
		schicht4Daten.put("CHEMIE_LAGA_BO", "");
		schicht4Daten.put("CHEMIE_LAGA_RC", "");
		schicht4Daten.put("CHEMIE_LFS", "");
		schicht4Daten.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "");
		schicht4Daten.put("CHEMIE_TLGESTEIN", "");
		schicht4Daten.put("CHEMIE_DEPV", "");
		schicht4Daten.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		schicht4Daten.put("CHEMIE_REKU", "");
		schicht4Daten.put("CHEMIE_ASBEST", "");
		schicht4Daten.put("CHEMIE_PCB", "");
		schicht4Daten.put("CHEMIE_BTEX", "");

		schicht4 = new Schicht(schicht4Daten);

		Map<String, String> schicht5Daten = new HashMap<>();

		schicht5Daten.put("SCHICHT_ID", "5");
		schicht5Daten.put("ERK_ID", "FB8");
		schicht5Daten.put("CHEMIE_ID", "C1");
		schicht5Daten.put("SCHICHT_NR", "5");
		schicht5Daten.put("SCHICHT_AUFSCHLUSS", "TOB");
		schicht5Daten.put("SCHICHT_ART", "Gem. a. G. (LS)");
		schicht5Daten.put("SCHICHT_KOERNUNG", "0/22");
		schicht5Daten.put("SCHICHT_DICKE", "10");
		schicht5Daten.put("SCHICHT_TIEFE_START", "18");
		schicht5Daten.put("SCHICHT_TIEFE_ENDE", "28");
		schicht5Daten.put("SCHICHT_PECH", "");
		schicht5Daten.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "BK, sg");
		schicht5Daten.put("SCHICHT_FARBE", "rotbraun");
		schicht5Daten.put("SCHICHT_FEUCHTIGKEIT", "");
		schicht5Daten.put("SCHICHT_WASSERGEHALT", "");
		schicht5Daten.put("SCHICHT_BEMERKUNGEN", "");
		schicht5Daten.put("SCHICHT_BEHAELTNIS", "");
		schicht5Daten.put("SCHICHT_ABFALLART", "Gem. a. G. (LS)");
		schicht5Daten.put("SCHICHT_GERUCH", "neutral");
		schicht5Daten.put("SCHICHT_RUK_NR", "");
		schicht5Daten.put("SCHICHT_RUK_PROBE", "");
		schicht5Daten.put("SCHICHT_RUK", "");
		schicht5Daten.put("CHEMIE_MUFV", "nicht gefährlich");
		schicht5Daten.put("CHEMIE_LAGA_BO", "Z0*");
		schicht5Daten.put("CHEMIE_LAGA_RC", "Z1.1");
		schicht5Daten.put("CHEMIE_LFS", "");
		schicht5Daten.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "eingehalten");
		schicht5Daten.put("CHEMIE_TLGESTEIN", "RC1");
		schicht5Daten.put("CHEMIE_DEPV", "DK0");
		schicht5Daten.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		schicht5Daten.put("CHEMIE_REKU", "");
		schicht5Daten.put("CHEMIE_ASBEST", "");
		schicht5Daten.put("CHEMIE_PCB", "");
		schicht5Daten.put("CHEMIE_BTEX", "");

		schicht5 = new Schicht(schicht5Daten);
	}
}
