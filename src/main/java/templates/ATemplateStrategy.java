package main.java.templates;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class ATemplateStrategy implements ITemplateStrategy
{
    String readTemplateHeader(final String pathToHeader) throws IOException
    {
        String content = Files.readString(Paths.get(pathToHeader), StandardCharsets.UTF_8);
        return content;
    }
}
