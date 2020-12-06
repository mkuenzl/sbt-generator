package main.java.util;

import main.java.templates.*;
import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class CLI
{
    private String[] args;
    private Options options = new Options();
    private String csvFilePath = System.getProperty("user.dir").concat("datenbank.csv");
    private IHtmlTemplateStrategy strategy;

    public CLI(String[] args)
    {
        this.args = args;

        options.addOption("h", "help", false, "Show options");
        options.addOption("t", "template", true, "Print template");
        options.addOption("c","csv", true,"Provide a different csv filepath");
    }

    //Keine Argumente Fall einführen
    public void parse()
    {
        CommandLineParser parser = new DefaultParser();
        try
        {
            CommandLine cmd = parser.parse(this.options, this.args);

            if (cmd.hasOption("c")){
                csvFilePath = cmd.getOptionValue("c");
            }

            if (cmd.hasOption("h"))
            {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("sbt-table-generator", this.options);
            }

            if (cmd.hasOption("t")){
                switch (cmd.getOptionValue("t")){
                    case "LP_Template":
                        this.strategy = LPTemplateStrategy.getInstance();
                        break;
                    case "PN_Template":
                        this.strategy = PNTemplateStrategy.getInstance();
                        break;
                    case "RUK_Template":
                        this.strategy = RUKTemplateStrategy.getInstance();
                        break;
                    case "ERK_Template":
                        //this.strategy = ERKTemplateStrategy.getInstance();
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

    public Map parseOption(String propValues)
    {
        Map<String, String> map = new HashMap<>();
        String[] values = propValues.split(",");
        for (String value : values)
        {
            String[] property = value.split("=");
            map.put(property[0], property[1]);
        }
        return map;
    }

    public String getCsvFilePath()
    {
        return csvFilePath;
    }

    public IHtmlTemplateStrategy getStrategy()
    {
        return strategy;
    }
}
