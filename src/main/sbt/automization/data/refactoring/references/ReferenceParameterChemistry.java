package sbt.automization.data.refactoring.references;

public enum ReferenceParameterChemistry implements Reference
{
	WASTE_WOOD_ORDINANCE
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("ALTHOLZ_VERORDNUNG");
				}
			},
	PAK
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("PAK");
				}
			},
	KMF
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("KMF");
				}
			},
	SULFATE
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("SULFAT");
				}
			},
	ICP_SCREENING
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("ICP_SCREENING");
				}
			},
	PHENOL
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("PHENOLE");
				}
			},
	EOX
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("EOX");
				}
			},
	BTEX
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("BTEX");
				}
			},
	PCB
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("PCB");
				}
			},
	ASBESTOS
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("ASBEST");
				}
			},
	WASTE_KEY
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("ABFALLSCHLUESSEL");
				}
			},
	REKU
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("REKU");
				}
			},
	DECISION_SUPPORT
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("ENTSCHEIDUNGSHILFE");
				}
			},
	DEPV
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("DEPV");
				}
			},
	TL_ROCK_STRATUM
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("TL_GESTEIN");
				}
			},
	LAGA_RC_ORIENTATION
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("LAGA_RC_ORIENTIERUNGSWERT");
				}
			},
	LAGA_RC
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("LAGA_RC");
				}
			},
	LAGA_BO
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("LAGA_BO");
				}
			},
	LFS
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("LFS");
				}
			},
	MUFV
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("MUFV");
				}
			},
	MUFV_PARAMETER
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("MUFV_PARAMETER");
				}
			},
	ID
			{
				@Override
				public String getKey()
				{
					return addChemistryTag("ID");
				}
			};

	private static String addChemistryTag(String parameter){
		return "PARAMETER.CHEMISTRY." + parameter;
	}
}
