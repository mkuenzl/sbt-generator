package sbt.automization.util;

import org.apache.commons.io.FileUtils;
import sbt.automization.data.Erkundungsstelle;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Util
{
	public static void serializeErkundungsstelleToFile(Erkundungsstelle erkundungsstelle, String fileName)
	{
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream outputStream = null;

		try
		{
			fileOutputStream = new FileOutputStream(fileName);
			outputStream = new ObjectOutputStream(fileOutputStream);

			outputStream.writeObject(erkundungsstelle);

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fileOutputStream != null)
			{
				try
				{
					assert outputStream != null;
					outputStream.flush();
					outputStream.close();
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

	public static Erkundungsstelle readSerializedErkundungsstelle(String filePath)
	{
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		Erkundungsstelle erkundungsstelle = null;

		try
		{
			fileInputStream = new FileInputStream(filePath);
			objectInputStream = new ObjectInputStream(fileInputStream);
			erkundungsstelle = (Erkundungsstelle) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fileInputStream != null)
			{
				try
				{
					//noinspection ConstantConditions
					objectInputStream.close();
					fileInputStream.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return erkundungsstelle;
	}

	public static void exportExcelTemplate() throws IOException
	{
		String filePath = "/sbt-excel-template.xlsx";
		String pathname = System.getProperty("user.dir") + filePath;

		if (new File(pathname).exists()) return;

		URL inputUrl = Util.class.getResource(filePath);

		File dest = new File(pathname);

		if (! dest.exists() && ! dest.isDirectory())
		{
			Path path = Paths.get(dest.getPath()).getParent();
			if (! Files.exists(path))
			{
				Files.createDirectories(path);
			}
			if (inputUrl != null && dest != null)
			{
				FileUtils.copyURLToFile(inputUrl, dest);
			}

		}
	}
}
