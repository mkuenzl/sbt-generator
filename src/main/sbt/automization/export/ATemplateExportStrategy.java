package sbt.automization.export;

import sbt.automization.data.ExplorationSite;
import sbt.automization.data.TableInformation;
import sbt.automization.templates.IHtmlTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class ATemplateExportStrategy
{

	final IHtmlTemplate strategy;

	ATemplateExportStrategy(final IHtmlTemplate strategy)
	{
		this.strategy = strategy;
	}

	public void export(TableInformation tableInformation)
	{
		export(getPath(), format(tableInformation));
	}

	private void export(String path, String content)
	{
		BufferedWriter bw = null;
		OutputStreamWriter outputStreamWriter = null;
		FileOutputStream fileOutputStream = null;
		try
		{
			fileOutputStream = new FileOutputStream(path);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
			bw = new BufferedWriter(outputStreamWriter);
			bw.write(content);
			bw.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fileOutputStream != null)
			{
				try
				{
					fileOutputStream.flush();
					fileOutputStream.close();
					assert outputStreamWriter != null;
					outputStreamWriter.flush();
					outputStreamWriter.close();
					assert bw != null;
					bw.flush();
					bw.close();
				} catch (IOException e)
				{
					// ignored
				}
			}
		}
	}

	public String getPath()
	{
		if (TableInformation.exportPath == null)
			return System.getProperty("user.dir").concat(File.separator).concat(strategy.getExportFileName());

		return TableInformation.exportPath.concat(File.separator).concat(strategy.getExportFileName());
	}

	abstract String format(TableInformation tableInformation);

	public void export()
	{
		export(System.getProperty("user.dir").concat(File.separator).concat(strategy.getExportFileName()), strategy.constructAndGetTemplate());
	}

	public void export(List<ExplorationSite> explorationSites)
	{
		export(getPath(), format(explorationSites));
	}

	abstract String format(List<ExplorationSite> explorationSites);

}
