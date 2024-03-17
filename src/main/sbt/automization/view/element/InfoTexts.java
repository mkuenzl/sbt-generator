package sbt.automization.view.element;

public enum InfoTexts
{
    HELP("1. Button \"CREATE\" erstellt ein Excel Template." +
                 "\n    Das Template erscheint im gleichen Verzeichnis/Ordner wie diese Anwendung." +
                 "\n    Button \"LOAD\" lädt ein Template oder eine CSV" +
                 "\n    Button \"OPEN\" öffnet die ausgewählte Datei" +
                 "\n    Unter den Buttons befindet sich der Pfad der ausgewählten Datei." +
                 "\n" +
                 "\n2. Öffnen Sie das ExcelTemplate und erstellen einen Datensatz." +
                 "\n    Zu beachten:" +
                 "\n    Tragen Sie zuerst in den weißen Sheets die entsprechenden Untersuchungsergebnisse zu Chemie, " +
                 "RuK und Lp ein." +
                 "\n    Anschließend tragen Sie in ihrem entsprechenden Bereich (gelb) Straße, (blau) Haufwerk, " +
                 "(lila) Gebäude die entsprechenden Daten ein." +
                 "\n    Beginnen Sie hierbei mit den Erkundungsstellen und fahren sie anschließend mit den Proben " +
                 "fort." +
                 "\n    Sie können Rückwirkend jederzeit Änderungen vornehmen." +
                 "\n" +
                 "\n    Bedienung in Excel:" +
                 "\n    Wenn Sie eine neue Zeile ausfüllen, tragen Sie den ersten Wert ein und drücken entweder TAB " +
                 "oder Enter." +
                 "\n    Jedes Dropdown Menu kann entweder mit der Maus oder mit \"ALT + Pfeiltaste nach unten\" " +
                 "geöffnet werden." +
                 "\n    WICHTIG! In allen Spalten in denen ..ID vorkommt, muss die entsprechende ID aus einem anderen" +
                 " Blatt (wie Chemie) eingetragen werden!" +
                 "\n" +
                 "\n3. Alle Informationen sind eingetragen." +
                 "\n    Nun gehen Sie in Excel in die Registerkarte \"Daten\"" +
                 "\n    Hier klicken Sie auf \"Daten Aktualisieren\"" +
                 "\n    Unten links in Excel sehen Sie das Excel arbeitet und Ihre Daten kombiniert. Warten Sie kurz." +
                 "\n    Speichern Sie das Template mit \"STRG + S\"." +
                 "\n" +
                 "\n4. Wechseln Sie nun in den Table Generator." +
                 "\n" +
                 "\n5. Wählen Sie einen den gewünschten Datensatz aus dem Template. (Gesamt kombiniert alle " +
                 "drei Datensätze.)" +
                 "\n" +
                 "\n6. Wählen Sie Ihre gewünschten Tabellen." +
                 "\n" +
                 "\n7. Klicken Sie auf den Button \"BUILD\"" +
                 "\n    Die Tabellen erscheinen nun als HTML Dateien in dem Ordner Ihrer ausgewählten Datei."),
    V_2_1_1(new ReleaseNoteBuilder("2.1.1")
                    .addReleaseNote(
                            "Neuer Release-Info Button, der die aktuelle Release Version angibt und per Mausklick die" +
                                    " Neuerungen auflistet.")
                    .addReleaseNote("Tabellen, die sich auf Standards vor 08 beziehen, befinden sich nun in eigener " +
                                            "Spalte (Alte Tabellen) ganz rechts (GOB vor 08, Erkundungsstellen vor " +
                                            "08).")
                    .addReleaseNote("GOB-08 Report listet \"pechfreien\" vor \"pechhaltigem\" Querschnitt.")
                    .addReleaseNote("UG Report listet \"Konsistenz\" vor \"Verdichtungsfähigkeit\".")
                    .addReleaseNote("UG Report \"Tragfähigkeit Grabensohle\"-Werte sind nun wie alle anderen " +
                                            "Werte auf formatiert.")
                    .addReleaseNote(
                            "ERK-08 Reports Parameter-Wert (Auffälligkeit, keine Auffälligkeit etc.) Formatierung " +
                                    "angepasst, sodass eine vernünftige Worttrennung stattfindet.")
                    .addReleaseNote(
                            "In der Datenbank-template wurde die Etagenbezeichnung \"Keller\" durch \"UG\" ersetzt.")
                    .addReleaseNote("Die Datenbank-template verrechnet nun nur noch bei aufeinanderfolgenden GOB-Proben die Starttiefe und Endtiefe.")
                    .addReleaseNote("Haufwerk Volumen zu Probenberechnung funktioniert wieder.")
                    .addReleaseNote("MUFV-Berichte tauchen nur auf, wenn MUFV-Werte hinterlegt sind.")
                    .buildReleaseNote());


    private final String infoText;

    InfoTexts(String infoText)
    {
        this.infoText = infoText;
    }

    public String getInfoText()
    {
        return infoText;
    }
}
