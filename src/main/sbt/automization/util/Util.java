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

/**
 * Class with static utility methods
 */
public final class Util
{
	private Util(){}

	/**
	 * Method is used for creating and saving test exploration site objects
	 * @param explorationSite the object to serialize
	 * @param fileName the name of the created file
	 */
	public static void serializeExplorationSiteToFile(ExplorationSite explorationSite, String fileName)
	{
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			 ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);)
		{
			outputStream.writeObject(explorationSite);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Method is used for reading test exploration site objects
	 * @param filePath the location of the serialized object file
	 * @return a ExplorationSite object
	 */
	public static ExplorationSite readSerializedExplorationSite(String filePath)
	{
		ExplorationSite explorationSite = null;

		try (FileInputStream fileInputStream = new FileInputStream(filePath);
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);)
		{
			explorationSite = (ExplorationSite) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return explorationSite;
	}

	/**
	 * Method used to export files from with the program resource folder
	 * @param fileName specifies which file should be exported
	 * @throws IOException if anything happens while reading oder writing the file
	 */
	public static void exportFile(final String pathTo, final String fileName) throws IOException
	{
		String pathname = System.getProperty("user.dir").concat(File.separator).concat(fileName);

		if (new File(pathname).exists()) return;

		URL inputUrl = Util.class.getResource(pathTo + fileName);

		File destinationFile = new File(pathname);

		if (! destinationFile.exists() && ! destinationFile.isDirectory())
		{
			Path path = Paths.get(destinationFile.getPath()).getParent();
			if (! Files.exists(path))
			{
				Files.createDirectories(path);
			}
			if (inputUrl != null && destinationFile != null)
			{
				FileUtils.copyURLToFile(inputUrl, destinationFile);
			}
		}
	}

	/**
	 * Method used to export files from with the program resource folder
	 * @param fileName specifies which file should be exported
	 * @throws IOException if anything happens while reading oder writing the file
	 */
	public static void exportFile(final String fileName) throws IOException
	{
		String pathname = System.getProperty("user.dir").concat(File.separator).concat(fileName);

		if (new File(pathname).exists()) return;

		URL inputUrl = Util.class.getResource(fileName);

		File destinationFile = new File(pathname);

		if (! destinationFile.exists() && ! destinationFile.isDirectory())
		{
			Path path = Paths.get(destinationFile.getPath()).getParent();
			if (! Files.exists(path))
			{
				Files.createDirectories(path);
			}
			if (inputUrl != null && destinationFile != null)
			{
				FileUtils.copyURLToFile(inputUrl, destinationFile);
			}
		}
	}

	/**
	 * Method can be used to separate lists into Collections of lists
	 * @param inputList a list
	 * @param size the maximal size per list
	 * @param <T> the object which is put into a list
	 * @return a collection of lists with maximal sizes
	 */
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
