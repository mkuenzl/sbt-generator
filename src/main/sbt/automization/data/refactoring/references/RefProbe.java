package sbt.automization.data.refactoring.references;

public enum RefProbe implements Reference
{
	FOOTNOTE_1
			{
				@Override
				public String getKey()
				{
					return addProbeTag("FOOTNOTE_1");
				}
			},
	FOOTNOTE_2
			{
				@Override
				public String getKey()
				{
					return addProbeTag("FOOTNOTE_2");
				}
			},
	FOOTNOTE_3
			{
				@Override
				public String getKey()
				{
					return addProbeTag("FOOTNOTE_3");
				}
			},
	FOOTNOTE_4
			{
				@Override
				public String getKey()
				{
					return addProbeTag("FOOTNOTE_4");
				}
			},
	FOOTNOTE_5
			{
				@Override
				public String getKey()
				{
					return addProbeTag("FOOTNOTE_5");
				}
			},
	FOOTNOTE_6
			{
				@Override
				public String getKey()
				{
					return addProbeTag("FOOTNOTE_6");
				}
			},
	FOOTNOTE_7
			{
				@Override
				public String getKey()
				{
					return addProbeTag("FOOTNOTE_7");
				}
			},
	FOOTNOTE_8
			{
				@Override
				public String getKey()
				{
					return addProbeTag("FOOTNOTE_8");
				}
			},
	FOOTNOTE_9
			{
				@Override
				public String getKey()
				{
					return addProbeTag("FOOTNOTE_9");
				}
			},
	FOOTNOTE_10
			{
				@Override
				public String getKey()
				{
					return addProbeTag("FOOTNOTE_10");
				}
			},
	EXTRACTION
			{
				@Override
				public String getKey()
				{
					return addProbeTag("ENTNAHME");
				}
			},
	ROOM
			{
				@Override
				public String getKey()
				{
					return addProbeTag("RAUM");
				}
			},
	FLOOR
			{
				@Override
				public String getKey()
				{
					return addProbeTag("ETAGE");
				}
			},
	BUILDING
			{
				@Override
				public String getKey()
				{
					return addProbeTag("GEBAEUDE");
				}
			},
	CHARACTER
			{
				@Override
				public String getKey()
				{
					return addProbeTag("PROBENCHARAKTER");
				}
			},
	PROBE_NUMBER
			{
				@Override
				public String getKey()
				{
					return addProbeTag("PROBENNUMMER");
				}
			},
	SMELL
			{
				@Override
				public String getKey()
				{
					return addProbeTag("GERUCH");
				}
			},
	COMPONENT
			{
				@Override
				public String getKey()
				{
					return addProbeTag("BAUTEIL");
				}
			},
	TARGET_DEPTH
			{
				@Override
				public String getKey()
				{
					return addProbeTag("ZIELTIEFE");
				}
			},
	COMPOSITE_UNDERLAY
			{
				@Override
				public String getKey()
				{
					return addProbeTag("VERBUND_UNTERLAGE");
				}
			},
	SOLE_DEPTH
			{
				@Override
				public String getKey()
				{
					return addProbeTag("SOHLE_TIEFE");
				}
			},
	WEAR_TRENCH_BOTTOM
			{
				@Override
				public String getKey()
				{
					return addProbeTag("TRAG_GRABENSOHLE");
				}
			},
	WEAR_PLANUM
			{
				@Override
				public String getKey()
				{
					return addProbeTag("TRAG_PLANUM");
				}
			},
	PITCH_QUANTITATIVE
			{
				@Override
				public String getKey()
				{
					return addProbeTag("PECH_QUANTITATIV");
				}
			},
	PITCH_HALF_QUANTITATIVE
			{
				@Override
				public String getKey()
				{
					return addProbeTag("PECH_HALBQUANTITATIV");
				}
			},
	PITCH_QUALITATIVE
			{
				@Override
				public String getKey()
				{
					return addProbeTag("PECH_QUALITATIV");
				}
			},
	LOAD_CLASS_BOARD
			{
				@Override
				public String getKey()
				{
					return addProbeTag("BELASTUNGSKLASSE_TAFEL");
				}
			},
	LOAD_CLASS
			{
				@Override
				public String getKey()
				{
					return addProbeTag("BELASTUNGSKLASSE");
				}
			},
	TOP_EDGE
			{
				@Override
				public String getKey()
				{
					return addProbeTag("OBERKANTE");
				}
			},
	OUTCROP_UG_OH_BA
			{
				@Override
				public String getKey()
				{
					return addProbeTag("AUFSCHLUSS_UG_OH_BA");
				}
			},
	OUTCROP_TOB
			{
				@Override
				public String getKey()
				{
					return addProbeTag("AUFSCHLUSS_TOB");
				}
			},
	OUTCROP_GOB
			{
				@Override
				public String getKey()
				{
					return addProbeTag("AUFSCHLUSS_OB");
				}
			},
	LOCATION
			{
				@Override
				public String getKey()
				{
					return addProbeTag("ORT");
				}
			},
	COORDINATES
			{
				@Override
				public String getKey()
				{
					return addProbeTag("KOORDINATEN");
				}
			},
	CONTACT_PERSON
			{
				@Override
				public String getKey()
				{
					return addProbeTag("ANSPRECHPARTNER");
				}
			},
	REGION
			{
				@Override
				public String getKey()
				{
					return addProbeTag("BEREICH");
				}
			},
	INSPECTOR
			{
				@Override
				public String getKey()
				{
					return addProbeTag("PRUEFER");
				}
			},
	DATE
			{
				@Override
				public String getKey()
				{
					return addProbeTag("DATUM");
				}
			},
	NUMBER
			{
				@Override
				public String getKey()
				{
					return addProbeTag("NUMMER");
				}
			},
	ID
			{
				@Override
				public String getKey()
				{
					return addProbeTag("ID");
				}
			},
	LP_ID
			{
				@Override
				public String getKey()
				{
					return addProbeTag("LP.ID");
				}
			};

	private static String addProbeTag(String parameter){
		return "PROBE." + parameter;
	}
}
