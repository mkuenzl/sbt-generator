package sbt.automization.gui;

import sbt.automization.TableEngine;
import sbt.automization.export.HtmlTemplateExportStrategy;
import sbt.automization.templates.IHtmlTemplate;
import sbt.automization.util.Parser;
import sbt.automization.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener
{

	private JPanel panel;
	//    private final JMenu menu;
//    private final JMenuItem info;
//    private final JMenuBar menuBar;
	private JButton executeButton;
	private JButton explorerButton;
	private JButton infoButton;
	private JCheckBox checkBox1;
	private JCheckBox checkBox2;
	private JCheckBox checkBox3;
	private JCheckBox checkBox4;
	private JCheckBox checkBox5;
	private JCheckBox checkBox6;
	private JCheckBox checkBox7;
	private JCheckBox checkBox8;
	private JCheckBox checkBox9;
	private JCheckBox checkBox10;
	private JCheckBox checkBox11;
	//private final JLabel label;
	private JTextArea textfield;
	private JButton createCSVButton;

	public GUI()
	{

		ImageIcon img = new ImageIcon(getClass().getResource("/sbt-logo.jpg"));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(img.getImage());
		this.setTitle("SBT Berichterstellung");
		this.setLayout(null);
		this.setSize(300, 650);
		this.setResizable(false);
		buildPanel();
		//this.setJMenuBar(menuBar);
		this.setVisible(true);

	}

	private void buildPanel()
	{

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 300, 650);
		panel.setBackground(Color.gray);

//        menu = new JMenu("Menu");
//        info = new JMenuItem("Info");
//        info.addActionListener(this);
//        menu.add(info);
//
//        menuBar = new JMenuBar();
//        Border border = new LineBorder(Color.BLACK);
//        menuBar.setBorder(border);
//        menuBar.add(menu);

		// OPTIONEN FÜR CHECKBOXES
		checkBox1 = new JCheckBox("ERK_TABELLE", false);
		checkBox1.setBounds(10, 10, 265, 20);
		checkBox1.setEnabled(false);
		checkBox1.setVisible(false);
		checkBox1.addItemListener(new CheckBoxListener());
		panel.add(checkBox1);

		checkBox2 = new JCheckBox("LP_TABELLE", false);
		checkBox2.setBounds(10, 40, 265, 20);
		checkBox2.setEnabled(false);
		checkBox2.setVisible(false);
		checkBox2.addItemListener(new CheckBoxListener());
		panel.add(checkBox2);

		checkBox3 = new JCheckBox("PN_TABELLE", false);
		checkBox3.setBounds(10, 70, 265, 20);
		checkBox3.setEnabled(false);
		checkBox3.setVisible(false);
		checkBox3.addItemListener(new CheckBoxListener());
		panel.add(checkBox3);

		checkBox4 = new JCheckBox("RUK_TABELLE", false);
		checkBox4.setBounds(10, 100, 265, 20);
		checkBox4.setEnabled(false);
		checkBox4.setVisible(false);
		checkBox4.addItemListener(new CheckBoxListener());
		panel.add(checkBox4);

		checkBox5 = new JCheckBox("BERICHT_GOB", false);
		checkBox5.setBounds(10, 130, 265, 20);
		checkBox5.setEnabled(false);
		checkBox5.setVisible(false);
		checkBox5.addItemListener(new CheckBoxListener());
		panel.add(checkBox5);

		checkBox6 = new JCheckBox("BERICHT_TOB", false);
		checkBox6.setBounds(10, 160, 265, 20);
		checkBox6.setEnabled(false);
		checkBox6.setVisible(false);
		checkBox6.addItemListener(new CheckBoxListener());
		panel.add(checkBox6);

		checkBox7 = new JCheckBox("BERICHT_UG", false);
		checkBox7.setBounds(10, 190, 265, 20);
		checkBox7.setEnabled(false);
		checkBox7.setVisible(false);
		checkBox7.addItemListener(new CheckBoxListener());
		panel.add(checkBox7);

		checkBox8 = new JCheckBox("BERICHT_OH", false);
		checkBox8.setBounds(10, 220, 265, 20);
		checkBox8.setEnabled(false);
		checkBox8.setVisible(false);
		checkBox8.addItemListener(new CheckBoxListener());
		panel.add(checkBox8);

		checkBox9 = new JCheckBox("BERICHT_TMHB_TEST", false);
		checkBox9.setBounds(10, 250, 265, 20);
		checkBox9.setEnabled(false);
		checkBox9.setVisible(false);
		checkBox9.addItemListener(new CheckBoxListener());
		panel.add(checkBox9);

		checkBox10 = new JCheckBox("BERICHT_BETON_TEST", false);
		checkBox10.setBounds(10, 280, 265, 20);
		checkBox10.setEnabled(false);
		checkBox10.setVisible(false);
		checkBox10.addItemListener(new CheckBoxListener());
		panel.add(checkBox10);

		checkBox11 = new JCheckBox("BERICHT_FUGE_TEST", false);
		checkBox11.setBounds(10, 310, 265, 20);
		checkBox11.setEnabled(false);
		checkBox11.setVisible(false);
		checkBox11.addItemListener(new CheckBoxListener());
		panel.add(checkBox11);

		checkBox1.setVisible(true);
		checkBox2.setVisible(true);
		checkBox3.setVisible(true);
		checkBox4.setVisible(true);
		checkBox5.setVisible(true);
		checkBox6.setVisible(true);
		checkBox7.setVisible(true);
		checkBox8.setVisible(true);
		checkBox9.setVisible(true);
		checkBox10.setVisible(true);
		checkBox11.setVisible(true);


		checkBox1.setEnabled(true);
		checkBox2.setEnabled(true);
		checkBox3.setEnabled(true);
		checkBox4.setEnabled(true);
		checkBox5.setEnabled(true);
		checkBox6.setEnabled(true);
		checkBox7.setEnabled(true);
		checkBox8.setEnabled(true);
		checkBox9.setEnabled(true);
		checkBox10.setEnabled(true);
		checkBox11.setEnabled(true);

		// ICON DAS NACH AUSWAHL VON DATEI VERSCHWINDET
		// WARUM?

//        ImageIcon icon = new ImageIcon("resources/SBT.jpg");
//
//        label = new JLabel(icon);
//        label.setBounds(10,20,265,190);
//        label.setEnabled(true);
//        panel.add(label);

		textfield = new JTextArea();
		textfield.setBounds(10, 340, 265, 90);
		textfield.setEditable(false);
		textfield.setWrapStyleWord(true);
		textfield.setLineWrap(true);
		textfield.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel.add(textfield);

		explorerButton = new JButton("Datei auswählen");
		explorerButton.setBounds(10, 440, 265, 50);
		explorerButton.setFocusable(false);
		explorerButton.addActionListener(this);
		panel.add(explorerButton);


		// BUTTONS.
		executeButton = new JButton("Ausführen");
		executeButton.setBounds(10, 500, 265, 50);
		executeButton.setFocusable(false);
		executeButton.addActionListener(this);
		executeButton.setEnabled(true);
		panel.add(executeButton);

		// BUTTONS.
		createCSVButton = new JButton("Generiere Excel-Template");
		createCSVButton.setBounds(10, 560, 210, 30);
		createCSVButton.setFocusable(false);
		createCSVButton.addActionListener(this);
		createCSVButton.setEnabled(true);
		panel.add(createCSVButton);

		infoButton = new JButton("");
		ImageIcon infoIcon = new ImageIcon(getClass().getResource("/questionmark-icon.png"));
		Image scaledInstance = infoIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon infoIconScaled = new ImageIcon(scaledInstance);
		infoButton.setIcon(infoIconScaled);
		infoButton.setBounds(240, 560, 30, 30);
		infoButton.setFocusable(false);
		infoButton.setBackground(Color.GRAY);
		infoButton.setOpaque(true);
		infoButton.addActionListener(this);
		panel.add(infoButton);

		this.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == createCSVButton)
		{
			try
			{
				Util.exportExcelTemplate();
			} catch (IOException ioException)
			{
				ioException.printStackTrace();
			}
		}


		if (e.getSource() == infoButton)
		{
			JDialog dialog = new JDialog();
			dialog.setTitle("Impressum");
			dialog.setBounds(10, 10, 100, 100);

			JPanel dialogpanel = new JPanel();
			dialogpanel.setBounds(0, 0, 100, 100);

			JTextArea infotext = new JTextArea();
			infotext.setText("Firma: sbt" + "\n" + "Am Kenner Haus 13" + "\n" +
					"54344 Kenn");
			infotext.setEditable(false);
			infotext.setVisible(true);

			dialog.add(dialogpanel);

			dialogpanel.add(infotext);

			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}

		if (e.getSource() == explorerButton)
		{

			JFileChooser explorer = new JFileChooser();
			explorer.setCurrentDirectory(new File(System.getProperty("user.dir")));

			int response = explorer.showOpenDialog(null); //select file to open

			if (response == JFileChooser.APPROVE_OPTION)
			{
				File file = new File(explorer.getSelectedFile().getAbsolutePath());
				//System.out.println(file);

				String path = file.toString();
				textfield.setText(path);

				//label.setVisible(false);
			}
		}

		if (e.getSource() == executeButton)
		{
			File csv = new File(textfield.getText());
			Parser parser = new Parser(csv);

			TableEngine database = new TableEngine(parser.parse(), csv.getParent());

			for (IHtmlTemplate strategy : StrategyStorage.getInstance().getStrategies())
			{
				database.export(new HtmlTemplateExportStrategy(strategy));
			}
		}
	}
}



