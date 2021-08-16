package sbt.automization.data.refactoring;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.refactoring.references.*;
import sbt.automization.data.refactoring.references.Probe;
import sbt.automization.data.refactoring.references.Sample;
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
                                                                                                                             
        File csv = new File(path + "datenbank-template.csv");
                                                                                                                             
        header = CsvParser.parseHeader(csv);
    }

    @Test
    public void sampleReferenceExistInHeaderTest()
    {
        List<String> missingReferences = new ArrayList<>();

        Sample[] samples = Sample.values();
        for (Sample reference : samples) {
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

        Probe[] probes = Probe.values();
        for (Probe reference : probes) {
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

        Chemistry[] chemistry = Chemistry.values();
        for (Chemistry reference : chemistry) {
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

        RuK[] ruK = RuK.values();
        for (RuK reference : ruK) {
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

        LP[] loadPlate = LP.values();
        for (LP reference : loadPlate) {
            String key = reference.getKey();
            if (! header.contains(key))
            {
                missingReferences.add(key);
            }
        }

        Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
    }
}
