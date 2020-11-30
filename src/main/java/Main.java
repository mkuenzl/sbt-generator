package main.java;

import main.java.export.HTMLTemplateExportStrategy;
import main.java.templates.ERK_TemplateStrategy;
import main.java.templates.LP_TemplateStrategy;
import main.java.templates.PN_TemplateStrategy;
import main.java.projekt.Projekt;
import main.java.templates.RUK_TemplateStrategy;
import main.java.util.CLI;
import main.java.util.Parser;

import java.io.File;
import java.io.IOException;


public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException
    {
        File file = new File("C:\\Users\\Kuenzl\\Desktop\\Datenbank.CSV");

        //Reflection
        //Class clazz = Class.forName("main.java.templates.PN_TemplateStrategy");
        //Method m = clazz.getMethod("getInstance");

        CLI commandLineInterface = new CLI(args);

        Parser parser = new Parser(file);

        Projekt projekt = new Projekt(parser.parse());

        projekt.export(new HTMLTemplateExportStrategy(LP_TemplateStrategy.getInstance()));
    }
}
