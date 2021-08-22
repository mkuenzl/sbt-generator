package sbt.automization.styles;

public enum ReportStyle implements Style
{
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
					return null;
				}
			},
	HEADER
			{
				@Override
				public String getStyleClass()
				{
					return "NormalHeader";
				}

				@Override
				public String getStyle()
				{
					return "width:110px";
				}
			},
	CELL
			{
				@Override
				public String getStyleClass()
				{
					return "NormalBold";
				}

				@Override
				public String getStyle()
				{
					return "width:60px";
				}
			},
	UNITCELL
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
			},
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
			}

}
