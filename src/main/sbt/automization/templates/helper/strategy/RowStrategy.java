package sbt.automization.templates.helper.strategy;

public interface RowStrategy
{
	String buildWithProbes();

	String buildWithSamples();

	String buildWithSamplesCombined();
}
