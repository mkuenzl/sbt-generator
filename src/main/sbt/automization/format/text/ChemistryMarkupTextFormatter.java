package sbt.automization.format.text;

import sbt.automization.html.HtmlText;

public final class ChemistryMarkupTextFormatter extends AbstractTextFormatter
{
	@Override
	public String format(final String classification)
	{
		StringBuilder stringBuilder = new StringBuilder();

		switch (classification)
		{
			case "Z0":
			case "DK0":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: white;font-weight: bold;\n\n" +
								"  color: black\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "Z0*":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: #00FFFF;font-weight: bold;\n" +
								"  color: black\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "Z1":
			case "Z1.1":
			case "RC1":
			case "DK1":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: #00FF00;font-weight: bold;\n" +
								"  color: black\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "Z1.2":
			case "RC2":
			case "DK2":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: yellow;font-weight: bold;\n" +
								"  color: black\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "Z2":
			case "RC3":
			case "DK3":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: red;font-weight: bold;\n" +
								"  color: white\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case ">Z2":
			case ">DK3":
			case ">RC3":
			case "gefährlich":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"background-color: black;font-weight: bold;\n" +
								"  color: white\">")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			case "nicht gefährlich":
				stringBuilder.append(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("<span style=\"background-color: white;font-weight: bold;\n" +
										"  color: black\">")
								.appendContent("nicht")
								.appendContent("</span>")
								.build().appendTag())
						.append(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("<span style=\"font-weight: bold\";>")
								.appendContent("gefährlich")
								.appendContent("</span>")
								.build().appendTag());
				break;
			case "nicht eingehalten":
				stringBuilder.append(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("<span style=\"font-weight: bold\";>")
								.appendContent("nicht")
								.appendContent("</span>")
								.build().appendTag())
						.append(new HtmlText.Builder()
								.appendAttribute("class", "Normal")
								.appendContent("<span style=\"font-weight: bold\";>")
								.appendContent("eingehalten")
								.appendContent("</span>")
								.build().appendTag());
				break;
			case "eingehalten":
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent("<span style=\"font-weight: bold\";>")
						.appendContent(classification)
						.appendContent("</span>")
						.build().appendTag());
				break;
			default:
				stringBuilder.append(new HtmlText.Builder()
						.appendAttribute("class", "Normal")
						.appendContent(replaceIfEmpty(classification))
						.build().appendTag());
				break;
		}

		return stringBuilder.toString();
	}

	@Override
	public String format(String firstText, String secondText)
	{
		return null;
	}
}
