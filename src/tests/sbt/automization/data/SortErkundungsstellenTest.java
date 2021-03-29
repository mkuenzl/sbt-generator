package sbt.automization.data;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.Erkundungsstelle;

import java.util.*;

public class SortErkundungsstellenTest {

    private static Erkundungsstelle BA1;
    private static Erkundungsstelle BA2;
    private static Erkundungsstelle FB1;
    private static Erkundungsstelle FB2;
    private static Erkundungsstelle FB11;
    private static Erkundungsstelle GEW1;

    private static Erkundungsstelle noNumber;
    private static Erkundungsstelle noString;
    private static Erkundungsstelle nothing;


    @BeforeClass
    public static void constructTestData()
    {
        HashMap<String, String> dataMap1 = new HashMap<>();
        dataMap1.put("ERK_ID", "BA1");
        BA1 = new Erkundungsstelle().setDataMap(dataMap1);
        HashMap<String, String> dataMap2 = new HashMap<>();
        dataMap2.put("ERK_ID", "BA2");
        BA2 = new Erkundungsstelle().setDataMap(dataMap2);
        HashMap<String, String> dataMap3 = new HashMap<>();
        dataMap3.put("ERK_ID", "FB1");
        FB1 = new Erkundungsstelle().setDataMap(dataMap3);
        HashMap<String, String> dataMap4 = new HashMap<>();
        dataMap4.put("ERK_ID", "FB2");
        FB2 = new Erkundungsstelle().setDataMap(dataMap4);
        HashMap<String, String> dataMap5 = new HashMap<>();
        dataMap5.put("ERK_ID", "FB11");
        FB11 = new Erkundungsstelle().setDataMap(dataMap5);
        HashMap<String, String> dataMap6 = new HashMap<>();
        dataMap6.put("ERK_ID", "GEW1");
        GEW1 = new Erkundungsstelle().setDataMap(dataMap6);

        HashMap<String, String> dataMap7 = new HashMap<>();
        dataMap7.put("ERK_ID", "GEW");
        noNumber = new Erkundungsstelle().setDataMap(dataMap7);
        HashMap<String, String> dataMap8 = new HashMap<>();
        dataMap8.put("ERK_ID", "1");
        noString = new Erkundungsstelle().setDataMap(dataMap8);
        HashMap<String, String> dataMap9 = new HashMap<>();
        dataMap9.put("ERK_ID", "");
        nothing = new Erkundungsstelle().setDataMap(dataMap9);
    }

    @Test
    public void sortMultipleErkundungsstellenByNumber()
    {
        List<Erkundungsstelle> list = Arrays.asList(FB11, FB1, FB2);

        Collections.sort(list);

        Assert.assertEquals(list, new ArrayList<>(Arrays.asList(FB1, FB2, FB11)));
    }

    @Test
    public void sortMultipleErkundungstellenByString()
    {
        //BA1 FB2 FB1 BA2 GEW1 FB3 GEW2
        List<Erkundungsstelle> list = Arrays.asList(FB1, GEW1, BA1);

        Collections.sort(list);

        Assert.assertEquals(list, new ArrayList<>(Arrays.asList(BA1, FB1, GEW1)));
    }

    @Test
    public void sortMultipleErkundungstellenTest()
    {
        //BA1 FB2 FB1 BA2 GEW1 FB3 GEW2
        List<Erkundungsstelle> list = Arrays.asList(FB11, FB1, GEW1, FB2, BA2, BA1);

        Collections.sort(list);

        Assert.assertEquals(list, new ArrayList<>(Arrays.asList(BA1, BA2, FB1, FB2, FB11, GEW1)));
    }

    @Test
    public void sortMultipleErkundungstellenNoNumberTest()
    {
        //BA1 FB2 FB1 BA2 GEW1 FB3 GEW2
        List<Erkundungsstelle> list = Arrays.asList(FB11, FB1, noNumber, GEW1, FB2, BA2, BA1);

        Collections.sort(list);

        for (Erkundungsstelle erkundungsstelle : list) {
            System.out.println(erkundungsstelle.getInformation("ERK_ID"));
        }

        Assert.assertEquals(list, new ArrayList<>(Arrays.asList(BA1, BA2, FB1, FB2, FB11, GEW1, noNumber)));
    }

    @Test
    public void sortMultipleErkundungstellenNoStringTest()
    {
        //BA1 FB2 FB1 BA2 GEW1 FB3 GEW2
        List<Erkundungsstelle> list = Arrays.asList(FB11, FB1, GEW1, noString, FB2, BA2, BA1);

        Collections.sort(list);

        for (Erkundungsstelle erkundungsstelle : list) {
            System.out.println(erkundungsstelle.getInformation("ERK_ID"));
        }

        Assert.assertEquals(list, new ArrayList<>(Arrays.asList(BA1, BA2, FB1, FB2, FB11, GEW1, noString)));
    }

    @Test
    public void sortMultipleErkundungstellenNoIdTest()
    {
        //BA1 FB2 FB1 BA2 GEW1 FB3 GEW2
        List<Erkundungsstelle> list = Arrays.asList(FB11, FB1, GEW1, nothing, FB2, BA2, BA1);

        Collections.sort(list);

        for (Erkundungsstelle erkundungsstelle : list) {
            System.out.println(erkundungsstelle.getInformation("ERK_ID"));
        }

        Assert.assertEquals(list, new ArrayList<>(Arrays.asList(BA1, BA2, FB1, FB2, FB11, GEW1, nothing)));
    }
}
