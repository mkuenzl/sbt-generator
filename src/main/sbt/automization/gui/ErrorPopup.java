package sbt.automization.gui;

import javax.swing.*;

public final class ErrorPopup
{
	final static JOptionPane pane = new JOptionPane();

	private ErrorPopup() {}

	public static void showErrorMessage(String message)
	{
		JOptionPane.showMessageDialog(new JFrame(), message, "Error Dialog",
				JOptionPane.ERROR_MESSAGE);
	}
}
