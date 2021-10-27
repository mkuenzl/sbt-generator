package sbt.automization.core.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class UtilExportFilesTest
{
    @Test
    public void exportExcelTemplate() throws IOException
    {
        FileExport.copyFileToUserDirectory("/datenbank-template.xlsx");
    
        File file = new File("datenbank-template.xlsx");
        
        Assert.assertTrue(file.exists());
        
        file.delete();
    }
}
