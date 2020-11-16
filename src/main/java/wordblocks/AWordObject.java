package main.java.wordblocks;

abstract class AWordObject implements IWordObject
{
    private final String parameter;

    AWordObject(final String parameter){
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
