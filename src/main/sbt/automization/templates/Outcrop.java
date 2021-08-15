package sbt.automization.templates;

import org.apache.commons.cli.Option;

public enum Outcrop
{
	GOB{
		@Override
		String toString(String parameter)
		{
			return "GOB";
		}
	},
	TMHB{
		@Override
		String toString(String parameter)
		{
			return "TMHB";
		}
	},
	CONCRETE{
		@Override
		String toString(String parameter)
		{
			return "BETON";
		}
	},
	SEAL{
		@Override
		String toString(String parameter)
		{
			return "ABDICHTUNG";
		}
	},
	COATING{
		@Override
		String toString(String parameter)
		{
			return "BESCHICHTUNG";
		}
	},
	BANQUET{
		@Override
		String toString(String parameter)
		{
			return "BANKETT";
		}
	},
	GAP{
		@Override
		String toString(String parameter)
		{
			return "FUGE";
		}
	},
	TOB{
		@Override
		String toString(String parameter)
		{
			return "TOB";
		}
	},
	UG{
		@Override
		String toString(String parameter)
		{
			return "UG";
		}
	},
	OH{
		@Override
		String toString(String parameter)
		{
			return "OH";
		}
	};



	abstract String toString(String parameter);
}
