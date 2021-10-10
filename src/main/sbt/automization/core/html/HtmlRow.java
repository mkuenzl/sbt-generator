package sbt.automization.core.html;

public class HtmlRow extends AHtml
{
	@Override
	public String appendTag()
	{
		StringBuilder strb = new StringBuilder();
		strb.append("<")
				.append("tr")
				.append(" ")
				.append(appendAttributes())
				.append(">")
				.append("\n")
				.append(content)
				.append("\n")
				.append("</tr>");
		return strb.toString();
	}

	//Tags
	//String Content
	public static class Builder extends BaseHtmlBuilder<HtmlRow, Builder>
	{

		@Override
		protected HtmlRow getActual()
		{
			return new HtmlRow();
		}

		@Override
		protected Builder getActualBuilder()
		{
			return this;
		}
	}

}
