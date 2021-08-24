package sbt.automization.util;

import org.junit.Test;

import java.io.IOException;

public class UtilExportFilesTest
{
    @Test
    public void exportCSVTemplate() throws IOException
    {
        FileExport.copyFileToUserDirectory("/datenbank-template.xlsx");

        //TODO implement option to check if there is a file created
    }
}
