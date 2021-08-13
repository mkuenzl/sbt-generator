package sbt.automization.util;

import org.apache.commons.cli.*;
import sbt.automization.templates.*;
import sbt.automization.templates.appendix.AppendixExplorationSite;
import sbt.automization.templates.appendix.AppendixLP;
import sbt.automization.templates.appendix.AppendixPN;
import sbt.automization.templates.appendix.AppendixRUK;
import sbt.automization.templates.report.ReportGOB;

import java.io.File;

/**
 * Class for a command line interface, when using the tool as jar
 */
public class CLI
{
    private final String[] args;
    private final Options options;
    private final File file = new File(System.getProperty("user.dir").concat(File.separator).concat("datenbank.csv"));
    private HtmlTableTemplate strategy;

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
                String csvFilePath = cmd.getOptionValue("c");
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
                        this.strategy = AppendixLP.getInstance();
                        break;
                    case "PN_Template":
                        this.strategy = AppendixPN.getInstance();
                        break;
                    case "RUK_Template":
                        this.strategy = AppendixRUK.getInstance();
                        break;
                    case "ERK_Template":
                        this.strategy = AppendixExplorationSite.getInstance();
                        break;
                    case "Bericht_Template":
                        this.strategy = ReportGOB.getInstance();
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

    public HtmlTableTemplate getStrategy()
    {
        return strategy;
    }
}
