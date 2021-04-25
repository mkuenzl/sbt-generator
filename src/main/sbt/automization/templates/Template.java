package sbt.automization.templates;

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
				public IHtmlTemplate getStrategy()
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
				public IHtmlTemplate getStrategy()
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
				public IHtmlTemplate getStrategy()
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
				public IHtmlTemplate getStrategy()
				{
					return AppendixRUK.getInstance();
				}
			};

	public abstract String getName();

	public abstract IHtmlTemplate getStrategy();
}
