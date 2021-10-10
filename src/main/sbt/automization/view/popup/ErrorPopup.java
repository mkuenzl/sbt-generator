package sbt.automization.view.popup;

import javax.swing.*;

public final class ErrorPopup
{
	final static JOptionPane pane = new JOptionPane();

	private ErrorPopup() {}

	public static void showMessage(String message)
	{
		JOptionPane.showMessageDialog(new JFrame(), message, "Error Dialog",
				JOptionPane.ERROR_MESSAGE);
	}
}
