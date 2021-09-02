package sbt.automization.export;

import org.docx4j.convert.in.xhtml.XHTMLImporter;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jodconverter.local.JodConverter;
import sbt.automization.data.DataTable;
import sbt.automization.data.Examination;
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

	public void export(String path, List<DataTable> tables)
	{
		try {
			export(getPath(path), format(tables));
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

	public abstract String getPath();

	public abstract String getPath(String path);
}
