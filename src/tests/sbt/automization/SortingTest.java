package sbt.automization;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SortingTest {

    @Test
    public void sortErkundungstellenTest()
    {
        //BA1 FB2 FB1 BA2 GEW1 FB3 GEW2
        List<String> list = Arrays.asList("BA1", "FB1", "FB3", "FB2", "BA2");

        java.util.Collections.sort(list);

        System.out.println(list);

    }
}
