package sbt.automization.util.html;

import java.util.HashMap;
import java.util.Map;

abstract class AHtml implements IHtmlCode
{

    String content = "";
    Map<String, String> attributes = new HashMap<>();

    public AHtml()
    {
    }

    String appendAttributes()
    {
        StringBuilder strb = new StringBuilder();
        for (String att : attributes.keySet())
        {
            strb.append(att)
                    .append("=")
                    .append(attributes.get(att))
                    .append(" ");
        }
        return strb.toString();
    }

    @Override
    public void appendAttribute(String attribute, String content)
    {
        attributes.put(attribute, content);
    }

    @Override
    public void appendContent(String content)
    {
        if ("".equals(this.content))
        {
            this.content = content;
        } else
        {
            this.content = this.content + "\n" + content;
        }
    }

    public boolean isWithoutContent()
    {
        return "".equals(content);
    }

    protected static abstract class BaseHtmlBuilder<T extends AHtml, B extends BaseHtmlBuilder>
    {
        protected T actualHtmlClass;
        protected B actualClassBuilder;

        protected BaseHtmlBuilder()
        {
            actualHtmlClass = getActual();
            actualClassBuilder = getActualBuilder();
        }

        protected abstract T getActual();

        protected abstract B getActualBuilder();

        public B appendAttribute(String attribute, String content)
        {
            actualHtmlClass.appendAttribute(attribute, content);
            return actualClassBuilder;
        }

        public B appendContent(String content)
        {
            if ("".equals(actualHtmlClass.content))
            {
                actualHtmlClass.content = content;
            } else
            {
                actualHtmlClass.content = actualHtmlClass.content + "\n" + content;
            }
            return actualClassBuilder;
        }

        public T build()
        {
            return actualHtmlClass;
        }
    }
}
