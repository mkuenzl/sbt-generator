package main.java;

import main.java.export.HTMLTemplateExportStrategy;
import main.java.projekt.ErkVariable;
import main.java.templates.ATemplateStrategy;
import main.java.templates.ITemplateStrategy;
import main.java.templates.PN_TemplateStrategy;
import main.java.projekt.Projekt;
import main.java.templates.RUK_TemplateStrategy;
import main.java.util.CLI;
import main.java.util.Parser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException
    {
        File file = new File("/Users/moritzkunzl/Desktop/HTML/ProjektDaten.csv");

        //Reflection
        //Class clazz = Class.forName("main.java.templates.PN_TemplateStrategy");
        //Method m = clazz.getMethod("getInstance");

        CLI commandLineInterface = new CLI(args);

        Parser parser = new Parser(file);

        Projekt projekt = new Projekt(parser.parse());

        projekt.export(new HTMLTemplateExportStrategy(PN_TemplateStrategy.getInstance()));
    }
}
