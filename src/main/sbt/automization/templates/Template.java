package sbt.automization.templates;

/**
 * Template Enum not in use TODO
 */
public enum Template
{
	//TODO change for Checkboxes / StrategyStorage
	APPENDIX_LP
			{
				@Override
				public String getName()
				{
					return "";
				}

				@Override
				public IHtmlTable getStrategy()
				{
					return AppendixLP.getInstance();
				}
			},
	APPENDIX_ERK
			{
				@Override
				public String getName()
				{
					return "";
				}

				@Override
				public IHtmlTable getStrategy()
				{
					return AppendixExplorationSite.getInstance();
				}
			},
	APPENDIX_PN
			{
				@Override
				public String getName()
				{
					return "";
				}

				@Override
				public IHtmlTable getStrategy()
				{
					return AppendixPN.getInstance();
				}
			},
	APPENDIX_RUK
			{
				@Override
				public String getName()
				{
					return "";
				}

				@Override
				public IHtmlTable getStrategy()
				{
					return AppendixRUK.getInstance();
				}
			},
	APPENDIX_PN_HEAP
			{
				@Override
				public String getName()
				{
					return null;
				}

				@Override
				public IHtmlTable getStrategy()
				{
					return null;
				}
			};

	public abstract String getName();

	public abstract IHtmlTable getStrategy();
}
