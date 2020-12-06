package sbt.automization.util.html;

public interface IHtmlCode {

    void appendAttribute(String attribute, String content);

    //gibt <htmlobject attributes>content</htmlobject> zurück
    String appendTag();

    void appendContent(String content);
}
