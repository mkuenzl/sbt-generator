package sbt.automization.data;

public enum Outcrop
{
	GOB
			{
				@Override
				public String toString()
				{
					return "GOB";
				}
			},
	TMHB
			{
				@Override
				public String toString()
				{
					return "TMHB";
				}
			},
	CONCRETE
			{
				@Override
				public String toString()
				{
					return "BETON";
				}
			},
	SEAL
			{
				@Override
				public String toString()
				{
					return "ABDICHTUNG";
				}
			},
	COATING
			{
				@Override
				public String toString()
				{
					return "BESCHICHTUNG";
				}
			},
	BANQUET
			{
				@Override
				public String toString()
				{
					return "BANKETT";
				}
			},
	GAP
			{
				@Override
				public String toString()
				{
					return "FUGE";
				}
			},
	TOB
			{
				@Override
				public String toString()
				{
					return "TOB";
				}
			},
	UG
			{
				@Override
				public String toString()
				{
					return "UG";
				}
			},
	HEAP
			{
				@Override
				public String toString()
				{
					return "HAUFWERK";
				}
			},
	OH
			{
				@Override
				public String toString()
				{
					return "OH";
				}
			}, BUILDING;
}
