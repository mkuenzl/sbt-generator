package sbt.automization.data.key;

public enum LpKey implements Key
{
	ID
			{
				@Override
				public String getKey()
				{
					return addLPTag("ID");
				}
			},
	NUMBER
			{
				@Override
				public String getKey()
				{
					return addLPTag("NUMMER");
				}
			},
	VALUE_1
			{
				@Override
				public String getKey()
				{
					return addLPTag("WERT_1");
				}
			},
	VALUE_2
			{
				@Override
				public String getKey()
				{
					return addLPTag("WERT_2");
				}
			},
	VALUE_3
			{
				@Override
				public String getKey()
				{
					return addLPTag("WERT_3");
				}
			},
	MEAN
			{
				@Override
				public String getKey()
				{
					return addLPTag("MITTELWERT");
				}
			},
	EV
			{
				@Override
				public String getKey()
				{
					return addLPTag("EV");
				}
			},
	EV85
			{
				@Override
				public String getKey()
				{
					return addLPTag("EV85");
				}
			},
	EV2
			{
				@Override
				public String getKey()
				{
					return addLPTag("EV2");
				}
			},
	EV2_TARGET
			{
				@Override
				public String getKey()
				{
					return addLPTag("EV2_SOLL");
				}
			};

	private static String addLPTag(String parameter)
	{
		return "PARAMETER.LP." + parameter;
	}
}
