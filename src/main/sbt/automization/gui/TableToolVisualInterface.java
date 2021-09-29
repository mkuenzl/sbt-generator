package sbt.automization.gui;

import sbt.automization.gui.listener.*;

import javax.swing.*;
import java.awt.*;


public class TableToolVisualInterface extends JFrame
{
	public static JTextArea textField;

	public static void main(String[] args) {
		new TableToolVisualInterface();
	}

	public TableToolVisualInterface()
	{
		super("SBT Report Construction Tool");

		setLookAndFeel();

		ImageIcon img = new ImageIcon(getClass().getResource("/sbt-logo.jpg"));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(img.getImage());
		this.setLayout(null);
		this.setSize(600, 580);
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
			UIManager.setLookAndFeel(
					design
			                        );
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
		panel.setBackground(Color.gray);

		// BUTTONS.
		JButton createCSVButton = new CustomButton("Generiere Excel-Template");
		createCSVButton.setBounds(10, 10, 265, 40);
		createCSVButton.addActionListener(new CsvButtonActionListener());
		panel.add(createCSVButton);

		//Convert to JPane with Mouselistener
		ImageIcon infoIcon = new ImageIcon(getClass().getResource("/questionmark-icon.png"));
		Image scaledInstance = infoIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon infoIconScaled = new ImageIcon(scaledInstance);
		JButton infoButton = new CustomButton("");
		infoButton.setIcon(infoIconScaled);
		infoButton.setBounds(535, 10, 40, 40);
		infoButton.addActionListener(new InfoButtonActionListener());
		panel.add(infoButton);

		JButton explorerButton = new CustomButton("Auswahl .CSV Datei");
		explorerButton.setBounds(10, 60, 265, 40);
		explorerButton.addActionListener(new ExplorerButtonActionListener());
		panel.add(explorerButton);

		// BUTTONS.
		String[] pattern = {
				"Lade Dokument ...",
				"Chemie_Vorlage.xlsx",
				"Bericht-Erkundung-Straße.docx",
				"Bericht-Straßenbau-Vorerkundung.docx",
				"Bericht-Straßenbau-Vorerkundung-(A3).docx",
				"Regelwerk-Straßenbau.docx"
		};

		JComboBox fileDropDown = new FileDropDownMenu(pattern);
		fileDropDown.setBounds(310, 60, 265, 40);
		fileDropDown.addActionListener(new FileDropDownActionListener(fileDropDown));
		panel.add(fileDropDown);

		textField = new JTextArea();
		textField.setBounds(10, 110, 565, 40);
		textField.setEditable(false);
		textField.setWrapStyleWord(true);
		textField.setLineWrap(true);
		textField.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		panel.add(textField);

		JLabel jLabel = new JLabel("Auswahl der Tabellen:", JLabel.LEFT);
		jLabel.setBounds(10, 160, 265, 40);
		jLabel.setForeground(Color.BLACK);
		jLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		panel.add(jLabel);

		// OPTIONEN FÜR CHECKBOXES
		JCheckBox checkBox1 = new CustomCheckBox("ANLAGE_ERK", false);
		checkBox1.setBounds(10, 210, 265, 20);
		checkBox1.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox1);

		JCheckBox checkBox2 = new CustomCheckBox("ANLAGE_LP", false);
		checkBox2.setBounds(10, 240, 265, 20);
		checkBox2.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox2);

		JCheckBox checkBox3 = new CustomCheckBox("ANLAGE_RUK", false);
		checkBox3.setBounds(10, 270, 265, 20);
		checkBox3.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox3);

		JCheckBox checkBox4 = new CustomCheckBox("ANLAGE_PN", false);
		checkBox4.setBounds(10, 300, 265, 20);
		checkBox4.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox4);

		JCheckBox checkBoxAppendixPNHEAP = new CustomCheckBox("ANLAGE_PN_HAUFWERK", false);
		checkBoxAppendixPNHEAP.setBounds(10, 330, 265, 20);
		checkBoxAppendixPNHEAP.addItemListener(new CheckBoxItemListener());
		panel.add(checkBoxAppendixPNHEAP);

		JCheckBox checkBoxAppendixPNBUILDING = new CustomCheckBox("ANLAGE_PN_GEBÄUDE", false);
		checkBoxAppendixPNBUILDING.setBounds(10, 360, 265, 20);
		checkBoxAppendixPNBUILDING.addItemListener(new CheckBoxItemListener());
		panel.add(checkBoxAppendixPNBUILDING);

		JCheckBox checkBox5 = new CustomCheckBox("BERICHT_GOB", false);
		checkBox5.setBounds(310, 210, 265, 20);
		checkBox5.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox5);

		JCheckBox checkBox6 = new CustomCheckBox("BERICHT_TOB", false);
		checkBox6.setBounds(310, 240, 265, 20);
		checkBox6.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox6);

		JCheckBox checkBox7 = new CustomCheckBox("BERICHT_UG", false);
		checkBox7.setBounds(310, 270, 265, 20);
		checkBox7.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox7);

		JCheckBox checkBox8 = new CustomCheckBox("BERICHT_OH", false);
		checkBox8.setBounds(310, 300, 265, 20);
		checkBox8.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox8);

		JCheckBox checkBox9 = new CustomCheckBox("BERICHT_TMHB", false);
		checkBox9.setBounds(310, 330, 265, 20);
		checkBox9.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox9);

		JCheckBox checkBox10 = new CustomCheckBox("BERICHT_BETON", false);
		checkBox10.setBounds(310, 360, 265, 20);
		checkBox10.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox10);

		JCheckBox checkBox11 = new CustomCheckBox("BERICHT_FUGE", false);
		checkBox11.setBounds(310, 390, 265, 20);
		checkBox11.addItemListener(new CheckBoxItemListener());
		panel.add(checkBox11);

		JCheckBox checkBoxReportHeap = new CustomCheckBox("BERICHT_HAUFWERK", false);
		checkBoxReportHeap.setBounds(310, 420, 265, 20);
		checkBoxReportHeap.addItemListener(new CheckBoxItemListener());
		panel.add(checkBoxReportHeap);

		JCheckBox checkBoxReportCoordinates = new CustomCheckBox("BERICHT_KOORDINATEN", false);
		checkBoxReportCoordinates.setBounds(310, 450, 265, 20);
		checkBoxReportCoordinates.addItemListener(new CheckBoxItemListener());
		checkBoxReportCoordinates.setEnabled(true);
		panel.add(checkBoxReportCoordinates);

		JCheckBox checkBoxReportBankett = new CustomCheckBox("BERICHT_BANKETT", false);
		checkBoxReportBankett.setBounds(310, 480, 265, 20);
		checkBoxReportBankett.addItemListener(new CheckBoxItemListener());
		checkBoxReportBankett.setEnabled(true);
		panel.add(checkBoxReportBankett);

		JCheckBox checkBoxReportBuilding = new CustomCheckBox("BERICHT_GEBÄUDE", false);
		checkBoxReportBuilding.setBounds(310, 510, 265, 20);
		checkBoxReportBuilding.addItemListener(new CheckBoxItemListener());
		checkBoxReportBuilding.setEnabled(true);
		panel.add(checkBoxReportBuilding);

		// BUTTONS.
		JButton executeButton = new CustomButton("Erstelle Tabellen");
		executeButton.setBounds(10, 420, 265, 40);
		executeButton.setFocusable(false);
		executeButton.addActionListener(new ExecuteButtonActionListener());
		executeButton.setEnabled(true);
		panel.add(executeButton);

		this.add(panel);
	}
}



