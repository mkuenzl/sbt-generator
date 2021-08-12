package sbt.automization.data.refactoring;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.refactoring.references.*;
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

        ReferenceSample[] referenceSamples = ReferenceSample.values();
        for (ReferenceSample reference : referenceSamples) {
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

        ReferenceProbe[] referenceProbes = ReferenceProbe.values();
        for (ReferenceProbe reference : referenceProbes) {
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

        ReferenceParameterChemistry[] referenceParameterChemistry = ReferenceParameterChemistry.values();
        for (ReferenceParameterChemistry reference : referenceParameterChemistry) {
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

        ReferenceParameterRuK[] referenceParameterRuK = ReferenceParameterRuK.values();
        for (ReferenceParameterRuK reference : referenceParameterRuK) {
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

        ReferenceParameterLP[] referenceParameterLP = ReferenceParameterLP.values();
        for (ReferenceParameterLP reference : referenceParameterLP) {
            String key = reference.getKey();
            if (! header.contains(key))
            {
                missingReferences.add(key);
            }
        }

        Assert.assertEquals(missingReferences.toString(), 0, missingReferences.size());
    }
}
