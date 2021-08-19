package sbt.automization.gui.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoButtonActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JDialog dialog = new JDialog();
		dialog.setTitle("Help");
		dialog.setSize(300, 300);

		JPanel dialogpanel = new JPanel();
		dialogpanel.setSize(300, 300);
		dialogpanel.setBackground(Color.WHITE);

		JTextArea infotext = new JTextArea();
		infotext.setBounds(10, 10, 280, 280);
		infotext.setEditable(false);
		infotext.setWrapStyleWord(true);
		infotext.setLineWrap(true);
		infotext.setFont(new Font("Gill Sans MT",Font.BOLD, 14));
		infotext.setText("How to use available soon.");
		infotext.setVisible(true);

		dialogpanel.add(infotext);

		dialog.add(dialogpanel);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// calculate the new location of the window
		int w = dialog.getSize().width;
		int h = dialog.getSize().height;

		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		dialog.setLocation(x,y);
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}
