package sbt.automization.view.element;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

//<div>Icons made by <a href="https://www.flaticon.com/authors/apien" title="apien">apien</a> from <a href="https" +
//		"://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>

public class InfoButton extends CustomButton
{
	public InfoButton(String s)
	{
		super(s);
		this.setBorder(new LineBorder(Color.white));
		addListener();
		setImage("/icons/question-mark-icon.png");
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
		setImage("/icons/question-mark-icon.png");
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (e.getSource() == this)
		{
			this.setOpaque(false);
			this.setContentAreaFilled(true);
			this.setBorderPainted(true);
			this.setBackground(this.hoverColor);
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		if (e.getSource() == this)
		{
			this.setOpaque(false);
			this.setContentAreaFilled(false);
			this.setBorderPainted(false);
			this.setBackground(this.backgroundColor);
		}
	}
}
