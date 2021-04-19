package sbt.automization.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Parser
{
    final File csv;

    public Parser(File csv)
    {
        this.csv = csv;
    }

    public List<Map<String,String>> parse()
    {
        List<Map<String,String>> dataPoints = new ArrayList<>();

        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        CSVParser csvParser = null;

        /* Excel export of .csv files in UTF-8 is always with BOM information (first bytes of each line) */
        BOMInputStream bomInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(csv);
            bomInputStream = new BOMInputStream(fileInputStream);
            inputStreamReader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8);
            csvParser = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().withIgnoreEmptyLines(true).parse(inputStreamReader);

            if (csvParser != null){
                //Zeile
                for (CSVRecord record : csvParser)
                {
                    /* Prevent wrong excel formatting of .csv files to crash the program */
                    if (!"".equals(record.get(0))){
                        Map<String,String> map = record.toMap();
                        dataPoints.add(map);
                    }
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (fileInputStream != null){
                try
                {
                    assert inputStreamReader != null;
                    inputStreamReader.close();
                    fileInputStream.close();
                    bomInputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return dataPoints;
    }
}
