package sbt.automization.util;

import org.junit.Test;
import sbt.automization.export.HtmlTemplateExportStrategy;
import sbt.automization.templates.Bericht_OB_Template;

import java.io.IOException;

public class UtilTest
{
	@Test
	public void exportCSVTemplate() throws IOException
	{
		Util.exportExcelTemplate();
	}
}
