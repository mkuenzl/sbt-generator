package sbt.automization.view;

import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame
{
	public GUI()
	{
		super("SBT Report Construction Tool");
		
		setLookAndFeel();
		
		ImageIcon img = new ImageIcon(getClass().getResource("/icons/sbt-logo.jpg"));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(img.getImage());
		this.setLayout(null);
		this.setSize(540, 490);
		this.setResizable(false);
		constructPanel();
		//this.setJMenuBar(menuBar);
		this.setVisible(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		// calculate the new location of the window
		int w = this.getSize().width;
		int h = this.getSize().height;
		
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		
		this.setLocation(x, y);
	}
	
	/**
	 * Set design and color of frame. https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	 */
	private static void setLookAndFeel()
	{
		String design = UIManager.getSystemLookAndFeelClassName();
		try
		{
			UIManager.setLookAndFeel(design);
		} catch (Exception e)
		{
			//e.printStackTrace();
		}
	}
	
	private void constructPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 580);
		panel.setBackground(Color.white);
		
		Layout layout = new Layout();
		for (JComponent component : layout.getComponents())
		{
			panel.add(component);
		}
		
		this.add(panel);
	}
	
	public static void main(String[] args)
	{
		new GUI();
	}
}



