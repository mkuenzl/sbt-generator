package sbt.automization.view;

import sbt.automization.core.templates.appendix.*;
import sbt.automization.core.templates.basic.Coordinates;
import sbt.automization.core.templates.report.*;
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
        positions.put("ButtonHelp", new Rectangle(540, 10, 20, 20));
        positions.put("TextAreaPath", new Rectangle(10, 40, 500, 20));

        //Choose Data Set Part
        positions.put("LabelChooseDataSet", new Rectangle(10, 90, 200, 20));
        positions.put("CheckBoxAllData", new Rectangle(10, 120, 120, 20));
        positions.put("CheckBoxExplorationData", new Rectangle(140, 120, 120, 20));
        positions.put("CheckBoxHeapData", new Rectangle(270, 120, 120, 20));
        positions.put("CheckBoxBuildingData", new Rectangle(400, 120, 120, 20));

        //Choose Tables Part
        positions.put("LabelChooseTable", new Rectangle(10, 160, 265, 40));
        positions.put("CheckBoxFirstColumnFirstRow", new Rectangle(10, 200, 180, 20));
        positions.put("CheckBoxFirstColumnSecondRow", new Rectangle(10, 220, 180, 20));
        positions.put("CheckBoxFirstColumnThirdRow",new Rectangle(10, 240, 180, 20));
        positions.put("CheckBoxFirstColumnFourthRow", new Rectangle(10, 260, 180, 20));
        positions.put("CheckBoxFirstColumnFifthRow", new Rectangle(10, 280, 180, 20));
        positions.put("CheckBoxFirstColumnSixthRow", new Rectangle(10, 300, 180, 20));

        positions.put("CheckBoxSecondColumnFirstRow", new Rectangle(200, 200, 180, 20));
        positions.put("CheckBoxSecondColumnSecondRow", new Rectangle(200, 220, 180, 20));
        positions.put("CheckBoxSecondColumnThirdRow",new Rectangle(200, 240, 180, 20));
        positions.put("CheckBoxSecondColumnFourthRow", new Rectangle(200, 260, 180, 20));
        positions.put("CheckBoxSecondColumnFifthRow", new Rectangle(200, 280, 180, 20));
        positions.put("CheckBoxSecondColumnSixthRow", new Rectangle(200, 300, 180, 20));
        positions.put("CheckBoxSecondColumnSeventhRow", new Rectangle(200, 320, 180, 20));
        positions.put("CheckBoxSecondColumnEighthRow", new Rectangle(200, 340, 180, 20));
        positions.put("CheckBoxSecondColumnNinthRow", new Rectangle(200, 360, 180, 20));
        positions.put("CheckBoxSecondColumnTenthRow", new Rectangle(200, 380, 180, 20));

        positions.put("CheckBoxThirdColumnFirstRow", new Rectangle(390, 200, 180, 20));


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
        ViewConstant.pathComponent = textArea;
        components.add(textArea);

        constructCheckBoxDataSetArea();

        JLabel jLabel = new JLabel("Wählen Sie Tabellen", JLabel.LEFT);
        jLabel.setBounds(positions.get("LabelChooseTable"));
        jLabel.setForeground(Color.BLACK);
        jLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
        components.add(jLabel);

        // TEMPLATE CHECKBOX
        constructCheckBoxTemplateArea();

        // BUTTONS.
        JButton buildTableButton = new BuildTableButton("Build", positions.get("ButtonBuildTable"));
        components.add(buildTableButton);

    }

    private void constructCheckBoxTemplateArea()
    {
        //APPENDIX
        TemplateCheckBox explorationSiteCheckBox = new TemplateCheckBox("Erkundungsstellen", positions.get("CheckBoxFirstColumnFirstRow"), ExplorationSite.getInstance());
        components.add(explorationSiteCheckBox);

        TemplateCheckBox lpCheckBox = new TemplateCheckBox("Lastplatten", positions.get("CheckBoxFirstColumnSecondRow"), LoadPlate.getInstance());
        components.add(lpCheckBox);

        TemplateCheckBox rukCheckBox = new TemplateCheckBox("Ring und Kugel", positions.get("CheckBoxFirstColumnThirdRow"), RingAndBall.getInstance());
        components.add(rukCheckBox);

        TemplateCheckBox pnCheckBox = new TemplateCheckBox("Probenahme", positions.get("CheckBoxFirstColumnFourthRow"), SamplingProtocol.getInstance());
        components.add(pnCheckBox);

        TemplateCheckBox pnHeapCheckBox = new TemplateCheckBox("Probenahme Haufwerk", positions.get("CheckBoxFirstColumnFifthRow"), SamplingProtocolHeap.getInstance());
        components.add(pnHeapCheckBox);

        TemplateCheckBox pnBuildingCheckBox = new TemplateCheckBox("Probenahme Gebäude", positions.get("CheckBoxFirstColumnSixthRow"), SamplingProtocolBuilding.getInstance());
        components.add(pnBuildingCheckBox);

        //REPORT
        TemplateCheckBox topsoilCheckBox = new TemplateCheckBox("OBERBODEN", positions.get("CheckBoxSecondColumnFirstRow"), Topsoil.getInstance());
        components.add(topsoilCheckBox);

        TemplateCheckBox banquetCheckBox = new TemplateCheckBox("BANKETT", positions.get("CheckBoxSecondColumnSecondRow"), Banquet.getInstance());
        components.add(banquetCheckBox);

        TemplateCheckBox concreteCheckBox = new TemplateCheckBox("BETON", positions.get("CheckBoxSecondColumnThirdRow"), Concrete.getInstance());
        components.add(concreteCheckBox);

        TemplateCheckBox boundSuperStructureCheckBox = new TemplateCheckBox("GOB", positions.get("CheckBoxSecondColumnFourthRow"), BoundSuperstructure.getInstance());
        components.add(boundSuperStructureCheckBox);

        TemplateCheckBox baseCourseWithoutBinderCheckBox = new TemplateCheckBox("TOB", positions.get("CheckBoxSecondColumnFifthRow"), BaseCourseWithoutBinder.getInstance());
        components.add(baseCourseWithoutBinderCheckBox);

        TemplateCheckBox baseCourseWithHydraulicBinderCheckBox = new TemplateCheckBox("TMHB", positions.get("CheckBoxSecondColumnSixthRow"), BaseCourseWithHydraulicBinder.getInstance());
        components.add(baseCourseWithHydraulicBinderCheckBox);

        TemplateCheckBox gapCheckBox = new TemplateCheckBox("FUGE", positions.get("CheckBoxSecondColumnSeventhRow"), Gap.getInstance());
        components.add(gapCheckBox);

        TemplateCheckBox undergroundCheckBox = new TemplateCheckBox("UNTERGRUND", positions.get("CheckBoxSecondColumnEighthRow"), Underground.getInstance());
        components.add(undergroundCheckBox);

        TemplateCheckBox buildingCheckBox = new TemplateCheckBox("GEBÄUDE", positions.get("CheckBoxSecondColumnNinthRow"), Building.getInstance());
        components.add(buildingCheckBox);

        TemplateCheckBox heapCheckBox = new TemplateCheckBox("HAUFWERK", positions.get("CheckBoxSecondColumnTenthRow"), Heap.getInstance());
        components.add(heapCheckBox);

        //Other
        TemplateCheckBox coordinatesCheckBox = new TemplateCheckBox("KOORDINATEN", positions.get("CheckBoxThirdColumnFirstRow"), Coordinates.getInstance());
        components.add(coordinatesCheckBox);

    }

    private void constructCheckBoxDataSetArea()
    {
        JLabel jLabel = new JLabel("Wählen Sie einen Datensatz", JLabel.LEFT);
        jLabel.setBounds(positions.get("LabelChooseDataSet"));
        jLabel.setForeground(Color.BLACK);
        jLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
        components.add(jLabel);

        DataSetCheckBox dataSetAllCheckBox = new DataSetCheckBox("Gesamt", positions.get("CheckBoxAllData"));
        dataSetAllCheckBox.setDataSet("Daten GESAMT");
        components.add(dataSetAllCheckBox);

        DataSetCheckBox dataSetExplorationCheckBox = new DataSetCheckBox("Erkundung", positions.get("CheckBoxExplorationData"));
        dataSetExplorationCheckBox.setDataSet("Daten ERK");
        components.add(dataSetExplorationCheckBox);

        DataSetCheckBox dataSetHeapCheckBox = new DataSetCheckBox("Haufwerke", positions.get("CheckBoxHeapData"));
        dataSetHeapCheckBox.setDataSet("Daten HAUF");
        components.add(dataSetHeapCheckBox);

        DataSetCheckBox dataSetBuildingCheckBox = new DataSetCheckBox("Gebäude", positions.get("CheckBoxBuildingData"));
        dataSetBuildingCheckBox.setDataSet("Daten GEBÄUDE");
        components.add(dataSetBuildingCheckBox);

        dataSetAllCheckBox.setRelatedCheckBoxes(dataSetExplorationCheckBox, dataSetHeapCheckBox, dataSetBuildingCheckBox);
        dataSetExplorationCheckBox.setRelatedCheckBoxes(dataSetAllCheckBox, dataSetHeapCheckBox, dataSetBuildingCheckBox);
        dataSetHeapCheckBox.setRelatedCheckBoxes(dataSetExplorationCheckBox, dataSetAllCheckBox, dataSetBuildingCheckBox);
        dataSetBuildingCheckBox.setRelatedCheckBoxes(dataSetExplorationCheckBox, dataSetHeapCheckBox, dataSetAllCheckBox);
    }

}
