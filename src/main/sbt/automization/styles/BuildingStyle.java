package sbt.automization.styles;

public enum BuildingStyle implements Style
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
	ROW_THIN_8
			{
				@Override
				public String getStyleClass()
				{
					return "NormalThin8";
				}

				@Override
				public String getStyle()
				{
					return null;
				}
			},
	ROW_THIN_6
			{
				@Override
				public String getStyleClass()
				{
					return "NormalThin6";
				}

				@Override
				public String getStyle()
				{
					return null;
				}
			},
	HEAD
			{
				@Override
				public String getStyleClass()
				{
					return "NormalTableHeader";
				}

				@Override
				public String getStyle()
				{
					return "text-align:center;font-size:14.0pt;background:#AEAAAA;";
				}
			},
	HEADER_CELL
			{
				@Override
				public String getStyleClass()
				{
					return "NormalTableHeaderBuilding";
				}

				@Override
				public String getStyle()
				{
					return "";
				}
			},
	CELL
			{
				@Override
				public String getStyleClass()
				{
					return "NormalBuilding";
				}

				@Override
				public String getStyle()
				{
					return "";
				}
			},
	ROW_PHOTO
			{
				@Override
				public String getStyleClass()
				{
					return null;
				}

				@Override
				public String getStyle()
				{
					return "height:7.0cm";
				}
			},
	CELL_PHOTO
			{
				@Override
				public String getStyleClass()
				{
					return "NormalBuilding";
				}

				@Override
				public String getStyle()
				{
					return "width:5.3cm;height:7.0cm;text-align:center;padding:0cm 0cm 0cm 0cm";
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
							.append(";")
							.append("font-size:10.0pt")
							.append("'")
							.toString();
				}
			}
}
