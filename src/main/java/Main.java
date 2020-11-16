package main.java;

import main.java.export.HTMLTemplateExportStrategy;
import main.java.templates.PN_TemplateStrategy;
import main.java.projekt.Projekt;
import main.java.util.Parser;

import java.io.File;
import java.io.IOException;


public class Main
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("/Users/moritzkunzl/Desktop/HTML/ProjektDaten.csv");

        Parser parser = new Parser(file);


        Projekt projekt = new Projekt(parser.parse());

        projekt.export(new HTMLTemplateExportStrategy());

    }
}
