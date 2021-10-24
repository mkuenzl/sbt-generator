package sbt.automization.view.element;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TextInputArea extends JTextArea
{
	
	public TextInputArea(String text, Rectangle position)
	{
		this.setBounds(position);
		this.setEditable(true);
		this.setWrapStyleWord(true);
		this.setLineWrap(true);
		this.setBorder(new LineBorder(Color.black));
		this.setBackground(Color.lightGray);
		this.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
	}
}
