package sbt.automization.data.refactoring;

public enum ReferenceParameter implements Reference
{
	BTEX
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_BTEX";
				}
			},
	PCB
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_PCB";
				}
			},
	ASBESTOS
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_ASBEST";
				}
			},
	WASTE_KEY
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_ABFALLSCHLUESSEL";
				}
			},
	REKU
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_REKU";
				}
			},
	DECISION_SUPPORT
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_ENTSCHEIDUNGSHILFE";
				}
			},
	DEPV
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_DEPV";
				}
			},
	TL_ROCK_STRATUM
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_TLGESTEIN";
				}
			},
	LAGA_RC_ORIENTATION
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_LAGARC_ORIENTIERUNGSWERT";
				}
			},
	LAGA_RC
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_LAGA_RC";
				}
			},
	LAGA_BO
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_LAGA_BO";
				}
			},
	LFS
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_LFS";
				}
			},
	MUFV
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_MUFV";
				}
			},
	ID
			{
				@Override
				public String getKey()
				{
					return "CHEMIE_ID";
				}
			}
}
