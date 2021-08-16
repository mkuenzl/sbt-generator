package sbt.automization.data.refactoring.references;

public enum RuK implements Reference
{
	ID
			{
				@Override
				public String getKey()
				{
					return addRuKTag("ID");
				}
			},
	NUMBER
			{
				@Override
				public String getKey()
				{
					return addRuKTag("NUMMER");
				}
			},
	SAMPLE
			{
				@Override
				public String getKey()
				{
					return addRuKTag("PROBENART");
				}
			},
	VALUE
			{
				@Override
				public String getKey()
				{
					return addRuKTag("WERT");
				}
			};

	private static String addRuKTag(String parameter){
		return "PARAMETER.RUK." + parameter;
	}
}
