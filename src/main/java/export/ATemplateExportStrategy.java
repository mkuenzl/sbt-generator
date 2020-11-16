package main.java.export;

import main.java.projekt.Erkundungsstelle;
import main.java.projekt.Projekt;
import main.java.templates.ATemplateStrategy;
import main.java.templates.PN_TemplateStrategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class ATemplateExportStrategy {

    private void export(String path, String content){
        String exportPath = path;
        File file = new File(path);
        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(content);
            bw.close();
            //ProfilingLogger.addText(String.format("Export to '%s' successful.", file.getName()));
        } catch (IOException e)
        {
            e.printStackTrace();
            //ProfilingLogger.addText(String.format("Export to '%s' failed.", file.getName()));
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
        return System.getProperty("user.dir" ).concat(File.separator).concat("testPN.html");
    }

    String readHTMLHead(final String pathToHead) throws IOException
    {
        String content = Files.readString(Paths.get(pathToHead), StandardCharsets.UTF_8);
        return content;
    }
}
