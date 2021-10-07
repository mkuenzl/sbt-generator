package sbt.automization.templates;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EnterRobot implements Runnable
{
	@Override
	public void run()
	{
		Robot keyRoboter = null;
		try
		{
			Thread.sleep(2L * 1000L);
			keyRoboter = new Robot();
		} catch (AWTException | InterruptedException e)
		{
			e.printStackTrace();
		}
		keyRoboter.keyPress(KeyEvent.VK_ENTER);
	}
}
