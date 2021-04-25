package sbt.automization.templates;

import sbt.automization.data.ExplorationSite;
import sbt.automization.util.Util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AReportTemplate extends AHtmlTemplate
{
	String layerId;

	Collection<List<ExplorationSite>> divideExplorationSites(List<ExplorationSite> sites)
	{

		List<ExplorationSite> templateExplorationSites = sites.stream()
				.filter(e -> e.getLayersWithOutcrop(layerId).size() > 0)
				.collect(Collectors.toList());

		Collection<List<ExplorationSite>> dividedExplorationSites = Util.separateBasedOnSize(templateExplorationSites, 17);

		return dividedExplorationSites;
	}

	abstract String buildTechnicalFeatures(List<ExplorationSite> explorationSites);

	abstract String buildEnvironmentTechnicalFeatures(List<ExplorationSite> explorationSites);
}
