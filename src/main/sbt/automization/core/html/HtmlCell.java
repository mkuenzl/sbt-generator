package sbt.automization.core.html;

public class HtmlCell extends AHtml
{
	public HtmlCell(HtmlCell lastCell)
	{
		super(lastCell);
	}
	
	public HtmlCell()
	{
		super();
	}
	
	@Override
	public String appendTag()
	{
		StringBuilder strb = new StringBuilder();
		strb.append("<")
				.append("td")
				.append(" ")
				.append(appendAttributes())
				.append(">")
				.append("\n")
				.append(content)
				.append("\n")
				.append("</td>");
		return strb.toString();
	}
	
	public static class Builder extends BaseHtmlBuilder<HtmlCell, Builder>
	{
		@Override
		protected HtmlCell getActual()
		{
			return new HtmlCell();
		}
		
		@Override
		protected Builder getActualBuilder()
		{
			return this;
		}
	}
}
