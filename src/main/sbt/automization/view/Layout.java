package sbt.automization.view;

import sbt.automization.view.element.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Layout
{
    Map<String, Rectangle> positions = new HashMap<>();
    List<JComponent> components = new ArrayList<>();

    public Layout()
    {
        setPositions();
        constructComponents();
    }

    public List<JComponent> getComponents() {
        return components;
    }

    private void setPositions()
    {
        //Excel Data Part
        positions.put("ButtonCreate", new Rectangle(10, 10, 160, 20));
        positions.put("ButtonLoad", new Rectangle(180, 10, 160, 20));
        positions.put("ButtonOpen", new Rectangle(350, 10, 160, 20));
        positions.put("ButtonHelp", new Rectangle(535, 10, 20, 20));
        positions.put("TextAreaPath", new Rectangle(10, 60, 565, 20));

        //Choose Data Set Part
        positions.put("LabelChooseDataSet", new Rectangle(10, 120, 100, 20));
        positions.put("CheckBoxAllData", new Rectangle(120, 120, 100, 20));
        positions.put("CheckBoxExplorationData", new Rectangle(230, 120, 100, 20));
        positions.put("CheckBoxHeapData", new Rectangle(340, 120, 100, 20));
        positions.put("CheckBoxBuildingData", new Rectangle(450, 120, 100, 20));

        //Choose Tables Part
        positions.put("LabelChooseTable", new Rectangle(10, 160, 265, 40));
        positions.put("CheckBoxFistColumnFirstRow", new Rectangle(10, 220, 265, 20));
        positions.put("CheckBoxFistColumnSecondRow", new Rectangle(10, 250, 265, 20));
        positions.put("CheckBoxFistColumnThirdRow",new Rectangle(10, 280, 265, 20));
        positions.put("CheckBoxFistColumnFourthRow", new Rectangle(10, 310, 265, 20));
        positions.put("CheckBoxFistColumnFifthRow", new Rectangle(10, 340, 265, 20));
        positions.put("CheckBoxFistColumnSixthRow", new Rectangle(10, 370, 265, 20));

        //Create Tables Part
        positions.put("ButtonBuildTable", new Rectangle(10, 410, 160, 20));
    }

    private void constructComponents()
    {
        // CREATE.
        JButton createButton = new CreateExcelButton("Create", positions.get("ButtonCreate"));
        components.add(createButton);
        // LOAD.
        JButton loadButton = new LoadExcelButton("Load", positions.get("ButtonLoad"));
        components.add(loadButton);
        // OPEN.
        JButton openButton = new OpenExcelButton("Open", positions.get("ButtonOpen"));
        components.add(openButton);
        //INFO.
        JButton infoButton = new InfoButton("", positions.get("ButtonHelp"));
        components.add(infoButton);

        TextInputArea textArea = new TextInputArea("", positions.get("TextAreaPath"));
        ViewParameter.pathComponent = textArea;
        components.add(textArea);

        constructCheckBoxDataSetArea();

        JLabel jLabel = new JLabel("3. Wähle Tabellen aus:", JLabel.LEFT);
        jLabel.setBounds(positions.get("LabelChooseTable"));
        jLabel.setForeground(Color.BLACK);
        jLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 10));
        components.add(jLabel);

        // TEMPLATE CHECKBOX
        constructCheckBoxTemplateArea();

        // BUTTONS.
        JButton buildTableButton = new BuildTableButton("Build", positions.get("ButtonBuildTable"));
        components.add(buildTableButton);

    }

    private void constructCheckBoxTemplateArea()
    {
        TemplateCheckBox templateCheckBox = new TemplateCheckBox("Anlage Erkundungsstelle", positions.get("CheckBoxFistColumnFirstRow"));
        components.add(templateCheckBox);
    }

    private void constructCheckBoxDataSetArea()
    {
        JLabel jLabel = new JLabel("Wähle einen Datensatz:", JLabel.LEFT);
        jLabel.setBounds(positions.get("LabelChooseDataSet"));
        jLabel.setForeground(Color.BLACK);
        jLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 10));
        components.add(jLabel);

        DataSetCheckBox dataSetAllCheckBox = new DataSetCheckBox("Gesamt", positions.get("CheckBoxAllData"));
        components.add(dataSetAllCheckBox);

        DataSetCheckBox dataSetExplorationCheckBox = new DataSetCheckBox("Erkundungsstellen", positions.get("CheckBoxExplorationData"));
        components.add(dataSetExplorationCheckBox);

        DataSetCheckBox dataSetHeapCheckBox = new DataSetCheckBox("Haufwerke", positions.get("CheckBoxHeapData"));
        components.add(dataSetHeapCheckBox);

        DataSetCheckBox dataSetBuildingCheckBox = new DataSetCheckBox("Gebäude", positions.get("CheckBoxBuildingData"));
        components.add(dataSetBuildingCheckBox);
    }
}
