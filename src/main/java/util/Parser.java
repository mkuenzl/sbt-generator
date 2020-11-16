package main.java.util;

import main.java.projekt.Erkundungsstelle;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Parser
{
    final File csv;

    public Parser(File csv)
    {
        this.csv = csv;
    }

    public List parse() throws IOException
    {
        List<Erkundungsstelle> erks = new ArrayList<>();

        InputStreamReader input = new InputStreamReader(new FileInputStream(csv));
        CSVParser csvParser = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().parse(input);


        //Zeile
        for (CSVRecord record : csvParser)
        {
            erks.add(new Erkundungsstelle(record.toMap()));
        }

        return erks;
    }
}
