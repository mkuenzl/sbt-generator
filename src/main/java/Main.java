package main.java;

import main.java.export.ATemplate;
import main.java.export.PN_Template;
import main.java.export.RUK_Template;
import main.java.generation.Erkundungsstelle;
import main.java.generation.Projekt;
import main.java.util.Parser;

import java.io.File;
import java.io.IOException;


public class Main
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("C:/Users/Kuenzl/Desktop/SBT/erkCSV.csv");

        Parser parser = new Parser(file);


        Projekt projekt = new Projekt(parser.parse());

        projekt.export(new PN_Template());

    }
}
