package sbt.automization.export;

import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Examination;
import sbt.automization.templates.HtmlTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Abstract class for an export strategy pattern
 */
public abstract class ATemplateExport
{
	HtmlTemplate tableExportStrategy;

	ATemplateExport(final HtmlTemplate tableExportStrategy)
	{
		this.tableExportStrategy = tableExportStrategy;
	}

	public ATemplateExport() {

	}

	public void export(Examination examination) throws Exception {
		export(getPath(), format(examination));
	}

	abstract String format(Examination examination);

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

	public void export(List<DataTable> tables)
	{
		try {
			export(getPath(), format(tables));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	abstract String format(List<DataTable> tables);

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
