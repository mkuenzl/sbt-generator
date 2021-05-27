package sbt.automization.gui;

import org.junit.Test;
import sbt.automization.templates.AppendixPN;

public class ErrorPopupTest
{
	@Test
	public void showDialogTest()
	{
		String message = "Error Message String";
		ErrorPopup.showErrorMessage(message);
	}

	@Test
	public void showStrategyErrorTest()
	{
		String message = "Error Message String: ";
		ErrorPopup.showErrorMessage(message + AppendixPN.getInstance().getClass().getSimpleName());
	}
}
