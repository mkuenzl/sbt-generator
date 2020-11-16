package main.java.wordblocks;

public class WordObjectRowBuilder
{
    private WordObjectRow wordObjectRow;

    public WordObjectRowBuilder(){
        wordObjectRow = new WordObjectRow();
    }

    public WordObjectRowBuilder setParameter(String parameter){
        wordObjectRow.setParameter(parameter);
        return this;
    }

    public WordObjectRowBuilder addCell(WordObjectCell cell){
        wordObjectRow.addCell(cell);
        return this;
    }

    public WordObjectRow build(){
        return this.wordObjectRow;
    }
}
