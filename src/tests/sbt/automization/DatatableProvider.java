package sbt.automization;

import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;

import java.util.HashMap;
import java.util.Map;

public final class DatatableProvider
{
	Sample sample_1_GOB;
	Sample sample_2_GOB;
	Sample sample_3_GOB;
	Sample sample_4_GOB;
	Sample sample_5_TOB;
	Sample sample_6_TOB;
	Sample sample_7_TOB;
	Sample sample_8_UG;
	Sample sample_9_UG;

	public DatatableProvider() {}

	public DataTable getTestDataTable()
	{
		Probe dataTable = new Probe();
		dataTable.setTable(buildProbeData());
		buildSampleData();
		dataTable.addSample(sample_1_GOB);
		dataTable.addSample(sample_2_GOB);
		dataTable.addSample(sample_3_GOB);
		dataTable.addSample(sample_4_GOB);
		dataTable.addSample(sample_5_TOB);
		dataTable.addSample(sample_6_TOB);
		dataTable.addSample(sample_7_TOB);
		dataTable.addSample(sample_8_UG);

		return dataTable;
	}
	
	public DataTable getTestDataTable(int sampleAmount)
	{
		Probe dataTable = new Probe();
		dataTable.setTable(buildProbeData());
		buildSampleData();
		
		for (int i = 0; i < sampleAmount; i++)
		{
			dataTable.addSample(sample_1_GOB);
		}
		
		return dataTable;
	}

	private Map<String, String> buildProbeData()
	{
		Map<String, String> data = new HashMap<>();

		data.put("ERK_ID", "FB8");
		data.put("ERK_DATUM", "01.01.2021");
		data.put("ERK_PRUEFER", "Mensur;Polaki");
		data.put("ERK_BEREICH", "LBM");
		data.put("ERK_ANSPRECHPARTNER", "Herr WasWeißIch");
		data.put("ERK_KOORDINATEN", "32U 123141 123123");
		data.put("ERK_ORT", "K 35, NK 5809 018 – NK 5809 016, Stat. 4+200, FS FR Treis");
		data.put("ERK_AUFSCHLUSS_OB", "Bk400");
		data.put("ERK_AUFSCHLUSS_TOB", "Handschurf");
		data.put("ERK_AUFSCHLUSS_UG", "Kleinrammbohrung");
		data.put("ERK_OBERKANTE", "FOK");
		data.put("ERK_BELASTUNGSKLASSE", "1.2");
		data.put("ERK_BELASTUNGSKLASSE_TAFEL", "Tafel 1, Zeile 1");
		data.put("ERK_LP", "");

		return data;
	}

	private void buildSampleData()
	{
		Map<String, String> sample_1 = new HashMap<>();

		sample_1.put("SCHICHT_ID", "1");
		sample_1.put("ERK_ID", "FB8");
		sample_1.put("CHEMIE_ID", "");
		sample_1.put("SCHICHT_NR", "1");
		sample_1.put("SCHICHT_AUFSCHLUSS", "BETON");
		sample_1.put("SCHICHT_ART", "Deckschicht");
		sample_1.put("SCHICHT_KOERNUNG", "0/8");
		sample_1.put("SCHICHT_DICKE", "3");
		sample_1.put("SCHICHT_TIEFE_START", "0");
		sample_1.put("SCHICHT_TIEFE_ENDE", "3");
		sample_1.put("SCHICHT_PECH", "nein");
		sample_1.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "");
		sample_1.put("SCHICHT_FARBE", "schwarz-grau");
		sample_1.put("SCHICHT_FEUCHTIGKEIT", "");
		sample_1.put("SCHICHT_WASSERGEHALT", "");
		sample_1.put("SCHICHT_BEMERKUNGEN", "");
		sample_1.put("SCHICHT_BEHAELTNIS", "");
		sample_1.put("SCHICHT_ABFALLART", "Asphalt");
		sample_1.put("SCHICHT_GERUCH", "neutral");
		sample_1.put("SCHICHT_RUK_NR", "8");
		sample_1.put("SCHICHT_RUK_PROBE", "Einzelprobe");
		sample_1.put("SCHICHT_RUK", "62,0");
		sample_1.put("CHEMIE_MUFV", "");
		sample_1.put("CHEMIE_LAGA_BO", "");
		sample_1.put("CHEMIE_LAGA_RC", "");
		sample_1.put("CHEMIE_LFS", "");
		sample_1.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "");
		sample_1.put("CHEMIE_TLGESTEIN", "");
		sample_1.put("CHEMIE_DEPV", "");
		sample_1.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		sample_1.put("CHEMIE_REKU", "");
		sample_1.put("CHEMIE_ASBEST", "");
		sample_1.put("CHEMIE_PCB", "");
		sample_1.put("CHEMIE_BTEX", "");

		sample_1_GOB = new Sample(sample_1);

		Map<String, String> sample_2 = new HashMap<>();

		sample_2.put("SCHICHT_ID", "2");
		sample_2.put("ERK_ID", "FB8");
		sample_2.put("CHEMIE_ID", "");
		sample_2.put("SCHICHT_NR", "2");
		sample_2.put("SCHICHT_AUFSCHLUSS", "GOB");
		sample_2.put("SCHICHT_ART", "Tragschicht");
		sample_2.put("SCHICHT_KOERNUNG", "0/16");
		sample_2.put("SCHICHT_DICKE", "3,5");
		sample_2.put("SCHICHT_TIEFE_START", "3");
		sample_2.put("SCHICHT_TIEFE_ENDE", "6,5");
		sample_2.put("SCHICHT_PECH", "nein");
		sample_2.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "");
		sample_2.put("SCHICHT_FARBE", "schwarz-grau");
		sample_2.put("SCHICHT_FEUCHTIGKEIT", "");
		sample_2.put("SCHICHT_WASSERGEHALT", "");
		sample_2.put("SCHICHT_BEMERKUNGEN", "");
		sample_2.put("SCHICHT_BEHAELTNIS", "");
		sample_2.put("SCHICHT_ABFALLART", "Asphalt");
		sample_2.put("SCHICHT_GERUCH", "neutral");
		sample_2.put("SCHICHT_RUK_NR", "9");
		sample_2.put("SCHICHT_RUK_PROBE", "Einzelprobe");
		sample_2.put("SCHICHT_RUK", "59,2");
		sample_2.put("CHEMIE_MUFV", "");
		sample_2.put("CHEMIE_LAGA_BO", "");
		sample_2.put("CHEMIE_LAGA_RC", "");
		sample_2.put("CHEMIE_LFS", "");
		sample_2.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "");
		sample_2.put("CHEMIE_TLGESTEIN", "");
		sample_2.put("CHEMIE_DEPV", "");
		sample_2.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		sample_2.put("CHEMIE_REKU", "");
		sample_2.put("CHEMIE_ASBEST", "");
		sample_2.put("CHEMIE_PCB", "");
		sample_2.put("CHEMIE_BTEX", "");

		sample_2_GOB = new Sample(sample_2);

		Map<String, String> sample_3 = new HashMap<>();

		sample_3.put("SCHICHT_ID", "3");
		sample_3.put("ERK_ID", "FB8");
		sample_3.put("CHEMIE_ID", "");
		sample_3.put("SCHICHT_NR", "3");
		sample_3.put("SCHICHT_AUFSCHLUSS", Outcrop.GOB.toString());
		sample_3.put("SCHICHT_ART", "Tragschicht");
		sample_3.put("SCHICHT_KOERNUNG", "0/22");
		sample_3.put("SCHICHT_DICKE", "6");
		sample_3.put("SCHICHT_TIEFE_START", "6,5");
		sample_3.put("SCHICHT_TIEFE_ENDE", "12,5");
		sample_3.put("SCHICHT_PECH", "ja");
		sample_3.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "");
		sample_3.put("SCHICHT_FARBE", "schwarz-grau");
		sample_3.put("SCHICHT_FEUCHTIGKEIT", "");
		sample_3.put("SCHICHT_WASSERGEHALT", "");
		sample_3.put("SCHICHT_BEMERKUNGEN", "");
		sample_3.put("SCHICHT_BEHAELTNIS", "");
		sample_3.put("SCHICHT_ABFALLART", "Asphalt");
		sample_3.put("SCHICHT_GERUCH", "neutral");
		sample_3.put("SCHICHT_RUK_NR", "10");
		sample_3.put("SCHICHT_RUK_PROBE", "Einzelprobe");
		sample_3.put("SCHICHT_RUK", "60,6");
		sample_3.put("CHEMIE_MUFV", "");
		sample_3.put("CHEMIE_LAGA_BO", "");
		sample_3.put("CHEMIE_LAGA_RC", "");
		sample_3.put("CHEMIE_LFS", "");
		sample_3.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "");
		sample_3.put("CHEMIE_TLGESTEIN", "");
		sample_3.put("CHEMIE_DEPV", "");
		sample_3.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		sample_3.put("CHEMIE_REKU", "");
		sample_3.put("CHEMIE_ASBEST", "");
		sample_3.put("CHEMIE_PCB", "");
		sample_3.put("CHEMIE_BTEX", "");

		sample_3_GOB = new Sample(sample_3);

		Map<String, String> sample_4 = new HashMap<>();

		sample_4.put("SCHICHT_ID", "4");
		sample_4.put("ERK_ID", "FB8");
		sample_4.put("CHEMIE_ID", "");
		sample_4.put("SCHICHT_NR", "4");
		sample_4.put("SCHICHT_AUFSCHLUSS", Outcrop.GOB.toString());
		sample_4.put("SCHICHT_ART", "Tragschicht");
		sample_4.put("SCHICHT_KOERNUNG", "0/22");
		sample_4.put("SCHICHT_DICKE", "5,5");
		sample_4.put("SCHICHT_TIEFE_START", "12,5");
		sample_4.put("SCHICHT_TIEFE_ENDE", "18");
		sample_4.put("SCHICHT_PECH", "");
		sample_4.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "");
		sample_4.put("SCHICHT_FARBE", "schwarz-grau");
		sample_4.put("SCHICHT_FEUCHTIGKEIT", "");
		sample_4.put("SCHICHT_WASSERGEHALT", "");
		sample_4.put("SCHICHT_BEMERKUNGEN", "");
		sample_4.put("SCHICHT_BEHAELTNIS", "");
		sample_4.put("SCHICHT_ABFALLART", "Asphalt");
		sample_4.put("SCHICHT_GERUCH", "neutral");
		sample_4.put("SCHICHT_RUK_NR", "11");
		sample_4.put("SCHICHT_RUK_PROBE", "Einzelprobe");
		sample_4.put("SCHICHT_RUK", "63,0");
		sample_4.put("CHEMIE_MUFV", "");
		sample_4.put("CHEMIE_LAGA_BO", "");
		sample_4.put("CHEMIE_LAGA_RC", "");
		sample_4.put("CHEMIE_LFS", "");
		sample_4.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "");
		sample_4.put("CHEMIE_TLGESTEIN", "");
		sample_4.put("CHEMIE_DEPV", "");
		sample_4.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		sample_4.put("CHEMIE_REKU", "");
		sample_4.put("CHEMIE_ASBEST", "");
		sample_4.put("CHEMIE_PCB", "");
		sample_4.put("CHEMIE_BTEX", "");

		sample_4_GOB = new Sample(sample_4);

		Map<String, String> sample_5 = new HashMap<>();

		sample_5.put("SCHICHT_ID", "5");
		sample_5.put("ERK_ID", "FB8");
		sample_5.put("CHEMIE_ID", "C1");
		sample_5.put("SCHICHT_NR", "5");
		sample_5.put("SCHICHT_AUFSCHLUSS", "TOB");
		sample_5.put("SCHICHT_ART", "Gem. a. G. (LS)");
		sample_5.put("SCHICHT_KOERNUNG", "0/22");
		sample_5.put("SCHICHT_DICKE", "10");
		sample_5.put("SCHICHT_TIEFE_START", "18");
		sample_5.put("SCHICHT_TIEFE_ENDE", "28");
		sample_5.put("SCHICHT_PECH", "");
		sample_5.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "BK, sg");
		sample_5.put("SCHICHT_FARBE", "rotbraun");
		sample_5.put("SCHICHT_FEUCHTIGKEIT", "");
		sample_5.put("SCHICHT_WASSERGEHALT", "");
		sample_5.put("SCHICHT_BEMERKUNGEN", "");
		sample_5.put("SCHICHT_BEHAELTNIS", "");
		sample_5.put("SCHICHT_ABFALLART", "Gem. a. G. (LS)");
		sample_5.put("SCHICHT_GERUCH", "neutral");
		sample_5.put("SCHICHT_RUK_NR", "");
		sample_5.put("SCHICHT_RUK_PROBE", "");
		sample_5.put("SCHICHT_RUK", "");
		sample_5.put("CHEMIE_MUFV", "gefährlich");
		sample_5.put("CHEMIE_LAGA_BO", "Z0*");
		sample_5.put("CHEMIE_LAGA_RC", "Z1.1");
		sample_5.put("CHEMIE_LFS", "");
		sample_5.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "eingehalten");
		sample_5.put("CHEMIE_TLGESTEIN", "RC1");
		sample_5.put("CHEMIE_DEPV", "DK0");
		sample_5.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		sample_5.put("CHEMIE_REKU", "");
		sample_5.put("CHEMIE_ASBEST", "");
		sample_5.put("CHEMIE_PCB", "");
		sample_5.put("CHEMIE_BTEX", "");

		sample_5_TOB = new Sample(sample_5);

		Map<String, String> sample_6 = new HashMap<>();

		sample_6.put("SCHICHT_ID", "6");
		sample_6.put("ERK_ID", "FB8");
		sample_6.put("CHEMIE_ID", "C1");
		sample_6.put("SCHICHT_NR", "6");
		sample_6.put("SCHICHT_AUFSCHLUSS", "TOB");
		sample_6.put("SCHICHT_ART", "Gem. a. G. (NS)");
		sample_6.put("SCHICHT_KOERNUNG", "0/24");
		sample_6.put("SCHICHT_DICKE", "10");
		sample_6.put("SCHICHT_TIEFE_START", "18");
		sample_6.put("SCHICHT_TIEFE_ENDE", "28");
		sample_6.put("SCHICHT_PECH", "");
		sample_6.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "BK, sg");
		sample_6.put("SCHICHT_FARBE", "rotbraun");
		sample_6.put("SCHICHT_FEUCHTIGKEIT", "");
		sample_6.put("SCHICHT_WASSERGEHALT", "");
		sample_6.put("SCHICHT_BEMERKUNGEN", "");
		sample_6.put("SCHICHT_BEHAELTNIS", "");
		sample_6.put("SCHICHT_ABFALLART", "Gem. a. G. (NS)");
		sample_6.put("SCHICHT_GERUCH", "neutral");
		sample_6.put("SCHICHT_RUK_NR", "");
		sample_6.put("SCHICHT_RUK_PROBE", "");
		sample_6.put("SCHICHT_RUK", "");
		sample_6.put("CHEMIE_MUFV", "nicht gefährlich");
		sample_6.put("CHEMIE_LAGA_BO", "Z0*");
		sample_6.put("CHEMIE_LAGA_RC", "Z1.1");
		sample_6.put("CHEMIE_LFS", "");
		sample_6.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "eingehalten");
		sample_6.put("CHEMIE_TLGESTEIN", "RC1");
		sample_6.put("CHEMIE_DEPV", "DK0");
		sample_6.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		sample_6.put("CHEMIE_REKU", "");
		sample_6.put("CHEMIE_ASBEST", "");
		sample_6.put("CHEMIE_PCB", "");
		sample_6.put("CHEMIE_BTEX", "");

		sample_6_TOB = new Sample(sample_6);

		Map<String, String> sample_7 = new HashMap<>();

		sample_7.put("SCHICHT_ID", "7");
		sample_7.put("ERK_ID", "FB8");
		sample_7.put("CHEMIE_ID", "C1");
		sample_7.put("SCHICHT_NR", "7");
		sample_7.put("SCHICHT_AUFSCHLUSS", "TOB");
		sample_7.put("SCHICHT_ART", "Gem. a. G. (GS)");
		sample_7.put("SCHICHT_KOERNUNG", "0/26");
		sample_7.put("SCHICHT_DICKE", "10");
		sample_7.put("SCHICHT_TIEFE_START", "18");
		sample_7.put("SCHICHT_TIEFE_ENDE", "28");
		sample_7.put("SCHICHT_PECH", "");
		sample_7.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "BK, sg");
		sample_7.put("SCHICHT_FARBE", "rotbraun");
		sample_7.put("SCHICHT_FEUCHTIGKEIT", "");
		sample_7.put("SCHICHT_WASSERGEHALT", "");
		sample_7.put("SCHICHT_BEMERKUNGEN", "");
		sample_7.put("SCHICHT_BEHAELTNIS", "");
		sample_7.put("SCHICHT_ABFALLART", "Gem. a. G. (LS)");
		sample_7.put("SCHICHT_GERUCH", "neutral");
		sample_7.put("SCHICHT_RUK_NR", "");
		sample_7.put("SCHICHT_RUK_PROBE", "");
		sample_7.put("SCHICHT_RUK", "");
		sample_7.put("CHEMIE_MUFV", "nicht gefährlich");
		sample_7.put("CHEMIE_LAGA_BO", "Z0*");
		sample_7.put("CHEMIE_LAGA_RC", "Z1.1");
		sample_7.put("CHEMIE_LFS", "");
		sample_7.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "eingehalten");
		sample_7.put("CHEMIE_TLGESTEIN", "RC1");
		sample_7.put("CHEMIE_DEPV", "DK0");
		sample_7.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		sample_7.put("CHEMIE_REKU", "");
		sample_7.put("CHEMIE_ASBEST", "");
		sample_7.put("CHEMIE_PCB", "");
		sample_7.put("CHEMIE_BTEX", "");

		sample_7_TOB = new Sample(sample_7);

		Map<String, String> sample_8 = new HashMap<>();

		sample_8.put("SCHICHT_ID", "8");
		sample_8.put("ERK_ID", "FB8");
		sample_8.put("CHEMIE_ID", "C1");
		sample_8.put("SCHICHT_NR", "8");
		sample_8.put("SCHICHT_AUFSCHLUSS", "UG");
		sample_8.put("SCHICHT_ART", "Boden");
		sample_8.put("SCHICHT_KOERNUNG", "0/22");
		sample_8.put("SCHICHT_DICKE", "10");
		sample_8.put("SCHICHT_TIEFE_START", "18");
		sample_8.put("SCHICHT_TIEFE_ENDE", "28");
		sample_8.put("SCHICHT_PECH", "");
		sample_8.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "BK, sg");
		sample_8.put("SCHICHT_FARBE", "rotbraun");
		sample_8.put("SCHICHT_FEUCHTIGKEIT", "");
		sample_8.put("SCHICHT_WASSERGEHALT", "");
		sample_8.put("SCHICHT_BEMERKUNGEN", "");
		sample_8.put("SCHICHT_BEHAELTNIS", "");
		sample_8.put("SCHICHT_ABFALLART", "Gem. a. G. (LS)");
		sample_8.put("SCHICHT_GERUCH", "neutral");
		sample_8.put("SCHICHT_RUK_NR", "");
		sample_8.put("SCHICHT_RUK_PROBE", "");
		sample_8.put("SCHICHT_RUK", "");
		sample_8.put("CHEMIE_MUFV", "nicht gefährlich");
		sample_8.put("CHEMIE_LAGA_BO", "Z0*");
		sample_8.put("CHEMIE_LAGA_RC", "Z1.1");
		sample_8.put("CHEMIE_LFS", "");
		sample_8.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "eingehalten");
		sample_8.put("CHEMIE_TLGESTEIN", "RC1");
		sample_8.put("CHEMIE_DEPV", "DK0");
		sample_8.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		sample_8.put("CHEMIE_REKU", "");
		sample_8.put("CHEMIE_ASBEST", "");
		sample_8.put("CHEMIE_PCB", "");
		sample_8.put("CHEMIE_BTEX", "");

		sample_8_UG = new Sample(sample_8);

		Map<String, String> sample_9 = new HashMap<>();

		sample_9.put("SCHICHT_ID", "9");
		sample_9.put("ERK_ID", "FB8");
		sample_9.put("CHEMIE_ID", "C1");
		sample_9.put("SCHICHT_NR", "8");
		sample_9.put("SCHICHT_AUFSCHLUSS", "UG");
		sample_9.put("SCHICHT_ART", "Boden");
		sample_9.put("SCHICHT_KOERNUNG", "0/22");
		sample_9.put("SCHICHT_DICKE", "10");
		sample_9.put("SCHICHT_TIEFE_START", "18");
		sample_9.put("SCHICHT_TIEFE_ENDE", "28");
		sample_9.put("SCHICHT_PECH", "");
		sample_9.put("SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT", "BK, sg");
		sample_9.put("SCHICHT_FARBE", "rotbraun");
		sample_9.put("SCHICHT_FEUCHTIGKEIT", "");
		sample_9.put("SCHICHT_WASSERGEHALT", "");
		sample_9.put("SCHICHT_BEMERKUNGEN", "");
		sample_9.put("SCHICHT_BEHAELTNIS", "");
		sample_9.put("SCHICHT_ABFALLART", "Gem. a. G. (LS)");
		sample_9.put("SCHICHT_GERUCH", "neutral");
		sample_9.put("SCHICHT_RUK_NR", "");
		sample_9.put("SCHICHT_RUK_PROBE", "");
		sample_9.put("SCHICHT_RUK", "");
		sample_9.put("CHEMIE_MUFV", "nicht gefährlich");
		sample_9.put("CHEMIE_LAGA_BO", "Z0*");
		sample_9.put("CHEMIE_LAGA_RC", "Z1.1");
		sample_9.put("CHEMIE_LFS", "");
		sample_9.put("CHEMIE_LAGARC_ORIENTIERUNGSWERT", "eingehalten");
		sample_9.put("CHEMIE_TLGESTEIN", "RC1");
		sample_9.put("CHEMIE_DEPV", "DK0");
		sample_9.put("CHEMIE_ENTSCHEIDUNGSHILFE", "");
		sample_9.put("CHEMIE_REKU", "");
		sample_9.put("CHEMIE_ASBEST", "");
		sample_9.put("CHEMIE_PCB", "");
		sample_9.put("CHEMIE_BTEX", "");

		sample_9_UG = new Sample(sample_9);
	}
}
