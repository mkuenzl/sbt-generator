package sbt.automization.view.element;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoButton extends CustomButton
{
	public InfoButton(String s)
	{
		super(s);
		this.setBorder(new LineBorder(Color.white));
		addListener();
		setImage("/questionmark-icon.png");
		this.setBackground(Color.white);
	}
	
	private void setImage(String path)
	{
		ImageIcon infoIcon = new ImageIcon(getClass().getResource(path));
		Image scaledInstance = infoIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon infoIconScaled = new ImageIcon(scaledInstance);
		this.setIcon(infoIconScaled);
	}
	
	private void addListener()
	{
		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				HelpTextBox info = new HelpTextBox();
				info.draw();
			}
		});
	}
	
	public InfoButton(String text, Rectangle position)
	{
		super(text, position);
		this.setBorder(new LineBorder(Color.white));
		addListener();
		setImage("/questionmark-icon.png");
		this.setBackground(Color.white);
	}
}
