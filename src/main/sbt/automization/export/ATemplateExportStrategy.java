package sbt.automization.export;

import sbt.automization.TableEngine;
import sbt.automization.data.Erkundungsstelle;
import sbt.automization.templates.IHtmlTemplateStrategy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class ATemplateExportStrategy
{

    final IHtmlTemplateStrategy strategy;

    ATemplateExportStrategy(final IHtmlTemplateStrategy strategy)
    {
        this.strategy = strategy;
    }

    public void export(TableEngine tableEngine)
    {
        export(getPath(), format(tableEngine));
    }

    public void export(List<Erkundungsstelle> erkundungsstellen)
    {
        export(getPath(), format(erkundungsstellen));
    }

    private void export(String path, String content)
    {
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
                    assert outputStreamWriter != null;
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    assert bw != null;
                    bw.flush();
                    bw.close();
                } catch (IOException e)
                {
                    // ignored
                }
            }
        }
    }

    private String getPath()
    {
        return System.getProperty("user.dir").concat(File.separator).concat(strategy.getExportFileName());
    }

    abstract String format(TableEngine tableEngine);

    abstract String format(List<Erkundungsstelle> erkundungsstellen);

}
