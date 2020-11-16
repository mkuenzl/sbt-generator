package main.java.util;

import main.java.templates.ITemplateStrategy;
import main.java.templates.PN_TemplateStrategy;
import main.java.templates.RUK_TemplateStrategy;
import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class CLI
{
    private String[] args;
    private Options options = new Options();
    private String config = null;
    private ITemplateStrategy strategy;

    public CLI(String[] args)
    {
        this.args = args;

        options.addOption("h", "help", false, "Show options");
        options.addOption("t", "template", true, "Print template");
        options.addOption("c","config", true,"Provide a different configuration filepath");
    }


    //Keine Argumente Fall einführen
    public void parse()
    {
        CommandLineParser parser = new DefaultParser();
        try
        {
            CommandLine cmd = parser.parse(this.options, this.args);

            if (cmd.hasOption("c")){
                config = cmd.getOptionValue("c");
            }

            if (cmd.hasOption("h"))
            {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("testSuite", this.options);
            }

            if (cmd.hasOption("t")){
                switch (cmd.getOptionValue("t")){
                    case "PN_Tabelle":
                        strategy = PN_TemplateStrategy.getInstance();
                        break;
                    case "RUK_Tabelle":
                        strategy = RUK_TemplateStrategy.getInstance();
                        break;
                    default:
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

    public ITemplateStrategy getStrategy()
    {
        return strategy;
    }
}
