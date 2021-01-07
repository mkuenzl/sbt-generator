package sbt.automization.gui;

import sbt.automization.export.HtmlTemplateExportStrategy;
import sbt.automization.engine.TableEngine;
import sbt.automization.templates.IHtmlTemplateStrategy;
import sbt.automization.util.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {

    private JPanel panel;
//    private final JMenu menu;
//    private final JMenuItem info;
//    private final JMenuBar menuBar;
    private JButton executeButton;
    private JButton explorerButton;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;
    //private final JLabel label;
    private JTextArea textfield;

    public GUI() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SBT Berichterstellung");
        this.setLayout(null);
        this.setSize(300, 440);
        this.setResizable(false);
        buildPanel();
        //this.setJMenuBar(menuBar);
        this.setVisible(true);

    }

    private void buildPanel(){

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, 400);
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

        // BUTTONS.
        executeButton = new JButton("Ausführen");
        executeButton.setBounds(10, 320, 265, 50);
        executeButton.setFocusable(false);
        executeButton.addActionListener(this);
        executeButton.setEnabled(true);
        panel.add(executeButton);

        explorerButton = new JButton("Datei auswählen");
        explorerButton.setBounds(10, 260, 265, 50);
        explorerButton.setFocusable(false);
        explorerButton.addActionListener(this);
        panel.add(explorerButton);


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

        checkBox5 = new JCheckBox("BERICHT_TABELLEN", false);
        checkBox5.setBounds(10, 130, 265, 20);
        checkBox5.setEnabled(false);
        checkBox5.setVisible(false);
        checkBox5.addItemListener(new CheckBoxListener());
        panel.add(checkBox5);

        checkBox1.setVisible(true);
        checkBox2.setVisible(true);
        checkBox3.setVisible(true);
        checkBox4.setVisible(true);
        checkBox5.setVisible(true);
        checkBox1.setEnabled(true);
        checkBox2.setEnabled(true);
        checkBox3.setEnabled(true);
        checkBox4.setEnabled(true);
        checkBox5.setEnabled(true);

        // ICON DAS NACH AUSWAHL VON DATEI VERSCHWINDET
        // WARUM?

//        ImageIcon icon = new ImageIcon("resources/SBT.jpg");
//
//        label = new JLabel(icon);
//        label.setBounds(10,20,265,190);
//        label.setEnabled(true);
//        panel.add(label);

        textfield = new JTextArea();
        textfield.setBounds(10, 160, 265, 90);
        textfield.setEditable(false);
        textfield.setWrapStyleWord(true);
        textfield.setLineWrap(true);
        textfield.setFont(new Font("Verdana",Font.PLAIN,10));

        panel.add(textfield);

        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//        if (e.getSource()==info) {
//            JDialog dialog = new JDialog();
//            dialog.setTitle("Impressum");
//            dialog.setBounds(10,10,200,200);
//
//            JPanel dialogpanel = new JPanel();
//            dialogpanel.setBounds(0,0,200,200);
//
//            JTextArea infotext = new JTextArea();
//            infotext.setText("Programmierung: Moritz Künzl" + "\n" + "GZUI: Vincent Görlich" + "\n" + "Firma: sbt"+ "\n" + "Am Kenner Haus 13" + "\n" +
//                    "54344 Kenn");
//            infotext.setEditable(false);
//            infotext.setVisible(true);
//
//            dialog.add(dialogpanel);
//
//            dialogpanel.add(infotext);
//
//            dialog.setVisible(true);
//            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        }

        if (e.getSource() == explorerButton) {

            JFileChooser explorer = new JFileChooser();
            explorer.setCurrentDirectory(new File(System.getProperty("user.dir")));

            int response = explorer.showOpenDialog(null); //select file to open

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(explorer.getSelectedFile().getAbsolutePath());
                //System.out.println(file);

                String path = file.toString();
                textfield.setText(path);

                //label.setVisible(false);
            }
        }

        if(e.getSource()==executeButton){

//            JFileChooser saver = new JFileChooser();
//            int userSelection = saver.showSaveDialog(null);
//            saver.setCurrentDirectory(new File("."));
//
//            if (userSelection == JFileChooser.APPROVE_OPTION) {
//                File file = new File(saver.getSelectedFile().getAbsolutePath());
//                // WAS ER SPEICHERN SOLL
//                System.out.println(file);
//            }

            Parser parser = new Parser(new File(textfield.getText()));

            try
            {
                TableEngine database = new TableEngine(parser.parse());

                for (IHtmlTemplateStrategy strategy : StrategyStorage.getInstance().getStrategies())
                {
                    database.export(new HtmlTemplateExportStrategy(strategy));
                }
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        }
    }
}



