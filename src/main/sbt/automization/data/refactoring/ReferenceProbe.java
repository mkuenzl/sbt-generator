package sbt.automization.data.refactoring;

public enum ReferenceProbe implements Reference
{
	HEAP_SAMPLE_AMOUNT
			{
				@Override
				public String getKey()
				{
					return "ERK_HAUFWERK_PROBEN_ANZAHL";
				}
			},
	HEAP_VOLUME
			{
				@Override
				public String getKey()
				{
					return "ERK_HAUFWERK_VOLUMEN";
				}
			},
	HEAP_MATERIAL
			{
				@Override
				public String getKey()
				{
					return "ERK_HAUFWERK_MATERIAL";
				}
			},
	TARGET_DEPTH
			{
				@Override
				public String getKey()
				{
					return "ERK_ZIELTIEFE";
				}
			},
	LP_EV2_EXPECTED
			{
				@Override
				public String getKey()
				{
					return "ERK_LP_EV2_SOLL";
				}
			},
	LP_EV2
			{
				@Override
				public String getKey()
				{
					return "ERK_LP_EV2";
				}
			},
	LP_EV85
			{
				@Override
				public String getKey()
				{
					return "ERK_LP_EV15";
				}
			},
	LP_EV
			{
				@Override
				public String getKey()
				{
					return "ERK_LP_EV";
				}
			},
	LP_MEAN
			{
				@Override
				public String getKey()
				{
					return "ERK_LP_MEAN";
				}
			},
	LP_SAMPLE_3
			{
				@Override
				public String getKey()
				{
					return "ERK_LP3";
				}
			},
	LP_SAMPLE_2
			{
				@Override
				public String getKey()
				{
					return "ERK_LP2";
				}
			},
	LP_SAMPLE_1
			{
				@Override
				public String getKey()
				{
					return "ERK_LP1";
				}
			},
	LP_ID
			{
				@Override
				public String getKey()
				{
					return "ERK_LP";
				}
			},
	COMPOSITE_UNDERLAY
			{
				@Override
				public String getKey()
				{
					return "ERK_VERBUND_UNTERLAGE";
				}
			},
	SOLE_DEPTH
			{
				@Override
				public String getKey()
				{
					return "ERK_SOHLE_TIEFE";
				}
			},
	WEAR_TRENCH_BOTTOM
			{
				@Override
				public String getKey()
				{
					return "ERK_TRAG_GRABENSOHLE";
				}
			},
	WEAR_PLANUM
			{
				@Override
				public String getKey()
				{
					return "ERK_TRAG_PLANUM";
				}
			},
	PITCH_QUANTITATIVE
			{
				@Override
				public String getKey()
				{
					return "ERK_PECH_QUANTITATIV";
				}
			},
	PITCH_HALF_QUANTITATIVE
			{
				@Override
				public String getKey()
				{
					return "ERK_PECH_HALBQUANTITATIV";
				}
			},
	PITCH_QUALITATIVE
			{
				@Override
				public String getKey()
				{
					return "ERK_PECH_QUALITATIV";
				}
			},
	LOAD_CLASS_BOARD
			{
				@Override
				public String getKey()
				{
					return "ERK_BELASTUNGSKLASSE_TAFEL";
				}
			},
	LOAD_CLASS
			{
				@Override
				public String getKey()
				{
					return "ERK_BELASTUNGSKLASSE";
				}
			},
	TOP_EDGE
			{
				@Override
				public String getKey()
				{
					return "ERK_OBERKANTE";
				}
			},
	OUTCROP_UG_OH_BA
			{
				@Override
				public String getKey()
				{
					return "ERK_AUFSCHLUSS_UG_OH_BA";
				}
			},
	OUTCROP_TOB
			{
				@Override
				public String getKey()
				{
					return "ERK_AUFSCHLUSS_TOB";
				}
			},
	OUTCROP_OB
			{
				@Override
				public String getKey()
				{
					return "ERK_AUFSCHLUSS_OB";
				}
			},
	LOCATION
			{
				@Override
				public String getKey()
				{
					return "ERK_ORT";
				}
			},
	COORDINATES
			{
				@Override
				public String getKey()
				{
					return "ERK_KOORDINATEN";
				}
			},
	CONTACT_PERSON
			{
				@Override
				public String getKey()
				{
					return "ERK_ANSPRECHPARTNER";
				}
			},
	REGION
			{
				@Override
				public String getKey()
				{
					return "ERK_BEREICH";
				}
			},
	INSPECTOR
			{
				@Override
				public String getKey()
				{
					return "ERK_PRUEFER";
				}
			},
	DATE
			{
				@Override
				public String getKey()
				{
					return "ERK_DATUM";
				}
			},
	NUMBER
			{
				@Override
				public String getKey()
				{
					return "ERK_NUMMER";
				}
			},
	ID
			{
				@Override
				public String getKey()
				{
					return "ERK_ID";
				}
			}
}
