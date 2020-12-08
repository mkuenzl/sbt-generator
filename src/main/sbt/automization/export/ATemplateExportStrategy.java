package sbt.automization.export;

import sbt.automization.projekt.Projekt;
import sbt.automization.templates.IHtmlTemplateStrategy;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class ATemplateExportStrategy
{

    final IHtmlTemplateStrategy strategy;

    ATemplateExportStrategy(final IHtmlTemplateStrategy strategy){
        this.strategy = strategy;
    }

    private void export(String path, String content){
        //File file = new File(path);
        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
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

    private String getPath(){
        return System.getProperty("user.dir").concat(File.separator).concat(strategy.getExportFileName());
    }

}
