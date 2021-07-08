package sbt.automization.export;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.TableInformation;
import sbt.automization.templates.IHtmlTable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Abstract class for an export strategy pattern
 */
public abstract class ATemplateExport
{
	/**
	 * Template to be exported
	 */
	IHtmlTable tableStrategy;

	ATemplateExport(final IHtmlTable tableStrategy)
	{
		this.tableStrategy = tableStrategy;
	}

	public ATemplateExport() {

	}

	public void export(TableInformation tableInformation) throws Exception {
		export(getPath(), format(tableInformation));
	}

	abstract String format(TableInformation tableInformation);

	/**
	 * Create an output stream to write the provided content into a new file.
	 * @param path used as location and name of the file to write
	 * @param content used as String
	 */
	private void export(String path, String content) throws Exception {
		try (FileOutputStream fileOutputStream = new FileOutputStream(path);
			 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
			 BufferedWriter bw = new BufferedWriter(outputStreamWriter))
		{
			bw.write(content);
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new Exception("Export failed.");
		}
	}

	/**
	 * Export variant used for testing template generation
	 * @param explorationSites a list of exploration sites
	 */
	public void export(List<ExplorationSite> explorationSites)
	{
		try {
			export(getPath(), format(explorationSites));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	abstract String format(List<ExplorationSite> explorationSites);

	/**
	 * Export variant used for testing template generation
	 * @param htmlCode a html code fragment
	 */
	public void exportAsShowcase(String htmlCode)
	{
		try {
			export(System.getProperty("user.dir").concat(File.separator).concat("testShowcase.html"), format(htmlCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	abstract String format(String text);

	public String getPathToShowcase()
	{
		return System.getProperty("user.dir").concat(File.separator).concat("testShowcase.html");

	}

	abstract String getPath();
}
