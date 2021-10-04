package sbt.automization.html;

public class HtmlColGroup extends AHtml
{
	@Override
	public String appendTag()
	{
		StringBuilder strb = new StringBuilder();
		strb.append("<")
				.append("colgroup")
				.append(" ")
				.append(appendAttributes())
				.append(">")
				.append("\n")
				.append(content)
				.append("\n")
				.append("</colgroup>");
		return strb.toString();
	}

	public static class Builder extends BaseHtmlBuilder<HtmlColGroup, Builder>
	{

		@Override
		protected HtmlColGroup getActual()
		{
			return new HtmlColGroup();
		}

		@Override
		protected Builder getActualBuilder()
		{
			return this;
		}
	}
}
