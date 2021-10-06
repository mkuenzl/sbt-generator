package sbt.automization.gui;

import javax.swing.*;

public final class InfoPopup
{
	final static JOptionPane pane = new JOptionPane();

	private InfoPopup() {}

	public static void showMessage(String message)
	{
		JOptionPane.showMessageDialog(new JFrame(), message, "Information Dialog",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
