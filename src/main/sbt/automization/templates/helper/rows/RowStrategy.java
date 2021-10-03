package sbt.automization.templates.helper.rows;

public interface RowStrategy
{
	String buildWithProbes();

	String buildWithSamples();

	String buildWithSamplesCombined();

	String buildWithChemistrySamplesCombined();
}
