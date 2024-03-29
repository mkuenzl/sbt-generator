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
	
	private void setPositions()
	{
		//Create Documents Part
		positions.put("LabelChooseDropdown", new Rectangle(10, 0, 500, 20));
		positions.put("DropdownFiles", new Rectangle(10, 30, 330, 20));
		
		//Excel Data Part
		positions.put("LabelChooseFile", new Rectangle(10, 60, 400, 20));
		positions.put("ButtonCreate", new Rectangle(10, 90, 160, 20));
		positions.put("ButtonLoad", new Rectangle(180, 90, 160, 20));
		positions.put("ButtonOpen", new Rectangle(350, 90, 160, 20));
		positions.put("TextAreaPath", new Rectangle(10, 120, 500, 20));
		
		//Choose Data Set Part
		positions.put("LabelChooseDataSet", new Rectangle(10, 150, 400, 20));
		positions.put("CheckBoxAllData", new Rectangle(10, 180, 120, 20));
		positions.put("CheckBoxExplorationData", new Rectangle(140, 180, 120, 20));
		positions.put("CheckBoxHeapData", new Rectangle(270, 180, 120, 20));
		positions.put("CheckBoxBuildingData", new Rectangle(400, 180, 120, 20));
		
		//Choose Tables Part
		positions.put("LabelChooseTable", new Rectangle(10, 210, 400, 20));
		positions.put("CheckBoxFirstColumnFirstRow", new Rectangle(10, 240, 180, 20));
		positions.put("CheckBoxFirstColumnSecondRow", new Rectangle(10, 260, 180, 20));
		positions.put("CheckBoxFirstColumnThirdRow", new Rectangle(10, 280, 180, 20));
		positions.put("CheckBoxFirstColumnFourthRow", new Rectangle(10, 300, 180, 20));
		positions.put("CheckBoxFirstColumnFifthRow", new Rectangle(10, 320, 180, 20));
		positions.put("CheckBoxFirstColumnSixthRow", new Rectangle(10, 340, 180, 20));
		positions.put("CheckBoxFirstColumnSeventhRow", new Rectangle(10, 360, 180, 20));
		
		positions.put("CheckBoxSecondColumnFirstRow", new Rectangle(200, 240, 180, 20));
		positions.put("CheckBoxSecondColumnSecondRow", new Rectangle(200, 260, 180, 20));
		positions.put("CheckBoxSecondColumnThirdRow", new Rectangle(200, 280, 180, 20));
		positions.put("CheckBoxSecondColumnFourthRow", new Rectangle(200, 300, 180, 20));
		positions.put("CheckBoxSecondColumnFifthRow", new Rectangle(200, 320, 180, 20));
		positions.put("CheckBoxSecondColumnSixthRow", new Rectangle(200, 340, 180, 20));
		positions.put("CheckBoxSecondColumnSeventhRow", new Rectangle(200, 360, 180, 20));
		positions.put("CheckBoxSecondColumnEighthRow", new Rectangle(200, 380, 180, 20));
		positions.put("CheckBoxSecondColumnNinthRow", new Rectangle(200, 400, 180, 20));
		positions.put("CheckBoxSecondColumnTenthRow", new Rectangle(200, 420, 180, 20));
		positions.put("CheckBoxSecondColumnEleventhRow", new Rectangle(200, 440, 180, 20));
		
		positions.put("CheckBoxThirdColumnFirstRow", new Rectangle(390, 240, 180, 20));
		
		
		//Create Tables Part
		positions.put("ButtonReport", new Rectangle(150, 470, 20, 20));
		positions.put("ButtonBuildTable", new Rectangle(180, 470, 160, 20));
		positions.put("ButtonHelp", new Rectangle(350, 470, 20, 20));
	}
	
	private void constructComponents()
	{
		JLabel labelChooseFile = new JLabel("1. Wählen Sie eine Excel oder CSV Datei", JLabel.LEFT);
		labelChooseFile.setBounds(positions.get("LabelChooseFile"));
		labelChooseFile.setForeground(Color.BLACK);
		labelChooseFile.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		components.add(labelChooseFile);
		
		// CREATE.
		JButton createButton = new CreateExcelButton("Create", positions.get("ButtonCreate"));
		components.add(createButton);
		// LOAD.
		JButton loadButton = new LoadExcelButton("Load", positions.get("ButtonLoad"));
		components.add(loadButton);
		// OPEN.
		JButton openButton = new OpenExcelButton("Open", positions.get("ButtonOpen"));
		components.add(openButton);
		
		TextInputArea textArea = new TextInputArea("", positions.get("TextAreaPath"));
		ViewConstant.pathComponent = textArea;
		components.add(textArea);
		
		JLabel labelChooseData = new JLabel("2. Wählen Sie einen Datensatz", JLabel.LEFT);
		labelChooseData.setBounds(positions.get("LabelChooseDataSet"));
		labelChooseData.setForeground(Color.BLACK);
		labelChooseData.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		components.add(labelChooseData);
		
		constructCheckBoxDataSetArea();
		
		JLabel labelChooseTable = new JLabel("3. Wählen Sie Tabellen", JLabel.LEFT);
		labelChooseTable.setBounds(positions.get("LabelChooseTable"));
		labelChooseTable.setForeground(Color.BLACK);
		labelChooseTable.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		components.add(labelChooseTable);
		
		// TEMPLATE CHECKBOX
		constructCheckBoxTemplateArea();
		
		//REPORT.
		JButton reportButton = new ReportButton("", positions.get("ButtonReport"));
		components.add(reportButton);
		
		// BUTTONS.
		JButton buildTableButton = new BuildTableButton("Build", positions.get("ButtonBuildTable"));
		components.add(buildTableButton);
		
		//INFO.
		JButton infoButton = new InfoButton("", positions.get("ButtonHelp"));
		components.add(infoButton);
		
		//FileDropDowns
		JLabel labelChooseFileDropDown = new JLabel("0. Auswahl zusätzlicher Dokumente", JLabel.LEFT);
		labelChooseFileDropDown.setBounds(positions.get("LabelChooseDropdown"));
		labelChooseFileDropDown.setForeground(Color.BLACK);
		labelChooseFileDropDown.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		components.add(labelChooseFileDropDown);
		
		JComboBox dropdownMenu = new FileDropDownMenu(new String[]{
				"Chemie_Vorlage.xlsx",
				"Bericht-Erkundung-Straße.docx",
				"Bericht-Gebäude.docx",
				"Bericht-Straßenbau-Vorerkundung.docx",
				"Bericht-Straßenbau-Vorerkundung-(A3).docx",
				"Regelwerk-Straßenbau.docx"
		}, positions.get("DropdownFiles"));
		components.add(dropdownMenu);
	}
	
	private void constructCheckBoxTemplateArea()
	{
		//APPENDIX
		TemplateCheckBox explorationSiteCheckBox = new TemplateCheckBox("Erkundungsstellen", positions.get("CheckBoxFirstColumnFirstRow"), ExplorationSite.getInstance());
		components.add(explorationSiteCheckBox);
		
		TemplateCheckBox explorationSite08CheckBox = new TemplateCheckBox("Erkundungsstellen 08.23", positions.get(
				"CheckBoxFirstColumnSecondRow"), ExplorationSite08.getInstance());
		components.add(explorationSite08CheckBox);
		
		TemplateCheckBox lpCheckBox = new TemplateCheckBox("Lastplatten", positions.get("CheckBoxFirstColumnThirdRow"), LoadPlate.getInstance());
		components.add(lpCheckBox);
		
		TemplateCheckBox rukCheckBox = new TemplateCheckBox("Ring und Kugel", positions.get("CheckBoxFirstColumnFourthRow"), RingAndBall.getInstance());
		components.add(rukCheckBox);
		
		TemplateCheckBox pnCheckBox = new TemplateCheckBox("Probenahme", positions.get("CheckBoxFirstColumnFifthRow"), SamplingProtocol.getInstance());
		components.add(pnCheckBox);
		
		TemplateCheckBox pnHeapCheckBox = new TemplateCheckBox("Probenahme Haufwerk", positions.get("CheckBoxFirstColumnSixthRow"), SamplingProtocolHeap.getInstance());
		components.add(pnHeapCheckBox);
		
		TemplateCheckBox pnBuildingCheckBox = new TemplateCheckBox("Probenahme Gebäude", positions.get(
				"CheckBoxFirstColumnSeventhRow"), SamplingProtocolBuilding.getInstance());
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
		
		TemplateCheckBox boundSuperStructure08CheckBox = new TemplateCheckBox("GOB 08.23", positions.get(
				"CheckBoxSecondColumnEleventhRow"), BoundSuperstructure08.getInstance());
		components.add(boundSuperStructure08CheckBox);
		
		
		//Other
		TemplateCheckBox coordinatesCheckBox = new TemplateCheckBox("KOORDINATEN", positions.get("CheckBoxThirdColumnFirstRow"), Coordinates.getInstance());
		components.add(coordinatesCheckBox);
		
	}
	
	private void constructCheckBoxDataSetArea()
	{
		DataSetCheckBox dataSetAllCheckBox = new DataSetCheckBox("Gesamt", positions.get("CheckBoxAllData"));
		dataSetAllCheckBox.setDataSet("Daten");
		components.add(dataSetAllCheckBox);
		
		DataSetCheckBox dataSetExplorationCheckBox = new DataSetCheckBox("Erkundung", positions.get("CheckBoxExplorationData"));
		dataSetExplorationCheckBox.setDataSet("DatenErkundungsstelle");
		components.add(dataSetExplorationCheckBox);
		
		DataSetCheckBox dataSetHeapCheckBox = new DataSetCheckBox("Haufwerke", positions.get("CheckBoxHeapData"));
		dataSetHeapCheckBox.setDataSet("DatenHaufwerk");
		components.add(dataSetHeapCheckBox);
		
		DataSetCheckBox dataSetBuildingCheckBox = new DataSetCheckBox("Gebäude", positions.get("CheckBoxBuildingData"));
		dataSetBuildingCheckBox.setDataSet("DatenGebaeude");
		components.add(dataSetBuildingCheckBox);
		
		dataSetAllCheckBox.setRelatedCheckBoxes(dataSetExplorationCheckBox, dataSetHeapCheckBox, dataSetBuildingCheckBox);
		dataSetExplorationCheckBox.setRelatedCheckBoxes(dataSetAllCheckBox, dataSetHeapCheckBox, dataSetBuildingCheckBox);
		dataSetHeapCheckBox.setRelatedCheckBoxes(dataSetExplorationCheckBox, dataSetAllCheckBox, dataSetBuildingCheckBox);
		dataSetBuildingCheckBox.setRelatedCheckBoxes(dataSetExplorationCheckBox, dataSetHeapCheckBox, dataSetAllCheckBox);
	}
	
	public List<JComponent> getComponents()
	{
		return components;
	}
	
}
