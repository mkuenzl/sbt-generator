package sbt;

import sbt.automization.engine.TableEngine;
import sbt.automization.export.HtmlTemplateExportStrategy;
import sbt.automization.gui.GUI;
import sbt.automization.templates.Anlage_ERK_TemplateStrategy;
import sbt.automization.templates.Anlage_LP_TemplateStrategy;
import sbt.automization.templates.Anlage_PN_TemplateStrategy;
import sbt.automization.templates.Anlage_RUK_TemplateStrategy;
import sbt.automization.util.Parser;

import java.io.File;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
//        CLI commandLineInterface = new CLI(args);
//        commandLineInterface.parse();
//
        //FALL KEIN FILE VORHANDEN
        //Parser parser = new Parser(commandLineInterface.getCsvFile());
<<<<<<< Updated upstream
        Parser parser = new Parser(new File("datenbank_schablone.csv"));
=======
//        Parser parser = new Parser(new File("datenbank.csv"));
>>>>>>> Stashed changes
//
//
 //       TableEngine projekt = new TableEngine(parser.parse());
//
//        //FALL KEINE STRATEGY VORHANDEN
//        //projekt.export(new HtmlTemplateExportStrategy(commandLineInterface.getStrategy()));
//        projekt.export(new HtmlTemplateExportStrategy(Anlage_RUK_TemplateStrategy.getInstance()));
//        projekt.export(new HtmlTemplateExportStrategy(Anlage_LP_TemplateStrategy.getInstance()));
//        projekt.export(new HtmlTemplateExportStrategy(Anlage_ERK_TemplateStrategy.getInstance()));
//        projekt.export(new HtmlTemplateExportStrategy(Anlage_PN_TemplateStrategy.getInstance()));

        new GUI();
    }
}
