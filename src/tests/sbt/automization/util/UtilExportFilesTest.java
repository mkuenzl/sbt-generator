package sbt.automization.util;

import org.junit.Test;

import java.io.IOException;

public class UtilExportFilesTest
{
    @Test
    public void exportCSVTemplate() throws IOException
    {
        Util.exportFile("/sbt-excel-template.xlsx");

        //TODO implement option to check if there is a file created
    }
}
