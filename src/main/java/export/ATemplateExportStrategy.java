package main.java.export;


import main.java.projekt.Projekt;
import main.java.templates.ITemplateStrategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class ATemplateExportStrategy {

    final ITemplateStrategy strategy;

    ATemplateExportStrategy(final ITemplateStrategy strategy){
        this.strategy = strategy;
    }

    private void export(String path, String content){
        String exportPath = path;
        File file = new File(path);
        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(content);
            bw.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (bw != null)
            {
                try
                {
                    bw.flush();
                    bw.close();
                } catch (IOException e)
                {
                    // ignored
                }
            }
        }
    }

    public void export(Projekt projekt){
        export(getPath(), format(projekt));
    };

    abstract String format(Projekt projekt);

    abstract String format(ITemplateStrategy strategy);

    private String getPath(){
        return System.getProperty("user.dir" ).concat(File.separator).concat("testPN.html");
    }

}
