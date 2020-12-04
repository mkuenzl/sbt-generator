package main.java.util;

public interface IHtmlCode {

    void appendAttribute(String attribute, String content);

    //gibt <htmlobject attributes>content</htmlobject> zurück
    StringBuilder appendTag(StringBuilder strb);

    void appendContent(String content);
}
