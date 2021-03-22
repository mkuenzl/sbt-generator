package sbt.automization.util;

import org.apache.commons.cli.*;
import sbt.automization.templates.*;

import java.io.File;

public class CLI
{
    private String[] args;
    private Options options;
    private String csvFilePath;
    private File file = new File(System.getProperty("user.dir").concat(File.separator).concat("datenbank.csv"));
    private IHtmlTemplate strategy;

    public CLI(String[] args)
    {
        this.args = args;
        this.options = new Options();

        options.addOption("h", "help", false, "Show options");
        options.addOption("t", "template", true, "Print template");
        options.addOption("c", "csv", true, "Provide a different csv filepath");
    }

    //Keine Argumente Fall einführen
    public void parse()
    {
        CommandLineParser parser = new DefaultParser();
        try
        {
            CommandLine cmd = parser.parse(this.options, this.args);

            if (cmd.hasOption("c"))
            {
                csvFilePath = cmd.getOptionValue("c");
            }

            if (cmd.hasOption("h"))
            {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("sbt-table-generator", this.options);
            }

            if (cmd.hasOption("t"))
            {
                switch (cmd.getOptionValue("t"))
                {
                    case "LP_Template":
                        this.strategy = Anlage_LP_Template.getInstance();
                        break;
                    case "PN_Template":
                        this.strategy = Anlage_PN_Template.getInstance();
                        break;
                    case "RUK_Template":
                        this.strategy = Anlage_RUK_Template.getInstance();
                        break;
                    case "ERK_Template":
                        this.strategy = Anlage_ERK_Template.getInstance();
                        break;
                    case "Bericht_Template":
                        this.strategy = Bericht_OB_Template.getInstance();
                        break;
                    default:
                        System.err.println("Please provide a template.");
                        break;
                }
            }

        } catch (ParseException e)
        {
            System.err.println("Parsing failed. Reason: " + e.getMessage());
        }
    }

    public File getCsvFile()
    {
        return file;
    }

    public IHtmlTemplate getStrategy()
    {
        return strategy;
    }
}
