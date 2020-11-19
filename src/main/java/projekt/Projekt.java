package main.java.projekt;

import main.java.export.ATemplateExportStrategy;
import main.java.templates.ATemplateStrategy;
import main.java.templates.ITemplateStrategy;

import java.util.List;

public class Projekt
{
    final List<AErkundungsstelle> erk;

    public Projekt(final List<AErkundungsstelle> erk){
        this.erk = erk;
    }

    public List<AErkundungsstelle> getErk()
    {
        return erk;
    }

    public void export(ATemplateExportStrategy templateExportStrategy)
    {
        templateExportStrategy.export(this);
    }
}
