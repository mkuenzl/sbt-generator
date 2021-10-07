package sbt.automization.gui.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoButtonActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JDialog dialog = new JDialog();
		dialog.setTitle("Help");
		dialog.setSize(1000, 500);

		JPanel dialogPanel = new JPanel();
		dialogPanel.setSize(1000, 500);
		dialogPanel.setBackground(Color.WHITE);

		JTextArea infoText = new JTextArea();
		infoText.setBounds(10, 10, 980, 480);
		infoText.setEditable(false);
		infoText.setWrapStyleWord(true);
		infoText.setLineWrap(true);
		infoText.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		infoText.setText(getHowToText());
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

	private String getHowToText()
	{
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("1. Um ein aktuelles / neues Excel Template zu erhalten klicken Sie auf den Button \"Erstelle ExcelTemplate\".")
				.append("\n    Das Template erscheint im gleichen Verzeichnis/Ordner wie diese Anwendung.")
				.append("\n2. Öffnen Sie das ExcelTemplate und erstellen einen Datensatz.")
				.append("\n    Zu beachten:")
				.append("\n    In allen Daten Sheets ist nichts einzutragen.")
				.append("\n    Tragen Sie zuerst in den weißen Sheets die entsprechenden Untersuchungsergebnisse zu Chemie, RuK und Lp ein.")
				.append("\n    Anschließend tragen Sie in ihrem entsprechenden Bereich (gelb) Straße, (blau) Haufwerk, (lila) Gebäude die entsprechenden Daten ein.")
				.append("\n    Beginnen Sie hierbei mit den Erkundungsstellen und fahren sie anschließend mit den Proben fort.")
				.append("\n    Sie können Rückwirkend jederzeit Änderungen vornehmen.")
				.append("\n    Bedienung in Excel:")
				.append("\n    Wenn Sie eine neue Zeile ausfüllen, tragen Sie den ersten Wert ein und drücken entweder TAB oder Enter.")
				.append("\n    Jedes Dropdown Menu kann entweder mit der Maus oder mit \"ALT + Pfeiltaste nach unten\" geöffnet werden.")
				.append("\nWICHTIG! In allen Spalten in denen ..ID vorkommt, muss die entsprechende ID aus einem anderen Blatt (wie Chemie) eingetragen werden!")
				.append("\n3. Sind alle Informationen eingetragen. Wechseln Sie zu ihrem Daten... Sheet")
				.append("\n    Nun gehen Sie in Excel in die Registerkarte \"Daten\"")
				.append("\n    Hier klicken Sie auf \"Daten Aktualisieren\"")
				.append("\n    Unten links in Excel sehen Sie das Excel arbeitet und Ihre Daten kombiniert. Warten Sie kurz.")
				.append("\n    Die Daten sollten nun in dem ausgewählten Sheet erscheinen. Sie können sie gegebenenfalls prüfen.")
				.append("\n    Bleiben Sie in dem Daten... Sheet und speichern dieses als CSV UTF-8 an eine beliebige Stelle.")
				.append("\n    Die erscheinende Warnung sollte Sie benachrichtigen, dass nur ein Blatt gespeichert wird und kann mit OK geschlossen werden.")
				.append("\n4. Wechseln Sie nun in den Table Generator.")
				.append("\n5. Wählen Sie nun mit dem entsprechenden Button Ihre CSV Datei aus.")
				.append("\n6. Wählen Sie Ihre gewünschten Tabellen.")
				.append("\n7. Klicken Sie auf den Button \"Erstelle Tabellen\"")
				.append("\n    Die Tabellen erscheinen nun als HTML Dateien in dem Ordner Ihrer CSV Datei.");

		return stringBuilder.toString();
	}
}