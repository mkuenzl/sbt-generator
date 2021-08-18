package sbt.automization.data;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.references.RefProbe;
import sbt.automization.data.references.RefSample;
import sbt.automization.data.references.*;
import sbt.automization.util.CsvParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReferenceTest {

    static List<String> header = new ArrayList<>();

    @BeforeClass
    public static void initializeHeader()
    {
        String path = System.getProperty("user.dir").concat(File.separator).concat("tests-resources").concat(File.separator);
                                                                                                                             
        File csv = new File(path + "datenbank-template-test.csv");
                                                                                                                             
        header = CsvParser.parseHeader(csv);
    }

    @Test
    public void sampleReferenceExistInHeaderTest()
    {
        List<String> missingReferences = new ArrayList<>();

        RefSample[] refSamples = RefSample.values();
        for (RefSample reference : refSamples) {
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

        RefProbe[] refProbes = RefProbe.values();
        for (RefProbe reference : refProbes) {
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

        RefChemistry[] refChemistry = RefChemistry.values();
        for (RefChemistry reference : refChemistry) {
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

        RefRuK[] refRuK = RefRuK.values();
        for (RefRuK reference : refRuK) {
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

        RefLP[] loadPlate = RefLP.values();
        for (RefLP reference : loadPlate) {
            String key = reference.getKey();
            if (! header.contains(key))
            {
                missingReferences.add(key);
            }
        }

        Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
    }
}
