package main.java.wordblocks;

abstract class AWordObject implements IWordObject
{
    private String parameter;

    AWordObject(final String parameter){
        this.parameter = parameter;
    }

    AWordObject(){}

    public String getParameter() {
        return parameter;
    }

    void setParameter(final String parameter)
    {
        this.parameter = parameter;
    }
}
