package sbt.automization.data;

/**
 * Enum for all information receivable from the excel template.
 * TODO
 */
public enum InformationTag
{
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
}
