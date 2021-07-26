package sbt.automization.data;

/**
 * Enum for all information receivable from the excel template.
 * TODO
 */
public enum ReferenceKey
{
	SITE_HEAP_SAMPLE_AMOUNT
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_HAUFWERK_PROBEN_ANZAHL";
				}
			},
	SITE_HEAP_VOLUME
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_HAUFWERK_VOLUMEN";
				}
			},
	SITE_HEAP_MATERIAL
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_HAUFWERK_MATERIAL";
				}
			},
	SITE_TARGET_DEPTH
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_ZIELTIEFE";
				}
			},
	SITE_LP_EV2_EXPECTED
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_LP_EV2_SOLL";
				}
			},
	SITE_LP_EV2
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_LP_EV2";
				}
			},
	SITE_LP_EV85
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_LP_EV15";
				}
			},
	SITE_LP_EV
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_LP_EV";
				}
			},
	SITE_LP_MEAN
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_LP_MEAN";
				}
			},
	SITE_LP_SAMPLE_3
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_LP3";
				}
			},
	SITE_LP_SAMPLE_2
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_LP2";
				}
			},
	SITE_LP_SAMPLE_1
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_LP1";
				}
			},
	SITE_LP
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_LP";
				}
			},
	SITE_COMPOSITE_UNDERLAY
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_VERBUND_UNTERLAGE";
				}
			},
	SITE_SOLE_DEPTH
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_SOHLE_TIEFE";
				}
			},
	SITE_WEAR_TRENCH_BOTTOM
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_TRAG_GRABENSOHLE";
				}
			},
	SITE_WEAR_PLANUM
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_TRAG_PLANUM";
				}
			},
	SITE_PITCH_QUANTITATIVE
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_PECH_QUANTITATIV";
				}
			},
	SITE_PITCH_HALF_QUANTITATIVE
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_PECH_HALBQUANTITATIV";
				}
			},
	SITE_PITCH_QUALITATIVE
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_PECH_QUALITATIV";
				}
			},
	SITE_LOAD_CLASS_BOARD
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_BELASTUNGSKLASSE_TAFEL";
				}
			},
	SITE_LOAD_CLASS
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_BELASTUNGSKLASSE";
				}
			},
	SITE_TOP_EDGE
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_OBERKANTE";
				}
			},
	SITE_OUTCROP_UG_OH_BA
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_AUFSCHLUSS_UG_OH_BA";
				}
			},
	SITE_OUTCROP_TOB
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_AUFSCHLUSS_TOB";
				}
			},
	SITE_OUTCROP_OB
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_AUFSCHLUSS_OB";
				}
			},
	SITE_LOCATION
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_ORT";
				}
			},
	SITE_COORDINATES
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_KOORDINATEN";
				}
			},
	SITE_CONTACT_PERSON
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_ANSPRECHPARTNER";
				}
			},
	SITE_REGION
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_BEREICH";
				}
			},
	SITE_INSPECTOR
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_PRUEFER";
				}
			},
	SITE_DATE
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_DATUM";
				}
			},
	SITE_NUMBER
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_NUMMER";
				}
			},
	SITE_ID
			{
				@Override
				public String getIdentifier()
				{
					return "ERK_ID";
				}
			},
	CHEMISTRY_BTEX
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_BTEX";
				}
			},
	CHEMISTRY_PCB
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_PCB";
				}
			},
	CHEMISTRY_ASBESTOS
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_ASBEST";
				}
			},
	CHEMISTRY_WASTE_KEY
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_ABFALLSCHLUESSEL";
				}
			},
	CHEMISTRY_REKU
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_REKU";
				}
			},
	CHEMISTRY_DECISION_SUPPORT
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_ENTSCHEIDUNGSHILFE";
				}
			},
	CHEMISTRY_DEPV
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_DEPV";
				}
			},
	CHEMISTRY_TL_ROCK_STRATUM
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_TLGESTEIN";
				}
			},
	CHEMISTRY_LAGA_RC_ORIENTATION
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_LAGARC_ORIENTIERUNGSWERT";
				}
			},
	CHEMISTRY_LAGA_RC
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_LAGA_RC";
				}
			},
	CHEMISTRY_LAGA_BO
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_LAGA_BO";
				}
			},
	CHEMISTRY_LFS
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_LFS";
				}
			},
	CHEMISTRY_MUFV
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_MUFV";
				}
			},
	CHEMISTRY_ID
			{
				@Override
				public String getIdentifier()
				{
					return "CHEMIE_ID";
				}
			},
	LAYER_HOMOGENEOUS_RANGE
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_HOMOGENBEREICH";
				}
			},
	LAYER_COMPRESSIBILITY
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_VERDICHTUNGSFAEHIGKEIT";
				}
			},
	LAYER_FROST_SENSITIVITY_CLASS
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_FROSTEMPFINDLICHKEITSKLASSE";
				}
			},
	LAYER_COMPRESSIVE_STRENGTH
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_DRUCKFESTIGKEIT";
				}
			},
	LAYER_RUK_SAMPLE
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_RUK_PROBE";
				}
			},
	LAYER_RUK
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_RUK";
				}
			},
	LAYER_GRAIN_SIZE_DISTRIBUTION
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_KORNGROESSENVERTEILUNG";
				}
			},
	LAYER_SMELL
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_GERUCH";
				}
			},
	LAYER_WASTE_TYPE
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_ABFALLART";
				}
			},
	LAYER_CONTAINER
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_BEHAELTNIS";
				}
			},
	LAYER_NOTE
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_NOTIZ";
				}
			},
	LAYER_WATER_PROCTOR
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_WASSERPROCTOR";
				}
			},
	LAYER_WATER_CONTENT
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_WASSERGEHALT";
				}
			},
	LAYER_MOISTURE
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_FEUCHTIGKEIT";
				}
			},
	LAYER_CONSISTENCY
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_KONSISTENZ";
				}
			},
	LAYER_SOIL_TYPE
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_BODENART";
				}
			},
	LAYER_COLOR
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_FARBE";
				}
			},
	LAYER_ROUNDING_GRADATION
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT";
				}
			},
	LAYER_PAK
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_PAK";
				}
			},
	LAYER_PITCH
			{   //TAR

				@Override
				public String getIdentifier()
				{
					return "SCHICHT_PECH";
				}
			},
	LAYER_THICKNESS
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_DICKE";
				}
			},
	LAYER_SOIL_CLASS
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_BODENKLASSE";
				}
			},
	LAYER_GRANULATION
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_KOERNUNG";
				}
			},
	LAYER_TYPE
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_ART";
				}
			},
	LAYER_OUTCROP
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_AUFSCHLUSS";
				}
			},
	LAYER_NUMBER
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_NR";
				}
			},
	LAYER_ID
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_ID";
				}
			},
	LAYER_DEPTH_START
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_TIEFE_START";
				}
			},
	LAYER_DEPTH_END
			{
				@Override
				public String getIdentifier()
				{
					return "SCHICHT_TIEFE_ENDE";
				}
			};

	public abstract String getIdentifier();

	//public abstract String getType();
}
