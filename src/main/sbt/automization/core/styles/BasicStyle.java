package sbt.automization.core.styles;

/**
 * Class for css access TODO
 */
public enum BasicStyle implements Style
{
	TABLE
			{
				@Override
				public String getStyleClass()
				{
					return "MsoNormalTable";
				}

				@Override
				public String getStyle()
				{
					return new StringBuilder()
							.append("'")
							.append("border-collapse:collapse")
							.append(";")
							.append("mso-table-layout-alt:fixed")
							.append(";")
							.append("border:none")
							.append(";")
							.append("mso-border-alt:solid windowtext .5pt")
							.append(";")
							.append("mso-padding-alt:0cm 5.4pt 0cm 5.4pt")
							.append("'")
							.toString();
				}
			},
	ROW
			{
				@Override
				public String getStyleClass()
				{
					return "Normal";
				}

				@Override
				public String getStyle()
				{
					return "";
				}
			},
	TEXT
			{
				@Override
				public String getStyleClass()
				{
					return "Normal";
				}

				@Override
				public String getStyle()
				{
					return null;
				}
			},
	TEXT_SMALL
			{
				@Override
				public String getStyleClass()
				{
					return "Normal6";
				}

				@Override
				public String getStyle()
				{
					return null;
				}
			}

}
