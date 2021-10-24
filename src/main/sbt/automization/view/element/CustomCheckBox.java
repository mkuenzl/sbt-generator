package sbt.automization.view.element;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class CustomCheckBox extends JCheckBox implements MouseListener
{
	
	Font defaultFont = new Font("Gill Sans MT", Font.BOLD, 10);
	Color textColor = Color.decode("#000000");
	Color backgroundColor = Color.decode("#ffffff");
	Color hoverColor = Color.decode("#00aced");
	
	public CustomCheckBox(String text, Rectangle position)
	{
		text = text.toUpperCase();
		this.setBounds(position);
		this.setSelected(false);
		this.setFocusPainted(false);
		this.setText(text);
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
	
	public CustomCheckBox(String s, Rectangle position, Color backgroundColor, Color hoverColor)
	{
		s = s.toUpperCase();
		this.setBounds(position);
		this.setSelected(false);
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
	
	public void setHoverColor(Color color)
	{
		hoverColor = color;
	}
	
	public void setBackgroundColor(Color color)
	{
		backgroundColor = color;
	}
	
	@Override
	public void mouseClicked(MouseEvent me)
	{
	}
	
	@Override
	public void mousePressed(MouseEvent me)
	{
	}
	
	@Override
	public void mouseReleased(MouseEvent me)
	{
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}