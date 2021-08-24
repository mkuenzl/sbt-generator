package sbt.automization.html;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to recreate custom html tags as java objects
 * Should probably be replaced by a framework / library
 */
abstract class AHtml implements IHtmlCode
{
    String content = "";
    Map<String, String> attributes = new HashMap<>();

    public AHtml()
    {
    }

    /**
     * Method formats all attributes to String
     * @return a String to put into the html tag
     */
    String appendAttributes()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (String att : attributes.keySet())
        {
            stringBuilder.append(att)
                    .append("=")
                    .append(attributes.get(att))
                    .append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Method adds an attribute with a value to the html tag to the attribute map
     */
    @Override
    public void appendAttribute(String attribute, String content)
    {
        attributes.put(attribute, content);
    }

    /**
     * Method to add content as String between the html opening and closing tag
     * @param content a String as text or html
     */
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

    @Deprecated
    public boolean isWithoutContent()
    {
        return "".equals(content);
    }

    /**
     * Builder Class for html objects. Each class inheriting from this class has to implement
     * a builder for object creation. Using generic type parameter its possible to create a abstract builder class.
     * https://www.torsten-horn.de/techdocs/java-generics.htm
     * @param <T> a generic type of AHtml
     * @param <B> a generic type of BaseHtmlBuilder
     */
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
