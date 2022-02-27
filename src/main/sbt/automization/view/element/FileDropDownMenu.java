package sbt.automization.view.element;

import sbt.automization.core.util.FileUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class FileDropDownMenu extends JComboBox implements MouseListener
{
	Font defaultFont = new Font("Gill Sans MT", Font.BOLD, 14);
	Color textColor = Color.decode("#000000");
	Color backgroundColor = Color.decode("#ffffff");
	Color hoverColor = Color.decode("#00aced");
	String[] pattern = {
			"Lade Dokument ...",
			"Chemie_Vorlage.xlsx",
			"Bericht-Erkundung-Straße.docx",
			"Bericht-Gebäude.docx",
			"Bericht-Straßenbau-Vorerkundung.docx",
			"Bericht-Straßenbau-Vorerkundung-(A3).docx",
			"Regelwerk-Straßenbau.docx"
	};
	
	public FileDropDownMenu(String[] strings, Rectangle position)
	{
		super(strings);
		this.setBorder(null);
		this.setForeground(textColor);
		this.setBackground(backgroundColor);
		this.setFont(defaultFont);
		this.setOpaque(true);
		this.setFocusable(false);
		this.setEditable(false);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBounds(position);
		addListener(this);
		addMouseListener(this);
	}
	
	private void addListener(JComboBox comboBox)
	{
		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Object selectedItem = comboBox.getSelectedItem();
				if (selectedItem instanceof String)
				{
					try
					{
						String item = (String) selectedItem;
						
						if ("".equals(item)) return;
						
						String itemPath = "/report-templates/".concat(item);
						
						JFileChooser explorer = new JFileChooser();
						explorer.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						explorer.setCurrentDirectory(new File(System.getProperty("user.dir")));
						
						int response = explorer.showOpenDialog(null); //select file to open
						
						if (response == JFileChooser.APPROVE_OPTION)
						{
							File file = new File(explorer.getSelectedFile().getAbsolutePath());
							
							String path = file.toString();
							//ViewConstant.pathComponent.setText(path);
							
							FileUtils.copyFileTo(itemPath, path);
						}
					} catch (IOException ioException)
					{
						ioException.printStackTrace();
					}
				}
			}
		});
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
