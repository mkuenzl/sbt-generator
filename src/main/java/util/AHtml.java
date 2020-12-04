package main.java.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

abstract class AHtml implements IHtmlCode{
    String content;
    Map<String,String> attributes;

    public AHtml(){
        attributes = new HashMap();
    }

    String appendAttributes(){
        StringBuilder strb = new StringBuilder();
        for (String att: attributes.keySet()) {
            strb.append(att)
                    .append("=")
                    .append(attributes.get(att))
                    .append(" ");
        }
        return strb.toString();
    }

    @Override
    public void appendContent(String content) {
        this.content = content;
    }

    @Override
    public void appendAttribute(String attribute, String content) {
        attributes.put(attribute,content);
    }
}
