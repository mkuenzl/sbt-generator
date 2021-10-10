package sbt.automization.view;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.core.templates.appendix.SamplingProtocol;

public class ErrorPopupTest
{
	@Test
	public void showStrategyErrorTest()
	{
		String strategyName = SamplingProtocol.getInstance().getClass().getSimpleName();

		Assert.assertEquals("SamplingProtocol", strategyName);
	}
}
