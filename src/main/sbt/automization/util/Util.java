package sbt.automization.util;

import org.apache.commons.io.FileUtils;
import sbt.automization.data.ExplorationSite;
import sbt.automization.data.Layer;

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

	public static void exportFile(String filePath) throws IOException
	{
		String pathname = System.getProperty("user.dir").concat(File.separator).concat(filePath);

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

	/**
	 * Used to detect if there are exploration sites where the key value is not empty
	 * @param explorationSites list of exploration sites
	 * @param key String that represents a key to get information from the exploration site
	 * @return true, if there is a non empty value, false, if all values are empty
	 */
	public static boolean thereExistsAnExplorationSiteWithData(List<ExplorationSite> explorationSites, String key)
	{
		for (ExplorationSite explorationSite : explorationSites)
		{
			if (!"-".equals(explorationSite.getInformation(key))) return true;
		}
		return false;
	}
	/**
	 * Used to detect if there are layers inside of exploration sites where the key value is not empty
	 * @param explorationSites list of exploration sites
	 * @param outcrop specifies which layers should be looked at
	 * @param key String that represents a key to get information from the layers
	 * @return true, if there is a non empty value, false, if all values are empty
	 */
	public static boolean thereExistsAnExplorationSiteLayerWithData(List<ExplorationSite> explorationSites, String outcrop, String key)
	{
		for (ExplorationSite explorationSite : explorationSites)
		{
			List<Layer> layersWithOutcrop = explorationSite.getLayersWithOutcrop(outcrop);
			for (Layer layer : layersWithOutcrop)
			{
				if (!"-".equals(layer.getInformation(key))) return true;
			}
		}
		return false;
	}
}
