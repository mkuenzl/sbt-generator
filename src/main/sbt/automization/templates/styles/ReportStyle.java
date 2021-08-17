package sbt.automization.templates.styles;

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
			}
}
