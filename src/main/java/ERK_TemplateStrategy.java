//package main.java.templates;
//
//import main.java.projekt.AErkundungsstelle;
//import main.java.projekt.AErkundungsstelleSchicht;
//import main.java.projekt.Erkundungsstelle;
//import main.java.projekt.Projekt;
//
//public class ERK_TemplateStrategy extends ATemplateStrategy
//{
//    public ERK_TemplateStrategy(){}
//
//    void appendTag(StringBuilder sb, String tag, String contents) {
//        sb.append('<').append(tag).append('>');
//        sb.append(contents);
//        sb.append("</").append(tag).append('>');
//    }
//
//    @Override
//    public String format(final AErkundungsstelle erkundungsstelle)
//    {
//        Erkundungsstelle erk = (Erkundungsstelle) erkundungsstelle;
//
//        StringBuilder strb = new StringBuilder();
//        strb.append("<!DOCTYPE html>");
//        strb.append("<html>");
//        strb.append("<head>");
//        strb.append("<meta charset=\"utf-8\">");
//        strb.append("<title>Erkundungsstellen Export</title>");
//        strb.append("<body style=\"overflow: hidden;white-space: nowrap;\">");
//        strb.append("<div style=\"float: left; width: 49%;position: relative;max-height: 90vh\">");
//        strb.append("<p style=\"border-bottom: 1px #005E9C solid;\"></p>");
//        strb.append("<dl>");
//        strb.append(erk.getId());
//        strb.append("</dl>");
//        strb.append("<dl>");
//        strb.append(erk.getDate());
//        strb.append("</dl>");
//        strb.append("<dl>");
//        strb.append(erk.getName());
//        strb.append("</dl>");
//        strb.append("<p style=\"border-bottom: 1px #005E9C solid;\"></p>");
//        strb.append("</div>");
//        strb.append("</body>");
//        strb.append("</html>");
//
//        return strb.toString();
//    }
//
//    @Override
//    public String format(AErkundungsstelleSchicht erkundungsstelleSchicht) {
//        return null;
//    }
//
//    @Override
//    public String buildHtmlTable(final Projekt projekt)
//    {
//        return null;
//    }
//}
