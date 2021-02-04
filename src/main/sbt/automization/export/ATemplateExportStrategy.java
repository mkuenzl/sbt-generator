package sbt.automization.export;

import sbt.automization.engine.TableEngine;
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
        BufferedWriter bw = null;
        OutputStreamWriter outputStreamWriter = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(path);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            bw = new BufferedWriter(outputStreamWriter);
            bw.write(content);
            bw.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (fileOutputStream != null)
            {
                try
                {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    bw.flush();
                    bw.close();
                } catch (IOException e)
                {
                    // ignored
                }
            }
        }
    }

    public void export(TableEngine tableEngine){
        export(getPath(), format(tableEngine));
    };

    abstract String format(TableEngine tableEngine);

    private String getPath(){
        return System.getProperty("user.dir").concat(File.separator).concat(strategy.getExportFileName());
    }

}
