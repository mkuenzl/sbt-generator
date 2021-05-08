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
			},
	APPENDIX_PN_HEAP
			{
				@Override
				public String getName()
				{
					return null;
				}

				@Override
				public IHtmlTemplate getStrategy()
				{
					return null;
				}
			};

	public abstract String getName();

	public abstract IHtmlTemplate getStrategy();
}
