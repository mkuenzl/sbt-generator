package main.java.export;

import main.java.generation.AErkundungsstelle;
import main.java.generation.Erkundungsstelle;
import main.java.generation.Projekt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class ATemplate
{
    final String htmlCloseTab = "</table>\n" +
            "\n" +
            "<p class=MsoNormal><o:p>&nbsp;</o:p></p>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>";

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

    public final void export(Erkundungsstelle erkundungsstelle){
        export(getPath(), format(erkundungsstelle));
    }

    private String getPath(){
        return System.getProperty("user.dir" ).concat(File.separator).concat("test.html");
    }

    public abstract String format(AErkundungsstelle erkundungsstelle);

    public void export(final Projekt projekt){
        export(getPath(), format(projekt));   
    }

    protected abstract String format(final Projekt projekt);

    String readTemplateHeader(final String pathToHeader) throws IOException
    {
        String content = Files.readString(Paths.get(pathToHeader), StandardCharsets.UTF_8);
        return content;
    }
}
