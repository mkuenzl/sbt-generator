package sbt.automization.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public List parse() throws IOException
    {
        List<Map> dataPoints = new ArrayList<>();

        InputStreamReader input = new InputStreamReader(new FileInputStream(csv));
        CSVParser csvParser = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().parse(input);

        //Zeile
        for (CSVRecord record : csvParser)
        {
            dataPoints.add(record.toMap());
        }

        return dataPoints;
    }
}
