package sbt.automization.util;

import org.apache.commons.io.FileUtils;
import sbt.automization.data.ExplorationSite;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class Util
{
	public static void serializeExplorationSiteToFile(ExplorationSite explorationSite, String fileName)
	{
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream outputStream = null;

		try
		{
			fileOutputStream = new FileOutputStream(fileName);
			outputStream = new ObjectOutputStream(fileOutputStream);

			outputStream.writeObject(explorationSite);

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

	public static ExplorationSite readSerializedExplorationSite(String filePath)
	{
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		ExplorationSite explorationSite = null;

		try
		{
			fileInputStream = new FileInputStream(filePath);
			objectInputStream = new ObjectInputStream(fileInputStream);
			explorationSite = (ExplorationSite) objectInputStream.readObject();
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

		return explorationSite;
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

	public static <T>Collection<List<T>> separateBasedOnSize(List<T> inputList, int size)
	{
		final AtomicInteger counter = new AtomicInteger(0);
		return inputList.stream().collect(Collectors.groupingBy(l -> counter.getAndIncrement()/size)).values();
	}
}
