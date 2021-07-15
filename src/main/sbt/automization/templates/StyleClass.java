package sbt.automization.templates;

/**
 * Class for css access TODO
 */
public enum StyleClass
{
	NORMAL_EXPLORATION_SITE_CELL
			{
				@Override
				public String getName()
				{
					return "NormalErkundungsstelle";
				}

				@Override
				public String print()
				{
					return null;
				}
			};

	public abstract String getName();

	public abstract String print();
}
