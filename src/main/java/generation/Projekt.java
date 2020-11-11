package main.java.generation;

import main.java.export.ATemplate;
import main.java.export.PN_Template;

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

    public void export(final ATemplate template)
    {
        template.export(this);
    }
}
