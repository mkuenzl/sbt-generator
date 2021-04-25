package sbt.automization.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomCheckBox extends JCheckBox implements MouseListener
{

	Font defaultFont = new Font("Gill Sans MT", Font.BOLD, 10);
	Color textColor = Color.decode("#000000");
	Color backgroundColor = Color.decode("#ffffff");
	Color hoverColor = Color.decode("#00aced");

	public CustomCheckBox(String s, Boolean selected)
	{
		s = s.toUpperCase();
		this.setSelected(selected);
		this.setFocusPainted(false);
		this.setText(s);
		this.setBorder(null);
		this.setForeground(textColor);
		this.setBackground(backgroundColor);
		this.setFont(defaultFont);
		this.setOpaque(true);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setEnabled(true);
		this.setVisible(true);
		addMouseListener(this);
	}

	public CustomCheckBox(String s, Boolean selected, Color backgroundColor, Color hoverColor)
	{
		s = s.toUpperCase();
		this.setSelected(selected);
		this.setFocusPainted(false);
		this.setText(s);
		this.setBorder(null);
		this.setHoverColor(hoverColor);
		this.setBackground(backgroundColor);
		this.setFont(defaultFont);
		this.setOpaque(true);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setEnabled(true);
		this.setVisible(true);
		addMouseListener(this);
	}

	public void setBackgroundColor(Color color)
	{
		backgroundColor = color;
	}

	public void setHoverColor(Color color)
	{
		hoverColor = color;
	}

	@Override
	public void mouseClicked(MouseEvent me) {}

	@Override
	public void mouseReleased(MouseEvent me) {}

	@Override
	public void mousePressed(MouseEvent me) {}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (e.getSource() == this)
		{
			this.setBackground(this.hoverColor);
		}
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		if (e.getSource() == this)
		{
			this.setBackground(this.backgroundColor);
		}
	}
}