package sbt.automization.gui;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.templates.appendix.SamplingProtocol;

public class ErrorPopupTest
{
	@Test
	public void showStrategyErrorTest()
	{
		String strategyName = SamplingProtocol.getInstance().getClass().getSimpleName();

		Assert.assertEquals("AppendixPN", strategyName);
	}
}
