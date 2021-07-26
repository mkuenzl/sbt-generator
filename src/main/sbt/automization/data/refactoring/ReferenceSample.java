package sbt.automization.data.refactoring;

public enum ReferenceSample implements Reference
{
	HOMOGENEOUS_RANGE
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_HOMOGENBEREICH";
				}
			},
	COMPRESSIBILITY
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_VERDICHTUNGSFAEHIGKEIT";
				}
			},
	FROST_SENSITIVITY_CLASS
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_FROSTEMPFINDLICHKEITSKLASSE";
				}
			},
	COMPRESSIVE_STRENGTH
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_DRUCKFESTIGKEIT";
				}
			},
	RUK_SAMPLE
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_RUK_PROBE";
				}
			},
	RUK
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_RUK";
				}
			},
	GRAIN_SIZE_DISTRIBUTION
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_KORNGROESSENVERTEILUNG";
				}
			},
	SMELL
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_GERUCH";
				}
			},
	WASTE_TYPE
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_ABFALLART";
				}
			},
	CONTAINER
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_BEHAELTNIS";
				}
			},
	NOTE
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_NOTIZ";
				}
			},
	WATER_PROCTOR
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_WASSERPROCTOR";
				}
			},
	WATER_CONTENT
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_WASSERGEHALT";
				}
			},
	MOISTURE
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_FEUCHTIGKEIT";
				}
			},
	CONSISTENCY
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_KONSISTENZ";
				}
			},
	SOIL_TYPE
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_BODENART";
				}
			},
	COLOR
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_FARBE";
				}
			},
	ROUNDING_GRADATION
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_RUNDUNGSGRAD_GESTUFTHEIT";
				}
			},
	PAK
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_PAK";
				}
			},
	PITCH
			{   //TAR
				@Override
				public String getKey()
				{
					return "SCHICHT_PECH";
				}
			},
	THICKNESS
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_DICKE";
				}
			},
	SOIL_CLASS
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_BODENKLASSE";
				}
			},
	GRANULATION
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_KOERNUNG";
				}
			},
	TYPE
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_ART";
				}
			},
	OUTCROP
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_AUFSCHLUSS";
				}
			},
	NUMBER
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_NR";
				}
			},
	ID
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_ID";
				}
			},
	DEPTH_START
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_TIEFE_START";
				}
			},
	DEPTH_END
			{
				@Override
				public String getKey()
				{
					return "SCHICHT_TIEFE_ENDE";
				}
			};
}
