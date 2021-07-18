package sbt.automization.gui;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.templates.AppendixPN;

public class ErrorPopupTest
{
	@Test
	public void showStrategyErrorTest()
	{
		String strategyName = AppendixPN.getInstance().getClass().getSimpleName();

		Assert.assertEquals("AppendixPN", strategyName);
	}
}
