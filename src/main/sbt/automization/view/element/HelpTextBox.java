package sbt.automization.view.element;

import javax.swing.*;
import java.awt.*;

public class HelpTextBox
{
	public HelpTextBox()
	{
	}
	
	public void draw()
	{
		JDialog dialog = new JDialog();
		dialog.setTitle("Help");
		dialog.setSize(1000, 600);
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setSize(1000, 600);
		dialogPanel.setBackground(Color.WHITE);
		
		JTextArea infoText = new JTextArea();
		infoText.setBounds(10, 10, 980, 580);
		infoText.setEditable(false);
		infoText.setWrapStyleWord(true);
		infoText.setLineWrap(true);
		infoText.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		infoText.setText(getText());
		infoText.setVisible(true);
		
		dialogPanel.add(infoText);
		
		dialog.add(dialogPanel);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		// calculate the new location of the window
		int w = dialog.getSize().width;
		int h = dialog.getSize().height;
		
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		
		dialog.setLocation(x, y);
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private String getText()
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("1. Button \"CREATE\" erstellt ein Excel Template.")
				.append("\n    Das Template erscheint im gleichen Verzeichnis/Ordner wie diese Anwendung.")
				.append("\n    Button \"LOAD\" lädt ein Template oder eine CSV")
				.append("\n    Button \"OPEN\" öffnet die ausgewählte Datei")
				.append("\n    Unter den Buttons befindet sich der Pfad der ausgewählten Datei.")
				.append("\n")
				.append("\n2. Öffnen Sie das ExcelTemplate und erstellen einen Datensatz.")
				.append("\n    Zu beachten:")
				.append("\n    Tragen Sie zuerst in den weißen Sheets die entsprechenden Untersuchungsergebnisse zu Chemie, RuK und Lp ein.")
				.append("\n    Anschließend tragen Sie in ihrem entsprechenden Bereich (gelb) Straße, (blau) Haufwerk, (lila) Gebäude die entsprechenden Daten ein.")
				.append("\n    Beginnen Sie hierbei mit den Erkundungsstellen und fahren sie anschließend mit den Proben fort.")
				.append("\n    Sie können Rückwirkend jederzeit Änderungen vornehmen.")
				.append("\n")
				.append("\n    Bedienung in Excel:")
				.append("\n    Wenn Sie eine neue Zeile ausfüllen, tragen Sie den ersten Wert ein und drücken entweder TAB oder Enter.")
				.append("\n    Jedes Dropdown Menu kann entweder mit der Maus oder mit \"ALT + Pfeiltaste nach unten\" geöffnet werden.")
				.append("\n    WICHTIG! In allen Spalten in denen ..ID vorkommt, muss die entsprechende ID aus einem anderen Blatt (wie Chemie) eingetragen werden!")
				.append("\n")
				.append("\n3. Alle Informationen sind eingetragen.")
				.append("\n    Nun gehen Sie in Excel in die Registerkarte \"Daten\"")
				.append("\n    Hier klicken Sie auf \"Daten Aktualisieren\"")
				.append("\n    Unten links in Excel sehen Sie das Excel arbeitet und Ihre Daten kombiniert. Warten Sie kurz.")
				.append("\n    Speichern Sie das Template mit \"STRG + S\".")
				.append("\n")
				.append("\n4. Wechseln Sie nun in den Table Generator.")
				.append("\n")
				.append("\n5. Wählen Sie einen den gewünschten Datensatz aus dem Template. (Gesamt kombiniert alle " +
						"drei Datensätze.)")
				.append("\n")
				.append("\n6. Wählen Sie Ihre gewünschten Tabellen.")
				.append("\n")
				.append("\n7. Klicken Sie auf den Button \"BUILD\"")
				.append("\n    Die Tabellen erscheinen nun als HTML Dateien in dem Ordner Ihrer ausgewählten Datei.");
		
		return stringBuilder.toString();
	}
}
