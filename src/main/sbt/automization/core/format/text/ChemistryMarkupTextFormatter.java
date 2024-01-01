package sbt.automization.core.format.text;

import sbt.automization.core.html.HtmlText;

/**
 * {@link TextFormatter}-Implementation for highlighting chemical values as {@link HtmlText}s.
 */
public final class ChemistryMarkupTextFormatter extends AbstractTextFormatter
{
	@Override
	public String format(final String classification)
	{

		StringBuilder stringBuilder = new StringBuilder();

		switch (classification)
		{
			// WHITE BACKGROUND COLORING
			case "Z0", "BM-0", "BG-0", "GS-0", "GRS", "SKG", "SKA", "SFA", "BFA", "HS", "DK 0" ->
					stringBuilder.append(new HtmlText.Builder()
												 .appendAttribute("class", "Normal")
												 .appendContent(
														 "<span style=\"" +
																 "background-color: white;" +
																 "font-weight: bold;" +
																 "\n\n" +
																 "  color: black\">")
												 .appendContent(classification)
												 .appendContent("</span>")
												 .build()
												 .appendTag());

			// CYAN BACKGROUND COLORING
			case "BM-0*", "BG-0*", "Z0*" -> stringBuilder.append(new HtmlText.Builder()
																		 .appendAttribute("class", "Normal")
																		 .appendContent(
																				 "<span style=\"background-color: " +
																						 "#00FFFF;font-weight: bold;" +
																						 "\n" +
																						 "  color: black\">")
																		 .appendContent(classification)
																		 .appendContent("</span>")
																		 .build()
																		 .appendTag());


			// GREEN BACKGROUND COLORING
			case "BM-F0*", "BG-F0*", "RC-1", "HOS-1", "GS-1", "SWS-1", "CUM-1", "HMVA-1", "Z1", "Z1.1", "RC1", "DK " +
					"I" ->
					stringBuilder.append(new HtmlText.Builder()
												 .appendAttribute("class", "Normal")
												 .appendContent(
														 "<span style=\"background-color: #00FF00;font-weight: bold;" +
																 "\n" +
																 "  color: black\">")
												 .appendContent(classification)
												 .appendContent("</span>")
												 .build()
												 .appendTag());

			// YELLOW BACKGROUND COLORING
			case "BM-F1", "BG-F1", "RC-2", "HOS-2", "GS-2", "SWS-2", "CUM-2", "HMVA-2", "Z1.2", "RC2", "DK II" ->
					stringBuilder.append(new HtmlText.Builder()
												 .appendAttribute("class", "Normal")
												 .appendContent(
														 "<span style=\"background-color: yellow;font-weight: bold;" +
																 "\n" +
																 "  color: black\">")
												 .appendContent(classification)
												 .appendContent("</span>")
												 .build()
												 .appendTag());

			// ORANGE BACKGROUND COLORING
			case "BG-F2", "BM-F2" -> stringBuilder.append(new HtmlText.Builder()
																  .appendAttribute("class", "Normal")
																  .appendContent(
																		  "<span style=\"background-color: #FFC000;" +
																				  "font-weight: bold;\n" +
																				  "  color: black\">")
																  .appendContent(classification)
																  .appendContent("</span>")
																  .build()
																  .appendTag());

			// RED BACKGROUND COLORING
			case "BM-F3", "BG-F3", "RC-3", "GS-3", "Z2", "RC3", "DK III" -> stringBuilder.append(new HtmlText.Builder()
																										 .appendAttribute(
																												 "class",
																												 "Normal")
																										 .appendContent(
																												 "<span style=\"background-color: red;font-weight: bold;\n" +
																														 "  color: white\">")
																										 .appendContent(
																												 classification)
																										 .appendContent(
																												 "</span>")
																										 .build()
																										 .appendTag());

			// BLACK BACKGROUND COLORING
			case ">BM-F3", ">BG-F3", ">Z2", ">DK III", ">RC3", "gefährlich" ->
					stringBuilder.append(new HtmlText.Builder()
												 .appendAttribute("class", "Normal")
												 .appendContent(
														 "<span style=\"background-color: black;font-weight: bold;\n" +
																 "  color: white\">")
												 .appendContent(classification)
												 .appendContent("</span>")
												 .build()
												 .appendTag());
			case "nicht gefährlich" -> stringBuilder.append(new HtmlText.Builder()
																	.appendAttribute("class", "Normal")
																	.appendContent(
																			"<span style=\"background-color: white;" +
																					"font-weight: bold;\n" +
																					"  color: black\">")
																	.appendContent("nicht")
																	.appendContent("</span>")
																	.build()
																	.appendTag())
													.append(new HtmlText.Builder()
																	.appendAttribute("class", "Normal")
																	.appendContent("<span style=\"font-weight: " +
																						   "bold\";>")
																	.appendContent("gefährlich")
																	.appendContent("</span>")
																	.build()
																	.appendTag());
			case "nicht eingehalten" -> stringBuilder.append(new HtmlText.Builder()
																	 .appendAttribute("class", "Normal")
																	 .appendContent(
																			 "<span style=\"font-weight: bold\";>")
																	 .appendContent("nicht")
																	 .appendContent("</span>")
																	 .build()
																	 .appendTag())
													 .append(new HtmlText.Builder()
																	 .appendAttribute("class", "Normal")
																	 .appendContent(
																			 "<span style=\"font-weight: bold\";>")
																	 .appendContent("eingehalten")
																	 .appendContent("</span>")
																	 .build()
																	 .appendTag());
			case "eingehalten", "Auffälligkeit" -> stringBuilder.append(new HtmlText.Builder()
																				.appendAttribute("class", "Normal")
																				.appendContent(
																						"<span style=\"font-weight: bold\";>")
																				.appendContent(classification)
																				.appendContent("</span>")
																				.build()
																				.appendTag());
			case "keine Auffälligkeit" -> stringBuilder.append(new HtmlText.Builder()
																	   .appendAttribute("class", "Normal")
																	   .appendContent(
																			   "<span style=\"font-weight: bold\";>")
																	   .appendContent("keine")
																	   .appendContent("</span>")
																	   .build()
																	   .appendTag())
													   .append(new HtmlText.Builder()
																	   .appendAttribute("class", "Normal")
																	   .appendContent(
																			   "<span style=\"font-weight: bold\";>")
																	   .appendContent("Auffälligkeit")
																	   .appendContent("</span>")
																	   .build()
																	   .appendTag());
			default -> stringBuilder.append(new HtmlText.Builder()
													.appendAttribute("class", "Normal")
													.appendContent(replaceIfEmpty(classification))
													.build()
													.appendTag());
		}

		return stringBuilder.toString();
	}

	@Override
	public String format(String firstText, String secondText)
	{
		return null;
	}
}
