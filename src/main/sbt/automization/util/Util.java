package sbt.automization.util;

import org.apache.commons.io.FileUtils;
import sbt.automization.data.refactoring.DataTable;
import sbt.automization.data.refactoring.Parameter;
import sbt.automization.data.refactoring.Probe;
import sbt.automization.data.refactoring.Sample;
import sbt.automization.data.refactoring.references.RefSample;
import sbt.automization.data.refactoring.references.Reference;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Class with static utility methods
 */
public final class Util
{
	private Util() {}

	/**
	 * Method is used for creating and saving test exploration site objects
	 *
	 * @param dataTable the object to serialize
	 * @param fileName  the name of the created file
	 */
	public static void serializeDatatableToFile(sbt.automization.data.refactoring.DataTable dataTable, String fileName)
	{
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileName) ;
		     ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream) ;)
		{
			outputStream.writeObject(dataTable);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Method is used for reading test exploration site objects
	 *
	 * @param filePath the location of the serialized object file
	 * @return a ExplorationSite object
	 */
	public static sbt.automization.data.refactoring.DataTable readSerializedDatatable(String filePath)
	{
		sbt.automization.data.refactoring.DataTable table = null;

		try (FileInputStream fileInputStream = new FileInputStream(filePath) ;
		     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream) ;)
		{
			table = (sbt.automization.data.refactoring.DataTable) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return table;
	}

	/**
	 * Method used to export files from with the program resource folder
	 *
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
	 *
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
	 *
	 * @param inputList a list
	 * @param size      the maximal size per list
	 * @param <T>       the object which is put into a list
	 * @return a collection of lists with maximal sizes
	 */
	public static <T> Collection<List<T>> separateBasedOnSize(List<T> inputList, int size)
	{
		final AtomicInteger counter = new AtomicInteger(0);
		return inputList.stream().collect(Collectors.groupingBy(l -> counter.getAndIncrement() / size)).values();
	}

	/**
	 * Used to detect if there is layers inside of exploration sites where the key value is not empty
	 *
	 * @param dataTables list of exploration sites
	 * @param outcrop    specifies which layers should be looked at
	 * @return true, if there is a non empty value, false, if all values are empty
	 */
	public static boolean thereExistsAnTableWithData(List<DataTable> dataTables, String outcrop, Reference reference)
	{
		for (DataTable dataTable : dataTables)
		{
			if (dataTable.containsValueFor(reference)) return true;
			if (thereExistsAnParameterWithData(dataTable, reference)) return true;
			if (thereExistsAnSampleWithData(dataTable, outcrop, reference)) return true;
		}
		return false;
	}

	private static boolean thereExistsAnParameterWithData(DataTable dataTable, Reference reference)
	{
		List<Parameter> parameters = new ArrayList<>();

		if (dataTable instanceof Probe)
		{
			List<Parameter> parameterList = ((Probe) dataTable).getParameters();
			parameters.addAll(parameterList);
		}
		if (dataTable instanceof Sample)
		{
			List<Parameter> parameterList = ((Sample) dataTable).getParameters();
			parameters.addAll(parameterList);
		}

		for (Parameter parameter : parameters)
		{
			if (parameter.containsValueFor(reference)) return true;
		}

		return false;
	}

	public static boolean thereExistsAnSampleWithData(DataTable dataTable, String outcrop, Reference reference)
	{
		List<Sample> samples;

		if (dataTable instanceof Probe)
		{
			if (! "".equals(outcrop))
			{
				samples = ((Probe) dataTable).getSamplesBy(RefSample.OUTCROP, outcrop);
			} else
			{
				samples = ((Probe) dataTable).getSamples();
			}

			for (Sample sample : samples)
			{
				if (sample.containsValueFor(reference)) return true;
				if (thereExistsAnParameterWithData(sample, reference)) return true;
			}
		}
		return false;
	}

	/**
	 * Method used to retrieve all ExplorationSites which contain at least one layer from a specified outcrop.
	 *
	 * @param tables   a List of ExplorationSite
	 * @param outcrop a String
	 * @return a List of ExplorationSite
	 */
	public static List<DataTable> getProbesWhichIncludeOutcrop(List<DataTable> tables, String outcrop)
	{
		List<DataTable> probesWithOutcrop = tables.stream()
				.filter(table -> table instanceof Probe)
				.filter(table -> ((Probe) table).hasSampleWith(RefSample.OUTCROP, outcrop))
				.collect(Collectors.toList());

		return probesWithOutcrop;
	}

	/**
	 * Method calculates the optimal size of each sample and adds the rest to the last sample.
	 *
	 * @param volume a Double of the Heap volume
	 * @param count  an Int of the amount of required samples
	 * @return an Array of sample volumes
	 */
	public static int[] calculateHeapSampleSizes(double volume, int count)
	{
		if (count <= 0 || volume <= 0) return null;

		double x = volume / count;

		double modulo = x % 5;

		x -= modulo;

		double sumOfModulo = modulo * count;

		int[] sampleSizes = new int[count];

		for (int i = 0 ; i < count - 1 ; i++)
		{
			sampleSizes[i] = (int) x;
		}

		sampleSizes[count - 1] = (int) (x + Math.round(sumOfModulo));

		return sampleSizes;
	}
}
