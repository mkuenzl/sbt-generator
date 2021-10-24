package sbt.automization.core.data.key;

public enum SampleKey implements Key
{
	WASTE_KEY_MIX
			{
				@Override
				public String getKey()
				{
					return addSampleTag("ABFALLSCHLUESSEL_GEMISCH");
				}
			},
	WASTE_KEY_MATERIAL
			{
				@Override
				public String getKey()
				{
					return addSampleTag("ABFALLSCHLUESSEL_MATERIAL");
				}
			},
	MATERIAL_COMPARISON
			{
				@Override
				public String getKey()
				{
					return addSampleTag("MATERIAL_VERGLEICH");
				}
			},
	SUSPECTED_POLLUTANT
			{
				@Override
				public String getKey()
				{
					return addSampleTag("SCHADSTOFFVERDACHT");
				}
			},
	EXTRACTION
			{
				@Override
				public String getKey()
				{
					return addSampleTag("ENTNAHME");
				}
			},
	MATERIAL
			{
				@Override
				public String getKey()
				{
					return addSampleTag("MATERIAL");
				}
			},
	SAMPLE_AMOUNT
			{
				@Override
				public String getKey()
				{
					return addSampleTag("PROBEN");
				}
			},
	VOLUME
			{
				@Override
				public String getKey()
				{
					return addSampleTag("VOLUMEN");
				}
			},
	HOMOGENEOUS_RANGE
			{
				@Override
				public String getKey()
				{
					return addSampleTag("HOMOGENBEREICH");
				}
			},
	COMPRESSIBILITY
			{
				@Override
				public String getKey()
				{
					return addSampleTag("VERDICHTUNGSFAEHIGKEIT");
				}
			},
	FROST_SENSITIVITY_CLASS
			{
				@Override
				public String getKey()
				{
					return addSampleTag("FROSTEMPFINDLICHKEITSKLASSE");
				}
			},
	COMPRESSIVE_STRENGTH
			{
				@Override
				public String getKey()
				{
					return addSampleTag("DRUCKFESTIGKEIT");
				}
			},
	GRAIN_SIZE_DISTRIBUTION
			{
				@Override
				public String getKey()
				{
					return addSampleTag("KORNGROESSENVERTEILUNG");
				}
			},
	SMELL
			{
				@Override
				public String getKey()
				{
					return addSampleTag("GERUCH");
				}
			},
	WASTE_TYPE
			{
				@Override
				public String getKey()
				{
					return addSampleTag("ABFALLART");
				}
			},
	CONTAINER
			{
				@Override
				public String getKey()
				{
					return addSampleTag("BEHAELTNIS");
				}
			},
	NOTE
			{
				@Override
				public String getKey()
				{
					return addSampleTag("NOTIZ");
				}
			},
	WATER_PROCTOR
			{
				@Override
				public String getKey()
				{
					return addSampleTag("WASSERPROCTOR");
				}
			},
	WATER_CONTENT
			{
				@Override
				public String getKey()
				{
					return addSampleTag("WASSERGEHALT");
				}
			},
	MOISTURE
			{
				@Override
				public String getKey()
				{
					return addSampleTag("FEUCHTIGKEIT");
				}
			},
	CONSISTENCY
			{
				@Override
				public String getKey()
				{
					return addSampleTag("KONSISTENZ");
				}
			},
	SOIL_TYPE
			{
				@Override
				public String getKey()
				{
					return addSampleTag("BODENART");
				}
			},
	COLOR
			{
				@Override
				public String getKey()
				{
					return addSampleTag("FARBE");
				}
			},
	ROUNDING_GRADATION
			{
				@Override
				public String getKey()
				{
					return addSampleTag("RUNDUNGSGRAD_GESTUFTHEIT");
				}
			},
	PITCH
			{   //TAR
				
				@Override
				public String getKey()
				{
					return addSampleTag("PECH");
				}
			},
	THICKNESS
			{
				@Override
				public String getKey()
				{
					return addSampleTag("DICKE");
				}
			},
	SOIL_CLASS
			{
				@Override
				public String getKey()
				{
					return addSampleTag("BODENKLASSE");
				}
			},
	GRANULATION
			{
				@Override
				public String getKey()
				{
					return addSampleTag("KOERNUNG");
				}
			},
	TYPE
			{
				@Override
				public String getKey()
				{
					return addSampleTag("ART");
				}
			},
	OUTCROP
			{
				@Override
				public String getKey()
				{
					return addSampleTag("AUFSCHLUSS");
				}
			},
	NUMBER
			{
				@Override
				public String getKey()
				{
					return addSampleTag("NUMMER");
				}
			},
	ID
			{
				@Override
				public String getKey()
				{
					return addSampleTag("ID");
				}
			},
	DEPTH_START
			{
				@Override
				public String getKey()
				{
					return addSampleTag("TIEFE_START");
				}
			},
	DEPTH_END
			{
				@Override
				public String getKey()
				{
					return addSampleTag("TIEFE_ENDE");
				}
			},
	CHEMISTRY_ID
			{
				@Override
				public String getKey()
				{
					return addSampleTag("CHEMISTRY.ID");
				}
			},
	RUK_ID
			{
				@Override
				public String getKey()
				{
					return addSampleTag("RUK.ID");
				}
			},
	PROBE_ID
			{
				@Override
				public String getKey()
				{
					return addSampleTag("PROBE.ID");
				}
			};
	
	private static String addSampleTag(String parameter)
	{
		return "SAMPLE." + parameter;
	}
}
