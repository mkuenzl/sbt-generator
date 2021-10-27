package sbt.automization.core.data;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.SampleKey;
import sbt.automization.core.data.key.*;
import sbt.automization.core.parser.ExcelParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KeyTest {

    static List<String> header = new ArrayList<>();

    @BeforeClass
    public static void initializeHeader()
    {
        File inputFile = new File("tests-resources/input/excel-template-test.xlsx");
    
        ExcelParser parser = new ExcelParser();
        parser.setSheetName("Daten");
        header = parser.retrieveHeader(inputFile);
    }

    @Test
    public void sampleReferenceExistInHeaderTest()
    {
        List<String> missingReferences = new ArrayList<>();

        SampleKey[] sampleKeys = SampleKey.values();
        for (SampleKey reference : sampleKeys) {
            String key = reference.getKey();
            if (! header.contains(key))
            {
                missingReferences.add(key);
            }
        }

        Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
    }

    @Test
    public void probeReferenceExistInHeaderTest()
    {
        List<String> missingReferences = new ArrayList<>();

        ProbeKey[] probeKeys = ProbeKey.values();
        for (ProbeKey reference : probeKeys) {
            String key = reference.getKey();
            if (! header.contains(key))
            {
                missingReferences.add(key);
            }
        }

        Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
    }

    @Test
    public void parameterChemistryReferenceExistInHeaderTest()
    {
        List<String> missingReferences = new ArrayList<>();

        ChemistryKey[] chemistryKey = ChemistryKey.values();
        for (ChemistryKey reference : chemistryKey) {
            String key = reference.getKey();
            if (! header.contains(key))
            {
                missingReferences.add(key);
            }
        }

        Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
    }

    @Test
    public void parameterRUKReferenceExistInHeaderTest()
    {
        List<String> missingReferences = new ArrayList<>();

        RuKKey[] ruKKey = RuKKey.values();
        for (RuKKey reference : ruKKey) {
            String key = reference.getKey();
            if (! header.contains(key))
            {
                missingReferences.add(key);
            }
        }

        Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
    }

    @Test
    public void parameterLPReferenceExistInHeaderTest()
    {
        List<String> missingReferences = new ArrayList<>();

        LpKey[] loadPlate = LpKey.values();
        for (LpKey reference : loadPlate) {
            String key = reference.getKey();
            if (! header.contains(key))
            {
                missingReferences.add(key);
            }
        }

        Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
    }
}
