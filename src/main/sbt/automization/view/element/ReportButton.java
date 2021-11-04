package sbt.automization.view.element;

import sbt.automization.core.util.FileUtils;
import sbt.automization.core.util.MailHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

//<div>Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/"
// title="Flaticon">www.flaticon.com</a></div>

public class ReportButton extends CustomButton
{
	public ReportButton(String s)
	{
		super(s);
		this.setBorder(new LineBorder(Color.white));
		addListener();
		setImage("/icons/exclamation-mark-icon.png");
		this.setBackground(Color.white);
	}
	
	private void setImage(String path)
	{
		ImageIcon infoIcon = new ImageIcon(getClass().getResource(path));
		Image scaledInstance = infoIcon.getImage().getScaledInstance(this.getWidth()-2, this.getHeight()-2,
				Image.SCALE_SMOOTH);
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
				MailHandler mailHandler = new MailHandler();
				mailHandler.setMailTo("mo.kuenzl@gmail.com");
				mailHandler.setHeader("SBT Generator Issue Report");
				String mailTemplate = FileUtils.parseFileToString("/report-mail-template.txt");
				mailHandler.setBody(mailTemplate);
				try
				{
					mailHandler.openMail();
				} catch (URISyntaxException | IOException ex)
				{
					ex.printStackTrace();
				}
			}
		});
	}
	
	public ReportButton(String text, Rectangle position)
	{
		super(text, position);
		this.setBorder(new LineBorder(Color.white));
		addListener();
		setImage("/icons/exclamation-mark-icon.png");
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}
	
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