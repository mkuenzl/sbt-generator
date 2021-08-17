package sbt.automization.templates.styles;

/**
 * Class for css access TODO
 */
public enum BasicStyle implements Style
{
	NORMAL_EXPLORATION_SITE_CELL
			{
				@Override
				public String getStyleClass()
				{
					return "NormalErkundungsstelle";
				}

				@Override
				public String getStyle()
				{
					return null;
				}
			}
}
