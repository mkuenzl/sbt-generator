package sbt.automization.templates;


import org.junit.Before;
import org.junit.Test;
import sbt.automization.data.Erkundungsstelle;
import sbt.automization.export.HtmlTemplateExportStrategy;

import java.util.ArrayList;
import java.util.List;

public class TemplateTest
{
    List<Erkundungsstelle> erkundungsstellen = new ArrayList<>();

    @Before
    public void createErk()
    {
        ERKCreationTestUtil erkCreationTestUtil = new ERKCreationTestUtil();
        erkundungsstellen.add(erkCreationTestUtil.getTestErkundungsstelle1());
    }

    @Test
    public void createErkTemplate(){
        HtmlTemplateExportStrategy htmlTemplateExportStrategy = new HtmlTemplateExportStrategy(Anlage_ERK_TemplateStrategy.getInstance());
        htmlTemplateExportStrategy.export(erkundungsstellen);
    }
}
